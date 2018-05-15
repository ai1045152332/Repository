/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */

var CourseListComp = {
	dftVals : { 
		cListID: '',
		cData:'',
		bgImg: 'images/cList_dftBg.png',
        bgMapped: '',
		serverIp:''
    },
    srcClass: 'COURSELIST_COMP_src'
};
DSS.util.extend(CourseListComp,CompScClass);

DSS.util.extend(CourseListComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
        srcObj.ondragstart = function (){return false};
        DSS.util.events(item,'selectstart',function(e){
            if(e.stopPropagation)e.stopPropagation();
            if(e.preventDefault)e.preventDefault(); // add 20140813
        });
        return item;
    },
    destroy : function (item){
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
        srcObj.ondragstart = null;
        DSS.util.delEvent(item,'selectstart');
        CompClass.comDestroy(item);
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
        switch (name){
            case 'cListID':
                params.cListID = value; 
				//var data = DSS.util.getCurrentCList(value);
                //params.cData = data;
                break;
			 case 'cData':
                params.cData = value; 
                break;
			case 'bgImg':
                params.bgImg = value.path;
                params.bgMapped = value.mapped;
                break;
			case 'serverIp':
                params.serverIp = value;
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'cListID':
                return params.cListID;
			case 'cData':
                return params.cData;
			case 'bgImg':
                return {path:params.bgImg,mapped:params.bgMapped};
			case 'serverIp':
				return params.serverIp;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
        }
    }
});

DSS.Options.comp_map['22'] = CourseListComp;





