// 搜索下拉效果
function searchslide() {

	var searchtext,
		searchtup,
		clickmore,
		searchtupli;

	inputtext = $('#searchtext');
	searchtup = $('#selectup');
	clickmore = $('#clickmore');

	searchtupli = $(searchtup).children('li');
	inputtext.on('focus blur', function() {
		searchtup.slideToggle(300);
	});
	searchtupli.on('click', function() {
		inputtext.val($(this).html());
	});
};

$(window).load(searchslide);

//元素tab效果
function searchlis(els){
	$(els).addClass('checked').siblings().removeClass('checked');
}

//当前元素传参
function eachel(){
		$(this).on('click',function(){
			var els = this;
			searchlis(els);
		})
	}

//切换效果元素入口
function searchmoreother() {

	var searchtab,searchlisa,projectpriority,pagination,paginationea;

	searchtab = $('.searchmoreother span');
	searchlisa = $('.searchmore ul li span:not(":first-child")');
	
	pagination = $('.pagination ul li');
	paginationea = $(pagination).not('.each');
	
	
	searchtab.each(eachel);
	searchlisa.each(eachel);
	
	
	//分页效果
	pagination.on('click',function(e){
		var curindex;
		var index = $(e.target).index();
		
		paginationea.each(function(){
			if($(this).hasClass('checked')){
				curindex = $(paginationea).index(this);
			}
		})
		
		if(index == 0){
			curindex--;
			
		}else if(index == pagination.length-1){
			curindex++;
		}else{
			curindex = index-1;
		}
		
		if(curindex<0){
			curindex = 0;
		}else if(curindex>pagination.length-1){
			curindex = pagination.length-1;
		}
		
		var els = paginationea[curindex];
		searchlis(els);
	})
	
	
	
}
$(window).load(searchmoreother);

//加载时筛选下拉菜单默认效果
function searchmoreslide(){
	var searchmore=$('.searchmore')
	var searchseletion = $('.searchselection');
			if($(searchmore).is(':visible')){
				$(searchseletion).val('收起');
			}else{
				$(searchseletion).val('筛选');
			}
		}
$(window).load(searchmoreslide);

// 更多搜索选项筛选切换
function searchseletion() {

	var searchseletion;

	searchseletion = $('.searchselection');
	searchseletion.on('click', function() {
		$('.section').height('auto');
		$('.searchmore').slideToggle(300,function(){
				searchmoreslide();
				prejectlistautoh();
			}).siblings('.searchmoreother').slideToggle(300);
	})
}

$(window).load(searchseletion);

// 待审核节目全选 单选 多选按钮效果
function selectall() {

	var selectall,
		selectlis,
		selectallstate,
		selectlistate;

	selectall = $('.checkin .selectall');
	selectlis = $('.checkin ul li .inputcheckbox');

	selectall.on('click', function() {
		selectallstate = $(this).children('.inputcheckbox').toggleClass('checked');
		if ($(selectallstate).hasClass('checked')) {
			$(selectlis).addClass('checked');
		} else {
			$(selectlis).toggleClass('checked');
		}

	})
	selectlis.on('click', function() {
		if ($(selectallstate).hasClass('checked')) {
			$(selectallstate).toggleClass('checked');
		};
		$(this).toggleClass('checked');
	})
}

$(window).load(selectall);

//替换内容为title文字
function titletext() {
		var curtext = $(this).text();
		$(this).attr('title', curtext);
	}

// title元素入口
function checkingtitle() {

	var checkingtitle,quickupdatet;

	checkingtitle = $('.section>ul>li .name h1');
	quickupdatet = $('.section>ul>li .driver p');
	
	checkingtitle.each(titletext);
	quickupdatet.each(titletext);
}

$(window).load(checkingtitle);


// 弹出层tab
function poptab() {

	var poptab;

	poptab = $('.tab');
	poptab.on('click', function() {
		$(this).addClass('checked').siblings().removeClass('checked');
	})
}

$(window).load(poptab);

// 弹出层下拉菜单
function selectlist() {

	var selectlistspan,
		selectlistli;

	selectlistspan = $('.selectlist span');
	selectlistli = $('.selectlist ul li');

	selectlistspan.on('click', function() {
		$(this).stop(true,true).siblings('ul').slideDown(300);
	}).on('blur', function() {
		$(this).stop(true).siblings('ul').slideUp(300);
	})
	selectlistli.on('click', function() {
		$(this).parent('ul').siblings('span').html($(this).html());
		$(this).stop(true,true).parent('ul').slideUp(300);
	})
};


$(window).load(selectlist);


//弹出层loading
function popupload(dis){
	var loadpw = $(dis).width();
	var loadi = $(dis).find('.inline');
	var loadiw = $(loadi).width();
	
	$(loadi).css({'display':'block','margin-left':-loadiw});
	
	function moveing(){
			$(loadi).animate({'margin-left':loadpw},3000,function(){
				$(this).css('margin-left',-loadiw);
			});
	}
	
	if($(dis).is(':visible')){
		setInterval(moveing,3000);		
	}else{
		clearInterval(moveing,3000);
	}
}

//弹出层定位居中
function popsitoin(dis) {
	var popinimg,
		popintext;
		
			var popwidth = $(dis).outerWidth(true);
			var popheight = $(dis).outerHeight(true);
			
			if(!$(dis).is(':hidden')){
			
				$(dis).css({
					'margin-left': -popwidth / 2,
					'margin-top': -popheight / 2
				});
			
			}else{
				$(dis).css({
					'margin':''
				})
			}
			
			popintext = $(dis).find('.img');
			popinimg = $(popintext).find('.text').children('img');
			
			
			$(popinimg).load(function(){
					var textw = $(popintext).width();
					var texth = $(popintext).height();
					var imgw = $(popinimg).width();
					var imgh = $(popinimg).height();
					
					if(imgw > textw){
						$(this).css({
							'width':textw,
							'height':'auto'
								})
						texth = $(popintext).height();
						imgh = $(this).height();
						if(imgh>texth){
							$(this).css({
								'width':'auto',
								'height':texth
									})
						}
						
					}else{
						$(this).css({
							'width':'auto',
							'height': texth
								})
						imgw = $(this).width();
						textw = $(popintext).width();
						if(imgw > textw ){
							$(this).css({
								'width': textw,
								'height': 'auto'
									})
						}
						
					}
					
					texth = $(popintext).height();
					imgh = $(this).height();
					
					if(texth>imgh){
						var loadedh = (texth - imgh)/2;
						loadedh<0?-loadedh:loadedh;
						$(popinimg).css('margin-top',loadedh);
					}
					
			})
			
			
			
}

// 打开关闭弹出层
function poponclose() {
	var poponclose,
		popxjjm,
		popdrjm,
		btncancle,
		popupk,
		popupbg,
		dis;

	popleftels = $('.section .header .left>span');
	poponclose = $('.popup .header .right'); // 弹出层关闭按钮
	popupbg = $('.popup-bg');
	btncancle = $(".cancel"); // 我的节目处->空白节目的取消按钮
	function guanbi() {
		$(this).parents('.popup').hide().siblings('.popup-bg').hide();
		dis = $('.popup');
		popsitoin(dis);
	}


	// 点击新建节目
		$(popleftels).on('click', function() {
			
			var thistext = $(this).text();
			switch(thistext){
			case '新建节目': 
				dis = $('#xjjm');
				break;
			case '导入节目':
				dis = $('#drjm');
				$("#impfilename").val("");
				$("#impfileurl").val("");
				break;	
			}
			$(dis).show().siblings('.popup-bg').show();
			popsitoin(dis);
			
		})
		
		myprograma = $('.section>ul>li .name .gn')


	poponclose.on('click', guanbi);
	btncancle.on('click', guanbi);
	
}

$(window).load(poponclose);


//列表页 计算section的高度
function prejectlistautoh(){
	var bodyh = $('body').height();
	var winh = $(window).height();
	var headh = $('.master-header').outerHeight(true);
	var nav = $('.nav').outerHeight(true);
	var footer = $('.master-footer').outerHeight(true);
	
	
	if( bodyh<winh ){
		$('.section').height(winh-headh-nav-footer-20);
		$('body').css('overflow','hidden');
	}else{
		$('.section').height('auto');
		$('body').css('overflow','');
	}
	prejectlistmargin();
	
}

// 列表页排列间距
function prejectlistmargin(){
	var secli = $('.section > ul > li');
	var seclione = $(secli).eq(0).width();
	//var seclionew = $(seclione).width();
	var secparent = $(secli).parent('ul');
	var secparentw = $(secparent).width();
	var chaw=0;
	var i=j=0;
	
	for(i=1;i<=secli.length;i++){
		var secliw = i*seclione;
		if((secparentw-secliw)<seclione&&(secparentw-secliw)>0){
			j = i;
		   chaw = secparentw - secliw;
		   $(secli).css('margin-right',(chaw/(i-1)));
		}
		if(i%j == 0){
			$(secli).eq(i-1).css('margin-right','0px');
		}
		
	};
	
}
$(window).load(prejectlistautoh);

//resize事件总会
$(window).resize(function(){
	popsitoin();
	prejectlistautoh();
	prejectlistmargin();
});


