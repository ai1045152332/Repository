/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */

var  ImgComp = {
    dftVals : {mapped:"",path:"",rds:0},
    imgClass: 'IMG_COMP_img'
};
DSS.util.extend(ImgComp,CompChClass);
DSS.util.extend(ImgComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var img =  item.getElementsByClassName(this.imgClass)[0];
        if (o.mapped) img.src = o.mapped;
        img.style.borderRadius = o.rds + 'px';
        img.ondragstart = function (){return false};
        DSS.util.events(item,'selectstart',function(e){
            if(e.stopPropagation)e.stopPropagation();
            if(e.preventDefault)e.preventDefault();// add 20140813
        });
        return item;
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var img =  item.getElementsByClassName(this.imgClass)[0];
        switch (name){
            case 'img_path':
                img.src = value.mapped;
                params.path = value.path;
                params.mapped = value.mapped;
                break;
            case 'img_bd_radius':
                img.style.borderRadius = value?value+'px':'';
                params.rds = value?value:0;
                break;

        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'img_path':
                return {path:params.path,mapped:params.mapped};
            case 'img_bd_radius':
                return params.rds;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
        }
    }
});

DSS.Options.comp_map['1'] = ImgComp;
