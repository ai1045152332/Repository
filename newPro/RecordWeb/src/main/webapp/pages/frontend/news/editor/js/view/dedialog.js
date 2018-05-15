/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-6-18
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */
DSS.MdDeDialog = {
    crEle : function (p){
        var el = document.createElement('div');
        el.className = "publishdiv";
        p.appendChild(el);
        return el;
        
    },
    open : function (url,closeFn,submitFn){
    //open : function (url,closeFn,backFn,okFn,successFn,submitFn){
        if(!this.dialog){
            this.dialog = this.crEle(document.body);
            
            this.mask = this.crEle(document.body);
            this.mask.className = "publishdiv-bg";

            this.inner = this.crEle(this.dialog);
            this.inner.className = "publishdiv-in";

            this.closeBtn = this.crEle(this.inner);
            this.closeBtn.className = "closebtn";
            this.closeBtn.innerHTML = 'X';
            this.closeBtn.title = '关闭';

            this.btnBar = this.crEle(this.inner);
            this.btnBar.className = "btnbar-bg";


            //this.sureBtn = this.crEle(this.btnBar);
            //this.sureBtn.className = "btnbar";
            /*this.sureBtn.onmouseover=function(){
            	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.sureBtn.onmouseout=function(){
                	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/
            //this.sureBtn.innerHTML = "下 一 步";
            //this.sureBtn.className = "btnbar nextstep";
            //this.sureBtn.id = "idNextstep";
            //this.sureBtn.style.display = "none";
            
            //隐藏提交
            this.submitBtn = this.crEle(this.btnBar);
            this.submitBtn.className = "btnbar idBtnsubmit";
            this.submitBtn.innerHTML = "确 定";
            this.submitBtn.id = "idBtnsubmit";
            
            //this.okBtn = this.crEle(this.btnBar);
            //this.okBtn.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            //this.okBtn.className = "btnbar playstep";
            //this.okBtn.style.display = "none";
            /*this.okBtn.onmouseover=function(){
            	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.okBtn.onmouseout=function(){
                	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/
            //this.okBtn.innerHTML = "播 放";
            
            //this.backBtn = this.crEle(this.btnBar);
            //this.backBtn.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            //this.backBtn.className = "btnbar prestep";
            //this.backBtn.style.display = "none";
            /*this.backBtn.onmouseover=function(){
            	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.backBtn.onmouseout=function(){
                	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/
            //this.backBtn.innerHTML = "上 一 步";
            
            //this.successBtn = this.crEle(this.btnBar);
            //this.backBtn.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            //this.successBtn.className = "btnbar success";
           // this.successBtn.style.display = "none";
            /*this.backBtn.onmouseover=function(){
            	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.backBtn.onmouseout=function(){
                	this.style.cssText = "margin-right:15px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/
            //this.successBtn.innerHTML = " 确 定 ";
            
            
            this.cancelBtn = this.crEle(this.btnBar);
            //this.cancelBtn.style.cssText = "width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            this.cancelBtn.className = "btnbar cancel";
            this.cancelBtn.innerHTML = "取 消";
            /*this.cancelBtn.onmouseover=function(){
            	this.style.cssText = "width:80px;float:right;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.cancelBtn.onmouseout=function(){
                	this.style.cssText = "width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/
            
            //this.addEvents(this.sureBtn,this.backBtn,this.okBtn,this.cancelBtn,this.successBtn,this.submitBtn);
            this.addEvents(this.cancelBtn,this.submitBtn);

        }
        //this.sureBtn.style.display = "none";
        this.dialog.style.display = 'block';
        this.mask.style.display = 'block';
        //this.sureFn = sureFn;
        this.closeFn = closeFn;
        //this.backFn = backFn;
        //this.okFn = okFn;
        //this.successFn = successFn;
        this.submitFn=submitFn;
        var ifm = document.createElement('iframe');
        this.inner.insertBefore(ifm,this.btnBar);
        ifm.style.cssText = "width:750px;height:400px;overflow:hidden;border:none;";
        /*ifm.onload  = function (){       console.log(111111111111);
            var doc = ifm.contentWindow.document;
            ifm.style.width = doc.documentElement.scrollWidth + 'px';   console.log(111111111111+'        '+doc.documentElement.scrollWidth);
            ifm.style.height = doc.documentElement.scrollHeight + 'px';
        };*/
        ifm.src = url;
        this.dialog.clientHeight = 454;
        var w,h;
        w = this.dialog.clientWidth;
        h = this.dialog.clientHeight;//554
        this.dialog.style.cssText = "margin-left:"+(-w/2)+"px;margin-top:"+(-h/2)+"px";
        
    },
    close : function (){
        var ifm = this.inner.getElementsByTagName('iframe')[0];
        if(ifm){
            ifm.onload = null;
            this.inner.removeChild(ifm);
        }
        this.dialog.style.display = 'none';
        this.mask.style.display = 'none';
    },
    addEvents:function (c,sb){
        var that = this;
        this.closeBtn.onclick = function (){
            if(that.closeFn)that.closeFn();
            //s.style.display = "block";
            c.style.display = "block";
           // b.style.display = "none";
            //o.style.display = "none";
            //su.style.display = "none";
            sb.style.display = "block";
            that.close();
        };
        this.cancelBtn.onclick = function (){
            if(that.closeFn)that.closeFn();
            //s.style.display = "block";
            c.style.display = "block";
           // b.style.display = "none";
           // o.style.display = "none";
            sb.style.display = "block";
           // su.style.display = "none";
            that.close();
        };
        //this.sureBtn.onclick = function (){
        //    if(that.sureFn)
        //    var bl = that.sureFn(that.inner.getElementsByTagName('iframe')[0].contentWindow);
        //    if(bl==false){
        //
        //    }else{
        //    	s.style.display = "none";
        //        b.style.display = "block";
        //        o.style.display = "block";
        //        sb.style.display = "none";
        //        c.style.display = "block";
        //    }
        //};
        //this.backBtn.onclick = function (){
        //	b.style.display = "none";
        //	if(that.backFn)that.backFn(that.inner.getElementsByTagName('iframe')[0].contentWindow);
        //	 s.style.display = "block";
        //	 c.style.display = "block";
        //     b.style.display = "none";
        //     o.style.display = "none";
        //     sb.style.display = "none";
        //     su.style.display = "none";
        //};
        //this.okBtn.onclick = function (){
        //	if(that.okFn)
        //	var cl = that.okFn(that.inner.getElementsByTagName('iframe')[0].contentWindow);
        //	if(cl==false){
        //
        //    }else{
	     //   	s.style.display = "none";
	     //       b.style.display = "none";
	     //       o.style.display = "none";
	     //       sb.style.display = "none";
	     //       c.style.display = "none";
	     //       su.style.display = "block";
        //    }
        //};
        //this.successBtn.onclick = function (){
        //	if(that.closeFn)that.closeFn();
        //    s.style.display = "block";
        //    c.style.display = "block";
        //    b.style.display = "none";
        //    o.style.display = "none";
        //    sb.style.display = "block";
        //    su.style.display = "none";
        //    that.close();
        //};
        /*this.mask.onclick = function (){
            if(that.closeFn)that.closeFn();
            s.style.display = "block";
            c.style.display = "block";
            b.style.display = "none";
            o.style.display = "none";
            sb.style.display = "none";
            su.style.display = "none";
            that.close();
        }*/

        this.submitBtn.onclick = function (){
        	if(that.submitFn){
                //ok(that.inner.getElementsByTagName('iframe')[0].contentWindow);
                var cl = that.submitFn(that.inner.getElementsByTagName('iframe')[0].contentWindow);
                //alert(cl)
            }

            if(!cl){

            }
                //if(cl==false){
                //
                //}else{
    	        	//s.style.display = "none";
    	         //   b.style.display = "none";
    	         //   o.style.display = "none";
    	         //   sb.style.display = "none";
    	         //   c.style.display = "none";
    	         //   su.style.display = "block";
                //}
        };

    }
};
