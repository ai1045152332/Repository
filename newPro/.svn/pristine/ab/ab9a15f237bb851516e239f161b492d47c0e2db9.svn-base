/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-7
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
var DSS = DSS || {};
DSS.Level={
    levelList : null,
    box : DSS.Options.el.mainBoxIn,

    getAllLevel : function (){
        var levelList=[];
        var ch = this.box.children;
        var obj;
        for(var i=0;i<ch.length;i++)
        {

            obj={id:ch[i].id,zIndex:ch[i].style.zIndex};
            levelList.push(obj);
        }
        return levelList;
    },
    setAllLevel : function (levelList){

        var el;
        for(var i=0;i<levelList.length;i++)
        {
            el= DSS.util.byId(levelList[i].id);
            el.style.zIndex = levelList[i].zIndex;
        }
    },
    top:function(itemId){
        var ch = this.box.children;
        var obj=new Object();
        var el;
        levelList=new Array();
        for(var i=0;i<ch.length;i++)
        {
            obj={num:ch[i].id,zIndex:ch[i].style.zIndex};
            levelList.push(obj);
        }
        levelList=this.sortArr(levelList).concat();
        var strI;
        for(var i=0;i<levelList.length;i++)
        {
            if(levelList[i].num==itemId)
            {
                strI=i;
            }
        }
        var strIndex=levelList[0].zIndex;
        for(var n=0;n<=strI;n++)
        {
            if(n==strI)
            {
                levelList[strI].zIndex=strIndex;
            } else
            {
                levelList[n].zIndex=levelList[n+1].zIndex;
            }
        }
        for(var m=0;m<levelList.length;m++)
        {
            el=DSS.util.byId(levelList[m].num);
            el.style.zIndex=levelList[m].zIndex;
        }
    },
    bottom:function(itemId){
        var ch = this.box.children;
        var obj=new Object();
        var el;
        levelList=new Array();
        for(var i=0;i<ch.length;i++)
        {
            obj={num:ch[i].id,zIndex:ch[i].style.zIndex};
            levelList.push(obj);
        }
        levelList=this.sortArr(levelList).concat();
        var strI;
        for(var i=0;i<levelList.length;i++)
        {
            if(levelList[i].num==itemId)
            {
                strI=i;
            }
        }
        var strIndex=levelList[levelList.length-1].zIndex;
        for(var n=levelList.length-1;n>=strI;n--)
        {
            if(n==strI)
            {
                levelList[strI].zIndex=strIndex;
            } else
            {
                levelList[n].zIndex=levelList[n-1].zIndex;
            }
        }
        for(var m=0;m<levelList.length;m++)
        {
            el=DSS.util.byId(levelList[m].num);
            el.style.zIndex=levelList[m].zIndex;
        }
    },
    moveUp:function(itemId){
        var ch = this.box.children;
        var el=DSS.util.byId(itemId);

        var divOffsetLeft=parseInt(el.offsetLeft);
        var divOffsetTop=parseInt(el.offsetTop);
        var divOffsetWidth=parseInt(el.offsetWidth);
        var divOffsetHeight=parseInt(el.offsetHeight);

        var divCompareOffsetLeft;
        var divCompareOffsetTop;
        var divCompareOffsetWidth;
        var divCompareOffsetHeight;

        levelList=new Array();
        for(var i=0;i<ch.length;i++)
        {
            obj={num:ch[i].id,zIndex:ch[i].style.zIndex};
            levelList.push(obj);
        }
        levelList=this.sortArr(levelList).concat();

        var strI;
        var strJ=0;
        /*for(var i=levelList.length-1;i>=0;i--){
        	 if( i!=0 && levelList[i].num==itemId ){
        		 console.info("i = "+i);
        		 var az= levelList[i].zIndex;
        		 var bz= levelList[i-1].zIndex;
        		 console.info(levelList[i].num + " -- "+levelList[i].zIndex);
        		 console.info(levelList[i-1].num + " -- "+levelList[i-1].zIndex);
        		 console.info(levelList[i].num + " : "+DSS.util.byId(levelList[i].num).style.zIndex);
        		 console.info(levelList[i-1].num + " : "+DSS.util.byId(levelList[i-1].num).style.zIndex);
        		 DSS.util.byId(levelList[i].num).style.zIndex = bz;
        		 DSS.util.byId(levelList[i-1].num).style.zIndex=az;
        		 console.info(levelList[i].num + " = "+DSS.util.byId(levelList[i].num).style.zIndex);
        		 console.info(levelList[i-1].num + " = "+DSS.util.byId(levelList[i-1].num).style.zIndex);
             }
        }*/
        
        for(var i=0;i<levelList.length;i++)
        {
            if(levelList[i].num==itemId)
            {
                strI=i;
                if(i!=0)
                {
                    for(var j=0;j<i;j++)
                    {	
                        divCompareOffsetLeft=parseInt((DSS.util.byId(levelList[j].num).style.left).slice(0,-2));
                        divCompareOffsetTop=parseInt((DSS.util.byId(levelList[j].num).style.top).slice(0,-2));
                        divCompareOffsetWidth=parseInt(DSS.util.byId(levelList[j].num).offsetWidth);
                        divCompareOffsetHeight=parseInt(DSS.util.byId(levelList[j].num).offsetHeight);
                        if((((divOffsetLeft>divCompareOffsetLeft)&&(divOffsetLeft<(divCompareOffsetLeft+divCompareOffsetWidth)))
                            ||(((divOffsetLeft+divOffsetWidth)>divCompareOffsetLeft)&&(divOffsetLeft<(divCompareOffsetLeft+divCompareOffsetWidth))))
                            &&(((divOffsetTop>divCompareOffsetTop)&&(divOffsetTop<(divCompareOffsetTop+divCompareOffsetHeight)))
                            ||(((divOffsetTop+divOffsetHeight)>divCompareOffsetTop)&&((divOffsetTop+divOffsetHeight)<(divCompareOffsetTop+divCompareOffsetHeight))))
                            )
                        {
                            strJ=j;
                        }
                    }
                }else
                {
                    strJ=0;
                }
            }
        }
        var strJzIndex=levelList[strJ].zIndex;
        for(var k=strJ;k<=strI;k++)
        {
            if(k==strI)
            {
                levelList[k].zIndex=strJzIndex;
            }else
            {
                levelList[k].zIndex=levelList[k+1].zIndex;
            }
        }
        for(var n=0;n<levelList.length;n++)
        {
            DSS.util.byId(levelList[n].num).style.zIndex=levelList[n].zIndex;
        }
    },
    moveDown:function(itemId){
        var ch = this.box.children;
        var el=DSS.util.byId(itemId);

        var divOffsetLeft=parseInt(el.offsetLeft);
        var divOffsetTop=parseInt(el.offsetTop);
        var divOffsetWidth=parseInt(el.offsetWidth);
        var divOffsetHeight=parseInt(el.offsetHeight);

        var divCompareOffsetLeft;
        var divCompareOffsetTop;
        var divCompareOffsetWidth;
        var divCompareOffsetHeight;

        levelList=new Array();
        for(var i=0;i<ch.length;i++)
        {
            obj={num:ch[i].id,zIndex:ch[i].style.zIndex};
            levelList.push(obj);
        }
        levelList=this.sortArr(levelList).concat();

        var strI;
        var strJ=levelList.length-1;
        /*for(var i=levelList.length-1;i>=0;i--){
       	 	if( i!=levelList.length-1 && levelList[i].num==itemId ){
	       		 console.info("i = "+i);
	       		 var az= levelList[i].zIndex;
	       		 var bz= levelList[i+1].zIndex;
	       		 console.info(levelList[i].num + " -- "+levelList[i].zIndex);
	       		 console.info(levelList[i+1].num + " -- "+levelList[i+1].zIndex);
	       		 console.info(levelList[i].num + " : "+DSS.util.byId(levelList[i].num).style.zIndex);
	       		 console.info(levelList[i+1].num + " : "+DSS.util.byId(levelList[i+1].num).style.zIndex);
	       		 DSS.util.byId(levelList[i].num).style.zIndex = bz;
	       		 DSS.util.byId(levelList[i+1].num).style.zIndex=az;
	       		 console.info(levelList[i].num + " = "+DSS.util.byId(levelList[i].num).style.zIndex);
	       		 console.info(levelList[i+1].num + " = "+DSS.util.byId(levelList[i+1].num).style.zIndex);
            }
       }*/
        for(var i=0;i<levelList.length;i++)
        {
            if(levelList[i].num==itemId)
            {
                strI=i;
                if(i!=levelList.length-1)
                {
                    for(var j=levelList.length-1;j>=i+1;j--)
                    {	
                        divCompareOffsetLeft=parseInt(DSS.util.byId(levelList[j].num).offsetLeft);
                        divCompareOffsetTop=parseInt(DSS.util.byId(levelList[j].num).offsetTop);
                        divCompareOffsetWidth=parseInt(DSS.util.byId(levelList[j].num).offsetWidth);
                        divCompareOffsetHeight=parseInt(DSS.util.byId(levelList[j].num).offsetHeight);
                        if((((divOffsetLeft>divCompareOffsetLeft)&&(divOffsetLeft<(divCompareOffsetLeft+divCompareOffsetWidth)))
                            ||(((divOffsetLeft+divOffsetWidth)>divCompareOffsetLeft)&&(divOffsetLeft<(divCompareOffsetLeft+divCompareOffsetWidth))))
                            &&(((divOffsetTop>divCompareOffsetTop)&&(divOffsetTop<(divCompareOffsetTop+divCompareOffsetHeight)))
                            ||(((divOffsetTop+divOffsetHeight)>divCompareOffsetTop)&&((divOffsetTop+divOffsetHeight)<(divCompareOffsetTop+divCompareOffsetHeight))))
                            )
                        {
                            strJ=j;
                        }
                    }
                }else
                {
                    strJ=(levelList.length-1);
                }
            }
        }
        var strJzIndex=levelList[strJ].zIndex;

        for(var k=strJ;k>=strI;k--)
        {

            if(k==strI)
            {
                levelList[k].zIndex=strJzIndex;
            }else
            {
                levelList[k].zIndex=levelList[k-1].zIndex;
            }
        }
        for(var n=0;n<levelList.length;n++)
        {
            DSS.util.byId(levelList[n].num).style.zIndex=levelList[n].zIndex;
        }
    },
    sortArr:function(arr){
        var arr1=new Array();
        var maxArr;
        var maxI=0;
        var arrLengthStart=arr.length;
        var arrLengthEnd=arr.length;
        for(var j=0;j<arrLengthStart;j++)
        {
            arrLengthEnd=arr.length;
            maxArr=0;
            for(var i=0;i<arrLengthEnd;i++)
            {
                if(parseInt(arr[i].zIndex)>=parseInt(maxArr))
                {
                    maxArr=arr[i].zIndex;
                    maxI=i;
                }
            }
//            arr[maxI].zIndex=arrLengthStart-j;
            arr1.push(arr[maxI]);
            arr.splice(maxI,1);
        }
        //alert(arr1.length);
        return arr1;
    },
    countZIndex:function()
    {
       return (this.box.children.length+1);
    }
};


