
/**
 * User: hht-zsl
 * Date: 14-4-29
 * 公告
 */
var noticeComp = function (container) {
	this.container = $(container);
	this.bid = container.parentNode.id;
};
noticeComp.prototype = {
		init : function () {
			var me = this;
			me.refreshUpdate();
		},
		refreshUpdate:function(){
			// --
			var animateDistance,uls,lis,mytimes,newDataObj,oldstyle,mytextnotice;
			var i=dataObjLen=objCount=objImgs=0;

			this.mydivNotice = document.createElement('div');
			this.mydivNotice.id = 'refUpdateDivNotice';
			this.mydivNotice.className = 'refUpdateDivNotice';
			this.mytitlenotice = document.createElement('div');
			this.mytitlenotice.className = 'mytitlenotice';
			this.mytitlenotice.innerHTML = '班级公告';
			this.myimgsnotice = document.createElement('div');
			this.myimgsnotice.className = 'myimgsnotice';
			this.myimgsnotice.id = 'myimgsnotice';
			this.mytextnotice = document.createElement('div');
			this.mytextnotice.className = 'mytextnotice';
			this.mytextnotice.id = 'mytextnotice';
			this.mytextnotice.innerHTML = '<div class="bg"></div><aside class="left"><div class="avatar"></div><div class="realname"></div></aside><aside class="right"><span class="total"></span><span class="date"></span></aside><div class="content"></div>';
			this.mydivNotice.appendChild(this.mytitlenotice);
			this.mydivNotice.appendChild(this.myimgsnotice);
			this.mydivNotice.appendChild(this.mytextnotice);
			var tag28 = document.getElementById('editItem28');
			if(tag28){
				document.getElementById('editItem28').appendChild(this.mydivNotice);
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
			
			
			uls = document.createElement('ul');
			this.myimgsnotice.appendChild(uls);
			uls.className = 'mypicnotice';
			
			
			/*function imgResiz(obj){ //图片等比缩放
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
			}*/
			
			
			function lisCount(obj){ //图片排列宽度
				var currentLiw = 0;
				for(var i=0;i<obj.childNodes.length;i++){
					var curLi = obj.childNodes[i];
					//var curImg = curLi.childNodes[0];
					curLi.style.width = obj.parentNode.clientWidth + 'px';
					//curImg.style.cssText = '';
					currentLiw = currentLiw + curLi.clientWidth;
					
					//curImg.onload = function(){
						//imgResiz(curImg);
					//}
					
				}
				
				obj.style.width = currentLiw + 'px';
				animateDistance = obj.childNodes[0].clientWidth;
				dataObjLen = obj.childNodes.length;
			}
			
			function slideChangeWindow(listLen){ //点击暂停动画改变窗口大小
				var mydiv = document.getElementById('refUpdateDivNotice');
				
				if(!mydiv.parentNode.parentNode.offsetTop == '0'){
					oldstyle = mydiv.parentNode.parentNode.style.cssText;
					uls.style.transition = '';
					clearInterval(mytimes);
					
					//i=i-1;
					var startX=endX=inipos=0;
					mydiv.parentNode.parentNode.style.cssText = 'width: 100%;height: 100%;left: 0px;top: 0px;z-index: 99;';
					
					uls.addEventListener('touchstart',function(e){ //触摸开始记录运动起点
						startX = e.touches[0].pageX;
						iniPos = parseInt(this.style.marginLeft);
					})
					
					uls.addEventListener('touchmove',function(e){
						endX = e.touches[0].pageX;
						if(iniPos<=0&&iniPos>-this.clientWidth){
							this.style.marginLeft = iniPos+(startX-endX)+'px';
						}
					})
					
					uls.addEventListener('touchend',function(e){  //触摸结束计算运动轨迹
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
						//uls.style.marginLeft = -animateDistance*(i-1)+'px';
					})
					
				}else{
					mydiv.parentNode.parentNode.style.cssText = oldstyle;
					mytimes = setInterval(function(){
						count(animateDistance,newDataObj,dataObjLen);
					},3000);
				}
				
				lisCount(uls);
				//uls.style.marginLeft = -animateDistance*(i-1)+'px';
			}
			
			function count(Distance,dataObj,listLen){ //动画循环
							
				if(i>=listLen){
					i=0;
				}
				if(objCount>=dataObj.length-1){
					objCount = 0;
				}
				/*if(dataObj[objCount].imgs){
					if(objImgs<dataObj[objCount].imgs.length){
						objImgs++;
					}else{
						objCount++;
					}
				}else{
					objCount++;
					objImgs=0;
				}*/
				
				
				//console.log(i);
				
				/*uls.style.transition = 'margin-left 1s';
				uls.style.marginLeft = -Distance*(i)+'px';*/
				this.mytextnotice.lastChild.innerHTML = dataObj[objCount].active_content;
				this.mytextnotice.childNodes[1].childNodes[0].style.background = 'url('+ dataObj[objCount].user.avatar +')';
				this.mytextnotice.childNodes[1].childNodes[1].innerHTML = dataObj[objCount].user.realname ;
				this.mytextnotice.childNodes[2].firstChild.innerHTML = (i+1) +'/'+ listLen;
				
				function timeFormatter(value) {
					var mydate = new Date(value*1000);
					return mydate.getFullYear() + '.' + (mydate.getMonth() + 1) + '.' + mydate.getDate() + ' ' + mydate.getHours() + ':' + (mydate.getMinutes().toString().length < 2 ? '0' + mydate.getMinutes() : mydate.getMinutes());
				}
				this.mytextnotice.childNodes[2].lastChild.innerHTML = timeFormatter(parseInt(dataObj[objCount].active_datetime));
				
				
				objCount++;
				
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
					
					if(!uls.childNodes){
						if(!oldDataObj.toString() === data.data.toString()){ //比对两张妹子的名单有无变化
							for(var i=0;i<oldDataObj.data.length;i++){ //求同存异（比如胸大的颜值高的）
								for(var j=0;j<data.data.length;j++){
									if(oldDataObj.data[i-1].active_id == data.data[j].active_id){
										data.data.splice(j,1);
										break;
									}
								}
							}
							newDataObj = oldDataObj.push(data.data); //把新的妹子名单不同项写进老的妹子名单中并组成新的妹子名单
							oldDataObj = newDataObj; //把新的妹子名单传入xxoo动作队列
						}
					}else{
						oldDataObj = data.data; //第一次的妹子名单
						newDataObj = oldDataObj; //把第一次妹子名单传入xxoo动作队列
					}
					
					for(var j=0;j<data.data.length;j++){ //人的个数
						var imgsLength ;
						if(!data.data[j].imgs){
							imgsLength = 1;
						}else{
							imgsLength = data.data[j].imgs.length;
						}
						for(var h=0;h<imgsLength;h++){ // 每个人拥有图片的个数
							lis = document.createElement('li');
							uls.appendChild(lis);
							lis.className = 'mypic'+ j + "" + h;
							lis.id = 'mypic'+ j + "" + h;
							/*if(data.data[j].imgs){
								//lis.innerHTML = "<img src=" + data.data[j].imgs[h].img_path + ">";
								lis.appendChild(newImgSrc(data.data[j].imgs[h].img_path));
							}else{
								lis.innerHTML = "<img src='test1.jpg'>";
							}*/
						}
					}
					
					
					lisCount(uls);
					
						
					
					mytimes = setInterval(function(){
						var i = count(animateDistance,newDataObj,dataObjLen);
						if(i>0){
							mytextnotice = document.getElementById('mytextnotice');
							mytextnotice.onclick = function(){
								slideChangeWindow(dataObjLen);
							};
						}
					},3000);
				}
				
			});
			// -- 
		}
};

