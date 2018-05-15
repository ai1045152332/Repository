/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-26
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */


var CMDBlockLock = function (){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.elIds  = DSS.BlockOpr.countSelectIds();
    this.util = DSS.util;
    var item = this.util.byId(this.elIds[0]);
    item.style.opacity = 0.5; // 部件处于阴影状态  表示已锁定
    DSS.Request.updateBlockReq(this.util.byId(this.elIds[0]));
};
CMDBlockLock.prototype = {
    constructor : CMDBlockLock,
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

