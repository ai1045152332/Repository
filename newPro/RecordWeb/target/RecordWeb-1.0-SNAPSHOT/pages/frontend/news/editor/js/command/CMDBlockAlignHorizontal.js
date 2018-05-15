/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-10
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-10
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
var CMDBlockAlignHorizontal = function (){
    this.oldPosList = DSS.Align.getAllPos();
    DSS.Align.horizontal();
    DSS.BlockOpr.maskMove2Obj();
    this.newPosList = DSS.Align.getAllPos();
    DSS.Request.alignBlockReq(this.newPosList);
};
CMDBlockAlignHorizontal.prototype = {
    constructor : CMDBlockAlignHorizontal,
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

