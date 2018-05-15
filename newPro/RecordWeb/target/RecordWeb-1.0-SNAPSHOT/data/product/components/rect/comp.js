/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-11
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */

var rectComp = function (boxId){
    this.box = (typeof (boxId)=="string")?document.getElementById(boxId):boxId;
};
rectComp.prototype = {
    constructor: rectComp,
    init : function (){
        var params = this.box.getAttribute("params");
        var options = eval("("+params+")");
        this.box.style.width = (options.w-options.bdW*2) + 'px';
		this.box.style.height = (options.h-options.bdW*2) + 'px';
    },
    destroy : function () {
        ;
    }
};
