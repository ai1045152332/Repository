/**
 * User: hht-zsl
 * Date: 2014-6-10
 * 根据输入的关键字生成城市列表
 */
(function(win){
	var currIndex = -1,
		doc = win.document,
		DSS = parent.DSS || DSS;
	var CreateCityList = function (id,options){
		if (!id) return;
		this.container = typeof id == 'string' ? doc.getElementById(id) : id; 
		this.arrow = this.container.previousElementSibling.children[0];
		this.options = options || {};
		this.init();
	};
	CreateCityList.prototype = {
		constructor: CreateCityList,
		init:function (){
			this.getCityList();
			this.addEvent(this.changeUI);
		},
		getCityList: function(){
			var me = this, html = [], targetArr = [],
				dataArr = this.options.data.citylist; 
			currIndex = -1;
				
			me.setServerIp(this.options.data.serverIp);
			for(var i=0,len=dataArr.length;i<len;i++){
				var oCity = dataArr[i], str='';
				for(var key in oCity){
					str += (key=='cname')? oCity[key] : '('+oCity[key].toLowerCase()+')';
				}
				targetArr.push(str);
			}
			//console.log(targetArr)
			var targetData = this.options.keyEl.value.trim().toLowerCase().likeStr(targetArr);
			console.log(targetData)
			if(''!=this.options.keyEl.value.trim()){
				if(targetData.length==1 && targetData[0]==''){
					this.container.innerHTML = '<span>暂未收录</span>'; // 
				}else{
					for(var j=0,len=targetData.length;j<len;j++){
						if(targetData[j].length>1){ // 去除拼音
							targetData[j]=targetData[j].substring(0,targetData[j].indexOf('('));
						}
						html.push('<a href="javascript:;" title="'+ targetData[j] +'">'+ targetData[j] +'</a>');
					}
					this.container.innerHTML = html.join('');
				}
			}
			this.container.style.display = 'block';
			this.arrow.style.top = 3 +'px';
			this.arrow.parentNode.style.cursor = 'pointer';
			DSS.util.events(this.arrow, 'click', function(e){
				me.changeUI.call(me);
				me.stopBubble(e); // 阻止事件冒泡	
			});
		},
		addEvent: function(callback){
			var me = this;
			
			// 通过事件代理，减少事件处理器的数量，提高性能
			DSS.util.events(this.container, 'click', function(e){
				e = window.event || e;
				var targetEl = e.srcElement || e.target;
				if(targetEl.tagName.toLowerCase() == 'a'){
					// 去掉拼音，然后将字符串赋给文本域
					//me.options.keyEl.value = targetEl.innerHTML.substring(0,targetEl.innerHTML.indexOf('('));
					me.options.keyEl.value = targetEl.innerHTML;
					me.setCity(me.options.keyEl.value);
				}
				callback && callback.call(me);
				me.stopBubble(e); // 阻止事件冒泡	
				return false; // 防止a标签跳转
			});
			
			// 监听document的click事件
			DSS.util.events(doc, 'click', function(e){
				callback && callback.call(me);
			});
		},
		pressUpOrDown_handler: function(currkeyCode){  // 方向键（上下键）的处理程序
			var items = this.container.getElementsByTagName('a');
			var total = items.length;
			if(!total){return;}
			var temp = currkeyCode == 38 ? total-1 : total+1;
			if(currIndex==-1 && currkeyCode == 38){ // 如果列表初始化后，按的是向上键
				temp=total;
			}
			currIndex = (temp+currIndex) % total;
			for(var i=0,len=total;i<len;i++){
				if(currIndex == i){
					DSS.util.aClass(items[i],'current');
					continue;
				}
				DSS.util.dClass(items[i],'current');
			}
		},
		pressEnter_handler: function(){
			var items = this.container.getElementsByTagName('a');
			if(items && !items.length){return;}
			for(var i=0,len=items.length;i<len;i++){
				if(currIndex == i){
					//this.options.keyEl.value = items[i].innerHTML.substring(0,items[i].innerHTML.indexOf('('));
					this.options.keyEl.value = items[i].innerHTML;
					this.setCity(this.options.keyEl.value);
					this.changeUI.call(this);
					break;
				}
			}
		},	
		setServerIp: function(serverIp){
			var oldServerIp = DSS.BlockOpr.getProperty('serverIp');
			DSS.BlockOpr.setProperty('serverIp',serverIp);
			DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'serverIp',oldServerIp,serverIp);
		},
		setCity: function(currentCity){
			var oldCity = DSS.BlockOpr.getProperty('city');
			DSS.BlockOpr.setProperty('city',currentCity);
			DSS.Controller.execute('CMD_BLOCK_PRPT_CHANGE',DSS.BlockOpr.getCurObj().id,'city',oldCity,currentCity);
		},
		changeUI: function(){ // 改变相关元素外观
			this.arrow.style.top = -6 +'px';
			this.arrow.parentNode.style.cursor = 'default';
			this.container.innerHTML = '';
			this.container.style.display = 'none';
			this.destory();
		},
		stopBubble: function(e){
			e = window.event || e;
			if(e.stopPropagation){
				e.stopPropagation();
			}else{
				e.cancelBubble=true;	
			}
		},
		destory: function(){
			DSS.util.delEvent(doc,'click');
			DSS.util.delEvent(this.container,'click');
			DSS.util.delEvent(this.arrow,'click');
		}
	}
	
	DSS.CreateCityList = CreateCityList;
	win.DSS = DSS;
})(window);

// 去首尾空白字符
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

// 校验搜索关键字
String.prototype.isSearch = function(){
	var patrn=/^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>\/?]{1}[^`~!@$%^&()+=|\\\] [\]\{\}:;'\,.<>?]{0,19}$/;
	if (!patrn.exec(this)) return false;  
	return true;  
}; 

// 字符串模糊查询,以数组形式返回
// target_array被查的数组
String.prototype.likeStr = function(target_array){
    var strLen = this.length, // [查找字符串]的长度
		startChar = this.charAt(0),// 开始字符, charAt返回指定位置的字符
		arrayStr,
		re,
		returnVal = '',
		arrayStr_cStartChar = '', // 目标城市数组元素的中文首字符
		arrayStr_eStartChar = '', // 目标城市数组元素的英文首字符
		arrayStr_eChar = '';// 目标城市数组元素的英文字符
	if(strLen>1){var otherChar = this.substring(1);}
    for(var i=0,len=target_array.length;i<len;i++){
        arrayStr = target_array[i];
		arrayStr_cStartChar = arrayStr.charAt(0);
		arrayStr_eChar = arrayStr.substring(arrayStr.indexOf('(')+1,arrayStr.length-1);
		arrayStr_eChar0 = arrayStr.substring(0,arrayStr.indexOf('('));
		arrayStr_eStartChar = arrayStr_eChar.charAt(0);
		if(otherChar){re = eval("/" + startChar + "[\\S|\\s]*[" + otherChar + "]+/g");}
        for(var j=0,curConLen=arrayStr.length;j<curConLen;j++){
            if((strLen==1 && (startChar==arrayStr_eStartChar  || startChar==arrayStr_cStartChar)) || (re && re.test(arrayStr_eChar))){  
            	returnVal=returnVal+arrayStr+"|";
				break;
            }
            else if(strLen>1){
            	if(this==arrayStr_eChar0){  
                	returnVal=returnVal+arrayStr+"|";
    				break;
                }
            }
        }
    }
    if(returnVal.length>1) returnVal=returnVal.substring(0,returnVal.length-1);
    var returnStrs = returnVal.split("|");
    returnStrs.sort();
    return returnStrs;
};




