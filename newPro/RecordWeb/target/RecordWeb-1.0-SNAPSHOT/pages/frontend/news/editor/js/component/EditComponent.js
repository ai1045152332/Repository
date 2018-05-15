/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-15
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
var DSS = DSS || {};
DSS.EditComponent = {
    type_map : DSS.Options.comp_map,
    originItemSize : DSS.Options.menuItem,
    getParseStr : function (item){
        var that = this;
        return this.parseStrObj(item,function(item,zu){
            zu.push(that.getItemPsStr(item));
            return zu;
        });
    },
    getMtPsStr : function (ids){
        var that = this;
        return this.parseStrObj(ids,function(list,zu){
            return that.getMtPsList(list,zu);
        });
    },
    getMtPsList :function (list,zu){
        if(!zu)zu = []; console.log(zu);
        for(var i=0;i<list.length;i++){
            var item = DSS.util.byId(list[i]);
            var strObj = this.getItemPsStr(item);
            zu.push(strObj);
        }
        return zu;
    },
    getDelPsStr : function(ids){
        return this.parseStrObj(ids,function(list,zu){
            for(var i=0;i<list.length;i++){
                zu.push({bid:list[i]});
            }
            return zu;
        });
    },
    getLevelPsStr : function (levelList){
        return this.parseStrObj(levelList,function(list,zu){
            for(var i=0;i<list.length;i++){
                zu.push({bid:list[i].id,idx:list[i].zIndex});
            }
            return zu;
        });
    },
    getAlignPsStr : function (alignList){
        return this.parseStrObj(alignList,function(list,zu){
            for(var i=0;i<list.length;i++){
                zu.push({bid:list[i].id,x:list[i].left,y:list[i].top});
            }
            return zu;
        });
    },
    getItemPsStr : function (item){
        var comp = this.type_map[item.type_id];
       // var strObj = JSON.parse(item.params);
        var strObj = comp.getParseStr(item);
        strObj.bid = item.id;
        strObj.t = item.type_id;
        strObj.x = DSS.util.getStyleVal(item,'left');
        strObj.y = DSS.util.getStyleVal(item,'top');
        strObj.w = DSS.util.getStyleVal(item,'width');
        strObj.h = DSS.util.getStyleVal(item,'height');
        strObj.idx = item.style.zIndex;
        strObj.op = item.style.opacity; // 部件的锁定 解锁
        strObj.iH = item.innerHTML;
        return strObj;
    },
    parseStrObj :function (o,fn){
        var str = 'prid='+DSS.Options.cur.program + '&pid='+ DSS.Options.cur.page;
        var zu = [];
        zu = fn(o,zu);
        // 视频  图片 flash
        if(zu[0].t == '2' || zu[0].t == '1' || zu[0].t == '11'){
        	str = str+"&t="+zu[0].t+"&bid="+zu[0].bid;
        }
        return str+'&params=' + encodeURIComponent(JSON.stringify(zu));
    },
    initBlock : function (o){
        var comp = this.type_map[o.t];
        if(!comp)return;
        var el = comp.initBlock(o);
        el.type_id = o.t;
        el.id = o.bid;
        el.style.zIndex = o.idx;
        //el.style.opacity = o.op;
        this.setItemL(el, o.x);
        this.setItemT(el, o.y);
        this.setItemW(el, o.w, o.scX);
        this.setItemH(el, o.h, o.scY);
        // 轮播图的缩略图的所见所得   和    PPT的缩略图的所见所得 
        if(el.type_id == '8' || el.type_id == '9' || el.type_id == '4' || el.type_id == '18'){// "18"-->PPT
        	if(o.imgLoop.length>0){
        		el.children[0].children[0].src = o.imgLoop[0].mapped;
        	}
        }
        return el;
    },
    setItemW : function (item,n,sc){
        var comp = this.type_map[item.type_id];
        comp.setItemW(item,n,sc);
    },
    setItemH : function (item,n,sc){
        var comp = this.type_map[item.type_id];
        comp.setItemH(item,n,sc);
    },
    setItemL : function (item,n){
        item.style.left = n +'px';
    },
    setItemT : function (item,n){
        item.style.top = n +'px';
    },
    setItemOp : function (item,n){
    	 item.style.opacity = n; 
    },
    addBlock : function (typeId){
        var comp = this.type_map[typeId];
        var el = comp.addBlock(typeId);
        el.type_id = typeId;
        el.style.zIndex = DSS.Level.countZIndex();
        el.style.opacity = 1;  //部件的 锁定 解锁
        return el;
    },
    copyBlock : function (item,id){
        if(!id)id = DSS.MakeId.mkBlockId;
        var comp = this.type_map[item.type_id];
        var el = comp.copyBlock(item,id);
        el.type_id = item.type_id;
        return el;
    },
    destroy : function (item){
        var comp = this.type_map[item.type_id];
        comp.destroy(item);
    },
    setProperty : function (item,name,value){
        var comp = this.type_map[item.type_id];
        comp.setProperty(item,name,value);
    },
    getProperty : function(item,name){
        var comp = this.type_map[item.type_id];
        return comp.getProperty(item,name);
    }
};
