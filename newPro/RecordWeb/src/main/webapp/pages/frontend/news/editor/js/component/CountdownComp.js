/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */

var CountdownComp = {
	dftVals : { 
		type:"countdownComp_1",
		tymd: '2', // 年月日
		tymdhms:'1', //年月日 时分秒
		tymdval: '', 
		tymdhmsval: '',
		fontcolor:'rgb(250,250,250)' // 天 时 分 秒 字体的颜色
//		numbercolor:'' // 数字的颜色
//		fontsize:'100' // 元素拖动时  元素内容的大小
    },
    countdownClass: 'COUNTDOWN_COMP_src'
};
DSS.util.extend(CountdownComp,CompScClass);

DSS.util.extend(CountdownComp,{
	addParamsToItem : function (item,o){
		CompClass.addParamsToItem(item,o);
        var weaObj =  item.getElementsByClassName(this.countdownClass)[0];
		if(o.tymd != '' && o.tymd == '2'){
			weaObj.innerHTML = '<span class="countdowntime" id="cdy">000</span><span class="countdowndate">天</span>';
		}else if(o.tymdhms != '' && o.tymdhms == '2'){
			weaObj.innerHTML = '<span class="countdowntime" id="cddd">00</span><span class="countdowndate">天</span><span class="countdowntime" id="cddh">00</span><span class="countdowndate">时</span><span class="countdowntime" id="cdddm">00</span><span class="countdowndate">分</span><span class="countdowntime" id="cdds">00</span><span class="countdowndate">秒</span>';
		}
        weaObj.style.width = weaObj.parentNode.style.width;//o.w + 'px';
        weaObj.style.height = weaObj.parentNode.style.height;//o.h + 'px';
        weaObj.style.lineHeight = weaObj.parentNode.style.height;//o.h + 'px';
        var chl = weaObj.children;
		if(chl.length > 0){
			for(var i =0;i<chl.length;i++){
				if(chl[i].className == 'countdowndate'){
					chl[i].style.color = o.fontcolor;
//					chl[i].style.fontSize = (o.fontsize / 2)+"px";
				}else if(chl[i].className == 'countdowntime'){
					chl[i].style.color = o.fontcolor;
//					chl[i].style.fontSize = o.fontsize+"px";
				}
			}
		}
		weaObj.ondragstart = function (){return false};
        return item;
    },
    destroy : function (item){
		var weaObj =  item.getElementsByClassName(this.countdownClass)[0];
        weaObj.ondragstart = null;
        CompClass.comDestroy(item);
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var srcObj =  item.getElementsByClassName(this.countdownClass)[0];
        switch (name){
            case 'tymd':
                params.tymd = value; 
                break;
			 case 'tymdhms':
                params.tymdhms = value; 
                break;
			 case 'tymdval':
	            params.tymdval = value; 
	            break;
			case 'tymdhmsval':
	            params.tymdhmsval = value; 
	            break;
//			case 'fontsize':
//	            params.fontsize = value; 
//	            break;
			case 'fontcolor':
				var chl = srcObj.children;
				if(chl.length > 0){
					for(var i =0;i<chl.length;i++){
						if(chl[i].className == 'countdowndate'){
							chl[i].style.color = value;
						}
						if(chl[i].className == 'countdowntime'){
							chl[i].style.color = value;
						}
					}
				}
	            params.fontcolor = value; 
	            break;
			case 'numbercolor':
				var chl = srcObj.children;
				if(chl.length > 0){
					for(var i =0;i<chl.length;i++){
						if(chl[i].className == 'countdowntime'){
							chl[i].style.color = value;
						}
					}
				}
	            params.numbercolor = value; 
	            break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
        	case 'tymd':
        		return params.tymd;
        	case 'tymdhms':
                return params.tymdhms;
        	case 'tymdval':
        		return params.tymdval;
        	case 'tymdhmsval':
                return params.tymdhmsval;
//        	case 'fontsize':
//        		return params.fontsize;
        	case 'fontcolor':
        		 var clr = params.fontcolor;
                 return DSS.util.getClrRgb(clr);
        	case 'numbercolor':
        		var clr = params.numbercolor;
                return DSS.util.getClrRgb(clr);
        	case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
        }
    }
});

DSS.Options.comp_map['26'] = CountdownComp;





