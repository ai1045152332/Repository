/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-20
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
var  Text2Comp = {
    dftVals : {con:'',dim:'0',speed:1},
    rectClass: 'editItem25',
    txtClass : 'TEXT_COMP_text2',
    maskClass : 'TEXT_COMP_MASK'
};
DSS.util.extend(Text2Comp,CompChClass);
DSS.util.extend(Text2Comp,{
    getParseStr:function (item){
        var txt =  item.getElementsByClassName(this.txtClass)[0];
        var params = JSON.parse(item.params);
        params.con = txt.innerHTML;
        return params;
    },
    txtFocus : function (e,txt){
        if(txt.contentEditable!="false"){
            txt.contentEditable = "false";
            txt.focus();
        }
        DSS.BlockOpr.mayMove = true;
    },
    txtBlur : function (e,txt,item){
        txt.contentEditable = "false";
        DSS.BlockOpr.mayMove = true;
        var oldVal = CompClass.getParamFromItem(item,'con');
        CompClass.setParamToItem(item,'con',txt.innerHTML);
        DSS.Controller.execute('CMD_TXT_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'txtCont',oldVal,txt.innerHTML);
    },
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var txt =  item.getElementsByClassName(this.txtClass)[0];
    	txt.innerHTML = o.con;
        var mask =  item.getElementsByClassName(this.maskClass)[0];
        if (txt.innerHTML.replace(/<br>/g, '').replace(/[ ]/g, '') == '' && o.con.replace(/<br>/g, '').replace(/[ ]/g, '') == '') {
        	//初始化内容
        	mask.style.backgroundImage = "url(images/textNotify.png)";
        	mask.style.backgroundPosition = "center";
        	mask.style.backgroundRepeat = "no-repeat";
        	mask.style.backgroundColor = "";
        	mask.style.filter = "";
        	mask.style.mozOpacity = "";
        	mask.style.khtmlOpacity = "";
        	mask.style.opacity = "";
        } else {
        	mask.style.backgroundImage = "";
        	mask.style.backgroundPosition = "";
        	mask.style.backgroundRepeat = "";
        	mask.style.backgroundColor = "red";
        	mask.style.filter = "alpha(opacity=0)";
        	mask.style.mozOpacity = "0";
        	mask.style.khtmlOpacity = "0";
        	mask.style.opacity = "0";
        }
        var rect = item.getElementsByClassName(this.rectClass)[0];
        var that = this;
        DSS.util.events(rect,'dblclick',function(e){
            that.txtFocus(e,txt);
        });
        DSS.util.events(txt,'blur',function(e){
            that.txtBlur(e,txt,item);
        });
        return item;
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var rect =  item.getElementsByClassName(this.rectClass)[0];
        var txt =  item.getElementsByClassName(this.txtClass)[0];
        switch (name){
            case 'txtCont':
                txt.innerHTML = value;
                params.con = value;
                break;
            case 'dim':
                params.dim = value;
                break;
            case 'speed':
                params.speed = parseInt(value);
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var rect =  item.getElementsByClassName(this.rectClass)[0];
        var txt =  item.getElementsByClassName(this.txtClass)[0];
        var params = JSON.parse(item.params);
        switch (name){
            case 'txtCont':
                return txt.innerHTML;
            case 'dim':
                return  params.dim;
            case 'speed':
                return params.speed;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.clientHeight;
        }
    }
});

DSS.Options.comp_map['25'] = Text2Comp;
