/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-20
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
var  TextComp = {
    dftVals : {con:'',dim:'0',speed:1},
    rectClass: 'editItem5',
    txtClass : 'TEXT_COMP_text'
};
DSS.util.extend(TextComp,CompChClass);
DSS.util.extend(TextComp,{
    getParseStr:function (item){
        var txt =  item.getElementsByClassName(this.txtClass)[0];
        var params = JSON.parse(item.params);
        params.con = txt.innerHTML;
        return params;
    },
    txtFocus : function (e,txt){
        if(txt.contentEditable!="true"){
            txt.contentEditable = "true";
            txt.focus();
        }
        DSS.BlockOpr.mayMove = false;
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

DSS.Options.comp_map['5'] = TextComp;
