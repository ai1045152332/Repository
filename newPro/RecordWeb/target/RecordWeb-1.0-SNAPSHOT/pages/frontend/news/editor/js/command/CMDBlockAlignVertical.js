/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-10
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
var CMDBlockAlignVertical = function (){
    this.oldPosList = DSS.Align.getAllPos();
    DSS.Align.vertical();
    DSS.BlockOpr.maskMove2Obj();
    this.newPosList = DSS.Align.getAllPos();
    DSS.Request.alignBlockReq(this.newPosList);
};
CMDBlockAlignVertical.prototype = {
    constructor : CMDBlockAlignVertical,
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

