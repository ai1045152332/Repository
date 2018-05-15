/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-22
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */

var  VideoComp = {
    dftVals : {path:"",mapped:""},
    imgClass: 'VIDEO_COMP_video'
};
DSS.util.extend(VideoComp,CompChClass);
DSS.util.extend(VideoComp,{
    addParamsToItem : function (item,o){
        CompClass.addParamsToItem(item,o);
        var video =  item.getElementsByClassName(this.imgClass)[0];
        if (o.mapped) video.src = o.mapped;
        video.ondragstart = function (){return false};
        DSS.util.events(item,'selectstart',function(e){
            if(e.stopPropagation)e.stopPropagation();
            if(e.preventDefault)e.preventDefault();// add 20140813
        });
        return item;
    },
    setProperty : function (item,name,value){
        var params = JSON.parse(item.params);
        var video =  item.getElementsByClassName(this.imgClass)[0];
        switch (name){
            case 'video_path':
                video.src = value.mapped;
                params.path = value.path;
                params.mapped = value.mapped;
                break;
        }
        item.params = JSON.stringify(params);
    },
    getProperty : function (item,name){
        var params = JSON.parse(item.params);
        switch (name){
            case 'video_path':
                var obj = {path:params.path,mapped:params.mapped};
                return obj;
            case 'width':
                return item.offsetWidth;
            case 'height':
                return item.offsetHeight;
        }
    }
});

DSS.Options.comp_map['2'] = VideoComp;