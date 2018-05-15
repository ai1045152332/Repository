/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-24
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */

var CMDProgramSize = function (o){
    this.oldSize = {w:DSS.Options.cur.w,h:DSS.Options.cur.h};
    this.newSize = o;
    DSS.Frame.setEditBoxSize(o);
    DSS.Request.updateProgramRatio(function(){
        DSS.Frame.initRatio(o.w,o.h,function(){
            DSS.Request.selectPageReq(DSS.Options.cur.page);
        });
    });
};
CMDProgramSize.prototype = {
    constructor : CMDProgramSize,
    undo : function (){
        DSS.Frame.setEditBoxSize(this.oldSize);
        DSS.Request.updateProgramRatio();
        DSS.Frame.initRatio(this.oldSize.w,this.oldSize.h);
    },
    redo : function (){
        DSS.Frame.setEditBoxSize(this.newSize);
        DSS.Request.updateProgramRatio();
        DSS.Frame.initRatio(this.newSize.w,this.newSize.h);
    },
    destroy : function (){
        this.oldSize = null;
        this.newSize = null;
    }
};
