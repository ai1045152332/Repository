/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-7
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */

var DSS = DSS || {};
DSS.BaseLine  = {
    box:DSS.Options.el.mainBoxIn,
    vBl_1 : DSS.Options.el.baslineV,
    vBl_2 : DSS.Options.el.baslineV_2,
    hBl_1 : DSS.Options.el.baslineH,
    hBl_2 : DSS.Options.el.baslineH_2,
    util : DSS.util,
    isNeedBaseLine : true,
    dAbs :20,
    isNeedLine : function (){
        return this.isNeedBaseLine;
    },
    setNeedLine : function (b){
        this.isNeedBaseLine = b;
    },
    countLines : function (item){
        var chs = this.box.children;
        this.hBdLines = [];
        this.hCtLines = [];
        this.vBdLines = [];
        this.vCtLines = [];
        if(!chs||chs.length==0)return;
        var zu = [];
        for(var i=0;i<chs.length;i++){
            var obj = chs[i];
            if(obj==item)continue;
            var x=obj.offsetLeft,y=obj.offsetTop,w=obj.offsetWidth,h=obj.offsetHeight,id=obj.id;
            //水平边框线
            this.hBdLines.push({x:x,y:y,id:id});
            this.hBdLines.push({x:x,y:y+h,id:id});
            //水平中轴线
            this.hCtLines.push({x:x,y:y+Math.round(h/2),id:id});
            //垂直边框线
            this.vBdLines.push({x:x,y:y,id:id});
            this.vBdLines.push({x:x+w,y:y,id:id});
            //垂直中轴线
            this.vCtLines.push({x:x+Math.round(w/2),y:y,id:id});
        }
        //获取编辑区的基线
        this.hBdLines.push({x:0,y:0,id:this.box.id});
        this.hBdLines.push({x:0,y:this.box.offsetHeight,id:this.box.id});
        this.hCtLines.push({x:0,y:Math.round(this.box.offsetHeight/2),id:this.box.id});
        this.vBdLines.push({x:0,y:0,id:this.box.id});
        this.vBdLines.push({x:this.box.offsetWidth,y:0,id:this.box.id});
        this.vCtLines.push({x:Math.round(this.box.offsetWidth/2),y:0,id:this.box.id});

        //给数组从小到大排序
        var hSort =  function(a,b){return a.y> b.y?1:-1},vSort = function(a,b){return a.x> b.x?1:-1};
        this.hBdLines.sort(hSort);
        this.hCtLines.sort(hSort);
        this.vBdLines.sort(vSort);
        this.vCtLines.sort(vSort);
    },
    getHintPosAndSize : function (){
        return this.posAndSize;
    },
    showLines : function (item,dim){
        return;
        this.clearBsLineEl();
        this.posAndSize = {x:-1,y:-1,w:-1,h:-1};
        this.showHLines(item,dim);
        this.showVLines(item,dim);
    },
    showHLines : function (item,dim){
        var y=item.offsetTop,h=item.offsetHeight,hAxis = y+Math.round(h/2);
        var hLine1 = this.findLine(1,this.hBdLines,y), abs1 = hLine1?Math.abs(hLine1.y-y):100,
            hLine2 = this.findLine(1,this.hBdLines,y+h),abs2 = hLine2?Math.abs(hLine2.y-y-h):100,
            hLine3 = this.findLine(1,this.hCtLines,hAxis),abs3=hLine3?Math.abs(hLine3.y-hAxis):100;
        var min;
        if(dim){
            if(dim.indexOf('T')>-1){
                min = Math.min(abs1,abs3);
                if(min>this.dAbs)return;
                if(min==abs1){
                    this.showLine(1,this.hBl_1,hLine1,item);
                    this.posAndSize.y=hLine1.y;
                    this.posAndSize.h =  y + h - hLine1.y;
                }else{
                    this.showLine(1,this.hBl_1,hLine3,item);
                    this.posAndSize.h = 2*(y+h-hLine3.y);
                    this.posAndSize.y = y+h - this.posAndSize.h;
                }
            }else if(dim.indexOf('B')>-1){
                min = Math.min(abs2,abs3);
                if(min>this.dAbs)return;
                if(min==abs2){
                    this.showLine(1,this.hBl_2,hLine2,item);
                    this.posAndSize.y = y;
                    this.posAndSize.h = hLine2.y - this.posAndSize.y;
                }else{
                    this.showLine(1,this.hBl_1,hLine3,item);
                    this.posAndSize.h = 2*(hLine3.y - y);
                    this.posAndSize.y = y;
                }
            }else{
                return;
            }
        }else{
            min = Math.min(abs1,abs2,abs3);
            if(min<this.dAbs){
                if(abs1==min){
                    this.showLine(1,this.hBl_1,hLine1,item);
                    this.posAndSize.y = hLine1.y;
                    if(abs2==min){
                        this.showLine(1,this.hBl_2,hLine2,item);
                        this.posAndSize.h = hLine2.y - hLine1.y;
                    }
                }else if(abs2==min){
                    this.showLine(1,this.hBl_2,hLine2,item);
                    this.posAndSize.y = hLine2.y - h;
                }else{
                    this.showLine(1,this.hBl_1,hLine3,item);
                    this.posAndSize.y = hLine3.y - Math.round(h/2);
                }
            }
        }
    },
    showVLines : function (item,dim){
        var x=item.offsetLeft,w=item.offsetWidth,vAxis=x+Math.round(w/2);
        var hLine1 = this.findLine(0,this.vBdLines,x), abs1 = hLine1?Math.abs(hLine1.x-x):100,
            hLine2 = this.findLine(0,this.vBdLines,x+w),abs2 = hLine2?Math.abs(hLine2.x-x-w):100,
            hLine3 = this.findLine(0,this.vCtLines,vAxis),abs3=hLine3?Math.abs(hLine3.x-vAxis):100;
        if(dim){
            var min;
            if(dim.indexOf('L')>-1){
                min = Math.min(abs1,abs3);
                if(min>this.dAbs)return;
                if(min==abs1){
                    this.showLine(0,this.vBl_1,hLine1,item);
                    this.posAndSize.x=hLine1.x;
                    this.posAndSize.w =  x + w - hLine1.x;
                }else{
                    this.showLine(0,this.vBl_1,hLine3,item);
                    this.posAndSize.w = 2*(x+w-hLine3.x);
                    this.posAndSize.x = x+w - this.posAndSize.w;
                }
            }else if(dim.indexOf('R')>-1){
                min = Math.min(abs2,abs3);
                if(min>this.dAbs)return;
                if(min==abs2){
                    this.showLine(0,this.vBl_2,hLine2,item);
                    this.posAndSize.x = x;
                    this.posAndSize.w = hLine2.x - this.posAndSize.x;
                }else{
                    this.showLine(0,this.vBl_1,hLine3,item);
                    this.posAndSize.w = 2*(hLine3.x - x);
                    this.posAndSize.x = x
                }
            }else{
                return;
            }
        }else{
            var min = Math.min(abs1,abs2,abs3);
            if(min<this.dAbs){
                if(abs1==min){
                    this.showLine(0,this.vBl_1,hLine1,item);
                    this.posAndSize.x = hLine1.x;
                    if(abs2==min){
                        this.showLine(0,this.vBl_2,hLine2,item);
                        this.posAndSize.w = hLine2.x - hLine1.x;
                    }
                }else if(abs2==min){
                    this.showLine(0,this.vBl_2,hLine2,item);
                    this.posAndSize.x = hLine2.x - w;
                }else{
                    this.showLine(0,this.vBl_1,hLine3,item);
                    this.posAndSize.x = hLine3.x - Math.round(w/2);
                }
            }
        }

    },
    showLine : function (isH,o,line,item){
        var x=item.offsetLeft,y=item.offsetTop,w=item.offsetWidth,h=item.offsetHeight;
        var el = this.util.byId(line.id),ew=el.offsetWidth,eh=el.offsetHeight;
        o.style.display = 'block';
        if(isH){
            o.style.top = line.y +'px';
            if(x> line.x){
                o.style.left = line.x + 'px';
                if(x+w> line.x+ew){
                    o.style.width = x + w - line.x +'px';
                }else{
                    o.style.width = ew +'px';
                }
            }else{
                o.style.left = x + 'px';
                if(line.x+ew>x+w){
                    o.style.width = line.x+ew - x +'px';
                }else{
                    o.style.width = w +'px';
                }
            }
        }else{
            o.style.left = line.x +'px';
            if(y> line.y){
                o.style.top = line.y + 'px';
                if(y+h> line.y+eh){
                    o.style.height = y + h - line.y +'px';
                }else{
                    o.style.height = eh +'px';
                }
            }else{
                o.style.top = y + 'px';
                if(line.y+eh>y+h){
                    o.style.height = line.y+eh - y +'px';
                }else{
                    o.style.height = h +'px';
                }
            }
        }
    },
    clearBsLineEl : function (){
        this.vBl_1.style.display = 'none';
        this.vBl_2.style.display = 'none';
        this.hBl_1.style.display = 'none';
        this.hBl_2.style.display = 'none';
    },
    findLine : function (isH,list,oY){
        if(!list ||list.length==0)return;
        var curI=-1,curAbs=this.dAbs;
        for(var i=0;i<list.length;i++){
            var obj = list[i];
            var abs=isH?Math.abs(obj.y-oY):Math.abs(obj.x-oY);
            if(abs<curAbs){
                curAbs = abs;
                curI = i;
            }else if(abs>curAbs&&abs<this.dAbs){
                break;
            }
        }
        if(curI>=0)return list[curI];
    }
};