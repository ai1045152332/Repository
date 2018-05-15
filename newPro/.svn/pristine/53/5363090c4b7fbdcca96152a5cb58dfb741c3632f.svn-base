
var ExcellentWorks = function(el){
	this.el = el;
	this.currentItem = null;
	this.workList = this.el.getElementsByClassName('eWork_list')[0];
	this.contentWrap = this.el.parentNode.nextElementSibling.getElementsByClassName('eWork_contentWrap')[0];
	this.contentInner = this.contentWrap.getElementsByClassName('eWork_contentInner')[0];
};
ExcellentWorks.prototype = {
	init: function(){ // 初始化
		this.touchEvents.initEvent();
		this.addEvents();
	},
	touchEvents: {
		touchstart: "touchstart",
		touchmove: "touchmove",
		touchend: "touchend",
		hasTouch: (function(){
			return 'touchstart' in window;
		})(),
		initEvent: function () { // 判断设备是否支持touch事件，不支持使用鼠标事件
			if (!this.hasTouch) {
				this.touchstart = "mousedown";
				this.touchmove = "mousemove";
				this.touchend = "mouseup";
			}
		}
	},
	addEvents: function(){ // 注册事件
		var me = this,
			listWrapperEl = this.el.getElementsByClassName('eWork_listWrapper')[0];
		
		// 创建作品列表
		window.addEventListener('load', function(e){ 
			setTimeout(function(){ 
				// 创建iScroll
				me.list_sl_obj = new IScroll(listWrapperEl,{ 
					scrollbars: true
				}); 
				if(me.list_sl_obj){
					me.createSLCallBack(me.list_sl_obj);
				}
			},100);
		}, false); 

		// 给作品列表项注册事件
		if(this.workList){
			var els = this.workList.getElementsByTagName('li');
			if(els.length==0){return;}
			for(var i=0, len = els.length; i<len; i++){
				els[i].index = i+1;
				els[i].addEventListener(me.touchEvents.touchstart, function(evt){ 
					me.currentItem = this;
					me.showDetail( function(){me.callback();} );
				}, false);
			}
		}
	},
	
	showDetail: function(callBack){ // 显示作品详情
        setStopSignValue('-1');
		stopAllVideos();
		var docEl = document.documentElement;
		this.contentWrap.style.width = docEl.clientWidth + 'px';
		this.contentWrap.style.height = docEl.clientHeight + 'px';
		this.contentWrap.style.display = 'block';
		this.contentInner.style.height = docEl.clientHeight-44 + 'px';
		callBack && callBack();

        if (WORK_POP || WORK_POP == null) {
            WORK_POP = this;
            docClick(null);
        }
	},
	createDetailView: function(){ // 生成详情界面相关
		var me = this,
			currentIndex = 1,
			collection = [];

		// 生成作品详情列表
		var getContent = function(dataItem){
			var str = '';
			dataItem.path = dataItem.path.split(",");
			for(var i=0, len = dataItem.path.length; i<len; i++){
				str += '<img src='+  dataItem.path[i] +' class="eWork_scrollItem" height="952" />';
			}
			return str;
		};
		var getWorkList = function(){
			var list = [];
			if(me.workList){
				var els = me.workList.getElementsByTagName('li');
				for(var i=0, len = els.length; i<len; i++){ // 获取作品详情列表
					var obj = JSON.parse(els[i].getAttribute("params"));
					list.push('<li author="'+ obj.author +'" ititle="'+ obj.title +'">'+getContent(obj)+ '</li>');
				}
			}
			return list;
		};

		// 生成作品详情弹出层
		var currentItem_data = JSON.parse(this.currentItem.getAttribute('params'));
		currentIndex = this.currentItem.index;
		collection.push('<aside class="eWork_title">');
		collection.push('	<span class="eWork_titleName" style="color: white">'+ currentItem_data.title +'</span>');
		collection.push('	<span class="eWork_authorInfo">'+ currentItem_data.author +'</span>');
		collection.push(' </aside>');
		collection.push(' <div class="opaLayer"></div>');
		collection.push('<article class="eWork_content">');
		collection.push('	<div class="eWork_scrollInner"><ul>'+ getWorkList().join('') + '</ul></div>');
		collection.push('</article>'); 
		this.contentWrap.getElementsByClassName('eWork_article')[0].innerHTML = collection.join('');
	
		// 图片等比缩放
		var si = 0;  // 用于标识主题展示二级页面是否生成了iscroll
		var contentEl = this.contentWrap.getElementsByClassName('eWork_content')[0];
		if(contentEl){
			var imgEls = this.contentWrap.getElementsByClassName("eWork_scrollItem");
			for(var i=0, len= imgEls.length; i<len; i++){
				imgEls[i].index = i;
				imgEls[i].onload = function() {
					if(contentEl.clientWidth < this.width){
						this.height = contentEl.clientWidth * this.height/this.width;
						this.width = contentEl.clientWidth;
					}
				};
				imgEls[i].ontouchstart = function() {
					if(si == 0){
						si = 1;
						setTimeout(function(){ 
							// 创建iScroll
							me.content_sl_obj = new IScroll(contentEl,{ 
//								scrollbars: true
							}); 
							if(me.content_sl_obj){
								me.createSLCallBack(me.content_sl_obj);
							} 
						},100);
					}
				};
			}
		}
		
		// 初始化弹出层外观
		var total = getWorkList().length;
		var prevBtn = this.contentWrap.getElementsByClassName('eWork_prev')[0];
		var nextBtn = this.contentWrap.getElementsByClassName('eWork_next')[0];
		if(total==1){
			prevBtn.style.opacity = ".5";
			nextBtn.style.opacity = ".5";
			return;
		}else{
			if(currentIndex==1){
				prevBtn.style.opacity = ".5";
			}else if(currentIndex == total){
				nextBtn.style.opacity = ".5";
			}
		}
		
		// 滑动实例化
		var container = this.contentWrap.getElementsByClassName('eWork_scrollInner')[0];
		var sliderInstance = new WorksSwipe(container, {
			startSlide:currentIndex-1,
			speed: 500,
			callback: function(evt, index, elem) {
				me.content_sl_obj && me.content_sl_obj._translate(0,0); // 滚动条回到顶部
				me.contentWrap.getElementsByClassName('eWork_titleName')[0].innerHTML = elem.getAttribute('ititle');
				me.contentWrap.getElementsByClassName('eWork_authorInfo')[0].innerHTML = elem.getAttribute('author');
				switch(index){
					case 0:
						prevBtn.style.opacity = ".5";
						break;
					case total-1:
						nextBtn.style.opacity = ".5";
						break;
					default:
						prevBtn.style.opacity = "1";
						nextBtn.style.opacity = "1";
				}
			}
		});
		prevBtn.addEventListener(me.touchEvents.touchstart, function(e){ 
			me.content_sl_obj && me.content_sl_obj._translate(0,0); // 滚动条回到顶部
			sliderInstance.prev();
		}, false);
		nextBtn.addEventListener(me.touchEvents.touchstart, function(e){ 
			me.content_sl_obj && me.content_sl_obj._translate(0,0); // 滚动条回到顶部
			sliderInstance.next();
		}, false);
		
	},
	hideDetail: function(){
        setStopSignValue('0');
		playAllVideos();

        this.contentWrap.style.display = 'none';

        if (this.content_sl_obj) {
            this.content_sl_obj.destroy();
            this.content_sl_obj = null;
        }

        //销毁二级页面
		this.contentWrap.getElementsByClassName('eWork_article')[0].innerHTML = "";

        WORK_POP = null;
	},
	callback: function(){ // 显示作品详情后的回调
		var me = this;
			goBackEl = this.contentWrap.getElementsByClassName('eWork_goBack')[0];
        
		// 生成详情界面
		this.createDetailView();
		
		// 给返回按钮注册关闭处理方法	
		goBackEl.addEventListener(me.touchEvents.touchstart, function(e){ 
			me.hideDetail();
		}, false);
	},
	createSLCallBack: function(slObj){
		// 列表初始化后，隐藏滚动条
		if(slObj.indicators){
			slObj.indicators[0].indicatorStyle['transition-duration'] = '350ms';
			slObj.indicators[0].indicatorStyle['opacity'] = '0';
		}
		
		// 列表滚动结束，隐藏滚动条
		slObj.on("scrollEnd",function(){
			//console.log('scrollEnd')
			if(this.indicators){
				this.indicators[0].indicatorStyle['transition-duration'] = '350ms';
				this.indicators[0].indicatorStyle['opacity'] = '0';
			}
		});
		// 列表开始滚动，显示滚动条
		slObj.on("scrollStart",function(){
			//console.log('scrollStart') //scrollMove
			if(this.indicators){
				this.indicators[0].indicatorStyle['transition-duration'] = '0ms';
				this.indicators[0].indicatorStyle['opacity'] = '0.8';
			}
		});
	}
};

































