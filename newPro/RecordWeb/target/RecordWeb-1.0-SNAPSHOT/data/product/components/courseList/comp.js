// 当前课程和下节课程对象
var CoursePop = function(container,options){
	this.container = $(container);
	this.options = options || {};
    var serverDomain = "";//this.container.attr("data-ip");
    if (window.serverDomain) {
        serverDomain = window.serverDomain;
    }
    if (serverDomain) {
    	this.options.url = 'http://'+ serverDomain + '/ManagementCenter/curriculum/Curriculum_returnCourseInfoJsonp.do';
    	this.options.url2 = 'http://'+ serverDomain + '/ManagementCenter/curriculum/Curriculum_returnCourseInfoJson.do';
    } else {
    	this.options.url = '/ManagementCenter/curriculum/Curriculum_returnCourseInfoJsonp.do';
    	this.options.url2 = '/ManagementCenter/curriculum/Curriculum_returnCourseInfoJson.do';
    }
	this.init();
}; 
CoursePop.prototype = {
	init: function(){
		this.currentCourseEl = this.container.find('.swiper-slide:first');
		this.nextCourseEl = this.container.find('.swiper-slide:last');
		this.courseListWrap = this.container.closest('.item').next().find('.courseListWrap');
		
		var data = this.courseListWrap.attr('default-data');
		if(data != ''){
			data = JSON.parse(data);
			this.cData = data;
			this.data2Html(this.cData);
		}
		
		this.requestCourseInfo(this);
		this.addEvent();

        this.startUpdateShow();
        this.getLatestData();
	},
    destroy: function(){
        if(this.updateShowTimer){clearInterval(this.updateShowTimer);}
        this.updateShowTimer = null;

        if(this.getDataTimer){clearInterval(this.getDataTimer);}
        this.getDataTimer = null;
    },
    onWebAccessResult: function(result, data) {
        if(result=='200'){
            var curData = this.courseListWrap.attr('default-data');
            if (data != curData) {
                this.courseListWrap.attr('default-data',data);
                this.cData = JSON.parse(data);

                // 更新课程列表
                this.data2Html(this.cData);
            }
        }
    },
	requestCourseInfo: function(obj){
        if (0/*window.isAndroid()*/) {
            var dt = 'idstr='+obj.options.id +'&t='+ new Date().getTime();
            window.nativeWebAccess(obj.options.url2, dt, "webAccessCallback", obj.options.blk_id);
            return;
        }

		var me = obj,
		url = me.options.url;
		$.ajax({ 
			url: url,
			data:{idstr: me.options.id, t: new Date().getTime()},
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getCourseData',
			success: function(data,textStatus,XHR){ 
				if(textStatus=='success'){
                    var newData = JSON.stringify(data);
                    var curData = me.courseListWrap.attr('default-data');
                    if (newData != curData) {
                        me.courseListWrap.attr('default-data',newData);
                        me.cData = data;

                        // 更新课程列表
                        me.data2Html(me.cData);
                    }
				}else{
				    // 如果请求失败，则保持本地的数据
				}
			}
		});
	},
    getLatestData: function(){
        // 课表表数据的更新(从服务器获取最新的数据到本地)
        var that = this;
        this.getDataTimer = setInterval(function(){
            that.requestCourseInfo(that);
        }, 600000);
    },
    // 当前课程和下节课程的实时更新
    startUpdateShow: function(){
        var me = this;
        this.updateShowTimer = setInterval(function(){
            me.updateCourse(me.cData.courseList);
        },30000);
    },
    showDetailPage: function() {
        setStopSignValue('-1');
        stopAllVideos();
        this.courseListWrap.width($(window).width());
        this.courseListWrap.height($(window).height());
        var that = this;
        this.courseListWrap.show(10,function(){ // 课程表显示后，回调: 创建iscroll
            that.myIScroll = new IScroll('.courseTable',{
                scrollbars: true
            });
        });

        if (COURSE_POP || COURSE_POP == null) COURSE_POP = this;
    },
    hideDetailPage: function() {
        setStopSignValue('0');
        playAllVideos();
        this.courseListWrap.hide();
        this.myIScroll.destroy();
        this.myIScroll = null;
        COURSE_POP = null;
    },
	addEvent: function(){
		var me = this,
			coursePop = this.container.find('.coursePop');

		coursePop.on('click', function(){
            me.showDetailPage();
		}); 
		// 点击课程表  自动关闭时间
		/*this.courseListWrap.find('.courseList').on('click', function(){
			me.hideDetailPage();
		});*/
		// 点击返回按钮 关闭事件
		this.courseListWrap.find('.eWork_ctrlLayerc').on('click', function(){
			me.hideDetailPage();
		});
	},
	getTodayCourse: function(courses){ // 获取当天课程列表
		var list,
			weekday = new Date().getDay();
		switch(weekday) {
			case 0:
				weekday = "星期日";
				break;
			case 1:
				weekday = "星期一";
				break;
			case 2:
				weekday = "星期二";
				break;
			case 3:
				weekday = "星期三";
				break;
			case 4:
				weekday = "星期四";
				break;
			case 5:
				weekday = "星期五";
				break;
			case 6:
				weekday = "星期六";
		}
		$(courses).each(function(){
			if(this.data == weekday){
				list = this.list;
				return false; // 跳出循环
			}
		});	
		return list;
	},
	formatCourse: function(todayCourses){ // 对课程对象的时间格式化
		var fCourses = [], startTime, endTime;
		$(todayCourses).each(function(i){
			var time = this.time;
			startTime = time.substring(0,time.indexOf('-'));
			endTime = time.substring(time.indexOf('-')+1);
			fCourses.push({cIndex:i, cName:this.coursename, sTime:startTime, eTime:endTime});
		});
		return fCourses;
	},
	formatTimeStr: function(_time,isHours){ 
		var index = isHours ? _time.indexOf(':') : _time.indexOf(':')+1;
		if(index<0) index = isHours ? _time.indexOf('：') : _time.indexOf('：')+1;
		if(index>-1){
			formatTime = isHours ? _time.substring(0,index) : _time.substring(index);
		}	
		return Number(formatTime);
	},
	getSeconds: function(hours,minutes){ // 返回总秒数
		return  hours*3600 + minutes*60;
	},
	data2Html: function(data){
    	this.updateCourse(data.courseList);
		this.createCourseList(data);
	},
    createCourseList: function(courseData){
        // 清空课程列表
        var courseTable = this.courseListWrap.find('.courseTable')[0];
        courseTable.removeChild(courseTable.children[0]);
        courseTable.innerHTML = "<table class=\"courseList\"></table>";

	    // 生成课程列表(二级页面)
		var courseList = courseData.courseList,
			$tEl = this.courseListWrap.find('.courseList'),
			tBody = document.createElement('tbody');
		
		// 生成课程表标题	
		$tEl.append('<caption>' + courseData.cListName + '</caption>');
		
		// 生成表头
		var getTheadStr = function(){
			var str = '';
			$(courseList).each(function(){
				str += '<th>' + this.data + '</th>';
			});
			return '<tr><th width="5%"></th><th>上课时间</th>' + str + '</tr>';
		};
		
		// 生成星期一的课程
		var getMainContent = function(){
			var str = '';
				mondayList = courseList[0].list;
			$(mondayList).each(function(i){
				str += '<tr><td>' + (i+1) + '</td>' + '<td>' + this.time + '</td>' + '<td>' + this.coursename + '</td></tr>';
			});
			return  str;
		};
		
		// 生成其他星期的课程
		var getOtherDayContent = function(list){
			var str = '';
			$(list).each(function(i){
				str = '<td>' + this.coursename + '</td>';
				$(tBody).find('tr').eq(i+1).append(str);
			});
		};
		
		var tHeadStr = getTheadStr();
		var mainContent = getMainContent();
		
		// 插入表头和星期一的数据
		$(tBody).append(tHeadStr + mainContent); 
		
		// 插入其它星期的数据
		$(courseList).each(function(dayIndex){
			if(dayIndex>0){ // 从星期二开始
				getOtherDayContent(this.list);
			}
		});
		
		// 插入table
		$tEl.append(tBody);
	},
	updateCourse: function(_courses){
        // 更新当前课程、下节课程显示
		var me = this,
			todayCourses = this.getTodayCourse(_courses);
		// 如果当天无课
		if(!todayCourses.length){
			return;
		}
		
		// 对课程对象的时间属性值作处理
		var courseList = this.formatCourse(todayCourses),
			cCount = courseList.length; // 当天的总课程数	
		console.log("-------------------updateCourse----------------cCount : "+cCount);
		// 获取当前系统时间
		var dt = new Date(), 
			currentTime = this.getSeconds(dt.getHours(), dt.getMinutes());	
			
		// 将当前系统时间与课程表中的每节课的开始时间和结束时间进行比对
		$(courseList).each(function(){ 
			var sTime_H = me.formatTimeStr(this.sTime,true),
				sTime_M = me.formatTimeStr(this.sTime),
				eTime_H = me.formatTimeStr(this.eTime,true),
				eTime_M = me.formatTimeStr(this.eTime),
				sCourseTime = me.getSeconds(sTime_H, sTime_M),
				eCourseTime = me.getSeconds(eTime_H, eTime_M);
				
			/*if(this.cIndex < cCount){ // 如果当前节次有课，且不是最后一节课
				var next_sTime = courseList[this.cIndex+1].sTime,
					next_sTime_H = me.formatTimeStr(next_sTime,true),
					next_sTime_M = me.formatTimeStr(next_sTime),
					next_sCourseTime = me.getSeconds(next_sTime_H, next_sTime_M);
			}*/

			// 如果当前系统时间在当前节次的时间范围内
			if(currentTime >= sCourseTime && currentTime <= eCourseTime){
				console.log("................updateCourse.....................A...............");
				me.showDetailCourse(this, courseList, cCount, true);
			}
			
			// 如果当前系统时间不在当前节次的时间范围内
			if(currentTime > eCourseTime/* && currentTime < next_sCourseTime*/){
				console.log("................updateCourse.....................B................");
				me.showDetailCourse(this, courseList, cCount);
			}
		});
	},
    showDetailCourse: function(courseObj, list, total, timeScope){
        console.log(courseObj.cIndex+"..............showDetailCourse.................. "+total);
        if(courseObj.cIndex < total-1){ // 如果当前节次不是最后节次
            // 当前课程
            if(courseObj.cName){ // 如果当前节次有课
                //不区分课间
                var _text = timeScope ? courseObj.cName : '无';
                console.log("..............showDetailCourse当前课程.................. "+_text);
                this.currentCourseEl.text(_text);
            }else{
                this.currentCourseEl.text('无');
            }

            // 下节课程
            if(list[courseObj.cIndex].cName && list[courseObj.cIndex+1].cName){ // 如果当前节次有课,并且下节课不为空
                console.log("..............showDetailCourse下节课程 ................."+list[courseObj.cIndex+1].cName);
                this.nextCourseEl.text(list[courseObj.cIndex+1].cName);
            }else{
                this.nextCourseEl.text('无');
            }
        }else{ // 如果当前节次是最后节次
            if(courseObj.cName){
                this.currentCourseEl.text(courseObj.cName);
            }else{
                this.currentCourseEl.text('无');
            }
            this.nextCourseEl.text('无');
        }
        return false;
    }
};

// 课程表对象
var Curriculum = function(){};
Curriculum.prototype = {
	init: function(){ 
		var courseWrapEl  = document.getElementsByClassName('courseWrap')[0],
			cListId = courseWrapEl.getAttribute("course-id");
			
		// 创建课程表对象
		this.courseObj = new CoursePop(courseWrapEl,{
            blk_id: this.blk_id,
			id: cListId
		});
	},
    destroy: function(){
        this.courseObj.destroy();
    },
    onWebAccessResult: function(result, data) {
        if (this.courseObj) {
            this.courseObj.onWebAccessResult(result, data);
        }
    }
};
