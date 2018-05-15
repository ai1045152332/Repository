/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */

var EWorksComp = {
	/**
	 * @parame dftVals：默认参数对象
	 **@parame worksType：作品类别
	 **@parame backPic：作品详情背景图
	 **@parame articleList：作品列表, 初始为空
	 **@parame snap：组件占位图
	 */
	dftVals : { 
		workType:"",
		backPic:"",
        backMapped: "../../images/add_detailBg.png",
		//snap:"images/eWorksComp.png",
		articleList:[
			/*{id:"1", title:'作品1',content:'images/1.jpg',author:'三年级一班 张XX'},
			{id:"1", title:'作品2',content:'images/2.jpg',author:'五年级一班 王XX'},
			{id:"1", title:'作品3',content:'images/3.jpg',author:'四年级二班 刘XX'}*/
		]
    },
    srcClass: 'EWorks_COMP_src'
};
DSS.util.extend(EWorksComp,CompChClass);

DSS.util.extend(EWorksComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var srcObj =  item.getElementsByClassName(this.srcClass)[0];
       // srcObj.src = o.snap;
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
            case 'work_type':
                params.workType = value;
                break;
			case 'backPic':
                params.backPic = value.path;
                params.backMapped = value.mapped;
                break;
			case 'articleList':
                params.articleList = value;
                break;	
            /*case 'snap':
                srcObj.src = value;
                params.snap = value;
                break;*/
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'work_type':
                return params.workType;
			case 'backPic':
                return {path:params.backPic,mapped:params.backMapped};
			case 'articleList':
                return params.articleList;
            /*case 'snap':
                return params.snap;*/
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
        }
    }
});

DSS.Options.comp_map['15'] = EWorksComp;





