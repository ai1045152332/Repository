/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-26
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */

var CMDBlockMove = function (id,pos1,pos2){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.elId  = id;
    this.util = DSS.util;

    this.oldPos = pos1;
    this.newPos = pos2;
    DSS.Request.updateBlockReq(this.util.byId(this.elId));
};

CMDBlockMove.prototype = {
    constructor : CMDBlockMove,
    undo : function(){
        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
        DSS.BlockOpr.setLeft(this.oldPos.x);
        DSS.BlockOpr.setTop(this.oldPos.y);
        DSS.Request.updateBlockReq(this.util.byId(this.elId));
    },
    redo : function(){
        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
        DSS.BlockOpr.setLeft(this.newPos.x);
        DSS.BlockOpr.setTop(this.newPos.y);
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