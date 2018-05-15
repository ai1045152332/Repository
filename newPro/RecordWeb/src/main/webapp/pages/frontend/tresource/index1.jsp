<%@ page import="com.honghe.recordweb.util.base.entity.RefreshRandom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
  RefreshRandom refreshRandom = new RefreshRandom();
  int randomjs = refreshRandom.getRandomjs();
%>
<!-- 引入模块自有的js -->
<!-- 根据其所选择的语言做框架的选择调整 -->
<!--uploadify相关js-->
<link href="${pageContext.request.contextPath}/css/frontend/tresource/progressbar.css?randomjs=<%=randomjs %>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/frontend/tresources/upload_dir.css?randomjs=<%=randomjs %>" type="text/css" />
<link href="${pageContext.request.contextPath}/js/frontend/tresources/uploadify.css?randomjs=<%=randomjs %>" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/frontend/tresource/resource-master.css?randomjs=<%=randomjs %>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/zTree_v3/css/zTreeStyle/zTreeStyle.css?randomjs=<%=randomjs %>" type="text/css">
<link id="master" rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/plan/planstyle.css?randomjs=<%=randomjs %>" type="text/css"/>
<link id="master" rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/device/content-tool.css?randomjs=<%=randomjs %>" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/tresource/resource-preview.css?randomjs=<%=randomjs %>" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/tresources/jquery.uploadify.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/tresources/jquery.uploadify.min.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/tresources/md5.js?randomjs=<%=randomjs %>"></script>
<!-- zTree相关 js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/trees.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/zTree_v3/js/jquery.ztree.core-3.5.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/zTree_v3/js/jquery.ztree.excheck-3.5.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/zTree_v3/js/jquery.ztree.exedit-3.5.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/easyui-extends.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/autoheight.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/subclass.css?randomjs=<%=randomjs %>" type="text/css">

<style type="text/css">
  .ztree li ul{ margin:0; padding:0}
  .ztree li {line-height:30px;}
  .ztree li a {width:200px;height:30px;padding-top: 0px;}
  .ztree li a:hover {text-decoration:none; background-color: #00CC33;}
  .ztree li a span.button.switch {visibility:hidden}
  .ztree.showIcon li a span.button.switch {visibility:visible}
  .ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
  .ztree li span {line-height:30px;}
  .ztree li span.button {margin-top: -7px;}
  .ztree li span.button.switch {width: 16px;height: 16px;}
  .ztree li ul.level0 {padding:0; background:none;}
</style>



<script type="text/javascript">
  var dg;
  var sortsign= 'asc';
  var searchFlag=false;
  function mysort(){
    if(sortsign== 'asc'){
      dg.datagrid('reload',{sortOrder:'desc'});
      sortsign = 'desc';
      return;
    }else if(sortsign== 'desc'){
      dg.datagrid('reload',{sortOrder:'asc'});
      sortsign = 'asc';
      return;
    }
  }
  var module="${moduleForOperate}";
  function disTitle() {
    var allVirtual = "${virtualPreView}";
    var currentPathId="${currentPathId}";

    var virtualArray = allVirtual.split("/");
    var newVirtual = "";
    var urlItem = "";
    for (var i = 1; i < virtualArray.length - 1; i++) {
      urlItem += "" == urlItem ? virtualArray[i] : "/" + virtualArray[i];
      var param=encodeURIComponent("/"+urlItem+"/");
      var linkVirtual = "<a href='${pageContext.request.contextPath}/resource/Resource_home.do?module=Resource&virtualPreView=" + param + "'>" + (virtualArray[i]=="root"?"公共资源":virtualArray[i]) + "</a>";
      newVirtual += "" == newVirtual ? linkVirtual : "  \\  " + linkVirtual;
    }
    $('#disTitle').html("<span class=\'master-header-span\' id=\'playplanContentHeader\' style=\'margin-top:11px\' '>"+newVirtual+"</span>");
    $('#pathIdForSubmit').val(currentPathId);
  }

  function moveToFolder() {
//    if(checkEdeitPermission(module)){
//      return;
//    }
    var rows = $('#dg').datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
      $.messager.alert('提示', '请至少选择一条记录进行操作!', 'info');
      $('#dg').datagrid('clearSelections');
      return;
    } else {
      var ids = [];
      for ( var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
      }
      if(operateFilter(ids)){
        return;
      }
      var arr = ids.join(',');
      var random=Math.random();
      $.ajax({
        url : '${pageContext.request.contextPath }/tresource/Tresource_havaSub.do?flag='+random,
        data : {
          data : arr
        },
        async : false,
        dataType : 'json',
        success : function(result) {
          var json = eval("("+ result + ")");
          if (json.fla=="2") {

            $("#booleanForDelete").val('2');
          }else if(json.fla=="1"){

            $("#booleanForDelete").val('1');
          }else{

            $("#booleanForDelete").val('0');
          }

        }
      });
      flag=$("#booleanForDelete").val();
      if(flag=="2"){
        $.messager.alert('失败','目录下有文件，不能移动！','info');
        return;
      }else if(flag=="1"){
        $.messager.alert('失败','目录不为空或者有审核相关的文件，不能移动！','info');
        return;
      }


      for(var i=0;i<rows.length;i++){
        rows[i].checked=0;
      }
      $('#dMoveToFolder').dialog('open');
      var treeObj = $("#treeDemo2");
      $.fn.zTree.init(treeObj, setting2);
    }
  }

  function downloadItems() {
    var rows = $('#dg').datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
      $.messager.alert('提示', '请选择一条资源记录进行下载操作!', 'info');
      $('#dg').datagrid('clearSelections');
      return;
    } else if (num > 1) {
      $.messager.alert('提示', '请选择一条资源记录进行下载操作!', 'info');
      return;
    } else {
      for ( var i = 0; i < rows.length; i++) {
        if(rows[i].type == 0) {
          $.messager.alert('提示', '请不要选择文件夹,选择一条资源记录进行下载操作!', 'info');
          return;
        }
      }
      /*        var n = 0;
       var arrayObj = new Array(); */
      for (var i = 0; i < num; i++) {
        var content = rows[i].id;
        var contentName = rows[i].name;
        $.ajax({
          url : '${pageContext.request.contextPath }/tresource/Tresource_getMd5name.do',
          data : {
            data : content
          },
          async : false,
          dataType : 'json',
          success : function(result) {
            var jsonResult=eval('('+result+')');
            $("#downloadName").val(jsonResult.md5name);
            $("#previewPath").val(jsonResult.path);
          }
        });
        var name=$("#downloadName").val();
        var pathFolder=$("#previewPath").val();
        window.location.href=encodeURI(requestContextPath+"/data/resources/"+pathFolder+"/"+name+'?n='+contentName);
        /*  setTimeout(function(){
         var name = arrayObj[n];
         var suffix = name.lastIndexOf(".") >= 0 ? name.substring(name.lastIndexOf(".")) : "";
         var myUrl = "/ManagementCenter/device/Device_downloadResource.do?name=" + encodeURIComponent(contentName + suffix) + "&url=" + encodeURIComponent(requestContextPath+"/data/resources/1/" + name);
         window.open(myUrl, "_blank", "");
         n++;
         }, n * 100); */
      }
    }
  }

  var arrayO = new Array();
  function isExist(emailAddr){
    arrayO = $("#aryo").val().split(",");
    if(arrayO.length ==0){
      return -1;
    }
    for(var i=0;i<arrayO.length;i++){
      //alert(emailAddr + "  isExist :　"+arrayO[i]);
      if(emailAddr == arrayO[i])
        return i;
    }
    return -1;
  }
  var sign = 0;
  function gh() {

    $("#file_upload").uploadify({
      'successTimeout' : 600,
      'buttonText' : '请选择文件',
      'auto'     : false,
      /*   'queueSizeLimit': 10,   */
      'overrideEvents' : [ 'onDialogClose', 'onUploadError', 'onSelectError' ],
      'formData'      : {'pathId':''},
      'cancelImg': requestContextPath+'/image/frontend/resources/resourceUpload-cancel.png',
      'removeCompleted' : true, //上传成功后是否自动消失
      'fileTypeDesc' : '请选择后缀为  的文件',
      'fileTypeExts' : '*.ppt;*.pptx;*.gif; *.bmp; *.jpg; *.jpeg; *.png; *.avi; *.wmv; *.mov; *.rm; *.rmvb; *.mpg; *.mpeg; *.mp4; *.vob; *.flv; *.f4v; *.mkv; *.3gp; *.m4v; *.ts;*.pps; *.swf; *.pdf; *.doc; *.docx; *.xls; *.xlsx; ',
      'fileObjName' : 'file',
      'swf'        : requestContextPath+'/js/common/uploadify.swf',
      'fileSizeLimit'   : 2000*1024,
      'uploader' : requestContextPath+'/tresource/TresourceUploadByUploadify_md5Validate.do;jsessionid=${pageContext.session.id}',
      onDialogClose : function(swfuploadifyQueue) {//当选择文件对话框打开时触发

      },
      onDialogOpen : function() {//当选择文件对话框打开时触发
        //alert("aa")
        //$('#file_upload').uploadify('cancel', '*');
      },
      'onUploadStart' : function(file) {
        var obj=file.name;
        //alert(obj)
        var name=obj.substring(0,obj.lastIndexOf('.'));
        var na=name;
        var md5na=hex_md5(na);
        $("#"+md5na).attr("style"," background:#3F9; height:48px; width:100%");
        $("#"+md5na+"3").attr("class","");
        //校验
        //var pathId=$('#pathIdForSubmit').val();
        var pathId=$('#pathId').val();
        if(pathId==''||pathId==null){
          $.messager.alert("错误","上传路径不能为空","error");
          return false;
        }
        $("#file_upload").uploadify("settings", "formData", {'pathId':pathId});
      } ,
      //进度条控制
      'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
        var obj=file.name;
        var name=obj.substring(0,obj.lastIndexOf('.'));
        var na=name;
        var md5na=hex_md5(na);
        var md5na3= file.id;
        $("#"+md5na3).attr("style"," background:#3F9; height:48px; width:"+bytesUploaded*100/bytesTotal+"%");
      $("#"+md5na3+"bar").attr("class","loading")
       // alert(Math.floor(bytesUploaded*100/bytesTotal)+'%')
        if(bytesUploaded==bytesTotal){
        	$("#"+md5na3+"bar").attr("class","")
          $("#"+md5na).attr("style","height:48px;width:100%;");
          $("#p4 div[id='"+md5na3+"']").attr("class","resource-progressbar");
          //    	 sendRequest(na);
        }
      },
      //选中文件
      'onSelect' : function(file) {
        var obj=file.name;
        var name=obj.substring(0,obj.lastIndexOf('.'));
        var str=name;
        var pathid=$('#pathIdForSubmit').val();

        $("#uploadFlag").val("1");
        var obj=file.name;
        var name=obj.substring(0,obj.lastIndexOf('.'));
        var nasu = name;
        var na=name;
        if(nasu.length>30){
          nasu = trunc(nasu,13,300);
        }
        var md5na =hex_md5(na);
        var md5na3= file.id;

        if(arrayO.length>0){
          var exist = isExist(file.name);
          if(exist<0){ //数组不包含元素
            arrayO.push(obj);
            $("#aryo").val(arrayO);
            $("#p3").append("<div class='2' id='"+md5na3+"' style='position:relative; border:1px #CCC solid;  height:48px; width:690px; margin:0 auto; padding:1px'> <div id='"+md5na+"' style=' background:#3F9; height:48px; width:0px'></div><strong  style='position:absolute; width:690px; top:7px; overflow:hidden'><div title="+na+" style='height:48px;width:340px;float:left;font-weight:normal'>"+nasu+"</div> <div style='height:48px;width:105px;float:left;font-weight:normal'>"+Math.floor(file.size/1024)+"kb</div><div style='height:48px;width:145px;float:left;font-weight:normal'></div> <div id='"+md5na3+"bar"+"'style='height:48px;width:50px;float:left;font-weight:normal'></div> <div><a name="+md5na3+" id="+md5na3+"pro"+" class='resourceUpload-cancel'/></div></strong> </div>");
          }
          if(exist>=0){ //数组包含元素
            arrayO.splice(exist,1);
            c("#p3").children("div[id=\'"+d+"\']").remove();
            c("#p4").children("div[id=\'"+d+"\']").remove();
            $("#aryo").val(arrayO);
            $("#p3").append("<div class='2' id='"+md5na3+"' style='position:relative; border:1px #CCC solid;  height:48px; width:690px; margin:0 auto; padding:1px'> <div id='"+md5na+"' style=' background:#3F9; height:48px; width:0px'></div><strong  style='position:absolute; width:690px; top:7px; overflow:hidden'><div title="+na+" style='height:48px;width:340px;float:left;font-weight:normal'>"+nasu+"</div> <div style='height:48px;width:105px;float:left;font-weight:normal'>"+Math.floor(file.size/1024)+"kb</div><div style='height:48px;width:145px;float:left;font-weight:normal'></div> <div id='"+md5na3+"bar"+"'style='height:48px;width:50px;float:left;font-weight:normal'></div> <div><a name="+md5na3+" id="+md5na3+"pro"+" class='resourceUpload-cancel'/></div></strong> </div>");

          }
        }
        else if(arrayO.length == 0){
          arrayO = new Array();
          arrayO.push(obj);
          $("#aryo").val(arrayO);
          $("#p3").append("<div class='2' id='"+md5na3+"' style='position:relative; border:1px #CCC solid;  height:48px; width:690px; margin:0 auto; padding:1px'> <div id='"+md5na+"' style=' background:#3F9; height:48px; width:0px'></div><strong  style='position:absolute; width:690px; top:7px; overflow:hidden'><div title="+na+" style='height:48px;width:340px;float:left;font-weight:normal'>"+nasu+"</div> <div style='height:48px;width:105px;float:left;font-weight:normal'>"+Math.floor(file.size/1024)+"kb</div><div style='height:48px;width:145px;float:left;font-weight:normal'></div> <div id='"+md5na3+"bar"+"'style='height:48px;width:50px;float:left;font-weight:normal'></div> <div><a name="+md5na3+" id="+md5na3+"pro"+" class='resourceUpload-cancel'/></div></strong> </div>");
        }else{
          arrayO.push(obj);
          $("#aryo").val(arrayO);
          $("#p3").append("<div class='2' id='"+md5na3+"' style='position:relative; border:1px #CCC solid;  height:48px; width:690px; margin:0 auto; padding:1px'> <div id='"+md5na+"' style=' background:#3F9; height:48px; width:0px'></div><strong  style='position:absolute; width:690px; top:7px; overflow:hidden'><div title="+na+" style='height:48px;width:340px;float:left;font-weight:normal'>"+nasu+"</div> <div style='height:48px;width:105px;float:left;font-weight:normal'>"+Math.floor(file.size/1024)+"kb</div><div style='height:48px;width:145px;float:left;font-weight:normal'></div> <div id='"+md5na3+"bar"+"'style='height:48px;width:50px;float:left;font-weight:normal'></div> <div><a name="+md5na3+" id="+md5na3+"pro"+" class='resourceUpload-cancel'/></div></strong> </div>");
        }
      },
//文件上传完成事件        onUploadSuccess  onUploadComplete
      'onUploadSuccess' : function(file,data,response) {
        alert("456")
        var obj=file.name;
        var name=obj.substring(0,obj.lastIndexOf('.'));
        var na=name;
        var md5na=hex_md5(na);
        var md5na3 = file.id;
        $("#p4 div[id='"+file.id+"']").removeClass('resource-progressbar');
        $("#p4 div[id='"+file.id+"']").addClass('resource-progressbar-ok');
        $("#"+md5na3+"pro").attr("class","resourceUpload-ok");
        $("#"+md5na3+"pro").unbind("click");
        $("#"+md5na3+"bar").html('100%');
      },
      'onSelectError': function (file, errorCode, errorMsg) {
        alert("789")
        switch (errorCode) {
          case -100:
            $.messager.alert('提示', "选择上传的文件数量已经超出系统限制的" + jQuery('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！", 'info');
            break;
          case -110:
            $.messager.alert('提示',"文件 [" + file.name + "] 大小超出系统限制的" + "2Gb"+ "大小！", 'info');
            break;
          case -120:
            $.messager.alert('提示',"文件 [" + file.name + "] 大小异常！", 'info');
            break;
          case -130:
            $.messager.alert('提示',"文件 [" + file.name + "] 类型不正确！", 'info');
            break;
        }
      },
//文件列表上传完成时间
      'onQueueComplete' : function(queueData) {
        //  gh();
        bbupload();
        valid();
        closedd4(queueData.uploadsSuccessful,queueData.uploadsErrored);
        queueData.uploadsSuccessful = 0;
        queueData.uploadsErrored = 0;
        cancelPathSelect2();
        $("#uploadFlag").val("0");
        $("#aryo").val("");
      },
      'onCancel': function(fileObj)   {
        if(sign == 2){ // 1-- 文件重复   2--文件上传
          alert("取消文件   " + fileObj.name+ " 上传!");
        }
        sign = 0;
        return;
      }
    });
  }
  function closeUpload(file){
    sign = 2;
    $('#file_upload').uploadify('cancel', file.id,'1');
  }
  function flashChecker() {
    var hasFlash = 0;
    var flashVersion = 0

    if (document.all) {
      var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
      if (swf) {
        hasFlash = 1;
        VSwf = swf.GetVariable("$version");
        flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
      }
    } else {
      if (navigator.plugins && navigator.plugins.length > 0) {
        var swf = navigator.plugins["Shockwave Flash"];
        if (swf) {
          hasFlash = 1;
          var words = swf.description.split(" ");
          for ( var i = 0; i < words.length; ++i) {
            if (isNaN(parseInt(words[i])))
              continue;
            flashVersion = parseInt(words[i]);
          }
        }
      }
    }
    return {
      f : hasFlash,
      v : flashVersion
    };
  }
  function doSearch(value, name) {
    var p = $('#dg').datagrid('getPager');
    $(p).pagination({
      pageNumber : 1
    });
    var queryParams = $('#dg').datagrid('options').queryParams;
    queryParams.name = value;
    switch(name){
      case "全部":
        queryParams.type = "all";
        break;
      case "视频":
        queryParams.type = "video";
        break;
      case "文档":
        queryParams.type = "word";
        break;
      case "Excel":
        queryParams.type = "excel";
        break;
      case "图片":
        queryParams.type = "img";
        break;
      case "PDF":
        queryParams.type = "pdf";
        break;
      case "PPT":
        queryParams.type = "ppt";
        break;
      case "动画":
        queryParams.type = "flash";
        break;
      case "audio":
        queryParams.type = "audio";
        break;
    }
    var flag=$("#divFlag").val();
    queryParams.divFlag=flag;
    $('#dg').datagrid("reload");
    var size = $('#dg').datagrid('getRows').length;
    searchFlag=false;
    if(value!=""){
      searchFlag=true;
    }
  }
  function bb(flag) {
    pendingAuditDisplay();
    changeDivAll();
    if(flag=="pubdels"){
    }else{
      $('#disTitle').html("<span class=\'master-header-span\' id=\'playplanContentHeader\' style=\'margin-top:11px\' '>"+"公共资源"+"</span>");
    }

    var editcount = 0;
    var myDate = new Date();
    var mytime=myDate.toLocaleTimeString();
    dg= $('#dg');
    var viru='${virtualPreView}';
    var viruecode=escape(encodeURIComponent(viru));
    $('#resourceList').show();
    dg.datagrid(
            {

              border : false,
              fitColum : false,
              toolbar : '#master-contenttool',
              fit : true,
              nowrap : false,
              sortName : 'name',
              sortOrder : 'asc',
              remoteSort: true ,
              pageSize : 10,
              checkOnSelect : true,
              selectOnCheck : true,
              striped : true,
              rownumbers : true,
              pagination : false,
              singleSelect : false,
              idField : 'id',
              pageList : [ 10, 20, 30, 40 ],
              iconCls : 'icon-save',
              url : '${pageContext.request.contextPath}/tresource/Tresource_load1.do?virtual='+viruecode,
              onLoadSuccess : function(d){
                var size = dg.datagrid('getRows').length;
                if(!searchFlag){//搜索
                  if(size==0){
                    $("#resourceList").attr("style","display:none");
                    $("#emptyr").attr("style","display:none");
                  }else{
                    $("#resourceList").attr("style","height:100%");
                    $("#emptyr").attr("style","display:none");
                  }
                }else{
                  searchFlag=false;
                  if(size==0){
                    $("#resourceList").attr("style","display:none");
                    $("#emptyr").attr("style","");
                  }else{
                    $("#resourceList").attr("style","height:100%");
                    $("#emptyr").attr("style","display:none");
                  }
                }
              },
              frozenColumns : [ [ {
                field : 'ck',
                checkbox : true,
                width:100
              } ] ],
              columns : [ [
                {
                  field : 'name',
                  width : 350,
                  editor : 'text',
                  sortable:'true',
                  formatter : function(value, row, index) {
                    var name=row.name;
                    var dis="";
                    if(name.length>35){
                      dis=name.substr(0,35);
                    }else{
                      dis=name;
                    }
                    switch (row.type) {
                      case 0:
                        var vir=encodeURI(encodeURI(row.virtual+row.name+'/'));
                        return '<img src="${pageContext.request.contextPath}/image/frontend/resources/floder_data.png"/>&nbsp&nbsp'
                                + '<a href="${pageContext.request.contextPath}/tresource/Tresource_subfloder.do?virtual='
                                +vir
                                  /* 		+ row.virtual
                                   + row.name
                                   + '/' */
                                +'&currentFolderId='
                                +row.id
                                +'&mytime='+mytime
                                + '" >'
                                + '<font title='+name+'>'+dis+'</font>' + '</a>';
                        break;
                      case 1:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/video_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 2:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/word_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 3:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/excel_data2.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 4:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/img_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 5:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/pdf_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 6:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/ppt_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                      case 7:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/flash_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                    }

                  },
                  title : '名称'
                },  {
                  field : 'virtual',
                  width : 150,
                  hidden : true,
                  editor : 'text',
                  title : '存储路径'
                },{field : 'status',
                  width : 150,
                  hidden:true,
                  title : '状态'
                }, {
                  field : 'size',
                  width : 150,
                  editor : 'text',
                  title : '文件大小',
                  formatter:function(value, row, index){
                    return row.size==0?"":(row.size/1024).toFixed(2)+"M";
                  }
                },{
                  field : 'createTime',
                  width : 150,
                  title : '创建时间'
                }, {
                  field : 'uploader',
                  width : 182,
                  title : '创建人1-1'
                } ] ],
              singleSelect : false,
              selectOnCheck : true,
              checkOnSelect : true
            });
    var p = $("#dg").datagrid('getPager');

  }
  function bbupload() {
    pendingAuditDisplay();
    changeDivAll();

    var editcount = 0;
    var myDate = new Date();
    var mytime=myDate.toLocaleTimeString();
    dg= $('#dg');
    var viru='${virtualPreView}';
    var viruecode=escape(encodeURIComponent(viru));
    $('#resourceList').show();
    dg.datagrid(
            {

              border : false,
              fitColum : false,
              toolbar : '#master-contenttool',
              fit : true,
              nowrap : false,
              sortName : 'name',
              sortOrder : 'asc',
              remoteSort: true ,
              pageSize : 10,
              checkOnSelect : true,
              selectOnCheck : true,
              striped : true,
              rownumbers : true,
              pagination : false,
              singleSelect : false,
              idField : 'id',
              pageList : [ 10, 20, 30, 40 ],
              iconCls : 'icon-save',
              url : '${pageContext.request.contextPath}/tresource/Tresource_load1.do?virtual='+viruecode,
              onLoadSuccess : function(d){
                var size = dg.datagrid('getRows').length;
                if(!searchFlag){//搜索
                  if(size==0){
                    $("#resourceList").attr("style","display:none");
                    $("#emptyr").attr("style","display:none");
                  }else{
                    $("#resourceList").attr("style","height:100%");
                    $("#emptyr").attr("style","display:none");
                  }
                }else{
                  searchFlag=false;
                  if(size==0){
                    $("#resourceList").attr("style","display:none");
                    $("#emptyr").attr("style","");
                  }else{
                    $("#resourceList").attr("style","height:100%");
                    $("#emptyr").attr("style","display:none");
                  }
                }
              },
              frozenColumns : [ [ {
                field : 'ck',
                checkbox : true,
                width:100
              } ] ],
              columns : [ [
                {
                  field : 'name',
                  width : 350,
                  editor : 'text',
                  sortable:'true',
                  formatter : function(value, row, index) {
                    var name=row.name;
                    var dis="";
                    if(name.length>35){
                      dis=name.substr(0,35);
                    }else{
                      dis=name;
                    }
                    switch (row.type) {
                      case 0:
                        var vir=escape(encodeURIComponent(row.virtual+row.name+'/'));
                        return '<img src="${pageContext.request.contextPath}/image/resources/floder_data.png"/>&nbsp&nbsp'
                                + '<a href="${pageContext.request.contextPath}/resource/Resource_subfloder.do?virtual='
                                +vir
                                  /* 		+ row.virtual
                                   + row.name
                                   + '/' */
                                +'&currentFolderId='
                                +row.id
                                +'&mytime='+mytime
                                + '" >'
                                + '<font title='+name+'>'+dis+'</font>' + '</a>';
                        break;
                      case 1:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/video_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 2:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/word_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 3:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/excel_data2.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 4:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/img_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 5:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/pdf_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 6:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/ppt_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                      case 7:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/flash_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                    }

                  },
                  title : '名称'
                },  {
                  field : 'virtual',
                  width : 150,
                  hidden : true,
                  editor : 'text',
                  title : '存储路径'
                },{field : 'status',
                  width : 150,
                  hidden:true,
                  title : '状态'
                }, {
                  field : 'size',
                  width : 150,
                  editor : 'text',
                  title : '文件大小',
                  formatter:function(value, row, index){
                    return row.size==0?"":(row.size/1024).toFixed(2)+"M";
                  }
                },{
                  field : 'createTime',
                  width : 150,
                  title : '创建时间'
                }, {
                  field : 'uploader',
                  width : 182,
                  title : '创建人1-2'
                } ] ],
              singleSelect : false,
              selectOnCheck : true,
              checkOnSelect : true
            });
    var p = $("#dg").datagrid('getPager');

    $(p).pagination({
      total : 17,
      loading : true,
      pageSize : 2,
      pageList : [ 2, 20, 50 ],
      beforePageText : '第',
      afterPageText : '页    共 {pages} 页',
      displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条',
      onBeforeRefresh : function() {
        $("#dg").datagrid("reload");
      }
    });
  }
  //
  function bb4() {
    /* window.location.href = "ManagementCenter/resource/Resource_home.do?module=Resource"; */
    window.location.href = "${pageContext.request.contextPath}/tresource/Tresource_home.do?module=Resource";
  }
  //我的上传
  function bb2() {
    changeDivMy();
    dg.datagrid(
            {
              border : false,
              fitColumns : false,
              toolbar : '#master-contenttool',
              fit : true,
              nowrap : false,
              sortName : 'name',
              sortOrder:'asc',
              checkOnSelect : true,
              selectOnCheck : true,
              striped : true,
              pagination : true,
              singleSelect : false,
              pagination : false,
              rownumbers : true,
              remoteSort : true,
              iconCls : 'icon-save',
              url : '${pageContext.request.contextPath}/tresource/Tresource_load2.do',
              frozenColumns : [ [ {
                field : 'ck',
                checkbox : true,
                width:100
              } ] ],
              columns : [ [
                {
                  field : 'name',
                  width : 350,
                  editor : 'text',
                  sortable:'true',
                  formatter : function(value, row, index) {
                    var name=row.name;
                    var dis="";
                    if(name.length>35){
                      dis=name.substr(0,35);
                    }else{
                      dis=name;
                    }
                    switch (row.type) {
                      case 0:
                        var vir=escape(encodeURIComponent(row.virtual+row.name+'/'));
                        return '<img src="${pageContext.request.contextPath}/image/resources/floder_data.png"/>&nbsp&nbsp'
                                + '<a href="${pageContext.request.contextPath}/resource/Resource_subfloder.do?virtual='
                                +vir
                                  /* 		+ row.virtual
                                   + row.name
                                   + '/' */
                                +'&currentFolderId='
                                +row.id
                                +'&mytime='+mytime
                                + '" >'
                                + '<font title='+name+'>'+dis+'</font>' + '</a>';
                        break;
                      case 1:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/video_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 2:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/word_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 3:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/excel_data2.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 4:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/img_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 5:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/pdf_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 6:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/ppt_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                      case 7:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/flash_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                    }

                  },
                  title : '名称'
                },{
                  field : 'virtual',
                  width : 262,
                  hidden : false,
                  editor : 'text',
                  title : '目录'
                }, {
                  field : 'status',
                  width : 80,
                  hidden:false,
                  title : '状态',
                  formatter : function(value, row, index) {
                    if(row.transStatus==3){
                      switch(row.status){
                        case 1:
                          return "<font color='ff8c1b'>待审核</font>";
                          break;
                        case 2:
                          return "<font color='#7B7B7B'>上传成功</font>";
                          break;
                        case 3:
                          return "<font color='red'>审核失败</font>";
                          break;
                      }
                    }else if(row.transStatus==2||row.transStatus==1){//正在转换
                      return "<font color='#7B7B7B'>上传中</font><img src='${pageContext.request.contextPath}/image/frontend/resources/resource-uploading.gif'/>";
                    }{//转换失败
                      return "<font color='red'>上传失败</font>";

                    }
                  }
                }, {
                  field : 'size',
                  width : 120,
                  editor : 'text',
                  title : '文件大小',
                  formatter:function(value, row, index){
                    return row.size==0?"":(row.size/1024).toFixed(2)+"M";
                  }
                }, {
                  field : 'createTime',
                  width : 150,
                  title : '创建时间'
                },  {
                  field : 'transStatus',
                  width : 60,
                  title : '转换状态',
                  hidden:true
                }, {
                  field : 'uploader',
                  width : 60,
                  title : '创建人1-3',
                  hidden:true
                } ] ],
              singleSelect : false,
              selectOnCheck : true,
              checkOnSelect : true
            });



  }
  //待审核资源
  function bb3() {
    if(checkVerifyPermission(module)){
      return;
    }
    changeDivVerify();
    var editcount = 0;
    dg.datagrid(
            {
              border : false,
              fitColumns : false,
              toolbar : '#master-contenttool',
              fit : true,
              nowarp : true,
              sortName : 'name',
              sortOrder : 'desc',
              remoteSort:true,
              pageSize : 10,
              checkOnSelect : false,
              selectOnCheck : false,
              striped : true,
              rownumbers : true,
              pagination : false,
              singleSelect : false,
              idField : 'id',
              pageList : [ 10, 20, 30, 40 ],
              iconCls : 'icon-save',
              url : '${pageContext.request.contextPath}/tresource/Tresource_load3.do',
              frozenColumns : [ [ {
                field : 'ck',
                checkbox : true,
                width:100
              } ] ],
              columns : [ [
                {
                  field : 'name',
                  width : 350,
                  editor : 'text',
                  sortable:'true',
                  formatter : function(value, row, index) {
                    var name=row.name;
                    var dis="";
                    if(name.length>35){
                      dis=name.substr(0,35);
                    }else{
                      dis=name;
                    }
                    switch (row.type) {
                      case 0:
                        var vir=escape(encodeURIComponent(row.virtual+row.name+'/'));
                        return '<img src="${pageContext.request.contextPath}/image/frontend/resources/floder_data.png"/>&nbsp&nbsp'
                                + '<a href="${pageContext.request.contextPath}/resource/Resource_subfloder.do?virtual='
                                +vir
                                  /* 		+ row.virtual
                                   + row.name
                                   + '/' */
                                +'&currentFolderId='
                                +row.id
                                +'&mytime='+mytime
                                + '" >'
                                + '<font title='+name+'>'+dis+'</font>' + '</a>';
                        break;
                      case 1:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/video_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 2:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/word_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 3:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/excel_data2"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 4:
                        return '<img  src="${pageContext.request.contextPath}/image/resources/img_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 5:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/pdf_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;
                      case 6:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/ppt_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                      case 7:
                        return '<img  src="${pageContext.request.contextPath}/image/frontend/resources/flash_data.png"/>&nbsp&nbsp'
                                + '<a style="text-decoration:none;" href="#" onclick="previewOpen('+row.id+','+row.type+",'"+row.name+"'"+');">'+'<font title='+name+'>'+dis+'</font>'+'</a>';
                        break;

                    }

                  },
                  title : '名称'
                }, {
                  field : 'virtual',
                  width : 60,
                  hidden : true,
                  editor : 'text',
                  title : '存储目录'
                },{field : 'status',
                  width : 60,
                  hidden:true,
                  title : '状态'
                }, {
                  field : 'size',
                  width : 150,
                  editor : 'text',
                  title : '文件大小',
                  formatter:function(value, row, index){
                    return row.size==0?"":(row.size/1024).toFixed(2)+"M";
                  }
                },{
                  field : 'createTime',
                  width : 150,
                  title : '上传时间'
                },
                {
                  field : 'transStatus',
                  width : 60,
                  title : '转换状态',
                  hidden:true
                }, {
                  field : 'uploader',
                  width : 182,
                  title : '上传人'
                } ] ],
              singleSelect : false,
              selectOnCheck : true,
              checkOnSelect : true
            });
    var p = $("#dg").datagrid('getPager');

    $(p).pagination({
      total : 17,
      loading : true,
      pageSize : 2,
      pageList : [ 2, 20, 50 ],
      beforePageText : '第',
      afterPageText : '页    共 {pages} 页',
      displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条',
      onBeforeRefresh : function() {
        $("#dg").datagrid("reload");
      }
    });
  }

  function create() {
//    if(checkEdeitPermission(module)){
//      return;
//    }
    $('#dd3').dialog('open');
  }
  function valid() {
    $('#ff')
            .form(
            {
              url : '${pageContext.request.contextPath}/tresource/ResourceUpload_md5Validate.do',
              onSubmit : function() {

              },
              success : function(data) {
                if (data == "我是MD5验证返回的信息") {

                } else {
                  $.messager.alert('Info', data, 'info');
                }

              },
              onLoadSuccess : function(data) {

              }
            });
  }
  $(function() {
    gh();
    bb("");
    valid();
    disTitle();
    <%--//var js_message = '<%=request.getAttribute("test")%>';--%>
    //	createElement(js_message);
  });
  function upload() {
    arrayO = $("#aryo").val();
//    if(checkUploadPermission(module)){
//      return;
//    }
    var fls = flashChecker();
    if (!fls.f){
      $.messager.alert('提示','flash插件未安装,本功能需要flash插件支持，请安装后刷新页面再进行尝试！','info');
      return;
    }
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
            .match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
            .match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
            .match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
            .match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

    var browser = {
      versions : function() {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
          trident : u.indexOf('Trident') > -1,
          presto : u.indexOf('Presto') > -1,
          webKit : u.indexOf('AppleWebKit') > -1,
          gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
          mobile : !!u.match(/AppleWebKit.*Mobile.*/),
          ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
          android : u.indexOf('Android') > -1
          || u.indexOf('Linux') > -1,
          iPhone : u.indexOf('iPhone') > -1,
          iPad : u.indexOf('iPad') > -1,
          webApp : u.indexOf('Safari') == -1
        };
      }(),
      language : (navigator.browserLanguage || navigator.language)
              .toLowerCase()
    }
    if (browser.versions.android) {
      $('#dd2').dialog('open');
    } else {
      opendd4();
    }

  }
  function createElement(js_message) {

    var createDiv = document.createElement("div");
    createDiv.id = 'test';
    createDiv.href = "#";
    createDiv.className = "main-path";
    var strs = new Array();
    var strs1 = new Array();
    strs = js_message.split("/");
    strs1 = strs.filter(function(n) {
      return n
    });
    if (strs1.length == 1) {
      var createA = document.createElement("a");
      createA.id = "path current";
      createA.innerHTML = strs1[0];
      createDiv.appendChild(createA);
      var div = document.getElementById("pheader");
      div.appendChild(createDiv);

    } else {
      for(var i in strs1)
      {
        var create="create"+i;
        create= document.createElement("a");
        if(i==strs1.length-1)
        {
          create.innerHTML=strs1[i]+"/";
          createDiv.appendChild(create);
        }
        else
        {
          create.innerHTML=strs1[i]+"/";
          if(i==0)
          {
            create.href ='${pageContext.request.contextPath}/tresource/Tresource_home.do?module=Resource';
          }
          else{
            var n=parseInt(i)+1;
            create.href ='${pageContext.request.contextPath}/tresource/Tresource_subfloder.do?virtual=/'+strs1.slice(0,n).join("/")+"/";
          }
          createDiv.appendChild(create);
        }
      }
      var div = document.getElementById("pheader");
      div.appendChild(createDiv);
    }

  }

  function operateFilter(array){
    for(var i=0;i<array.length;i++){
      if("2"==array[i]){
        $.messager.alert('提示','固定目录无法完成此操作','info');
        return true;
      }
    }
  }
  function pubDels(){
    var flag=$("#divFlag").val();
    if(flag=="all"){
//      if(checkDeletePermission(module)){
//        return false;
//      }
    }
    var ids = [];
    var rows = $('#dg').datagrid('getSelections');
    for ( var i = 0; i < rows.length; i++) {
      ids.push(rows[i].id);
    }
    if(operateFilter(ids)){
      return;
    }
    var arr = ids.join('/');
    if (arr.length > 0) {
      $.messager
              .confirm(
              '提示信息',
              '您确认要删除吗?',
              function(data) {
                if (data) {

                  /* 	$.ajax({
                   url : '${pageContext.request.contextPath }/resource/Resource_ifVerify.do',
                   data : {
                   data : arr
                   },
                   async : false,
                   dataType : 'json',
                   success : function(result) {
                   var json = eval("("+ result + ")");
                   if (json) {
                   $.messager.alert('失败','您的选择中存在已审核文件,不能删除！','error');
                   $("#delsIfVerified").val("1");
                   $('#dg')
                   .datagrid('clearSelections');
                   }

                   }
                   });
                   if($("#delsIfVerified").val()=="1"){
                   $("#delsIfVerified").val("0");
                   return;
                   } */
                  var myDate = new Date();
                  var mytime=myDate.toLocaleTimeString();
                  $.ajax({
                    url : '${pageContext.request.contextPath }/tresource/Tresource_havaSub.do?mytime='+mytime,
                    data : {
                      data : arr
                    },
                    async : false,
                    dataType : 'json',
                    success : function(result) {
                      var json = eval("("+ result + ")");
                      if (json.fla=="2") {

                        $("#booleanForDelete").val('2');
                      }else if(json.fla=="1"){

                        $("#booleanForDelete").val('1');
                      }else{
                        $("#booleanForDelete").val('0');
                      }
                    }
                  });

                  flag=$("#booleanForDelete").val();
                  if(flag=="2"){
                    $.messager.alert('提示','目录下有文件，不能删除！','info');
                    return;
                  }else if(flag=="1"){
                    $.messager.alert('提示','目录不为空或者有审核相关的文件，不能删除！','info');
                    return;
                  }

                  $.ajax({
                    url : '${pageContext.request.contextPath }/tresource/Tresource_dels.do',
                    data : {
                      data : arr
                    },
                    async : false,
                    dataType : 'json',
                    success : function(result) {
                      var json = eval("("
                      + result + ")");
                      if (json) {
                        $.messager.alert('提示',
                                '删除成功', 'info');
                        bb("pubdels");
                      } else {
                        $.messager
                                .alert('提示',
                                '删除失败',
                                'error');
                      }
                      $('#dg')
                              .datagrid('clearSelections');
                    }
                  });
                }
                else
                {
                }
              });
    } else
    {
      $.messager.alert('提示', '请先选择要删除的资源。', 'info');

    }

  }
  function dels() {
    var flag=$("#divFlag").val();


    var ids = [];
    var rows = $('#dg').datagrid('getSelections');
    for ( var i = 0; i < rows.length; i++) {
      ids.push(rows[i].id);
    }
    var arr = ids.join('/');
    if (arr.length > 0) {
      $.messager
              .confirm(
              '提示',
              '您确认要删除吗?',
              function(data) {
                if (data) {
                  $.ajax({
                    url : '${pageContext.request.contextPath }/tresource/Tresource_ifVerify.do',
                    data : {
                      data : arr
                    },
                    async : false,
                    dataType : 'json',
                    success : function(result) {
                      var json = eval("("+ result + ")");
                      if (json) {
                        if(checkDeletePermission(module)){

                          $("#delsIfVerified").val("1");
                          $('#dg').datagrid('clearSelections');
                        }

                      }

                    }
                  });
                  if($("#delsIfVerified").val()=="1"){
                    $("#delsIfVerified").val("0");
                    return;
                  }

                  $.ajax({
                    url : '${pageContext.request.contextPath }/tresource/Tresource_dels.do',
                    data : {
                      data : arr
                    },
                    async : false,
                    dataType : 'json',
                    success : function(result) {
                      var json = eval("("
                      + result + ")");
                      if (json) {
                        $.messager.alert('提示',
                                '删除成功', 'info');
                        bb("");
                      } else {
                        $.messager
                                .alert('提示',
                                '删除失败',
                                'error');
                      }
                      /* $('#dg').datagrid('reload'); */
                      $('#dg')
                              .datagrid('clearSelections');
                    }
                  });
                }
                else
                {
                  /* return window.location.reload(); */
                }
              });
    } else
    {
      $.messager.alert('提示', '请先选择要删除的资源。', 'info');

      /* return window.location.reload(); */

    }

  }
  function rename()
  {
//    if(checkEdeitPermission(module)){
//      return;
//    }
    var rows = $('#dg').datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
      $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
      $('#dg').datagrid('clearSelections');
      return;
    } else if (num > 1) {
      $.messager.alert('提示','您选择了多条记录,只能选择一条记录进行修改!', 'info');
      $('#dg').datagrid('clearSelections');
      return;
    } else {

      var ids = [];
      for ( var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
      }
      if(operateFilter(ids)){
        return;
      }
      var arr = ids.join('/');
      var random;
      var randomVal=Math.random();
      $.ajax({
        url : '${pageContext.request.contextPath }/tresource/Tresource_havaSub.do',
        data : {
          data : arr,
          random:randomVal
        },
        async : false,
        dataType : 'json',
        success : function(result) {
          var json = eval("("+ result + ")");
          if (json.fla=="2") {

            $("#booleanForDelete").val('2');
          }else if(json.fla=="1"){

            $("#booleanForDelete").val('1');
          }else{

            $("#booleanForDelete").val('0');
          }

        }
      });
      flag=$("#booleanForDelete").val();
      if(flag=="2"){
        $.messager.alert('提示','目录下有文件，不能更改名称！','info');
        return;
      }else if(flag=="1"){
        $.messager.alert('提示','目录不为空或者有审核相关的文件，不能更改名称！','info');
        return;
      }


      $('#dd4').dialog('open');
      $("#dd4").form('load',{
        ids:rows[0].id
      });


    }


  }

  function changeDivMy(){
    $("#delete").attr("onclick","dels();");
    $("#divFlag").val("my");
    $('#disTitle').html("<span class=\'master-header-span\' id=\'playplanContentHeader\' style=\'margin-top:11px\' '>我上传的</span>");
    document.getElementById("delete").style.display="block";
    document.getElementById("download").style.display="block";
    document.getElementById("upload").style.display="block";
    document.getElementById("newfloder").style.display="none";
    document.getElementById("move").style.display="none";
    document.getElementById("rename").style.display="none";
    document.getElementById("pass").style.display="none";
    document.getElementById("noPass").style.display="none";

  }
  function changeDivAll(){
    $("#delete").attr("onclick","pubDels();");
    $("#divFlag").val("all");
    disTitle();
    document.getElementById("pass").style.display="none";
    document.getElementById("noPass").style.display="none";
    document.getElementById("move").style.display="block";
    document.getElementById("rename").style.display="block";
    document.getElementById("delete").style.display="block";
    document.getElementById("download").style.display="block";
    document.getElementById("newfloder").style.display="block";
    document.getElementById("upload").style.display="block";
  }
  function changeDivVerify(){
    $('#disTitle').html("<span class=\'master-header-span\' id=\'playplanContentHeader\' style=\'margin-top:11px\' '>待审核资源</span>");
    $("#divFlag").val("toBeVerify");
    document.getElementById("pass").style.display="block";
    document.getElementById("noPass").style.display="block";
    document.getElementById("move").style.display="none";
    document.getElementById("rename").style.display="none";
    document.getElementById("delete").style.display="none";
    document.getElementById("download").style.display="none";
    document.getElementById("newfloder").style.display="none";
    document.getElementById("upload").style.display="none";

  }
  function pass(){

    var ids = [];
    var rows = $('#dg').datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
      $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
      $('#dg').datagrid('clearSelections');
      return;
    }
    for ( var i = 0; i < rows.length; i++) {
      ids.push(rows[i].id);
    }
    var arr = ids.join(',');
    verify(arr,"1");
  }
  function noPass(){
    var ids = [];
    var rows = $('#dg').datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
      $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
      $('#dg').datagrid('clearSelections');
      return;
    }
    for ( var i = 0; i < rows.length; i++) {
      ids.push(rows[i].id);
    }
    var arr = ids.join(',');
    verify(arr,"0");
  }
  //审核
  function verify(ids,flag){
    var idstr=ids;
    var fla=flag;
    $.ajax({
      url : '${pageContext.request.contextPath }/tresource/Tresource_verifyResource.do',
      data : {
        idstr : idstr,
        flag  : fla
      },
      async : false,
      dataType : 'json',
      success : function(result) {
        var json=eval('('+result+')');
        if(json){
          $.messager.alert('提示','审核成功','success');
          bb3();
          $('#dg').datagrid('clearSelections');
        }else{
          $.messager.alert('提示','审核失败','error');
          bb3();
          $('#dg').datagrid('clearSelections');
        }
      }


    });
  }
  function pendingAuditDisplay(){
    var myDate = new Date();
    var mytime=myDate.toLocaleTimeString();

    $.ajax({
      url : '${pageContext.request.contextPath }/tresource/Tresource_pendingAuditDisplay.do?mytime='+mytime,
      data : {
      },
      async : false,
      dataType : 'json',
      success : function(result) {
        var json=eval('('+result+')');
        if(json){
          document.getElementById("pendingAudit").style.display="block";
        }else{
          document.getElementById("pendingAudit").style.display="none";
        }
      }


    });
  }
  //预览开启
  /* function preview(type,id,name){

   var content= "";
   $.ajax({
   url : '${pageContext.request.contextPath }/resource/Resource_getMd5name.do',
   data : {
   data : id
   },
   async : false,
   dataType : 'json',
   success : function(result) {
   $("#downloadName").val(result);
   }
   });
   var md5name=$("#downloadName").val();
   var num=md5name.lastIndexOf('.');
   var foldername=md5name.subString(0,num);
   alert(foldername);
   switch (type) {
   case 0:


   break;
   case 1:
   break;
   case 2:
   break;
   case 3:
   break;
   case 4:
   content+="<img style=\"background:url('"+requestContextPath+"/data/resources/1/"+md5name+"')\"></img><br>";
   break;
   case 5:
   break;
   case 6:
   case 7:
   break;

   }

   content+=  '<span class="edit-resources-span">'+name+'</span>';
   alert("in ");
   $("previewEditId").val(id);
   $("#previewContent").append(content);
   $("#previewId").css("display","block");
   } */
  //预览关闭
  function previewClose(){
    $("#previewId").css("display","none");
    /* 		$("#previewContent").empty(); */
    $("#previewContent")[0].innerHTML="";
    $("#downloadName").val("");
    $("#previewEditName").val("");
    $("#previewEditId").val("");
    $("#previewEditType").val("");
    $("previewPath").val("");
    document.getElementById("previewContent").innerHTML="";
  }

  function previewOpen(id,type,name){
    $("#previewEditId").val(id);
    $("#previewEditType").val(type);
    $("#previewEditName").val(name);
    var content= "";
    $.ajax({
      url : '${pageContext.request.contextPath }/tresource/Tresource_getMd5name.do',
      data : {
        data : id
      },
      async : false,
      dataType : 'json',
      success : function(result) {
        var jsonResult=eval('('+result+')');
        $("#downloadName").val(jsonResult.md5name);
        $("#previewPath").val(jsonResult.path);
      }
    });
    var md5name=$("#downloadName").val();
    var pathFolder=$("#previewPath").val();
    var index=md5name.lastIndexOf('.');
    var namelength=md5name.length;
    var nameType=md5name.substring(index+1,namelength);
    var foldername=md5name.substring(0,index);
    var pathForQuery="/data/resources/"+pathFolder+"/"+foldername+"/";

    switch (type) {

      case 1:
        if (typeof(Worker) !== "undefined"){
          content=
                  "<video width='500' height='500' controls='controls'   autoplay='autoplay' >"+
                  "	<source src='"+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/video.mp4'>"+
                  "	<object data='movie.mp4' width='500' height='500'> "+
                  "     <embed src='"+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/video.mp4'>"+
                  "	  </object>"+
                  "</video>";
        }else{
          content=
                  "<object data='"+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/video.mp4' height='500' width='500'/>";
        }
        break;
      case 2:
        $.ajax({
          url : '${pageContext.request.contextPath }/tresource/Tresource_getFileNum.do',
          data : {
            data : pathForQuery
          },
          async : false,
          dataType : 'json',
          success : function(result) {
            $("#previewEditNum").val(result);
          }
        });
        var num=$("#previewEditNum").val();
        for(var i=1;i<=num;i++){
          content+="<img class=\"edit-resources-image\" src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/"+i+".png\"></img>";
        }
        $("#previewEditNum").val("");
        break;
      case 3:
        $.ajax({
          url : '${pageContext.request.contextPath }/tresource/Tresource_getFileNum.do',
          data : {
            data : pathForQuery
          },
          async : false,
          dataType : 'json',
          success : function(result) {
            $("#previewEditNum").val(result);
          }
        });
        var num2=$("#previewEditNum").val();
        for(var i=1;i<=num2;i++){
          content+="<img class=\"edit-resources-image\" src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/"+i+".png\"></img>";
        }
        $("#previewEditNum").val("");
        break;

      case 4:
        if(nameType.toLowerCase()=="jpg"||nameType.toLowerCase()=="bmp"){
          content+="<img class=\"edit-resources-image\" src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/image.png\"></img><br>";
        }else{
          content+="<img class=\"edit-resources-image\" src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/image."+nameType+"\"></img><br>";
        }

        break;
      case 5:
        $.ajax({
          url : '${pageContext.request.contextPath }/tresource/Tresource_getFileNum.do',
          data : {
            data : pathForQuery
          },
          async : false,
          dataType : 'json',
          success : function(result) {
            $("#previewEditNum").val(result);
          }
        });
        var num3=$("#previewEditNum").val();
        for(var i=1;i<=num3;i++){
          content+="<img class=\"edit-resources-image\" src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/"+i+".png\"></img>";
        }
        $("#previewEditNum").val("");
        break;
      /* case 6:
       content+=
       "<iframe src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/"+"thumb.png\">"+
       "</iframe>";
       break; */
      case 6: // PPT
        $.ajax({
          url : '${pageContext.request.contextPath }/tresource/Tresource_getFileNum.do',
          data : {
            data : pathForQuery
          },
          async : false,
          dataType : 'json',
          success : function(result) {
            $("#previewEditNum").val(result);
          }
        });
        var num=$("#previewEditNum").val();
        for(var i=1;i<=num;i++){
          content+="<img class=\"edit-resources-image\" src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/"+i+".png\"></img>";
        }
        $("#previewEditNum").val("");
        break;
      case 7:
        var fla=
                " <object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\"> "+
                "	<param name=\"movie\" value=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/flash."+nameType+"\" />"+
                "	<param name=\"quality\" value=\"high\" /> "+
                "	<param name=\"play\" value=\"true\" /> "+
                "	<param name=\"loop\" value=\"true\" /> "+
                "	<param name=\"wmode\" value=\"transparent\" /> "+
                "	<param name=\"scale\" value=\"showall\" /> "+
                "	<param name=\"menu\" value=\"false\" /> "+
                "	<embed  src=\""+requestContextPath+"/data/resources/"+pathFolder+"/"+foldername+"/flash."+nameType+"\" quality=\"high\" play=\"true\" loop=\"true\" wmode=\"transparent\" scale=\"showall\" menu=\"false\" type=\"application/x-shockwave-flash\"></embed> "+
                " </object><br>" ;
        content+=fla;

        break;

    }
    content+=  '<span class="edit-resources-span">'+name+'</span>';
    /* $("#previewContent").append(content);   */
    $("#previewContent").remove();
    $(".edit-resources-title").after("<div id='previewContent' class='edit-resources-content'></div>");
    document.getElementById("previewContent").innerHTML=content;
    $("#previewId").css("display","block");

  }
  function previewDelete(){
    var delId=$("#previewEditId").val();
    if(checkDeletePermission(module)){
      return;
    }
    var arr=delId;
    $.messager
            .confirm(
            '提示',
            '您确认要删除吗?',
            function(data) {
              if (data) {
                /* 	$.ajax({
                 url : '${pageContext.request.contextPath }/resource/Resource_ifVerify.do',
                 data : {
                 data : arr
                 },
                 async : false,
                 dataType : 'json',
                 success : function(result) {
                 var json = eval("("+ result + ")");
                 if (json) {
                 $.messager.alert('失败','已审核文件不能删除！','error');
                 $("#delsIfVerified").val("1");
                 $('#dg')
                 .datagrid('clearSelections');
                 }

                 }
                 });
                 if($("#delsIfVerified").val()=="1"){
                 $("#delsIfVerified").val("0");
                 return;
                 } */
                $.ajax({
                  url : '${pageContext.request.contextPath }/tresource/Tresource_dels.do',
                  data : {
                    data : delId
                  },
                  async : false,
                  dataType : 'json',
                  success : function(result) {
                    var json = eval("("
                    + result + ")");
                    if (json) {
                      $.messager.alert('提示',
                              '删除成功', 'success');

                      bb("");
                      previewClose();
                    } else {
                      $.messager
                              .alert('提示',
                              '删除失败',
                              'error');
                      previewClose();
                    }
                    /* $('#dg').datagrid('reload'); */
                    $('#dg')
                            .datagrid('clearSelections');
                  }
                });
              }
              else
              {
                /* return window.location.reload(); */
              }
            });
  }
  function previewDownload(){
    var downloadId=$("#previewEditId").val();
    var previewName=$("#previewEditName").val();
    $.messager.confirm('提示', '确认要下载吗?',
            function(data) {
              if (data) {
                $.ajax({
                  url : '${pageContext.request.contextPath }/tresource/Tresource_getMd5name.do',
                  data : {
                    data : downloadId
                  },
                  async : false,
                  dataType : 'json',
                  success : function(result) {
                    var jsonResult=eval('('+result+')');
                    $("#downloadName").val(jsonResult.md5name);
                    $("#previewPath").val(jsonResult.path);
                  }
                });
                var name=$("#downloadName").val();
                var pathFolder=$("#previewPath").val();
                window.location.href=encodeURI(requestContextPath+"/data/resources/"+pathFolder+"/"+name+'?n='+previewName);
              }
            }
    );
  }
</script>

<!-- 左边部分 -->
<div class="master-left-bar">
  <jsp:include page="/pages/frontend/tresource/menu.jsp"></jsp:include>
</div>

<!-- 右边部分 -->
<div class="master-content">
  <div class="master-content-tool">
    <jsp:include page="/pages/frontend/tresource/contentTool.jsp"></jsp:include>
  </div>

  <div id="disTitle" i="pheader" class="master-contentheader" style="margin-top:9px;height: 40px;"></div>

  <div class="master-content-body" style="margin-top:0px;">
    <jsp:include page="/pages/frontend/tresource/main.jsp"></jsp:include>
  </div>
</div>
