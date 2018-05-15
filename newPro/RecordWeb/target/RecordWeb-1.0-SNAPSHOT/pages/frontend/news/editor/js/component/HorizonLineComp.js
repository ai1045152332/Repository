/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-27
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */

var  HorizonLineComp = {
    dftVals : {bdC:'#969696',bdS:'solid'},
    rectClass: 'editItem6'
};
DSS.util.extend(HorizonLineComp,CompChClass);
DSS.util.extend(HorizonLineComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var rect =  item.getElementsByClassName(this.rectClass)[0];
        rect.style.borderTopColor = o.bdC;
        rect.style.borderTopStyle = o.bdS;
        rect.style.width = o.w + 'px';
        rect.style.borderTopWidth = o.h + 'px';
        return item;
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var rect =  item.getElementsByClassName(this.rectClass)[0];
        switch (name){
            case 'bd_color':
                var clr = params.bdC;
                var opacity = DSS.util.getClrOpacity(clr);
                value = DSS.util.colorRgba(value,opacity);
                rect.style.borderTopColor = value;
                params.bdC = value;
                break;
            case 'bd_opacity':
                value = DSS.util.colorRgba(params.bdC,value);
                rect.style.borderColor = value;
                params.bdC = value;
                break;
            case 'bd_style':
                rect.style.borderTopStyle = value;
                params.bdS = value;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var rect =  item.getElementsByClassName(this.rectClass)[0];
        var params = JSON.parse(item.params);
        switch (name){
            case 'bd_color':
                var clr = params.bdC;
                return DSS.util.getClrRgb(clr);
            case 'bd_style':
                return DSS.util.getStyle(rect,'borderTopStyle');
            case 'bd_opacity':
                var clr = params.bdC;
                return parseFloat(DSS.util.getClrOpacity(clr));
            case 'width':
                return item.children[0].clientWidth;
            case 'height':
                return item.children[0].offsetHeight;
        }
    },
    setItemH : function (item,n,sc){
        item.children[0].style.borderTopWidth = n + 'px';
        item.style.height = n + 'px';
        CompClass.setParamToItem(item,'h',n);
    }
});

DSS.Options.comp_map['6'] = HorizonLineComp;
