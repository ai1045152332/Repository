/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */

var  StreamingComp = {
    dftVals : {url:"",
    	thirdstream: '2', // 第三方流媒体
    	honghestream:'1', // 鸿合流媒体	
    },
    srcClass: 'STREAM_COMP_src'
};
DSS.util.extend(StreamingComp,CompChClass);
DSS.util.extend(StreamingComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
        srcObj.ondragstart = function (){return false};
        DSS.util.events(item,'selectstart',function(e){
            if(e.stopPropagation)e.stopPropagation();
            if(e.preventDefault)e.preventDefault();// add 20140813
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
            case 'url':
                params.url = value;
                break;
            case 'thirdstream':
                params.thirdstream = value; 
                break;
			 case 'honghestream':
                params.honghestream = value; 
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'url':
                return params.url;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
            case 'thirdstream':
        		return params.thirdstream;
        	case 'honghestream':
                return params.honghestream;
        }
    }
});

DSS.Options.comp_map['14'] = StreamingComp;
