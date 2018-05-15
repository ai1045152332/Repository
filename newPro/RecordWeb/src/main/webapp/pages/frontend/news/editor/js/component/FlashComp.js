/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */

var  FlashComp = {
    dftVals : {mapped:"",path:""},
    srcClass: 'FLASH_COMP_src'
};
DSS.util.extend(FlashComp,CompChClass);
DSS.util.extend(FlashComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
        if (o.mapped) srcObj.src = o.mapped;
        srcObj.ondragstart = function (){return false};
        DSS.util.events(item,'selectstart',function(e){
            if(e.stopPropagation)e.stopPropagation();
        	if(e.preventDefault)e.preventDefault();// add 20140813
        });
        return item;
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
        switch (name){
            case 'flash_path':
                srcObj.src = value.mapped;
                params.path = value.path;
                params.mapped = value.mapped;
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'flash_path':
	        var obj = {path:params.path,mapped:params.mapped};
                return obj;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
        }
    }
});

DSS.Options.comp_map['11'] = FlashComp;
