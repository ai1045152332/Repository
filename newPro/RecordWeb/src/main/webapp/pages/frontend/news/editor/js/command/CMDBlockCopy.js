/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-3
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */

var CMDBlockCopy = function (){
    this.mainBox = DSS.Options.el.mainBoxIn;
    this.util = DSS.util;

    this.elIds = DSS.BlockOpr.copyEles();
};
CMDBlockCopy.prototype = {
    constructor : CMDBlockCopy,
    undo : function (){
        //DSS.BlockOpr.moveElsToEdit(this.elIds);
        if(this.elIds){
            DSS.BlockOpr.setCurObj(this.util.byId(this.elIds[0]));
        }
    },
    redo : function (){
        //DSS.BlockOpr.moveElsToDel(this.elIds);
    },
    destroy : function (){
        this.mainBox = null;
        this.elIds  = null;
        this.util = null;
    }
};



