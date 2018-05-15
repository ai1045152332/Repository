/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-18
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
var scrollImgs_accordion = function (boxId,params){
    this.box = (typeof (boxId)=="string")?document.getElementById(boxId):boxId;
    this.during =  params?params.during*1000:1500;
};
scrollImgs_accordion.prototype = {
    constructor:scrollImgs_accordion,
    layerClass : 'scroll_acd_layer',
    titleClass : 'scroll_acd_title',
    imgClass : 'scroll_acd_img',
	layersWrap: 'scroll_acd_ul',
	bigImg_wrapper: 'accordion_bigImg_wrapper',
    imgW : 200,
    imgH : 200,
    titleW : 20,
    curIdx:4,
    init : function (){
        this.initLayers();
        this.addEvents();
        this.setTimer();
    },
    initLayers : function (){
		this.imgW = 300 - this.titleW*(this.box.children.length-1);
		if (this.imgW<=this.titleW)this.imgW=this.titleW;
        this.initLayersPos();
    },
    addLayer : function (path,title){
        var layer = document.createElement('li');
        layer.className = this.layerClass;
        this.box.appendChild(layer);

        if(title){
            var tDiv = document.createElement('div');
            tDiv.className = this.titleClass;
            layer.appendChild(tDiv);
            DSS.util.setInnerText(tDiv,title);
        }

        var img = new Image();
        img.className = this.imgClass;
        img.style.width = this.imgW + 'px';
        img.style.height = this.imgH + 'px';
        layer.appendChild(img);
        img.src = path;
        return layer;
    },
    initLayersPos : function (){
        var chs = this.box.children;
        for(var i=0;i<chs.length;i++){
            chs[i].style.left = this.titleW*i + 'px';
            chs[i].style.top = 0;
            chs[i].style.width = this.titleW + this.imgW + 'px';
            chs[i].style.zIndex = i+1;
            var img = chs[i].getElementsByClassName(this.imgClass)[0];
            img.style.width = this.imgW + 'px';
            img.style.height = this.imgH + 'px';
        }
    },
    openLayer : function (i){
        this.curIdx = i = i%this.box.children.length;
        for(var j=0;j<=i;j++){
            (function (el,n){
                DSS.util.startMove(el,{left:n});
            })(this.box.children[j],j*this.titleW);
        }
        if(i<this.box.children.length-1){
            for(var j=this.curIdx+1;j<this.box.children.length;j++){
                (function (el,n){
                    DSS.util.startMove(el,{left:n});
                })(this.box.children[j],this.box.clientWidth - (this.box.children.length-j)*this.titleW);
            }
        }
    },
    findIndex : function (el){
        var chs = el.parentNode.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i]==el)return i;
        }
    },
    setTimer : function(){
        var that = this;
        this.timer = setInterval(function(){
        	var _s = getStopSignValue();
        	if(parseInt(_s)==0){
        		that.curIdx ++;
                that.openLayer(that.curIdx);
        	}
        },this.during);
    },
    clearTimer : function (){
        if(this.timer)clearInterval(this.timer);
    },
    addEvents : function (){
		var detailView,
			bigImg_wrapperEl = this.box.parentNode.nextElementSibling.getElementsByClassName(this.bigImg_wrapper)[0],
			chs = this.box.getElementsByClassName(this.layerClass),that = this;
        for(var i=0;i<chs.length;i++){
            (function(el){
                DSS.util.events(el,'click',function(e){
                    var idx = that.findIndex(el);
                    that.clearTimer();
                    that.openLayer(idx);
                    that.setTimer();
					
					// 生成二级页面
					if(detailView){detailView.destroy(); detailView = null;}
					detailView = new DetailView(bigImg_wrapperEl,that.box,{
						startSlide:idx,
						speed:that.during,
						loop:true,
						delay:that.during
					});
                })
            })(chs[i]);
        }
    },
    destroy:function (){
        var chs = this.box.getElementsByClassName(this.layerClass),that = this;
        for(var i=0;i<chs.length;i++){
            (function(el){
                DSS.util.delEvent(el,'click')
            })(chs[i]);
        }
    }
};