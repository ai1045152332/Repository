function autoh(){
	var winh,bodyh,minbodyh,header,footer,contentool,contentheader,contentleft,contentbody,dsarticle,dsnv,articH;
	winh = document.documentElement.clientHeight;
	bodyh = document.body.clientHeight;
	minbodyh = $('.master-mainbody');
	contentbody = $('.master-content-body');
	contentleft = $('.master-left-bar');
	header = $('.master-header').height();
	//console.log(header)
	footer = $('.master-footer').height();
	contentool = $('.master-content-tool').outerHeight(true);
	contentheader = $('.master-contentheader').outerHeight(true);
	//alert(bodyh + 'haha' + winh)
	
	if(winh<=590){
		winh=590
	}
	$(".master-content-body").height($(".master-content").height()*0.93-40)

	var curh = $(minbodyh).height();
	var contentcurh = curh - contentool - contentheader;
	contentbody.height(contentcurh);
	$(contentbody).find('.datagrid,.datagrid-wrap').height(contentcurh);
	
	dsnv = $('.ds_nv').height();
	var articH = contentcurh-dsnv;
	$('.ds_top .artic,.ds_top_con4,.ds_top_con7,.ds_top_con8').height(articH);
	$('.newtreecls').height(curh-40);

	
	
	
		
}

$(window).load(autoh);
$(window).resize(autoh);