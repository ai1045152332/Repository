/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-26
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */


var CMDBlockDel = function (){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.elIds  = DSS.BlockOpr.countSelectIds();
    this.util = DSS.util;

    DSS.BlockOpr.moveElsToDel(this.elIds);
    DSS.Request.delBlockReq(this.elIds);
};
CMDBlockDel.prototype = {
    constructor : CMDBlockDel,
    undo : function (){
        DSS.BlockOpr.moveElsToEdit(this.elIds);
        DSS.Request.addBlockReq(this.elIds);
    },
    redo : function (){
        DSS.BlockOpr.moveElsToDel(this.elIds);
        DSS.Request.delBlockReq(this.elIds);
    },
    destroy : function (){
        this.mainBox = null;
        this.elIds  = null;
        this.util = null;
    }
};

