package com.honghe.recordweb.action.frontend.settings;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.Constant;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.ftp.Ftp4jService;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.resource.NasAutoDel;
import com.honghe.recordweb.service.frontend.resource.RWNasProperties;
import com.honghe.recordweb.service.frontend.resource.ResourceWebServiceTool;
import com.honghe.recordweb.service.frontend.settings.FtpSettingService;
import com.honghe.recordweb.service.frontend.settings.RecordNameSettingService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import com.honghe.recordweb.util.HongheDeviceServiceFactory;
import com.honghe.recordweb.util.IOUtil;
import com.honghe.recordweb.util.LicenseUtils;
import com.honghe.recordweb.util.base.util.ConfigUtil;
import com.honghe.service.client.Result;
import com.opensymphony.xwork2.ActionContext;
import it.sauronsoftware.ftp4j.FTPClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanghongbin on 2014/11/11.
 */
@Controller
//@PropertySource("classpath:/system.properties")
@Scope(value = "prototype")
public class SettingsAction extends BaseAction {
    private static final int BUFFER_SIZE = 8 * 1024;
    private final static Logger logger = Logger.getLogger(SettingsAction.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    @Resource
    private Environment env;
    @Resource
    private ClasstimeService classtimeService;
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private SettingsService settingsService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private NVR nvr;
    @Resource
    private Ftp4jService ftp4jService;
    @Resource
    private FtpSettingService ftpSettingService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private RecordNameSettingService recordNameSettingService;
    private RecordNameSetting recordNameSetting;
    private int settingType;
    private String recordinfoResolution;
    private String recordinfoBitRate;
    private String nvrUserName;
    private String nvrPassword;
    private String fileName;
    private String ftpName;
    private String ftpPort;
    private String uName;
    private String uPwd;
    private Setting setting;
    private int hostid;
    private Byte week_id = 0;
    private String date;
    private int curriculumId;
    private String curriculumName;
    private String teacherName;
    private int curSection;
    private String unit;
    private List<Object[]> ployGroups;
    private List<ClasstimePloy> classtimePloys = new ArrayList<ClasstimePloy>();
    private String timePloy;
    private Classtime classtime;
    private String[] timePloys;
    private String groupName;
    private Integer groupId;
    private Integer ployId;
    private Integer p2gId;
    private List<String[]> sectionlist;
    private Integer classtimePloy;
    private Initialization initInfo;
    private String classtimePloyName;
    private String channel;
    private String lockStatus;
    private String devicetype;
    private List channelName;
    private String flag;
    private Map<String, String> licenseInfo;
    private String licenseNum;//激活码
    private String applicationname;//申请人姓名
    private String applicationcompany; //公司/学校名称
    private String applicationtell; //电话
    private String applicationorder;//合同号
    private String type_activation;//激活类型：1 授权码激活；2 网络激活
    private String register = "false"; //是否注册成功
    private String reg_state = LicenseUtils.STATE_UN_REG; //返回注册状态
    private List classtimelist;
    private Map<Integer,String> intToUpper;
    private List<Object[]> hostlist;//主机列表
    private List timeplanList;//录像计划列表
    private Map<Integer, List<Curriculum>> curriculumMaps = new HashMap<Integer, List<Curriculum>>();
    private Map<Integer, List<Object[]>> classtimeMaps = new HashMap<Integer, List<Object[]>>();
    private Map<String,Object> jsonMap = new HashMap<String,Object>();
    private String hostIds;
    private String logoSite;
    private Integer logoId;
    private String logoFlag;
    private String liveAddr;
    private Integer nasId;
    private String nasRootpath;
    private String nasUsername;
    private String nasPassword;
    private Integer hostCnt;
    private List<Integer> sectionList;

    public String getClasstimePloyName() {
        return classtimePloyName;
    }

    public void setClasstimePloyName(String classtimePloyName) {
        this.classtimePloyName = classtimePloyName;
    }

    public Initialization getInitInfo() {
        return initInfo;
    }

    public void setInitInfo(Initialization initInfo) {
        this.initInfo = initInfo;
    }

    public Integer getClasstimePloy() {
        return classtimePloy;
    }

    public void setClasstimePloy(Integer classtimePloy) {
        this.classtimePloy = classtimePloy;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Object[]> getPloyGroups() {
        return ployGroups;
    }

    public void setPloyGroups(List<Object[]> ployGroups) {
        this.ployGroups = ployGroups;
    }

    public List<ClasstimePloy> getClasstimePloys() {
        return classtimePloys;
    }

    public void setClasstimePloys(List<ClasstimePloy> classtimePloys) {
        this.classtimePloys = classtimePloys;
    }

    public String getTimePloy() {
        return timePloy;
    }

    public void setTimePloy(String timePloy) {
        this.timePloy = timePloy;
    }

    public Classtime getClasstime() {
        return classtime;
    }

    public void setClasstime(Classtime classtime) {
        this.classtime = classtime;
    }

    public String[] getTimePloys() {
        return timePloys;
    }

    public void setTimePloys(String[] timePloys) {
        this.timePloys = timePloys;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPloyId() {
        return ployId;
    }

    public void setPloyId(Integer ployId) {
        this.ployId = ployId;
    }

    public Integer getP2gId() {
        return p2gId;
    }

    public void setP2gId(Integer p2gId) {
        this.p2gId = p2gId;
    }

    public List<String[]> getSectionlist() {
        return sectionlist;
    }

    public void setSectionlist(List<String[]> sectionlist) {
        this.sectionlist = sectionlist;
    }

    public RecordNameSetting getRecordNameSetting() {
        return recordNameSetting;
    }

    public void setRecordNameSetting(RecordNameSetting recordNameSetting) { this.recordNameSetting = recordNameSetting; }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getHostid() {
        return hostid;
    }

    public void setHostid(int hostid) {
        this.hostid = hostid;
    }

    public int getCurSection() {
        return curSection;
    }

    public void setCurSection(int curSection) {
        this.curSection = curSection;
    }

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Byte getWeek_id() {
        return week_id;
    }

    public void setWeek_id(Byte week_id) {
        this.week_id = week_id;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Object[]> getHostlist() {
        return hostlist;
    }

    public void setHostlist(List<Object[]> hostlist) {
        this.hostlist = hostlist;
    }

    public Map<Integer, List<Curriculum>> getCurriculumMaps() {
        return curriculumMaps;
    }

    public void setCurriculumMaps(Map<Integer, List<Curriculum>> curriculumMaps) { this.curriculumMaps = curriculumMaps; }

    public Map<Integer, List<Object[]>> getClasstimeMaps() {
        return classtimeMaps;
    }

    public void setClasstimeMaps(Map<Integer, List<Object[]>> classtimeMaps) {
        this.classtimeMaps = classtimeMaps;
    }

    public List getTimeplanList() {
        return timeplanList;
    }

    public void List(List timeplanList) {
        this.timeplanList = timeplanList;
    }

    public List getClasstimelist() {
        return classtimelist;
    }

    public void setClasstimelist(List classtimelist) {
        this.classtimelist = classtimelist;
    }

    public Map<Integer, String> getIntToUpper() {
        return intToUpper;
    }

    public void setIntToUpper(Map<Integer, String> intToUpper) {
        this.intToUpper = intToUpper;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public Map<String, String> getLicenseInfo() {
        return licenseInfo;
    }

    public void setLicenseInfo(Map<String, String> licenseInfo) {
        this.licenseInfo = licenseInfo;
    }

    public String getHostIds() {
        return hostIds;
    }

    public void setHostIds(String hostIds) {
        this.hostIds = hostIds;
    }

    public String getLogoSite() {
        return logoSite;
    }

    public void setLogoSite(String logoSite) {
        this.logoSite = logoSite;
    }

    public Integer getLogoId() {
        return logoId;
    }

    public void setLogoId(Integer logoId) {
        this.logoId = logoId;
    }

    public String getLogoFlag() {
        return logoFlag;
    }

    public void setLogoFlag(String logoFlag) {
        this.logoFlag = logoFlag;
    }

    public void setSettingType(int settingType) {this.settingType = settingType; }

    public int getSettingType() {
        return settingType;
    }

    public void set(int settingType) {
        this.settingType = settingType;
    }

    public String getRecordinfoBitRate() {
        return recordinfoBitRate;
    }

    public void setRecordinfoBitRate(String recordinfoBitRate) {
        this.recordinfoBitRate = recordinfoBitRate;
    }

    public String getNvrUserName() {
        return nvrUserName;
    }

    public void setNvrUserName(String nvrUserName) {
        this.nvrUserName = nvrUserName;
    }

    public String getNvrPassword() {
        return nvrPassword;
    }

    public void setNvrPassword(String nvrPassword) {
        this.nvrPassword = nvrPassword;
    }

    public String getRecordinfoResolution() {
        return recordinfoResolution;
    }

    public void setRecordinfoResolution(String recordinfoResolution) { this.recordinfoResolution = recordinfoResolution; }

    public String getLiveAddr() {
        return liveAddr;
    }

    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    public Integer getNasId() {
        return nasId;
    }

    public void setNasId(Integer nasId) {
        this.nasId = nasId;
    }

    public String getNasRootpath() {
        return nasRootpath;
    }

    public void setNasRootpath(String nasRootpath) {
        this.nasRootpath = nasRootpath;
    }

    public String getNasUsername() {
        return nasUsername;
    }

    public void setNasUsername(String nasUsername) {
        this.nasUsername = nasUsername;
    }

    public String getNasPassword() {
        return nasPassword;
    }

    public void setNasPassword(String nasPassword) {
        this.nasPassword = nasPassword;
    }

    public Integer getHostCnt() {
        return hostCnt;
    }

    public void setHostCnt(Integer hostCnt) {
        this.hostCnt = hostCnt;
    }
    //------addbyhwx20160803------------
    private String nasTime;

    public String getNasTime() {
        return nasTime;
    }

    public void setNasTime(String nasTime) {
        this.nasTime = nasTime;
    }
    //------addbyhwx------------

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFtpName() {
        return ftpName;
    }

    public void setFtpName(String ftpName) {
        this.ftpName = ftpName;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUPwd() {
        return uPwd;
    }

    public void setUPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    public String getApplicationcompany() {
        return applicationcompany;
    }

    public void setApplicationcompany(String applicationcompany) {
        this.applicationcompany = applicationcompany;
    }

    public String getApplicationtell() {
        return applicationtell;
    }

    public void setApplicationtell(String applicationtell) {
        this.applicationtell = applicationtell;
    }

    public String getApplicationorder() {
        return applicationorder;
    }

    public void setApplicationorder(String applicationorder) {
        this.applicationorder = applicationorder;
    }

    public String getType_activation() {
        return type_activation;
    }

    public void setType_activation(String type_activation) {
        this.type_activation = type_activation;
    }

    public String getReg_state() {
        return reg_state;
    }

    public void setReg_state(String reg_state) {
        this.reg_state = reg_state;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public List getChannelName() {
        return channelName;
    }

    public void setChannelName(List channelName) {
        this.channelName = channelName;
    }

    public List<Integer> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Integer> sectionList) {
        this.sectionList = sectionList;
    }


    /**
     * todo 加注释
     * @return
     */
    public String logoList() {
        ActionContext.getContext().put("logolist", settingsService.getLogoList());
        return "logolist";
    }

    /**
     * todo 加注释
     * @return
     */
    public String hhtcSetting() {
        this.licenseInfo = LicenseUtils.findLicense();
        this.reg_state = LicenseUtils.getState();
        if (LicenseUtils.isRegister()) {
            this.register = "true";
        }
        return "findLicense";
    }

    /**
     * 获取默认通道信息列表
     * @return
     */
    public String initialization(){
        String type = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        //获得最初始的默认信息
        initInfo = settingsService.getinitInfo(type);
        //获取通道信息
        channelName = settingsService.getChannelName(type);
        return "initialization";
    }

    /**
     * 设置默认通道信息
     */
    public void InitSetting(){
        JSONObject jsonObject=new JSONObject();
        boolean flag =settingsService.updateInitInfo(channel, lockStatus, devicetype);
        if (flag){
            jsonObject.put("success",true);
            jsonObject.put("msg","初始化成功");
        }else {
            jsonObject.put("success",false);
            jsonObject.put("msg","初始化失败");
        }
        writeJSON(jsonObject.toString());
    }

    /**
     * 更新台标
     */
    public void uploadLogo() {
        JSONObject json = new JSONObject();
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            List<Logo> logoList = settingsService.getLogoList();
            Logo oldLogo = null;
            int oldLogoIndex = 0;
            if (logoList != null && !logoList.isEmpty()) {
                for (int i = 0; i < logoList.size(); i++) {
                    if (logoList.get(i).getLogoUsing() == 1) {
                        oldLogo = logoList.get(i);
                        oldLogoIndex = i;
                    }
                }
            }
            if (logoFlag.equals("1")) {
                if (delLogo(logoList)) {
                    json.put("success", "true");
                    json.put("msg", "台标移除成功");
                } else {
                    json.put("success", "false");
                    json.put("msg", "台标移除失败");
                }
            } else {
                final String path = ServletActionContext.getServletContext().getRealPath("/upload");
                if (!fileName.equals("")) {
                    if (addLogo(fileName, path, logoList, oldLogo, oldLogoIndex)) {
                        json.put("success", "true");
                        json.put("msg", "台标设置成功");
                    } else {
                        json.put("success", "false");
                        json.put("msg", "台标设置失败");
                    }
                } else {
                    if (editLogo(path, logoList, oldLogo, oldLogoIndex)) {
                        json.put("success", "true");
                        json.put("msg", "台标设置成功");
                    } else {
                        json.put("success", "false");
                        json.put("msg", "台标设置失败");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            json.put("success", "false");
            json.put("msg", "台标设置失败");
        }
        this.writeJSON(json.toString());
    }

    /**
     * 选择图片重新添加台标
     */
    public void uploadLogoToServer() {
        JSONObject json = new JSONObject();
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (((MultiPartRequestWrapper) request).getFileNames("file") != null) {
                final String path = ServletActionContext.getServletContext().getRealPath("/upload");
                String myFileName = ((MultiPartRequestWrapper) request).getFileNames("file")[0];
                File myFile = ((MultiPartRequestWrapper) request).getFiles("file")[0];
                int pos = myFileName.lastIndexOf(".");
                String str = myFileName.substring(pos);
                // 时间加后缀名保存
                final String saveName = new Date().getTime() + str;
                // 根据服务器的文件保存地址创建目录文件全路径

                File f = new File(new File(path), saveName);
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                copy(myFile, path + File.separator + saveName);
                BufferedImage image = ImageIO.read(new File(path + File.separator + saveName));
                int width = image.getWidth();
                int height = image.getHeight();
                json.put("success", "true");
                json.put("msg", saveName);
                json.put("width", width);
                json.put("height", height);
            } else {
                json.put("success", "false");
                json.put("msg", "");
            }
        }
        catch (Exception e)
        {
            json.put("success", "false");
            json.put("msg", "");
        }
        this.writeJSON(json.toString());
    }

    /**
     * 选择图片重新添加台标
     * @param fileName
     * @param path
     * @param logoList
     * @param oldLogo
     * @param oldLogoIndex
     * @return
     */
    private Boolean addLogo(String fileName, String path, List<Logo> logoList, Logo oldLogo, int oldLogoIndex) {
        if (oldLogo != null) {
            oldLogo.setLogoUsing(0);
            if (settingsService.updateLogo(oldLogo)) {
                logoList.set(oldLogoIndex, oldLogo);
                settingsService.setLogoList(logoList);
            }
        }
        Logo logo = new Logo();
        logo.setLogoName(fileName);
        logo.setLogoUsing(1);
        logo.setLogoSite(logoSite);
        boolean flag = settingsService.saveLogo(logo);
        if (flag == true) {
            logoList.add(0, logo);
            settingsService.setLogoList(logoList);
            setLogo(path, fileName, logoSite);
            return true;
        }
        return false;
    }

    /**
     * 更换已有台标位置或者已有台标间切换
     * @param path
     * @param logoList
     * @param oldLogo
     * @param oldLogoIndex
     * @return
     */
    private Boolean editLogo(String path, List<Logo> logoList, Logo oldLogo, int oldLogoIndex) {
        if (oldLogo != null) {
            oldLogo.setLogoUsing(0);
            if (settingsService.updateLogo(oldLogo)) {
                logoList.set(oldLogoIndex, oldLogo);
                settingsService.setLogoList(logoList);
            }
        }
        if (logoList != null && logoList.size() > 0) {
            if (logoId != null) {
                Logo usingLogo = null;
                for (int i = 0; i < logoList.size(); i++) {
                    if (logoId == logoList.get(i).getLogoId()) {
                        usingLogo = logoList.get(i);
                        usingLogo.setLogoSite(logoSite);
                        usingLogo.setLogoUsing(1);
                        if (settingsService.updateLogo(usingLogo)) {
                            logoList.set(i, usingLogo);
                            settingsService.setLogoList(logoList);
                            setLogo(path, usingLogo.getLogoName(), logoSite);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 禁用台标
     * @param logoList
     * @return
     */
    private Boolean delLogo(List<Logo> logoList) {
        if (logoList != null && logoList.size() > 0) {
            for (int i = 0; i < logoList.size(); i++) {
                Logo logo = logoList.get(i);
                if (logo.getLogoUsing() == 1) {
                    logo.setLogoUsing(0);
                    if (settingsService.updateLogo(logo)) {
                        logoList.set(i, logo);
                        removeLogo();
                    } else {
                        return false;
                    }
                }
            }
            settingsService.setLogoList(logoList);
            return true;
        }
        return true;
    }

    /**
     * 设置台标
     * @param path
     * @param saveName
     * @param logoSite
     */
    private void setLogo(final String path, final String saveName, final String logoSite) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Object[]> ipList = nvr.getOnlineClasses();
                for (int i = 0; i < ipList.size(); i++) {
                    JSONArray devices = JSONArray.fromObject(ipList.get(i));
                    final String ip = devices.getString(0);
                    String name = devices.getString(1);
                    String pass = devices.getString(2);
                    if (name != null && name.toString().equals("")) {
                        name = null;
                    }
                    if (pass != null && pass.toString().equals("")) {
                        pass = null;
                    }
                    String imagesaddr = path + File.separator + saveName;

                    FTPClient ftpClient = ftp4jService.run(ip, 21, env.getProperty("FtpUserName"), env.getProperty("FtpPassword"));
                    if (ftpClient != null) {
                        ftp4jService.uploadFile(ftpClient, imagesaddr, Constant.LOGOPATH);
                    }
                    nvrCommand.removeLogo(ip);
                    nvrCommand.setLogo(ip, Constant.LOGOPATH + "/" + saveName, "1", logoSite);
                }
            }
        });
    }

    /**
     * 移动台标
     */
    private void removeLogo() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Object[]> ipList = nvr.getOnlineClasses();
                for (int i = 0; i < ipList.size(); i++) {
                    JSONArray devices = JSONArray.fromObject(ipList.get(i));
                    final String ip = devices.getString(0);
                    nvrCommand.removeLogo(ip);
                }
            }
        });
    }

    /**
     * 禁用台标
     * @param src
     * @param path
     */
    public static void copy(File src, String path) {
        IOUtil.write(src, path);
    }

    /**
     *todo 加注释
     * @return
     */
    public String findRecordInfo() {
        Setting setting = settingsService.getSetting();
        ActionContext.getContext().put("setting", setting);
        return "findRecordInfo";
    }
    /**
     *todo 加注释
     * @return
     */
    public String findNvr() {
        Setting setting = settingsService.getSetting();
        ActionContext.getContext().put("setting", setting);
        return "findNvr";
    }
    /**
     *todo 加注释
     * @return
     */
    public String findLiveplay() {
        Setting setting = settingsService.getSetting();
        ActionContext.getContext().put("setting", setting);
        return "findLiveplay";
    }
    /**
     *todo 加注释
     * @return
     */
    public void saveSettingInfo() {
        JSONObject json = new JSONObject();
        Setting setting = settingsService.getSetting();
        boolean isNullSetting = false;
        if (setting == null) {
            setting = new Setting();
            isNullSetting = true;
        }
        if (settingType == 1) {
            if (settingsService.saveSettingService(isNullSetting, setting, Integer.parseInt(this.recordinfoResolution), Integer.parseInt(this.recordinfoBitRate))) {
                json.put("success", true);
                json.put("msg", "设置成功");
            } else {
                json.put("success", false);
                json.put("msg", "设置失败");
            }

        } else {
            if (settingType == 2) {
                if (this.liveAddr.endsWith("/") || this.liveAddr.endsWith("\\")) {
                    this.liveAddr = liveAddr.substring(0, this.liveAddr.length() - 1);
                }
                setting.setLiveAddr(this.liveAddr);
            } else if (settingType == 3) {
                setting.setNvrUsername(this.nvrUserName);
                setting.setNvrPassword(this.nvrPassword);
            }
            if (isNullSetting) {
                if (settingsService.saveSetting(setting)) {
                    if (settingType == 3) {
                        updateNvrUserAndPassword(setting);
                    }
                    json.put("success", true);
                    json.put("msg", "设置成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "设置失败");
                }
            } else {
                if (settingsService.updateSetting(setting)) {
                    if (settingType == 3) {
                        updateNvrUserAndPassword(setting);
                    }
                    json.put("success", true);
                    json.put("msg", "设置成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "设置失败");
                }
            }
        }
        settingsService.setSetting(setting);
        this.writeJSON(json.toString());
    }

    /**
     * 更新NVR用户密码
     * @param setting
     */
    private void updateNvrUserAndPassword(final Setting setting) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Object[]> onlineClasses = nvr.getOnlineClasses();
                for (Object[] obj : onlineClasses) {
                    nvr.update(obj[0].toString(), setting.getNvrUsername(), setting.getNvrPassword());
                }
            }
        });

    }
    /**
     *todo 加注释
     * @return
     */
    public String findNas() {
        List<Object[]> nasList = settingsService.getNasList();
        //--------addbyhwx20160803---
        String timeNasDelete = ConfigUtil.get("timeNasDelete");
        ActionContext.getContext().put("timeNasDelete", timeNasDelete);
        ActionContext.getContext().put("nasDelMap", RWNasProperties.getNasMap());
        //------------------------
        ActionContext.getContext().put("naslist", nasList);
        return "findNas";
    }
    /**
     *添加Nas
     */
    public void addNas() {
        JSONObject json = new JSONObject();
        try {
            if (!nasRootpath.equals("")) {
                Nas nas = new Nas();
                nas.setNasRootpath(nasRootpath);
                nas.setNasUsername(nasUsername);
                nas.setNasPassword(nasPassword);
                List<Object[]> nasList = settingsService.getNasByPath(nasRootpath);
                if(nasList.isEmpty()) {
                    Integer id = settingsService.saveNas(nas);
                    if (id > 0) {
                        Object[] obj = new Object[5];
                        obj[0] = id;
                        obj[1] = nasRootpath;
                        obj[2] = nasUsername;
                        obj[3] = nasPassword;
                        obj[4] = hostCnt;
                        List<Object[]> objList = settingsService.getNasList();
                        objList.add(obj);
                        settingsService.setNasList(objList);
                        //-------addbyhwx20160803-----
                        NasAutoDel.setValue(String.valueOf(id), nasTime);
                        //-------addbyhwx20160803-----
                        json.put("success", true);
                        json.put("msg", "添加成功");
                    } else {
                        json.put("success", false);
                        json.put("msg", "添加失败");
                    }
                }
                else
                {
                    json.put("success", false);
                    json.put("msg", "NAS路径已存在");
                }
            } else {
                json.put("success", false);
                json.put("msg", "请输入NAS根路径");
            }
        } catch (Exception e) {
            logger.error("", e);
            json.put("success", false);
            json.put("msg", "添加失败");
        }
        writeJSON(json.toString());
    }
    /**
     *更新Nas
     */
    public void updateNas() {
        JSONObject json = new JSONObject();

        if (!nasRootpath.equals("")) {
            Nas nas = new Nas();
            nas.setNasId(nasId);
            nas.setNasRootpath(nasRootpath);
            nas.setNasUsername(nasUsername);
            nas.setNasPassword(nasPassword);
            boolean result = settingsService.updateNas(nas);
            if (result) {
                setNas(nas, settingsService.getNas2hostMap());
                List<Object[]> objList = settingsService.getNasList();
                int index = -1;
                for (int i = 0; i < objList.size(); i++) {
                    if (objList.get(i)[0] == nasId) {
                        index = i;
                        break;
                    }
                }
                Object[] obj = new Object[5];
                obj[0] = nasId;
                obj[1] = nasRootpath;
                obj[2] = nasUsername;
                obj[3] = nasPassword;
                obj[4] = hostCnt;
                objList.set(index, obj);
                //-------addbyhwx20160803-----
                NasAutoDel.setValue(String.valueOf(nasId),nasTime);
                //-------addbyhwx20160803-----
                json.put("success", true);
                json.put("msg", "修改成功");
            } else {
                json.put("success", false);
                json.put("msg", "修改失败");
            }
        } else {
            json.put("success", false);
            json.put("msg", "请输入NAS根路径");
        }
        writeJSON(json.toString());
    }

    /**
     * 存在Nas根目录
     */
    public void hasNasPath() {
        JSONObject json = new JSONObject();
        if (settingsService.hasNasPath(this.nasId, this.nasRootpath)) {
            json.put("success", true);
            json.put("msg", "nas根目录已存在");
        } else {
            json.put("success", false);
            json.put("msg", "");
        }
        writeJSON(json.toString());
    }

    /**
     * 删除Nas
     */
    public void deleteNas() {
        JSONObject json = new JSONObject();
        Map<Integer, String[]> _nas2hostMap = settingsService.getNas2hostMap();
        Set<Map.Entry<Integer, String[]>> entrySet = _nas2hostMap.entrySet();
        for (Map.Entry<Integer, String[]> entry : entrySet) {
            String[] values = entry.getValue();
            Map<String, Object> host = settingsService.getHost(entry.getKey());
            String ip = host == null || host.isEmpty() ? "" : host.get("host_ipaddr").toString();
            if (this.nasId == Integer.parseInt(values[0])) {
                if (nvr.isOnline(ip)) {
                    Map<String, Object> status = nvrCommand.getRecordingStatus(ip);
                    if (!status.isEmpty()) {
                        if (!status.get("Status").toString().equals("STOP")) {
                            json.put("success", false);
                            json.put("msg", host.get("host_name") + "正在录制不能移除Nas");
                            writeJSON(json.toString());
                            return;
                        }
                    }
                }
            }
        }
        if (settingsService.deleteNas(String.valueOf(this.nasId))) {

            for (Map.Entry<Integer, String[]> entry : entrySet) {
                String[] values = entry.getValue();
                if (this.nasId == Integer.parseInt(values[0])) {
                    String[] deleteValues = _nas2hostMap.remove(entry.getKey());
                    setNas(deleteValues);
                    break;
                }
            }
            settingsService.setNas2hostMap(_nas2hostMap);
            List<Object[]> nasList = settingsService.getNasList();
            for (int i = 0; i < nasList.size(); i++) {
                if (Integer.parseInt(nasList.get(i)[0].toString()) == this.nasId) {
                    nasList.remove(i);
                    break;
                }
            }
            settingsService.setNasList(nasList);
            //-------addbyhwx20160803-----
            NasAutoDel.removeValue(String.valueOf(nasId));
            //-------addbyhwx20160803-----
            json.put("success", true);
            json.put("msg", "");
        } else {
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * todo 加注释
     * @return
     */
    public String nasAssignHost() {
        ActionContext.getContext().put("noNas2hostList", settingsService.getNoNas2HostList());
        Map<Integer, String> hostMap = new HashMap<Integer, String>();
        Map<Integer, String[]> nas2HostMap = settingsService.getNas2hostMap();
        Set<Integer> keySet = nas2HostMap.keySet();
        String str = "";
        for (Integer hostId : keySet) {
            String[] values = nas2HostMap.get(hostId);
            if (this.nasId == Integer.parseInt(values[0].toString())) {
                str += hostId + ",";
            }
        }
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
            DeviceServiceClient deviceServiceClient = HongheDeviceServiceFactory.getDeviceServiceClient();
            Map<String, String> params = new HashMap<>();
            params.put("deviceType", "hhrec");
            params.put("hostIdStr", str);
            Result res = deviceServiceClient.execute("device", DeviceServiceClient.Method.Device_HostInfo, params);
            if (res.getCode() == 0 && res.getValue() != null) {
                List<Map<String, Object>> values = (List<Map<String, Object>>) res.getValue();
                if (!values.isEmpty()) {
                    for (int i = 0; i < values.size(); i++) {
                        Map<String, Object> value = (Map<String, Object>) values.get(i);
                        Integer hostId = Integer.parseInt(value.get("id").toString());
                        String hostName = value.get("name").toString();
                        hostMap.put(hostId, hostName);
                    }
                }
            }
        }
        ActionContext.getContext().put("nas2hostMap", hostMap);
        return "findNas2Host";
    }

    /**
     * update by zlj on 2018/04/12
     * todo 加注释,测试
     */
    public void assignHost() {
        JSONObject json = new JSONObject();
        String[] hostIdArray;
        if (this.hostIds.length() != 0) {
            hostIdArray = this.hostIds.substring(0, hostIds.length() - 1).split(",");
        } else {
            hostIdArray = new String[0];
        }

        Map<Integer, String[]> nas2hostMap = settingsService.getNas2hostMap();
        Set<Integer> hostIdSet = nas2hostMap.keySet();
        List<String> currentNasHosts = new ArrayList<String>();
        for (Integer hostId : hostIdSet) {
            String[] values = nas2hostMap.get(hostId);
            if (Integer.parseInt(values[0].toString()) == this.nasId) {
                currentNasHosts.add(String.valueOf(hostId));
            }
        }

        //拼接设备ip，多个用逗号隔开，用来批量获取设备在离线状态-----------------
        StringBuilder hostIpStr = new StringBuilder();
        Map<String,String> hostNameMap = new HashMap<>();//存放设备名称，key为设备ip，value为对应的设备名称
        Map<String,String> hostIpMap = new HashMap<>();//存放设备ip，key为设备id，value为对应的设备ip
        for (String currentNasHost:currentNasHosts){
            boolean flag = false;
            for (String hostId : hostIdArray) {
                if (currentNasHost.equals(hostId)) {
                    flag = true;
                    break;
                }
            }
            if (!flag){
                Map<String, Object> host = settingsService.getHost(Integer.parseInt(currentNasHost));
                String ip = host == null || host.isEmpty() ? "" : host.get("host_ipaddr").toString();
                hostIpStr.append(ip);
                hostIpStr.append(",");
                hostNameMap.put(ip,host==null||host.isEmpty()?"" : host.get("host_name").toString());
                hostIpMap.put(host==null||host.isEmpty()?"" : host.get("host_id").toString(),ip);
            }
        }
        if (hostIpStr.length()>0&&hostIpStr.charAt(hostIpStr.length()-1)==','){
            hostIpStr.deleteCharAt(hostIpStr.length()-1);
        }
        //---------------------------拼接结束-----------------------------------

        Map<String,Object> statusMap = new HashMap<>();
        //批量查询设备在离线状态
        List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(hostIpStr.toString());
        //将设备的状态信息存入map中，key为ip，value为状态
        for (Map<String,String> status :deviceStatus){
            statusMap.put(status.get("ip"),status.get("deviceStatus"));
        }

        for (String _currentNasHosts : currentNasHosts) {
            boolean flag = false;
            for (String hostId : hostIdArray) {
                if (_currentNasHosts.equals(hostId)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                //hostIpMap.get(_currentNasHosts)获取设备id对应的设备ip
                boolean isOnline = statusMap.get(hostIpMap.get(_currentNasHosts)).equals("Online")?true:false;
                if (isOnline) {
                    Map<String, Object> status = nvrCommand.getRecordingStatus(hostIpMap.get(_currentNasHosts));
                    if (!status.isEmpty()) {
                        if (!status.get("Status").toString().equals("STOP")) {
                            json.put("success", false);
                            json.put("msg", hostNameMap.get(hostIpMap.get(_currentNasHosts)) + "正在录制不能从Nas中移除");
                            writeJSON(json.toString());
                            return;
                        }
                    }
                }
            }
        }
        if (settingsService.deleteNas2Host(currentNasHosts, hostIdArray, this.nasId)) {
            setNas(currentNasHosts, hostIdArray, this.nasId);
            json.put("success", true);
            json.put("msg", "");
        } else {
            json.put("success", false);
            json.put("msg", "分配失败");
        }

        writeJSON(json.toString());
    }

    /**
     * 设置Nas
     * @param deleteValues
     */
    private void setNas(final String[] deleteValues) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    nvrCommand.clearNas(deleteValues[2]);
                } catch (Exception e) {
                    logger.error("", e);
                    //e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置Nas
     * todo 在离线状态批量获取
     * @param nas
     * @param nas2hostMap
     */
    private void setNas(final Nas nas, final Map<Integer, String[]> nas2hostMap) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Set<Integer> keySet = nas2hostMap.keySet();
                for (Integer key : keySet) {
                    String[] values = nas2hostMap.get(key);
                    if (Integer.parseInt(values[0]) == nas.getNasId()) {
                        try {
                            Map<String, Object> host = settingsService.getHost(key);
                            String ip = host.get("host_ipaddr").toString();
                            String hostName = host.get("host_name").toString();
                            if (nvr.isOnline(ip)) {
                                nvrCommand.setNas(ip, nas.getNasRootpath(), hostName, nas.getNasUsername(), nas.getNasPassword());
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                }
            }
        });
    }

    /**
     * 设置Nas
     * @param currentNasHosts
     * @param hostIdArray
     * @param nasId
     */
    private void setNas(final List<String> currentNasHosts, final String[] hostIdArray, final Integer nasId) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //清空所有跟nas关联主机
                List<Object[]> onlineClasses = nvr.getOnlineClasses();
                if (hostIdArray.length == 0) {
                    for (String cnh : currentNasHosts) {

                        Host host = new Host();
                        try {
                            host = hostgroupService.getHostInfo(Integer.valueOf(cnh));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (host == null) {
                            host = new Host();
                        }
                        for (int i = 0; i < onlineClasses.size(); i++) {
                            JSONArray obj = JSONArray.fromObject(onlineClasses.get(i));
                            if (obj.getString(0).equals(host.getHostIpaddr())) {
                                try {
                                    nvrCommand.clearNas(obj.getString(0));
                                } catch (Exception e) {
                                    logger.error("", e);
                                    //e.printStackTrace();
                                }
                            }
                        }
                    }
                    return;
                }
                List<Object[]> nasList = settingsService.getNasList();
                int i = 0;
                for (i = 0; i < nasList.size(); i++) {
                    Object[] obj = nasList.get(i);
                    if (Integer.parseInt(obj[0].toString()) == nasId) {
                        break;
                    }
                }
                List<String> hostIdList = new ArrayList<String>();
                for (String hostId : hostIdArray) {
                    hostIdList.add(hostId);
                }
                //获取相同hostId;
                try {
                    hostIdList.retainAll(currentNasHosts);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (String currentHostId : currentNasHosts) {
                    if (!hostIdList.contains(currentHostId)) {
                        Host host = new Host();
                        try {
                            host = hostgroupService.getHostInfo(Integer.valueOf(currentHostId));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (host == null) {
                            host = new Host();
                        }
                        for (int m = 0; m < onlineClasses.size(); m++) {
                            JSONArray obj = JSONArray.fromObject(onlineClasses.get(m));
                            if (obj.getString(0).equals(host.getHostIpaddr())) {
                                try {
                                    nvrCommand.clearNas(obj.getString(0));
                                } catch (Exception e) {
                                    logger.error("", e);
                                    // e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                for (String hostId : hostIdArray) {
                    if (!hostIdList.contains(hostId)) {

                        Host host = new Host();
                        try {
                            host = hostgroupService.getHostInfo(Integer.valueOf(hostId));
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                        if (host == null) {
                            host = new Host();
                        }
                        for (int m = 0; m < onlineClasses.size(); m++) {
                            JSONArray obj = JSONArray.fromObject(onlineClasses.get(m));
                            if (obj.getString(0).equals(host.getHostIpaddr())) {
                                try {
                                    Object[] nas = nasList.get(i);
                                    nvrCommand.setNas(obj.getString(0), nas[1].toString(), host.getHostName(), nas[2].toString(), nas[3].toString());
                                } catch (Exception e) {
                                    logger.error("", e);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 查询FTP连接
     * @return
     */
    public String findFtp() {
        Map<String, String> mapftp = ftpSettingService.getFtp();
        if (mapftp != null && mapftp.containsKey("ftp_addr")) {
            this.ftpName = mapftp.get("ftp_addr");
            this.ftpPort = mapftp.get("ftp_port");
            this.uName = mapftp.get("ftp_user");
            this.uPwd = mapftp.get("ftp_pass");
        }
        return "findFtp";
    }

    /**
     * 保存FTP配置
     */
    public void saveSettingFtp() {
        JSONObject json = new JSONObject();
        Map<String, String> mapftp = new HashMap<String, String>();
        mapftp.put("ftp_addr", this.ftpName);
        mapftp.put("ftp_port", this.ftpPort);
        mapftp.put("ftp_user", this.uName);
        mapftp.put("ftp_pass", this.uPwd);
        if (ftpSettingService.updateFtpSetting(mapftp)) {
            synchroFtp();//给在线录播主机同步ftp设置
            json.put("success", true);
            json.put("msg", "修改成功");
        } else {
            json.put("success", false);
            json.put("msg", "修改失败");
        }
        this.writeJSON(json.toString());
    }

    /**
     * 显示注册信息
     *
     * @return
     */
    public String findLicense() {
        this.licenseInfo = LicenseUtils.findLicense();
        this.reg_state = LicenseUtils.getState();
        if (LicenseUtils.isRegister()) {
            this.register = "true";
        }
        return "findLicense";
    }

    /**
     * 显示注册信息
     *
     * @return
     */
    public String register() {
        this.licenseInfo = LicenseUtils.findLicense();
        this.reg_state = LicenseUtils.STATE_UN_REG;
        if (LicenseUtils.isRegister()) {
            this.register = "false";
        }
        return "findLicense";
    }

    /**
     * 显示注册信息
     *
     * @return
     */
    public String findLicenseKey() {
        this.licenseInfo = LicenseUtils.findLicense();
        if (LicenseUtils.isRegister()) {
            this.register = "true";
        }
        return "findLicenseKey";
    }

    /**
     * 注册
     */
    public void saveLicense() {
        String message = LicenseUtils.regLicense(this.licenseNum.trim(), this.applicationname.trim(), this.applicationcompany.trim(), this.applicationtell.trim(), this.applicationorder, this.type_activation.trim());
        JSONObject json = new JSONObject();
        json.put("msg", message);
        json.put("success", LicenseUtils.isRegister());
        this.writeJSON(json.toString());
    }

    //同步精品ftp设置
    public void synchroFtp() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final ResourceWebServiceTool resourceWebServiceTool = DaoFactory.getDao(ResourceWebServiceTool.class);
                List<Object[]> ipList = nvr.getOnlineClasses();
                for (int i = 0; i < ipList.size(); i++) {
                    final String ip = ipList.get(i)[0].toString();
                    resourceWebServiceTool.resetFtp(ip);
                }
            }
        });
    }

    /**
     * 课表下载
     */
    public void curriculumDownload()
    {
        Setting setting = settingsService.getSetting();
        // 初始设置周课表
        int curriculumType = Curriculum.CUR_WEEK_TYPE;
        if (setting != null && setting.getCurriculumType() != null) {
            curriculumType = setting.getCurriculumType();
        }
        String path = ServletActionContext.getServletContext().getRealPath("/export");
        if(curriculumType == Curriculum.CUR_WEEK_TYPE) {
            settingsService.creatExcelTempletService();
            path += "\\周课表模版.xls";
        }
        else
        {
            path += "\\学期课表模版.xls";
        }
        try {
            ServletActionContext.getResponse().setContentType("text/pain;charset=utf-8");

            if (curriculumType == Curriculum.CUR_WEEK_TYPE) {
                // sIn.getBytes("GBK"), "ISO-8859-1"
                ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;fileName=" + new String("周课表模版".getBytes("GBK"), "ISO-8859-1")+".xls");
            } else {
                ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;fileName=" + new String("学期课表模版".getBytes("GBK"), "ISO-8859-1")+".xls");
            }
            File file = new File(path);
            InputStream in = new FileInputStream(file);
            ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1){
                b = in.read(buffer);
                out.write(buffer,0,b);
            }
            in.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            logger.error(e);
        }

    }

    /**
     * 导入课表
     */
    public void importExcel()
    {
        JSONObject json = new JSONObject();
        HttpServletRequest request = ServletActionContext.getRequest();
        String msg = "";

        if (((MultiPartRequestWrapper) request).getFileNames("file") != null) {
            File myFile = ((MultiPartRequestWrapper) request).getFiles("file")[0];
            if (settingType == Curriculum.CUR_WEEK_TYPE) {
                msg = settingsService.importExcel_week(myFile);
            } else if (settingType == Curriculum.CUT_DATE_TYPE) {
                msg = settingsService.importExcel_term(myFile);
            }
            if (msg == "") {
                json.put("success", "true");
                json.put("msg", "导入成功");
            } else {
                json.put("success", "true");
                json.put("msg", msg);
            }
        } else {
            json.put("success", "false");
            json.put("msg", "导入文件不存在,请下载模板文件!");
        }
        this.writeJSON(json.toString());
    }

    /**
     * 查询课表类型
     * @return
     */
    public String curriculumTypeOpt() {
        try {
            setting = settingsService.getCurriculumType();
//            settingsService.setSetting(setting);
        } catch (Exception e) {
            logger.error("获取课程类型失败");
        }
        return "curriculumTypeOpt";
    }

    /**
     * 切换课表类型清空课表
     */
    public void curriculumDel() {
        JSONObject jsonObject = new JSONObject();
        try {
            settingsService.curriculumDel();
            jsonObject.put("flag", true);
            jsonObject.put("msg", "清空成功");
        } catch (Exception e) {
            logger.error("清空课表失败");
            jsonObject.put("flag", false);
            jsonObject.put("msg", "清空失败");
        }
        writeJSON(jsonObject.toString());
    }

    /**
     * 清空课表
     */
    public void curriculumDelete() {
        JSONObject jsonObject = new JSONObject();
        try {
            List curriculums = settingsService.getCurriculum();
            if (curriculums != null && curriculums.size() != 0) {
                //settingsService.curriculumDel();
                settingsService.curriculumDelte();
                jsonObject.put("flag", true);
                jsonObject.put("msg", "清空成功");
            } else {
                logger.error("课表数据空");
                jsonObject.put("flag", false);
                jsonObject.put("msg", "课表数据空");
            }
        } catch (Exception e) {
            logger.error("清空课表失败");
            jsonObject.put("flag", false);
            jsonObject.put("msg", "清空失败");
        }
        writeJSON(jsonObject.toString());
    }



    /**
     * 删除课表
     */
    public void delCurriculum() {
        JSONObject jsonObject = new JSONObject();
        try {
            if(settingsService.delCurriculum(curriculumId)) {
                jsonObject.put("flag", true);
                jsonObject.put("msg", "删除成功");
            }
            else
            {
                jsonObject.put("flag", false);
                jsonObject.put("msg", "删除失败");
            }
        } catch (Exception e) {
            logger.error("删除失败");
            jsonObject.put("flag", false);
            jsonObject.put("msg", "删除失败");
        }
        writeJSON(jsonObject.toString());
    }

    /**
     * 初始化周/总课表内容(课表管理入口)
     */
    public String curriculumImport() throws Exception{
        intToUpper = classtimeService.intToUpper();
        sectionList = classtimeService.getSectionList();
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer uid = user.getUserId();
        if (user.getUser_salt().equals("true")) {
            uid = 0;
        }
        //cancel by xinye
        //timeplanService.hostlist()中有过滤步骤，只允许2，5，16（dspec）通过
//        hostlist = timeplanService.hostlist(uid);//主机列表
        //cancle by xinye end
        //add by xinye
        hostlist = hostgroupService.hostList(uid);//主机列表
        //add by xinye end
        List<Curriculum> curriculums = new ArrayList<Curriculum>();
        setting = settingsService.getSetting();
        int curriculumType = Curriculum.CUR_WEEK_TYPE;
        if(setting != null && setting.getCurriculumType() != null )
        {
            curriculumType = setting.getCurriculumType();
        }
        Calendar calendar = Calendar.getInstance();
        week_id = Byte.parseByte(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        if (week_id == 0) {
            week_id = 7;
        }
        date  = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        for (int i=0; i<hostlist.size(); i++) {
            Object[] objs = hostlist.get(i);
            int hostid = Integer.parseInt(objs[0].toString());
            if (curriculumType == Curriculum.CUR_WEEK_TYPE ) {
                curriculums = settingsService.getCurriculumInfo(hostid, week_id);
            } else{
                curriculums = settingsService.getCurriculumInfo(hostid, date);
            }
            curriculumMaps.put(hostid, curriculums);
            List<Object[]> classtimeList = classtimeService.getClasstimList(hostid,week_id);
            classtimeMaps.put(hostid,classtimeList);
        }
        if (curriculumMaps.size() == 0) {
            return "curriculum";
        } else {
            return "curriculumImport";
        }
    }

    /**
     * 获取周/总课表显示内容
     * @return
     * @throws Exception
     */
    public String getCurWeek() {
        try {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer uid = user.getUserId();
            if (user.getUser_salt().equals("true")) {
                uid = 0;
            }
            hostlist = timeplanService.hostlist(uid);//主机列表
            List<Curriculum> curriculums = new ArrayList<Curriculum>();
            setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null )
            {
                curriculumType = setting.getCurriculumType();
            }
            for (int i = 0; i < hostlist.size(); i++) {
                Object[] objs = hostlist.get(i);
                int hostid = Integer.parseInt(objs[0].toString());
                if (curriculumType == Curriculum.CUR_WEEK_TYPE ) {
                    curriculums = settingsService.getCurriculumInfo(hostid, week_id);
                } else {
                    curriculums = settingsService.getCurriculumInfo(hostid, date);
                    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
                    Date _date = sdf.parse(date);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(_date);
                    week_id = Byte.parseByte(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1));
                    if (week_id == 0) {
                        week_id = 7;
                    }
                }
                curriculumMaps.put(hostid, curriculums);
                List<Object[]> classtimeList = classtimeService.getClasstimList(hostid,week_id);
                classtimeMaps.put(hostid,classtimeList);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return "curriculumInfoList";
    }


    /**
     * 更新课表
     */
    public void updateCurriculum() {
        JSONObject jsonObject = new JSONObject();
        try {
            boolean success = false;
            int settingType = Curriculum.CUR_WEEK_TYPE;
            Setting setting = settingsService.getSetting();
            if(setting != null && setting.getCurriculumType() != null)
            {
                settingType = setting.getCurriculumType();
            }
            if (settingType == Curriculum.CUT_DATE_TYPE) {
                // 更新总课表
                success = settingsService.updateCurriculum(hostid, curriculumName, teacherName, curSection, unit, date);
            } else if (settingType == Curriculum.CUR_WEEK_TYPE){
                // 更新周课表
                success = settingsService.updateCurriculum(hostid, curriculumName, teacherName, curSection, unit, week_id);
            }
            if(success) {
                addplan();// 向录播主机下发录像计划
                // 返回json格式字符串
                jsonObject.put("flag", true);
                jsonMap.put("teacherName", teacherName);
                jsonMap.put("curriculumName", curriculumName);
                jsonMap.put("unit", unit);
                jsonObject.put("data", jsonMap);
            }
            else
            {
                jsonObject.put("flag", false);
                jsonMap.put("msg", "设置失败");
                jsonMap.put("teacherName", "");
                jsonMap.put("curriculumName", "");
                jsonMap.put("unit", "");
                jsonObject.put("data", jsonMap);
            }
        } catch (Exception e) {
            jsonObject.put("flag", false);
            jsonMap.put("msg", "设置失败");
            jsonMap.put("teacherName", "");
            jsonMap.put("curriculumName", "");
            jsonMap.put("unit", "");
            jsonObject.put("data", jsonMap);
            logger.error(e);
        }
        writeJson(jsonObject);
    }

    /**
     * 新增课表
     */
    public void saveCurriculum() {
        JSONObject jsonObject = new JSONObject();
        try {
            int curriculumId = 0;
            int settingType = Curriculum.CUR_WEEK_TYPE;
            Setting setting = settingsService.getSetting();
            if(setting != null && setting.getCurriculumType() != null)
            {
                settingType = setting.getCurriculumType();
            }
            if (settingType == Curriculum.CUT_DATE_TYPE) {
                Curriculum curriculum = settingsService.getCurriculumInfo(hostid,curSection,date);
                if(curriculum != null)
                {
                    curriculumId = curriculum.getCurId();
                }
                else {
                    curriculumId = settingsService.saveCurriculum(hostid, teacherName, curriculumName, curSection, unit, date);
                }
            } else if (settingType == Curriculum.CUR_WEEK_TYPE){
                Curriculum curriculum = settingsService.getCurriculumInfo(hostid,curSection,week_id);
                if(curriculum != null)
                {
                    curriculumId = curriculum.getCurId();
                }
                else {
                    curriculumId = settingsService.saveCurriculum(hostid, teacherName, curriculumName, curSection, unit, week_id);
                }
            }
            // 返回json格式字符串
            if(curriculumId != 0) {
                jsonObject.put("flag", true);
                jsonMap.put("curriculumId", curriculumId);
                jsonMap.put("teacherName", teacherName);
                jsonMap.put("curriculumName", curriculumName);
                jsonMap.put("unit", unit);
                jsonMap.put("msg", "新增成功");
                jsonObject.put("data", jsonMap);
            }
            else
            {
                jsonObject.put("flag", false);
                jsonMap.put("msg", "新增失败");
                jsonMap.put("curriculumId", "");
                jsonMap.put("teacherName", "");
                jsonMap.put("curriculumName", "");
                jsonMap.put("unit", "");
                jsonObject.put("data", jsonMap);
            }
        } catch (Exception e) {
            jsonObject.put("flag", false);
            jsonMap.put("msg", "新增失败");
            jsonMap.put("curriculumId", "");
            jsonMap.put("teacherName", "");
            jsonMap.put("curriculumName", "");
            jsonMap.put("unit", "");
            jsonObject.put("data", jsonMap);
            logger.error(e);
        }
        writeJson(jsonObject);
    }

    /**
     * 向录播主机下发录像计划
     */
    public void addplan() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            boolean isSetNas = settingsService.hasSettingNas(hostid);
            Map<String, Object> host = timeplanService.getHost(hostid);
            //add by xinye
            //只要不是HL-ZF0400的设备都放行
            isSetNas = host.get("dspec_id").equals("5")?isSetNas:true;
            //add by xinye end
            if (host != null && !host.isEmpty()) {
                String ip = host.get("host_ipaddr").toString();
                if (isSetNas || host.get("host_desc").equals("1")) {
                    boolean isOnline = nvr.isOnline(ip);
                    if (!isOnline) {
                        jsonMap.put("msg", "设备离线");
                    } else {
                        //查询录像计划
                        Integer timeplan_id = 0;
                        if (settingType == Curriculum.CUR_WEEK_TYPE) {
                            timeplan_id = timeplanService.getTimeplan(hostid, curSection, week_id);
                        } else if (settingType == Curriculum.CUT_DATE_TYPE) {
                            if (date.endsWith(".0")) {
                                date = date.substring(0, date.indexOf('.'));
                            }
                            timeplan_id = timeplanService.getTimeplan(hostid, curSection, date);
                        }
                        // 获取当前日期, 如果date小于当前日期, 则不下发录像计划 调试
                        String curDate = sdf.format(new Date());
                        date = date.substring(0, 10);
                        int res = settingsService.compare_date2(date, curDate);
                        if (timeplan_id > 0 && res >= 0) {
                            Date d = sdf.parse(date);
                            List<Object[]> timePlanList = timeplanService.getTimeplanByHost(hostid, d);
//                            List<Map<String, Object>> recordTasks = new ArrayList<>();
//                            for(int i=0;i<timePlanList.size();i++)
//                            {
//                                Object[] timePlan = timePlanList.get(i);
//                                Map<String, Object> params = new HashMap<>();
//                                params.put("hostId", String.valueOf(hostid));
//                                params.put("timeplan_id", timePlan[0]);
//                                params.put("ext", "");
//                                params.put("week_id", timePlan[1]);
//                                params.put("startTime", timePlan[4]);
//                                params.put("stopTime", timePlan[5]);
//                                params.put("subjectName",timePlan[6]);
//                                params.put("teacher",timePlan[7]);
//                                recordTasks.add(params);
//                            }
//                            int flag = nvrCommand.addPlanCommand(ip, recordTasks);
                            int flag = timeplanService.setTimePlan(ip,timePlanList,hostid,nvrCommand);
                            if (flag == 1) {
                                jsonMap.put("id", timeplan_id.toString());
                                jsonMap.put("ip", ip);
                                jsonMap.put("msg", "课表已保存并向录播主机同步成功");
                            } else {
                                jsonMap.put("msg", "课表已保存，向录播主机同步失败");
                            }
                        }
                        else
                        {
                            jsonMap.put("msg", "课表已保存");
                        }
                    }
                    } else {
                    jsonMap.put("msg", "课表已保存，向录播主机同步失败");
                }
            } else {
                jsonMap.put("msg", "课表已保存，向录播主机同步失败");
            }
        } catch (Exception e) {
            logger.error("", e);
            jsonMap.put("msg", "课表已保存，向录播主机同步失败");
        }
    }

    // 时间策略入口
    /**
     * 获取所有的组所对的时间策略
     */
    public String timeploy() {
        // {"小学部作息策略", "小学部"}
        ployGroups = classtimeService.getAllClasstimeployGroup();
        classtimePloys = classtimeService.getAllTimePloy();

        return "timeploy";
    }

    /**
     * 添加时间策略
     */
    public void addTimePloy() {
        JSONObject jsonObject = new JSONObject();
        try {
            //
            boolean flag = classtimeService.getTimePloy(timePloy);
            if (!flag) {
                // 1.添加时间策略
                classtimeService.saveTimePloy(timePloy);
                // 2.查询添加策略的id,并添加节次
                Integer timePloyId = classtimeService.getTimePloyId(timePloy);

                if (timePloyId > 0) {
                    String[] startTimes = ServletActionContext.getRequest().getParameterValues("starttime");
                    String[] startTimes_1 = ServletActionContext.getRequest().getParameterValues("starttime_1");

                    String[] endTimes = ServletActionContext.getRequest().getParameterValues("endtime");
                    String[] endTimes_1 = ServletActionContext.getRequest().getParameterValues("endtime_1");

                    sectionlist= new ArrayList<String[]>();
                    String[] sections1 = ServletActionContext.getRequest().getParameterValues("section_1");
                    sectionlist.add(sections1);
                    String[] sections2 = ServletActionContext.getRequest().getParameterValues("section_2");
                    sectionlist.add(sections2);
                    String[] sections3 = ServletActionContext.getRequest().getParameterValues("section_3");
                    sectionlist.add(sections3);
                    String[] sections4 = ServletActionContext.getRequest().getParameterValues("section_4");
                    sectionlist.add(sections4);
                    String[] sections5 = ServletActionContext.getRequest().getParameterValues("section_5");
                    sectionlist.add(sections5);
                    String[] sections6 = ServletActionContext.getRequest().getParameterValues("section_6");
                    sectionlist.add(sections6);
                    String[] sections7 = ServletActionContext.getRequest().getParameterValues("section_7");
                            sectionlist.add(sections7);

                    for (int i = 0; i < sectionlist.size(); i++) {
                        classtimeService.addClasstime(i+1, sectionlist, startTimes, startTimes_1, endTimes, endTimes_1, timePloyId);
                    }
                }


                //List groupIds = classtimeService.getGroupIds();

                jsonObject.put("flag", true);
                jsonObject.put("msg", "添加成功");
                jsonObject.put("data", timePloy);
                jsonObject.put("ployId", timePloyId);
                //jsonObject.put("groupIds", groupIds);

            } else {
                jsonObject.put("flag", false);
                jsonObject.put("msg", "作息策略已经存在,请重新添加新的作息策略名称!");
            }
            //jsonObject.put("")
        } catch (Exception e){
            jsonObject.put("flag", false);
            jsonObject.put("msg", "添加失败");
            jsonObject.put("data", "");
            jsonObject.put("ployId", null);
            //jsonObject.put("groupIds", null);
        }

        writeJson(jsonObject);
    }

    /**
     * 删除时间策略
     */
    public void delTimePloy() {
        String[] timePloys = ServletActionContext.getRequest().getParameterValues("timeploys");
        JSONObject jsonObject = new JSONObject();
        String[] timePloyStrs = timePloys[0].split(",");

        try {
            for (int i = 0; i < timePloyStrs.length; i++) {
                classtimeService.deleteTimePloy(timePloyStrs[i]);
            }
            jsonObject.put("flag", true);
            jsonObject.put("msg", "删除成功");

        } catch (Exception e) {
            jsonObject.put("flag", false);
            jsonObject.put("msg", "删除失败");
        }

        writeJson(jsonObject);
    }

    /**
     * 更新班组和策略关系
     */
    public void updateGroupIdAndPloyId() {
        try {
            classtimeService.updateGroupIdAndPloyId(groupId, ployId);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * 获取所有的组,策略已经所对应的课程信息
     * @return
     */
    public void getAllClassTime() {
        JSONObject jsonObject = new JSONObject();

        try {
            Integer ployId = classtimeService.getClassTimePloyId(timePloy);
            List<Object[]> dataList = classtimeService.getAllClassTime(ployId);
            jsonObject.put("flag", true);
            jsonObject.put("data", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            jsonObject.put("flag", false);
            jsonObject.put("data", null);
        }

        writeJson(jsonObject);
    }

    /**
     * 修改时间策略
     */
    public void updateTimePloy() {
        JSONObject jsonObject = new JSONObject();

        try {
            String[] startTimes = ServletActionContext.getRequest().getParameterValues("starttime");
            String[] startTimes_1 = ServletActionContext.getRequest().getParameterValues("starttime_1");

            String[] endTimes = ServletActionContext.getRequest().getParameterValues("endtime");
            String[] endTimes_1 = ServletActionContext.getRequest().getParameterValues("endtime_1");

            sectionlist = new ArrayList<String[]>();
            String[] sections1 = ServletActionContext.getRequest().getParameterValues("section_1");
            sectionlist.add(sections1);
            String[] sections2 = ServletActionContext.getRequest().getParameterValues("section_2");
            sectionlist.add(sections2);
            String[] sections3 = ServletActionContext.getRequest().getParameterValues("section_3");
            sectionlist.add(sections3);
            String[] sections4 = ServletActionContext.getRequest().getParameterValues("section_4");
            sectionlist.add(sections4);
            String[] sections5 = ServletActionContext.getRequest().getParameterValues("section_5");
            sectionlist.add(sections5);
            String[] sections6 = ServletActionContext.getRequest().getParameterValues("section_6");
            sectionlist.add(sections6);
            String[] sections7 = ServletActionContext.getRequest().getParameterValues("section_7");
            sectionlist.add(sections7);
            classtimeService.deleteClassTime(classtimePloy);

            for (int i = 0; i < sectionlist.size(); i++) {
                classtimeService.updateClasstime(i + 1, sectionlist, startTimes, startTimes_1, endTimes, endTimes_1, classtimePloy);
            }

            jsonObject.put("flag", true);
            jsonObject.put("msg", "修改成功");
        } catch (Exception e) {
            jsonObject.put("flag", false);
            jsonObject.put("msg", "修改失败");
        }

        writeJson(jsonObject);
    }

    /**
     * 获取数据库中记录的命名模式
     * Author xinye
     * @return
     */
    public String recordName(){
        try{
            recordNameSetting = recordNameSettingService.getSetting();
        }catch(Exception e){
            logger.error("",e);
        }
        return "recordName";
    }

    /**
     * 保存录像的文件命名模式
     * Author xinye
     */
    public void saveRecordNameSetting(){
        JSONObject jsonResult = new JSONObject();
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            boolean subject = Boolean.parseBoolean(request.getParameter("subject"));
            boolean teacher = Boolean.parseBoolean(request.getParameter("teacher"));
            boolean classroom = Boolean.parseBoolean(request.getParameter("classroom"));
            if(recordNameSettingService.setRecordNameSetting(subject,teacher,classroom)){
                List<Object[]> ipList = nvr.getOnlineClasses();
                StringBuffer com = new StringBuffer("%d_%t_");
                com.append(classroom?"%c_":"");
                com.append(subject?"%s_":"");
                com.append(teacher?"%l_":"");
                com.append("%r_%e");
                for(Object[] obj:ipList){
                    nvrCommand.setRecordInfo((String)obj[0],com.toString());
                }
                jsonResult.put("status",0);
            }else{jsonResult.put("status",1);}
        }catch(Exception e){
            logger.error("",e);
            jsonResult.put("status",1);
        }
        writeJSON(jsonResult.toString());
    }
}
