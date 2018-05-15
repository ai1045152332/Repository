/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-24
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
var DSS = DSS || {};
DSS.Commond = {
    undoList : [],
    redoList : [],
    add : function (obj){
        this.undoList.push(obj);
    },
    undo : function (){
        if(this.undoList.length==0)return;
        var li = this.undoList;
        var o =li[li.length-1];
        o.undo();
        li.length = li.length-1;
        this.redoList.push(o);
        DSS.BlockOpr.refreshPrptBar();
        this.clearUndo();
    },
    redo : function (){
        if(this.redoList.length==0)return;
        var li = this.redoList;
        var o =li[li.length-1];
        o.redo();
        li.length = li.length-1;
        this.undoList.push(o);
        DSS.BlockOpr.refreshPrptBar();
        this.clearRedo();
    },
    // update by lihuimin 20150122 begin
    // 不需要进行undo redo的操作   走此方法
    execute : function (cmd){
//        this.clearRedo();
        var obj = new cmd(arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7],arguments[8]);
//        if(obj)this.undoList.push(obj);
    },
    executeImVe : function (cmd){
//      this.clearRedo();
      var obj = new cmd(arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7],arguments[8],arguments[9],arguments[10]);
//      if(obj)this.undoList.push(obj);
  },
    // 需要进行undo redo的操作   走此方法
    executeUnRe : function (cmd){
        this.clearRedo();
        //log.console(cmd);
        var obj = new cmd(arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7],arguments[8]);
        if(obj)this.undoList.push(obj);
    },
    // update by lihuimin 20150122 end 
    clearUndo : function (){
        this.undoList.length = 0;
    },
    clearRedo : function (){
        var li = this.redoList;
        for(var i in li){
            li[i].destroy();
        }
        this.redoList.length = 0;
    }
};
