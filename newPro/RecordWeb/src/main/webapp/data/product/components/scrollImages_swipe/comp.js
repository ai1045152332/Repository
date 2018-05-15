/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-18
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */

/* Scroll Image by Swipe */
var scrollImgs_swipe = function (boxId){
    this.box = (typeof (boxId)=="string")?document.getElementById(boxId):boxId;
};
scrollImgs_swipe.prototype = {
    constructor: scrollImgs_swipe,
    init : function (){
        var params = this.box.getAttribute("params");
        var options = eval("("+params+")");
        this.swipe = new Swipe(this.box, options);
		
		var detailView,
			imgWrapEl = this.box.getElementsByClassName('scrollImgs_swipe-wrap')[0],
			bigImg_wrapperEl = this.box.parentNode.nextElementSibling.getElementsByClassName('swipe_bigImg_wrapper')[0],
			chs = imgWrapEl.getElementsByTagName('div'),that = this;
        for(var i=0;i<chs.length;i++){
            (function(el){
                DSS.util.events(el,'click',function(e){
                    var idx = that.findIndex(el);
					// 生成二级页面
					if(detailView){detailView.destroy(); detailView = null;}
					detailView = new DetailView(bigImg_wrapperEl,imgWrapEl,{
						startSlide:idx,
						speed:options.auto,
						loop:true,
						delay:options.auto
					});
                })
            })(chs[i]);
        }
    },
    findIndex : function (el){
        var chs = el.parentNode.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i]==el)return i;
        }
    },
    destroy : function () {
        this.swipe.kill();
    }
};