/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-26
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */

var CMDBlockResize = function (id,size1,size2){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.elId  = id;
    this.util = DSS.util;

    this.oldSize = size1;
    this.newSize = size2;
    DSS.Request.updateBlockReq(this.util.byId(this.elId));
};

CMDBlockResize.prototype = {
    constructor : CMDBlockResize,
    undo : function(){
        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
        DSS.BlockOpr.setW(this.oldSize.w);
        DSS.BlockOpr.setH(this.oldSize.h);
        DSS.Request.updateBlockReq(this.util.byId(this.elId));
    },
    redo : function(){
        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
        DSS.BlockOpr.setW(this.newSize.w);
        DSS.BlockOpr.setH(this.newSize.h);
        DSS.Request.updateBlockReq(this.util.byId(this.elId));
    },
    destroy : function (){
        this.mainBox = null;
        this.elId  = null;
        this.util = null;

        this.oldPos = null;
        this.newPos = null;
    }
};