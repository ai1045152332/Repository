package com.honghe.recordweb.util;

import com.honghe.service.client.Result;
import com.honghe.service.client.http.HttpServiceClient;
import com.honghe.service.client.http.HttpUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Map;

/**
 * 通过配置文件获取不同服务的
 */
public class HttpServiceUtil {
    static Logger logger = Logger.getLogger(HttpServiceUtil.class);
    public final static String SERVICE_USER = "user";// 用户服务标识
    public final static String SERVICE_AD = "ad";// 目录服务标识
    public final static String SERVICE_AREA = "area";// 地点服务标识
    public final static String SERVICE_DEVICE = "device";// 设备服务标识
    public final static String SERVICE_CURRICULUM = "curriculum";// 课表服务标识
    public final static String SERVICE_RESOURSE = "iClassification";// 资源服务标识
    public final static String SERVICE_STORAGE = "storage";//存储服务标识
    public final static String SERVICE_SCREENCOMMAND = "screenCommand";//大屏设备命令标识
    public final static String SERVICE_RECORDCOMMAND = "recordCommand"; //录播设备命令标识
    public final static String SERVICE_PROJECTORCOMMAND = "projectorCommand";//投影机设备命令标识
    public static final String SERVICE_WHITEBOARD = "whiteboardCommand";//电子白板设备命令标识
    public final static String SERVICE_DSSCOMMAND = "digitalClassCommand";//班牌设备命令标识

    /**
     * 获取连接不同服务的客户端
     * @return
     */
    private static HttpServiceClient getServiceClient(String serviceType){
        if (SourceFactory.getSouceInstance(ConfigSource.class) != null) {
            return new HttpServiceClient(SourceFactory.getSouceInstance(ConfigSource.class).getIp(serviceType),
                    SourceFactory.getSouceInstance(ConfigSource.class).getPort(serviceType));
        } else {
            return null;
        }
    }

    /**
     * 抛出连接远程调用异常
     *
     * @param code 连接调用状态返回值
     * @throws Exception
     */
    private static void throwExceptionMessage(int code) throws Exception {
        if (code != 0) {
            logger.debug(parseErrorCode(code));
            throw new Exception("response fault:" + parseErrorCode(code));
        }
    }

    /**
     * 返回值错误解析
     *
     * @param code
     * @return
     */
    private static String parseErrorCode(int code) {
        String msg = "";
        if (code == -1) {
            msg = "参数错误";
        } else if (code == -2) {
            msg = "没有此方法";
        } else {
            msg = "未知错误";
        }
        return msg;
    }

    /**
     *
     * @param method 服务执行方法
     * @param params
     * @return
     * @throws Exception
     */
    public static Result service(String serviceType, String method, Map<String, String> params)  {
        Result re_value = getServiceClient(serviceType).execute(serviceType, method, params);
//        throwExceptionMessage(re_value.getCode());
        return re_value;
    }

    /**
     * 上传文件到服务器
     *
     * @param file
     * @return String 上传到服务器上的文件名
     */
    public static String uploadFile(File file){
        return HttpUtil.upload("http://" + SourceFactory.getSouceInstance(ConfigSource.class).getIp("upload") + ":" +
                SourceFactory.getSouceInstance(ConfigSource.class).getPort("upload") + "/service/cloud/httpUploadService", file);
    }

    public static enum Method {
        /*设备服务接口*/
        Device_GetConditionsHostListByPage("getConditionsHostListByPage"),
        Device_GetLocalModel("getLocalModel"),
        Device_Get_Status("getStatus"),
        Device_Update("update"),
        Device_HostInfo("hostInfo"),
        Device_HostInfoByPage("hostInfoByPage"),
        Device_NotHostInfo("notHostInfo"),
        Device_HostCount("hostCount"),
        Device_GetHostInfoById("getHostInfoById"),
        Device_HostInfoBytype("hostInfoBytype"),
        Device_NotHostInfoByPage("notHostInfoByPage"),
        Device_GetHostInfoByIp("getHostInfoByIp"),
        Device_GetAllHostList("getAllHostList"),
        Device_GetHostListByPage("getHostListWithAreaByPage"),
        Device_Command_Boot("boot"),
        Device_Command_Shutdown("shutdown"),
        Device_Command_ChangeSignal("changeSignal"),
        Device_Command_ChangeAudioMode("changeAudioMode"),
        Device_Command_ChangeAudioControl("changeAudioControl"),
        Device_Command_GetInfo("getInfo"),
        Device_Command_SendMessage("sendMessage"),
        Device_Command_GetPictrue("getPictrue"),
        Device_Command_UpdateCmdCodeSql("updateCmdCodeSql"),
        Device_Command_IsDeviceCmdCodeTimeOut("isDeviceCmdCodeTimeOut"),
        Device_Command_SetSysTime("setSysTime"),
        Device_Command_GetCmd("getCmd"),
        Device_Command_ChangeRemoteEnergy("changeRemoteEnergy"),
        Device_Command_SendAddress("sendAddress"),
        Device_Command_RegistEventAdrr("registEventAdrr"),
        Device_Command_Ring("ring"),
        Device_Command_Backup("backup"),
        Device_Command_Recovery("recovery"),
        Device_Command_ClearRubish("clearRubish"),
        Device_Command_OneKeyInstall("oneKeyInstall"),
        Device_Command_OneKeyUninstall("oneKeyUninstall"),
        Device_Command_AtvOrDtv("atvOrDtv"),
        Device_Hhrec_GetOnlineDevice("getOnlineDevice"),
        Device_HhrecCommand_GetCameraInfoById("getCameraInfoById"),
        Device_HhrecCommand_SetLogo("setLogo"),
        Device_HhrecCommand_GetLogo("getLogo"),
        Device_HhrecCommand_RemoveLogo("removeLogo"),
        Device_HhrecCommand_SetResolution("setResolution"),
        Device_HhrecCommand_GetResolution("getResolution"),
        Device_HhrecCommand_SetBackgroundDirectMode("setBackgroundDirectMode"),
        Device_HhrecCommand_SetBitrate("setBitrate"),
        Device_HhrecCommand_GetBitrate("getBitrate"),
        Device_HhrecCommand_SetNas("setNas"),
        Device_HhrecCommand_ClearNas("clearNas"),
        Device_HhrecCommand_GetRecordingStatus("getRecordingStatus"),
        Device_HhrecCommand_GetRecording("getRecording"),
        Device_HhrecCommand_GetVolume("getVolume"),
        Device_HhrecCommand_GetCaption("getCaption"),
        Device_HhrecCommand_GetTitleVideo("getTitleVideo"),
        Device_HhrecCommand_GetEndingVideo("getEndingVideo"),
        Device_HhrecCommand_GetCourseInfo("getCourseInfo"),
        Device_HhrecCommand_GetMainTokenByHostid("getMainTokenByHostid"),
        Device_HhrecCommand_GetDirectMode("getDirectMode"),
        Device_HhrecCommand_StartRecord("startRecord"),
        Device_HhrecCommand_StopRecord("stopRecord"),
        Device_HhrecCommand_AddPlanCommand("addPlanCommand"),
        Device_HhrecCommand_GetAllPlan("getAllPlan"),
        Device_HhrecCommand_GetCurrentLayout("getCurrentLayout"),
        Device_HhrecCommand_GetLive("getLive"),
        Device_HhrecCommand_GetAllMediaToken("getAllMediaToken"),
        Device_HhrecCommand_GetCameraUrl("getCameraUrl"),
        Device_HhrecCommand_GetLayout("getLayout"),
        Device_HhrecCommand_StartStreaming("startStreaming"),
        Device_HhrecCommand_RestartStreaming("restartStreaming"),
        Device_HhrecCommand_StopStreaming("stopStreaming"),
        Device_HhrecCommand_PauseStreaming("pauseStreaming"),
        Device_HhrecCommand_PauseRecording("pauseRecording"),
        Device_HhrecCommand_SetCourseInfo("setCourseInfo"),
        Device_HhrecCommand_SetEndingVideo("setEndingVideo"),
        Device_HhrecCommand_SetTitleVideo("setTitleVideo"),
        Device_HhrecCommand_SetLayout("setLayout"),
        Device_HhrecCommand_PTZStartMove("PTZStartMove"),
        Device_HhrecCommand_PTZStopMove("PTZStopMove"),
        Device_HhrecCommand_GetNasSettings("getNasSettings"),
        Device_HhrecCommand_SetNasSettings("setNasSettings"),
        Device_HhrecCommand_SetLayoutConfigurations("setLayoutConfigurations"),
        Device_HhrecCommand_GetPresets("getPresets"),
        Device_HhrecCommand_SetPresets("setPresets"),
        Device_HhrecCommand_InsertKeyNote("insertKeyNote"),
        Device_HhrecCommand_RestartRecording("restartRecording"),
        Device_HhrecCommand_SettingMutilScreen("settingMutilScreen"),
        Device_HhrecCommand_RemoveTitleVideo("removeTitleVideo"),
        Device_HhrecCommand_RemoveEndVideo("removeEndVideo"),
        Device_HhrecCommand_DelPlan("delPlan"),
        Device_HhrecCommand_DelAllPlan("delAllPlan"),
        Device_HhrecCommand_GetSupports("getSupports"),
        Device_HhrecCommand_SetVideoRecorderInfo("setVideoRecorderInfo"),
        Device_HhrecCommand_GetDeviceConfigList("getDeviceConfigList"),
        Device_ManualNetDiscovered("manualNetDiscovered"),
        Device_GetIp("getIp"),
        Device_GetNameByMac("getNameByMac"),
        Device_Command_SetProjectorTurnState("setProjectorTurnState"),
        Device_HhtcCommand_GetScreenLockMode("getScreenLockMode"),
        Device_HhtcCommand_SetScreenLockMode("setScreenLockMode"),
        Device_HhtcCommand_GetPanelKeyLockMode("getPanelKeyLockMode"),
        Device_HhtcCommand_SetPanelKeyLockMode("setPanelKeyLockMode"),
        Device_HtprCommand_GetProjectorAllState("getProjectorAllState"),
        Device_HhtwbCommand_SetBoardOneKeyLock("setBoardOneKeyLock"),
        Device_HhtwbCommand_GetBoardOneKeyLockState("getBoardOneKeyLockState"),
        Device_HhtwbCommand_SetBoardMuteState("setBoardMuteState"),
        Device_HhtwbCommand_GetBoardMuteState("getBoardMuteState"),
        Device_HhtwbCommand_SetBoardIncreaseVolume("setBoardIncreaseVolume"),
        Device_HhtwbCommand_SetBoardDecreaseVolume("setBoardDecreaseVolume"),
        Device_HhtwbCommand_SetBoardVolume("setBoardVolume"),
        Device_HhtwbCommand_GetBoardVolume("getBoardVolume"),
        Device_HhtwbCommand_SetBoardOpsRestart("setBoardOpsRestart"),
        Device_HhtwbCommand_SetBoardProjectorStandby("setBoardProjectorStandby"),
        Device_HhtwbCommand_GetBoardProjectorStandbyState("getBoardProjectorStandbyState"),
        Device_HhtwbCommand_SetBoardProjectorTurn("setBoardProjectorTurn"),
        Device_HhtwbCommand_GetBoardProjectorTurnState("getBoardProjectorTurnState"),
        Device_HhtwbCommand_SetBoardProjectorEnergy("setBoardProjectorEnergy"),
        Device_HhtwbCommand_GetBoardProjectorEnergyState("getBoardProjectorEnergyState"),
        Device_GetHostListByNames("getHostListByNames"),
        Device_Command_GetAllChannelIntersectionByType("getAllChannelIntersectionByType"),
        Device_Command_SetDeviceRestart("setDeviceRestart"),
        Device_HhtpaCommand_boot("boot"),
        Device_HhtpaCommand_shutdown("shutdown"),
        Device_HhtpaCommand_getInfo("getInfo"),
        Device_HhtpaCommand_setHeartbeat("setHeartbeat"),
        Device_HhtpaCommand_setTimeoutTime("setTimeoutTime"),
        Device_HhtpaCommand_setPersistTime("setPersistTime"),
        Device_HhrecCommand_SetPlanCommand("setPlanCommand"),
        Device_Command_UpdateHhtcHostName("updateHhtcHostName"),
        Device_Command_UpdateHhrecHostName("updateHhrecHostName"),
        Device_Command_DigSignBoardSwitchPlan("DigSignBoardSwitchPlan"),
        Device_Command_DelTempPlan("delTempPlan"),
        Device_Command_SetLubocfg("setLubocfg"),
        Device_Command_SetBrightness("setBrightness"),
        Device_Command_GetBrightness("getBrightness"),
        Device_Command_SetPTZSpeed("setPTZSpeed"),
        Device_Command_GetPTZSpeed("getPTZSpeed"),
        // 班牌
        Device_DigitalClassCommand_boot("boot"),
        Device_DigitalClassCommand_StartDigSignScreen("startDigSignScreen"),
        Device_DigitalClassCommand_shutdown("shutdown"),
        Device_DigitalClassCommand_ShutDigSignScreen("shutDigSignScreen"),
        Device_DigitalClassCommand_Ring("ring"),
        Device_DigitalClassCommand_SetDeviceRestart("setDeviceRestart"),
        Device_DigitalClassCommand_DigSignBoardCommonCommand("DigSignBoardCommonCommand"),
        /*用户服务接口*/
        User_Check("userCheck"),
        User_Search("userSearch"),
        User_Search_ByPage("userSearchByPage"),
        User_Delete("userDelete"),
        User_Add("userAdd"),
        User_Update("userUpdate"),
        User_IsExist("userIsExist"),
        Teach_Search_ByPage("teacherSearchByPage"),
        Teacher_Search_ByClassId("teacherSearchByClassId"),
        Student_Search_ByPage("studentSearchByPage"),
        Student_Search_ByClassId("studentSearchByClassId"),
        Student_Search_ByParentsId("studentSearchByParentsId"),
        Classes_Relation_Search_ByStudentId("classesRelationSearchByStudentId"),
        Classes_Relation_Search_ByTeacherId("classesRelationSearchByTeacherId"),
        Classes_Relation_Search_ByParentsId("classesRelationSearchByParentsId"),
        Classes_Relation_Delete_ByClassId("classesRelationDeleteByClassId"),
        Classes_Relation_Delete("classesRelationDelete"),
        Classes_Relation_Add("classesRelationAdd"),
        Parents_Relation_Search_ByStudentId("parentsRelationSearchByStudentId"),
        Parents_Relation_Add("parentsRelationAdd"),
        Role_Search("roleSearch"),
        RoleId_Search("roleIdSearch"),
        Role_Allot("roleAllot"),
        AuthCheck("authCheck"),
        Agency_Search("agencySearch"),
        Agency_Search_ByPage("agencySearchByPage"),
        Agency_Delete("agencyDelete"),
        Agency_Add("agencyAdd"),
        Agency_Update("agencyUpdate"),
        User_Agency_Realtion_Delete("userAgencyRelationDelete"),
        User_Agency_Realtion_Add("userAgencyRelationAdd"),
        User_InfoByToken("userInfoByToken"),
        Auth_Permissions_Search("authPermissionsSearch"),
        /*资源服务接口*/
        Stroage_FindNasConfig("findNasConfig"),
        /*课表服务接口*/
        Curriculum_MaxSection("detail_MaxSection"),
        Curriculum_WeekTable("detail_weekTable"),
        Curriculum_ClasstimeGetByRoom("classtime_getByRoom"),
        Curriculum_DetailByRoom("detail_byRoom"),
        Curriculum_ClassTimeFindByPloy("classtime_findByPloy"),//查找指定策略下的所有作息
        Curriculum_PloyGetByRoom("ploy_getByRoom"),//根据教室id查找策略
        Curriculum_PloyMaxSections("ploy_rMaxSections"),
        Curriculum_Detail_InRoom("detail_InRoom"),
        /*目录服务*/
        Ad_AreaSearch("areaSearch"),
        AD_Campus2room("campus2room"),
        AD_USER2DEVICEGROUP("user2DeviceGroup"),
        /*地点服务*/
        AERA_TREE("area_tree");
        private String name;
        private Method(String name) {
            this.name = name;
        }
        public String toString() {
            return this.name;
        }
    }
}
