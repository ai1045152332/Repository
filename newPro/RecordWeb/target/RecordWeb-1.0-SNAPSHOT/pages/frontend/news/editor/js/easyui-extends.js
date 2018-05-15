	$.extend($.fn.validatebox.defaults.rules, { 
	    minLength : { // 判断最小长度 
	        validator : function(value, param) { 
	            value = $.trim(value);	//去空格 
	            return value.length >= param[0]; 
	        }, 
	        message : '最少输入 {0} 个字符。' 
	    }, 
	    length:{validator:function(value,param){ 
	        var len=$.trim(value).length; 
	            return len>=param[0]&&len<=param[1]; 
	        }, 
	            message:"输入内容长度必须介于{0}和{1}之间." 
	        }, 
	    phone : {// 验证电话号码 
	        validator : function(value) { 
	            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
	        }, 
	        message : '格式不正确,请使用下面格式:020-88888888' 
	    }, 
	    mobile : {// 验证手机号码 
	        validator : function(value) { 
	            return /^(13|15|18)\d{9}$/i.test(value); 
	        }, 
	        message : '手机号码格式不正确' 
	    }, 
	    idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }, 
	    intOrFloat : {// 验证整数或小数 
	        validator : function(value) { 
	            return /^\d+(\.\d+)?$/i.test(value); 
	        }, 
	        message : '请输入数字，并确保格式正确' 
	    }, 
	    currency : {// 验证货币 
	        validator : function(value) { 
	            return /^\d+(\.\d+)?$/i.test(value); 
	        }, 
	        message : '货币格式不正确' 
	    }, 
	    qq : {// 验证QQ,从10000开始 
	        validator : function(value) { 
	            return /^[1-9]\d{4,9}$/i.test(value); 
	        }, 
	        message : 'QQ号码格式不正确' 
	    }, 
	    integer : {// 验证整数 
	        validator : function(value) { 
	            return /^[+]?[1-9]+\d*$/i.test(value); 
	        }, 
	        message : '请输入整数' 
	    }, 
	    chinese : {// 验证中文 
	        validator : function(value) { 
	            return /^[\u0391-\uFFE5]+$/i.test(value); 
	        }, 
	        message : '请输入中文' 
	    }, 
	    english : {// 验证英语 
	        validator : function(value) { 
	            return /^[A-Za-z]+$/i.test(value); 
	        }, 
	        message : '请输入英文' 
	    }, 
	    unnormal : {// 验证是否包含空格和非法字符 
	        validator : function(value) { 
	            return /.+/i.test(value); 
	        }, 
	        message : '输入值不能为空和包含其他非法字符' 
	    }, 
	    username : {// 验证用户名 
	        validator : function(value) { 
	            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value); 
	        }, 
	        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）' 
	    }, 
	    faxno : {// 验证传真 
	        validator : function(value) { 
//	            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value); 
	            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
	        }, 
	        message : '传真号码不正确' 
	    }, 
	    zip : {// 验证邮政编码 
	        validator : function(value) { 
	            return /^[1-9]\d{5}$/i.test(value); 
	        }, 
	        message : '邮政编码格式不正确' 
	    }, 
	    ip : {// 验证IP地址 
	        validator : function(value) { 
	            return /d+.d+.d+.d+/i.test(value); 
	        }, 
	        message : 'IP地址格式不正确' 
	    }, 
	    name : {// 验证姓名，可以是中文或英文 
	            validator : function(value) { 
	                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
	            }, 
	            message : '请输入姓名' 
	    }, 
	    carNo:{ 
	        validator : function(value){ 
	            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
	        }, 
	        message : '车牌号码无效（例：粤J12350）' 
	    }, 
	    carenergin:{ 
	        validator : function(value){ 
	            return /^[a-zA-Z0-9]{16}$/.test(value); 
	        }, 
	        message : '发动机型号无效(例：FG6H012345654584)' 
	    }, 
	    email:{ 
	        validator : function(value){ 
	        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	    }, 
	    message : '请输入有效的电子邮件账号(例：abc@126.com)'    
	    }, 
	    msn:{ 
	        validator : function(value){ 
	        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	    }, 
	    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)' 
	    },eqPwd:{ 
	        validator : function(value, param){ 
	                return $(param[0]).val() == value; 
	        }, 
	        message : '两次输入的密码不一致！'    
	    } 
	}); 
	var myExtend = $.extend({}, myExtend);
	$.parser.auto = false;
	$(function() {
		$.messager.progress({
			text : '页面加载中....',
			interval : 100
		});
		$.parser.parse(window.document);
		window.setTimeout(function() {
			$.messager.progress('close');
			if (self != parent) {
				window.setTimeout(function() {
					try {
						parent.$.messager.progress('close');
					} catch (e) {
					}
				}, 500);
			}
		}, 1);
		$.parser.auto = true;
	});
	
	$.fn.tree.defaults.loadFilter = function (data, parent) {
		var opt = $(this).data().tree.options;
		var idFiled,
		textFiled,
		parentField;
		if (opt.parentField) {
			idFiled = opt.idFiled || 'id';
			textFiled = opt.textFiled || 'text';
			parentField = opt.parentField;
			var i,
			l,
			treeData = [],
			tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idFiled]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textFiled];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textFiled];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	};
	/**
	 * @requires jQuery,EasyUI
	 * 
	 * panel关闭时回收内存
	 */
	$.fn.panel.defaults.onBeforeDestroy = function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for ( var i = 0; i < frame.length; i++) {
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if ($.browser.msie) {
					CollectGarbage();
				}
			}
		} catch (e) {
		}
	};
	/** 
	 * @requires jQuery,EasyUI
	 * 
	 * 防止panel/window/dialog组件超出浏览器边界
	 * @param left
	 * @param top
	 */
	var easyuiPanelOnMove = function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	};
	$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	$.fn.window.defaults.onMove = easyuiPanelOnMove;
	$.fn.panel.defaults.onMove = easyuiPanelOnMove;
	
	/**
	 * @requires jQuery
	 * 
	 * 将form表单元素的值序列化成对象
	 * 
	 * @returns object
	 */
	serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	
	/**
	 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
	 * 
	 * @returns list
	 */
	getList = function(value) {
		if (value != undefined && value != '') {
			var values = [];
			var t = value.split(',');
			for ( var i = 0; i < t.length; i++) {
				values.push('' + t[i]);/* 避免他将ID当成数字 */
			}
			return values;
		} else {
			return [];
		}
	};
	/**
	 * @requires jQuery,EasyUI
	 * 
	 * 通用错误提示
	 * 
	 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
	 */
	var easyuiErrorFunction = function(XMLHttpRequest) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	};
	$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
	$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
	$.fn.form.defaults.onLoadError = easyuiErrorFunction;
	
	/**
	 * 
	 * @param title
	 *            标题
	 * 
	 * @param msg
	 *            提示信息
	 * 
	 * @param fun
	 *            回调方法
	 */
	myExtend.messagerConfirm = function(title, msg, fn) {
		return $.messager.confirm(title, msg, fn);
	};

	/**
	 * @requires jQuery,EasyUI
	 */
	myExtend.messagerShow = function(options) {
		return $.messager.show(options);
	};

	/**
	 * @requires jQuery,EasyUI
	 */
	myExtend.messagerAlert = function(title, msg, icon, fn) {
		return $.messager.alert(title, msg, icon, fn);
	};
	/**
	 * 
	 * 增加formatString功能
	 * 
	 * 使用方法：myExtend.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
	 * 
	 * @returns 格式化后的字符串
	 */
	myExtend.fs = function(str) {
		for ( var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
	};
	
	myExtend.dialog = function(options) {
		var opts = $.extend({
			modal : true,
			onClose : function() {
				$(this).dialog('destroy');
			}
		}, options);
		return $('<div/>').dialog(opts);
	};
