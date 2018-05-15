/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-18
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */

var winh = document.body.offsetHeight;
//alert(winh)

DSS.MdDialog = {
    crEle : function (p){
        var el = document.createElement('div');
        p.appendChild(el);
        return el;
    },
    open : function (url,sureFn,closeFn){
    	
    	
        if(!this.dialog){
            this.dialog = this.crEle(document.body);
            this.dialog.style.cssText = "position:absolute;left:50%;top:50%;z-index:999;";

            this.mask = this.crEle(document.body);
            this.mask.style.cssText = "position:absolute;top:0;left:0;width:100%;height:100%;background-color:rgba(0,0,0,0.3);z-index:99;";

            this.inner = this.crEle(this.dialog);
            this.inner.style.cssText = "position: fixed;margin-left:-350px;background-color:#ffffff;width:700px;"+"margin-top:"+(-553/2)+"px";
            
            this.closeBtn = this.crEle(this.inner);
            this.closeBtn.style.cssText = "position:absolute;width:15px;height:15px;border-radius:100px;top:10px;right:10px;z-index:50;font-size:28px;color:#FFFFFF;cursor:pointer;background:url('/image/frontend/news/04.png') no-repeat";
            this.closeBtn.innerHTML = '';
            this.closeBtn.title = '关闭';

            this.btnBar = this.crEle(this.inner);
            this.btnBar.className = "popupbtn";

            this.sureBtn = this.crEle(this.btnBar);
            this.sureBtn.className = "left";
            //this.sureBtn.style.cssText = "margin-left:195px;width:80px;float:left;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            this.sureBtn.innerHTML = "确 定";
            /*this.sureBtn.onmouseover=function(){
            	this.style.cssText = "margin-left:195px;width:80px;float:left;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.sureBtn.onmouseout=function(){
                	this.style.cssText = "margin-left:195px;width:80px;float:left;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/

            this.cancelBtn = this.crEle(this.btnBar);
            this.cancelBtn.className = "right";
            //this.cancelBtn.style.cssText = "margin-right:170px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            this.cancelBtn.innerHTML = "取 消";
            /*this.cancelBtn.onmouseover=function(){
            	this.style.cssText = "margin-right:170px;width:80px;float:right;background: none repeat scroll 0% 0% #46AF4B;color: #FFFFFF;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
            	};
            this.cancelBtn.onmouseout=function(){
                	this.style.cssText = "margin-right:170px;width:80px;float:right;background: none repeat scroll 0% 0% #FFFFFF;color: #000000;border: 2px solid #46AF4B;filter: none;text-align:center;vertical-align:middle;line-height:40px;border-radius:5px;";
                	};*/

            this.addEvents();
        }
        this.dialog.style.display = 'block';
        this.mask.style.display = 'block';
        this.sureFn = sureFn;
        this.closeFn = closeFn;
        

        var ifm = document.createElement('iframe');
        this.inner.insertBefore(ifm,this.btnBar);
        ifm.style.cssText = "width:700px;height:500px;overflow:hidden;border:none;";
        /*ifm.onload  = function (){       console.log(111111111111);
            var doc = ifm.contentWindow.document;
            ifm.style.width = doc.documentElement.scrollWidth + 'px';   console.log(111111111111+'        '+doc.documentElement.scrollWidth);
            ifm.style.height = doc.documentElement.scrollHeight + 'px';
        };*/
        ifm.src = url;
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
    addEvents:function (){
        var that = this;
        this.closeBtn.onclick = function (){
            if(that.closeFn)that.closeFn();
            that.close();
        };
        this.cancelBtn.onclick = function (){
            if(that.closeFn)that.closeFn();
            that.close();
        };
        this.sureBtn.onclick = function (){
            if(that.sureFn)
           // console.log("-----"+that.inner.getElementsByTagName('iframe')[0]);
           // console.log(that.inner.getElementsByTagName('iframe')[0].contentWindow)
            	var ret = that.sureFn(that.inner.getElementsByTagName('iframe')[0].contentWindow);
            if(ret != ""){
            	that.close();
            }
        };
        //点击其他区域关闭弹出层
        /*this.mask.onclick = function (){
            if(that.closeFn)that.closeFn();
            that.close();
        }*/
    }
};
