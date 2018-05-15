$(function(){
	
	var exphei=document.documentElement.clientHeight;
	var expwidth=document.documentElement.clientWidth;
	var footerheight=parseInt(exphei*0.04);
	//通用处理
	$(".foot_center").css({"line-height":footerheight+"px"});
	//获取导航的高度
	var navhei=$(".public_nav").height()
	$(".public_nav ul li").height(navhei)
	
	//所有分辨率
	if(screen.width==1920&&screen.height==1080||screen.width==1680&&screen.height==1050||screen.width==1400&&screen.height==1050||screen.width==1280&&screen.height==1024||screen.width==1600&&screen.height==900||screen.width==1440&&screen.height==900||screen.width==1366&&screen.height==768||screen.width==1360&&screen.height==768||screen.width==1280&&screen.height==800||screen.width==1280&&screen.height==960||screen.width==1280&&screen.height==720||screen.width==1280&&screen.height==600||screen.width==1024&&screen.height==738){
		//判断浏览器当前的高度，依据高度区间重新定义样式表
		//alert(exphei+"---"+expwidth)
		if(exphei>=900){
			//$(".foot_center").append(1+"--"+exphei)
			//导航区域
			$(".n_loginhead").css({"background-position":"57px 25px"});
			$(".public_head_logo").css({"background-position":"0px 10px"});
			$(".public_headbg").css({"background-position":"-50px -348px"});
			$(".public_nav ul li a").css({"line-height":"630%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"40px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"40px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"38px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"37px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"37px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"40px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"35px"});
			$(".public_setting").css({"top":"25px"});
			//巡课
			$(".equipment").css({"line-height":"370%"});
			$(".xk_options").css({"margin":"10px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"15px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"370%"});
			$(".vipt_head_option").css({"margin-top":"0.3%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.9%"});
			$(".mm_head_nummachine").css({"margin-top":"0.7%"});
			$(".mm_nogroup_option").css({"margin-top":"0.8%"});
			$(".mmn_camera_option").css({"margin-top":"0.9%"});
			$(".refreshpage").css({"margin-top":"0.8%"});
			$(".groupname").css({"line-height":"370%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"400%"});
			$(".settinghead ul li .tb").css({"margin-top":"13%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"13%"});
			$(".settinghead ul li .living").css({"margin-top":"13%"});
			$(".settinghead ul li .nas").css({"margin-top":"10%"});
			$(".settinghead ul li .passuser").css({"margin-top":"7%"});
			$(".settinghead ul li .ftp").css({"margin-top":"13%"});
			$(".settinghead ul li .license").css({"margin-top":"13%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"12%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"13%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"11%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"12%"});
			/*用户管理*/
			$(".user_head_option").css({"line-height":"420%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"15%"});
			$(".user_search").css({"margin-top":"0.7%"});
			
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.7%"});
			$(".table_head,.table_recycle").css({"line-height":"370%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.7%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.7%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"300%"});
			
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"60px","line-height":"60px"})
		
		}else if(exphei>=800&&exphei<900){
			//$(".foot_center").append(2+"--"+exphei)
			//导航区域
			$(".n_loginhead").css({"background-position":"57px 15px"});
			$(".public_head_logo").css({"background-position":"0px 5px"});
			$(".public_headbg").css({"background-position":"-50px -352px"});
			$(".public_nav ul li a").css({"line-height":"550%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"34px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"34px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"32px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"31px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"31px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"34px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"29px"});
			$(".public_setting").css({"top":"20px"});
			//巡课
			$(".equipment").css({"line-height":"320%"});
			$(".xk_options").css({"margin":"7px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"10px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"320%"});
			$(".vipt_head_option").css({"margin-top":"0.5%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.7%"});
			$(".mm_head_nummachine").css({"margin-top":"0.5%"});
			$(".mm_nogroup_option").css({"margin-top":"0.7%"});
			$(".mmn_camera_option").css({"margin-top":"0.7%"});
			$(".refreshpage").css({"margin-top":"0.6%"});
			$(".groupname").css({"line-height":"320%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"320%"});
			$(".settinghead ul li .tb").css({"margin-top":"9%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"8%"});
			$(".settinghead ul li .living").css({"margin-top":"9%"});
			$(".settinghead ul li .nas").css({"margin-top":"7%"});
			$(".settinghead ul li .passuser").css({"margin-top":"5%"});
			$(".settinghead ul li .ftp").css({"margin-top":"9%"});
			$(".settinghead ul li .license").css({"margin-top":"9%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"8%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"6%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"7%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"7%"});

			/*用户管理*/
			$(".user_head_option").css({"line-height":"320%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"10%"});
			$(".user_search").css({"margin-top":"0.5%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.5%"});
			$(".table_head,.table_recycle").css({"line-height":"300%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.5%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.5%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"270%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"55px","line-height":"55px"})
		
		}else if(exphei>=700&&exphei<800){
			//$(".foot_center").append(3+"--"+exphei)
			$(".n_loginhead").css({"background-position":"57px 10px"});
			$(".public_head_logo").css({"background-position":"0px 0px"});
			$(".public_headbg").css({"background-position":"-50px -358px"});
			$(".public_nav ul li a").css({"line-height":"480%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"28px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"28px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"26px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"28px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"23px"});
			$(".public_setting").css({"top":"20px"});
			//巡课
			$(".equipment").css({"line-height":"320%"});
			$(".xk_options").css({"margin":"5px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"8px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"280%"});
			$(".vipt_head_option").css({"margin-top":"0.3%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.5%"});
			$(".mm_head_nummachine").css({"margin-top":"0.3%"});
			$(".mm_nogroup_option").css({"margin-top":"0.5%"});
			$(".mmn_camera_option").css({"margin-top":"0.5%"});
			$(".groupname").css({"line-height":"280%"});
			$(".refreshpage").css({"margin-top":"0.4%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"320%"});
			$(".settinghead ul li .tb").css({"margin-top":"9%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"8%"});
			$(".settinghead ul li .living").css({"margin-top":"9%"});
			$(".settinghead ul li .nas").css({"margin-top":"7%"});
			$(".settinghead ul li .passuser").css({"margin-top":"5%"});
			$(".settinghead ul li .ftp").css({"margin-top":"9%"});
			$(".settinghead ul li .license").css({"margin-top":"9%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"8%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"7%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"7%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"7%"});

			/*用户管理*/
			$(".user_head_option").css({"line-height":"320%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"10%"});
			$(".user_search").css({"margin-top":"0.4%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.4%"});
			$(".table_head,.table_recycle").css({"line-height":"260%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.3%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.3%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"200%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"48px","line-height":"48px"})
		
		}else if(exphei>=620&&exphei<700){
			//$(".foot_center").append(4+"--"+exphei)
			$//导航区域
			$(".n_loginhead").css({"background-position":"57px 5px"});
			$(".public_head_logo").css({"background-position":"0px 0px"});
			$(".public_headbg").css({"background-position":"-50px -362px"});
			$(".public_nav ul li a").css({"line-height":"400%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"23px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"20px"});
			$(".public_setting").css({"top":"10px"});
			//巡课
			$(".equipment").css({"line-height":"260%"});
			$(".xk_options").css({"margin":"2px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"5px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"280%"});
			$(".vipt_head_option").css({"margin-top":"0.2%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.3%"});
			$(".mm_head_nummachine").css({"margin-top":"0.2%"});
			$(".mm_nogroup_option").css({"margin-top":"0.5%"});
			$(".mmn_camera_option").css({"margin-top":"0.3%"});
			$(".groupname").css({"line-height":"260%"});
			$(".refreshpage").css({"margin-top":"0.3%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"260%"});
			$(".settinghead ul li .tb").css({"margin-top":"6%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"5%"});
			$(".settinghead ul li .living").css({"margin-top":"6%"});
			$(".settinghead ul li .nas").css({"margin-top":"4%"});
			$(".settinghead ul li .passuser").css({"margin-top":"2%"});
			$(".settinghead ul li .ftp").css({"margin-top":"6%"});
			$(".settinghead ul li .license").css({"margin-top":"6%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"5%"});			$(".settinghead ul li .record_name_setting").css({"margin-top":"11%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"4%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"5%"});
			/*用户管理*/
			$(".user_head_option").css({"line-height":"260%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"6%"});
			$(".user_search").css({"margin-top":"0.2%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.2%"});
			$(".table_head,.table_recycle").css({"line-height":"220%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.2%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.2%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"150%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"40px","line-height":"40px"})
		}else{
			//$(".foot_center").append(5+"--"+exphei)
			//导航区域
			$(".n_loginhead").css({"background-position":"57px 5px"});
			$(".public_head_logo").css({"background-position":"0px 0px"});
			$(".public_headbg").css({"background-position":"-50px -362px"});
			$(".public_nav ul li a").css({"line-height":"400%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"23px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"20px"});
			$(".public_setting").css({"top":"10px"});
			//巡课
			$(".equipment").css({"line-height":"260%"});
			$(".xk_options").css({"margin":"1px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"4px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"220%"});
			$(".vipt_head_option").css({"margin-top":"0.1%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.3%"});
			$(".mm_head_nummachine").css({"margin-top":"0.1%"});
			$(".mm_nogroup_option").css({"margin-top":"0.5%"});
			$(".mmn_camera_option").css({"margin-top":"0.3%"});
			$(".groupname").css({"line-height":"220%"});
			$(".refreshpage").css({"margin-top":"0.2%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"260%"});
			$(".settinghead ul li .tb").css({"margin-top":"6%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"5%"});
			$(".settinghead ul li .living").css({"margin-top":"6%"});
			$(".settinghead ul li .nas").css({"margin-top":"4%"});
			$(".settinghead ul li .passuser").css({"margin-top":"2%"});
			$(".settinghead ul li .ftp").css({"margin-top":"6%"});
			$(".settinghead ul li .license").css({"margin-top":"6%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"4%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"5%"});

			/*用户管理*/
			$(".user_head_option").css({"line-height":"260%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"6%"});
			$(".user_search").css({"margin-top":"0.15%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.1%"});
			$(".table_head,.table_recycle").css({"line-height":"220%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.05%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.05%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"150%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"33px","line-height":"33px"})
			
		}
	}
	$(window).resize(function(){
		if(location.pathname=="/resource/Resource_resourceListNew.do")
		{
			//资源页面
			var democlass=$(".resiframe").contents().find(".demo").attr("class");
			if(democlass==undefined)
			{//无弹窗页执行
				//console.log("a")
				location.href=location.href

			}
			else
			{
				var exphei=document.documentElement.clientHeight;
				var exphei86=exphei*0.86;
				if(exphei86<507)
				{
					exphei86=507
				}
				$(".mm,.res_tree,.res_card").css("height",exphei86+"px")
				//console.log(exphei*0.86)
				//所有分辨率
	if(screen.width==1920&&screen.height==1080||screen.width==1680&&screen.height==1050||screen.width==1400&&screen.height==1050||screen.width==1280&&screen.height==1024||screen.width==1600&&screen.height==900||screen.width==1440&&screen.height==900||screen.width==1366&&screen.height==768||screen.width==1360&&screen.height==768||screen.width==1280&&screen.height==800||screen.width==1280&&screen.height==960||screen.width==1280&&screen.height==720||screen.width==1280&&screen.height==600||screen.width==1024&&screen.height==738){
		//判断浏览器当前的高度，依据高度区间重新定义样式表
		//alert(exphei+"---"+expwidth)
		if(exphei>=900){
			//$(".foot_center").append(1+"--"+exphei)
			//导航区域
			$(".n_loginhead").css({"background-position":"57px 25px"});
			$(".public_head_logo").css({"background-position":"0px 10px"});
			$(".public_headbg").css({"background-position":"-50px -348px"});
			$(".public_nav ul li a").css({"line-height":"630%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"40px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"40px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"38px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"37px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"37px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"40px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"35px"});
			$(".public_setting").css({"top":"25px"});
			//巡课
			$(".equipment").css({"line-height":"370%"});
			$(".xk_options").css({"margin":"10px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"15px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"370%"});
			$(".vipt_head_option").css({"margin-top":"0.7%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.9%"});
			$(".mm_head_nummachine").css({"margin-top":"0.7%"});
			$(".mm_nogroup_option").css({"margin-top":"0.8%"});
			$(".mmn_camera_option").css({"margin-top":"0.9%"});
			$(".refreshpage").css({"margin-top":"0.8%"});
			$(".groupname").css({"line-height":"370%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"400%"});
			$(".settinghead ul li .tb").css({"margin-top":"13%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"13%"});
			$(".settinghead ul li .living").css({"margin-top":"13%"});
			$(".settinghead ul li .nas").css({"margin-top":"10%"});
			$(".settinghead ul li .passuser").css({"margin-top":"7%"});
			$(".settinghead ul li .ftp").css({"margin-top":"13%"});
			$(".settinghead ul li .license").css({"margin-top":"13%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"12%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"12%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"11%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"12%"});
			/*用户管理*/
			$(".user_head_option").css({"line-height":"420%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"15%"});
			$(".user_search").css({"margin-top":"0.7%"});

			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.7%"});
			$(".table_head,.table_recycle").css({"line-height":"370%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.7%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.7%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"300%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"60px","line-height":"60px"})

		}else if(exphei>=800&&exphei<900){
			//$(".foot_center").append(2+"--"+exphei)
			//导航区域
			$(".n_loginhead").css({"background-position":"57px 15px"});
			$(".public_head_logo").css({"background-position":"0px 5px"});
			$(".public_headbg").css({"background-position":"-50px -352px"});
			$(".public_nav ul li a").css({"line-height":"550%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"34px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"34px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"32px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"31px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"31px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"34px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"29px"});
			$(".public_setting").css({"top":"20px"});
			//巡课
			$(".equipment").css({"line-height":"320%"});
			$(".xk_options").css({"margin":"7px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"10px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"320%"});
			$(".vipt_head_option").css({"margin-top":"0.5%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.7%"});
			$(".mm_head_nummachine").css({"margin-top":"0.5%"});
			$(".mm_nogroup_option").css({"margin-top":"0.7%"});
			$(".mmn_camera_option").css({"margin-top":"0.7%"});
			$(".refreshpage").css({"margin-top":"0.6%"});
			$(".groupname").css({"line-height":"320%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"320%"});
			$(".settinghead ul li .tb").css({"margin-top":"9%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"8%"});
			$(".settinghead ul li .living").css({"margin-top":"9%"});
			$(".settinghead ul li .nas").css({"margin-top":"7%"});
			$(".settinghead ul li .passuser").css({"margin-top":"5%"});
			$(".settinghead ul li .ftp").css({"margin-top":"9%"});
			$(".settinghead ul li .license").css({"margin-top":"9%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"8%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"7%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"7%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"7%"});
			/*用户管理*/
			$(".user_head_option").css({"line-height":"320%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"10%"});
			$(".user_search").css({"margin-top":"0.5%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.5%"});
			$(".table_head,.table_recycle").css({"line-height":"300%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.5%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.5%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"270%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"55px","line-height":"55px"})

		}else if(exphei>=700&&exphei<800){
			//$(".foot_center").append(3+"--"+exphei)
			$(".n_loginhead").css({"background-position":"57px 10px"});
			$(".public_head_logo").css({"background-position":"0px 0px"});
			$(".public_headbg").css({"background-position":"-50px -358px"});
			$(".public_nav ul li a").css({"line-height":"480%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"28px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"28px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"26px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"28px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"23px"});
			$(".public_setting").css({"top":"20px"});
			//巡课
			$(".equipment").css({"line-height":"320%"});
			$(".xk_options").css({"margin":"5px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"8px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"280%"});
			$(".vipt_head_option").css({"margin-top":"0.3%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.5%"});
			$(".mm_head_nummachine").css({"margin-top":"0.3%"});
			$(".mm_nogroup_option").css({"margin-top":"0.5%"});
			$(".mmn_camera_option").css({"margin-top":"0.5%"});
			$(".groupname").css({"line-height":"280%"});
			$(".refreshpage").css({"margin-top":"0.4%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"320%"});
			$(".settinghead ul li .tb").css({"margin-top":"9%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"8%"});
			$(".settinghead ul li .living").css({"margin-top":"9%"});
			$(".settinghead ul li .nas").css({"margin-top":"7%"});
			$(".settinghead ul li .passuser").css({"margin-top":"5%"});
			$(".settinghead ul li .ftp").css({"margin-top":"9%"});
			$(".settinghead ul li .license").css({"margin-top":"9%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"7%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"7%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"7%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"7%"});
			/*用户管理*/
			$(".user_head_option").css({"line-height":"320%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"10%"});
			$(".user_search").css({"margin-top":"0.4%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.4%"});
			$(".table_head,.table_recycle").css({"line-height":"260%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.3%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.3%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"200%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"48px","line-height":"48px"})

		}else if(exphei>=620&&exphei<700){
			//$(".foot_center").append(4+"--"+exphei)
			$//导航区域
			$(".n_loginhead").css({"background-position":"57px 5px"});
			$(".public_head_logo").css({"background-position":"0px 0px"});
			$(".public_headbg").css({"background-position":"-50px -362px"});
			$(".public_nav ul li a").css({"line-height":"400%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"23px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"20px"});
			$(".public_setting").css({"top":"10px"});
			//巡课
			$(".equipment").css({"line-height":"260%"});
			$(".xk_options").css({"margin":"2px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"5px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"280%"});
			$(".vipt_head_option").css({"margin-top":"0.2%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.3%"});
			$(".mm_head_nummachine").css({"margin-top":"0.2%"});
			$(".mm_nogroup_option").css({"margin-top":"0.5%"});
			$(".mmn_camera_option").css({"margin-top":"0.3%"});
			$(".groupname").css({"line-height":"260%"});
			$(".refreshpage").css({"margin-top":"0.3%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"260%"});
			$(".settinghead ul li .tb").css({"margin-top":"6%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"5%"});
			$(".settinghead ul li .living").css({"margin-top":"6%"});
			$(".settinghead ul li .nas").css({"margin-top":"4%"});
			$(".settinghead ul li .passuser").css({"margin-top":"2%"});
			$(".settinghead ul li .ftp").css({"margin-top":"6%"});
			$(".settinghead ul li .license").css({"margin-top":"6%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"3%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"5%"});

			/*用户管理*/
			$(".user_head_option").css({"line-height":"260%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"6%"});
			$(".user_search").css({"margin-top":"0.2%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.2%"});
			$(".table_head,.table_recycle").css({"line-height":"220%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.2%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.2%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"150%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"40px","line-height":"40px"})
		}else{
			//$(".foot_center").append(5+"--"+exphei)
			//导航区域
			$(".n_loginhead").css({"background-position":"57px 5px"});
			$(".public_head_logo").css({"background-position":"0px 0px"});
			$(".public_headbg").css({"background-position":"-50px -362px"});
			$(".public_nav ul li a").css({"line-height":"400%"});
			$(".public_nav ul li:nth-child(1) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(2) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(3) span").css({"top":"23px"});
			$(".public_nav ul li:nth-child(4) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(5) span").css({"top":"22px"});
			$(".public_nav ul li:nth-child(6) span").css({"top":"25px"});
			$(".public_nav ul li:nth-child(7) span").css({"top":"20px"});
			$(".public_setting").css({"top":"10px"});
			//巡课
			$(".equipment").css({"line-height":"260%"});
			$(".xk_options").css({"margin":"1px 0px 0px 25px"});
			$(".xk_stage_change,.xk_fournine").css({"margin-top":"4px"});
			/*录像计划*/
			$(".vipt_headtext,.vip_head").css({"line-height":"220%"});
			$(".vipt_head_option").css({"margin-top":"0.1%"});
			/*设备管理*/
			$(".mm_head_option").css({"margin-top":"0.3%"});
			$(".mm_head_nummachine").css({"margin-top":"0.1%"});
			$(".mm_nogroup_option").css({"margin-top":"0.5%"});
			$(".mmn_camera_option").css({"margin-top":"0.3%"});
			$(".groupname").css({"line-height":"220%"});
			$(".refreshpage").css({"margin-top":"0.2%"});
			/*系统设置,.videoinfo,.living,.nas,.passuser,.ftp*/
			$(".settinghead ul li").css({"line-height":"260%"});
			$(".settinghead ul li .tb").css({"margin-top":"6%"});
			$(".settinghead ul li .videoinfo").css({"margin-top":"5%"});
			$(".settinghead ul li .living").css({"margin-top":"6%"});
			$(".settinghead ul li .nas").css({"margin-top":"4%"});
			$(".settinghead ul li .passuser").css({"margin-top":"2%"});
			$(".settinghead ul li .ftp").css({"margin-top":"6%"});
			$(".settinghead ul li .license").css({"margin-top":"6%"});
			$(".settinghead ul li .second_nav_selected").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_schedule").css({"margin-top":"4%"});
			$(".settinghead ul li .record_name_setting").css({"margin-top":"5%"});
			$(".settinghead ul li .second_nav_celve").css({"margin-top":"4%"});
			/*用户管理*/
			$(".user_head_option").css({"line-height":"260%"});
			$(".user_head_addicon,.user_head_editicon,.user_head_delicon,.user_head_reseticon").css({"margin-top":"6%"});
			$(".user_search").css({"margin-top":"0.15%"});
			/*日志管理*/
			$(".logmargintop").css({"padding-top":"0.1%"});
			$(".table_head,.table_recycle").css({"line-height":"220%"});
			/*软件统计*/
			$(".analysis_checkbtn").css({"margin-top":"0.05%"});
			/*软件安装*/
			$(".soft_installbtn").css({"margin-top":"0.05%"});
			/*资源管理*/
			$(".classtable_cancel").css({"line-height":"150%"});
			/*信息发布-normal*/
			$(".jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt").css({"height":"33px","line-height":"33px"})

		}
	}
			}

		}else{
			location.href=location.href
		}



	})
	$(".navmorelist a").css({"line-height":"30px","height":"30px"})
})
