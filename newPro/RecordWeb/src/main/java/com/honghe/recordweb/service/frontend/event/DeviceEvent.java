package com.honghe.recordweb.service.frontend.event;

import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.service.frontend.devicemanager.*;
import com.honghe.recordweb.service.frontend.devicemanager.message.DeviceResponseMessage;
import com.honghe.recordweb.service.frontend.ftp.Ftp4jService;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.monitor.MonitorService;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.service.frontend.news.NewsService;
import com.honghe.recordweb.service.frontend.onlinetime.OnlineTimeService;
import com.honghe.recordweb.service.frontend.resource.NasScanThread;
import com.honghe.recordweb.service.frontend.resource.ResourceService;
import com.honghe.recordweb.service.frontend.resource.ResourceWebServiceTool;
import com.honghe.recordweb.service.frontend.settings.RecordNameSettingService;
import com.honghe.recordweb.service.frontend.settings.Resolution;
import com.honghe.recordweb.service.frontend.settings.ResolutionValue;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.socket.SendMessage;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.tempplan.TemporaryplanService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import com.honghe.recordweb.service.frontend.video.VideoService;
import com.honghe.recordweb.service.frontend.websocket.DirectPool;
import com.honghe.recordweb.service.frontend.websocket.MessageSender;
import com.honghe.recordweb.service.frontend.websocket.SessionPool;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ThreadPoolUtil;
import it.sauronsoftware.ftp4j.FTPClient;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 设备通知回调类
 * User: zhanghongbin
 * Date: 14-10-9
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */
@Service("deviceEvent")
//@PropertySource("classpath:/system.properties")
public class DeviceEvent {
    private final static Logger logger = Logger.getLogger(DeviceEvent.class);
    private static final String HOST_ONLINE = "101";//设备上线
    private static final String HOST_OFFLINE = "102";//设备下线
    private static final String HOST_USER_PASSWORD_WRONG = "103";//用户密码错误
    private static final String Event_DeviceMessage = "104";//设备事件
    private static final String EVENT_TYPE_NAS_STORE = "event_recording_finished";
    private static final String NAS_STORE_PATH_KEY = "NASPath";
    private static final String EVENT_FIELD_SEPARATOR = ";"; //字段属性间隔符
    private static final String EVENT_FIELD_FLAG = "=";//字段属性内容分隔符，
    private Map<String, List<Map<String, String>>> cmdMap= new HashMap<>();
    private static final String CONTROL = "control";
    private static final String RECORD = "record";

    @Resource
    private Environment env;
    @Resource
    private MonitorService monitorService;
    @Resource
    private OnlineTimeService onlineTimeService;
    @Resource
    private SettingsService settingsService;
    @Resource
    private TemporaryplanService temporaryplanService;
    @Resource
    private RecordNameSettingService recordNameSettingService;
    /**
     * 有事件通知时执行的方法
     *
     * @param strEventType    事件类型 101 上线102下线103用户名密码错误104设备事件
     * @param deviceType      设备类型
     * @param strDeviceToken  设备IP
     * @param strEventContext 事件内容
     * @param deviceMac       设备MAC地址
     */
    public void excute(String strEventType, final String deviceType, String strDeviceToken, String strEventContext, String deviceMac) {
        final NVR nvr;
        final NVRCommand nvrCommand;
        final HostgroupService hostgroupService;
        final SettingsService settingsService;
        final SyslogService deviceLogService;
        final TimeplanService timeplanService;
        final ResourceService resourceService;
        final VideoService videoService;
        final ComputerCommand computerCommand;
        final WhiteboardCommand whiteboardCommand;
        final HTProjectorCommand htProjectorCommand;
        final HostDevice hostDevice;
        final ResourceWebServiceTool resourceWebServiceTool;

        try {
            nvr = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NVR.class);
            nvrCommand = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NVRCommand.class);
            hostgroupService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostgroupService.class);
            settingsService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(SettingsService.class);
            deviceLogService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(SyslogService.class);
            resourceService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResourceService.class);
            videoService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(VideoService.class);
            computerCommand = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ComputerCommand.class);
            whiteboardCommand = ContextLoaderListener.getCurrentWebApplicationContext().getBean(WhiteboardCommand.class);
            htProjectorCommand = ContextLoaderListener.getCurrentWebApplicationContext().getBean(HTProjectorCommand.class);
            resourceWebServiceTool = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResourceWebServiceTool.class);
            hostDevice = ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostDevice.class);
            timeplanService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(TimeplanService.class);
        } catch (Exception e) {
            logger.error("初始化事件对象", e);
            return;
        }

        if (strEventType.equals(HOST_USER_PASSWORD_WRONG)) {
            userNameOrPasswordWrong(deviceLogService, strDeviceToken, hostgroupService, nvr, settingsService);
            return;
        } else if (strEventType.equals(HOST_ONLINE)) {//开机事件
            final String ip = strDeviceToken;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    hostOnline(nvr, computerCommand, hostgroupService, nvrCommand, settingsService, ip, deviceType, resourceWebServiceTool,timeplanService);
//                }
//            }).start();
            ThreadPoolUtil.execute(new Runnable() {
                @Override
                public void run() {
                    hostOnline(nvr, computerCommand, hostgroupService, nvrCommand, settingsService, ip, deviceType, resourceWebServiceTool,timeplanService);
                }
            });
            //新增储存开机时间方法
            // 18-03-30 增加录播主机开机记录 add mz
            if (CommonName.DEVICE_TYPE_SCREEN.equals(deviceType)||CommonName.DEVICE_TYPE_WHITEBOARD.equals(deviceType) || CommonName.DEVICE_TYPE_RECOURD.equals(deviceType)) {
                try {
                    onlineTimeService.addOnTime(strDeviceToken, deviceMac,deviceType, CONTROL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//           //增加默认童锁事件
            if(CommonName.DEVICE_TYPE_SCREEN.equals(deviceType)||CommonName.DEVICE_TYPE_WHITEBOARD.equals(deviceType)){
                Initialization initinfo = settingsService.getinitInfo(deviceType);
                if(initinfo!=null) {
                    String lockstatus = initinfo.getLockStatus();
                    if (CommonName.DEVICE_TYPE_SCREEN.equals(deviceType)) {
                        computerCommand.changeTouchScreen(strDeviceToken, lockstatus);
                    } else if (CommonName.DEVICE_TYPE_WHITEBOARD.equals(deviceType)) {
                        whiteboardCommand.setBoardOneKeyLock(strDeviceToken, lockstatus);
                    }
                }
            }
//            //增加默认通道事件
            if (CommonName.DEVICE_TYPE_SCREEN.equals(deviceType)||CommonName.DEVICE_TYPE_WHITEBOARD.equals(deviceType)||CommonName.DEVICE_TYPE_PROJECTOR.equals(deviceType)){
                Initialization initinfo = settingsService.getinitInfo(deviceType);
                if(initinfo != null) {
                    String channel = initinfo.getChannelName();
                    String enchannel = "";
                    Map<String, Object> hostmap = new HashMap<>();
                    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                    hostmap = hostDevice.getHostInfoByIp(strDeviceToken);
                    String str_dspec_id = (String) hostmap.get("dspec_id");
                    if (deviceType.equals("hhtc")) {
                        cmdMap = computerCommand.getDspecCommand(CommonName.DEVICE_TYPE_SCREEN);
                    } else if (deviceType.equals("hhtwb")) {
                        cmdMap = whiteboardCommand.getDspecCommand(CommonName.DEVICE_TYPE_WHITEBOARD);
                    }
                    Iterator iterator = cmdMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        if (entry.getKey().toString().equals(str_dspec_id)) {
                            list = (List<Map<String, String>>) entry.getValue();
                            break;
                        }
                    }
                    //调用信号源切换执行默认通道
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).containsValue("信号源切换")) {
                            Map cmdmap = list.get(i);
                            String codetype[] = cmdmap.get("codetype").toString().split(",");
                            String code_cmd[] = cmdmap.get("code_cmd").toString().split(",");
                            for (int j = 0; j < codetype.length; j++) {
                                if (codetype[j].equals(channel)) {
                                    enchannel = code_cmd[j];
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (CommonName.DEVICE_TYPE_SCREEN.equals(deviceType)) {
                        computerCommand.changSignal(strDeviceToken, enchannel);
                    } else if (CommonName.DEVICE_TYPE_WHITEBOARD.equals(deviceType)) {
                        whiteboardCommand.changSignal(strDeviceToken, enchannel);
                    }
                }
            }
        }
        //增加关机事件 并不影响其他逻辑
        else if (strEventType.equals(HOST_OFFLINE)) {
            //储存关机时间
            if (CommonName.DEVICE_TYPE_SCREEN.equals(deviceType)||CommonName.DEVICE_TYPE_WHITEBOARD.equals(deviceType) || CommonName.DEVICE_TYPE_RECOURD.equals(deviceType)) {
                try {
                    onlineTimeService.updateOffTime(deviceMac);
                } catch (Exception e) {
//                    e.printStackTrace();
                    logger.error("储存关机时间异常",e);
                }
            }
        } else if (strEventType.equals(Event_DeviceMessage)) { //事件通知
            EventHelper eventHelper = new EventHelper();
            // eventHelper.initial();
            //拆分字符串
            String[] contextArr = eventHelper.stringSeparate(strEventContext, "");
            final int cont = 4;
            if (contextArr != null && contextArr.length >= cont) {
                String hostIp = strDeviceToken;

                Map<String, Object> hosts = hostDevice.getHostInfoByIp(hostIp);
                Integer hostid = new Integer(hosts.get("host_id").toString());

                /**判断信息类型*/
                if ("monitors".equals(contextArr[3])) {
                    JSONObject jsonobject = JSONObject.fromObject(contextArr[2]);
                    JSONObject soft = jsonobject.getJSONObject("Soft");
                    String softName = soft.getString("SoftName");
                    String time = soft.getString("Time");
                    String duration = soft.getString("Duration");

                    Monitor monitor = new Monitor();
                    monitor.setSoftName(softName);
                    monitor.setUseTime(Integer.parseInt(duration));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = null;
                    try {
                        date = sdf.parse(time);
                    } catch (ParseException e) {
                        logger.error("日期解析错误", e);
                    }
                    monitor.setCreateTime(date);
                    try {
                        Host host = hostgroupService.getHostInfoByIp(hostIp);
                        monitor.setMac(host.getHostMac());
                        monitor.setDeviceName(host.getHostName());
                    } catch (Exception e) {
                        logger.error("根据id获取主机信息 异常", e);
                    }
                    monitorService.addMonitorService(monitor);
                }
                else if ("OneKeyClean".equals(contextArr[3])){//垃圾清理
                    String ip = strDeviceToken;
                    String cleanStatus = contextArr[2];
                    if (cleanStatus.equals("true")){
                        deviceLogService.addDeviceLog(ip,"垃圾清理完毕","SYSTEM");
                    }else {
                        deviceLogService.addDeviceLog(ip,"垃圾清理失败","SYSTEM");
                    }

                }
                else if ("ClassName".equals(contextArr[3])) {//修改班级名称
                    String ip = strDeviceToken;
                    String hostName = contextArr[2];
                    hostDevice.updateHostName(ip, hostName,"HHTC");//录播没有修改班级名称事件，只有大屏有
                    deviceLogService.addDeviceLog(ip, "修改班级名称为" + hostName, "SYSTEM");
                } else {
                    String logContentTmp = eventHelper.generateLog(contextArr[2], contextArr[3]);
                    String logType = null;
                    if ("GetAlarm".equals(contextArr[3])) {
                        logType = "ALARM";
                        logContentTmp = contextArr[2];
                    } else {
                        logType = eventHelper.getEventType();
                    }
                    //如果此次数据有效，则写入日志
                    Map<String, String> fieldsMap = eventHelper.getCurrentEventFields();
                    if (hostIp != null && logType != null && !logType.equals("")) {
                        deviceLogService.addDeviceLog(hostIp, logContentTmp, logType);
                        return;
                    } else if (hostIp != null) {
                        if (contextArr[3].equals("event_disk_avaliable")) {
                            strEventContext = "可用空间 : " + contextArr[2] + "@@" + contextArr[3];
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {
                                logger.error("线程延时异常", e);
                            }
                        } else if (contextArr[3].equals("event_direct_modechange")) {
                            DirectPool.remove(strDeviceToken);
                        } else if (contextArr[3].equals(EVENT_TYPE_NAS_STORE) && fieldsMap != null && fieldsMap.size() > 0) {
                            if (fieldsMap.containsKey(NAS_STORE_PATH_KEY)) {
                                //---接收录播主机事件，扫描xml文件，写入数据库
                                String path = fieldsMap.get(NAS_STORE_PATH_KEY);
                                logger.debug("path值解析为" + path);
                                String hostName = hostgroupService.getHostNameByIp(hostIp);
                                if (path != null && !path.equals("")) {
                                    //--由于平台未部署到录播主机，未解决通过路径无法访问文件的问题
                                    String[] path_arr = path.split("/");
                                    if(":cifs:".equals(path.substring(0, path.indexOf("/")))){
                                        try {
                                            path = "\\\\" + path_arr[path_arr.length - 4] + "\\res\\" + path_arr[path_arr.length - 2] + "\\" + path_arr[path_arr.length - 1];
                                        }catch(Exception e){
                                            logger.error("cifs协议path值错误" + path);
                                        }
                                    }else{
                                        if (path_arr != null && path_arr.length > 0 && path.indexOf(":/") > 0) {
                                            if (!Character.isDigit(path.charAt(0))) {
                                                path = path.substring(1, path.length() - 1);
                                            }
                                            path = "\\\\" + path.substring(0, path.indexOf(":/")) + "\\res\\" + path_arr[path_arr.length - 2] + "\\" + path_arr[path_arr.length - 1];
                                            //path = "\\\\" + path.substring(0, path.indexOf(":/")) + path_arr[path_arr.length - 2] + "\\" + path_arr[path_arr.length - 1];
                                        }
                                    }
                                }
                                //由于接收事件时，录播主机的文件保存过程未完成，所以增加延时
                                try {
                                    Thread.sleep(5000);
                                } catch (Exception e) {
                                    logger.error("线程延时异常", e);
                                }
                                Host host = new Host();
                                try {
                                    host = hostgroupService.getHostInfoByIp(hostIp);
                                } catch (Exception e) {
                                    logger.error("根据ip获取设备信息失败", e);
                                }

                                // NasScanThread nst = new NasScanThread(resourceService, videoService, path, hostIp, hostName,Integer.valueOf(host.getDspecification().getDspecId()));
                                // NasScanThread nst = new NasScanThread(settingsService, resourceService, videoService, path, hostIp, hostName,Integer.valueOf(host.getDspecification().getDspecId()));
                                NasScanThread nst = new NasScanThread(settingsService, resourceService, videoService, path, hostIp, hostid, hostName,Integer.valueOf(host.getDspecification().getDspecId()));
                                nst.run();
                            }

                        } else if ("updateTboxChannel".equals(contextArr[3])) { // 修改tbox通道名称 add by mz
                            System.out.println(contextArr[3]);
                            //deviceDao.updateTBOXDeviceData(name_eleven,"11");
                            String tboxInfo = contextArr[2];
                            String[] tboxInfos = tboxInfo.split(" ");
                            String token = tboxInfos[0];
                            String deviceName = tboxInfos[1];

                            try {
                                String newDeviceName = new String(deviceName.split(":")[1].getBytes("ISO-8859-1"), "gb2312");
                                String newToken = token.split(":")[1];
                                // 只修改自定义1(10) 和 自定义2(11)
                                if ("10".equals(newToken) || "11".equals(newToken)) {
                                    settingsService.updateTboxDeviceName(newDeviceName, newToken);
                                    // settingsService.updateTboxDeviceConfigName(newDeviceName, newToken);
                                }
                            } catch (UnsupportedEncodingException e) {
                                logger.error(e);
                                e.printStackTrace();
                            }
                        } else if ("event_direct_start".equals(contextArr[3])) {
                            try {
                                String str_hostid = hostid +"";
                                onlineTimeService.addOnTime(str_hostid, deviceMac,deviceType, RECORD);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if ("event_direct_stop".equals(contextArr[3])) {
                            try {
                                onlineTimeService.updateOffTime(deviceMac);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (hostIp != null && logType != null && !logType.equals("") && !logContentTmp.equals("")) {
                        deviceLogService.addDeviceLog(hostid.toString(), logContentTmp, logType);
                        return;
                    }
                }
            }
        }
        logger.debug("事件通知====ip为:" + strDeviceToken + "事件类型:" + strEventType + ",设备类型:" + deviceType + ",事件内容:" + strEventContext);
        DeviceResponseMessage deviceResponseMessage = new DeviceResponseMessage(strDeviceToken, deviceType, DeviceResponseMessage.Type.EVENT, strEventContext);
        deviceResponseMessage.setEventType(strEventType);
        Set<String> idSet = SessionPool.Keys();
        for (String id : idSet) {
            MessageSender.send(id, deviceResponseMessage.toJson());
        }
        SendMessage.sendSocketMessage(deviceResponseMessage.toJson());
    }

    /**
     * 用户名或密码错误
     * @param deviceLogService
     * @param strDeviceToken
     * @param hostgroupService
     * @param nvr
     * @param settingsService
     */
    private void userNameOrPasswordWrong(SyslogService deviceLogService, String strDeviceToken, HostgroupService hostgroupService, NVR nvr, SettingsService settingsService) {
        String hostIp = strDeviceToken;
        // deviceLogService.addDeviceLog(hostIp, "用户名或密码错误", DeviceLogService.LogType.ERROR);
        deviceLogService.addDeviceLog(hostIp, "用户名或密码错误", "SYSTEM");
        Setting setting = settingsService.getSetting();
        String userName = null;
        String password = null;
        if (setting != null) {
            userName = setting.getNvrUsername();
            password = setting.getNvrPassword();
        }
        if (userName != null && userName.equals("")) {
            userName = null;
        }
        if (password != null && password.equals("")) {
            password = null;
        }
        nvr.update(hostIp, userName, password);
    }

    /**
     * 设置录像信息
     * @param hostIp
     * @param nvrCommand
     * @param setting
     * @param info
     */
    private void setVideoInfo(String hostIp, NVRCommand nvrCommand, Setting setting, Map<String, Object> info) {
        try {
            ResolutionValue resolutionValue = Resolution.getResolutionValue(setting.getRecordinfoResolution());
            nvrCommand.setVideoInfo(hostIp, info.get("host_id").toString(), resolutionValue.getX(), resolutionValue.getY(), setting.getRecordinfoBitRate());
        } catch (Exception e) {
            logger.error("设置录像信息异常", e);
//            logger.info(e.getMessage());
        }
    }

    /**
     * todo 加注释
     * @param nvr
     * @param computerCommand
     * @param hostgroupService
     * @param nvrCommand
     * @param settingsService
     * @param hostIp
     * @param deviceType
     * @param resourceWebServiceTool
     * @param timeplanService
     */
    private void hostOnline(final NVR nvr, final ComputerCommand computerCommand, final HostgroupService hostgroupService,
                            final NVRCommand nvrCommand, final SettingsService settingsService, final String hostIp,
                            String deviceType, final ResourceWebServiceTool resourceWebServiceTool,
                            final TimeplanService timeplanService) {
        if (hostIp != null) {
            try {
                if (deviceType.equals(CommonName.DEVICE_TYPE_RECOURD)) {
                    if (hostIp != null) {
                        if(settingsService.getLicenseVersion()==1) {//只有學校版對錄播主機進行操作。add by wzz2016-10-12
                            Map<String, Object> info = nvr.getExtensionInfo(hostIp);
                            Setting setting = settingsService.getSetting();
                            if (setting != null && !info.isEmpty()) {
                                setVideoInfo(hostIp, nvrCommand, setting, info);
                            }
                            List<Logo> logoList = settingsService.getLogoList();
                            if (logoList != null && !logoList.isEmpty()) {
                                setLogo(hostIp, nvrCommand, logoList);
                            }
                            Map<Integer, String[]> nas2hostMap = settingsService.getNas2hostMap();
                            if (nas2hostMap != null && !nas2hostMap.isEmpty()) {
                                setNas(hostIp, nvrCommand, info, settingsService.getNasList(), nas2hostMap);
                            }
                            if (resourceWebServiceTool != null) {
                                //设置录播主机ftp
                                resourceWebServiceTool.resetFtp(hostIp);
                            }
                            //同步录像计划
                            if(!info.get("host_factory").equals("honghe-wbox")) {
                                int hostId = Integer.parseInt(info.get("host_id").toString());
                                Calendar calendar = Calendar.getInstance();
                                List<Object[]> timePlanList = timeplanService.getTimeplanByHost(hostId, calendar.getTime());
                                List<Object[]> tempPlanList = temporaryplanService.getTempPlanByHost(hostId);
                                if (!timePlanList.isEmpty()) {
                                    timeplanService.setTimePlan(hostIp, timePlanList, hostId, nvrCommand);
                                }
                                //add by xinye
                                //判断数据中是否有临时计划，如果有则下发
                                else if (!tempPlanList.isEmpty()) {
                                    temporaryplanService.setTempPlan(hostIp, hostId, tempPlanList);
                                }
                                //如果数据库中没有计划，则清空设备上的计划
                                else {
                                    nvrCommand.delAllPlanCommand(hostId, hostIp);
                                }
                                //下发录像文件命名协议
                                RecordNameSetting recordNameSetting = recordNameSettingService.getSetting();
                                StringBuffer commondStr = new StringBuffer("%d_%t_");
                                if (recordNameSetting != null) {
                                    commondStr.append(recordNameSetting.isClassRoomName() ? "%c_" : "");
                                    commondStr.append(recordNameSetting.isSubjectName() ? "%s_" : "");
                                    commondStr.append(recordNameSetting.isTeacherName() ? "%l_" : "");
                                }
                                commondStr.append("%r_%e");
                                nvrCommand.setRecordInfo(hostIp, commondStr.toString());
                                //add by xinye end
                            }
                        }
                    }
                }
                else if (deviceType.equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
                    Host hostInfo = hostgroupService.getHostInfoByIp(hostIp);
                    String ip = hostgroupService.getIp();
                    //软件安装下发
                    computerCommand.oneKeyInstall(hostIp, "", "", ip, "21");
                    //信息发布下发
                    NewsService newsService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NewsService.class);
                    newsService.sendNewsInitial(hostInfo.getHostId().toString(), hostIp);
                }
            } catch (Exception e) {
                logger.error("根据id获取主机信息 异常", e);
            }
        }
    }

    /**
     * logo设置
     * @param hostIp
     * @param nvrCommand
     * @param logoList
     */
    private void setLogo(String hostIp, NVRCommand nvrCommand, List<Logo> logoList) {
        try {
            nvrCommand.removeLogo(hostIp);
        } catch (Exception e) {
//            logger.debug("除去logo异常");
            logger.error("除去logo异常", e);
        }
        if (logoList != null && !logoList.isEmpty()) {
            try {
                Logo logo = null;
                for (int i = 0; i < logoList.size(); i++) {
                    if (logoList.get(i).getLogoUsing() == 1) {
                        logo = logoList.get(i);
                    }
                }
                if (logo != null) {
                    String path = DeviceEvent.class.getResource("/").getPath().replaceAll("/WEB-INF/classes", "") + "upload";
                    String imagesaddr = path + File.separator + logo.getLogoName();
                    Ftp4jService ftp4jService = new Ftp4jService();
                    //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
                    FTPClient ftpClient = ftp4jService.run(hostIp, 21, ConfigUtil.get("FtpUserName"), ConfigUtil.get("FtpPassword"));
                    if (ftpClient != null) {
                        ftp4jService.uploadFile(ftpClient, imagesaddr, Constant.LOGOPATH);
                    }
                    Thread.sleep(1000);
//                    nvrCommand.setLogo(hostIp, Constant.LOGOPATH +  "/" + logo.getLogoName(),"1", logo.getLogoSite(), defaultResponseHandler);
                    nvrCommand.setLogo(hostIp, Constant.LOGOPATH + "/" + logo.getLogoName(), "1", logo.getLogoSite());

                }
            } catch (Exception e) {
                logger.error("设置Logo异常", e);
            }
        }

    }

    /**
     * 设置Nas
     * @param hostIp
     * @param nvrCommand
     * @param info
     * @param nasList
     * @param nas2hostMap
     */
    private void setNas(String hostIp, NVRCommand nvrCommand, Map<String, Object> info, List<Object[]> nasList, Map<Integer, String[]> nas2hostMap) {

        if (!nasList.isEmpty()) {
            if (nas2hostMap.containsKey(Integer.parseInt(info.get("host_id").toString()))) {
                String[] values = nas2hostMap.get(Integer.parseInt(info.get("host_id").toString()));
                int i = -1;
                for (i = 0; i < nasList.size(); i++) {
                    Object[] objects = nasList.get(i);
                    if (objects[0].toString().equals(values[0])) {
                        break;
                    }
                }
                if (i != -1) {
                    Object[] objects = nasList.get(i);
                    try {
                        nvrCommand.setNas(hostIp, objects[1].toString(), info.get("host_name").toString(), objects[2].toString(), objects[3].toString());
                    } catch (Exception e) {
                        logger.error("设置nas异常", e);
                    }
                }
            }
        }
    }

    /**
     * 设置比特率
     * @param hostIp
     * @param nvrCommand
     * @param setting
     * @param hostId
     */
    private void setBitrate(String hostIp, NVRCommand nvrCommand, Setting setting, int hostId) {
        try {
            nvrCommand.setBitrate(hostIp, String.valueOf(hostId), setting.getRecordinfoBitRate());
        } catch (Exception e) {
            logger.error("设置比特率异常", e);
        }
    }

    /**
     * 设置分辨率
     * @param hostIp
     * @param nvrCommand
     * @param setting
     * @param hostId
     */
    private void setResolution(String hostIp, NVRCommand nvrCommand, Setting setting, int hostId) {
        try {
            ResolutionValue resolutionValue = Resolution.getResolutionValue(setting.getRecordinfoResolution());
            // nvrCommand.setResolution(hostIp, info.get("host_id").toString(), resolutionValue.getX(), resolutionValue.getY(), defaultResponseHandler);
            nvrCommand.setResolution(hostIp, String.valueOf(hostId), resolutionValue.getX(), resolutionValue.getY());

        } catch (Exception e) {
            logger.error("设置分辨率异常", e);
        }
    }
//    private void setTimePlan(String hostIp,List<Object[]>timePlanList,int hostId,NVRCommand nvrCommand)
//    {
//        List<Map<String, Object>> recordTasks = new ArrayList<>();
//        for(int i=0;i<timePlanList.size();i++)
//        {
//            Object[] timePlan = timePlanList.get(i);
//            Map<String, Object> params = new HashMap<>();
//            params.put("hostId", String.valueOf(hostId));
//            params.put("timeplan_id", timePlan[0]);
//            params.put("ext", "");
//            params.put("week_id", timePlan[1]);
//            params.put("startTime", timePlan[4]);
//            params.put("stopTime", timePlan[5]);
//            params.put("subjectName",timePlan[6]);
//            params.put("teacher",timePlan[7]);
//            recordTasks.add(params);
//        }
//        nvrCommand.addPlanCommand(hostIp, recordTasks);
//    }
}