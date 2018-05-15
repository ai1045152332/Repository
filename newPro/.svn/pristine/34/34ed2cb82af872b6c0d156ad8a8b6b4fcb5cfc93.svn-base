
var CMDBlockAlignBottom = function (){
    this.oldPosList = DSS.Align.getAllPos();
    DSS.Align.bottom();
    DSS.BlockOpr.maskMove2Obj();
    this.newPosList = DSS.Align.getAllPos();
    DSS.Request.alignBlockReq(this.newPosList);
};
CMDBlockAlignBottom.prototype = {
    constructor : CMDBlockAlignBottom,
    undo :function (){
        DSS.Align.setAllPos(this.oldPosList);
        DSS.BlockOpr.maskMove2Obj();
    },
    redo : function (){
        DSS.Align.setAllPos(this.newPosList);
        DSS.BlockOpr.maskMove2Obj();
    },
    destroy : function (){
        this.oldPosList = null;
        this.newPosList = null;
    }
};
