/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-24
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */

DSS.PageOpr = {
    box:DSS.Options.el.pageBoxIn,
    pBox : DSS.Options.el.pageBoxIn.parentNode,
    hintEl : DSS.Options.el.pgDragHint,
    itemClass:DSS.Options.eClass.pageItem,
    itemInClass :DSS.Options.eClass.pgItemIn,
    imgClass : DSS.Options.eClass.pageImg,
    checkClass : DSS.Options.eClass.pageCheck,
    util : DSS.util,
    tcrId : null, // 在页面的时间上点击delete键
    init : function (list){
        this.initList(list);
        this.addEvents();
    },
    initList : function (list){
         for(var i=0;i<list.length;i++){
             var item = this.creItem(list[i]);
             //item.innerHTML = 'page_'+(i+1);
         }
    },
    creItem : function (obj,idx){
        if(!obj.time)obj.time = 300;
        var item = document.createElement('li');
        item.className = this.itemClass;
        this.setPageId(item,obj.pid);
        var chs = this.box.children;
        if(typeof(idx)=='undefined'||chs.length<idx){
            this.box.appendChild(item);
        }else{
            this.box.insertBefore(item,chs[idx-1]);
        }

        var inner = document.createElement('div');
        inner.className = this.itemInClass;
        item.appendChild(inner);

        var img = document.createElement('div');
        img.style.backgroundImage = 'url('+obj.path+')';
        img.className = this.imgClass;
        inner.appendChild(img);

        var foot = document.createElement('div');
        foot.className = DSS.Options.eClass.pageFoot;
        item.appendChild(foot);

        var span = document.createElement('span');
        DSS.util.setInnerText(span,this.findIdxByItem(item));
        foot.appendChild(span);
        
        var pre = document.createElement('img');
        pre.className = DSS.Options.eClass.pagePre;
        pre.src='images/hide.png';
        pre.id=obj.pid;
        foot.appendChild(pre);

        var tm = document.createElement('div');
        tm.className = DSS.Options.eClass.pageTime;
        foot.appendChild(tm);

		// 在普通编辑页面 左侧的page页下方的播放时长为可输入状态          在快速发布页面 page页的播放时长为只读状态
        if(DSS.util.byId('quick').value=='1'){
        	// label的形式 显示每页播放的时长
//       	 var timer = document.createElement('label');
//       	 timer.style.top = '2px';
//       	 timer.style.paddingLeft = '25px';
//           DSS.util.setInnerText(timer,obj.time+'秒');
//           tm.appendChild(timer);
        	
        	var tmEdit = document.createElement('input');
	        tmEdit.className = DSS.Options.eClass.pageTimeEdit;
	        tmEdit.type="text";
	        tmEdit.value = obj.time;
	        tmEdit.disabled = 'disabled';
	        tmEdit.id = 'timeinput'+this.findIdxByItem(item);
	        tm.appendChild(tmEdit);
       }else{
	        var tmSet = document.createElement('div');
	        tmSet.className = DSS.Options.eClass.pageTimeSet;
	        tm.appendChild(tmSet);
	        
	        var tmEdit = document.createElement('input');
	        tmEdit.className = DSS.Options.eClass.pageTimeEdit;
	        tmEdit.type="text";
	        tmEdit.value = obj.time;
	        tmEdit.style.display = '';
	        tmEdit.id = 'timeinput'+this.findIdxByItem(item);
	        tm.appendChild(tmEdit);
       }

        var miao = document.createElement('label');
        DSS.util.setInnerText(miao,'秒');
        tm.appendChild(miao);
        return item;
    },
    getPages : function(){
    	var chs = this.box.children;
    	return chs;
    },
    initPageNum : function (){
        var chs = this.box.children;
        if(!chs||chs.length==0)return;
        for(var i=0;i<chs.length;i++){
            var span = chs[i].getElementsByTagName('span')[0];
            DSS.util.setInnerText(span,i+1);
        }
    },
    setPageId : function (item,id){
        id = this.cvt2PageId(id);
        item.id = id;
    },
    getPageId : function (item){
        return this.cvt2DataId(item.id);
    },
    cvt2PageId : function (id){
        var newId;
        if(typeof(id)=='string'){
            newId=id.indexOf('pg_')>=0?id:'pg_'+id;
        }else{
            newId = 'pg_'+id;
        }
        return newId;
    },
    cvt2DataId : function (id){
        var newId;
        if(typeof(id)=='string'){
            newId=id.replace('pg_','');
        }else{
            newId = id;
        }
        return newId;
    },
    findPgIdByIdx : function (idx){
        var item = this.box.children[idx-1];
        return this.cvt2DataId(item.id);
    },
    findIdxById : function (id){
        id = this.cvt2PageId(id);
        var chs = this.box.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i].id==id)return i+1;
        }
    },
    findIdxByItem : function (item){
        var chs = item.parentNode.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i]==item)return i+1;
        }
    },
    findPageById : function (id){
        id = this.cvt2PageId(id);
        return this.util.byId(id);
    },
    findPItem : function (e){
        var tar = e.target || e.srcElement || e;
        var bd = document.getElementsByTagName('body')[0];
        while(tar!=bd&&tar){
            if(this.util.cClass(tar,this.itemClass)){
                return tar;
            }
            tar = tar.parentNode;
        }
    },
    getCurId : function (){
        var id = DSS.Options.cur.page;
        return this.cvt2DataId(id);
    },
    setTcrId : function (id){
    	this.tcrId = id;
    },
    getTcrId : function (){
    	 return this.tcrId;
    },
    setCurId : function (id){
        DSS.Options.cur.page = this.cvt2DataId(id);
        var chs = this.box.children;
        for(var i=0;i<chs.length;i++){
            this.util.dClass(chs[i],this.checkClass);
        }
        var item = this.findPageById(id);
        if(item)this.util.aClass(item,this.checkClass);
    },
    setCurIdx : function (idx){
        var chs = this.box.children;
        if(idx>chs.length)return;
        var item = chs[idx-1];
        this.setCurItem(item);
    },
    setCurItem : function (item){
        this.setCurId(item.id);
    },
    getCurItem : function (){
        var id = DSS.Options.cur.page;
        return this.findPageById(id);
    },
    getCurIdx : function (){
        var id = DSS.Options.cur.page;
        return this.getIdxById(id);
    },
    getIdxById : function (id){
        var item = this.findPageById(id);
        var chs = this.box.children;
        for(var i=0;i<chs.length;i++){
            if(chs[i]==item)return i+1;
        }
        return -1;
    },
    getItemByIdx : function (idx) {
        var chs = this.box.children;
        if(idx>chs.length)return;
        var item = chs[idx];
        return item;
    },
    renewAllPageIdx : function () {
        var chs = this.box.children;
        for (var i = 0; i < chs.length; i++) {
            var item = chs[i];
            var foot = item.getElementsByClassName(DSS.Options.eClass.pageFoot)[0];
            var span = foot.getElementsByTagName("span")[0];
            span.innerHTML = (i + 1);
        }
    },
    addPage : function (o,idx){
        var item = this.creItem(o,idx);
        var par = this.box.parentNode,that = this;
        var timeEdit = par.getElementsByClassName(DSS.Options.eClass.pageTimeEdit);
        // 只有在page页的时间上 才触发获取焦点的事件
        for(var i = 0;i<timeEdit.length ;i++){
        	 this.util.events(timeEdit[i],'focus',function(e){
                 that.focus(e);
             });
             this.util.events(timeEdit[i],'blur',function(e){
                 that.blur(e);
             });
        }
        //item.innerHTML = 'page_'+idx;
        this.setCurId(o.pid);
    },
    delPage : function (id){
        var idx = typeof(id)=='undefined'?this.getCurIdx():this.getIdxById(id);
        var item = this.findPageById(id);
        this.box.removeChild(item);
        var chs = this.box.children;
        if(!chs||chs.length==0)return;
        var delPage = document.createElement('div');
        delPage.id = DSS.MakeId.mkPageId();
        DSS.Options.el.delPgsDiv.appendChild(delPage);
        DSS.util.moveAllChildren(DSS.Options.el.mainBoxIn,delPage);
        idx = (idx>chs.length)?chs.length:idx;
        this.setCurIdx(idx);
        return delPage.id;
    },
    setSpPosition : function(item){
        with (item.style){
            position = 'absolute';
            marginLeft = 0;
            marginTop = 0;
            opacity = 0.6;
        }
    },
    delSpPosition : function (item){
        with (item.style){
            position = '';
            marginLeft = '';
            marginTop = '';
        }
    },
    insertPageByIdx : function (id,idx){
        var item = this.findPageById(id);
        this.box.removeChild(item);
        chs = this.box.children;
        if(idx<=chs.length){
            this.box.insertBefore(item,chs[idx-1]);
        }else{
            this.box.appendChild(item);
        }
    },
    setSpXY : function (item,x,y){
        with (item.style){
            left = x + 'px';
            top = y + 'px';
        }
    },
    getIdxByY : function (y){
        var chs = this.box.children;
        var h = chs[0].offsetHeight*0.5;
        var ln = chs.length;
        if(chs.length==1)return 0;
        if(y<=chs[0].offsetTop+h){
            return 0;
        }else if(y>chs[ln-1].offsetTop +h){
            return ln;
        }else{
            for(var i=1;i<ln;i++){
                var obj0 = chs[i-1];
                var obj1 = chs[i];
                if(y>obj0.offsetTop + h&&y<=obj1.offsetTop+h){
                    return i;
                }
            }
        }
        return -1;
    },
    setHintPos : function (idx){
        this.hintEl.style.display = 'block';
        var chs = this.box.children;
        var h = chs[0].offsetTop*0.5;
        var oh = chs[0].offsetHeight;
        if(idx<=chs.length-1){
            this.hintEl.style.top = chs[idx].offsetTop - h +'px';
        }else{
            this.hintEl.style.top =  chs[chs.length-1].offsetTop + oh + h +'px';
        }
    },
    mousedwn : function (e){
        var item = this.findPItem(e);
        if(!item)return;
        var tar = e.target || e.srcElement;
        if(DSS.util.cClass(tar,DSS.Options.eClass.pageTimeEdit)||DSS.util.cClass(tar,DSS.Options.eClass.pageTimeSet)||DSS.util.cClass(tar,DSS.Options.eClass.pagePre)){
            //如果点在时间修改区，那么默认为是需要修改时间，不触发拖放事件
            return;
        }
        this.dragObj = item.cloneNode(true);
        this.dragItem = item;
        this.pBox.appendChild(this.dragObj);
        this.startPos = this.util.getMs2Ele(item,e);
        this.setSpPosition(this.dragObj);
        this.startPosToBox = this.util.getMs2Ele(this.pBox,e);
        this.setSpXY(this.dragObj,this.startPosToBox.x - this.startPos.x,this.startPosToBox.y - this.startPos.y - 8);
        this.oldIdx = this.findIdxById(this.dragItem.id);
    },
    mousemv : function (e){
        if(!this.dragObj)return;
        var pos = this.util.getMs2Ele(this.pBox,e);
        this.setSpXY(this.dragObj,this.startPosToBox.x - this.startPos.x,pos.y - this.startPos.y);
        var idx  = this.getIdxByY(pos.y);
        if(pos.x!=this.startPosToBox.x||pos.y!=this.startPosToBox.y){
            this.setHintPos(idx);
        }
    },
    focus:function (e){
    	var clickItem = this.findPItem(e);
        var timeEdit = clickItem.getElementsByClassName(DSS.Options.eClass.pageTimeEdit)[0];
        DSS.PageOpr.setTcrId(timeEdit.id);
        
        this.setCurItem(clickItem);
        var newId = clickItem.id.split('_')[1];
        DSS.Controller.execute('CMD_SELECT_PAGE','',newId);
    },
    blur:function (e){
    	var clickItem = this.findPItem(e);
    	this.setCurItem(DSS.util.byId(clickItem.id));
    	var that = this;
    	 
     	var timeEdit = clickItem.getElementsByClassName(DSS.Options.eClass.pageTimeEdit)[0];
     	var edv = timeEdit.value.replace(/[^\d]/g,'');
     	if(edv == '' || edv == '0' || edv == 0){
     		timeEdit.value = 30;
     	}else{
     		edv = parseInt(edv,10);
         	if(edv<30)edv=30;
//            else if(edv>86400)edv=86400;
            timeEdit.value = edv;	 
     	}
     	
     	DSS.PageOpr.setTcrId(null);
        var oldVal = "";
        var newVal = timeEdit.value;
        var item = that.findPItem(clickItem);
        DSS.Controller.execute('CMD_UPDATE_PAGE_TIME',that.getPageId(item),oldVal,newVal);
    },
    mouseup : function (e){
    	 var tar = e.target || e.srcElement;
    	 // 普通编辑页面 单页预览 读取的是edit目录下的     快速发布页面 单页预览 读取的是publish目录下的
    	 if(DSS.util.byId('quick').value=='1'){
    		 if(DSS.util.cClass(tar,DSS.Options.eClass.pagePre)){
        		 this.setCurItem(DSS.util.byId("pg_"+tar.id));
             	 DSS.Controller.execute('CMD_SINGLE_PAGE_PREVIEW_Q',this.getCurItem());
             }
    	 }else{
    		 if(DSS.util.cClass(tar,DSS.Options.eClass.pagePre)){
        		 this.setCurItem(DSS.util.byId("pg_"+tar.id));
             	 DSS.Controller.execute('CMD_SINGLE_PAGE_PREVIEW',this.getCurItem());
             }
    	 }
        if(!this.dragObj)return;
        var endPs = this.util.getMs2Ele(this.pBox,e);
        if(endPs.x==this.startPosToBox.x&&endPs.y==this.startPosToBox.y){
            //选择页
            var oldId = this.getCurId();
            this.setCurItem(this.dragItem);
            var newId = this.getPageId(this.dragItem);
            DSS.Controller.execute('CMD_SELECT_PAGE',oldId,newId);
        }else{
            //拖放事件
            // 快速发布页面 page页是不允许进行拖动改变顺序的
        	if(DSS.util.byId('quick').value!='1'){
	        	var idx  = this.getIdxByY(endPs.y);
	            var chs = this.box.children;
	            if(idx<=chs.length-1){
	                this.box.insertBefore(this.dragItem,chs[idx]);
	            }else{
	                this.box.appendChild(this.dragItem);
	            }
	            var newIdx = this.findIdxById(this.dragItem.id);
            	DSS.Controller.execute('CMD_MOVE_PAGE',this.cvt2DataId(this.dragItem.id),this.oldIdx,newIdx);
            }
        }
        this.dragItem = null;
        this.dragObj.parentNode.removeChild(this.dragObj);
        this.dragObj = null;
        this.hintEl.style.display = '';
    },
    addEvents : function (){
        var par = this.box.parentNode,that = this;
        var timeEdit = par.getElementsByClassName(DSS.Options.eClass.pageTimeEdit);
        this.util.events(par,'mousedown',function(e){
            that.mousedwn(e);
        });
        this.util.events(document.body,'mousemove',function(e){
            that.mousemv(e);
        });
        this.util.events(document.body,'mouseup',function(e){
            that.mouseup(e);
        });
        // 只有在page页的时间上 才触发获取焦点的事件
        for(var i = 0;i<timeEdit.length ;i++){
        	 this.util.events(timeEdit[i],'focus',function(e){
                 that.focus(e);
             });
             this.util.events(timeEdit[i],'blur',function(e){
                 that.blur(e);
             });
        }
    }

};
