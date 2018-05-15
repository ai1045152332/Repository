var scrollText = function (boxId,params){
    this.box = typeof(boxId)=='string'?DSS.util.byId(boxId):boxId;
    this.dim = params.dim;
    this.speed = parseInt(params.speed);
    this.txtBox =  this.getEleByClass(this.box,this.classes.txtBox);
    this.space = this.box.offsetWidth;
};
scrollText.prototype = {
    constructor: scrollText,
    classes : {
        box : 'editItem5',
        txtBox : 'TEXT_COMP_text'
    },
    init : function (){
        if(!this.dim||this.dim=='0')return;
        this.addEvents();
        this.setTimer();
    },
    getEleByClass : function (p,n){
        return p.getElementsByClassName(n)[0];
    },
    setTimer : function(){
        var that = this;
        if(this.dim=="right"){
            DSS.util.startUniformMove(this.txtBox,{left:this.space},this.speed,function(){
                this.style.left = -that.space +'px';
                that.setTimer();
            })
        }else if(this.dim=="left"){
            DSS.util.startUniformMove(this.txtBox,{left:-this.space},this.speed,function(){
                this.style.left = that.space +'px';
                that.setTimer();
            })
        }
    },
    addEvents : function (){
    },
    destroy:function (){
        this.box = null;
        this.dim = null;
        this.speed = null;
        this.txtBox =  null;
        this.space = null;
    }
};