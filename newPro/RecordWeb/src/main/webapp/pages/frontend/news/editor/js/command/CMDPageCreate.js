/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-23
 * Time: 下午4:43
 * To change this template use File | Settings | File Templates.
 */

var CMDPageCreate= function (){
    var that =this;
    this.idx = DSS.Options.el.pageBoxIn.children.length>0?(DSS.PageOpr.getCurIdx() + 1):1;
    DSS.Request.addPageReq(this.idx,function(id){
        var o = {pid:id,path:''};
        DSS.PageOpr.addPage(o,that.idx);
        that.elId = id;
        DSS.PageOpr.setCurId(that.elId);
        DSS.BlockOpr.clearAllBlock();
        DSS.PageOpr.initPageNum();
        DSS.Controller.snapPage();
    })

};
CMDPageCreate.prototype = {
    constructor : CMDPageCreate,
    undo :function (){
        DSS.PageOpr.delPage(this.elId);
        DSS.Request.delPageReq(this.elId,function (){
            DSS.Request.selectPageReq(DSS.PageOpr.getCurId());
            DSS.PageOpr.initPageNum();
        });
    },
    redo : function (){
        var that = this;
        DSS.Request.addPageReq(this.idx,function(id){
            var o = {pid:id,path:''};
            DSS.PageOpr.addPage(o,that.idx);
            that.elId = id;
            DSS.PageOpr.setCurId(that.elId);
            DSS.BlockOpr.clearAllBlock();
            DSS.PageOpr.initPageNum();
        })
    },
    destroy : function (){
        this.elId = null;
        this.idx = null;
    }
};
