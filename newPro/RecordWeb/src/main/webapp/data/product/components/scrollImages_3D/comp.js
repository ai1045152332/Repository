
/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-7
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
var scrollImgs_3D = function (boxId,params){
    this.navClass = 'scroll_3D_nav';
    this.nextClass = 'scroll_3D_next';
    this.sliderClass = 'scroll_3D_slider';
    this.active3DImg = 'cur'; // update 20141224 3D轮播图点击大图显示不正确的问题的解决 
    this.box = this.$(boxId);
    var wp = this.$$$(this.sliderClass,this.box), ul = this.$$('ul', wp)[0], li = this.li = this.$$('li', ul);
    this.l = li.length;
    this.w = wp.offsetWidth;
    this.h = wp.offsetHeight;
    var con = this.con = this.ce('div', wp);
    con.style.cssText = 'position:absolute;left:0;top:0;width:'+this.w+'px;height:'+this.h+'px';
    ul.style['display'] = 'none';
    this.a1 = this.ce('a', con);
    this.a1.style.cssText = 'position:absolute;left:0;top:0;overflow:hidden';
    this.a2 = this.ce('a', con);
    this.a2.style.cssText = 'position:absolute;top:0;right:0;overflow:hidden';
    this.a1.innerHTML = this.a2.innerHTML = '<img alt="" />';
    this.img = this.$$('img', ul);
    this.s =  5;
    this.during = params?params.during*1000:3000;
    this.mask11 = this.ce('span', this.a1);
    this.mask12 = this.ce('span', this.a1);
    this.mask21 = this.ce('span', this.a2);
    this.mask22 = this.ce('span', this.a2);
    this.pos(0);
};
scrollImgs_3D.prototype = {
    constructor:scrollImgs_3D,
    pos : function (i) {
        clearInterval(this.li[i].a); clearInterval(this.au); this.au = 0; this.cur = i;
        var navli = this.$$('li', this.$$$(this.navClass,this.box));
        for (var j=0; j<navli.length; j++) {
            navli[j].className = i == j ? 'cur' : '';
        }
        var img1 = this.$$('img', this.a1)[0], img2 = this.$$('img', this.a2)[0], _this = this;
        img1.src = i==0 ? this.img[this.l-1].src : this.img[i-1].src;
        img1.width = this.w;
        img2.src = this.img[i].src;
        img2.width = 0;
        img1.height = img2.height = this.h;
        this.li[i].a = setInterval(function(){_this.anim(i)}, 20);

    },
    anim : function (i) {
        var w1 = this.$$('img', this.a1)[0].width, w2  = this.$$('img', this.a2)[0].width;
        if (w2 == this.w) {
            clearInterval(this.li[i].a);
            this.$$('img', this.a1)[0].width = 0;
            this.$$('img', this.a2)[0].width = this.w;
        }else {
            this.$$('img', this.a1)[0].width -= Math.ceil((this.w-w2)*.13);
            this.$$('img', this.a2)[0].width += Math.ceil((this.w-w2)*.13);
            if (!this.au) this.auto();
        }
    },
    auto : function () {
        var _this = this;
        this.au = setInterval(function(){var _s = getStopSignValue();if(parseInt(_s)==0)_this.move()}, this.during);
    },
    move : function () {
        var n = this.cur==this.l-1 ? 0 : this.cur+1;
        this.pos(n);
    },
    findIndex : function (el){
        var chs = el.parentNode.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i]==el)return i;
        }
    },
    addEvents : function (){
        var navli = this.$$('li', this.$$$(this.navClass,this.box));
        var that = this;
        for (var j=0; j<navli.length; j++) {
            DSS.util.events(navli[j],'click',function(e,ele){
                var idx = that.findIndex(ele);
                that.pos(idx);
            })
        }

        var nxtBtn = this.$$$(this.nextClass,this.box);
        DSS.util.events(nxtBtn,'click',function(){
            that.move();
        });
		
		// 点击图片生成二级页面（大图展示）
		var detailView,
			imgWrapEl = this.$$$('scroll_3D_slider',this.box),
			bigImg_wrapperEl = this.$$$('scroll_3D_bigImg_wrapper',this.box.parentNode.nextElementSibling),
			chs = this.$$('a',imgWrapEl),that = this;
        for(var i=0;i<chs.length;i++){
            (function(el){
                DSS.util.events(el,'click',function(e){
//                  var idx = that.findIndex(el);
                	// update 20141224 3D轮播图点击大图显示不正确的问题的解决 
                    var idx = that.findIndex(that.getEleByClass(that.box,that.active3DImg));
					// 生成二级页面
					if(detailView){detailView.destroy(); detailView = null;}
					detailView = new DetailView(bigImg_wrapperEl,imgWrapEl.children[0],{
						startSlide:idx,
						speed:that.during,
						loop:true,
						delay:that.during
					});
                })
            })(chs[i]);
        }
    },
    $ : function(i) {return typeof (i)=='string'?document.getElementById(i):i;},
    $$ : function(c, p) {return p.getElementsByTagName(c)},
    $$$ : function (n,p){return p.getElementsByClassName(n)[0]},
    ce : function(i, t) {
        var o = document.createElement(i);
        t.appendChild(o);
        return o;
    },
    // update 20141224 3D轮播图点击大图显示不正确的问题的解决 
    getEleByClass : function (p,n){
        return p.getElementsByClassName(n)[0];
    },
    init : function (){this.addEvents();},
    destroy:function (){
        var navli = this.$$('li', this.$$$(this.navClass,this.box));
        var that = this;
        for (var j=0; j<navli.length; j++) {
            DSS.util.delEvent(navli[j],'click');
        }

        var nxtBtn = this.$$$(this.nextClass,this.box);
        DSS.util.delEvent(nxtBtn,'click');
    }
};