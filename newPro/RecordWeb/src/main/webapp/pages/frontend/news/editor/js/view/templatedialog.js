/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-18
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */

DSS.MdTempDialog = {
    crEle : function (p){
        var el = document.createElement('div');
        el.className = "publishdiv";
        p.appendChild(el);
        return el;
    },
    open : function (url,sureFn,closeFn,successFn){
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
            
            this.sureBtn = this.crEle(this.btnBar);
            this.sureBtn.className = "btnbar success";
            this.sureBtn.innerHTML = "确认";

            this.cancelBtn = this.crEle(this.btnBar);
            this.cancelBtn.className = "btnbar cancel";
            this.cancelBtn.innerHTML = "取消";
            
            this.successBtn = this.crEle(this.btnBar);
            this.successBtn.className = "btnbar success";
            this.successBtn.innerHTML = "确认";
            this.successBtn.style.display="none";
            
            this.addEvents(this.sureBtn,this.cancelBtn,this.successBtn);
        }
        this.dialog.style.display = 'block';
        this.mask.style.display = 'block';
        this.sureFn = sureFn;
        this.closeFn = closeFn;

        var ifm = document.createElement('iframe');
        /*this.inner.appendChild(ifm);*/
        this.inner.insertBefore(ifm,this.btnBar);
        ifm.src = url;
        ifm.className = "tempifram";
        
        var w,h;
        w = this.dialog.clientWidth;
        h = this.dialog.clientHeight;
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
    addEvents:function (s,c,su){
        var that = this;
        this.cancelBtn.onclick = function (){
        	that.close();
        };
        this.closeBtn.onclick = function (){
        	that.close();
        };
        this.sureBtn.onclick = function (){
        	 if(that.sureFn)
             var bl = that.sureFn(that.inner.getElementsByTagName('iframe')[0].contentWindow);
        	 if(bl==false){
        		
             }else {
            	 c.style.display="none";
	        	 s.style.display="none";
	        	 su.style.display="block";
             }
        };
        this.successBtn.onclick = function (){
        	 c.style.display="block";
        	 s.style.display="block";
        	 su.style.display="none";
        	that.close();
        };
        // 点击弹出框以外的区域  弹出框会自动关闭
        /*this.mask.onclick = function (){
            if(that.closeFn)that.closeFn();
            that.close();
        }*/
    }
};