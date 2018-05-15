/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-18
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */

var  ImageSwipeComp = {
    dftVals : {isFtp:0,during:2000,ftpPath:'',imgLoop:[
        /*{title:'1',path:'images/1.jpg'},
        {title:'2',path:'images/2.jpg'},
        {title:'3',path:'images/3.jpg'}*/
    ]},
    imgClass : 'IMAGESWIPE_COMP_img'
};
DSS.util.extend(ImageSwipeComp,CompChClass);
DSS.util.extend(ImageSwipeComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var img =  item.getElementsByClassName(this.imgClass)[0];
        img.ondragstart = function (){return false};
        DSS.util.events(item,'selectstart',function(e){
            if(e.stopPropagation)e.stopPropagation();
            if(e.preventDefault)e.preventDefault();// add 20140813
        });
        return item;
    },
    destroy : function (item){
        var img =  item.getElementsByClassName(this.imgClass)[0];
        img.ondragstart = null;
        DSS.util.delEvent(item,'selectstart');
        CompClass.comDestroy(item);
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        switch (name){
            case 'img_list':
                params.imgLoop = value;
                break;
            case 'isFtp':
                params.isFtp = value;
                break;
            case 'tm_during':
                params.during = value*1000;
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'img_list':
                return params.imgLoop;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
            case 'isFtp':
                return params.isFtp;
            case 'tm_during':
                return params.during/1000;
            case 'ftpPath':
                return params.ftpPath;
        }
    }
});

DSS.Options.comp_map['8'] = ImageSwipeComp;
DSS.Options.comp_map['18'] = ImageSwipeComp;  //"18"--->PPT 
