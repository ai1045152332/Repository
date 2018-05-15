/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-21
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
/**
 *部件的各种通用方法
 * @type {{originItemSize: *, setItemScW: Function, setItemScH: Function, setItemChW: Function, setItemChH: Function, comAdd: Function, comCopy: Function, addParamsToItem: Function, setParamToItem: Function, getParamFromItem: Function, comDestroy: Function}}
 */
var CompClass = {
    originItemSize : DSS.Options.menuItem,
    setItemScW : function (item,n,sc){
        var bdw = DSS.util.getStyleVal(item.children[0],'borderLeftWidth');
        var oSize = this.originItemSize[item.type_id];
        var ow = oSize.w + 2*bdw;
        var tp = typeof(sc);
        var ratio = (tp!="null"&& tp!='undefined')?sc : n/ow;
        item.style.width = (tp!="null"&& tp!='undefined')?sc*ow+'px':n + 'px';
        item.t_scaleX = ratio;
        var str =  'scale('+item.t_scaleX+','+item.t_scaleY+')';
        item.children[0].style.transform =  str;
        item.children[0].style.webkitTransform = str;
        var params = JSON.parse(item.params);
        params.scX = ratio;
        item.params = JSON.stringify(params);
    },
    setItemScH : function (item,n,sc){
        var bdw = DSS.util.getStyleVal(item.children[0],'borderLeftWidth');
        var oSize = this.originItemSize[item.type_id];
        var oh = oSize.h + 2*bdw;
        var tp = typeof(sc);
        var ratio = (tp!="null"&& tp!='undefined')?sc : n/oh;
        item.style.height = (tp!="null"&& tp!='undefined')?sc*oh+'px':n + 'px';
        item.t_scaleY = ratio;
        var str = 'scale('+item.t_scaleX+','+item.t_scaleY+')';
        item.children[0].style.transform = str;
        item.children[0].style.webkitTransform = str;
        var params = JSON.parse(item.params);
        params.scY = ratio;
        item.params = JSON.stringify(params);
    },
    setItemChW : function (item,n){
        var ch = item.children[0];
        var bdw = DSS.util.getStyleVal(ch,'borderLeftWidth');
        ch.style.width = n - 2*bdw+'px';
        item.style.width = n  + 'px';
        var params = JSON.parse(item.params);
        params.w = n;
        item.params = JSON.stringify(params);
    },
    setItemChH : function (item,n){
        var ch = item.children[0];
        var bdw = DSS.util.getStyleVal(ch,'borderTopWidth');
        ch.style.height = n - 2*bdw+'px';
        item.style.height = n  + 'px';
        var params = JSON.parse(item.params);
        params.h = n;
        item.params = JSON.stringify(params);
    },
    comAdd : function (typeId){
         var item = document.createElement('div');
         item.className = DSS.Options.eClass.editBlock;
         item.innerHTML = DSS.Options.menuItem[typeId].innerHTML;
         item.id = DSS.MakeId.mkBlockId();
         item.params = "{}";
         DSS.Options.el.mainBoxIn.appendChild(item);
         return item;
    },
    comCopy : function (e,id){
        if(!id)id = DSS.MakeId.mkBlockId;
        var el = e.cloneNode(true);
        el.params = e.params;
        //console.log(el.innerHTML+"-----------------paramscloneNode-----------------------------" )
        el.id = id;
        //update by caoqian 2016/3/29 flash\ppt\pic\视频\网站等文件复制及粘贴操作  begin----------------------
        if(el.getElementsByClassName("editItem25").length>0)   //文本
        {
            var con=el.getElementsByClassName("TEXT_COMP_text2")[0].innerHTML;//从获取纯文本改为获取富文本(textContent改为innerHTML)
            var jsObject = eval("(" +  el.params + ")");
            jsObject.con = con;
            el.params =JSON.stringify(jsObject);
            el.params =JSON.stringify(jsObject);
        }
        else
        {
            var jsObject = eval("(" +  el.params + ")");
            el.params =JSON.stringify(jsObject);
        }
        //update by caoqian 2016/3/29 pdf\flash\ppt\pic等文件复制及粘贴操作  end----------------------

        DSS.Options.el.mainBoxIn.appendChild(el);
        return el;
    },

    addParamsToItem : function (item,o){
        var obj = JSON.parse(item.params);
        for(var i in o){
            obj[i]=o[i];
        }
        item.params = JSON.stringify(obj);
    },
    setParamToItem : function (item,name,value){
        var obj = JSON.parse(item.params);
        obj[name] = value;
        item.params = JSON.stringify(obj);
    },
    getParamFromItem :function (item,name){
        var obj = JSON.parse(item.params);
        return obj[name];
    },
    comDestroy : function (item){
        item.parentNode.removeChild(item);
    }
};

/**
 * 用width和height来处理缩放的部件的类
 * @type {{getParseStr: Function, initBlock: Function, addBlock: Function, copyBlock: Function, setItemW: Function, setItemH: Function}}
 */
var CompChClass = {
    getParseStr:function (item){
        return JSON.parse(item.params);
    },
    initBlock : function (o){
        var item = CompClass.comAdd(o.t);
        return this.addParamsToItem(item,o);
    },
    addBlock : function (typeId){
        var item = CompClass.comAdd(typeId);
        var o = {w:this.getProperty(item,'width'),h:this.getProperty(item,'height')};
        for(var i in this.dftVals){
            o[i] = this.dftVals[i];
        }
        return this.addParamsToItem(item,o);
    },
    copyBlock : function (e,id){
        var item = CompClass.comCopy(e,id);
        var o = JSON.parse(item.params);
        return this.addParamsToItem(item,o);
    },
    setItemW : function (item,n,sc){
        CompClass.setItemChW(item,n,sc);
    },
    setItemH : function (item,n,sc){
        CompClass.setItemChH(item,n,sc);
    },
    destroy : function (item){
        CompClass.comDestroy(item);
    }
};
/**
 * 用scX和scY来处理缩放的部件的类
 * @type {{}}
 */
var CompScClass = {};
DSS.util.extend(CompScClass,CompChClass);
/*DSS.util.extend(CompScClass,{
    addBlock : function (typeId){
        var item = CompClass.comAdd(typeId);
        var o = {scX:1,scY:1};
        for(var i in this.dftVals){
            o[i] = this.dftVals[i];
        }
        return this.addParamsToItem(item,o);
    },
    setItemW : function (item,n,sc){
        CompClass.setItemScW(item,n,sc);
    },
    setItemH : function (item,n,sc){
        CompClass.setItemScH(item,n,sc);
    }
});*/
DSS.util.extend(CompScClass,{
    getParseStr:function (item){
        var params = JSON.parse(item.params);
        params.w = this.getProperty(item,'width');
        params.h = this.getProperty(item,'height');
        var bdw = DSS.util.getStyleVal(item.children[0],'borderLeftWidth');
        var oSize = CompClass.originItemSize[item.type_id];
        if(params.tymd == '2'){
        	oSize.w = 100;
        	oSize.h = 60;
        }else if(params.tymdhms == '2'){
        	oSize.w = 300;
        	oSize.h = 60;
        }
        var obj = {};
        for(var i in params){
            if(i=='w'){
                obj['scX']=params[i]/(oSize.w+2*bdw);
            }else if(i=='h'){
                obj['scY']=params[i]/(oSize.h+2*bdw);
            }else{
                obj[i] = params[i];
            }
        }
        return obj;
    },
    addBlock : function (typeId){
        var item = CompClass.comAdd(typeId);
        var o = {scX:1,scY:1};
        for(var i in this.dftVals){
            o[i] = this.dftVals[i];
        }
        return this.addParamsToItem(item,o);
    },
    initBlock : function (o){
        var item = CompClass.comAdd(o.t),ch = item.children[0];
        var oSize = CompClass.originItemSize[o.t];
        if(o.tymd == '2'){
        	oSize.w = 100;
        	oSize.h = 60;
        }else if(o.tymdhms == '2'){
        	oSize.w = 300;
        	oSize.h = 60;
        }
        var bdw = DSS.util.getStyleVal(ch,'borderLeftWidth');
        var scX =  parseFloat(o.scX,10),scY = parseFloat(o.scY,10),dbW = 2*bdw;
        ch.style.width = Math.round(scX*oSize.w+(scX-1)*dbW) +'px';
        ch.style.height = Math.round(scY*oSize.h+(scY-1)*dbW) +'px';
        return this.addParamsToItem(item,o);
    }
});
