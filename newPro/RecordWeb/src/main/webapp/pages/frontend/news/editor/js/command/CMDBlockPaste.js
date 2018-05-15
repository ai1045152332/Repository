/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-3
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.
 */

var CMDBlockPaste = function (){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.util = DSS.util;

    this.elIds = DSS.BlockOpr.pasteEles();
    if(this.elIds){
        DSS.BlockOpr.setCurObj(this.util.byId(this.elIds[0]));
    }
    DSS.Request.addBlockReq(this.elIds);
};
CMDBlockPaste.prototype = {
    constructor : CMDBlockPaste,
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

