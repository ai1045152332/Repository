/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-7
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
var CMDBlockAlignLeft = function (){
    this.oldPosList = DSS.Align.getAllPos();
    DSS.Align.left();
    DSS.BlockOpr.maskMove2Obj();
    this.newPosList = DSS.Align.getAllPos();
    DSS.Request.alignBlockReq(this.newPosList);
};
CMDBlockAlignLeft.prototype = {
    constructor : CMDBlockAlignLeft,
    undo :function (){
        DSS.Align.setAllPos(this.oldPosList);
        DSS.BlockOpr.maskMove2Obj();
        DSS.Request.alignBlockReq(this.oldPosList);
    },
    redo : function (){
        DSS.Align.setAllPos(this.newPosList);
        DSS.BlockOpr.maskMove2Obj();
        DSS.Request.alignBlockReq(this.newPosList);
    },
    destroy : function (){
        this.oldPosList = null;
        this.newPosList = null;
    }
}