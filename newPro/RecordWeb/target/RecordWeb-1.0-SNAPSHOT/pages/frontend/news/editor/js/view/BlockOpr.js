/**
 * Created with JetBrains WebStorm. User: hht Date: 13-12-13 Time: 下午2:45 To
 * change this template use File | Settings | File Templates.
 */

DSS = DSS || {};
DSS.BlockOpr = {
	prptBarBox : DSS.Options.el.prptBar,
	box : DSS.Options.el.mainBoxIn,
	mainBoxPrt : DSS.Options.el.mainBox,
	delBox : DSS.Options.el.delElsDiv,
	cutBox : DSS.Options.el.cutElsDiv,
	dragMaskObj : DSS.Options.el.editElMask,
	ctrlMaskObj : DSS.Options.el.ctrEleMask,
	blockClass : DSS.Options.eClass.editBlock,
	editBlockClass : DSS.Options.eClass.editBlock,
	editMaskClass : DSS.Options.eClass.editElMask,
	ctrlMaskClass : DSS.Options.eClass.ctrElMask,
	resizeClass : DSS.Options.eClass.BR_rszEl + ' '
			+ DSS.Options.eClass.B_rszEl + ' ' + DSS.Options.eClass.R_rszEl,
	rszPsClass : DSS.Options.eClass.TL_rszEl + ' ' + DSS.Options.eClass.T_rszEl
			+ ' ' + DSS.Options.eClass.L_rszEl + ' '
			+ DSS.Options.eClass.BL_rszEl + ' ' + DSS.Options.eClass.TR_rszEl,
	prptBar : null,
	util : DSS.util,
	curObj : null,
	prptUrlList : DSS.Options.menuItem,
	mayMove : true,
	imw:null,
	imh:null,
	setImw : function(imw) {
		this.imw = imw;
	},
	getImw : function(imw) {
		return this.imw;
	},
	setImh : function(imh) {
		this.imh = imh;
	},
	getImh : function(imh) {
		return this.imh;
	},
	getPrptBar : function(prptBar) {
		this.prptBar = prptBar;
	},
	setCurObj : function(curObj) {
		if(curObj == null){
			return false;
		}else{
			if (this.curObj == curObj)
				if(this.curObj.type_id != '18'){ // PPT
					return;
				}
			this.curObj = curObj;
			if (!this.dragMask) {
				this.dragMask = this.dragMaskObj.cloneNode(true);
				this.mainBoxPrt.appendChild(this.dragMask);
			}
			this.dragMask.item_id = this.curObj.id;
			this.maskMove2Obj();
			this.loadPrptBar(this.curObj.type_id);
		}
	},
	getCurObj : function() {
		return this.curObj;
	},
	clearAllBlock : function() {
		this.clearMask();
		this.clearDragAndResize();
		this.util.removeAllChildren(this.box);
	},
	initAllBlock : function(list) {
		this.clearMask();
		this.clearAllBlock();
		this.loadPrptBar();
		for ( var i = 0; i < list.length; i++) {
			DSS.EditComponent.initBlock(list[i]);
//			DSS.Controller.snapPage();  // 点击单个左侧缩略图的时候  会进行绘图  以此来保证左侧的缩略图和中间制作区域保持一致
		}
		DSS.Controller.snapPage(); // 新创建一个节目 进入节目制作页 左侧第一个page页是带有黑色背景的缩略图
	},
	addBlock : function(typeId) {
		return DSS.EditComponent.addBlock(typeId);
	},

	addCtrlMask : function(item) {
		if (this.curObj == item)
			return;
		var ctrlMask = this.ctrlMaskObj.cloneNode(true);
		this.mainBoxPrt.appendChild(ctrlMask);
		ctrlMask.item_id = item.id;
		var eObj = item;
		with (ctrlMask.style) {
			left = eObj.offsetLeft + 'px';
			top = eObj.offsetTop + 'px';
			width = eObj.clientWidth + 'px';
			height = eObj.clientHeight + 'px';
			display = 'block';
		}
	},

	getSelectEles : function() {
		var zu = this.countSelectIds();
		if (!zu || zu.length <= 0)
			return;
		var li = [];
		for ( var i = 0; i < zu.length; i++) {
			li.push(this.util.byId(zu[i]));
		}
		return li;
	},
	getCutIds : function() {
		return this.cutIds;
	},
	countSelectIds : function() {
		var zu = [];
		if (this.curObj)
			zu.push(this.curObj.id);
		var chs = this.mainBoxPrt.getElementsByClassName(this.ctrlMaskClass);
		for ( var i = 0; i < chs.length; i++) {
			zu.push(chs[i].item_id);
		}

		return zu;
	},
	getAllIds : function(id) {
		var par = id ? this.box : this.util.byId(id);
		var zu = [];
		for ( var i = 0; i < par.children.length; i++) {
			zu.push(par.children[i].id);
		}
		return zu;
	},
	cutEles : function() {
		this.cutIds = this.countSelectIds();
		this.moveElsToDel(this.cutIds);
		this.isCopy = null;
		return this.cutIds;
	},
	pasteEles : function() {
		var ids;
		if (this.isCopy) {
			this.moveElsToEdit(this.cutIds);
			ids = this.cutIds;
		} else {
			this.moveElsToEdit(this.cutIds);
			ids = this.cutIds;
			this.cutIds = null;
		}
		return ids;
	},
	copyEles : function() {
		var ids = this.countSelectIds();
		if (!ids)
			return;
		ids = this.copy(ids);
		this.moveElsToDel(ids);
		this.cutIds = ids;
		this.isCopy = 1;
		return this.cutIds;
	},
	copy : function(ids) {
		var cutIds = DSS.MakeId.mkBlockIds(ids.length);
		for ( var i = 0; i < ids.length; i++) {
			var e = this.util.byId(ids[i]);
			DSS.EditComponent.copyBlock(e, cutIds[i]);
		}
		return cutIds;
	},
	moveElsToEdit : function(cutIds) {
		if (!cutIds)
			return;
		for ( var i in cutIds) {
			this.moveToEdit(cutIds[i]);
		}
	},
	moveElsToDel : function(cutIds) {
		if (!cutIds)
			return;
		for ( var i in cutIds) {
			this.moveToDel(cutIds[i]);
		}
	},
	moveToDel : function(id) {
		var item = id ? this.util.byId(id) : this.curObj;
		this.delBox.appendChild(item);
		if (this.curObj == item) {
			this.clearMask();
			this.loadPrptBar();
		}
	},
	moveToEdit : function(id) {
		var item = id ? this.util.byId(id) : this.curObj;
		this.box.appendChild(item);
		this.setCurObj(item);
	},
	clearMask : function() {
		if (this.dragMask) {
			this.dragMask.parentNode.removeChild(this.dragMask);
			this.dragMask = null;
			this.curObj = null;
		}
		this.clearCtrlMask();
	},
	clearCtrlMask : function() {
		this.util.removeAllChsByClass(this.mainBoxPrt, this.ctrlMaskClass);
	},
	setLeft : function(n) {
		DSS.EditComponent.setItemL(this.curObj, n);
		this.dragMask.style.left = n + 'px';
	},
	setEleLeft : function(el, n) {
		DSS.EditComponent.setItemL(el, n);
	},
	setTop : function(n) {
		DSS.EditComponent.setItemT(this.curObj, n);
		this.dragMask.style.top = n + 'px';
	},
	setEleTop : function(el, n) {
		DSS.EditComponent.setItemT(el, n);
	},
	setW : function(n) {
		this.curObj.editW = n;
		DSS.EditComponent.setItemW(this.curObj, n);
		this.dragMask.style.width = n + 'px';
	},
	setEleW : function(el, n) {
		el.editW = n;
		DSS.EditComponent.setItemW(el, n);
	},
	setH : function(n) {
		this.curObj.editH = n;
		DSS.EditComponent.setItemH(this.curObj, n);
		this.dragMask.style.height = n + 'px';
	},
	setEleH : function(el, n) {
		el.editH = n;
		DSS.EditComponent.setItemH(el, n);
	},
	getLeft : function() {
		return this.curObj.offsetLeft;
	},
	getTop : function() {
		return this.curObj.offsetTop;
	},
	getW : function() {
		return DSS.EditComponent.getProperty(this.curObj, 'width');
	},
	getH : function() {
		return DSS.EditComponent.getProperty(this.curObj, 'height');
	},
	setPos : function(x, y) {
		var s = this.curObj;
		//console.info(s.children[0].style.cssText.indexOf('opacity'));
		if(s.children[0].style.cssText.indexOf('opacity')>-1){
			return false;
		}
		// 快速发布的状态下  不允许进行拖动操作
		if(DSS.util.byId('quick').value!='1'){
			this.setLeft(x);
			this.setTop(y);
		}
	},
	setElePos : function(el, x, y) {
		el.setLeft(x);
		el.setTop(y);
	},
	getPos : function() {
		return {
			x : this.curObj.offsetLeft,
			y : this.curObj.offsetTop
		};
	},
	setSize : function(w, h) {
		this.setW(w);
		this.setH(h);
	},
	setEleSize : function(el, w, h) {
		this.setEleW(el, w);
		this.setEleH(el, h);
	},
	getSize : function() {
		if (this.curObj.editW && this.curObj.editH)
			return {
				w : this.curObj.editW,
				h : this.curObj.editH
			};
		return {
			w : this.curObj.clientWidth,
			h : this.curObj.clientHeight
		};
	},
	setPosAndSize : function(x, y, w, h) {
		this.setPos(x, y);
		this.setSize(w, h);
	},
	getPosAndSize : function() {
		return {
			x : this.curObj.offsetLeft,
			y : this.curObj.offsetTop,
			w : this.curObj.clientWidth,
			h : this.curObj.clientHeight
		};
	},
	loadPrptBar : function(id) {
		var el = this.prptBarBox.getElementsByTagName('iframe')[0];
		if (id && this.prptUrlList[id]) {
			el.src = this.prptUrlList[id].prp;
		} else if (id) {
			el.src = 'page/property/commonProperty.html';
		} else {
			el.src = '';
		}

	},
	refreshPrptBar : function() {
		var cur = this.getCurObj();
		if (cur)
			this.loadPrptBar(cur.type_id);
		else
			this.loadPrptBar();
	},
	findPItem : function(e) {
		var tar = e.target || e.srcElement || e;
		var bd = document.getElementsByTagName('body')[0];
		while (tar != bd && tar) {
			if (this.util.cClass(tar, this.editBlockClass)) {
				return tar;
			} else if (this.util.cClass(tar, this.editMaskClass)) {
				return this.util.byId(tar.item_id);
			} else if (this.util.cClass(tar, this.ctrlMaskClass)) {
				return this.util.byId(tar.item_id);
			}
			tar = tar.parentNode;
		}
	},
	setProperty : function(name, value) {
		switch (name) {
		case 'xVal':
			this.setLeft(value);
			break;
		case 'yVal':
			this.setTop(value);
			break;
		case 'wVal':
			this.setW(value);
			break;
		case 'hVal':
			this.setH(value);
			break;
		default:
			DSS.EditComponent.setProperty(this.curObj, name, value);
		}
	},
	setEleProperty : function(el, name, value) {
		switch (name) {
		case 'xVal':
			this.setEleLeft(el, value);
			break;
		case 'yVal':
			this.setEleTop(el, value);
			break;
		case 'wVal':
			this.setEleW(el, value);
			break;
		case 'hVal':
			this.setEleH(el, value);
			break;
		default:
			DSS.EditComponent.setProperty(el, name, value);
		}
	},
	getProperty : function(name) {
		switch (name) {
		case 'xVal':
			return this.getLeft(name);
			break;
		case 'yVal':
			return this.getTop(name);
			break;
		case 'wVal':
			return this.getW(name);
			break;
		case 'hVal':
			return this.getH(name);
			break;
		default:
			return DSS.EditComponent.getProperty(this.curObj, name);
		}
	},
    getOffsetPos: function(item, e) {
        var scale = DSS.Options.view.scale ? DSS.Options.view.scale : 1.0;
        var bounds = {};
        if (item.getBoundingClientRect){
            bounds = item.getBoundingClientRect();
        }
        var mx = (e.clientX-bounds.left)/scale;
        var my = (e.clientY-bounds.top)/scale;
        if (mx < 0) mx = 0;
        if (mx > item.offsetWidth) mx = item.offsetWidth;
        if (my < 0) my = 0;
        if (my > item.offsetHeight) my = item.offsetHeight;

        return {
            x : mx,
            y : my
        }
    },
	setItemPos : function(item, e) {
		var pos = this.getOffsetPos(this.mainBoxPrt, e);//this.util.getMs2Ele(this.box, e);
		var x = this.startXY.x + pos.x - this.startPos.x;
		var y = this.startXY.y + pos.y - this.startPos.y;
		var size = this.getSize();
		var w = this.box.offsetWidth, h = this.box.offsetHeight;
		if (x < 0)
			x = 0;
		else if (parseInt(x, 10) + parseInt(size.w, 10) > parseInt(w, 10))
			x = w - size.w;
		if (y < 0)
			y = 0;
		else if (parseInt(y, 10) + parseInt(size.h, 10) > parseInt(h, 10))
			y = h - size.h;
		this.setPos(x, y);
        //console.log('mouse pos:('+pos.x+','+pos.y+'); item pos:('+x+','+y+')');
		if (DSS.PropertyBar)
			DSS.PropertyBar.setPos(x, y);
	},
	/**
	 * 是否是等比缩放
	 * 
	 */
	isGeoScale : function() {
		return this.curObj.type_id == '5' ? false : true;
	},
	setItemSize : function(item, e) {
		var pos = this.getOffsetPos(this.mainBoxPrt, e);//this.util.getMs2Ele(item, e);
		var sW, sH;
		if (this.dim == 'R') {
			sW = this.startSize.w - this.startPos.x + pos.x;
			sH = this.startSize.h;
		} else if (this.dim == 'B') {
			sW = this.startSize.w;
			sH = this.startSize.h - this.startPos.y + pos.y;
		} else if (this.dim == 'BR') {
			sW = this.startSize.w - this.startPos.x + pos.x;
			sH = this.startSize.h - this.startPos.y + pos.y;
		}
		if (sW < 1)
			sW = 1;
		if (sH < 1)
			sH = 1;
		if (this.isGeoScale() && this.dim.length > 1) {
			// 如果是等比缩放
			var size = this.getSize();
			sH = size.h * sW / size.w;
            if (this.startXY.y + sH > this.mainBoxPrt.offsetHeight) {
                sH = this.mainBoxPrt.offsetHeight - sY;
                sW = size.w * sH / size.h;
            }
		}
		this.setSize(sW, sH);
		if (DSS.PropertyBar)
			DSS.PropertyBar.setSize(sW, sH);
	},
	setItemSizeAndPos : function(item, e) {
		var pos = this.getOffsetPos(this.mainBoxPrt, e);//this.util.getMs2Ele(this.box, e);
		var sX, sY, sW, sH, dX, dY;
        var startR = this.startXY.x + this.startSize.w;
        var startB = this.startXY.y + this.startSize.h;

		if (this.dim == 'TL') {
            if (pos.x >= startR) pos.x = startR - 1;
            if (pos.y >= startB) pos.y = startB - 1;
            dX = pos.x - this.startPos.x;
            dY = pos.y - this.startPos.y;
			sX = this.startXY.x + dX;
			sY = this.startXY.y + dY;
			sW = this.startSize.w - dX;
			sH = this.startSize.h - dY;
		} else if (this.dim == 'T') {
            if (pos.y >= startB) pos.y = startB - 1;
            dY = pos.y - this.startPos.y;
			sX = this.startXY.x;
			sY = this.startXY.y + dY;
			sW = this.startSize.w;
			sH = this.startSize.h - dY;
		} else if (this.dim == 'TR') {
            if (pos.x <= this.startXY.x) pos.x = this.startXY.x + 1;
            if (pos.y >= startB) pos.y = startB - 1;
            dX = pos.x - this.startPos.x;
            dY = pos.y - this.startPos.y;
			sX = this.startXY.x;
			sY = this.startXY.y + dY;
			sW = this.startSize.w + dX;
			sH = this.startSize.h - dY;
		} else if (this.dim == 'L') {
            if (pos.x >= startR) pos.x = startR - 1;
            dX = pos.x - this.startPos.x;
			sX = this.startXY.x + dX;
			sY = this.startXY.y;
			sW = this.startSize.w - dX;
			sH = this.startSize.h;
		} else if (this.dim == 'BL') {
            if (pos.x >= startR) pos.x = startR - 1;
            if (pos.y <= this.startXY.y) pos.y = this.startXY.y + 1;
            dX = pos.x - this.startPos.x;
            dY = pos.y - this.startPos.y;
			sX = this.startXY.x + dX;
			sY = this.startXY.y;
            sW = this.startSize.w - dX;
            sH = this.startSize.h + dY;
		}
		if (sW < 1)
			sW = 1;
		if (sH < 1)
			sH = 1;
		if (this.isGeoScale() && this.dim.length > 1) {
			// 如果是等比缩放
			var size = this.getSize();
            var nW;
			var nH = size.h * sW / size.w;
			if (this.dim.indexOf('T') > -1) {
				sY = sH + sY - nH;
			}
			sH = nH;
            if (sY < 0) {
                sH = nH + sY;
                sY = 0;
                nW = size.w * sH / size.h;
                sX = sW + sX - nW;
                sW = nW;
            }
            if (sY + sH > this.mainBoxPrt.offsetHeight) {
                sH = this.mainBoxPrt.offsetHeight - sY;
                nW = size.w * sH / size.h;
                sX = sW + sX - nW;
                sW = nW;
            }
		}
		this.setSize(sW, sH);
		this.setPos(sX, sY);
		if (DSS.PropertyBar)
			DSS.PropertyBar.setPosAndSize(sX, sY, sW, sH);
	},
	setHintPosAndSize : function(ps) {
		if (!ps)
			return;
		if (ps.x > -1) {
			this.setLeft(ps.x);
		}
		if (ps.y > -1) {
			this.setTop(ps.y);
		}
		if (ps.w > -1) {
			this.setW(ps.w);
		}
		if (ps.h > -1) {
			this.setH(ps.h);
		}
		if (DSS.PropertyBar)
			DSS.PropertyBar.setPosAndSize(this.curObj.offsetLeft,
					this.curObj.offsetTop, this.curObj.offsetWidth,
					this.curObj.offsetHeight);
	},
	clearDragAndResize : function() {
		this.dragObj = null;
		this.resizeObj = null;
		this.rszPsObj = null;
		this.dim = null;
	},
	maskMove2Obj : function() {
		if (!this.curObj)
			return;
		with (this.dragMask.style) {
			left = this.curObj.offsetLeft + 'px';
			top = this.curObj.offsetTop + 'px';
			width = this.curObj.clientWidth + 'px';
			height = this.curObj.clientHeight + 'px';
			display = 'block';
		}
		var chs = this.mainBoxPrt.getElementsByClassName(this.ctrlMaskClass);
		if (!chs || chs.length == 0)
			return;
		for ( var i = 0; i < chs.length; i++) {
			var obj = chs[i], el = this.util.byId(obj.item_id);
			with (obj.style) {
				left = el.offsetLeft + 'px';
				top = el.offsetTop + 'px';
				width = el.clientWidth + 'px';
				height = el.clientHeight + 'px';
				display = 'block';
			}
		}
	},

	mainBoxMsDwn : function(e) {
		var tar = e.target || e.srcElement;
		// 当在快速发布节目页面  点击右键出现右键菜单（置顶 置底 上移一层 下移一层）同时为选中状态  update 2015205
		if(DSS.util.byId('quick').value=='1'){
			var item = this.findPItem(e);
			this.setCurObj(item);
			var cn = tar.className.baseVal ? tar.className.baseVal : tar.className;
			if(cn == 'VIDEO_COMP_video' || cn == 'IMG_COMP_img'){
				this.setImw(tar.clientWidth);
				this.setImh(tar.clientHeight);
			}
			return;
		}

		// 当在普通的节目编辑界面  如果被选中的部件属于锁定状态 则不允许拖动
		if(tar.parentNode.parentNode.style.opacity == '0.5'){
			var item = this.findPItem(e);
			this.setCurObj(item);
			return;
		}
		// 在普通编辑页面 鼠标右键 出现右键菜单同时选中该部件
		if(e.button==2){
			var item = this.findPItem(e);
			this.setCurObj(item);
			return;
		}
		
		var cn = tar.className.baseVal ? tar.className.baseVal : tar.className;
		if (this.resizeClass.indexOf(cn) != -1) {
			var item = this.findPItem(tar);
			this.resizeObj = item;
			this.startPos = this.getOffsetPos(this.mainBoxPrt, e);//this.util.getMs2Ele(item, e);
			this.startSize = {
				w : item.offsetWidth,
				h : item.offsetHeight
			};
            this.startXY = {
                x : item.offsetLeft,
                y : item.offsetTop
            };
			this.dim = cn.split('_')[1];
			if (DSS.BaseLine.isNeedLine())
				DSS.BaseLine.countLines(this.resizeObj);
		} else if (this.rszPsClass.indexOf(cn) != -1) {
			var item = this.findPItem(tar);
			this.rszPsObj = item;
			this.startPos = this.getOffsetPos(this.mainBoxPrt, e);//this.util.getMs2Ele(item, e);
			this.startSize = {
				w : item.offsetWidth,
				h : item.offsetHeight
			};
			this.startXY = {
				x : item.offsetLeft,
				y : item.offsetTop
			};
			this.dim = cn.split('_')[1];
			if (DSS.BaseLine.isNeedLine())
				DSS.BaseLine.countLines(this.rszPsObj);
		} else {
			var item = this.findPItem(e);
			if (!item)
				return;
			if (e.ctrlKey) {
				if (item == this.curObj)
					return;
				var tar = e.target || e.srcElement;
				if (tar.className == this.ctrlMaskClass)
					return;
				this.addCtrlMask(item);
			} else {
				this.clearCtrlMask();
				this.dragObj = item;
				this.startPos = this.getOffsetPos(this.mainBoxPrt, e);//this.util.getMs2Ele(item, e);
				this.setCurObj(item);
                this.startSize = {
                    w : item.offsetWidth,
                    h : item.offsetHeight
                };
				this.startXY = {
					x : item.offsetLeft,
					y : item.offsetTop
				};
				if (DSS.BaseLine.isNeedLine())
					DSS.BaseLine.countLines(this.dragObj);
			}
		}
        this.startMSPos = this.util.getMs2Ele(this.box, e);
        //console.log('resizeObj:'+this.resizeObj+', rszPsObj:'+this.rszPsObj+', dragObj:'+this.dragObj);
        //console.log('startPos:('+this.startPos.x+','+this.startPos.y+') startXY:('+this.startXY.x+','+this.startXY.y+') startSize:('+this.startSize.w+','+this.startSize.h+')');
	},
	// 鼠标划出编辑区域时：
	mainBoxMsOut : function(e) {
		var mo = DSS.Options.el.mainBox;
		var tar = e.target || e.srcElement;
		//console.info("mo.clientWidth : "+mo.clientWidth+"---------------------tar.id : "+tar.id+"---------------e.offsetX : "+e.offsetX+"-------------------"+"e.offsetY : "+e.offsetY);
		if(tar!=null && (tar.id=="main_box_inner" || tar.id == 'page_panel' || tar.id =='left_page_inner' || tar.id =='mainb' || tar.id == 'prpt_panel')){
			if(e.offsetX+5 >= mo.clientWidth || e.offsetY+5 >= mo.clientHeight || e.offsetX <= 0 || e.offsetY <= 0 
					 || tar.parentNode.parentNode.offsetLeft + e.offsetX+5 >= mo.clientWidth){
				this.mainBoxMsUp(e);
			}
		}
	},
	mainBoxMsMv : function(e) {
		if (!this.mayMove)
			return;
		if (this.dragObj) {
			this.setItemPos(this.dragObj, e);
			if (DSS.BaseLine.isNeedLine())
				DSS.BaseLine.showLines(this.dragObj);
		} else if (this.resizeObj) {
			this.setItemSize(this.resizeObj, e);
			if (DSS.BaseLine.isNeedLine())
				DSS.BaseLine.showLines(this.resizeObj, this.dim);
			
				this.util = DSS.util;
				if(this.resizeObj.id != ""){
					var item = this.util.byId(this.resizeObj.id);
					if(item != null){
						var params = JSON.parse(item.params);
						if(params.type == 'countdownComp_1'){
							item.children[0].children[0].style.lineHeight = item.children[0].style.height;
							item.children[0].children[0].style.width = item.children[0].style.width;
							item.children[0].children[0].style.textAlign="center";
						}
					}
				}
		} else if (this.rszPsObj) {
			this.setItemSizeAndPos(this.rszPsObj, e);
			if (DSS.BaseLine.isNeedLine())
				DSS.BaseLine.showLines(this.rszPsObj, this.dim);
			
			this.util = DSS.util;
			if(this.rszPsObj.id != ""){
				var item = this.util.byId(this.rszPsObj.id);
				if(item != null){
					var params = JSON.parse(item.params);
					if(params.type == 'countdownComp_1'){
						item.children[0].children[0].style.lineHeight = item.children[0].style.height;
						item.children[0].children[0].style.width = item.children[0].style.width;
						item.children[0].children[0].style.textAlign="center";
					}
				}
			}
		}
	},
	mainBoxMsUp : function(e) {
		DSS.BaseLine.clearBsLineEl();
		if (!this.dragObj && !this.resizeObj && !this.rszPsObj)
			return;
		var curMSPos = this.util.getMs2Ele(this.box, e);
		if (curMSPos.x == this.startMSPos.x && curMSPos.y == this.startMSPos.y) {
			this.clearDragAndResize();
			this.startMSPos = null;
			return;
		}
		if (!this.mayMove) {
			this.clearDragAndResize();
			this.startMSPos = null;
			return;
		}
		if (DSS.BaseLine.isNeedLine()) {
			var ps = DSS.BaseLine.getHintPosAndSize();
			this.setHintPosAndSize(ps);
		}
		if (this.dragObj) {
			DSS.Controller.execute('CMD_BLOCK_MOVE', this.dragObj.id,
					this.startXY, {x:this.dragObj.offsetLeft, y:this.dragObj.offsetTop});//this.util.getEle2Ele(this.box, this.dragObj));
		} else if (this.resizeObj) {
			DSS.Controller.execute('CMD_BLOCK_RESIZE', this.resizeObj.id,
					this.startSize, {
						w : this.resizeObj.offsetWidth,
						h : this.resizeObj.offsetHeight
					});
		} else if (this.rszPsObj) {
			DSS.Controller.execute('CMD_BLOCK_MOVE_RESIZE', this.rszPsObj.id, {
				x : this.startXY.x,
				y : this.startXY.y,
				w : this.startSize.w,
				h : this.startSize.h
			}, {
				x : this.rszPsObj.offsetLeft,
				y : this.rszPsObj.offsetTop,
				w : this.rszPsObj.offsetWidth,
				h : this.rszPsObj.offsetHeight
			});
		}
		this.clearDragAndResize();
	}
};
