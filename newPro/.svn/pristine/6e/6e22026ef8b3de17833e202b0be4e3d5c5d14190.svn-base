
/**
 * User: hht-zsl
 * Date: 14-4-29
 * 动态
 */
var classComp = function (container) {
	this.container = $(container);
	this.bid = container.parentNode.id;
};
classComp.prototype = {
		init : function () {
			var me = this;
			me.RefreshUpdateClass();
		},
		RefreshUpdateClass:function(){
			 //--
			var animateDistanceClass,ulsClass,lisClass,mytimeClass,newDataObjClass;
			var i=dataObjLenClass=objCountClass=objImgsClass=0;

			this.mydivClass = document.createElement('div');
			this.mydivClass.id = 'refUpdateDivClass';
			this.mydivClass.className = 'refUpdateDivClass';
			this.mytitleclass = document.createElement('div');
			this.mytitleclass.className = 'mytitleclass';
			this.mytitleclass.innerHTML = '班级动态';
			this.myimgsclass = document.createElement('div');
			this.myimgsclass.className = 'myimgsclass';
			this.myimgsclass.id = 'myimgsclass';
			this.mytextclass = document.createElement('div');
			this.mytextclass.className = 'mytextclass';
			this.mytextclass.id = 'mytextclass';
			this.mytextclass.innerHTML = '<div class="bg"></div><aside class="left"><div class="avatar"></div><div class="realname"></div></aside><aside class="right"><span class="total"></span><span class="date"></span></aside><div class="content"></div>';
			this.mydivClass.appendChild(this.mytitleclass);
			this.mydivClass.appendChild(this.myimgsclass);
			this.mydivClass.appendChild(this.mytextclass);
			var tag27 = document.getElementById('editItem27');
			if(tag27){
				document.getElementById('editItem27').appendChild(this.mydivClass);
			}
			
			var classid = "";
			if (window.serverClassid) {
				classid = window.serverClassid;
	        }
			var dataop = {
					classid: classid,
					pageSize: 10,
					currentPage: 1,
					sessionkey: 'phpclient',
					resttime: ''
			};
			
		    var timestamp = new Date().getTime();
		    var times = timestamp.toString();//一定要转换字符串
		    dataop.resttime = times / 1000;

			var a1 = getSign(dataop); 
			dataop.restauth = a1;
			var url = getUrl(dataop);
			
			
			ulsClass = document.createElement('ul');
			this.myimgsclass.appendChild(ulsClass);
			ulsClass.className = 'mypicclass';
			
			
			function newImgSrc(url){
				var img = new Image();
				img.src = url;
				return img;
			}
			
			
			function imgResizClass(obj){ //图片等比缩放
				var img = new Image();
				img.src = obj.src;
				img.onload = function(){
					var objParent = obj.parentNode;
					var imgInitW = obj.clientWidth;
					var imgInitH = obj.clientHeight;
					var objParentValue = objParent.clientWidth/objParent.clientHeight;
					if(imgInitW/imgInitH < objParentValue){ //判断形状
						obj.style.width = objParent.clientHeight/obj.clientHeight*imgInitW + 'px';
						obj.style.height = objParent.clientHeight + 'px'; 
					}else{
						obj.style.height = objParent.clientWidth/imgInitW*imgInitH + 'px';
						obj.style.width = objParent.clientWidth + 'px';
					}
					obj.style.marginTop = (objParent.clientHeight - obj.clientHeight)/2 + 'px'; 
				}
			}
			
			
			function lisCount(obj){ //图片排列宽度
				var currentLiw = 0;
				for(var i=0;i<obj.childNodes.length;i++){
					var curLi = obj.childNodes[i];
					var curImg = curLi.childNodes[0];
					curLi.style.width = obj.parentNode.clientWidth + 'px';
					currentLiw = currentLiw + curLi.clientWidth;
					if(curImg){
						curImg.style.cssText = '';
						imgResizClass(curImg);
					}
				}
				
				obj.style.width = currentLiw + 'px';
				animateDistanceClass = obj.childNodes[0].clientWidth;
				dataObjLenClass = obj.childNodes.length;
			}
			
			function slideChangeWindow(listLen){ //点击暂停动画改变窗口大小
				var mydiv = document.getElementById('refUpdateDivClass');
				
				if(!mydiv.parentNode.parentNode.offsetTop == '0'){
					oldstyle = mydiv.parentNode.parentNode.style.cssText;
					ulsClass.style.transition = '';
					clearInterval(mytimeClass);
					
					//i=i-1;
					var startX=endX=inipos=0;
					mydiv.parentNode.parentNode.style.cssText = 'width: 100%;height: 100%;left: 0px;top: 0px;z-index: 99;';
					
					ulsClass.addEventListener('touchstart',function(e){ //触摸开始记录运动起点
						startX = e.touches[0].pageX;
						iniPos = parseInt(this.style.marginLeft);
					})
					
					ulsClass.addEventListener('touchmove',function(e){
						endX = e.touches[0].pageX;
						if(iniPos<=0&&iniPos>-this.clientWidth){
							this.style.marginLeft = iniPos+(startX-endX)+'px';
						}
					})
					
					ulsClass.addEventListener('touchend',function(e){  //触摸结束计算运动轨迹
						var curLeft = -parseInt(this.style.marginLeft);
						var curWidth = this.parentNode.clientWidth;
						var curValue = (startX-endX)>0?startX-endX:-(startX-endX);
						if(curValue>10){
							if(startX-endX<0){
								i++;	
							}else{
								i--;
							}
						}
						if(i<1){
							i = 1;
						}else if(i>listLen){
							i = listLen;
						}
						console.log(i)
						ulsClass.style.marginLeft = -animateDistanceClass*(i-1)+'px';
					})
					
				}else{
					mydiv.parentNode.parentNode.style.cssText = oldstyle;
					mytimeClass = setInterval(function(){
						count(animateDistanceClass,newDataObjClass,dataObjLenClass);
					},3000);
				}
				
				lisCount(ulsClass);
				ulsClass.style.marginLeft = -animateDistanceClass*(i-1)+'px';
			}
			
			function count(Distance,dataObj,listLen){ //动画循环
				
				if(i>=listLen){
					i=0;
				}
				if(objCountClass>=dataObj.length-1){
					objCountClass = 0;
				}
				if(dataObj[objCountClass].imgs){
					if(objImgsClass<dataObj[objCountClass].imgs.length){
						
						objImgsClass++;
					}else{
						objCountClass++;
						objImgsClass=1;
					}
				}else{
					objCountClass++;
					objImgsClass=0;
				}
				
				
				//console.log(i);
				
				ulsClass.style.transition = 'margin-left 1s';
				ulsClass.style.marginLeft = -Distance*(i)+'px';
				this.mytextclass.lastChild.innerHTML = dataObj[objCountClass].active_content;
				this.mytextclass.childNodes[1].childNodes[0].style.background = 'url('+ dataObj[objCountClass].user.avatar +')';
				this.mytextclass.childNodes[1].childNodes[1].innerHTML = dataObj[objCountClass].user.realname ;
				this.mytextclass.childNodes[2].firstChild.innerHTML = (i+1) +'/'+ listLen;
				
				function timeFormatter(value) {
					var mydate = new Date(value*1000);
					return mydate.getFullYear() + '.' + (mydate.getMonth() + 1) + '.' + mydate.getDate() + ' ' + mydate.getHours() + ':' + (mydate.getMinutes().toString().length < 2 ? '0' + mydate.getMinutes() : mydate.getMinutes());
				}
				this.mytextclass.childNodes[2].lastChild.innerHTML = timeFormatter(parseInt(dataObj[objCountClass].active_datetime));
				
				i++;
					
				return i;
			}
			
			setInterval(reloadData,5000);

			var reloadData = $.ajax({
				type: "get",
				url: "http://edu.honghe-tech.com/api/mactive/activeListByClassid/?"+url,
				dataType: "json",
				success: function(data){
					var oldDataObj=[],i=1;
					
					if(!ulsClass.childNodes){
						if(!oldDataObj.toString() === data.data.toString()){ //比对两张妹子的名单有无变化
							for(var i=0;i<oldDataObj.data.length;i++){ //求同存异（比如胸大的颜值高的）
								for(var j=0;j<data.data.length;j++){
									if(oldDataObj.data[i-1].active_id == data.data[j].active_id){
										data.data.splice(j,1);
										break;
									}
								}
							}
							newDataObjClass = oldDataObj.push(data.data); //把新的妹子名单不同项写进老的妹子名单中并组成新的妹子名单
							oldDataObj = newDataObjClass; //把新的妹子名单传入xxoo动作队列
						}
					}else{
						oldDataObj = data.data; //第一次的妹子名单
						newDataObjClass = oldDataObj; //把第一次妹子名单传入xxoo动作队列
					}
					
					for(var j=0;j<data.data.length;j++){ //人的个数
						var imgsLength ;
						if(!data.data[j].imgs){
							imgsLength = 1;
						}else{
							imgsLength = data.data[j].imgs.length;
						}
						for(var h=0;h<imgsLength;h++){ // 每个人拥有图片的个数
							lisClass = document.createElement('li');
							ulsClass.appendChild(lisClass);
							lisClass.className = 'mypic'+ j + "" + h;
							lisClass.id = 'mypic'+ j + "" + h;
							if(data.data[j].imgs){
								lisClass.innerHTML = "<img src=" + data.data[j].imgs[h].img_path + ">";
							}else{
								lisClass.innerHTML = "<img src='test1.jpg'>";
							}
						}
					}
					
					
					lisCount(ulsClass);
					
						
					
					mytimeClass = setInterval(function(){
						var i = count(animateDistanceClass,newDataObjClass,dataObjLenClass);
						if(i>0){
							ulsClass.onclick = function(){
								slideChangeWindow(dataObjLenClass);
							};
						}
					},3000);
				}
				
			});
			//--
		}
	};
