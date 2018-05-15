/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-24
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */

var CMDBlockAddPinao = function (id,x,y){
	id= '4';/*与request.js中data数组中的id的属性值*/
	x=0;
	y=0;
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.delBox = DSS.Options.el.delElsDiv;
    this.util = DSS.util;

    var that = this;
    var item = DSS.BlockOpr.addBlock(id);
    DSS.BlockOpr.setCurObj(item);
    DSS.BlockOpr.setPos(x,y);
    if(DSS.BlockOpr.prptBar)DSS.BlockOpr.prptBar.onBlockDrag(x,y);
    that.elId = item.id;

    DSS.Request.addBlockReq([item.id]);
};
CMDBlockAddPinao.prototype = {
    constructor : CMDBlockAddPinao,
    undo : function (){
        DSS.BlockOpr.moveToDel(this.elId);
        DSS.Request.delBlockReq([this.elId]);
    },
    redo : function (){
        DSS.BlockOpr.moveToEdit(this.elId);
        DSS.Request.addBlockReq([this.elId]);
    },
    destroy : function (){
        this.typeId = null;
        this.mainBox = null;
        this.delBox = null;
        var item = this.util.byId(this.elId);
        DSS.EditComponent.destroy(item);
        this.util = null;
        this.elId = null;
    }
};


