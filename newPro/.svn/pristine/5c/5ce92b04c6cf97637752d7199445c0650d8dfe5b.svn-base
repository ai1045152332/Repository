/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-18
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */

DSS.MdPagerateDialog = {
    crEle : function (p){
        var el = document.createElement('div');
        el.className = "publishdiv";
        p.appendChild(el);
        return el;
    },
    open : function (url,sureFn,closeFn){
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
            
            this.addEvents(this.sureBtn,this.cancelBtn);
        }
        this.dialog.style.display = 'block';
        this.mask.style.display = 'block';
        this.sureFn = sureFn;
        this.closeFn = closeFn;

        var ifm = document.createElement('iframe');
        /*this.inner.appendChild(ifm);*/
        this.inner.insertBefore(ifm,this.btnBar);
        ifm.style.cssText = "width:100%;height:186px;overflow:hidden;border:none;";
        ifm.src = url;
        
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
    addEvents:function (s,c){
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
        	if(bl!=''){
        		that.close();
        	}
        };
        //  点击弹出框以外的区域  弹出框会自动关闭
        /*this.mask.onclick = function (){
            if(that.closeFn)that.closeFn();
            that.close();
        }*/
    }
};