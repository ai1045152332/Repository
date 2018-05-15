<%@ page import="com.honghe.recordweb.util.base.entity.RefreshRandom" %>
<%@ page import="com.honghe.recordweb.util.base.util.GlobalParameter" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
  RefreshRandom refreshRandom = new RefreshRandom();
  int randomjs = refreshRandom.getRandomjs();
  boolean isCampusVersion= GlobalParameter.isCampusVersion();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/home/title.ico" />
  <link rel="Bookmark" href="${pageContext.request.contextPath}/images/home/title.ico" />
  <%if(isCampusVersion){ %>
  <title>鸿合校宣系统</title>
  <%}else{ %>
  <title>鸿合信息发布系统</title>
  <%} %>
  <jsp:include page="/pages/frontend/news/editor/include.jsp"></jsp:include>
  <link href="${pageContext.request.contextPath}/css/frontend/tresource/resource-select.css?randomjs=<%=randomjs %>" rel="stylesheet" type="text/css"></link>
  <!--  <script type="text/javascript">
      if (top.location != self.location)top.location=self.location;
  </script>-->
  <style type="text/css">
    .thumnImage {width:expression(this.width > 70 && (this.width / 70) > (this.height / 60) ? 70 : auto);height:expression(this.height > 60 ? 60 : auto);max-width:70px;max-height:60px;}
  </style>
  <script type="text/javascript">
    var type = "${type}";
    var multi = "${multi}";
    var op = "${op}";
    var prid="${prid}";
    function getResources(myVirtual, srhText) {
      // 在线视频地址回显
      if(op != ''){
        document.getElementById("onlinesource").value=op;
      }
      if(type == 1){ // 视频
        document.getElementById("online").style.display='none';
      }else{
        document.getElementById("online").style.display='none';
      }

      if (myVirtual == null || "" == myVirtual) myVirtual = "/root/";
      $.ajax({
        type    : 'POST',
        async   : false,
        url     : "${pageContext.request.contextPath}/news/ResourceFile_brown.do?type=" + type,
        data    : {srhText : srhText, virtual : myVirtual},
        success : function(data) {
          var COLS = 5;

          var dataObj = eval("(" + data + ")");
          if (dataObj.length == 0) {
            var virtualArray = myVirtual.split("/");
            var newVirtual = "";
            var urlItem = "";
            var urlItemTitle2="";
            var newVirtualtitle2="";
            for (var i = 1; i < virtualArray.length - 1; i++) {
              urlItem += "" == urlItem ? virtualArray[i] : "/" + virtualArray[i];
              var linkVirtual = "<a href='javascript:getResources(\"/" + urlItem + "/\", \"\");'>" + virtualArray[i] + "</a>";
              newVirtual += "" == newVirtual ? linkVirtual : " > " + linkVirtual;
            }
            for (var i = 1; i < virtualArray.length - 1; i++) {
              urlItemTitle2 += "" == urlItemTitle2 ? virtualArray[i] : "/" + virtualArray[i];
              var linkVirtual2 = "<a href='javascript:getResources(\"/" + urlItemTitle2 + "/\", \"\");'>" + (virtualArray[i]=="root"?"公共资源":virtualArray[i]) + "</a>";
              newVirtualtitle2 += "" == newVirtualtitle2 ? linkVirtual2 : " > " + linkVirtual2;
            }
            var allContent="</div></div></div>";
            $("#imagecontent").empty();
            $("#imagecontent").append(allContent);
            $("#roottitle").empty();
            $("#roottitle").append(newVirtualtitle2);
          } else {
            var allVirtual = myVirtual;
            var folderCnt = 0;
            var folderPic = "";
            var folderContent = "";
            $.each(dataObj, function(idx, item){
              var aType = item.type;
              var name = item.name;
              if (name.indexOf(".") >= 0) {
                name = name.substring(0, name.lastIndexOf("."));
              }
              var newVirtual = item.virtual + name + "/";
              if (aType == 0) {
                folderContent +=

                        " <span style='line-height:24px' title='"+name+"' onclick='getResources(\"" + newVirtual + "\", \"\")'>"+name+"</span> "+
                        " </div>point";
                folderPic +=
                        " <div class='tri-image-content'>"+
                        " <img  onclick='getResources(\"" + newVirtual + "\", \"\")' src='${pageContext.request.contextPath}/image/frontend/resources/folder.png' ></br>point";
                folderCnt++;
              }
            });
            $.each(dataObj, function(idx, item){
              var aType = item.type;
              var name = item.name;
              var path = item.path;
              var id = item.id;
              var size = item.uploader;
              var parent_path = path.substring(0, path.lastIndexOf("."));
              path = parent_path + "/thumb.png";
              var res_path = "";
              if (aType == 4) {
                if (item.path.substring(item.path.lastIndexOf("."), item.path.length) == ".gif")
                  res_path = parent_path + "/image.gif";
                else
                  res_path = parent_path + "/image.png";
              }
              if (aType > 0) {
                /*  folderContent += "<td align='left'><input type='" + (multi == "1" ? "checkbox" : "radio") + "' name='resourceChk' id='chk_" + id + "' width='20px' value='" + path.replace("@", "@#") + "@KANG-LIN@" + (item.virtual + name).replace("@", "@#") + "@KANG-LIN@" + size + "@KANG-LIN@" + aType + "' /></td><td align='left' width='*' onclick='selcetPic(" + id + ",\"" + multi + "\");'>" + name + "</td></tr><tr>"; */
                if(multi=="1"){
                  folderContent+=" <div id='"+"chk_"+id+"'class='select-content-checkbox' value='"+path.replace("@", "@#")+ "@KANG-LIN@"+(item.virtual + name).replace("@", "@#")+"@KANG-LIN@"+size+"@KANG-LIN@"+aType+"@KANG-LIN@"+res_path+"'></div> ";
                }else{
                  folderContent+="<input id='"+"chk_"+id+"' type='radio' name='select-content-radiobox' value='"+path.replace("@", "@#")+ "@KANG-LIN@"+(item.virtual + name).replace("@", "@#")+"@KANG-LIN@"+size+"@KANG-LIN@"+aType+"@KANG-LIN@"+res_path+"'></input>";
                }
                folderContent +=

                        " <span  onclick='selcetPic(" + id + ",\"" + multi + "\");' style='line-height:24px' title='"+name+"'>"+name+"</span> "+
                        " </div>point";


                folderPic +=
                        " <div class='tri-image-content'>"+
                        " <img onclick='	    selcetPic(" + id + ",\"" + multi + "\");' src='${pageContext.request.contextPath}" + path + "' ></br>point";
                folderCnt++;
              }
            });
            var allContent = "";
            var contentArray = folderContent.split("point");
            var picArray = folderPic.split("point");
            for (var i = 0; i < contentArray.length; i++) {
              allContent += picArray[i]+""+contentArray[i];
            }
            allContent+="</div></div></div>";
            var virtualArray = allVirtual.split("/");
            var newVirtual = "";
            var newVirtualtitle="";
            var urlItem = "";
            var urlItemTitle="";
            for (var i = 1; i < virtualArray.length - 1; i++) {
              urlItem += "" == urlItem ? virtualArray[i] : "/" + virtualArray[i];
              var linkVirtual = "<a href='javascript:getResources(\"/" + urlItem + "/\", \"\");'>" + virtualArray[i] + "</a>";
              newVirtual += "" == newVirtual ? linkVirtual : " > " + linkVirtual;
            }
            for (var i = 1; i < virtualArray.length - 1; i++) {
              urlItemTitle += "" == urlItemTitle ? virtualArray[i] : "/" + virtualArray[i];
              var linkVirtual = "<a href='javascript:getResources(\"/" + urlItemTitle + "/\", \"\");'>" + (virtualArray[i]=="root"?"公共资源":virtualArray[i]) + "</a>";
              newVirtualtitle += "" == newVirtualtitle ? linkVirtual : " > " + linkVirtual;
            }

            console.log(allContent);
            $("#imagecontent").empty();
            $("#imagecontent").append(allContent);
            $("#rootId").empty();
            $("#rootId").append(newVirtual);

            $("#roottitle").empty();
            $("#roottitle").append(newVirtualtitle);
            addEventForCheckBox();

          }
        }
      });
    }

    function selcetPic(id, multi) {
      if ("1" == multi) {
        var chkid = "chk_" + id;
        if($("#"+chkid).hasClass("checked")){

          $("#"+chkid).removeClass("checked");
        }else{
          $("#"+chkid).addClass("checked");
        }
      } else  {
        var chkid = "chk_" + id;
        document.getElementById(chkid).checked=true;
      }
    }

    function query() {
      var ids = "";
      var objects;
      var resources = new Array();
      if("1" == multi){
        objects = $(".select-content-checkbox");
        if (objects.length > 0) {
          for (var i = 0; i < objects.length; i++) {
            if ($(objects[i]).hasClass("checked")) {
              /* var aValue = objects[i].value; */
              var aValue = objects[i].getAttribute("value");

              var valueArray = aValue.split("@KANG-LIN@");

              var thumbPath = valueArray[0].replace("@#", "@");//.substring(valueArray[0].indexOf("resources/"));
              var size = valueArray[2];
              var width = size.split("*")[0];
              var height = size.split("*")[1];
              var resource = new Object();

              resource.path = valueArray[1].replace("@#", "@");
              //resource.mapped = "/ManagementCenter" + thumbPath;
              resource.mapped = thumbPath.substring(thumbPath.indexOf("/msgResource/data/resources/"));
              resource.snap = thumbPath;
              resource.w = width;
              resource.h = height;
              resource.type = valueArray[3];
              resource.respath = valueArray[4].substring(thumbPath.indexOf("/msgResource/data/resources/"));
              resources.push(resource);
              ids += "" == ids ? aValue : "," + aValue;
            }
          }
        }
      }else{
        var t = document.getElementById("vt").value;
        if(t == 'videoonline'){
          /* var onlinesource = document.getElementById("onlinesource").value;
           onlinesource = onlinesource.replace(/\s/g,'');  // 去除所有空格
           var resource = new Object();
           resource.path = onlinesource;
           resource.mapped = "/ManagementCenter/data/defaultvideothumb/thumb.png";
           resource.snap = "/ManagementCenter/data/defaultvideothumb/thumb.png";
           resource.w = "480";
           resource.h = "360";
           resource.type = "1";
           resource.respath = "";
           resources.push (resource);  */
        }else{
          objects =$(":radio");
          if (objects.length > 0) {
            for (var i = 0; i < objects.length; i++) {
              if (objects[i].checked) {
                var aValue = objects[i].getAttribute("value");
                var valueArray = aValue.split("@KANG-LIN@");
                console.log(valueArray);
                var thumbPath = valueArray[0].replace("@#", "@");//.substring(valueArray[0].indexOf("resources/"));
                console.log(thumbPath);
                var size = valueArray[2];
                var width = size.split("*")[0];
                var height = size.split("*")[1];
                var resource = new Object();
                resource.path = valueArray[1].replace("@#", "@");
                //resource.mapped = "/ManagementCenter" + thumbPath;
                resource.mapped = thumbPath.substring(thumbPath.indexOf("/msgResource/data/resources/"));
                resource.snap = thumbPath;
                if(valueArray[3] == 2 || valueArray[3] == 3 || valueArray[3]==5){
                  valueArray[3] = 6;
                  var srcpath = thumbPath.replace("thumb","1");//默认显示第一页
                  resource.mapped = srcpath;
                }else {
                  //var srcpath = thumbPath.replace("thumb","1");//默认显示第一页
                  resource.mapped = thumbPath;
                }
                resource.w = width;
                resource.h = height;

                resource.type = valueArray[3];
                resource.respath = valueArray[4].substring(thumbPath.indexOf("/msgResource/data/resources/"));
                resources.push(resource);
                ids += "" == ids ? aValue : "," + aValue;
              }
            }
          }
        }
      }


      if (resources.length == 0) {
        $.messager.alert('提示', '请选择记录进行操作!', 'info');
        return false;
      }
      return resources;
    }
    function doSearch(value,name){

      getResources("", value);
    }
    function addEventForCheckBox(){
      $(".select-content-checkbox").click(function(){
        if($(this).hasClass("checked")){
          $(this).removeClass("checked");
        }else{
          $(this).addClass("checked");
        }

      })
    }
    // 资源库的视频  or 在线视频
    function getVideoType(){
      var t = document.getElementById("vt").value;
      return t;
    }
    function IsURL(str_url){
      var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.mp4 (:s[0-9]{1-4})?/$";
      var re = new RegExp(strRegex);
      return re.test(str_url);
    }
    // 获取在线视频的src
    function getVideoOnlineSource(){
      var onlinesource = document.getElementById("onlinesource").value;
      onlinesource = onlinesource.replace(/\s/g,'');  // 去除所有空格
      if(onlinesource == ''){
        alert("请输入在线视频地址！");
      }
      if(onlinesource != '' && (onlinesource.indexOf("/") == -1 || onlinesource == '/')){ // 格式验证
        alert("在线视频地址格式不对！");
        onlinesource = "";
      }
      if(onlinesource != ''){ // 格式验证
        var a = onlinesource.split(".");
        var b =  a[a.length-1];
        if(b != 'mp4'){
          alert("在线视频地址格式不对！");
          onlinesource = "";
        }
      }
      return onlinesource;
    }
    // 资源库 or 在线视频 div切换
    function showVideo(obj){

      sibls(obj);

      var curId = obj.getAttribute('id');
      if(curId == 'resources'){
        document.getElementById("videoresources").style.display='block';
        document.getElementById("videoonline").style.display='none';
        document.getElementById("vt").value="videoresources";
      }else if(curId == 'online'){
        document.getElementById("videoonline").style.display='block';
        document.getElementById("videoresources").style.display='none';
        document.getElementById("vt").value="videoonline";
      }
    }

    //点击切换
    function sibls(obj){
      var thisParent = obj.parentNode;
      var thisChilds =  thisParent.children;
      obj.className = 'selected';
      for(var i=0;i<thisChilds.length-1;i++){
        if(thisChilds[i] != obj){
          thisChilds[i].className = '';
        }
      }
    }
  </script><style>
  .search-box-right span.searchbox, .search-box-right span input {
    border-width: 1px;
    border-color: gray;
  }
</style>

</head>
<body style="width:700px;height:500px;background-color:#FFFFFF;" onload="getResources('', '');">
<input type="hidden" id="vt" value="videoresources"/>
<!--节目编辑的弹出框-->
<div align="left" style="background-color:#46AF4B;height:38px;padding-left:6px;">
  <font color="#FFFFFF" style="text-align:center;font-size:16px; line-height: 38px;margin-top: 6px;font-weight:800;">添加资源</font>
</div>
<div class="all-picture-content">
  <div class="select-nav">
    <!--  <p>上传</p> -->
    <p class="selected" id="resources" onclick="showVideo(this)">资源库</p>
    <p id="online" onclick="showVideo(this)">在线视频</p>
    <p class="selected" onclick="window.location.href = '${pageContext.request.contextPath}/tresource/Tresource_home.do?module=Resource&prid=${prid}'">上传资源</p>
  </div>
  <div class="add-picture-right" id='videoresources'>
    <div class="search-box-right">
      <!-- <INPUT class="easyui-searchbox" id="ss" style="width: 100%;" data-options="searcher:doSearch,prompt:'查找用户名/姓名',menu:'#mm'"> -->
      <input id="ss" class="easyui-searchbox" data-options="prompt:&#39;请输入资源名称&#39;,menu:&#39;#mm&#39;,searcher:doSearch"
             style="width: 199px; height: 29px; display: none;margin-top:5px;border-radius: 6px;"></div>
    <div class="bread"><span id="rootId" style="display:none"></span><span id="roottitle"></span></div>
    <div id ="imagecontent" class="image-content">

    </div>
  </div>
  <div class="add-picture-right add-videoonline" id='videoonline' style="display: none">
    <p>视频地址 :</p>
    <input type="text" id='onlinesource'/>
    <p>请确保输入的视频地址格式.mp4的格式，如：http://127.0.0.1:7001/1.mp4</p>
  </div>
</div>


</body>
</html>

