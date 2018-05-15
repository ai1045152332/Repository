/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-3
 * Time: 上午9:31
 * To change this template use File | Settings | File Templates.
 */

var CMDBlockCut = function (){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.util = DSS.util;

    this.elIds = DSS.BlockOpr.cutEles();
    DSS.Request.delBlockReq(this.elIds);
};
CMDBlockCut.prototype = {
    constructor : CMDBlockCut,
    undo : function (){
        DSS.BlockOpr.moveElsToEdit(this.elIds);
        if(this.elIds){
            DSS.BlockOpr.setCurObj(this.util.byId(this.elIds[0]));
        }
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


