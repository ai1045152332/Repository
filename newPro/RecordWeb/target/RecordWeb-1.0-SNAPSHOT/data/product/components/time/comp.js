/**
 * User: hht-zsl
 * Date: 14-4-29
 * 时间部件（播放）
 */
var GetCurrentTime = function(container){
	this.container = container;
	this.timeEl = this.container.getElementsByClassName("time")[0];
	this.dateEl = this.container.getElementsByClassName("date")[0];
};
GetCurrentTime.prototype = {
	init: function(){
		var dt = new Date(),
			mouth = this.fixTime(dt.getMonth()+1),
			day = this.fixTime(dt.getDate()),
			hours = this.fixTime(dt.getHours()),
			minute = this.fixTime(dt.getMinutes());
		var weekday = dt.getDay();
		switch(weekday) {
			case 0:
				weekday = "星期日";
				break;
			case 1:
				weekday = "星期一";
				break;
			case 2:
				weekday = "星期二";
				break;
			case 3:
				weekday = "星期三";
				break;
			case 4:
				weekday = "星期四";
				break;
			case 5:
				weekday = "星期五";
				break;
			case 6:
				weekday = "星期六";
				break;
		}
		this.timeEl.innerHTML = hours + ':' + minute;
		this.dateEl.innerHTML = mouth + '月' + day + '日' + weekday;
		var me = this,
			secondsEl = this.timeEl.getElementsByTagName("span")[0];
		setTimeout(function(){
			me.init();
		}, 1000);
	},
	fixTime:function(val) {
		if(Number(val)<10) {
			val = "0" + val;
		}
		return val;
	} 
};