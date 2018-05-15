/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-5-15
 * Time: 上午9:20
 * To change this template use File | Settings | File Templates.
 */

var CMDPageCopy = function (){
    this.copyId = DSS.Options.cur.page;
    var chs = DSS.Options.el.mainBoxIn.children;
    if(chs.length>0){
        this.elIds = DSS.MakeId.mkBlockIds(chs.length);
        for(var i=0;i<chs.length;i++){
            chs[i].id = this.elIds[i];
        }
    }else{
        this.elIds = [];
    }
    this.idx = DSS.PageOpr.getCurIdx() + 1;
    var that = this;
    DSS.Request.copyPageReq(this.idx,this.elIds,function(obj){
        var itemOld = DSS.PageOpr.getItemByIdx(that.idx-2);
        var tmOld = itemOld.getElementsByClassName(DSS.Options.eClass.pageTimeEdit)[0].value;
        var o = {pid:obj.id,path:obj.path,time:tmOld};
        DSS.PageOpr.addPage(o,that.idx);
        that.elId = obj.id;
        DSS.PageOpr.setCurId(that.elId);
        DSS.PageOpr.initPageNum();
        DSS.Request.updatePageTime(that.elId,tmOld);
        DSS.Controller.snapPage();
    })
};
CMDPageCopy.prototype = {
    constructor : CMDPageCopy,
    undo :function (){
    },
    redo : function (){
    },
    destroy : function (){
        this.elId = null;
        this.idx = null;
        this.elIds = null;
        this.copyId = null;
    }
};