/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-3
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */

var DSS = DSS || {};
DSS.Align = {
    posList : null,
    box : DSS.Options.el.mainBoxIn,
    getAllPos : function (){
        var posList=[];
        var ch = this.box.children;
        var obj;
        for(var i=0;i<ch.length;i++)
        {
            obj={id:ch[i].id,top:ch[i].offsetTop,left:ch[i].offsetLeft};
            posList.push(obj);
        }
        return posList;
    },
    setAllPos : function (posList){
        var el;
        for(var i=0;i<posList.length;i++)
        {
            el= DSS.util.byId(posList[i].id);
            el.style.top = posList[i].top+"px";
            el.style.left =  posList[i].left+"px";
        }
    },
    top : function(){
        var ch;
        if(this.box.children.length!=0)
        {
            if(DSS.BlockOpr.getSelectEles().length>=2)
            {
                ch = DSS.BlockOpr.getSelectEles();
            }else
            {
                ch = this.box.children;
            }
            //console.log(ch);
            var yMin=ch[0].offsetTop;
            var topMin;
            for(var i=0;i<ch.length;i++)
            {
                topMin=ch[i].offsetTop;
                if(topMin<yMin)
                {
                    yMin=topMin;
                }
            }
            for(var j=0;j<ch.length;j++)
            {
                ch[j].style.top=yMin+"px";
            }
        }
    },
    bottom : function(){
        var ch;
        if(this.box.children.length!=0)
        {
            if(DSS.BlockOpr.getSelectEles().length>=2)
            {
                ch = DSS.BlockOpr.getSelectEles();
            }else
            {
                ch = this.box.children;
            }
            var yMax=ch[0].offsetTop+ch[0].offsetHeight;
            var topMax;
            for(var i=0;i<ch.length;i++)
            {
                topMax=ch[i].offsetTop+ch[i].offsetHeight;
                if(topMax>yMax)
                {
                    yMax=topMax;
                }
            }
            for(var j=0;j<ch.length;j++)
            {
                ch[j].style.top=(yMax-ch[j].offsetHeight)+"px";
            }
        }
    },
    left : function (){
        var ch;
        if(this.box.children.length!=0)
        {
            if(DSS.BlockOpr.getSelectEles().length>=2)
            {
                ch = DSS.BlockOpr.getSelectEles();
            }else
            {
                ch = this.box.children;
            }
            var xMin=ch[0].offsetLeft;
            var leftMin;
            for(var i=0;i<ch.length;i++)
            {
                leftMin=ch[i].offsetLeft;
                if(leftMin<xMin)
                {
                    xMin=leftMin;
                }
            }
            for(var j=0;j<ch.length;j++)
            {
                ch[j].style.left=xMin+"px";
            }
        }
    },
    right:function(){
        var ch;
        if(this.box.children.length!=0)
        {
            if(DSS.BlockOpr.getSelectEles().length>=2)
            {
                ch = DSS.BlockOpr.getSelectEles();
            }else
            {
                ch = this.box.children;
            }
            var xMax=ch[0].offsetLeft+ch[0].offsetWidth;
            var leftMax;
            for(var i=0;i<ch.length;i++)
            {
                leftMax=ch[i].offsetLeft+ch[i].offsetWidth;
                if(leftMax>xMax)
                {
                    xMax=leftMax;
                }
            }
            for(var j=0;j<ch.length;j++)
            {
                ch[j].style.left=(xMax-ch[j].offsetWidth)+"px";
            }
        }
    },
    horizontal:function(){
        var ch;
        if(this.box.children.length!=0)
        {
            if(DSS.BlockOpr.getSelectEles().length>=2)
            {
                ch = DSS.BlockOpr.getSelectEles();
            }else
            {
                ch = this.box.children;
            }
            var yMin=ch[0].offsetTop+ch[0].offsetHeight/2;
            var topMin;
            for(var i=0;i<ch.length;i++)
            {
                topMin=ch[i].offsetTop+ch[i].offsetHeight/2;
                if(topMin<yMin)
                {
                    yMin=topMin;
                }
            }
            for(var j=0;j<ch.length;j++)
            {
                ch[j].style.top=(yMin-ch[j].offsetHeight/2)+"px";
            }
        }
    },
    vertical:function(){
        var ch;
        if(this.box.children.length!=0)
        {
            if(DSS.BlockOpr.getSelectEles().length>=2)
            {
                ch = DSS.BlockOpr.getSelectEles();
            }else
            {
                ch = this.box.children;
            }
            var xMin=ch[0].offsetLeft+ch[0].offsetWidth/2;
            var leftMin;
            for(var i=0;i<ch.length;i++)
            {
                leftMin=ch[i].offsetLeft+ch[i].offsetWidth/2;
                if(leftMin<xMin)
                {
                    xMin=leftMin;
                }
            }
            for(var j=0;j<ch.length;j++)
            {
                ch[j].style.left=(xMin-ch[j].offsetWidth/2)+"px";
            }
        }
    }
}
