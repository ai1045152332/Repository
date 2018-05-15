/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-14
 * Time: 上午9:23
 * To change this template use File | Settings | File Templates.
 */
Date.prototype.Format = function(formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());
    str=str.replace(/M/g,this.getMonth());

    str=str.replace(/w|W/g,Week[this.getDay()]);

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());

    return str;
};
var DSS = DSS || {};
DSS.MakePageId = {
    mkPageId : function (){
        var dt = new Date();
        var str = dt.Format('yyyyMMDDHHmmss');
        return 'blk'+str+dt.getMilliseconds();
    },
    mkPageIds : function (n){
        if(n<=0)return;
        var zu = [];
        for(var i=0;i<1;i++){
            zu.push(this.mkPageId()+i);
        }
        return zu;
    }
};
