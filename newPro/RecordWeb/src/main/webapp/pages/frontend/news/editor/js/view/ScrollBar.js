/**
 * User: hht-zsl
 * Date: 14-4-24
 * 滚动实现百分比数大小的设置
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
			}  
		};
	})();
	
	/**
	 * SliderBar(滚动实现数值大小的设置)
	 */ 
	var SliderBar = function (id){
		if (!id) return;
		this.container = typeof id == 'string' ? getDom.byId(id) : id; 
		this.init();
	};
	SliderBar.prototype = {
		constructor: SliderBar,
		init:function (){
			var parEL = this.container;
			this.count = getDom.byClassName('count',parEL)[0];
			this.sliderRail = getDom.byClassName('sliderRail',parEL)[0];
			this.sliderProgress = getDom.byClassName('sliderProgress',parEL)[0];
			this.slider = getDom.byClassName('slider',parEL)[0];
			this.addEvent();
		},
		addEvent: function(){
			var me = this,
				sliderRailWidth = this.sliderRail.clientWidth, // 滚动条的宽度
				sliderWidth = this.slider.offsetWidth, // 滑块的宽度
				maxDistance = sliderRailWidth - sliderWidth; // 滚动条的最大值
				
			// 滚动条相关事件的处理
			this.sliderRail.onmousedown = function(evt){
				var evt = evt || window.event,
					targetPos = evt.clientX - this.offsetLeft; 
				me.sliderFn(targetPos,maxDistance);
			}
			
			// 滑块相关事件的处理
			this.slider.onmousedown = function (evt){
				var evt = evt || window.event,
					currentPos = evt.clientX - this.offsetLeft; // 滑块的当前位置
					
				// 阻止滑块mousedown事件的冒泡(不阻止冒泡，会导致点击滑块的时候滑块出现滚动)
				if(evt.stopPropagation){ evt.stopPropagation();}
				if(evt.preventDefault){ evt.preventDefault();}
				
				doc.onmousemove = function (evt){
					var evt = evt || window.event,
						moveDistance = evt.clientX - currentPos; // 计算滚过的距离
					me.sliderFn(moveDistance,maxDistance);
				};
				doc.onmouseup = function(){
					this.onmousemove = null;
					this.onmouseup = null;
				};
			};
		},
		sliderFn: function(distance,maxDistance){ // 滚动实现方法
			distance = Math.max(0, distance),  // 限制极值
			distance = Math.min(distance, maxDistance);
			this.slider.style.left = distance + 'px'; // 滚动后滑块的水平位移
			this.sliderProgress.style.width = distance + 'px'; // 已滚过的距离
			this.count.innerHTML = Math.round(distance/maxDistance*100); // 计数百分比
		}
	}
	
	//SliderBar.getDom = getDom; 
	DSS.SliderBar = SliderBar;
	win.DSS = DSS;
})(window);

