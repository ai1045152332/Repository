/**
 * User: hht-zsl
 * Date: 14-4-28
 * 时间组件1
 */

var TimeComp_1 = {
	dftVals : { 
		type:"timeComp_1",
		bgC:"rgb(70,175,75)",
		ftC:"rgb(247,251,247)",
		bdW:0,
		bdC:"rgb(90,90,90)"
    },
    timeClass: 'editItem16'
};
DSS.util.extend(TimeComp_1,CompScClass);

DSS.util.extend(TimeComp_1,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var timeObj =  item.getElementsByClassName(this.timeClass)[0];
		timeObj.style.backgroundColor = o.bgC;
        timeObj.style.borderColor = o.bdC;
        timeObj.style.borderWidth = o.bdW + 'px';
		timeObj.style.color = o.ftC;
        timeObj.style.width = o.w + 'px';
        timeObj.style.height = o.h + 'px';
		timeObj.ondragstart = function (){return false};
        return item;
    },
    destroy : function (item){
		var timeObj =  item.getElementsByClassName(this.timeClass)[0];
        timeObj.ondragstart = null;
        CompClass.comDestroy(item);
    },
    setProperty : function (item,name,val){
        var params = JSON.parse(item.params);
        var timeObj =  item.getElementsByClassName(this.timeClass)[0];
        switch (name){
            case 'compType':
                params.type = val;
                break;
			case 'bg_color':
                var opacityVal = DSS.util.getClrOpacity(params.bgC);
                val = DSS.util.colorRgba(val,opacityVal);
                timeObj.style.backgroundColor = val;
                params.bgC = val;
                break;
			case 'bg_opacity': 
				val = DSS.util.colorRgba(params.bgC,val);
                timeObj.style.backgroundColor = val;
                params.bgC = val;
                break;
			case 'font_color':
				timeObj.style.color = val;
				params.ftC = val;
                break;
			case 'bd_width':
                timeObj.style.borderWidth = val + 'px';
                params.bdW = val;
				item.style.width = timeObj.offsetWidth + 'px';
                item.style.height = timeObj.offsetHeight + 'px';
                DSS.BlockOpr.maskMove2Obj();
                break;
			case 'bd_color':
                var opacityVal = DSS.util.getClrOpacity(params.bdC);
                val = DSS.util.colorRgba(val,opacityVal);
                timeObj.style.borderColor = val;
                params.bdC = val;
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
		var params = JSON.parse(item.params);
		//var timeObj =  item.getElementsByClassName(this.timeClass)[0];
        switch (name){
            case 'compType':
                return params.type;
			case 'bg_color':
				var clr = params.bgC;
                return DSS.util.getClrRgb(clr);
			case 'bg_opacity':
				var clr = params.bgC;
                return parseFloat(DSS.util.getClrOpacity(clr));
			case 'font_color':
			   var clr = params.ftC;
               return DSS.util.getClrRgb(clr);
			case 'bd_width':
				//return DSS.util.getStyleVal(timeObj,'borderLeftWidth');
				return params.bdW;
			case 'bd_color':
			   var clr = params.bdC;
               return DSS.util.getClrRgb(clr);
            case 'width':
                return item.offsetWidth;//item.children[0].clientWidth;
            case 'height':
                return item.offsetHeight;//item.children[0].clientHeight;
        }
    }
});

DSS.Options.comp_map['16'] = TimeComp_1;







