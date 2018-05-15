/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-11
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 */

var BindElement = {
    util : util,
    init : function (chgId,xValId,yValId,wValId,hValId){
        var chgEle=$(chgId).get(0),xEle=$(xValId).get(0),yEle=$(yValId).get(0),wEle=$(wValId).get(0),hEle=$(hValId).get(0);
        this.bind(chgEle,xEle,'left');
        this.bind(chgEle,yEle,'top');
        this.bind(chgEle,wEle,'width');
        this.bind(chgEle,hEle,'height');
        this.setCordsAndSizeVal(chgEle,xEle,yEle,wEle,hEle);
    },
    bind : function (chgEle,prptyEle,prpty){
        var that = this;
        if(prpty=='width'||prpty == 'height'){
            that.bindResize(chgEle,prptyEle,prpty);
        }else{
            that.bindDrag(chgEle,prptyEle,prpty);
        }

        that.bindChange(chgEle,prptyEle,prpty);
    },
    bindDrag : function (chgEle,prptyEle,prpty){
        var that = this;
        $(chgEle).bind('dragend',function(e){
            that.setCordsVal(chgEle,prptyEle,prpty);
        });
    },
    bindResize : function(chgEle,prptyEle,prpty){
        var that = this;
        $(chgEle).resize(function(e){
             that.setSizeVal(chgEle,prptyEle,prpty);
        });
    },
    bindChange : function (chgEle,prptyEle,prpty){
        $(prptyEle).bind('keyup',function(e){
            $(chgEle).css(prpty,$(prptyEle).val()+'px');
        });
    },
    setCordsVal : function (chgEle,prptyEle,prpty){
        switch (prpty){
            case 'left':
                $(prptyEle).val(chgEle.offsetLeft);
                break;
            case  'top':
                $(prptyEle).val(chgEle.offsetTop);
                break;
        }
    },
    setSizeVal : function (chgEle,prptyEle,prpty){
        switch (prpty){
            case  'width':
                $(prptyEle).val(chgEle.clientWidth);
                break;
            case  'height':
                $(prptyEle).val(chgEle.clientHeight);
                break;
        }
    },
    setCordsAndSizeVal : function (chgEle,xEle,yEle,wEle,hEle){
        this.setCordsVal(chgEle,xEle,'left');
        this.setCordsVal(chgEle,yEle,'top');
        this.setSizeVal(chgEle,wEle,'width');
        this.setSizeVal(chgEle,hEle,'height');
    },
    removeBind : function (chgEle){
        $(chgEle).unbind('drag');
        $(chgEle).unbind('resize');
    }
};