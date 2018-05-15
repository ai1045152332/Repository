<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>设备命令码查看|集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>

    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />

    <%--<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>--%>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/public.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/checkbox-user.js"></script>
</head>

<body>
<div class="public">
    <%--<%--%>
        <%--String flag = "1";   //大屏界面--%>
    <%--%>--%>
    <%--<%--%>
        <%--if (flag.equals("1")) {--%>
    <%--%>--%>
    <jsp:include page="../pr_header.jsp"></jsp:include>
    <%--<%--%>
    <%--} else if (flag.equals("0")) {--%>
    <%--%>--%>
    <%--<jsp:include page="../lb_header.jsp"></jsp:include>--%>
    <%--<%--%>
        <%--}--%>
    <%--%>--%>
    <style>
      .user  div{
            display: block;
            float: left;
            line-height: 34px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          -o-text-overflow: ellipsis;
            border: 1px solid:red;

        }
      .code_search_btn,.amd_avarage{
          border-radius: 8px;
          background: #28b779;
          color: white;
          cursor: pointer;
          float: left;
          font-size: 14px;
          height: 30px;
          line-height: 30px;
          margin-top: 2px;
          text-align: center;
          width: 82px;
      }
      .tab_content_listinput {
          font-size: 12px;
          line-height: 15px;
          text-indent: 2px;
          display: block;
          float: left;
          width: 150px;
          margin-top: 2px;
          height: 30px;
      }

      #backp{
          color: white;
          border-radius: 8px;
          background: #28b779;
          cursor: pointer;
          float: left;
          font-size: 14px;
          height: 30px;
          line-height: 30px;
          margin-top: 2px;
          text-align: center;
          width: 82px;
      }
      .csearch{
          float:left;
          margin:5px 5px;
          width:auto
          /*global_checkbox*/
      }
      #pages{
          display: inline;
          bottom: 4.5%;
          height: 25px;
          width:40px;
          float: none;
      }
      .selec{
          float:right;
      }
        .sall {
            height: 36px;
            width: 500px;
        }

        #selectdivall0 {
            width: 115px;
            background-position: 0 0;

        }

        .selectdiv {
            background: none;
            width: 139px;
            height: 27px;
            line-height: 27px;
            margin-top: 3px;
        }

        .selectdivul {
            width: 113px;
        }

        .selectdivul a {
            text-indent: 10px;
            text-align: left;
            width: 113px;
        }

        #selectdivul0 {
            border: 1px solid #dbdbdb;
            /*	margin-top: -2px;*/
            height: 108px;
            width: 113px;
            overflow: hidden;
        }

        .tr {
            background: #fff;
            display: block;
            margin-bottom: 3px;
        }

        .laydate-icon {
            float: left;
            text-indent: 5px;
            margin-top: 2px;
            width: 100px;
        }

        .laydate-icon {
            height: 25px;
            line-height: 25px;
        }

        .deviceloginput {
            border: 1px solid #C6C6C6;
            float: left;
            height: 25px;
            line-height: 25px;
            margin-right: 30px;
            margin-top: 2px;
            outline: none;
            text-indent: 5px;
            width: 120px;
        }

        .wid_20, .wid_16, .wid_10, .wid_6, .wid_24, .wid_15 {
            overflow: hidden;
            text-overflow: ellipsis;
            text-indent: 10px;
            white-space: nowrap;
        }

        #selectdivall0 {
            background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
        }
        input[type="checkbox"] {
            display: block;
            float:left
        }
        .checkline{
            float:left;
            margin:13px 13px;
            width:auto
            /*global_checkbox*/
        }
      .page,.pages_nextpage,.pages_prevpage {
          cursor: pointer;
      }
    </style>
    <div class="mm floatleft">
        <div class="mm_head floatleft">
            <div class="logmargintop" style="width: 1200px;margin-left: 30px;">
                <div class="amd_avarage" style="color:white" href="javascript:void(0)"  onclick="addcode()">添加</div>
                <div class="sall" >
                   <div  style="width: 25px;float: left;line-height: 33px;margin-left: 40px;">
                    <select name="dspecName" id="dspec" class="tab_content_listinput"  onchange="changes()">
                       <%
                   Map<String,Object> dpecnamemaps=(Map<String,Object>)request.getAttribute("dpecNameMap");
                     List<Object[]> dpecnamemap=(List<Object[]>)dpecnamemaps.get("codeadd");%>
                    <option  value="" selected="selected">所有设备</option>
                     <%
                      if(dpecnamemap!=null&&dpecnamemap.size()>0){
                             for (int i=0;i<dpecnamemap.size();i++){
                            %>
                    <option  value="<%=dpecnamemap.get(i)[0]%>"><%=dpecnamemap.get(i)[0]%></option>
                     <%
                              }
                     }
                     %>
                    </select>
                   </div>
                   <div style="width: 25px;float: left;line-height: 33px;text-align: center;margin-left: 140px;">
                      <select name="cmdName" id="cmdname" class="tab_content_listinput">
                     <%
                       Map<String,Object> cmdnamemaps=(Map<String,Object>)request.getAttribute("cmdNameMap");
                       List<Object[]> cmdnamemap=(List<Object[]>)cmdnamemaps.get("codeadd");%>
                        <option value="" selected="selected">所有命令</option>
                     <%
                         if (cmdnamemap!=null&&cmdnamemap.size()>0){
                            for(int i=0;i<cmdnamemap.size();i++){
                      %>
                        <option  value="<%=cmdnamemap.get(i)[0]%>"><%=cmdnamemap.get(i)[0]%></option>
                      <%
                             }
                         }
                     %>
                    </select>
                   </div>
                     <div style="float: right;margin-right: 20px"  class="code_search_btn" onclick="querycode()">查询</div>
                        </div>
                <div style="float: left;" id="backp" onclick="javascript:backpage()">返回首页</div>
            </div>
        </div>
        <div class="user" style="margin-left: 3%;min-width: 0;width: 94%;">
            <div class="table_head " style="margin-top: 15px;">
                <div class="wid_10 ">&nbsp;</div>
                <div class="wid_10 floatleft">命令编码</div>
                <div class="wid_10 floatleft">对应的通用命令id</div>
                <div class="wid_10 floatleft">命令编码备注</div>
                <div class="wid_10 floatleft">命令编码说明</div>
                <div class="wid_10 floatleft" title="通信接口解释标准码">通信接口解释标准码</div>
                <div class="wid_10 floatleft">命令返回数值</div>
                <div class="wid_10 floatleft">命令所属组</div>
                <div class="wid_10 floatleft">设备名称</div>
                <div class="wid_10 floatleft">操作</div>
            </div>
            <div id="loglist" class="wid_100 floatleft hei_93">
                <%
                    Map<String,Object> codelists=(Map<String,Object>)request.getAttribute("codeList");
                    List<Object[]> codelist=(List<Object[]>)codelists.get("codelist");
                    int currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());
                    Page pages=(Page)codelists.get("page");
                    int pageCount=pages.getTotalPageSize();
                    if(codelist!=null && !codelist.isEmpty()){
                        for (int i=0;i<codelist.size();i++)
                        {
                            Object[] commandCode = (Object[])codelist.get(i);
                %>
                <div class="table_recycle">
                    <div class="wid_10  hei_100"><span><%=i+1%></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[1]==null?"":commandCode[1].toString()%>">
                    <%=commandCode[1]==null?"&nbsp;":commandCode[1].toString()%>&nbsp;<!--命令编码--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[2]==null?"":commandCode[2].toString()%>">
                    <%=commandCode[2]==null?"&nbsp;":commandCode[2].toString()%>&nbsp;<!--对应的通用命令id--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[3]==null?"":commandCode[3].toString()%>">
                    <%=commandCode[3]==null?"&nbsp;":commandCode[3].toString()%>&nbsp;<!--命令编码备注--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[4]==null?"":commandCode[4].toString()%>">
                    <%=commandCode[4]==null?"&nbsp;":commandCode[4].toString()%>&nbsp;<!--命令编码说明--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[5]==null?"":commandCode[5].toString()%>">
                    <%=commandCode[5]==null?"&nbsp;":commandCode[5].toString()%>&nbsp;<!--通信接口解释标准码--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[6]==null?"":commandCode[6].toString()%>">
                    <%=commandCode[6]==null?"&nbsp;":commandCode[6].toString()%>&nbsp;<!--命令返回数值--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[7]==null?"":commandCode[7].toString()%>">
                    <%=commandCode[7]==null?"&nbsp;":commandCode[7].toString()%>&nbsp;<!--命令所属组--></span></div>
                    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[8]==null?"":commandCode[8].toString()%>">
                    <%=commandCode[8]==null?"&nbsp;":commandCode[8].toString()%>&nbsp;<!--设备名称--></span></div>
                    <div class="wid_10 floatleft hei_100"><span >
                        <a href="javascript:void(0)" onclick="delcode(<%=commandCode[0]==null?"":commandCode[0].toString()%>)">删除</a>
                        <a href="javascript:void(0)" onclick="updatecode(<%=commandCode[0]==null?"":commandCode[0].toString()%>)">修改</a>
                    </span></div>
                </div>
                <%
                    }

//                    Pager pager=new Pager(pageCount,currentPage,3,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
//                    String pagers=pager.run();
                %>
                <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
                <style>
                #linkpage{
                position: absolute;
                bottom: 4.5%;
                left: 55%;
                text-align:center;
                margin-left: -252px;
                width: 476px;
                }
                .tpage{
                    margin-left: 152px;
                    font-size: large;
                }
                .page div{
                    line-height: 23px;
                    text-align: center;
                    float: none;
                }
                </style>
                <div id="linkpage">
                    <%if(currentPage==1){%>
                    <li class="previous"><span class="pages_prevpage"></span></li>
                    <li class="selected">1</li>
                    <li class="page"><div href="./Devicecode_ccodelist.do?currentPage=2">2</div></li>
                    <li class="page"><div href="./Devicecode_ccodelist.do?currentPage=2">3</div></li>
                    <li class="next"><div href="./Devicecode_ccodelist.do?currentPage=2"><span class="pages_nextpage"></span></div></li>
                    <%--<%}else if (currentPage!=1&&currentPage!=pageCount){%>--%>
                    <%--<li class="previous"><div href="/devicecode/Devicecode_ccodelist.do?currentPage=<%=currentPage-1%>"><span class="pages_prevpage"></span></div></li>--%>
                    <%--<li class="page"><div href="./Devicecode_ccodelist.do?currentPage=<%=currentPage-1%>"><%=currentPage-1%></div></li>--%>
                    <%--<li class="selected"><%=currentPage%></li>--%>
                    <%--<li class="page"><div href="./Devicecode_ccodelist.do?currentPage=<%=currentPage+1%>"><%=currentPage+1%></div></li>--%>
                    <%--<li class="previous"><div href="/devicecode/Devicecode_ccodelist.do?currentPage=<%=currentPage+1%>"><span class="pages_nextpage"></span></div></li>--%>
                    <%--<div selected="selected"><a href="/pages/devicecode/Devicecode_ccodelist.do?currentfPage="+(currentPage-1)>上一页</a></div>--%>
                    <%--<div selected="selected"><a href="/pages/devicecode/Devicecode_ccodelist.do?currentfPage="+(currentPage+1)>下一页</a></div>--%>
                    <%--<% }if (currentPage==pageCount){%>--%>
                    <%--<div disabled="disabled" selected="selected">下一页</div>--%>
                    <%}%>
                    <%--<%=pagers%>--%>

                    <%
                        if (pageCount==0){
                            pageCount=1;}
                        if (pageCount==1){
                    %>
                    <div class="tpage">共<%=pageCount%>页</div>
                       <% }else {
                    %>
                    共<%=pageCount%>页
                    跳转到<select id="pages">
                        <option  value="0" selected="selected">
                                <%
                   for(int i=1;i<=pageCount;i++){%>
                        <option  value="<%=i%>"><%=i%>
                                <% }
                 %>
                    </select>页
                  <input type="button" name="跳转" value="跳转" onclick="go()" />
                    <% }%>
                </div>
                <%
                } else {
                %>
                <style>

                    #selectdivall0 {
                        background-position: 0 -460px;
                        height: 28px;
                        line-height: 28px;
                        width: 115px;

                    }

                    .selectdiv {
                        background: none;
                        width: 139px;
                        height: 28px;
                        line-height: 28px;
                        margin-top: 0;
                    }

                    .selectdivul {
                        height: 140px;
                        max-height: 0;
                        width: 113px;
                    }

                    .selectdivul a {
                        text-indent: 10px;
                        text-align: left;
                        line-height: 28px;
                        width: 113px;
                    }

                    #selectdivul0 {
                        border: 1px solid #dbdbdb;
                        /*	margin-top: -2px;*/
                        height: 108px;
                        width: 113px;
                        overflow: hidden;
                    }

                    .laydate-icon {
                        float: left;
                        text-indent: 5px;
                        width: 100px;
                    }

                    .logsearch , .zdeplay{
                        border-radius: 8px;
                        background: #28b779;
                        color: #fff;
                        cursor: pointer;
                        float: left;
                        font-size: 14px;
                        height: 27px;
                        line-height: 27px;
                        text-align: center;
                        width: 82px;
                    }

                    .deviceloginput {
                        border: 1px solid #C6C6C6;
                        float: left;
                        height: 24px;
                        line-height: 24px;
                        margin-right: 30px;
                        outline: none;
                        text-indent: 5px;
                        width: 120px;
                    }

                </style>
                <div class="error">
                    <%--<div class="error_text"></div>--%>
                    <div class="error_pic">
                        <span class="error_pictext">暂无您要找的数据</span>
                    </div>
                </div>
                <%
                    }
                %>

            </div>
        </div>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
</body>
<script type="text/javascript">
    var root_path = $("#root_path").val();
    function closelayer()
    {
        layer.close(classmanagerlayer);
    }
    var classmanagerlayer = "";
    function addcode()

    {
        $.layer({
            type:2,
            title:'添加命令',
            shadeClose:true,
            maxmin:false,
            fix:false,
            area: ['450px','600px'],
            iframe:{
                src:"./frontend/devicecode/Devicecode_codeDetail.do?codeId=-1"
            },
            end:function(){
                var pages = document.getElementById("pages").value;
                $.post("./frontend/devicecode/Devicecode_ccodelist.do", {currentPage: pages}, function (data) {
                    $("#loglist").html(data);
                }, 'html');
//                location.href="./Devicecode_codeList.do"
            }
        });
    }
    function delcode(codeid)
    {
        layer.confirm("确定删除该命令吗？",function(){
            layer.closeAll();
            $.post("./frontend/devicecode/Devicecode_delete.do",{codeId:codeid},function(data){
                if(data.success==true)
                {
//                    var pages = document.getElementById("pages").value;
//                    $.post("./frontend/devicecode/Devicecode_ccodelist.do", {currentPage: pages}, function (data) {
//                        $("#loglist").html(data);
//                    }, 'html');
                    location.href="./Devicecode_codeList.do"
                }
                else
                {
                    layer.alert("删除失败" , "确定");
                }
            })
        });
    }

    function updatecode(codeId)
    {
        $.layer({
            type:2,
            title:'编辑用户',
            shadeClose:true,
            maxmin:false,
            fix:false,
            area:['450px','600px'],
            iframe:{src:"./Devicecode_codeDetail.do?codeId="+codeId},
            end:function(){
//                var pages = document.getElementById("pages").value;
//                $.post("./frontend/devicecode/Devicecode_ccodelist.do", {currentPage: pages}, function (data) {
//                    $("#loglist").html(data);
//                }, 'html');
                location.href="./Devicecode_codeList.do"
            }
        });
    }
    function changes(){
        var dspecName=$("#dspec").val();

        $.post("./frontend/devicecode/Devicecode_getcmdName.do",{dspecName:dspecName},function(data){

            var html="";
            html+="<option value="+''+">所有命令</option>";
            $.each(data.cmdnamelist,function(key,vals){
//                alert(dspecName);
//               alert( vals[0]);
                html+="<option value="+vals+">"+vals+"</option>";
//                alert(vals);
            });
            $("#cmdname").html(html);
        },'json');
    }
    function querycode()
    {

        var dspecName = encodeURI(document.getElementById('dspec').value);
        var cmdName =encodeURI( document.getElementById('cmdname').value);
        $.post("./frontend/devicecode/Devicecode_codeSearch.do",{dspecName: dspecName,cmdName:cmdName},function (data) {
//            $("#loglist").html("");
            $("#loglist").html(data);

        },'html');
    }
    $("#linkpage li div").live("click",function(){
        //alert("aa");
        var href = $(this).attr("href");
//    alert(href);
        $.get(href,{},function(data){
            $("#loglist").html(data);
        },"html");
        return false;
    });
    function go(){
        var pages = document.getElementById("pages").value;
        $.post("./frontend/devicecode/Devicecode_ccodelist.do", {currentPage: pages}, function (data) {
            $("#loglist").html(data);
        }, 'html');
    }
    function backpage()
    {
        window.location="./Devicecode_codeList.do";
    }
</script>
</html>