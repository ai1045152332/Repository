/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-13
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */

var DSS = DSS || {};
DSS.Options = {
    el : {
        leftMenuBox :  DSS.util.byId('left_menu_box'),
        leftPageBox :  DSS.util.byId('left_page_box'),
        mainBox : DSS.util.byId('main_box'),
        mainBoxIn : DSS.util.byId('main_box_inner'),
        pageBoxIn : DSS.util.byId('left_page_inner'),
        pgDragHint : DSS.util.byId('page_drag_hint'),
        prptBar : DSS.util.byId('property_bar'),
        editElMask : DSS.util.isIE()?DSS.util.byId('editEle_mask_2'):DSS.util.byId('editEle_mask_1'),
        ctrEleMask : DSS.util.byId('ctrlEle_mask'),
        delElsDiv : DSS.util.byId('deleted_els'),
        cutElsDiv : DSS.util.byId('cut_els'),
        delPgsDiv : DSS.util.byId('deleted_pgs'),
        baslineH : DSS.util.byId('baseline_horizontal_1'),
        baslineH_2 : DSS.util.byId('baseline_horizontal_2'),
        baslineV : DSS.util.byId('baseLine_vertical_1'),
        baslineV_2 : DSS.util.byId('baseLine_vertical_2'),
        layerBox : DSS.util.byId('mylayer'),
        layerTable : DSS.util.byId('layertable'),
        compGrpList : DSS.util.byId('comp_group_list'),
        compPnlList : DSS.util.byId('comp_panel_list'),
        layerLocktable : DSS.util.byId('layerLocktable'), // 在普通编辑页面  如果部件处于锁定状态 则右键只允许进行 锁定 解锁两个操作    update 20150130
        layerQtable : DSS.util.byId('layerQtable'),  // 快速编辑页面 右键菜单（置顶 置底 上移一层 下移一层）   update 20150205
        mainb : DSS.util.byId('mainb')
    },
    eClass : {
        leftMenuItem : 'item',
        leftMenuGroup : 'group',
        leftMenuCheck : 'check',
        pageItem : 'item',
        pgItemIn : 'item_inner',
        pageImg :'page_img',
        pageFoot : 'page_footer',
        pageTime : 'page_time',
        pageTimeSet : 'page_time_set',
        pageTimeEdit : 'page_time_edit',
        pageCheck : 'check',
        editBlock : 'item',
        editElMask : 'editEle_mask',
        ctrElMask : 'ctrlEle_mask',
        TL_rszEl : 'editEle_TL_resize',
        T_rszEl : 'editEle_T_resize',
        L_rszEl : 'editEle_L_resize',
        BL_rszEl : 'editEle_BL_resize',
        B_rszEl : 'editEle_B_resize',
        TR_rszEl : 'editEle_TR_resize',
        R_rszEl : 'editEle_R_resize',
        BR_rszEl : 'editEle_BR_resize',
        pagePre:'page_pre'
    },
    eID : {
        leftMenuBox : 'left_menu_box'
    },
    btn : {
        'button_undo':'CMD_UNDO',
        'button_redo':'CMD_REDO',
        'button_del_blk':'CMD_BLOCK_DEL',
        'button_cut_blk':'CMD_BLOCK_CUT',
        'button_copy_blk':'CMD_BLOCK_COPY',
        'button_paste_blk':'CMD_BLOCK_PASTE',
        'button_align_top':'CMD_BLOCK_ALIGN_TOP',
        'button_align_bottom':'CMD_BLOCK_ALIGN_BOTTOM',
        'button_align_left':'CMD_BLOCK_ALIGN_LEFT',
        'button_align_right':'CMD_BLOCK_ALIGN_RIGHT',
        'button_align_horizontal':'CMD_BLOCK_ALIGN_HORIZONTAL',
        'button_align_vertical':'CMD_BLOCK_ALIGN_VERTICAL',

        'button_level_top' :'CMD_BLOCK_LEVEL_TOP',
        'button_level_bottom' :'CMD_BLOCK_LEVEL_BOTTOM',
        'button_level_up' :'CMD_BLOCK_LEVEL_UP',
        'button_level_down' :'CMD_BLOCK_LEVEL_DOWN',

        'button_baseline_no':'CMD_BASELINE_NO',
        'button_baseline_yes':'CMD_BASELINE_YES',
        'button_resource':'CMD_RESOURSE',
        'create_page':'CMD_CREATE_PAGE',
        'delete_page':'CMD_DELETE_PAGE',
        'copy_page':'CMD_COPY_PAGE',
        'page_preview':'CMD_PAGE_PREVIEW',
        'program_preview':'CMD_PROJECT_PREVIEW',
        'program_publish':'CMD_PROJECT_PUBLISH',
        'program_template':'CMD_PROJECT_TEMPLATE',
        'page_rate_custom':'CMD_PROJECT_PAGERATECUSTOM',
        'program_open':'CMD_PROJECT_OPEN',
        'program_open_Q':'CMD_PROJECT_OPEN_Q',
        'program_new':'CMD_PROJECT_NEW',
      
        /*以下的18的键值对,是新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      begin  */
        'button_resource_text':'CMD_NEW_BLOCK_TEXT',
        'button_resource_image':'CMD_RESOURSE_IMAGE',
        'button_resource_ppt':'CMD_RESOURSE_PPT',
        'button_resource_flash':'CMD_RESOURSE_FLASH',
        'button_resource_video':'CMD_RESOURSE_VIDEO',
        'button_resource_flowvideo':'CMD_NEW_BLOCK_FLOWVIDEO',
        'button_resource_webn':'CMD_NEW_BLOCK_WEB',
        'button_resource_rectangle':'CMD_NEW_BLOCK_RECTANGLE',
        'button_resource_horizontaline':'CMD_NEW_BLOCK_HORIZONTALINE',
        'button_resource_verticalline':'CMD_NEW_BLOCK_VERTICALLINE',
        'button_resource_piano':'CMD_NEW_BLOCK_PIANO',
        'button_resource_wander':'CMD_NEW_BLOCK_WANDER',
        'button_resource_swepe':'CMD_NEW_BLOCK_SWEPE',
        'button_resource_3D':'CMD_NEW_BLOCK_3D',
        'button_resource_timen':'CMD_NEW_BLOCK_TIME',
        'button_resource_weather1':'CMD_NEW_BLOCK_WEATHER',
        'button_resource_goodplay':'CMD_NEW_BLOCK_GOODPLAY',
        'button_resource_curricula':'CMD_NEW_BLOCK_CURRICULA',
        'button_resource_countdown':'CMD_NEW_BLOCK_COUNTDOWN' // 倒计时 add 20150519
         /*以下的18的键值对,是新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      begin  */
    },
    menuItem : {
        '1':{w:287,h:142,prp:'page/property/imgProperty.html',mnClass:'comp_item_1'},
        '2':{w:176,h:132,prp:'page/property/videoProperty.html',mnClass:'comp_item_2'},
        '3':{w:200,h:200,prp:'page/property/rectProperty.html',mnClass:'comp_item_3'},
        '4':{w:299,h:199,prp:'page/property/AccordionSimpleProperty.html',mnClass:'comp_item_4'},
        '5':{w:200,h:10,prp:'page/property/textProperty.html',mnClass:'comp_item_5'},
        '6':{w:200,h:10,prp:'page/property/horizonLineProperty.html',mnClass:'comp_item_6'},
        '7':{w:200,h:200,prp:'page/property/verticalLineProperty.html',mnClass:'comp_item_7'},
        '8':{w:200,h:200,prp:'page/property/AccordionSimpleProperty.html',mnClass:'comp_item_8'},
        '18':{w:200,h:200,prp:'page/property/NewPptProperty.html',mnClass:'comp_item_8'},
        '9':{w:497,h:301,prp:'page/property/AccordionProperty.html',mnClass:'comp_item_9'},
        '10':{w:300,h:185,prp:'page/property/AccordionSimpleProperty.html',mnClass:'comp_item_10'},
        '11':{w:400,h:300,prp:'page/property/flashProperty.html',mnClass:'comp_item_11'},
		'12':{w:400,h:300,prp:'page/property/pptProperty.html',mnClass:'comp_item_12'},
		'13':{w:400,h:300,prp:'page/property/linkProperty.html',mnClass:'comp_item_13'},
		'14':{w:400,h:300,prp:'page/property/streamProperty.html',mnClass:'comp_item_14'},
		'15':{w:300,h:169,prp:'page/property/eWorksProperty.html',mnClass:'comp_item_15'},
		'16':{w:145,h:62,prp:'page/property/time1Property.html',mnClass:'comp_item_16'},
		'19':{w:118,h:62,prp:'page/property/weather1Property.html',mnClass:'comp_item_19'},
		'22':{w:240,h:120,prp:'page/property/courseListProperty.html',mnClass:'comp_item_22'},
        '25':{w:200,h:50,prp:'page/property/text2Property.html',mnClass:'comp_item_25'},
        '26':{w:100,h:60,prp:'page/property/countdownProperty.html',mnClass:'comp_item_26'}
    },
    cur : {
        program : DSS.util.getQueryString('prid'),
        page : '1',
        //w : 720,
        //h :540
        w:1920,
        h:1080
    },
    comp_map : {},
    cmd_map : {},
    view : {
        scale : 1.0
    }
};
