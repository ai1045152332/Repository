/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-12
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */


var DSS = DSS ||{};
DSS.LeftMenuAcr = {
    getMenuGroup : function (i){
        return this.data[i];
    },
    getGroupId : function (i){
        return this.getMenuGroup(i).id;
    },
    getGroupName : function (i){
        return this.getMenuGroup(i).name;
    },
    getGroupData : function (i){
        return this.getMenuGroup(i).data;
    },
    getItemId : function(i,j){
        return this.getGroupData(i)[j].id;
    },
    getItemName : function(i,j){
        return this.getGroupData(i)[j].name;
    },
    getGroupNum : function (){
        return this.data.length;
    },
    getGroupDataNum : function (i){
        return this.getGroupData(i).length;
    },
    setData : function (data){
        this.data = data;
    },
    getData : function (fn){
        var that = this;
        DSS.Request.getLMenuList(function(data){
            //that.data = data;
            /*var list = [];
            for(var o in data){
                data[o].id=o;
                list.push(data[o]);
                DSS.Options.menuItem[o].innerHTML=data[o].innerHTML;
            }
            that.data = [{id:'ID_LEFT_MENU_1',name:'菜单项分组1',data:list}];
            if(fn)fn(that);*/
            that.data = data;
            for(var i=0;i<data.length;i++){
                var dt = data[i];
                for(var j=0;j<dt.data.length;j++){
                    var obj = dt.data[j];
                    if(i>=6)continue;
                    DSS.Options.menuItem[obj.id].innerHTML = obj.innerHTML;
                }
            }
            if(fn)fn(that);
        })
    }
};
