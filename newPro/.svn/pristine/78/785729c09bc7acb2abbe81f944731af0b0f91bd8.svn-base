/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-5-12
 * Time: 上午10:01
 * To change this template use File | Settings | File Templates.
 */

var CMDAddRsrc = function (list){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.util = DSS.util;

    this.elIds = [];
    var w = this.mainBox.offsetWidth,h = this.mainBox.offsetHeight;
    for(var i=0;i<list.length;i++){
        var obj = list[i];
        var typeObj = this.typeMap[obj.type];
        var item = DSS.EditComponent.addBlock(typeObj.type);
        //  add by lihuimin 20140930 begin   解决在控件栏 选择图片 图片清晰度不够的问题
        if(typeObj.type == '1'){ // ‘1’表示的是图片
        	 var o = {path:obj.path,mapped:obj.respath};
        }
        else{
        	 var o = {path:obj.path,mapped:obj.mapped};
        }
        // 上方的 ：视频  图片  flash 添加的时候  如果文件过大   会存在暂不显示的问题
        if(typeObj.type == '2' || typeObj.type == '1' || typeObj.type == '11'){
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
        DSS.EditComponent.setProperty(item,typeObj.name+'_path',o);
        if (obj.type == '1' || obj.type == '4') // video & img
        {
            var oldSize = {w:DSS.EditComponent.getProperty(item,'width'),h:DSS.EditComponent.getProperty(item,'height')};
            var newSize = {};
            if(oldSize.w/oldSize.h>obj.w/obj.h){
                newSize.h = oldSize.h;
                newSize.w = obj.w*oldSize.h/obj.h;
            }else{
                newSize.w = oldSize.w;
                newSize.h = obj.h*oldSize.w/obj.w;
            }
            DSS.EditComponent.setItemW(item, newSize.w);
            DSS.EditComponent.setItemH(item, newSize.h);
        }
        /*item.style.left = (w - o.w)/2 + 'px';
        item.style.top = (h - o.h)/2 + 'px';
        DSS.EditComponent.setItemW(item, o.w);
        DSS.EditComponent.setItemH(item, o.h);*/
        this.elIds.push(item.id);
    }
    if(this.elIds){
        DSS.BlockOpr.setCurObj(this.util.byId(this.elIds[this.elIds.length-1]));
    }
    DSS.Request.addBlockReq(this.elIds);
    item = null;
};
CMDAddRsrc.prototype = {
    constructor : CMDAddRsrc,
    typeMap : {
		'0':{type:'',name:'flodder'},
        '1':{type:'2',name:'video'},
        '2':{type:'',name:'word'},
        '3':{type:'',name:'excel'},
        '4':{type:'1',name:'img'},
        '5':{type:'',name:'pdf'},
        '6':{type:'12',name:'ppt'},
        '7':{type:'11',name:'flash'}
    },
    undo : function (){
        DSS.BlockOpr.moveElsToDel(this.elIds);
        DSS.Request.delBlockReq(this.elIds);
    },
    redo : function (){
        DSS.BlockOpr.moveElsToEdit(this.elIds);
        if(this.elIds){
            DSS.BlockOpr.setCurObj(this.util.byId(this.elIds[0]));
        }
        DSS.Request.addBlockReq(this.elIds);
    },
    destroy : function (){
        this.mainBox = null;
        this.elIds  = null;
        this.util = null;
    }
};
