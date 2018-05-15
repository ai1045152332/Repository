/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-23
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
var CMDPageDelete= function (){
	if(confirm("页面删除后，不能进行撤销操作，是否确认删除？")){
		this.elId = DSS.PageOpr.getCurId();
	    this.idx = DSS.PageOpr.getCurIdx();
	    this.delPgId = DSS.PageOpr.delPage(this.elId);
	    DSS.Request.delPageReq(this.elId,function (){
	        DSS.Request.selectPageReq(DSS.PageOpr.getCurId());
	    });
	    if(DSS.Options.el.pageBoxIn.children.length == 0){
	    	// 节目制作  当删除的页面是最后一个的时候，自动新建一个页面
	    	var that =this;
	 	    DSS.Request.addPageReq(1,function(id){
	 	        var o = {pid:id,path:''};
	 	        DSS.PageOpr.addPage(o,that.idx);
	 	        that.elId = id;
	 	        DSS.PageOpr.setCurId(that.elId);
	 	        DSS.BlockOpr.clearAllBlock();
	 	        DSS.PageOpr.initPageNum();
	 	        DSS.Controller.snapPage();
	 	    });
	 	    // 当删除最后一个页面时 ， 新建的页面自动选中
	 	    DSS.Options.el.pageBoxIn.children[0].className = 'check item';
	    }
	}else{
		return false;
	}
};
CMDPageDelete.prototype = {
    constructor : CMDPageDelete,
    undo :function (){
        var that = this;
        DSS.Request.addPageReq(this.idx,function(id){
            DSS.PageOpr.addPage(id,that.idx);
            that.elId = id;
            DSS.PageOpr.setCurId(that.elId);
            DSS.BlockOpr.clearAllBlock();
            var ids =  DSS.BlockOpr.getAllIds(that.delPgId);
            if(ids&&ids.length>0){
                DSS.Request.addBlockReq(DSS.BlockOpr.getAllIds(that.delPgId));
            }
            var delPg = DSS.util.byId(that.delPgId);
            DSS.util.moveAllChildren(delPg,DSS.Options.el.mainBoxIn);
            delPg.parentNode.removeChild(delPg);
        })
    },
    redo : function (){
        this.delPgId = DSS.PageOpr.delPage(this.elId);
        DSS.Request.delPageReq(this.elId,function (){
            DSS.Request.selectPageReq(DSS.PageOpr.getCurId());
        });
    },
    destroy : function (){
        this.elId = null;
        this.idx = null;
    }
};

