/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-7
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
var CMDBlockLevelMoveDown = function (){
    this.elId  = DSS.BlockOpr.getCurObj().id;
    this.oldLevelList = DSS.Level.getAllLevel();
    DSS.Level.moveDown(this.elId);
    this.newLevelList = DSS.Level.getAllLevel();
    DSS.Request.levelBlockReq(this.newLevelList);
};
CMDBlockLevelMoveDown.prototype = {
    constructor : CMDBlockLevelMoveDown,
    undo : function(){
        DSS.Level.setAllLevel(this.oldLevelList);
        DSS.Request.levelBlockReq(this.oldLevelList);
    },
    redo : function(){
        DSS.Level.setAllLevel(this.newLevelList);
        DSS.Request.levelBlockReq(this.newLevelList);
    },
    destroy : function (){
        this.elId  = null;
        this.oldLevelList = null;
        this.newLevelList = null;
    }
};

