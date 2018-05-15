
/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-3
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */

var scrollImgs_wander = function (boxId,params){
    this.box = typeof(boxId)=='string'?DSS.util.byId(boxId):boxId;
    this.during = params?params.during*1000:1500;
};
scrollImgs_wander.prototype = {
    constructor: scrollImgs_wander,
    classes : {
        box : 'scroll_wander_box',
        show : 'scroll_wdr_show',
        showIn :'scroll_wdr_show_inner',
        showImgBox : 'scroll_wdr_show_img',
        showTitle : 'scroll_wdr_show_title',
        film :'scroll_wdr_film',
        filmMask : 'scroll_wdr_film_mask',
        filmImgs : 'swiper-wrapper',
        slideImg :'swiper-slide',
        activeImg: 'active-nav',
        sa:'active-nav swiper-slide',
        wander_bigImg_wrapper: 'wander_bigImg_wrapper'
    },
    curIdx:5,
    init : function (){
    	var that = this;
    	this.filmBox = this.getEleByClass(this.box,this.classes.filmImgs);
        var	wander_bigImg_wrapper = this.getEleByClass(this.box.parentNode.nextElementSibling,this.classes.wander_bigImg_wrapper);
         
        // 页面初始化后给第一项加高亮样式
        this.titleBox = this.getEleByClass(this.box,this.classes.showTitle);
        this.showImgBox = this.getEleByClass(this.box,this.classes.showImgBox);
		var firstNav = this.box.getElementsByClassName(that.classes.slideImg)[0];
		DSS.util.aClass(firstNav,'active-nav');
		this.showImgBox.appendChild(firstNav.children[0].cloneNode(true));
		var ch = this.filmBox.children[0];
		DSS.util.setInnerText(this.titleBox,ch.getAttribute('title'));
        this.addEvents();
        this.setTimer();
        
        // 点击左侧的大图  进入二级页面
        var detailView;
		DSS.util.events(this.showImgBox,'click',function(){
			var currentIndex = that.findIndex(that.getEleByClass(that.box,that.classes.sa));
			if(detailView){detailView.destroy(); detailView = null;}
			detailView = new DetailView(wander_bigImg_wrapper,that.filmBox,{
				startSlide:currentIndex,
				speed:that.during,
				loop:true,
				delay:that.during
			});
        });
    },
    getEleByClass : function (p,n){
        return p.getElementsByClassName(n)[0];
    },
    findIndex : function (el){
        var chs = el.parentNode.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i]==el)return i;
        }
    },
    openFilm : function (i){
        var filmBox = this.getEleByClass(this.box,this.classes.filmImgs),
            filmMask = this.getEleByClass(this.box,this.classes.filmMask),
            showImgBox = this.getEleByClass(this.box,this.classes.showImgBox),
            titleBox = this.getEleByClass(this.box,this.classes.showTitle);
        this.curIdx = i = i%filmBox.children.length;
       
        // 旁边的小图滑动时 加样式
        var currActiveNav = this.getEleByClass(this.box,this.classes.activeImg),
		activeNav =  this.getEleByClass(this.box,this.classes.filmImgs).children[this.curIdx];
        DSS.util.dClass(currActiveNav,'active-nav');
        DSS.util.aClass(activeNav,'active-nav');
       
        //加上check
        var ch = filmBox.children[i];
//        for(var j=0;j<filmBox.children.length;j++){
//            DSS.util.dClass(filmBox.children[j],'check');
//        }
//        DSS.util.aClass(ch,'check');

        //显示区换图片
        var img = ch.children[0];
        var img3 = showImgBox.children[0];
        var oldImg = showImgBox.children[1];
        if(oldImg){
            DSS.util.stopMove(oldImg);
            showImgBox.removeChild(oldImg);
        }
        var img2 = img.cloneNode(true);
        this.showImgBox.appendChild(img2);
        with (img2.style){
            position = 'absolute';
            top = 0;
            left = 0;
        }
        DSS.util.setOpacity(img2,0);
        if(img3){
            DSS.util.startMove(img3,{opacity:0},function(){
                showImgBox.removeChild(img3);
            });
        }
        DSS.util.setInnerText(this.titleBox,ch.getAttribute('title'));
        DSS.util.startMove(img2,{opacity:100});

        //film区计算及移动位置
        var iTop = DSS.util.getEle2Ele(filmBox,ch).y,iH = ch.offsetHeight,
            bTop = DSS.util.getEle2Ele(filmMask,ch).y,bH = filmMask.offsetHeight;

        if(bTop+iH>bH || bTop<0){
            var fTop;
            if(bTop+iH>bH){
                fTop = bH - iTop -iH+6; // 超出六个后
            }else if(bTop<0){
                fTop = -iTop; //回到第一个
            }
            DSS.util.startMove(filmBox,{top:fTop});
        }
    },
    setTimer : function(){
        var that = this;
        this.timer = setInterval(function(){
//            that.curIdx ++;
//            that.openFilm(that.curIdx);
        	var _s = getStopSignValue();
        	if(parseInt(_s)==0){
	            var nextSlide = that.findIndex(that.getEleByClass(that.box,that.classes.activeImg))+1;
				if (nextSlide >= that.box.getElementsByClassName(that.classes.slideImg).length){nextSlide = 0;}
				that.openFilm(nextSlide);
        	}
        },that.during);
    },
    clearTimer : function (){
        if(this.timer)clearInterval(this.timer);
    },
    addEvents : function (){
        var filmBox = this.getEleByClass(this.box,this.classes.filmImgs);
        var that = this;
        for(var i=0;i<filmBox.children.length;i++){
            (function(ch){
                // 点击右侧的图片列表 左侧显示相对应的图图片
            	DSS.util.events(ch,'click',function(){
                    var idx = that.findIndex(ch);
                    that.openFilm(idx);
                    that.clearTimer();
                    that.setTimer();
                })
            })(filmBox.children[i]);
        }
    },
    destroy:function (){
        setStopSignValue('0');
        var filmBox = this.getEleByClass(this.box,this.classes.filmImgs);
        var that = this;
        for(var i=0;i<filmBox.children.length;i++){
            (function(ch){
                DSS.util.delEvent(ch,'click')
            })(filmBox.children[i]);
        }
    }
};


