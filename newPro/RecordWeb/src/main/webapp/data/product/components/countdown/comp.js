
/**
 * User: hht-zsl
 * Date: 14-4-29
 * 倒计时
 */
var ddid ;
var GetTimer = function (container) {
	this.container = $(container);
	this.bid = container.parentNode.id;
	ddid = this.bid;
};
GetTimer.prototype = {
	init : function () {
		var me = this;
		var wander = document.getElementById(ddid);
		var leng = wander.children[0].children[0].children.length;
		if(leng<=4){
			setTimeout(function(){
				me.setTimer0();
			},10);
		}else{
			setTimeout(function(){
				me.setTimer();
			}, 10);
		}
		console.info("_______________________________________________________________________________");
	},
	setTimer:function(){
		 var me = this;
		 var now=new Date();
		 var ofd,ofh,ofm,ofs;
		 var endDate = me.container[0].children[0].children[0].value;
		 if(endDate==''){
			ofd=0;
			ofh=0;
			ofm=0;
			ofs=0;
		 }else{
			 var d2 = new Date(Date.parse(endDate));
			 var oft=Math.round((d2-now)/1000);
			 ofd=parseInt(oft/3600/24);
			 ofh=parseInt((oft%(3600*24))/3600);
			 ofm=parseInt((oft%3600)/60);
			 ofs=oft%60;
			 if(oft <= 0){
				ofd=0;
				ofh=0;
				ofm=0;
				ofs=0;
			 }
		 }
		 chll = this.container[0].children[0].children; 
		 for(var i = 0;i<chll.length;i++){
			 if(chll[i].className == 'countdowntime'){
				 if(ofd < 10){
					 chll[i].innerHTML = "0"+ofd;
				 }else if(ofd > 999){
					 ofd = 999
					 chll[i].innerHTML = ofd;
				 }else{
					 chll[i].innerHTML = ofd;
				 }
				 
				 if(ofh < 10){
					 chll[i+2].innerHTML = "0"+ofh;
				 }else{
					 chll[i+2].innerHTML = ofh;
				 }
				 
				 if(ofm < 10){
					 chll[i+4].innerHTML = "0"+ofm;
				 }else{
					 chll[i+4].innerHTML = ofm;
				 }
				 
				 if(ofs < 10){
					 chll[i+6].innerHTML = "0"+ofs;
				 }else{
					 chll[i+6].innerHTML = ofs;
				 }
				 break;
			 }
		 }
		 setTimeout(function(){
			 me.setTimer();
			}, 1000);
	},
	setTimer0:function(){
		 var me = this;
		 var now=new Date();
		 var ofd = '';
		 var endDate = me.container[0].children[0].children[0].value;
		 if(endDate==''){
			ofd=0;
		}else{
			 var d2 = new Date(Date.parse(endDate));
			 var oft=Math.round((d2-now)/1000);
			 ofd=parseInt(oft/3600/24)+1; 
		}
		if(ofd>=1000){
			ofd = 999;
		}
		 chll = this.container[0].children[0].children; 
		 for(var i = 0;i<chll.length;i++){
			 if(chll[i].className == 'countdowntime'){
				 if(ofd<10){
					 chll[i].innerHTML = "00"+ofd;
					 //chll[i].innerHTML = "99";
				 }
				 else if(ofd<100){
					 chll[i].innerHTML = "0"+ofd;
				 }else if(ofd>=100 && ofd<1000){
					 chll[i].innerHTML = ofd;
				 }
				 break;
			 }
		 }
		 setTimeout(function(){
			 me.setTimer0();
			}, 86400000);
	}
};