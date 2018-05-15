/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-12
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
var DSS = DSS || {};
DSS.Observer = {
    list : {},
    reg : function (name,fn){
        if(!name)return;
        var list = this.list;
        if(!list[name])list[name] = [];
        list[name].push(fn);
    },
    exc : function (name){
        if(!name || !this.list[name])return;
        var zu = this.list[name];
        for(var i in zu){
            zu[i]();
        }
    }
};
