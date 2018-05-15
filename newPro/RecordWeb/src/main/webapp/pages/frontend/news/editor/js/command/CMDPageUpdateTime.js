/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-5-20
 * Time: 下午4:30
 * To change this template use File | Settings | File Templates.
 */

var CMDPageUpdateTime = function (pid,time1,time2){
    this.pid = pid;
    this.oldVal = time1;
    this.newVal = time2;
    this.tmSetClass = DSS.Options.eClass.pageTimeSet;
    this.tmEditClass = DSS.Options.eClass.pageTimeEdit;

    this.setPageTime(this.newVal);
}
CMDPageUpdateTime.prototype = {
    undo : function (){
        this.setPageTime(this.oldVal);
    },
    redo : function (){
        this.setPageTime(this.newVal);
    },
    setPageTime : function (tm){
        var item = DSS.PageOpr.findPageById(this.pid);
//        var tmSet = item.getElementsByClassName(this.tmSetClass)[0];
//        DSS.util.setInnerText(tmSet,tm);
        var tmEdit = item.getElementsByClassName(this.tmEditClass)[0];
        tmEdit.value = tm;
        DSS.Request.updatePageTime(this.pid,tm);
    },
    destroy : function (){
        this.pid = null;
        this.oldVal = null;
        this.newVal = null;
        this.tmSetClass = null;
        this.tmEditClass = null;
    }
}
