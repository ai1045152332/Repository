/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-18
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */



var  AccordionComp = {
    dftVals : {isFtp:0,during:2,ftpPath:'',imgLoop:[
        /*{title:'img1',path:'images/pic1.jpg'},
        {title:'img2',path:'images/pic2.jpg'},
        {title:'img3',path:'images/pic3.jpg'},
        {title:'img4',path:'images/pic4.jpg'},
        {title:'img5',path:'images/pic5.jpg'}*/
    ]},
    imgClass : 'ACCORDION_COMP_img'
};
DSS.util.extend(AccordionComp,CompScClass);
DSS.util.extend(AccordionComp,{
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
                params.during = value;
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
                return params.during;
            case 'ftpPath':
                return params.ftpPath;
        }
    }
});

DSS.Options.comp_map['4'] = AccordionComp;
