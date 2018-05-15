/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-17
 * Time: 上午9:23
 * To change this template use File | Settings | File Templates.
 */

(function(){
    var byId = DSS.util.byId;
    var util = DSS.util;
    
    


  //编辑区监听
    util.events(DSS.Options.el.mainBox,'mousedown',function(event){
    		var ev =event.srcElement?event.srcElement:event.target;
    		var mylayer = DSS.util.byId("mylayer");
    		var mylayerLock = DSS.util.byId("mylayerLock"); // 普通编辑页面的 右键菜单（锁定  解锁）
    		var mylayerQ = DSS.util.byId("mylayerQ"); // 快速编辑页面的 右键菜单（置顶 置底 上移一层 下移一层）
        	if(ev.id == 'main_box_inner'){
        		 if(DSS.util.byId('quick').value=='1'){
        			 mylayerQ.style.display = "none";
        		 }else{
        			 mylayer.style.display = "none";
        		 }
        		return false;
        	}else{
        		var event = event || window.event;
        		var mainbox = DSS.Options.el.mainBox;
                // 快速发布的状态下  右键菜单不允许出现
                if(DSS.util.byId('quick').value=='1'){
	                if(event.button==2){	
	                	mylayerQ.style.display = "block";
	                	if(event.clientY+mylayerQ.clientHeight>mainbox.clientHeight*scale){
	                		mylayerQ.style.top =  event.clientY - mylayerQ.clientHeight + 'px';
						}
						else{
							mylayerQ.style.top = event.clientY+ "px";
						}
						mylayerQ.style.left = event.clientX + "px";
						var tableBox=DSS.Options.el.layerQtable;
						
						DSS.util.byId(tableBox.rows[0].id).onclick=function(){
							DSS.Controller.execute('CMD_BLOCK_LEVEL_TOP')
						}
						DSS.util.byId(tableBox.rows[1].id).onclick=function(){
							DSS.Controller.execute('CMD_BLOCK_LEVEL_BOTTOM')
						}
						DSS.util.byId(tableBox.rows[2].id).onclick=function(){
							DSS.Controller.execute('CMD_BLOCK_LEVEL_UP')
						}
						DSS.util.byId(tableBox.rows[3].id).onclick=function(){
							DSS.Controller.execute('CMD_BLOCK_LEVEL_DOWN')
						}
	                }
	             }
                else if(DSS.util.byId('quick').value!='1'){
	                if(event.button==2){	
	                	// 锁定状态下  只允许解锁 锁定 两个操作
	                	if(ev.parentNode.parentNode.style.opacity == 0.5){
	                		mylayerLock.style.display = "block";
							if(event.clientY>444){
								mylayerLock.style.top =  "350px";
							}
							else{
								mylayerLock.style.top = event.clientY+ "px";
							}
							mylayerLock.style.left = event.clientX +20+ "px";
							var tableBox=DSS.Options.el.layerLocktable;
							
							DSS.util.byId(tableBox.rows[0].id).onclick=function(){
								DSS.Controller.execute('CMD_BLOCK_LOCK')
							}
							DSS.util.byId(tableBox.rows[1].id).onclick=function(){
								DSS.Controller.execute('CMD_BLOCK_UNLOCK')
							}
                		}
						else/* if(event.target.parentNode.parentNode.style.opacity == 1)*/
						{
							mylayer.style.display = "block";
							//alert(event.clientY+"--"+mylayer.clientHeight+"---"+mainbox.clientHeight*scale)
//							if(event.clientY+mylayer.clientHeight>mainbox.clientHeight*scale){
//								mylayer.style.top =  event.clientY - mylayer.clientHeight + 'px';
//							}
//							else{
//								 mylayer.style.top = event.clientY+ "px";
//							}
							var public_headhei=$(".public_head").height()
							var titlehei=$(".title").height()
							var photohei=$(".photos").height()
							if(event.clientY<=(public_headhei+titlehei+photohei)){
								mylayer.style.top = (public_headhei+titlehei+photohei)+ "px";
							}else{
								mylayer.style.top = event.clientY+ "px";
							}
							
							mylayer.style.left = event.clientX + "px";
							var tableBox=DSS.Options.el.layerTable;
							DSS.util.byId(tableBox.rows[0].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_CUT')
							}
							DSS.util.byId(tableBox.rows[1].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_COPY')
							}
							DSS.util.byId(tableBox.rows[2].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_PASTE')
							}
							DSS.util.byId(tableBox.rows[3].id).onclick=function(){
								//DSS.Level.top(selectId);
								DSS.Controller.execute('CMD_BLOCK_LEVEL_TOP')
							}
							DSS.util.byId(tableBox.rows[4].id).onclick=function(){
								//DSS.Level.bottom(selectId);
								DSS.Controller.execute('CMD_BLOCK_LEVEL_BOTTOM')
							}
							DSS.util.byId(tableBox.rows[5].id).onclick=function(){
								//DSS.Level.moveUp(selectId);
								DSS.Controller.execute('CMD_BLOCK_LEVEL_UP')
							}
							DSS.util.byId(tableBox.rows[6].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_LEVEL_DOWN')
							}
							DSS.util.byId(tableBox.rows[7].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_DEL')
							}
							/* 【普通节目编辑页面的   右键 锁定 解锁 功能  如需增加功能 放开屏蔽】
							 * DSS.util.byId(tableBox.rows[8].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_LOCK')
							}
							DSS.util.byId(tableBox.rows[9].id).onclick=function(){
								//DSS.Level.moveDown(selectId);
								DSS.Controller.execute('CMD_BLOCK_UNLOCK')
							}*/
						}
						if($("#mylayer").css("display")=="block")
			         	{
			         		$(document).bind("click",function(e){
				         		var target = $(e.target);//获取点击时间
								if(target.closest("#mylayer").length == 0)
								{
									$("#mylayer").hide()
								}
				         	})
			         	}
	                }
                }
                DSS.BlockOpr.mainBoxMsDwn(event);
    	}
    	
    });
    // 鼠标划出编辑区域时 /*DSS.Options.el.mainBoxIn*/
    util.events(document.body,'mouseout',function(e){
    	 DSS.BlockOpr.mainBoxMsOut(e);
    });
    util.events(document.body,'mousemove',function(e){
        DSS.BlockOpr.mainBoxMsMv(e);
    });

    util.events(document.body,'mouseup',function(e){
        DSS.BlockOpr.mainBoxMsUp(e);
    });
    util.events(document.body,'click',function(e){
    	// 点击左键 右键菜单消失
    	if(DSS.util.byId('quick').value=='1'){
	        var mylayerQ= DSS.util.byId("mylayerQ");
	        mylayerQ.style.display="none";
    	}else if(DSS.util.byId('quick').value=='0'){
    		// 普通编辑  右键大菜单 （复制 粘贴 剪切 置顶 置底 上移一层 下移一层 删除）
    		var mylayer= DSS.util.byId("mylayer");
	        mylayer.style.display="none";
	        // 普通编辑  右键小菜单（锁定 解锁）  锁定 解锁功能增加后 可放开
	        //var mylayerLock = DSS.util.byId("mylayerLock");
	        //mylayerLock.style.display="none";
    	}
    });
    //按钮监听
    for(var btn in DSS.Options.btn){
        if(!util.byId(btn))continue;
        (function(b){
            util.events(util.byId(b),'click',function(){
                DSS.Controller.execute(DSS.Options.btn[b]);
            })
        })(btn);
    }

    DSS.Frame = {};
    DSS.Frame.setEditBoxSize  = function (o){
        DSS.Options.cur.w = o.w;
        DSS.Options.cur.h = o.h;
        var box = DSS.Options.el.mainBox;
        box.style.width = o.w +'px';
        box.style.height = o.h +'px';
    };

  //初始化编辑区尺寸
    DSS.Frame.setEditBoxSize({w:DSS.Options.cur.w,h:DSS.Options.cur.h});


    //点击按钮出现下拉菜单的函数
    DSS.Frame.showBar = function (btnEle,barEle,hei,fn){
    	//点击按钮出现菜单
        btnEle.children[0].onclick = function (event){
            var ch = barEle.children[0];
            barEle.style.display = 'block';
            event.stopPropagation();
        }

        //点选完菜单后收起菜单
        var chs =  barEle.children[0].getElementsByTagName('li');

        for(var i=0;i<chs.length;i++){
        	if(!chs[i].className)continue;
        	(function(pEl,el,bar,fnc){
                if(!fnc)return;
                el.onclick = function (){
                    fnc(el,pEl,btnEle);
                    //pEl.style.height = '';
                    //var ht = pEl.offsetHeight;
                    //pEl.style.height = ht + 'px';
                    //pEl.style.overflow = 'hidden';
                    bar.style.display = 'none';
                };
            })(barEle.children[0],chs[i],barEle,fn)
        }


      //点击body，收起菜单
        DSS.util.events(document.body,'click',function(e){
            barEle.children[0].style.overflow = 'hidden';
            barEle.style.display = 'none';
        });


    }

    /**
     *  页面比率处理
     */

    //选择页面比率
    DSS.Frame.checkRatio = function (el,pEl){
        var chs = pEl.getElementsByClassName('check');
        for(var i=0;i<chs.length;i++){
            util.dClass(chs[i],'check');
        }
        util.aClass(el,'check');
    }

  //点击页面比率按钮，出现页面比率选择框
    DSS.Frame.showBar(util.byId('page_ratio'),util.byId('page_ratio_bar'),68,function(el,pEl,bEl){
    	// 点击自定义后  直接弹框  不获取之前的自定义
        if(el.id=='page_rate_custom'){
        	return ;
        }
        document.getElementById("page_rate_custom").className = 'ratio_select';
        DSS.Frame.checkRatio(el,pEl);
        util.setInnerText(bEl.children[0],util.getInnerText(el.children[0]));
        var strs = el.getAttribute('_val').split('*');
        DSS.Controller.execute('CMD_PROGRAM_SIZE',{w:strs[0],h:strs[1]});
    });
    

  //设置页面的初始比例为后台传过来的值
    DSS.Frame.initRatio = function (w,h,fn){
        var btnSp =  DSS.util.byId('page_ratio').children[0],checkUl = DSS.util.byId('page_ratio_bar').children[0];
        var pgRatio  = w + '*' + h;
        
        var chs = checkUl.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i].getAttribute('_val')==pgRatio){
                DSS.Frame.checkRatio(chs[i],checkUl);
                if(DSS.util.getInnerText(chs[i].children[0]) == '自定义'){
                	 break;
				}
                DSS.util.setInnerText(btnSp,DSS.util.getInnerText(chs[i].children[0]));
                break;
            }
        }
        
        loadautoheight();
        adjustPlayer(w,h);
        
        if (fn)fn();
        DSS.Controller.snapPage(); // 新创建一个节目 进入节目制作页 左侧第一个page页是带有黑色背景的缩略图
    };

    function loadautoheight(){
		var pagewinheight = $(window).outerHeight(true);
		if(pagewinheight<=590){
			pagewinheight=590
		}
		var headheight=$(".public_head").height()
		var footheight=$(".foot").height()
	    var pagetitle = $('.container > .title').height();
	    var pagenav = $('.container > nav').height();
	    $('.container > section').height(pagewinheight-pagetitle-pagenav-headheight-footheight-10-10-10-10-10);
	}

    function adjustPlayer(w, h) {
        var mainbox = $(".container > section article .main");
        var mainboxval = $(mainbox).attr('val');

        if(!mainboxval) {
            var pageWidth = $('.container > section article').width() ; //浏览器宽度
            var pageHeight = $('.container > section article').height() ; //浏览器高度

            var playerWidth = w; //参数宽度
            var playerHeight = h;  //参数高度

            scale = pageWidth / playerWidth; // 缩放比例  浏览器宽度 / 编辑框宽度
            if (playerHeight * scale > pageHeight) // 判断比例纵横向
            {scale = pageHeight / playerHeight;}

            var l = (pageWidth - playerWidth * scale) / 2 ;
            var t = (pageHeight - playerHeight * scale) / 2 ;
            l = Math.round(l);
            t = Math.round(t);

            DSS.Options.view.scale = scale;

            $(mainbox).parent().scrollTop(0);
            $(mainbox).parent().css('overflow','');

            $(".container > section article .main").css({
                'margin': '',
                 "-ms-transform-origin":"left top",
                "-webkit-transform-origin":"left top",
                 "-moz-transform-origin":"left top",
                 "-o-transform-origin":"left top",
                 "transform":"matrix("+scale+",0,0,"+scale+","+l+","+t+")",
                 "-ms-transform":"matrix("+scale+",0,0,"+scale+","+l+","+t+")",
                 "-moz-transform":"matrix("+scale+",0,0,"+scale+","+l+","+t+")",
                 "-webkit-transform":"matrix("+scale+",0,0,"+scale+","+l+","+t+")",
                 "-o-transform":"matrix("+scale+",0,0,"+scale+","+l+","+t+")"
                 });
        }
        else {
            DSS.Options.view.scale = 1.0;
            $(mainbox).parent().css('overflow','auto');
            $(".container > section article .main").css({
                'margin': '0 auto',
                "transform":"",
                "-ms-transform":"",
                "-moz-transform":"",
                "-webkit-transform":"",
                "-o-transform":""
             });
        }
    }

    DSS.Frame.initRatio(DSS.Options.cur.w,DSS.Options.cur.h);

    $('.slidewindow').on('click',function(){
    	var curtext = $(this).text();
    	$(this).text(curtext=='实际大小'?'窗口大小':'实际大小');
    	var mainbox = $(".container > section article .main");
    	!$(mainbox).attr('val')?$(mainbox).attr('val','1'):$(mainbox).removeAttr('val');
    	adjustPlayer(DSS.Options.cur.w,DSS.Options.cur.h);
    });
    
    function aresize(){
        loadautoheight();
    	adjustPlayer(DSS.Options.cur.w,DSS.Options.cur.h);
    }
    
    $(window).on('resize',aresize);

    /**
     * 页面上方按钮菜单处理
     */
    //页面上方菜单初始化
    DSS.Request.loadBtn(function (rs){
        if(rs=='null')return;
        var list = rs.split(',');

        for(var i=0;i<list.length;i++){
            var btn = util.byId(list[i]);
            if(btn){
                util.aUniqueClass(btn,'show');
            }
        }

        var barEl =  util.byId('btn_menu_bar');
        var chs = barEl.getElementsByTagName('li');
        for(var i=0;i<chs.length;i++){
            var tarId = chs[i].getAttribute('_tar');
            if(tarId){
                if(rs.indexOf(tarId)>-1){
                    util.aClass(chs[i],'check');
                }
            }
        }
    });

    //点击菜单按钮，出现按钮选择列表
    DSS.Frame.showBar(util.byId('btn_menu'),util.byId('btn_menu_bar'),376,function(el,pEl,bEl){
        if(util.cClass(el,'check')){
            util.dClass(el,'check');
            var tarBtn = util.byId(el.getAttribute('_tar'));
            if(tarBtn){
                util.dClass(tarBtn,'show');
            }
        }else{
            util.aClass(el,'check');
            var tarBtn = util.byId(el.getAttribute('_tar'));
            if(tarBtn){
                util.aUniqueClass(tarBtn,'show');
            }
        }

      //查找所有被check的btn，将btn的id串成字符串传到后台
        var chs = pEl.getElementsByClassName('check');
        var list = [];
        for(var i=0;i<chs.length;i++){
            var tarId = chs[i].getAttribute('_tar');
            if(tarId)list.push(tarId);
        }
        var idsStr = list.join(',');
        if(!idsStr)idsStr = 'null'
        DSS.Request.saveBtn(idsStr);
    });

    /**
     * 页面操作板块的收起与展开
     */
    (function (bEl,pEl){
        bEl.onclick = function (){
            if(!pEl.isFold){
                util.startMove(pEl,{width:bEl.parentNode.offsetWidth},function (){
                    pEl.isFold = true;
                    bEl.children[0].style.borderWidth='7px 0 7px 7px ';

                    adjustPlayer(DSS.Options.cur.w,DSS.Options.cur.h);
                });
            }else{
                util.startMove(pEl,{width:pEl.children[0].offsetWidth},function (){
                    pEl.isFold = false;
                    bEl.children[0].style.borderWidth='7px 7px 7px 0 ';

                    adjustPlayer(DSS.Options.cur.w,DSS.Options.cur.h);
                });
            }
        }
    })(util.byId('page_panel_opr_btn'),util.byId('page_panel'));

    /**
     *属性区板块的收起与展开
     */
    (function (bEl,pEl){
        bEl.onclick = function (){
            if(!pEl.isFold){
                util.startMove(pEl,{width:bEl.parentNode.offsetWidth},function (){
                    pEl.isFold = true;
                    bEl.children[0].style.borderWidth='7px 7px 7px 0px ';

                    adjustPlayer(DSS.Options.cur.w,DSS.Options.cur.h);
                });
            }else{
                util.startMove(pEl,{width:pEl.children[0].offsetWidth},function (){
                    pEl.isFold = false;
                    bEl.children[0].style.borderWidth='7px 0 7px 7px ';

                    adjustPlayer(DSS.Options.cur.w,DSS.Options.cur.h);
                });
            }
        }
    })(util.byId('prpt_panel_opr_btn'),util.byId('prpt_panel'));

  //快捷键
    util.events(document,'keydown',function(e){
    	// 快速发布禁用快捷键
        if(DSS.util.byId('quick').value!='1'){
        	if(e.ctrlKey&&e.keyCode==88){
                DSS.Controller.execute('CMD_BLOCK_CUT');
            }else if(e.ctrlKey&&e.keyCode==86){
                DSS.Controller.execute('CMD_BLOCK_PASTE');
            }else if(e.ctrlKey&&e.keyCode==67){
                DSS.Controller.execute('CMD_BLOCK_COPY');
            }else if(e.keyCode==46){
            	var tc = DSS.PageOpr.getTcrId();
            	if(tc == null){
            		DSS.Controller.execute('CMD_BLOCK_DEL');
            	}
            }
        }
    });
})();

