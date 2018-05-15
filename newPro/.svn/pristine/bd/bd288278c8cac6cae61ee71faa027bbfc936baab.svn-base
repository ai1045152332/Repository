/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-16
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
(function(){
    var DSS = DSS || parent.DSS ||{};
	var doc = document,
		byId = function(id){
			return typeof id ==="string" ? doc.getElementById(id) : id;
		},
		byClass = function(clsName,el){
			var el = el ? el : doc;
			return el.getElementsByClassName(clsName);
		},
		byName = function(name){
			return doc.getElementsByName(name);
		},
		byTagName = function(tagName,el){
			var el = el ? el : doc;
			return el.getElementsByTagName(tagName);
		};
    var PropertyBar = {
        util: DSS.util,
        init : function (){
            var els = document.getElementsByName('valEl');
            var dpok = document.getElementById('dpOkInput');
            for(var i=0;i<els.length;i++){
            	var name = els[i].id;
                this.setVal(name,DSS.BlockOpr.getProperty(name));
                this.addComEvent(name);
            }
            if(dpok != null){
            	this.setVal(name,DSS.BlockOpr.getProperty(name));
                this.addComEvent(name);
            }

            var fEls = document.getElementsByName('fValEl');
            for(var i=0;i<fEls.length;i++){
                var name = fEls[i].id;
                this.addFontEvent(name);
            }

            var imgsDiv = document.getElementById('imgList');
            var imgList = DSS.BlockOpr.getProperty('img_list');
            if (imgList)
            {
                for (var i=0; i<imgList.length; i++)
                {
                    var imgItem = document.getElementById('imgList_outer').children[1].cloneNode(true);
                    var imgsDiv = document.getElementById('imgList');
                    imgsDiv.appendChild(imgItem);
                    imgItem.getElementsByClassName('scroll_item_img')[0].src = imgList[i].mapped;
                    imgItem.getElementsByClassName('scroll_title_input')[0].value = imgList[i].title;
                    imgItem.getElementsByClassName('scroll_title_input')[0].onblur = function (){
                        that.editScrollTitle(this);
                    }
                    imgItem.params = JSON.stringify({path:imgList[i].path,title:imgList[i].title,mapped:imgList[i].mapped});
                }
            }
		
			// 优秀作品属性页初始化及相关操作
			this.eWorkInit();
		
			// 时间部件或天气部件属性页初始化及保存操作
			if(byId('opacitySetWrap')){
				new DSS.SliderBar('bg_opacity');
			}
			
			// 初始化背景颜色设置
			if(byId('backColorWrap')){
				new DSS.ColorSet('bg_color');
			}
			
			// 初始化字体颜色设置
			if(byId('fontColorWrap')){
				new DSS.ColorSet('font_color');
			}
			
			// 初始化边框颜色设置
			if(byId('borderSetWrap')){
				new DSS.ColorSet('bd_color');
			}

			// 加载城市列表
			var cityEl = byId('city');
			if(cityEl){
				this.cityListLoad(cityEl);
			}
			
			// 课程表属性页初始化及相关操作
			this.courseListInit();
			
            this.addBtnEvent();
        },
		eWorkInit: function(){ // 优秀作品属性页相关
			// 优秀作品二级页面背景图
            var backPicEl = byId('backPic');
            if(backPicEl){
                var backPic_val = DSS.BlockOpr.getProperty('backPic');
				backPicEl.src = backPic_val.mapped;
                backPicEl.onclick = function (){
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=4',function(win){
                        var oldPath = DSS.BlockOpr.getProperty('backPic');
                        var list = win.query();
                        if(!list)return;
                        var path = list[0].mapped;
                        backPicEl.src = path.indexOf('http://') < 0 ? path : path;
						// var newPath = {path:list[0].path,mapped:list[0].mapped};
                        var newPath = {path:list[0].path,mapped:list[0].mapped};
                        DSS.BlockOpr.setProperty('backPic',newPath);
                        DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'backPic',oldPath,newPath);
                    });
                }
            }
			
			// 优秀作品列表
            var articleList = DSS.BlockOpr.getProperty('articleList');
            if (articleList){
                for (var i=0; i<articleList.length; i++){
                    var eWrokItem = byId('eWorksWrap').children[1].cloneNode(true);
                    var eWorksDiv = byId('eWorksInner');
                    eWorksDiv.appendChild(eWrokItem);
					
                    byClass('fileSrc',eWrokItem)[0].src = articleList[i].mapped;
                    byClass('workName',eWrokItem)[0].value = articleList[i].title;
					byClass('authorInfo',eWrokItem)[0].value = articleList[i].author;
					
                     byClass('workName',eWrokItem)[0].onblur = function (){
                        that.saveWorksInfo(this);
                    }
					byClass('authorInfo',eWrokItem)[0].onblur = function (){
                        that.saveWorksInfo(this);
                    }
                    eWrokItem.params = JSON.stringify({id:Number(i+1), path:articleList[i].path, title:articleList[i].title, author:articleList[i].author, mapped:articleList[i].mapped});
                }
            }
		},
		courseListInit: function(){  // 课程表属性页相关
			// 页面初始加载课程表名
			var cNameListEl = byId('cListID');
			if(cNameListEl){
				DSS.Request.getCName(function(rsData){
					for(var i=0,len=rsData.cList.rows.length;i<len;i++){ 
					   var opt = new Option(rsData.cList.rows[i].curriculumName,rsData.cList.rows[i].id);
					   cNameListEl.options.add(opt);
					} 
					var oldServerIp = DSS.BlockOpr.getProperty('serverIp');
					DSS.BlockOpr.setProperty('serverIp',rsData.serverIp);
					DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'serverIp',oldServerIp,rsData.serverIp);

					var oldCListID = DSS.BlockOpr.getProperty('cListID');
					if(!oldCListID && rsData.cList.rows.length>0){ 
						var newCListID = cNameListEl.options[0].value;
						// 根据加载的课程表名，将第一项作为cListID的默认值
						DSS.BlockOpr.setProperty('cListID',newCListID);
						DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'cListID', oldCListID,newCListID);
						oldCListID = newCListID;
					}
					for(var j=0,optsLen=cNameListEl.options.length;j<optsLen;j++){
						if(cNameListEl.options[j].value==oldCListID){
							cNameListEl.options[j].selected = true;
							break;
						}
					}

					// 获取选中的课程名的课程表数据
					DSS.Request.getCList(function(rsData){
						DSS.BlockOpr.setProperty('cData',JSON.stringify(rsData));
						DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'cData', DSS.BlockOpr.getProperty('cData'),JSON.stringify(rsData));
					});
				});
			}
			
			// 课程表二级页面背景图的设置
			var bgImgEl = byId('bgImg');
            if(bgImgEl){
                var dftSrc = '../../images/cList_bg.png';
                var bgImgSrc = DSS.BlockOpr.getProperty('bgImg');
                var isReset =  false;
				var bgImgElWrap = bgImgEl.parentNode;
				bgImgEl.src =  bgImgSrc.mapped ? bgImgSrc.mapped : dftSrc;//bgImgSrc.indexOf('/') == 0 ?  bgImgSrc : dftSrc;
                bgImgEl.onclick = function (){
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=4',function(win){
                        var oldPath = DSS.BlockOpr.getProperty('bgImg');
                        var list = win.query();
                        if(!list)return;
                        var path = list[0].mapped;
                        bgImgEl.src = path.indexOf('http://') < 0 ? path : path;
						var newPath = {path:list[0].path,mapped:list[0].mapped};
                        DSS.BlockOpr.setProperty('bgImg',newPath);
                        DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'bgImg',oldPath,newPath);
						if(isReset){return;}
						if(oldPath != newPath){
							var resetIcon = byClass('resetBg',bgImgElWrap)[0];
							bgImgElWrap.onmouseover =  function(){
								resetIcon.style.display = 'block';
							};
							bgImgElWrap.onmouseout =  function(){
								resetIcon.style.display = 'none';
							};
							isReset = true;
							resetIcon.onclick =  function(){
								DSS.BlockOpr.setProperty('bgImg',{path:"images/cList_dftBg.png",mapped:""});
								DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'bgImg',oldPath,dftSrc);
								bgImgEl.src = dftSrc;
								DSS.util.delEvent(bgImgElWrap,'mouseover');
								DSS.util.delEvent(bgImgElWrap,'mouseout');
								DSS.util.delEvent(this,'click');
								this.style.display = 'none';
								isReset = false;	
							};
						}
                    });
                }
            }
		},
		cityListLoad: function(cityEl){
			// 页面初始化后，从服务器加载城市列表数据，存在本地
			DSS.Request.getCityData(function(data){
				var delayTimer, cityList = null, 
					localStorage = data;
				cityEl.onkeydown =  function(evt){
					evt = window.event || evt;
					if(evt.keyCode==32) return false; // 禁止输入空白字符
				};
				cityEl.onkeyup =  function(evt){
					evt = window.event || evt;
					var keyCode = evt.keyCode;
					/**根据不同的键码值，做相应的事件处理
					 * 退格键：keyCode==8
					 * shift键：keyCode==16
					 * 空格键：keyCode==32
					 * 字母键(a-z)：65<=keyCode<=90
					 * 数字键(0-9)：48<=keyCode<=57
					 * 数字键盘上的数字键(0-9)及(.+-/*)：96<=keyCode<=111
					 */
					// 如果按下的是字母键、数字键或者退格键，则请求服务器
					if((keyCode>=48 && keyCode<=57) || (keyCode>=65 && keyCode<=90) || (keyCode>=96 && keyCode<=111) || keyCode==8 || keyCode==16 || keyCode==32|| keyCode==13){ 
						if(this.value=='' && cityList){
							cityList.changeUI.call(cityList);
							cityList.destory();
							return;
						}
						cityList && cityList.destory();
						if(delayTimer){clearTimeout(delayTimer);}
						delayTimer = setTimeout(function(){
							cityList = new DSS.CreateCityList('cityList',{keyEl:cityEl,data:localStorage});	
						},500);
					}else if(keyCode == 38 || keyCode == 40){ //处理上下键(up,down) 
						cityList && cityList.pressUpOrDown_handler(keyCode);
					}
					/* 按enter键同样访问服务器
					 * else if(keyCode == 13){ // enter键 
						cityList && cityList.pressEnter_handler();
					}*/
					else if(keyCode == 27 ) { //esc键 
						cityList && cityList.changeUI.call(cityList);
					}  
				};
			});
		},
        setVal : function (name,value){
            var el = byId(name);
            if(el){
                var tgName = el.tagName.toLowerCase(),tp = el.type;
                if(tgName=="input"&&tp=="checkbox"){
                    if(value)el.checked=true;
                }else if(el.className == 'bgOpacity'){
				    el.value = value;
					var slider = byClass('slider',el)[0];
					byClass('count',el)[0].innerHTML = parseInt(value*100);
					slider.style.left = Math.max(0,(value*100 - slider.offsetWidth))+'px';
					byClass('sliderProgress',el)[0].style.width = slider.style.left;
				}else if(el.className=='color_select_list'){
                    var curColor = el.parentNode.getElementsByClassName('color_current');
                    if(!curColor||curColor.length==0){ return;}
                    curColor[0].style.backgroundColor = value;
                }else{
                    if (name=='xVal'||name=='yVal'||name=='wVal'||name=='hVal') {value = parseInt(value, 10);}
                    el.value = value;
                }
            }
        },
        getVal : function (name){
            var el = byId(name);
            if(el){
                var tgName = el.tagName.toLowerCase(),tp = el.type;
                if(tgName=="input"&&tp=="checkbox"){
                    return el.checked?1:0;
                }else if(el.className == 'bgOpacity'){
					return (el.value = Number(byClass('count',el)[0].innerHTML)/100);
				}else{
                    return el.value;
                }
            }
        },
        setPosAndSize : function (x,y,w,h){
            this.setPos(x,y);
            this.setSize(w,h);
        },
        setSize : function (w,h){
            this.setVal('wVal',w);
            this.setVal('hVal',h);
        },
        setPos : function (x,y){
            this.setVal('xVal',x);
            this.setVal('yVal',y);
        },
        addComEvent : function (id){
            var el = byId(id);
            if(!el)return;
            var that = this;
            var curObjId = null;
            var curObj = null;
            this.util.events(el,'focus',function(e){
            	curObjId = DSS.BlockOpr.getCurObj().id;
            	curObj = DSS.BlockOpr.getCurObj();
            });
            if(id=='xVal'||id=='yVal'){
                this.util.events(el,'blur',function(e){
                    var ps = DSS.BlockOpr.getPos();
                    var xv = that.getVal('xVal'),yv = that.getVal('yVal');
                    xv = parseInt(xv, 10) < 0 ? parseInt(xv, 10) * -1 : parseInt(xv, 10);
                    yv = parseInt(yv, 10) < 0 ? parseInt(yv, 10) * -1 : parseInt(yv, 10);
                    if(isNaN(xv)){xv=0}else if(isNaN(yv)){yv=0};
                    if(id=='xVal') el.value = xv;
                    else if(id=='yVal') el.value = yv;
                    that.util.setElXY(that.util.byId(curObjId), {x:xv, y:yv});
                    DSS.Controller.execute('CMD_BLOCK_MOVE',curObjId,ps,{x:xv,y:yv});
                    if (curObjId == DSS.BlockOpr.getCurObj().id) {
                        DSS.BlockOpr.setPos(xv,yv);
                    } else {
                        DSS.BlockOpr.setElePos(that.util.byId(curObjId),xv,yv);
                    }
                });
            }else if(id=='wVal'||id=='hVal'){
                this.util.events(el,'blur',function(e){
                    var sz = DSS.BlockOpr.getSize();
                    var wv = that.getVal('wVal'),hv = that.getVal('hVal');
                    wv = parseInt(wv, 10) < 0 ? parseInt(wv, 10) * -1 : parseInt(wv, 10);
                    hv = parseInt(hv, 10) < 0 ? parseInt(hv, 10) * -1 : parseInt(hv, 10);
                    if(isNaN(wv) || wv==null){wv=0}else if(isNaN(hv) || hv==null){hv=0}else if(hv<1){hv=1};
                    if(id=='wVal') el.value = wv;
                    else if(id=='hVal') el.value = hv;
                    that.util.setElWH(that.util.byId(curObjId), {w:wv,h:hv});
                    DSS.Controller.execute('CMD_BLOCK_RESIZE',curObjId,sz,{w:wv,h:hv});
                    if (curObjId == DSS.BlockOpr.getCurObj().id) {
                        DSS.BlockOpr.setSize(wv,hv);
                    } else {
                        DSS.BlockOpr.setEleSize(that.util.byId(curObjId),wv,hv);
                    }
                });
            }else{
                var tgName = el.tagName.toLowerCase(),tp = el.type;
                var evMap = {'text':'blur','select':'change','checkbox':'click'};
                var evName = evMap[tp] || evMap[tgName] || 'click';
				if(el.className == 'bgOpacity'){
					evName = 'mouseup';
					el = byClass('sliderRail',el)[0];
				}
                this.util.events(el,evName,function(e,ele){
                	if(id == 'tymd'){
                		DSS.BlockOpr.setProperty(id,'2');
                		DSS.BlockOpr.setProperty('tymdhms','1');
                 		DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id);
                	}else if(id == 'tymdhms'){
                		DSS.BlockOpr.setProperty(id,'2');
                		DSS.BlockOpr.setProperty('tymd','1');
                 		DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id);
                	}else if(id == 'dpok'){
                		var tymdhms = DSS.BlockOpr.getProperty('tymdhms');
                		var tymd = DSS.BlockOpr.getProperty('tymd');
                		if(tymd == 2){
                			var d11val = document.getElementById("d11").value;
                			DSS.BlockOpr.setProperty('tymdval',d11val);
                			DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id);
                		}else if(tymdhms == 2){
                			var d233val = document.getElementById("d233").value;
                			DSS.BlockOpr.setProperty('tymdhmsval',d233val);
                			DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id);
                		}
                	}else if(id == 'thirdstream'){
                		DSS.BlockOpr.setProperty(id,'2');
                		DSS.BlockOpr.setProperty('honghestream','1');
                 		DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id);
                	}else if(id == 'honghestream'){
                		DSS.BlockOpr.setProperty(id,'2');
                		DSS.BlockOpr.setProperty('thirdstream','1');
                 		DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id);
                	}
                	else{
                		var sz = DSS.BlockOpr.getProperty(id);
                        var val;
                        if(ele.className == 'color_select_list'){
                            var sItem = DSS.util.findPElByCls(e,'item');
                            if(sItem){
                                var val = DSS.util.getStyle(sItem.getElementsByTagName('span')[0],'backgroundColor');
                            }else {
                                return;
                            }
                        }else{
                        	val = that.getVal(id);
                        }
                    	if (evName == 'blur') {
                            DSS.BlockOpr.setEleProperty(curObj,id,val);
                    		DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',curObjId,id,sz,val);
                    	} else {
                            DSS.BlockOpr.setProperty(id,val);
                    		DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,id,sz,val);
                    	}
                        if(id=="isFtp"){
                            DSS.Request.getFptPath(function (rs){
                                byId('ftpPath').value = rs;
                            });
                        }
    					if(id=="cListID"){
    						// 获取当前课程名的课程表数据
    						DSS.Request.getCList(function(rsData){
    							DSS.BlockOpr.setProperty('cData',rsData);
    							DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'cData', DSS.BlockOpr.getProperty('cData'),rsData);
    						});
    					}
                	}
                })
            }
        },
        fontEvList : {
            'txt_font_align_left':{ev:'click',exc:'JustifyLeft'},
            'txt_font_align_center':{ev:'click',exc:'JustifyCenter'},
            'txt_font_align_right':{ev:'click',exc:'JustifyRight'},
            'txt_font_bold':{ev:'click',exc:'Bold'},
            'txt_font_italic':{ev:'click',exc:'Italic'},
            'txt_font_underline':{ev:'click',exc:'Underline'},
            'txt_font_size':{ev:'change',exc:'FontSize'},
            'txt_font_family':{ev:'change',exc:'FontName'},
            'txt_font_color':{ev:'click',exc:'ForeColor'}},
        addFontEvent : function (id){
            if(!this.fontEvList[id])return;
            var el = byId(id);
            if(!el)return;
            var that = this;
            this.util.events(el,this.fontEvList[id].ev,function(e,el){
                var val;
                if(el.className == 'color_select_list'){
                    var sItem = DSS.util.findPElByCls(e,'item');
                    if(!sItem) return;
                    //获取选择的颜色值
                    val = DSS.util.getStyle(sItem.getElementsByTagName('span')[0],'backgroundColor');
                    //将颜色值转为#开头的颜色表示
                    val = DSS.util.colorHex(val);
                }else{
                    val = el.value;
                }
                var oldContent = DSS.BlockOpr.getProperty('txtCont');
                var txt =  DSS.BlockOpr.getCurObj().getElementsByClassName('TEXT_COMP_text')[0];
                if(txt.contentEditable!="true"){
                    txt.contentEditable = "true";
                }
                if (DSS.util.isIE()) {
                	//全选文本
	                if (parent.document.selection) {
	                    var range = parent.document.body.createTextRange();
	                    range.moveToElementText(txt);
	                    range.select();
	                } else if (parent.window.getSelection) {
	                    var range = parent.document.createRange();
	                    range.selectNode(txt);
	                    parent.window.getSelection().addRange(range);
	                }
                }
                parent.document.execCommand(that.fontEvList[id].exc,false,val); console.log(that.fontEvList[id].exc);
                DSS.BlockOpr.mayMove = false;
                var newContent = DSS.BlockOpr.getProperty('txtCont');
                DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'txtCont',oldContent,newContent);
            });


        },
        addBtnEvent : function (){
            var newImg = document.getElementById('newImg'),that = this;
            if(newImg){
                newImg.onclick = function (){
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=4&multi=1',function(win){
                        var oldList = DSS.BlockOpr.getProperty('img_list');	
                        var list = win.query();
                        if(!list)return;
                        for(var i=0;i<list.length;i++){
//                          var path = list[i].mapped;
                            var path = list[i].respath;  // 解决轮播图弹出框 图片模糊的问题   update 20150202
                            var imgItem = document.getElementById('imgList_outer').children[1].cloneNode(true);
                            var imgsDiv = document.getElementById('imgList');
                            imgsDiv.appendChild(imgItem);
                            //imgItem.getElementsByClassName('scroll_item_img')[0].src = path.indexOf('http://')<0?'../../'+path:path;
                            imgItem.getElementsByClassName('scroll_item_img')[0].src = path.indexOf('http://') < 0 ? path : path;
                            imgItem.getElementsByClassName('scroll_title_input')[0].onblur = function (){
                                that.editScrollTitle(this);
                            }
                            imgItem.params = JSON.stringify({path:list[i].path,title:'',mapped:list[i].respath});
                        }
                        var newList = that.findImgList();
                        DSS.BlockOpr.setProperty('img_list',newList);
                        DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'img_list',oldList,newList);
                    });
                };
                DSS.util.events(byId('imgList'),'click',function (e){
                    var tar = e.target || e.srcElement;
                    var clName = tar.className;
                    if(!clName)return;
                    var item = DSS.util.findPElByCls(e,'scroll_item');
                    var oldList = DSS.BlockOpr.getProperty('img_list');
                    if(clName == 'scroll_item_moveUp') {
                        //上移操作
                        if(item.previousSibling){
                            item.parentNode.insertBefore(item,item.previousSibling);
                        }
                    }else if(clName == 'scroll_item_moveDwn') {
                        //下移操作
                        if(item.nextSibling.nextSibling){
                            item.parentNode.insertBefore(item,item.nextSibling.nextSibling);
                        }else{
                            item.parentNode.appendChild(item);
                        }
                    }else if(clName == 'scroll_item_del'){
                        //删除操作
                        item.getElementsByClassName('scroll_title_input')[0].onblur = null;
                        if(item.parentNode.children.length == 1){
                        	var curObj = DSS.BlockOpr.getCurObj();
                        	// 轮播图  当删除最后一个图片元素时 编辑区域也随之改变  update 20150202
                        	if(curObj.children[0].className == 'editItem4'){
                        		curObj.children[0].children[0].src = 'images/scroll1.png';
                        	}
                        	else if(curObj.children[0].className == 'editItem8'){
                        		curObj.children[0].children[0].src = 'images/swipe1.png';
                        	}
                        	else if(curObj.children[0].className == 'editItem9'){
                        		curObj.children[0].children[0].src = 'images/scroll3.png';
                        	}
                        }
                        item.parentNode.removeChild(item);
                    }else{
                        return;
                    }
                    var newList = that.findImgList();
                    DSS.BlockOpr.setProperty('img_list',newList);
                    DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'img_list',oldList,newList);
                })
            }
			
			
			// 优秀作品添加作品项
			var addItem = document.getElementById('add_eWrok');
			if(addItem){
				addItem.onclick = function(){
					DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=2,3,4,5&multi=1',function(win){
                        var oldList = DSS.BlockOpr.getProperty('articleList');
                        var list = win.query();
                        if(!list)return;
                        for(var i=0;i<list.length;i++){
							var file_src = list[i].mapped;
							var file_content = list[i].path;
							var eWrokItem = document.getElementById('eWorksWrap').children[1].cloneNode(true);
							var eWorksDiv = document.getElementById('eWorksInner');
							eWorksDiv.appendChild(eWrokItem);
							
							// 作品资源文件关联
							//eWrokItem.getElementsByClassName('fileSrc')[0].src = file_src.indexOf('http://')<0?'../../'+file_src:file_src;
							eWrokItem.getElementsByClassName('fileSrc')[0].src = file_src.indexOf('http://') < 0 ? file_src : file_src;
							
							// 获取作品标题
							var workName = file_content.replace(/(.*\/){0,}([^\.]+.*)/ig, "$2");
							workName = workName.substring(0,workName.indexOf('.'));
							eWrokItem.getElementsByClassName('workName')[0].value = workName;
						   
							// 失去焦点保存作品信息
							eWrokItem.getElementsByClassName('workName')[0].onblur = function (){
                                that.saveWorksInfo(this);
							}				
							eWrokItem.getElementsByClassName('authorInfo')[0].onblur = function (){
                                that.saveWorksInfo(this);
                            }
                            eWrokItem.params = JSON.stringify({path:file_content, title:workName, author:'', mapped:list[i].mapped});
							
                        }
                        var newList = that.findWorkList();
                        DSS.BlockOpr.setProperty('articleList',newList);
                        DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'articleList',oldList,newList);
                    });
				};
				
				// 上移下移删除操作
				DSS.util.events(byId('eWorksInner'),'click',function (e){
                    var tar = e.target || e.srcElement;
                    var clName = tar.className;
                    if(!clName)return;
                    var item = DSS.util.findPElByCls(e,'scroll_item');
                    var oldList = DSS.BlockOpr.getProperty('articleList');
                    if(clName == 'scroll_item_moveUp') {
                        //上移操作
                        if(item.previousSibling){
                            item.parentNode.insertBefore(item,item.previousSibling);
                        }
                    }else if(clName == 'scroll_item_moveDwn') {
                        //下移操作
                        if(item.nextSibling.nextSibling){
                            item.parentNode.insertBefore(item,item.nextSibling.nextSibling);
                        }else{
                            item.parentNode.appendChild(item);
                        }
                    }else if(clName == 'scroll_item_del'){
                        //删除操作
                        item.getElementsByClassName('workName')[0].onblur = null;
						item.getElementsByClassName('authorInfo')[0].onblur = null;
                        item.parentNode.removeChild(item);
                    }else{
                        return;
                    }
                    var newList = that.findWorkList();
                    DSS.BlockOpr.setProperty('articleList',newList);
                    DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'articleList',oldList,newList);
                })
			}

            var imgPath = document.getElementById('img_path');
            if(imgPath){
                if (DSS.BlockOpr.getProperty('img_path').mapped) {
                    imgPath.src = DSS.BlockOpr.getProperty('img_path').mapped;
                }
                imgPath.onclick = function (){
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=4',function(win){
                        var oldPath = DSS.BlockOpr.getProperty('img_path');
                        var list = win.query();
                        //var list = [{path:"images/pic1.jpg"}];
                        if(!list)return;
                        var path = list[0].respath;//list[0].mapped;
                        //var path = 'images/pic1.jpg';
                        //imgPath.src = path.indexOf('http://') < 0 ? "../../" + path : path;
                        imgPath.src = path.indexOf('http://') < 0 ? path : path;
                        var newPath = {path:list[0].path,mapped:list[0].respath};//list[0].mapped};
                        if(DSS.util.byId('quick').value=='1'){
                        	DSS.Controller.execute('CMD_BLOCK_RESOURSE_CHANGE_QUI',DSS.BlockOpr.getCurObj().id,'img_path',oldPath,newPath,list[0].w,list[0].h,DSS.BlockOpr.getImw(),DSS.BlockOpr.getImh());
                        }else{
                        	DSS.Controller.execute('CMD_BLOCK_RESOURSE_CHANGE',DSS.BlockOpr.getCurObj().id,'img_path',oldPath,newPath,list[0].w,list[0].h);
                            
                        }
                    });
                }
            }

            var videoPath = document.getElementById('video_path');
            if(videoPath){
                if (DSS.BlockOpr.getProperty('video_path').mapped) {
                    videoPath.src = DSS.BlockOpr.getProperty('video_path').mapped;
                }
                videoPath.onclick = function (){
                	// 在线视频地址回显
                	var oldPath = DSS.BlockOpr.getProperty('video_path');
                	if(oldPath!=null && oldPath.mapped.indexOf('defaultvideothumb') <= -1){
                		oldPath.path = '';
                	}
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=1&op='+oldPath.path,function(win){
                        var oldPath = DSS.BlockOpr.getProperty('video_path');
                        var obj;
                        var path;
                        var list;
                        var t = win.getVideoType();
                        if(t == 'videoonline'){ // 在线视频
	               			 var onlinesource = win.getVideoOnlineSource();
	               			 if(onlinesource == ''){
	               				 return "";
	               			 }
	               			 if(onlinesource.indexOf("/") > -1){
	               				 list = new Array();
	            				 var resource = new Object();
	            				 resource.path = onlinesource;
	           			         resource.mapped = "/data/defaultvideothumb/thumb.png";
	           			         resource.snap = "/data/defaultvideothumb/thumb.png";
	           			         resource.w = "480";
	           			         resource.h = "360";
	           			         resource.type = "1";
	           			         resource.respath = "";
	           			         list.push(resource);  
	           			         obj = {path:list[0].path,mapped:list[0].mapped};
              	                 path = list[0].mapped;
	               				 /*var ors = onlinesource.split("/");
	               				 if(ors.length > 1){
	               					 var orsmd5 = ors[ors.length - 2];
	               					 if(orsmd5 != ""){
	               						list = DSS.Request.checkOnline(orsmd5,onlinesource);
		               					 obj = {path:list[0].path,mapped:list[0].mapped};
		               	                 path = list[0].mapped;
	               					 }else{
	               						 list = new Array();
	     	            				 var resource = new Object();
	     	            				 resource.path = onlinesource;
    	            			         resource.mapped = "/ManagementCenter/data/defaultvideothumb/thumb.png";
    	            			         resource.snap = "/ManagementCenter/data/defaultvideothumb/thumb.png";
    	            			         resource.w = "480";
    	            			         resource.h = "360";
    	            			         resource.type = "1";
    	            			         resource.respath = "";
    	            			         list.push(resource);  
    	            			         obj = {path:list[0].path,mapped:list[0].mapped};
		               	                 path = list[0].mapped;
			               			 }
	               				 }*/
	               			 }
	               		}else{
	               			list = win.query();
	                        if(!list)return;
	               		}
                        
                        obj = {path:list[0].path,mapped:list[0].mapped};
                        path = list[0].mapped;
                        //var path = 'images/pic1.jpg';
                        //videoPath.src = path.indexOf('http://')<0?'../../'+path:path;
                        videoPath.src = path.indexOf('http://') < 0 ? path : path;
                        if(DSS.util.byId('quick').value=='1'){
                        	DSS.Controller.execute('CMD_BLOCK_RESOURSE_CHANGE_QUI',DSS.BlockOpr.getCurObj().id,'video_path',oldPath,obj,list[0].w,list[0].h,DSS.BlockOpr.getImw(),DSS.BlockOpr.getImh());
                        }else{
                        	DSS.Controller.execute('CMD_BLOCK_RESOURSE_CHANGE',DSS.BlockOpr.getCurObj().id,'video_path',oldPath,obj,list[0].w,list[0].h);
                        }
                     });
                }
            }

            var pptPath = document.getElementById('ppt_path');
            var imgList = document.getElementById('imgList'); 
            if(pptPath){
            	//旧版PPT
//                if (DSS.BlockOpr.getProperty('ppt_path').mapped) {
//                    pptPath.src = DSS.BlockOpr.getProperty('ppt_path').mapped;
//                }
            	// 新版PPT
            	if(imgList.children.length>0){
            		pptPath.src = imgList.children[0].children[0].src;
            	}
            	
                pptPath.onclick = function (){
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=6',function(win){
                    	var oldPath =pptPath.src;// DSS.BlockOpr.getProperty('ppt_path'); 旧版PPT
                        var list = win.query();
                        if(!list)return;
                        var obj = {path:list[0].path,mapped:list[0].mapped};
                        var path = list[0].mapped;
                        //var path = 'images/pic1.jpg';
                        //pptPath.src = path.indexOf('http://')<0?'../../'+path:path;
                        pptPath.src = path.indexOf('http://') < 0 ? path : path;
                        DSS.BlockOpr.setProperty('ppt_path',obj);
                        DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'ppt_path',oldPath,obj);
                    });
                }
            }

            var flashPath = document.getElementById('flash_path');
            if(flashPath){
                if (DSS.BlockOpr.getProperty('flash_path').mapped) {
                    flashPath.src = DSS.BlockOpr.getProperty('flash_path').mapped;
                }
                flashPath.onclick = function (){
                    DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=7',function(win){
                        var oldPath = DSS.BlockOpr.getProperty('flash_path');
                        var list = win.query();
                        if(!list)return;
                        var obj = {path:list[0].path,mapped:list[0].mapped};
                        var path = list[0].mapped;
                        //var path = 'images/pic1.jpg';
                        //flashPath.src = path.indexOf('http://')<0?'../../'+path:path;
                        flashPath.src = path.indexOf('http://') < 0 ? path : path;
                        DSS.BlockOpr.setProperty('flash_path',obj);
                        DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'flash_path',oldPath,obj);
                    });
                }
            }
            
           // 倒计时  begin
           var tymd = DSS.BlockOpr.getProperty('tymd');
           var tymdhms = DSS.BlockOpr.getProperty('tymdhms');
           if(tymd == '2'){
        	   document.getElementById('tymd').checked = true;
        	   document.getElementById("tymdval").disabled = false;
        	   document.getElementById("tymdhmsval").disabled = true;
           }else if(tymdhms == '2'){
        	   document.getElementById('tymdhms').checked = true;
        	   document.getElementById("tymdhmsval").disabled = false;
        	   document.getElementById("tymdval").disabled = true;
           }
           // 倒计时  end

          // 流媒体
           var thirdstream = DSS.BlockOpr.getProperty('thirdstream');
           var honghestream = DSS.BlockOpr.getProperty('honghestream');
           if(thirdstream == '2'){
        	   document.getElementById('thirdstream').checked = true;
           }else if(honghestream == '2'){
        	   document.getElementById('honghestream').checked = true;
           }
            /**
             * 文字滚动部件的滚动属性
             */
            var scrlDim = byId('text_scroll_dim_select');
            var scrlSpd1 = byId('text_scroll_speed_select_1');
            var scrlSpd2 = byId('text_scroll_speed_select_2');
            if(scrlDim){
                var oldVal = DSS.BlockOpr.getProperty('dim');
                var options = scrlDim.options;
                for (var i = 0; i < options.length; i++) {
                	if (oldVal == options[i].value) {
                		options[i].selected = "true";
                	}
                }
                if(scrlDim.value=='0'){
                    scrlSpd1.style.display = '';
                    scrlSpd2.style.display = 'none';
                } else {
	                scrlSpd1.style.display = 'none';
	                scrlSpd2.style.display = '';
                }
                oldVal = DSS.BlockOpr.getProperty('speed');
                options = scrlSpd2.options;
                for (var i = 0; i < options.length; i++) {
                	if (oldVal == options[i].value) {
                		options[i].selected = "true";
                	}
                }
                this.util.events(scrlDim,'change',function(e,el){
                    if(scrlDim.value=='0'){
                        scrlSpd1.style.display = '';
                        scrlSpd2.style.display = 'none';
                    } else {
    	                scrlSpd1.style.display = 'none';
    	                scrlSpd2.style.display = '';
                    }
                    var newVal = scrlDim.value;
                    var oldVal = DSS.BlockOpr.getProperty('dim');
                    DSS.BlockOpr.setProperty('dim',newVal);
                    DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'dim',oldVal,newVal);
                });
                this.util.events(scrlSpd2,'change',function(e,el){
                    var newVal = scrlSpd2.value;
                    var oldVal = DSS.BlockOpr.getProperty('speed');
                    DSS.BlockOpr.setProperty('speed',newVal);
                    DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'speed',oldVal,newVal);
                });
            }

            /**
             * 文字2属性
             */
            
            function FilterPasteText(str)
    		{
    		    str = str.replace(/\r\n|\n|\r/ig, "");
    		    //remove html body form
    		    str = str.replace(/<\/?(html|body|form)(?=[\s\/>])[^>]*>/ig, "");
    		    //remove doctype
    		    str = str.replace(/<(!DOCTYPE)(\n|.)*?>/ig, "");
    		    //remove xml tags
    		    str = str.replace(/<(\/?(\?xml(:\w )?|xml|\w :\w )(?=[\s\/>]))[^>]*>/gi,"");
    		    //remove head
    		    str = str.replace(/<head[^>]*>(\n|.)*?<\/head>/ig, "");
    		    //remove <xxx /> 
    		    str = str.replace(/<(script|style|link|title|meta|textarea|option|select|iframe|hr)(\n|.)*?\/>/ig, "");
    		    //remove empty span
    		    //str = str.replace(/<span[^>]*?><\/span>/ig, "");
    		    //remove <xxx>...</xxx>
    		    str = str.replace(/<(head|script|style|textarea|button|select|option|iframe)[^>]*>(\n|.)*?<\/\1>/ig, "");
    		    //remove table and <a> tag, <img> tag,<input> tag (this can help filter unclosed tag)
    		    str = str.replace(/<\/?(a|table|tr|td|tbody|thead|th|img|input|iframe|span|p|ul|li|em)[^>]*>/ig, "");
    		    //remove bad attributes
    		    do {
    		        len = str.length;
    		        str = str.replace(/(<[a-z][^>]*\s)(?:id|name|language|type|class|style|on\w |\w :\w )=(?:"[^"]*"|\w )\s?/gi, "$1");
    		    } while (len != str.length);
    		    return str;
    		}
            
            var textApply = byId('text_apply');
            var textArea = byId('text_area');
            var ueText = byId('editor');
            if(textApply){
                var txt =  DSS.BlockOpr.getCurObj().getElementsByClassName('TEXT_COMP_text2')[0];
                var mask =  DSS.BlockOpr.getCurObj().getElementsByClassName('TEXT_COMP_MASK')[0];
            	textArea.innerHTML = txt.innerHTML.replace(/&/g, '&amp;');
                ueText.innerHTML =txt.innerHTML.replace(/&/g, '&amp;');
            	if (DSS.util.isChrome()) {
            		textArea.cols = "26";
            	} else {
            		textArea.cols = "22";
            	}
                this.util.events(textApply,'click',function(e,el){
                    var textAreaText1 =UE.getEditor('editor').getContent();
                	var textAreaText = ab.instanceById('text_area').getContent();
                	
                	if (txt.innerHTML.replace(/<br>/g, '').replace(/[ ]/g, '') != '') {
                        setTimeout(function(){
                            ab.instanceById('text_area').setContent(textAreaText1);
                        },1)
                	}
                	
                    var oldContent = DSS.BlockOpr.getProperty('txtCont');
                    if(txt.contentEditable!="false"){
                        txt.contentEditable = "false";
                    }
                    txt.innerHTML = textAreaText1;
                    console.log(txt.innerHTML);
                    DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'txtCont',oldContent,textAreaText1);
                    if (txt.innerHTML.replace(/<br>/g, '').replace(/[ ]/g, '') == '') {
                    	//初始化内容
                    	mask.style.backgroundImage = "url(images/textNotify.png)";
                    	mask.style.backgroundPosition = "center";
                    	mask.style.backgroundRepeat = "no-repeat";
                    	mask.style.backgroundColor = "";
                    	mask.style.filter = "";
                    	mask.style.mozOpacity = "";
                    	mask.style.khtmlOpacity = "";
                    	mask.style.opacity = "";
                    } else {
                    	mask.style.backgroundImage = "";
                    	mask.style.backgroundPosition = "";
                    	mask.style.backgroundRepeat = "";
                    	mask.style.backgroundColor = "red";
                    	mask.style.filter = "alpha(opacity=0)";
                    	mask.style.mozOpacity = "0";
                    	mask.style.khtmlOpacity = "0";
                    	mask.style.opacity = "0";
                    }
                });
            }
            var scrl2Dim = byId('text2_scroll_dim_select');
            var scrl2Spd1 = byId('text2_scroll_speed_select_1');
            var scrl2Spd2 = byId('text2_scroll_speed_select_2');
            if(scrl2Dim){
                var oldVal = DSS.BlockOpr.getProperty('dim');
                var options = scrl2Dim.options;
                for (var i = 0; i < options.length; i++) {
                	if (oldVal == options[i].value) {
                		options[i].selected = "true";
                	}
                }
                if(scrl2Dim.value=='0'){
                	scrl2Spd1.style.display = '';
                	scrl2Spd2.style.display = 'none';
                } else {
                	scrl2Spd1.style.display = 'none';
                	scrl2Spd2.style.display = '';
                }
                oldVal = DSS.BlockOpr.getProperty('speed');
                options = scrl2Spd2.options;
                for (var i = 0; i < options.length; i++) {
                	if (oldVal == options[i].value) {
                		options[i].selected = "true";
                	}
                }
                this.util.events(scrl2Dim,'change',function(e,el){
                    if(scrl2Dim.value=='0'){
                    	scrl2Spd1.style.display = '';
                    	scrl2Spd2.style.display = 'none';
                    } else {
                    	scrl2Spd1.style.display = 'none';
                    	scrl2Spd2.style.display = '';
                    }
                    var newVal = scrl2Dim.value;
                    var oldVal = DSS.BlockOpr.getProperty('dim');
                    DSS.BlockOpr.setProperty('dim',newVal);
                    DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'dim',oldVal,newVal);
                });
                this.util.events(scrl2Spd2,'change',function(e,el){
                    var newVal = scrl2Spd2.value;
                    var oldVal = DSS.BlockOpr.getProperty('speed');
                    DSS.BlockOpr.setProperty('speed',newVal);
                    DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'speed',oldVal,newVal);
                });
            }
        },
        findImgList : function (){
            var imgsDiv = document.getElementById('imgList');
            var imgList = [];
            if(!imgsDiv)return imgList;
            for (var i=0;i<imgsDiv.children.length;i++){
                imgList.push(JSON.parse(imgsDiv.children[i].params));
            }
            return imgList;
        },
        editScrollTitle : function (el){
            var item = DSS.util.findPElByCls(el,'scroll_item');
            var params = JSON.parse(item.params);
            params.title = el.value;
            item.params = JSON.stringify(params);
            var old = DSS.BlockOpr.getProperty('img_list');
            var nnew =  this.findImgList();
            DSS.BlockOpr.setProperty('img_list',nnew);
            DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'img_list',old,nnew);
        },
        findWorkList : function (){
            var eWorksDiv = document.getElementById('eWorksInner');
            var workList = [];
            if(!eWorksDiv)return workList;
			var eWrokItems = eWorksDiv.children;
            for (var i=0, len=eWrokItems.length; i<len; i++){
                workList.push(JSON.parse(eWrokItems[i].params));
            }
            return workList;
        },
        saveWorksInfo : function (el){
            var item = DSS.util.findPElByCls(el,'scroll_item');
            var params = JSON.parse(item.params);
			if(el.className == "workName"){ // 作品标题失去焦点
				params.title = el.value;
			}else{
				params.author = el.value;
			}
            item.params = JSON.stringify(params);
            var old = DSS.BlockOpr.getProperty('articleList');
            var nnew =  this.findWorkList();
            DSS.BlockOpr.setProperty('articleList',nnew);
            DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'articleList',old,nnew);
        }
    };

    PropertyBar.init();
    DSS.PropertyBar = PropertyBar;

     //属性区页面动态效果
    (function (){
        var byId = function (id){
            return  typeof (id)=='string'?document.getElementById(id):id;
        };
        /**
         * 属性区块动态展开及收起
         */
        var navs = byClass('prpt_nav')
        for(var i=0;i<navs.length;i++){
           DSS.util.events(navs[i],'click',function (e,el){
               DSS.util.togClass(el.parentNode,'check');
           })
        }


        /**
         * 颜色值选择块的“其它”选项展开和收起
         */
        var clrSlcts = document.getElementsByClassName('color_select_list');
        for(var i=0;i<clrSlcts.length;i++){
            var moreBtn = clrSlcts[i].getElementsByClassName('more_color_select_btn')[0];
            DSS.util.events(moreBtn,'click',function (e,el){
                var morePanel = el.parentNode.getElementsByClassName('more_color_select_panel')[0];
                DSS.util.togClass(morePanel,'check');
            })
        }

        DSS.util.events(document.body,'click',function (e,el){
            var moreBtn = DSS.util.findPElByCls(e,'more_color_select_btn');
            if(moreBtn) {
                return;
            }else{
                var els =  document.getElementsByClassName('more_color_select_panel');
                for(var i=0;i<els.length;i++){
                    DSS.util.dClass(els[i],'check');
                }
            }
        })


    })()
})();
