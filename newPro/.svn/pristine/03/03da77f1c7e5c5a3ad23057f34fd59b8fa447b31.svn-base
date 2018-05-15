/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-25
 * Time: 下午3:28
 * To change this template use File | Settings | File Templates.
 */

var CMDPageMove = function (id,oldIdx,newIdx){
    this.elId = id;
    this.oldIdx = oldIdx;
    this.newIdx = newIdx;
    DSS.Request.sortPageReq(this.elId,this.newIdx);
    DSS.PageOpr.renewAllPageIdx();
};
CMDPageMove.prototype = {
    constructor : CMDPageMove,
    undo : function (){
        DSS.PageOpr.insertPageByIdx(this.elId,this.oldIdx);
        DSS.Request.sortPageReq(this.elId,this.oldIdx);
    },
    redo : function (){
        DSS.PageOpr.insertPageByIdx(this.elId,this.newIdx);
        DSS.Request.sortPageReq(this.elId,this.newIdx);
    },
    destroy : function (){
        this.elId = null;
        this.oldIdx = null;
        this.newIdx = null;
    }
};
