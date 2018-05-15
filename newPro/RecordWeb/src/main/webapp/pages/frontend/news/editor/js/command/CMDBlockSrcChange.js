/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-6-5
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
var CMDBlockSrcChange = function (id,prptName,oldVal,newVal,w,h){
    this.oldSize = {w:DSS.BlockOpr.getW(),h:DSS.BlockOpr.getH()};
    this.newSize = {};
    if(this.oldSize.w/this.oldSize.h>w/h){
        this.newSize.h = this.oldSize.h;
        this.newSize.w = w*this.oldSize.h/h;
    }else{
        this.newSize.w = this.oldSize.w;
        this.newSize.h = h*this.oldSize.w/w;
    }

    this.elId = id;
    this.util = DSS.util;
         console.log(oldVal+'==='+newVal)
    this.prptName = prptName;
    this.oldVal = oldVal;
    this.newVal = newVal;

    this.updatePrpt(this.newVal,this.newSize)

}
CMDBlockSrcChange.prototype = {
    constructor : CMDBlockSrcChange,
    undo : function (){
        this.updatePrpt(this.oldVal,this.oldSize)
    },
    redo : function (){
        this.updatePrpt(this.newVal,this.newSize)
    },
    updatePrpt : function (val,size){
    	var typeObj = this.util.byId(this.elId);
    	//  视频  图片  flash 添加的时候  如果文件过大   会存在暂不显示的问题
        if(typeObj.type_id == '2' || typeObj.type_id == '1' || typeObj.type_id == '11'){
            // 胖子加一个div遮罩
        	var elb = document.getElementsByTagName('body')[0];

    	    var el0 = document.createElement("div");
    	    el0.className="loading0";
    	    el0.style.cssText = "height:100%;width:100%;position:absolute;left:0;top:0;display:block;z-index:998;background-color:#333;opacity:0.5;";
    	    var el = document.createElement("div");
    	    el.className="loading1";
    	    el.style.cssText = "height:100px;width:100px;position:absolute;left:50%;top:40%;display:block;z-index:999;margin-left:-50px;margin-top:-50px;";
    		el.style.backgroundImage="url(images/load.gif)"; 
    		elb.appendChild(el);
    		elb.appendChild(el0);
        }
    	
        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));

        DSS.BlockOpr.setProperty(this.prptName,val);
        if(DSS.PropertyBar)DSS.PropertyBar.setVal(this.prptName,val);

        DSS.BlockOpr.setSize(size.w,size.h);
        if(DSS.PropertyBar)DSS.PropertyBar.setSize(size.w,size.h);

        DSS.Request.updateBlockReq(this.util.byId(this.elId));
    },
    destroy : function (){
        this.oldSize = null;
        this.newSize = null;
        this.util = null;
        this.elId = null;
        this.prptName = null;
        this.oldVal = null;
        this.newVal = null;
    }
}
