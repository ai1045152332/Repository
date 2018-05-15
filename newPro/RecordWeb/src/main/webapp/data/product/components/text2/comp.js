var scrollText2 = function (boxId,params){
    this.box = typeof(boxId)=='string'?DSS.util.byId(boxId):boxId;
    this.dim = params.dim;
    this.speed = parseInt(params.speed);
    this.txtBox =  this.getEleByClass(this.box,this.classes.txtBox);
    this.space = this.box.offsetWidth;
};
scrollText2.prototype = {
    constructor: scrollText2,
    classes : {
        box : 'editItem25',
        txtBox : 'TEXT_COMP_text2'
    },
    init : function (){
        if(!this.dim||this.dim=='0')return;
        this.initTime = new Date().getTime();
        this.addEvents();
        //this.setTimer();
        this.startMove();
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
            });
        }else if(this.dim=="left"){
            DSS.util.startUniformMove(this.txtBox,{left:-this.space},this.speed,function(){
                this.style.left = that.space +'px';
                that.setTimer();
            });
        }
    },
    startMove : function(){
        if(this.dim == "right" || this.dim == "left"){
            var options = {"dim":this.dim, "speed":this.speed};
            this.rolling = this.startTextMove(this.txtBox, options);
        } else if(this.dim == "up" || this.dim == "down"){
            var options = {"dim":this.dim, "speed":this.speed};
            this.rolling = this.startTextMove(this.txtBox, options);
        }
    },
    addEvents : function (){
    },//文字垂直移动2
    startTextMove : function(divContainer, options){
    	var style = $('<style></style>');
        var container = typeof divContainer === 'string' ? document.getElementById(divContainer) : divContainer;
        var contentTemp = '<div class="scrollYDiv">'
        	+ '<table>'
            + ' <tr>'
            + ((options.dim == "right" || options.dim == "left") ? '     <td id="rollingcell" class="rollingCell_1">' : '     <td id="rollingcell" class="rollingCell_V1">')
            + '         <table>'
            + '             <tr>'
            + '                 <td>{content}</td>'
            + '             </tr>'
            + '         </table>'
            + '     </td>'
            + ' </tr>'
            + '</table>'
            + '</div>';
        container.innerHTML = contentTemp.replace(/\{content\}/g, container.innerHTML);
        container.style.cssText = '';
        var options = options || {};
        var speed = 3;
    	
    	var parcontent = $(this.txtBox);
    	var parcontentid = $(parcontent).parent().parent().attr('id');
    	var parcontentw = $(parcontent).width();
    	var parcontenth = $(parcontent).height();
        container.innerHTML = "<div class='" + parcontentid + "'>" + container.innerHTML + "</div>";
        //$('.newdiv').attr('style', options.dim + "width:" + parcontentw + "' height:" + parcontenth + "' scrollAmount='" + speed);
        var newdiv = $('.newdiv');
        	if(options.dim == 'up'||options.dim == 'down'){
            	$('.rollingCell_V1').css({'word-break':'break-all','white-space':'normal'});
            }else if(options.dim == 'left'||options.dim == 'right'){
            	$('#rollingcell table tr td').find('br').remove();
            	$('.rollingCell_1').css({'word-break':'normal','white-space':'nowrap'});
            }
        
        var rollingcell = $('#'+parcontentid).find('#rollingcell');
        var newdivchildw = $(rollingcell).width();
    	var newdivchildh = $(rollingcell).height();
    	$('head').append(style);
    	
    	var curpostart,curposend,curposway,moveway;
    	if(options.dim == 'up'){
    		curpostart = parcontenth;
    		curposend = -newdivchildh;
    		
    	}else if(options.dim == 'down'){
    		curpostart = -newdivchildh;
    		curposend = parcontenth;
    	}
    	
    	if(options.dim == 'left'){
    		curpostart = parcontentw;
    		curposend = -newdivchildw;
    	}else if(options.dim == 'right'){
    		curpostart = -newdivchildw;
    		curposend = parcontentw;
    	}
    	
    	if(options.dim == 'up' || options.dim == 'down'){
    		curposway = "top";
    		moveway = (parcontenth + newdivchildh);
		}else{
    		curposway = 'left';	
    		moveway = (parcontentw + newdivchildw);
		}
    	
    	
    	
    	if (options.speed == "1") {
    		speed = moveway/50;
    	} else if (options.speed == "2") {
    		speed = moveway/200;
    	} else if (options.speed == "4") {
    		speed = moveway/300;
    	}
    	
    	
		$(style).append("." + parcontentid 
				+ "{ " 
				+ "width:100%;height:100%;" 
				+ "position: relative;"
				+ curposway + ":" + curpostart + "px;"
				+ "animation-name:" + parcontentid + "; " 
				+ "animation-duration:" + speed + 's;'
				+ "animation-iteration-count: infinite;"
				+ "animation-timing-function: linear;"
				+ "-webkit-animation-name:" + parcontentid + "; " 
				+ "-webkit-animation-duration:" + speed + 's;'
				+ "-webkit-animation-iteration-count: infinite;"
				+ "-webkit-animation-timing-function: linear;"
				+ "}"
				+ "@keyframes " + parcontentid + "{" 
				+ "from{" 
				+ curposway + ":" + curpostart + "px;"
				+ "}"
				+ "to{" 
				+ curposway + ":" + curposend + "px;"
				+ "}"
				+ "}"
				+ "@-webkit-keyframes " + parcontentid + "{" 
				+ "from{" 
				+ curposway + ":" + curpostart + "px;"
				+ "}"
				+ "to{" 
				+ curposway + ":" + curposend + "px;"
				+ "}"
				+ "}"
		);
    	
    },
    destroy:function (){
        this.box = null;
        this.dim = null;
        this.speed = null;
        this.txtBox =  null;
        this.space = null;
    }
};
(function() {
    var lastTime = 0;
    var vendors = ['webkit', 'moz'];
    for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
        window.requestAnimationFrame = window[vendors[x] + 'RequestAnimationFrame'];
        window.cancelAnimationFrame = window[vendors[x] + 'CancelAnimationFrame'] ||    // Webkit中此取消方法的名字变了
                                      window[vendors[x] + 'CancelRequestAnimationFrame'];
    }
    if (!window.requestAnimationFrame) {
        window.requestAnimationFrame = function(callback, element) {
            var currTime = new Date().getTime();
            var timeToCall = Math.max(0, 16.7 - (currTime - lastTime));
            var id = window.setTimeout(function() {
                callback(currTime + timeToCall);
            }, timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        };
    }
    if (!window.cancelAnimationFrame) {
        window.cancelAnimationFrame = function(id) {
            clearTimeout(id);
        };
    }
}());