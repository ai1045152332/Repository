/**
 * User: hht-zsl
 * Date: 14-4-25
 * 颜色设置
 */
(function(win){
	var doc = win.document,
		DSS = parent.DSS || {};
		
	// dom工具类
	var getDom = (function(id){
		return {
			byId: function(id){
				return typeof id ==="string" ? doc.getElementById(id) : id;
			},
			byClassName: function(clsName,el){
				var el = el ? el : doc;
				return el.getElementsByClassName(clsName);
			},
			byTagName: function(tagName,el){
				var el = el ? el : doc;
				return el.getElementsByTagName(tagName);
			}
		};
	})();
	
	var colorObj = {
		colors_1: ["#E61912","#F39800","#FFF000","#8FC41E","#00A1EA","#5F52A0","#FACD89","#13B5B1","#E4007F","#C490BF"],
		colors_2: ["#FFFFFF","#969696","#1E467D","#BEB4D7","#B4DCE6","#F19149"],
		colors_3: ["#F1F1F1","#F2F2F2","#D8D8D8","#BFBFBF","#8C8C8C","#969696","#7F7F7F","#5A5A5A","#282828","#0C0C0C","#C8D7F0","#8CB4E1","#558CD2","#1E467D","#14375F","#BEB4D7","#A091C8","#7D6EB9","#5F52A0","#46328C","#DCEBF5","#B4DCE6","#91CDDC","#32879B","#19505F","#FFF45C","#F8B551","#F19149","#EB6100","#E61912"],
	};

	var ColorSet = function (id){
		if (!id) return;
		this.container = typeof id == 'string' ? getDom.byId(id) : id; 
		var me = this;
		this.init(function(){
			me.addEvent();
		});
	};
	ColorSet.prototype = {
		constructor: ColorSet,
		init: function(callback){
			var colorPannel = this.createColorPannel();
			this.container.innerHTML = colorPannel;
			callback && callback();
		},
		createColorPannel: function(){
			var html = [],
				str = '', colors_2_str = '', colors_3_str = '';
			
			for(var n=0, colors_1_len=colorObj.colors_1.length;n<colors_1_len;n++){
				str += '<li class="item"><span style="background-color:'+ colorObj.colors_1[n] +';"></span></li>';
			}
			str = '<ul>' + str;
			
			for(var i=0, colors_2_len=colorObj.colors_2.length;i<colors_2_len;i++){
				colors_2_str += '<div class="item"><span style="background-color:'+ colorObj.colors_2[i] +';"></span></div>';
			}
			str += '<div class="moreColor"><span class="more_color_select_btn">更多...</span><div class="more_color_select_panel"><div class="arrow-outer"><span class="arrow-inner"></span></div><dl class="colorPannel"><dt>' + colors_2_str + '</dt><dd>';
			
			for(var j=0, colors_3_len=colorObj.colors_3.length;j<colors_3_len;j++){
				if(j!=0 && (j+1)%5==0){
					colors_3_str += '<div class="item"><span style="background-color:'+ colorObj.colors_3[j] +'; border-right:1px solid #000;"></span></div>';
				}else{
					colors_3_str += '<div class="item"><span style="background-color:'+ colorObj.colors_3[j] +';"></span></div>';
				}
			}
			str += colors_3_str + '</dd></dl></div></div></ul>';
			
			return str;
		},
		addEvent: function(){
			var me = this,
				moreEl = getDom.byClassName('moreColor',this.container)[0];
			if(moreEl){
				var panneWrap = getDom.byClassName('more_color_select_panel',this.container)[0];
				moreEl.onmouseover = function(){
					//panneWrap.style.display = "block";
					DSS.util.aClass(panneWrap,'check');
					me.adjustPos(this,panneWrap);
					
				};
				moreEl.onmouseout = function(){
					DSS.util.dClass(panneWrap,'check');
					//panneWrap.style.display = "none";
				};
			}
		},
		adjustPos: function(btnEl,pannelEl){
			var targetElTop = btnEl.offsetTop,
				pannelElHeight = pannelEl.offsetHeight+20,
				pageHeight = Math.max(doc.documentElement.clientHeight,doc.body.clientHeight),
				scrollTop =  doc.documentElement.scrollTop || doc.body.scrollTop;
			if(targetElTop + pannelElHeight > pageHeight+scrollTop){
				pannelEl.style.top = - (pannelElHeight-26) + 'px';
				pannelEl.children[0].style.top = (pannelElHeight-34) + 'px';
				pannelEl.children[0].children[0].style.top = -6 + 'px';
				pannelEl.children[1].style.marginTop = 0;
				pannelEl.children[1].style.marginBottom = 12 + 'px';
				return;
			}
			pannelEl.style.top = 26 + 'px';
		}
	}
	
	DSS.ColorSet = ColorSet;
	win.DSS = DSS;
})(window);