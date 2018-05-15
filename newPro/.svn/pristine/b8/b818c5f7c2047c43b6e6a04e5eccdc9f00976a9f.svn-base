/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-25
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */

var CMDPageSelect = function (oldId,newId){
    this.oldId = oldId;
    this.newId = newId;

    var that = this;
    // 如果在快速发布页面  则根目录是publish下           如果是普通节目编辑页面  则根目录是edit
    if(DSS.util.byId('quick').value=='1'){
    	DSS.Request.selectPageReqQui(this.newId) 
    }else{
    	DSS.Request.selectPageReq(this.newId)
    }
    
};
CMDPageSelect.prototype = {
    undo : function (){
        var that = this;
        DSS.Request.selectPageReq(this.oldId)
    },
    redo : function (){
        var that = this;
        DSS.Request.selectPageReq(this.newId)
    },
    destroy : function (){
        this.oldId = null;
        this.newId = null;
    }
};
