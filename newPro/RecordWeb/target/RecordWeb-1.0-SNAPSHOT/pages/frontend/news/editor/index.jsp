<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" onselectstart="return false">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
	<title>编辑富文本消息</title>
    <%--<meta charset="utf-8">--%>
    <link href="${pageContext.request.contextPath}/pages/frontend/news/editor/css/base.css" rel="stylesheet" type="text/css"/>
     <link href="${pageContext.request.contextPath}/pages/frontend/news/editor/css/editor.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/pages/frontend/news/editor/css/editItem.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
	</head>
<body>
<div class="public">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <%
        //String nId = request.getAttribute("newsId").toString();
    %>

    <input type="hidden" id="indexportIndex"><!-- 发布时的定时器 -->
    <input type="hidden" id="state" >
    <input type="hidden" id="quick" ><!-- 普通发布  和 快速发布 的区分标志 -->
    <div class="container">
        <header>
            <aside>
                <span onclick="location.href= DSS.serverIp + '/ManagementCenter/home/Home_login.do'" style="cursor:pointer;" title="返回系统首页"></span>
                <label onclick="navigateToModule('Project')" style="cursor:pointer;" title="返回节目管理">节目管理</label>
            </aside>
            <aside>
                <ul>
                    <div style="display: inline-block; margin-top: 8px;">
                        <span style="color:rgb(240,240,240)" id="unam"></span>
                        <img src="images/house.png" style="cursor:pointer" onclick="gohome();" title="返回首页">
                        <img src="images/backtohome.png" style="cursor:pointer" onclick="exit()" title="注销">
                    </div>
                </ul>
            </aside>
        </header>
        <div class='title'>
            <div class='left-one' >
                <img src="./images/playlogo.png">
                <span style="font-size: 16px;color: #666;line-height: 40px;">消息制作</span>
            </div>
            <div class="master-content-tool">
                <ul class="firstphoto">
                    <li class="show top_btn_12" title="按钮菜单" id="btn_menu" style="display:none;">
                        <span style="font-weight: bold;color: white;width:30px">-</span>
                        <div id="btn_menu_bar" style="display:none;">
                            <ul >
                                <li>
                                    <ul>
                                        <li id="open" class="btn_list_2" _tar="program_open">
                                            <span></span><label>打开</label><span id="btn_list_2_span"></span>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <ul>
                                        <li id="cut" class="btn_list_4" _tar="button_cut_blk">
                                            <span></span><label>剪切</label><span id="btn_list_4_span"></span>
                                        </li>
                                        <li id="copy" class="btn_list_5" _tar="button_copy_blk">
                                            <span></span><label>复制</label><span id="btn_list_5_span"></span>
                                        </li>
                                        <li id="paste" class="btn_list_6" _tar="button_paste_blk">
                                            <span></span><label>粘贴</label><span id="btn_list_6_span"></span>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <ul>
                                        <li id="preview" class="btn_list_14" _tar="program_preview">
                                            <span></span><label>预览</label><span id="btn_list_14_span"></span>
                                        </li>
                                        <li id="publish" class="btn_list_13" _tar="program_publish">
                                            <span></span><label>发布</label><span id="btn_list_13_span"></span>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="top_btn_7" style="display: block;margin-top: 13px;cursor:pointer;"  title="撤销" id="button_undo">
                        <span  style="padding-top: 8px;padding-left: 29px;margin-left: 10px;font-size: 13px;color: white;background-image:url(images/arrow_pre.png);background-repeat: no-repeat;"></span></li>
                    <li class="top_btn_8" style="display: block;margin-top: 13px;cursor:pointer;"  title="恢复" id="button_redo">
                        <span style="padding-top: 8px;padding-left: 28px;margin-left: 10px;font-size: 13px;color: white;background-image:url(images/arrow_next.png);background-repeat: no-repeat;"></span></li>
                    <li class="slidewindow" style="display:none"><span style="margin-left: 10px;">窗口大小</span></li>
                    <li class="show top_btn_9"  style="display: none;background-color: rgb(70,175,75);margin-top:7px;cursor:pointer;margin-left: 13px;"  title="页面分辨率" id="page_ratio">
                        <span id="defaultrate">960：540</span>
                        <div id="page_ratio_bar" style="display:none;left:-9px">
                            <ul>
                                <li _val="1080*1920" id="10801920" class="ratio_select" style="text-align: left;">
                                    <label >1080:1920</label><span></span>
                                </li>
                                <li _val="768*1366" id="7681366" class="ratio_select" style="text-align: left;">
                                    <label >768:1366</label><span></span>
                                </li>
                                <li  _val="1920*1080" id="19201080" class="ratio_select" style="text-align: left;">
                                    <label >1920:1080</label><span></span>
                                </li>
                                <li  _val="1366*768" id="1366768" class="ratio_select" style="text-align: left;">
                                    <label >1366:768</label><span></span>
                                </li>
                                <li _val="1280*720" id="1280720" class="ratio_select" style="text-align: left;">
                                    <label >1280:720</label><span></span>
                                </li>
                                <li  _val="1024*768" id="1024768" class="ratio_select" style="text-align: left;">
                                    <label >1024:768</label><span></span>
                                </li>
                                <li  id="page_rate_custom" _val="1000*1000" class="ratio_select" >
                                    <label >自定义</label><span></span>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <!--   <li class="top_btn_13" style="display: block;margin-top: 9px;cursor:pointer;" title="剪切" id="button_cut_blk"><span style="margin-left: 10px;font-size: 13px;color: white">剪切</span></li>
                      <li class="top_btn_14" style="display: block;margin-top: 9px;cursor:pointer;"  title="复制" id="button_copy_blk"><span style="margin-left: 10px;font-size: 13px;color: white">复制</span></li>
                      <li class="top_btn_15" style="display: block;margin-top: 9px;cursor:pointer;"  title="粘贴" id="button_paste_blk"><span style="margin-left: 10px;font-size: 13px;color: white">粘贴</span></li>
                       -->
                    <li class="top_btn_20" style="display: block;margin-top: 9px;cursor:pointer;color: #666;"  title="预览" id="program_preview"><span style="margin-left: 15px;font-size: 13px;color: #666">预览</span></li>
                    <li class="top_btn_11" style="display: block;margin-top: 9px;cursor:pointer;color: #666;"  title="发布" id="program_publish"><span style="margin-left: 10px;font-size: 13px;color: #666">发布</span></li>
                    <%--<li class="top_btn_20" style="display: block;margin-top: 9px;cursor:pointer;"  title="另存为模板" id="program_template"><span style="margin-left: 10px;font-size: 13px;color: white">另存为模板</span></li>--%>
                    <li class="top_btn_2" style="display: block;margin-top: 9px;cursor:pointer;color: #666;"  title="返回列表" id="program_open"><span style="margin-left: 10px;font-size: 13px;color: #666">返回列表</span></li>
                </ul>
            </div>
        </div>
        <nav>
            <ul id="comp_group_list"></ul>
            <!-- <li _tar="comp_panel_1">时间</li>
            <li class="check" _tar="comp_panel_2">天气</li>
            <li _tar="comp_panel_3">通知</li>
            <li _tar="comp_panel_4">图片</li>
            <li _tar="comp_panel_5">文字图形</li>-->
            <!--旧版的节目制作    begin  -->
            <!-- <ul>
             <li>
                 <ul id="comp_panel_list">
                 </ul>
             </li>
             <li></li>
         </ul>  -->
            <!-- 旧版的节目制作     end   -->
            <!-- 新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      begin  -->
            <div class="photos">
                <ul class="firstphoto">
                    <li  id="button_resource_text"><span><a href="javascript:void(0)"><img src="./images/text.png"/></a></span>
                        <span class="text">文本</span>
                    </li>
                    <li id="button_resource_image"><span><a href="javascript:void(0)"><img src="./images/roundsun.png"/></a></span>
                        <span class="text">图片</span>
                    </li>
                    <li id='button_resource_ppt'><span><a href="javascript:void(0)"><img src="./images/pptn.png"/></a></span>
                        <span class="text">文档</span>
                    </li>
                    <li id='button_resource_flash'><a href="javascript:void(0)"><img src="./images/flashn.png"/></a>
                        <span class="text">Flash</span>
                    </li>
                    <li id='button_resource_video'><a href="javascript:void(0)"><img src="./images/videon.png"/></a>
                        <span class="text">视频</span>
                    </li>
                    <li id='button_resource_flowvideo' style="display: none"><span><a href="javascript:void(0)"><img src="./images/flowvideo.png"/></a></span>
                        <span class="text">直播</span>
                    </li>
                    <li id='button_resource_webn' style="display: none"><a href="javascript:void(0)"><img src="./images/webn.png"/></a>
                        <span class="text">网页</span>
                    </li>
                    <li id='button_resource_rectangle'><a href="javascript:void(0)"><img src="./images/rectangle.png"/></a>
                        <span class="text">矩形</span>
                    </li>
                    <li id='button_resource_horizontaline'><a href="javascript:void(0)"><img src="./images/horizontaline.png"/></a>
                        <span class="text">横线</span>
                    </li>
                    <li id='button_resource_verticalline'><a href="javascript:void(0)"><img src="./images/verticalline.png"/></a>
                        <span class="text">竖线</span>
                    </li>
                </ul>
                <ul class="secondphoto">
                    <li id='button_resource_piano'><a href="javascript:void(0)"><img src="./images/piano.png"/></a>
                        <span class="text">手风琴</span>
                    </li>
                    <li id='button_resource_wander'><a href="javascript:void(0)"><img src="./images/wander.png"/></a>
                        <span class="text">漫游</span>
                    </li>
                    <li id='button_resource_swepe'><a href="javascript:void(0)"><img src="./images/swepe.png"/></a>
                        <span class="text">滑动</span>
                    </li>
                    <!--   <li id='button_resource_3D'><a href="javascript:void(0)"><img src="./images/3D.png"/></a>
                      <span class="text">3D</span>
                      </li> -->
                </ul>
                <ul class="thirdphoto">
                    <li id='button_resource_timen'><a href="javascript:void(0)"><img src="./images/timen.png"/></a>
                        <span class="text">时间</span>
                    </li>
                    <li id='button_resource_weather1' style="display:none"><a href="javascript:void(0)"><img src="./images/weather1.png"/></a>
                        <span class="text">天气</span>
                    </li>
                    <li id='button_resource_goodplay'><a href="javascript:void(0)"><img src="./images/goodplay.png"/></a>
                        <span class="text">主题展示</span>
                    </li>
                    <li id='button_resource_curricula' style="display:none"><a href="javascript:void(0)"><img src="./images/curricula.png"/></a>
                        <span class="text">课程表</span>
                    </li>
                    <li id='button_resource_countdown'><a href="javascript:void(0)"><img src="./images/countdown.png"/></a>
                        <span class="text">倒计时</span>
                    </li>
                </ul>
            </div>
            <!-- 新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      end  -->
        </nav>
        <section>
            <aside id="page_panel">
                <div >
                    <section>
                        <nav>
                            <ul>
                                <li class="pg_btn_1" style="cursor:pointer;" title="添加" id="create_page"><span></span></li>
                                <li class="pg_btn_2" style="cursor:pointer;display:none" title="复制" id="copy_page"><span></span></li>
                                <li class="pg_btn_3" style="cursor:pointer;" title="删除" id="delete_page"><span></span></li>
                            </ul>
                        </nav>
                        <comment  id="left_page_box"  onselectstart="return false;">
                            <ul id="left_page_inner" >
                            </ul>
                            <div class="drag_hint" id="page_drag_hint"></div>
                        </comment>
                    </section>
                    <section>
                        <div  id="page_panel_opr_btn"><span></span></div>
                    </section>
                </div>
            </aside>
            <article id="mainb">
                <div class="main" id="main_box">
                    <div class="inner" id="main_box_inner"></div>
                    <div class="baseline_horizontal" id="baseline_horizontal_1"></div>
                    <div class="baseline_horizontal" id="baseline_horizontal_2"></div>
                    <div class="baseLine_vertical" id="baseLine_vertical_1"></div>
                    <div class="baseLine_vertical" id="baseLine_vertical_2"></div>
                </div>
            </article>
            <aside  id="prpt_panel">
                <div>
                    <section>
                        <div id="prpt_panel_opr_btn"><span></span></div>
                    </section>
                    <section>
                        <comment id="property_bar">
                            <iframe src="" width="100%" height="100%">
                            </iframe>
                        </comment>
                    </section>
                </div>
            </aside>
        </section>
    </div>

    <div class="editEle_mask" id="editEle_mask_1"  style="display: none;">
        <div class="editEle_TL_resize"></div>
        <div class="editEle_T_resize"></div>
        <div class="editEle_TR_resize"></div>
        <div class="editEle_L_resize"></div>
        <div class="editEle_BL_resize"></div>
        <div class="editEle_B_resize"></div>
        <div class="editEle_R_resize"></div>
        <div class="editEle_BR_resize"></div>
    </div>
    <svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="200" width="200"
         class="editEle_mask" id="editEle_mask_2" style="position:absolute;overflow: visible;display: none;" pointer-events="none">
        <g fill="gray">
            <circle cx="0" cy="0" r="3" pointer-events="all" class="editEle_TL_resize"></circle>
            <circle cx="50%" cy="0" r="3" pointer-events="all"  class="editEle_T_resize"></circle>
            <circle cx="100%" cy="0" r="3" pointer-events="all"  class="editEle_TR_resize"></circle>
            <circle cx="0" cy="50%" r="3" pointer-events="all"  class="editEle_L_resize"></circle>
            <circle cx="0" cy="100%" r="3" pointer-events="all"  class="editEle_BL_resize"></circle>
            <circle cx="50%" cy="100%" r="3" pointer-events="all"  class="editEle_B_resize"></circle>
            <circle cx="100%" cy="50%" r="3" pointer-events="all"  class="editEle_R_resize"></circle>
            <circle cx="100%" cy="100%" r="3" pointer-events="all"  class="editEle_BR_resize"></circle>
        </g>
    </svg>
    <div class="ctrlEle_mask" id="ctrlEle_mask" style="display: none;">
    </div>
    <div class="deleted_els" id="deleted_els" style="display: none;">
    </div>
    <div class="deleted_pgs" id="deleted_pgs" style="display: none"></div>
    <div class="cut_els" id="cut_els" style="display: none;">
    </div>
    <div id="mylayer" class="mylayer" style="display:none;position:absolute;z-index:10000">
        <table id="layertable" class="layerTable" >
            <tr id="row0">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">剪切</td>
            </tr>
            <tr id="row1">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">复制</td>
            </tr>
            <tr id="row2">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">粘贴</td>
            </tr>
            <tr id="row3">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">置顶</td>
            </tr>
            <tr id="row4">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">置底</td>
            </tr>
            <tr id="row5">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">上移一层</td>
            </tr>
            <tr id="row6">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">下移一层</td>
            </tr>
            <tr id="row7">
                <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">删除</td>
            </tr>
            <!--   【普通节目编辑页面的   右键 锁定 解锁 功能  如需增加功能 放开屏蔽】  
            <tr id="row8">
                 <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">锁定</td>
             </tr> 
             <tr id="row9">
                 <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">解锁</td>
             </tr>  -->
        </table>
    </div>
   <div class="foot">
       <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>

   </div>
</div>
     <!--【普通节目编辑页面  部件在呗锁定的状态下     右键的 锁定 解锁 功能   如需增加功能 放开屏蔽】  
     <div id="mylayerLock" class="mylayer" style="display:none;position:absolute;z-index:10000">
         <table id="layerLocktable" class="layerTable" >
             
             <tr id="row10">
                 <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">锁定</td>
             </tr> 
             <tr id="row11">
                 <td style="cursor:pointer;" class="mouseOutStyle" onmouseover="this.className='mouseOverStyle'" onmouseout="this.className='mouseOutStyle'">解锁</td>
             </tr>
         </table>
     </div>
      --> 
     <script>
         var DSS = {};
     </script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/util/util.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/util/makeId.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/accessor/leftMenuAcr.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/request/request.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/options.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/Align.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/dialog.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/dedialog.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/templatedialog.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/pageratedialog.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/Level.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAdd.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockMove.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockResize.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockMvAndRs.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockPrptChange.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockSrcChange.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockSrcChangeQui.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockDel.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockCut.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockPaste.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockCopy.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAlignTop.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAlignBottom.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAlignLeft.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAlignRight.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAlignHorizontal.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAlignVertical.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockLock.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockUnlock.js" type="text/javascript" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDPageCreate.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDPageDelete.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDPageSelect.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDPageMove.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDPageCopy.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDPageUpdateTime.js" type="text/javascript" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockLevelTop.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockLevelBottom.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockLevelMoveDown.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockLevelMoveUp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDProgramSize.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDAddRsrc.js" type="text/javascript" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/CompClass.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/ImgComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/VideoComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/RectComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/AccordionComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/TextComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/Text2Comp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/HorizonLineComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/VerticalLineComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/WanderComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/ImageSwipeComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/Imgs3D.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/FlashComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/PPTComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/WebPageComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/StreamingComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/EWorksComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/TimeComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/WeatherComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/CourseListComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/CountdownComp.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/component/EditComponent.js" type="text/javascript" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/command.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/baseline.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/BlockOpr.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/menuTree.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/view/PageOpr.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/html2canvas.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/controller.js" type="text/javascript" charset="utf-8"></script>

    <!-- 以下的14个js的引入,是新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      begin  -->
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddText.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddFlowvideo.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddWeb.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddRectangle.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddHorizontaline.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddVerticalline.js" type="text/javascript" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddPinao.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddWander.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddSwepe.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddPpt.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAdd3D.js" type="text/javascript" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddTime.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddWeather.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddGoodplay.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddCurricula.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/command/CMDBlockAddCountdown.js" type="text/javascript" charset="utf-8"></script>
    <!--  以上的14个js的引入,是新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      begin  -->


    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/easyui-extends.js"></script>--%>
	
     <script>
         var si = 0;
         //树目录生成
         DSS.LeftMenuAcr.getData(function(acr){
             /* 负责加载 三个标签页  ：开始， 图片秀，  插件      --> 如果想还原拖拽效果请放开此处
              var leftMenuTree = new MenuTree(DSS.Options.eID.leftMenuBox,acr);
              leftMenuTree.init();*/
             DSS.Request.getProgramReq(DSS.Options.cur.program,function(rs){
                 DSS.util.loadJS(['js/view/Frame.js'],true);

                 // 分辨率的设置
                 var v=getUrlParameter('username');
                 var pagerate=getUrlParameter('proresolution');
                 var state=getUrlParameter('state');
                 var quick=getUrlParameter('quick');
                 document.getElementById("unam").innerText=v;
                 document.getElementById("page_rate_custom").setAttribute("_val",pagerate);
                 document.getElementById("defaultrate").innerHTML=pagerate;
                 document.getElementById("state").value=state;
                 document.getElementById("quick").value=quick;

                 var strs = pagerate.split(':');
                 if(strs[0]=='1080' && strs[1]=='1920'){
                 }else if(strs[0]=='768' && strs[1]=='1366'){
                 }else if(strs[0]=='1920' && strs[1]=='1080'){
                 }else if(strs[0]=='1366' && strs[1]=='768'){
                 }else if(strs[0]=='1280' && strs[1]=='720'){
                 }else if(strs[0]=='1024' && strs[1]=='768'){
                 }else{
                     document.getElementById("page_rate_custom").className="check ratio_select";
                 }
             });

         });
         //返回节目列表
         function navigateToModule(urlPath) {
             top.location.href = DSS.serverIp + '/ManagementCenter/'
             + urlPath.toLowerCase() + '/' + urlPath + '_home.do?module='
             + urlPath;
         }
         document.body.onselectstart = document.body.oncontextmenu=  function(){ return false;}
         function exit() {
             var r=confirm("您确定要退出系统吗?");
             if (r==true){
                 window.location.href = DSS.serverIp + '/ManagementCenter/home/Home_logout.do';
             }
         }
         function gohome() {
             window.location.href = DSS.serverIp + '/ManagementCenter/home/Home_login.do';
         }
         // 右上角的用户信息显示
         function getUrlParameter( name ){
             name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
             var regexS = "[\\?&]"+name+"=([^&#]*)";
             var regex = new RegExp( regexS );
             var results = regex.exec(window.parent.location.href );
             if(results == null)return "";
             else {
                 return results[1];
             }
         }
         $(function(){
         	$("#mylayer tr").click(function(){
         		$("#mylayer").hide()
         	})
         	
         })
     </script>
     
     
</body>
</html>