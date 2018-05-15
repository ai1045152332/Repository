/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-26
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */


var CMDBlockUnlock = function (){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.elIds  = DSS.BlockOpr.countSelectIds();
    this.util = DSS.util;
    var item = this.util.byId(this.elIds[0]);
    item.style.opacity = 1;  // 部件处于解锁状态
    DSS.Request.updateBlockReq(this.util.byId(this.elIds[0]));
};
CMDBlockUnlock.prototype = {
    constructor : CMDBlockUnlock,
//    undo : function (){
//        DSS.BlockOpr.moveElsToEdit(this.elIds);
//        DSS.Request.addBlockReq(this.elIds);
//    },
//    redo : function (){
//        DSS.BlockOpr.moveElsToDel(this.elIds);
//        DSS.Request.delBlockReq(this.elIds);
//    },
    destroy : function (){
        this.mainBox = null;
        this.elIds  = null;
        this.util = null;
    }
};

