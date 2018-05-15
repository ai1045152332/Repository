
/**
 * User: hht-zsl
 * Date: 14-4-29
 * 天气部件
 */
var ddid ;
var GetWeather = function (container) {
	this.container = $(container);
	this.bid = container.parentNode.id;
	ddid = this.bid;
};
GetWeather.prototype = {
	init : function () {
		var me = this;
		// init 时，首先读取缓存中的数据  如果缓存中没有 则不进行显示 ；如果缓存中有 则显示；
		var val = me.loadData(me.container.attr("data-city"));//me.cookie(me.container.attr("data-city"));
		if (val) {
			var $iconEl = me.container.find('img');
			var $cityEl = me.container.find('span');
			var weatherMsg = val.split("*");
			console.log(weatherMsg[0]+"  "+weatherMsg[1]+"  "+weatherMsg[2]+"  "+weatherMsg[3]+"  "+weatherMsg[4]);
			$iconEl.attr("src",'images/'+weatherMsg[0]+'.gif');
			$iconEl.attr("title",weatherMsg[1]);
			$cityEl.html(weatherMsg[2] + '<br />' +  weatherMsg[3]  + '°/' + weatherMsg[4]+ '°');
		}

        var serverDomain = "";//me.container.attr("data-ip");
        if (window.serverDomain) {
            serverDomain = window.serverDomain;
        }
        if (0/*window.isAndroid()*/) {
            var url2 = 'http://'+ serverDomain + '/ManagementCenter/project/Interface_WeatherInfo.do';
            var dt = 'city='+encodeURIComponent(me.container.attr("data-city")) +'&t='+ new Date().getTime();
            window.nativeWebAccess(url2, dt, "webAccessCallback", me.blk_id);
        } else {
            var url = '/ManagementCenter/project/Interface_WeatherInfop.do';
            if (serverDomain) {
            	url = 'http://'+ serverDomain + '/ManagementCenter/project/Interface_WeatherInfop.do';
            }
            $.ajax({
                url : url,
                data:{city: me.container.attr("data-city"), t: new Date().getTime()},
                crossDomain: true,
                dataType: 'jsonp',
                jsonpCallback: 'showCityWeater_'+me.bid,
                success : function (data, textStatus, XHR) {
                    if (textStatus == 'success') {
                        var str = data.current_conditions.code+'*'+data.current_conditions.conditionCn+'*'+data.city+'*'+data.date[0].low  +'*'+data.date[0].high+'*SCONTAINER'+ddid+'ECONTAINER#JSCOOKIEID';
                        //me.SetCookie(me.container.attr("data-city"),str);
                        me.saveData(me.container.attr("data-city"),str);
                        me.data2Html(data);
                    }
                }
            });
        }

		setTimeout(function(){
			me.init();
		}, 600000);
	},
    onWebAccessResult: function(result, odata) {
        if(result=='200'){
            var data = JSON.parse(odata);
            var str = data.current_conditions.code+'*'+data.current_conditions.conditionCn+'*'+data.city+'*'+data.date[0].low  +'*'+data.date[0].high+'*SCONTAINER'+ddid+'ECONTAINER#JSCOOKIEID';
            this.saveData(this.container.attr("data-city"),str);
            this.data2Html(data);
        }
    },
	data2Html : function (data) {
		var me = this;
		var $iconEl = me.container.find('img');
		var $cityEl = me.container.find('span');
		$iconEl.attr("src",'images/'+data.current_conditions.code+'.gif');
		$iconEl.attr("title",data.current_conditions.conditionCn);
		$cityEl.html(data.city + '<br />' + data.date[0].low  + '°/' + data.date[0].high+ '°');
	},
	loadData : function(city) {
		var data = localStorage.getItem("weatherData");
		var dataObj = {};
		if (data) {
			dataObj = JSON.parse(data);
		}
		
		return dataObj[city];
	},
	saveData : function(city, info) {
		var data = localStorage.getItem("weatherData");
		var dataObj = {};
		if (data) {
			dataObj = JSON.parse(data);
		}
		
		dataObj[city] = info;
		data = JSON.stringify(dataObj);
		localStorage.setItem("weatherData", data);
	},
	SetCookie : function (name,value)//两个参数，一个是cookie的名子，一个是值
	{
		document.cookie = name + "="+ escape (value) + ";";
	},
	cookie : function (name) {     // 根据cookie的name属性获取value值
		var cookieArray=document.cookie.split("; "); //得到分割的cookie名值对    
		console.log(cookieArray.length);
		for (var i=0;i<cookieArray.length;i++){    
			if(cookieArray[i].indexOf("=") > 0){
				var arr=cookieArray[i].split("=");       //将名和值分开 
				if(arr[0]==name)return unescape(arr[1]); //如果是指定的cookie，则返回它的值    
			}
		} 
		return ""; 
	} 
};