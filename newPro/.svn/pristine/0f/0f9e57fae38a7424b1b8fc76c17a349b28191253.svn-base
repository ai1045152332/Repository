package com.honghe.recordweb.service.frontend.settings;

import com.honghe.recordhibernate.dao.*;
import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.*;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.HttpServiceClientFactory;
import com.honghe.recordweb.util.LicenseUtils;
import com.honghe.service.client.Result;
import com.honghe.service.client.http.HttpServiceClient;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by zhanghongbin on 2014/11/11.
 */
@Service
public class SettingsService {
    private final static Logger logger = Logger.getLogger(SettingsService.class);
    private Setting setting;
    private List<Object[]> nasList;
    private List<Logo> logoList;
    private List<Object[]> deviceConfigList;
    private static Map<String, JSONObject> courseInfoCache = new HashMap<>();
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private WhiteboardCommand whiteboardCommand;
    @Resource
    private ClasstimeDao classtimeDao;
    @Resource
    private SettingDao settingsDao;
    @Resource
    private LogoDao logoDao;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private NVR nvr;
    @Resource
    private NasDao nasDao;
    @Resource
    private DeviceConfigDao deviceConfigDao;
    @Resource
    private DeviceDao deviceDao;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private ClasstimeService classtimeService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private CurriculumDao curriculumDao;
    @Resource
    private InitializationDao initializationDao;
    @Resource
    private HostgroupDao hostgroupDao;
    private List<Map<String, String>> nasConfigs;

    public Map<Integer, String[]> getNas2hostMap() {
        return nas2hostMap;
    }

    public void setNas2hostMap(Map<Integer, String[]> nas2hostMap) {
        this.nas2hostMap = nas2hostMap;
    }

    private Map<Integer, String[]> nas2hostMap = new ConcurrentHashMap<Integer, String[]>();

    public Map<String, JSONObject> getCourseInfoCache() {
        return courseInfoCache;
    }

    public void setCourseInfoCache(Map<String, JSONObject> courseInfoCache) {
        this.courseInfoCache = courseInfoCache;
    }

    public List<Map<String, String>> getNasConfigs() {
        return nasConfigs;
    }

    public void setNasConfigs(List<Map<String, String>> nasConfigs) {
        this.nasConfigs = nasConfigs;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public List<Object[]> getNasList() {
        return nasList;
    }

    public void setNasList(List<Object[]> nasList) {
        this.nasList = nasList;
    }

    public List<Logo> getLogoList() {
        return logoList;
    }

    public void setLogoList(List<Logo> logoList) {
        this.logoList = logoList;
    }

    public List<Object[]> getDeviceConfigList() {
        return deviceConfigList;
    }

    public void setDeviceConfigList(List<Object[]> deviceConfigList) {
        this.deviceConfigList = deviceConfigList;
    }
    /**
     * todo 加注释
     */
    @PostConstruct
    public void init() {
        try {
            //add by xinye
            //添加了对TBOX自定义name的数据更新(10-25取消从配置文件读入)
           /* String name_eleven = new String(ConfigUtil.get("tboxName_eleven").getBytes("ISO8859_1"), "utf-8");
            String name_twelve = new String(ConfigUtil.get("tboxName_twelve").getBytes("ISO8859_1"), "utf-8");
            deviceConfigDao.initDeviceConfig(name_eleven,"11");
            deviceConfigDao.initDeviceConfig(name_twelve,"12");
            deviceDao.updateTBOXDeviceData(name_eleven,"11");
            deviceDao.updateTBOXDeviceData(name_twelve,"12");*/
            //add by xinye end
            setting = settingsDao.getSetting();
            initNas();
            logoList = logoDao.getLogoList();
            deviceConfigList = deviceConfigDao.getDeviceConfigList();
            nasConfigs = getNasConfig();

        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 获取NAS设置
     * @return
     */
    public List<Map<String, String>> getNasConfig() {
        HttpServiceClient httpServiceClient = HttpServiceClientFactory.getHttpServiceClient();
        Map<String,String> params = new HashMap<>();
         Result result = httpServiceClient.execute("storage","findNasConfig",params);
        if(result.getCode()==0)
        {
            Object obj = result.getValue();
            if (obj != null) {
                //Map<String, String> results = (Map)obj;
                List<Map<String, String>> results = (List<Map<String, String>>)obj;
                return results;
            }

            return Collections.EMPTY_LIST;
        }

        return Collections.EMPTY_LIST;
    }

    /**
     * update by zlj on 2018/04/12
     * 根据id获取host
     * @param id
     * @return
     */
    public Map<String, Object>  getHost(int id) {
        Map<String, Object> re_value = null;
        try {
            re_value = hongheDeviceService.getHostInfoById(String.valueOf(id));
        } catch (Exception e) {
            logger.error("根据id获取host异常 id=" + id, e);
        }
        return re_value;
    }

    /**
     * 初始化nas
     * @throws Exception
     */
    public void initNas() throws Exception {
        nasList = nasDao.getNasList();
        Map<Integer, String[]> tmp = new ConcurrentHashMap<Integer, String[]>();
        List<Object[]> nas2hostList = nasDao.getNas2HostList();
        for (Object[] obj : nas2hostList) {
            tmp.put(Integer.parseInt(obj[0].toString()), new String[]{obj[1].toString()});
        }
        nas2hostMap = tmp;
    }

    /**
     * 根据hostid查询课表信息
     * @param hostid
     * @return
     * @throws Exception
     */
    public Curriculum findCulum(String hostid) throws Exception {
        int week = getWeekOfDate(new Date());
        List<Object[]> classtimes = new ArrayList<>();
//        List<Object[]> groupList = hostgroupDao.getGroupInfoByhostId(Integer.parseInt(hostid));
        //add by lichong
        List<Object[]> groupList =classtimeService.getGroupInfoByHostId(Integer.parseInt(hostid));
        if(groupList == null || groupList.isEmpty())
        {
            classtimes = classtimeDao.getClasstimeListNoGroup(week);
        }
        else {
//            classtimes = classtimeDao.getClasstimeList(Integer.parseInt(hostid), week);
            //add by lichong
            int groupId = Integer.valueOf(groupList.get(0)[0].toString());
            classtimes = classtimeDao.getClasstimeListByGroup(week, groupId);
        }
        int sectionid = -1;
        Calendar calendar = Calendar.getInstance();
        for (Object[] classtime : classtimes) {
            String classtimeStart = classtime[3]==null?"":classtime[3].toString();
            String classtimeEnd = classtime[4]==null?"":classtime[4].toString();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            // 获取当前系统时间所对应的节次
            if((compare_date(hour+":"+min, classtimeStart)==1 || compare_date(hour+":"+min, classtimeStart) == 0) && compare_date(classtimeEnd, hour+":"+min)==1){
                sectionid = Integer.parseInt(classtime[2]==null?"-1":classtime[2].toString());
                break;
            }
        }
        Setting setting = this.getSetting();
        int curriculumType = Curriculum.CUR_WEEK_TYPE;
        if (setting != null && setting.getCurriculumType() != null) {
            curriculumType = setting.getCurriculumType();
        }

        Curriculum curriculum = new Curriculum();
        // 周课表
        if(curriculumType == Curriculum.CUR_WEEK_TYPE) {
            curriculum = curriculumDao.getCurriculum_week(week, sectionid, Integer.parseInt(hostid));
        } else if(curriculumType == Curriculum.CUT_DATE_TYPE) {// 总课表
            SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
            String dateStr = df.format(calendar.getTime());
            Date date = df.parse(dateStr);
            curriculum = curriculumDao.getCurriculum_term(date, sectionid, Integer.parseInt(hostid)) ;
        }
        return curriculum;
    }

    /**
     * 比较时间方法1
     * @param date1 todo 加注释
     * @param date2 todo 加注释
     * @return
     */
    public static int compare_date(String date1, String date2) {
            SimpleDateFormat df = new SimpleDateFormat("hh:mm");

            try {
                int hour1 = Integer.parseInt(date1.split(":")[0]);
                int min1 = Integer.parseInt(date1.split(":")[1]);
                int hour2 = Integer.parseInt(date2.split(":")[0]);
                int min2 = Integer.parseInt(date2.split(":")[1]);
                if(hour1 > hour2)
                {
                    return 1;
                }
                else if(hour1 == hour2)
                {
                    if(min1 > min2)
                    {
                        return 1;
                    }
                    else if(min1 == min2)
                    {
                        return 0;
                    }
                    else
                    {
                        return -1;
                    }
                }
                else
                {
                    return -1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
    }

    /**
     * 比较时间方法2
     * @param DATE1 todo 加注释
     * @param DATE2 todo 加注释
     * @return
     */
    public static int compare_date2(String DATE1, String DATE2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * update by zlj on 2018/04/12
     * todo 加注释
     * @param isNullSetting
     * @param setting
     * @param recordinfoResolution
     * @param recordinfoBitRate
     * @return
     */
    @Transactional
    public boolean saveSettingService(boolean isNullSetting, Setting setting, final Integer recordinfoResolution, final Integer recordinfoBitRate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ResolutionValue resolutionValue = Resolution.getResolutionValue(recordinfoResolution);
                List<Object[]> onlineClasses = nvr.getOnlineClasses();
                for (int i = 0; i < onlineClasses.size(); i++) {
                    String ip = onlineClasses.get(i)[0].toString();
                    String hostId = hongheDeviceService.getHostInfoByIp(ip).get("host_id").toString();
                    /*nvrCommand.setResolution(ip, hostId, resolutionValue.getX(), resolutionValue.getY(), defaultResponseHandler);
                    nvrCommand.setBitrate(ip, hostId, recordinfoBitRate, defaultResponseHandler);*/
//                    nvrCommand.setResolution(ip, hostId, resolutionValue.getX(), resolutionValue.getY());
//                    nvrCommand.setBitrate(ip, hostId, recordinfoBitRate);
                    nvrCommand.setVideoInfo(ip, hostId, resolutionValue.getX(), resolutionValue.getY(), recordinfoBitRate);
                }
            }
        }).start();
        setting.setRecordinfoResolution(recordinfoResolution);
        setting.setRecordinfoBitRate(recordinfoBitRate);
        if (isNullSetting) {
            return this.saveSetting(setting);
        } else {
            return this.updateSetting(setting);
        }
    }

    /**
     * todo 加注释
     * @param setting
     * @return
     */
    @Transactional
    public boolean saveSetting(Setting setting) {
        try {
            settingsDao.saveSetting(setting);
        } catch (Exception e) {
            logger.error("保存设置异常", e);
            return false;
        }
        return true;
    }

    /**
     * todo 加注释
     * @param setting
     * @return
     */
    @Transactional
    public boolean updateSetting(Setting setting) {
        try {
            settingsDao.updateSetting(setting);
        } catch (Exception e) {
            logger.error("更新设置异常", e);
            return false;
        }
        return true;
    }


    /**
     * 获取设备通道信息
     * @param type
     * @return
     */
    @Transactional
    public List getChannelName(String type){
        List result = null;
        try {
            if (type=="hhtc"){
                result = computerCommand.getInitInfo(CommonName.DEVICE_TYPE_SCREEN);
            }else if (type == "hhtwb"){
                result = whiteboardCommand.getInitInfo(CommonName.DEVICE_TYPE_WHITEBOARD);
            }
//            result = initializationDao.getChannel(type);
        }catch (Exception e){
            logger.error("设备通道获取失败", e);
        }
        return result;
    }

    /**
     * 更新默认信息数据库
     * @param channelName
     * @param lockStatus
     * @param type
     * @return
     */
    @Transactional
    public boolean updateInitInfo(String channelName,String lockStatus,String type){
        try {
            Initialization  initialization = initializationDao.getInitializationListbytype(type);
            initialization.setChannelName(channelName);
            initialization.setLockStatus(lockStatus);
            initialization.setType(type);
            boolean flag = initializationDao.updateInitializationInfo(initialization);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取设备的最初始默认信息
     * @param type
     * @return
     */
    public Initialization getinitInfo(String type){
        Initialization initinfo = null;

        try {
           initinfo = initializationDao.getInitializationListbytype(type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return initinfo;
    }
    /**
     * 更新台标
     *
     * @param logo Logo
     * @return boolean
     */
    @Transactional
    public boolean updateLogo(Logo logo) {
        try {
            logoDao.updateLogo(logo);
            return true;
        } catch (Exception e) {
            logger.error("更新台标异常", e);
            return false;
        }
    }

    /**
     * 保存台标
     * @param logo
     * @return
     */
    @Transactional
    public boolean saveLogo(Logo logo) {
        try {
            logoDao.saveLogo(logo);
            return true;
        } catch (Exception e) {
            logger.error("保存台标异常", e);
            return false;
        }
    }

    /**
     * 保存nas
     * @param nas
     * @return
     */
    @Transactional
    public Integer saveNas(Nas nas) {
        try {
            return nasDao.saveNas(nas);
        } catch (Exception e) {
            logger.error("保存nas异常", e);
            return -1;
        }
    }

    /**
     * 更新nas
     * @param nas
     * @return
     */
    @Transactional
    public boolean updateNas(Nas nas) {
        try {
            nasDao.updateNas(nas);
            return true;
        } catch (Exception e) {
            logger.error("更新nas异常", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param nasId
     * @param nasRootPath
     * @return
     */
    public boolean hasNasPath(int nasId, String nasRootPath) {
        for (Object[] objects : this.nasList) {
            if (nasRootPath.toLowerCase().equals(objects[1].toString().toLowerCase())) {
                if (Integer.parseInt(objects[0].toString()) == nasId) {
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除nas
     * @param nasId
     * @return
     */
    @Transactional
    public boolean deleteNas(String nasId) {
        try {
            for (Object[] objects : this.nasList) {
                if (Integer.parseInt(objects[4].toString()) != 0 && objects[0].toString().equals(nasId)) {
                    nasDao.deleteFromNas(nasId);
                    break;
                }
            }
            nasDao.deleteNas(nasId);
            initNas();
            return true;
        } catch (Exception e) {
            logger.error("删除nas异常", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param hostId
     * @return
     */
    public boolean hasSettingNas(int hostId) {
        return this.nas2hostMap.containsKey(hostId);
    }

    /**
     * todo 加注释
     * @return
     */
    public List<Map> getNoNas2HostList() {
        List<Map> mapList = Collections.emptyList();
        try {
            List<Object[]> result = nasDao.getNas2HostList();
            String hostIds = "";
            if (!result.isEmpty()) {
                for (int i = 0; i < result.size(); i++) {
                    hostIds += String.valueOf(result.get(i)[0]) + ",";
                }
                hostIds = hostIds.substring(0, hostIds.length() - 1);
            }
            if (hostIds == "null") {
                hostIds = "";
            }
            String type = CommonName.DEVICE_TYPE_RECOURD;
            mapList = hostgroupService.getNotHostInfo(type, hostIds);
        } catch (Exception e) {
            logger.error("", e);
        }
        return mapList;
    }

    /**
     * todo 加注释
     * @param currentNasHosts
     * @param hostId
     * @param nasId
     * @return
     */
    @Transactional
    public boolean deleteNas2Host(List<String> currentNasHosts, String[] hostId, Integer nasId) {
        // boolean isDeleteFormNas = false;
//        for (String _hostId : hostId) {
//            if (this.nas2hostMap.containsKey(Integer.parseInt(_hostId))) {
//                isDeleteFormNas = true;
//                break;
//            }
//        }
        try {
            if (!currentNasHosts.isEmpty()) {
                this.nasDao.deleteFromNas(String.valueOf(nasId));
            }
            this.nasDao.saveNas2Host(hostId, nasId);
            this.initNas();
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除设备要删除跟nas关联的设备
     *
     * @param hostId
     * @return
     */
    @Transactional
    public boolean deleteNas2Host(String hostId) {
        try {
            nasDao.deleteFromHost(hostId);
            return true;
        } catch (Exception e) {
            logger.error("删除设备要删除跟nas关联的设备异常，hostId=" + hostId, e);
            return false;
        }
    }

    /**
     * 依据主机Id获取设备内置型号
     *update by zlj on 2018/04/12
     * @param hostId
     * @return
     */
    public String getConnect_param(String hostId) {
        Map map = hongheDeviceService.getHostInfoById(hostId);
        String str_dspec_id = String.valueOf(map.get("dspec_id"));
        Integer dspec_id = str_dspec_id != null ? Integer.valueOf(str_dspec_id) : 0;
        List<Dspecification> dspecifications = hostgroupService.getSpecList();
        String connect_param = "";
        for (Dspecification dspecification : dspecifications) {
            if (dspecification.getDspecId() == dspec_id) {
                connect_param = dspecification.getConnectParam();
            }
        }
        return connect_param;
    }

    /**
     * 依据主机Ip获取设备内置型号
     * update by zlj on 2018/04/12
     * @param hostIp
     * @return
     */
    public String getConnect_paramByIp(String hostIp) {
        Map map = hongheDeviceService.getHostInfoByIp(hostIp);
        Object str_dspec_id = map.get("dspec_id");
        Integer dspec_id = str_dspec_id != null ? Integer.valueOf(str_dspec_id+"") : 0;
        List<Dspecification> dspecifications = hostgroupService.getSpecList();
        String connect_param = "";
        for (Dspecification dspecification : dspecifications) {
            if (dspecification.getDspecId() == dspec_id) {
                connect_param = dspecification.getConnectParam();
                break;
            }
        }
        return connect_param;
    }
    /**
     * 根据班级、上课节次生成周课表
     *
     * @return
     */
    public void creatExcelTempletService() {
        try {
            final String path = ServletActionContext.getServletContext().getRealPath("/export");
            OutputStream out = new FileOutputStream(path + "\\周课表模版.xls");
            String[] headers = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
            HSSFWorkbook workbook = new HSSFWorkbook();

            POIExcelUtil poiExcelUtil = new POIExcelUtil();
            Map hostMap = hostDevice.getAllHostInfo(CommonName.DEVICE_TYPE_RECOURD);
            List hostList = (List) hostMap.get("hostInfo");
            for (int i = 0; i < hostList.size(); i++) {
                Map host = (Map) hostList.get(i);
                String hostName = host.get("name").toString();
                String hostId = host.get("id").toString();
                List<List<String>> data = new ArrayList<List<String>>();
                Map<Integer, String> intToUpper = classtimeService.intToUpper();
                List sectionList = classtimeService.getSectionListByHost(Integer.parseInt(hostId));;
                for (int k = 1; k <= sectionList.size(); k++) //共多少节课
                {
                    List rowData = new ArrayList();
                    rowData.add("第" + intToUpper.get(k) + "节");
                    for (int j = 0; j < 21; j++) {
                        rowData.add("");
                    }
                    data.add(rowData);
                }
                poiExcelUtil.exportExcel(workbook, i, hostName+"("+hostId+")", headers, data, out);
            }
            //原理就是将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add by lichong
     * 格式化sheet，将整行数据为空的row删除，提高效率
     * @param sheet
     * @return
     */
    private Sheet formatSheet(Sheet sheet){
        CellReference cellReference = new CellReference("A4");
        boolean flag = false;
        for (int i = cellReference.getRow(); i <= sheet.getLastRowNum();) {
            Row r = sheet.getRow(i);
            if(r == null){
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
                sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);
                continue;
            }
            flag = false;
            for(Cell c:r){
                if(c.getCellType() != Cell.CELL_TYPE_BLANK){
                    flag = true;
                    break;
                }
            }
            if(flag){
                i++;
                continue;
            }
            else{//如果是空白行（即可能没有数据，但是有一定格式）
                if(i == sheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉
                    sheet.removeRow(r);
                else//如果还没到最后一行，则数据往上移一行
                    sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);
            }
        }
        return sheet;
    }
    /**
     * 根据班级、上课节次生成周课表
     *
     * @return
     */
    @Transactional
    public String importExcel_week(File file)
    {
        String msg = "";
        InputStream stream = null;
        try {
            stream = new FileInputStream(file);// 文件流指向excel文件 stream = new FileInputStream("D:\\test.xls");
            Workbook workbook = WorkbookFactory.create(stream);//HSSF只能打开2003，XSSF只能打开2007，WorkbookFactory.create兼容以上两个版本
            //遍历所有以班级名称命名的sheet表
            for(int j=0;j<workbook.getNumberOfSheets();j++) {
                Sheet sheet = this.formatSheet(workbook.getSheetAt(j));// 得到工作表
                Row row;// 对应excel的行
                int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数(注意，从0开始的)
                if(totalRow <= 0)
                {
                    msg += "<br />sheet:'"+sheet.getSheetName()+"'您要导入的文件与周课表模版不一致，请重新下载模版。";
                    continue;
                }
                row = sheet.getRow(0);
                if(row.getLastCellNum()!=22) {
                    msg += "<br />sheet:'"+sheet.getSheetName()+"'您要导入的文件与周课表模版不一致，请重新下载模版。";
                    continue;
                }
                String host = sheet.getSheetName();
                String hostName = "";
                String hostId = "";
                if(host.indexOf("(") != -1) {
                    hostName = host.substring(0, host.lastIndexOf("("));
                    hostId = host.substring(host.lastIndexOf("(") + 1, host.length() - 1);
                }
                else
                {
                    msg += "<br />sheet:'"+sheet.getSheetName()+"'未找到相关教室id信息，请重新下载模版。";
                    continue;
                }

                //逐行写入数据库
                for(int k=1;k<22;k=k+3) {
                    List classtimeList = classtimeService.getClasstimList(Integer.parseInt(hostId), (k + 2) / 3);
                    for (int i=2; i<=totalRow; i++) {
                        row = sheet.getRow(i);
                        if (i - 1 > classtimeList.size()) {
                            if((row.getCell(k) != null && !"".equals(row.getCell(k).toString())) && (row.getCell(k + 1) != null && !"".equals(row.getCell(k + 1).toString())))
                            {
                                msg += "<br />sheet:'" + sheet.getSheetName() + "'，星期"+(k + 2) / 3+",当前节次为:" + (i - 1) +
                                        ",超过设置的上课总节次:" + classtimeList.size();
                            }
                        }
                        else {
                            int sectionId = i - 1;
                            Curriculum curriculum = curriculumDao.getCurriculum_week((k + 2) / 3, sectionId, Integer.parseInt(hostId));
                            if (curriculum == null) {
                                curriculum = new Curriculum();
                            }
                            curriculum.setCurSection(sectionId);
                            curriculum.setCurHost(Integer.parseInt(hostId));
                            curriculum.setCurClass(hostName);
                            curriculum.setCurWeek((k + 2) / 3);
                            if ((row.getCell(k) != null && !"".equals(row.getCell(k).toString())) && (row.getCell(k + 1) != null && !"".equals(row.getCell(k + 1).toString()))) {

                                curriculum.setCurSubject(row.getCell(k).toString());
                                curriculum.setCurTeacher(row.getCell(k + 1).toString());
                                curriculum.setCurUnit(row.getCell(k + 2).toString());
                                if (curriculum.getCurId() != 0) {
                                    if (!curriculumDao.update(curriculum)) {
                                        msg += "<br />sheet:'" + sheet.getSheetName() + "'，第" + i + "行第" + k + "列更新失败。";
                                    }
                                } else {
                                    if (!curriculumDao.save(curriculum)) {
                                        msg += "<br />sheet:'" + sheet.getSheetName() + "'，第" + i + "行第" + k + "列保存失败。";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            msg += "<br />文件未找到。";
        } catch (Exception ex) {
            msg += "<br />未知错误，导入中止。";
        } catch (Throwable throwable){
            msg += "<br />未知错误，导入中止!";
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    msg += "<br />未知错误，导入中止。";
                }
            }
        }
        logger.error(msg);
        return msg;
    }
    /**
     * 根据学期课表
     *
     * @return
     */
    @Transactional
    public String importExcel_term(File file)
    {
        String msg = "";
        InputStream stream = null;
        try {
            // 文件流指向excel文件
//            stream = new FileInputStream("D:\\test.xls");
            stream = new FileInputStream(file);
            //HSSF只能打开2003，XSSF只能打开2007，WorkbookFactory.create兼容以上两个版本
            Workbook workbook = WorkbookFactory.create(stream);
            Sheet sheet = workbook.getSheetAt(0);// 得到工作表
            Row row = null;// 对应excel的行
            int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
            if(totalRow <= 0)
            {
                msg += "<br />您要导入的文件与学期课表模版不一致，请重新下载模版。";
                return msg;
            }
            row = sheet.getRow(0);
            int columnNum = row.getPhysicalNumberOfCells();//得到excel的总列数
            if(row.getLastCellNum()!=columnNum) {
                msg += "<br />您要导入的文件与学期课表模版不一致，请重新下载模版。";
                return msg;
            }
            //逐行写入数据库
            for (int i=1; i<=totalRow; i++) {
                row = sheet.getRow(i);

                if (row.getCell(0) != null && !"".equals(row.getCell(0).toString())
                        && row.getCell(1) != null && !"".equals(row.getCell(1).toString())
                        && row.getCell(2) != null && !"".equals(row.getCell(2).toString())
                        && row.getCell(3) != null && !"".equals(row.getCell(3).toString())
                        && row.getCell(4) != null && !"".equals(row.getCell(4).toString())
                        && row.getCell(5) != null && !"".equals(row.getCell(5).toString()))
                {
                    Date date = new Date();
                    if (DateUtil.isCellDateFormatted(row.getCell(0))) {
                        date = row.getCell(0).getDateCellValue();
                    } else {
                        msg += "<br />第" + i + "行:日期类型不正确。";
                    }
                    String hostName = row.getCell(5).toString();
                    if(hostName.endsWith(".0"))
                    {
                        hostName = hostName.substring(0,hostName.length()-2);
                    }
                    List<Map> hostList = hostDevice.getHostListByNames(CommonName.DEVICE_TYPE_RECOURD,hostName);
                    String hostId = "";
                    if(!hostList.isEmpty())
                    {
                        Map hostMap = hostList.get(0);
                        hostId = hostMap.get("id").toString();
                    }
                    if (("").equals(hostId)) {
                        msg += "<br />第" + i + "行:未找到相关录播教室，请确认上课场地名称与录播教室名称一致。";
                        continue;
                    }
                    List<Object[]> classtimeList = classtimeService.getClasstimList(Integer.parseInt(hostId), getWeekOfDate(date));
                    String classtimeStr = row.getCell(1).toString();
                    if(classtimeStr.endsWith(".0"))
                    {
                        classtimeStr = classtimeStr.substring(0,classtimeStr.length()-2);
                    }
                    if(classtimeStr.endsWith("节"))
                    {
                        classtimeStr = classtimeStr.substring(0,classtimeStr.length()-1);
                    }
                    String[] classtimeArray = classtimeStr.split("-");
                    for(int j=0;j<classtimeArray.length;j++) {
                        int classtime = Integer.parseInt(classtimeArray[j]);
                        if (classtime > classtimeList.size()) {
                            msg += "<br />第" + i + "行:当前节次为:" + classtime + ",超过设置的上课总节次:" + classtimeList.size();
                            continue;
                        }
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = df.format(date);
                        int curSection = classtime;
                        Curriculum curriculum = curriculumDao.getCurriculum_term(df.parse(dateStr), curSection, Integer.parseInt(hostId));
                        if (curriculum == null) {
                            curriculum = new Curriculum();
                        }
                        curriculum.setCurDate(date);
                        curriculum.setCurSection(curSection);
                        curriculum.setCurHost(Integer.parseInt(hostId));
                        curriculum.setCurClass(row.getCell(3).toString());
                        curriculum.setCurWeek(getWeekOfDate(date));
                        curriculum.setCurSubject(row.getCell(2).toString());
                        curriculum.setCurTeacher(row.getCell(4).toString());
                        curriculum.setCurUnit(row.getCell(6).toString());
                        if (curriculum.getCurId() != 0) {
                            if (!curriculumDao.update(curriculum)) {
                                msg += "<br />第" + i + "行更新失败。";
                            }
                        } else {
                            if (!curriculumDao.save(curriculum)) {
                                msg += "<br />第" + i + "行保存失败。";
                            }
                        }
                    }
                }
                else
                {
                    msg += "<br />第" + i + "行保存失败。";
                }
            }
        } catch (FileNotFoundException e) {
            msg += "<br />文件未找到。";
        } catch (Exception ex) {
            msg += "<br />未知错误，导入中止。";
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    msg += "<br />未知错误，导入中止。";
                }
            }
        }
        logger.error(msg);
        return msg;
    }
    /** * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
//        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0){
            w = 7;
        }
//        return weekOfDays[w];
        return w;
    }

    /**
     * 获取课表类型
     */
    public Setting getCurriculumType() throws Exception {
        return settingsDao.getSetting();
    }

    /**
     * 清空课程表
     * @throws Exception
     */
    @Transactional
    public void curriculumDel() throws Exception {
        // 清空课程表
        curriculumDao.trucateCurrriculum();
        // 修改课程表
        Setting setting = settingsDao.getSetting();
        if (setting == null ) {
            setting = new Setting();
            setting.setCurriculumType(Curriculum.CUT_DATE_TYPE);
            this.saveSetting(setting);
        }
        else if(setting.getCurriculumType() == null || setting.getCurriculumType() == Curriculum.CUR_WEEK_TYPE)
        {
            setting.setCurriculumType(Curriculum.CUT_DATE_TYPE);
            this.updateSetting(setting);
        }
        else {
            setting.setCurriculumType(Curriculum.CUR_WEEK_TYPE);
            this.updateSetting(setting);
        }
        this.setSetting(setting);
    }
    @Transactional
    public void curriculumDelte() throws Exception {
        // 清空课程表
        curriculumDao.trucateCurrriculum();
    }
    /**
     * 查询所有课表信息
     */
    public List<Curriculum> getCurriculumInfo(int hostid) {
        try {
            return curriculumDao.getCurriculumList(hostid);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
    /**
     * 查询所有课表信息
     */
    public List<Curriculum> getCurriculumInfo(int hostid, int week_id) {
        try {
            return curriculumDao.getCurriculumList(hostid, week_id);
        } catch (Exception e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }
    /**
     * 查询所有课表信息
     */
    public List<Curriculum> getCurriculumInfo(int hostid, String date) {
        try {
            List<Curriculum> curriculums = curriculumDao.getCurriculumList(hostid, date);
            return curriculums;
        } catch (Exception e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据星期查询周课表信息
     * @param hostid
     * @param section
     * @param week_id
     * @return
     */
    public Curriculum getCurriculumInfo(int hostid, int section, int week_id) {
        try {
            Curriculum curriculum = curriculumDao.getCurriculum(hostid, section, week_id);
            return curriculum;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 根据日期查询学期课表信息
     * @param hostid
     * @param section
     * @param date
     * @return
     */
    public Curriculum getCurriculumInfo(int hostid, int section, String date) {
        try {
            Curriculum curriculum = curriculumDao.getCurriculum(hostid, section, date);
            return curriculum;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 新增总课程表
     */
   @Transactional
    public int saveCurriculum(int hostid, String teacherName, String curriculumName, int curSection, String unit, String date) {
        try {
            Curriculum curriculum = new Curriculum();
            curriculum.setCurHost(hostid);
            curriculum.setCurTeacher(teacherName);
            curriculum.setCurSubject(curriculumName);
            curriculum.setCurSection(curSection);
            curriculum.setCurUnit(unit);
            curriculum.setCurDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            curriculumDao.save(curriculum);
            return curriculum.getCurId();
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * 新增周课程表
     */
    @Transactional
    public int saveCurriculum(int hostid, String teacherName, String curriculumName, int curSection, String unit, Byte week_id) throws Exception{
        try {
            Curriculum curriculum = new Curriculum();
            curriculum.setCurHost(hostid);
            curriculum.setCurTeacher(teacherName);
            curriculum.setCurSubject(curriculumName);
            curriculum.setCurSection(curSection);
            curriculum.setCurUnit(unit);
            curriculum.setCurWeek(week_id);
            Host host = hostgroupService.getHostInfo(hostid);
            if(host != null) {
                curriculum.setCurClass(host.getHostName());
            }
            curriculumDao.save(curriculum);
            return curriculum.getCurId();
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }


    /**
     * 更新周课程表
     */

    @Transactional
    public boolean updateCurriculum(int hostid, String curriculumName, String teacherName, int curSection, String unit, Byte week_id) throws Exception{
        Curriculum curriculum = new Curriculum();
        curriculum.setCurHost(hostid);
        curriculum.setCurSubject(curriculumName);
        curriculum.setCurTeacher(teacherName);
        curriculum.setCurSection(curSection);
        curriculum.setCurWeek(week_id);
        curriculum.setCurUnit(unit);
        return curriculumDao.updateWeekCurriculum(curriculum);

    }

    /**
     * 更新总课程表
     */
    @Transactional
    public boolean updateCurriculum(int hostid, String curriculumName, String teacherName, int curSection, String unit, String date) throws Exception{
        Curriculum curriculum = new Curriculum();
        curriculum.setCurHost(hostid);
        curriculum.setCurSubject(curriculumName);
        curriculum.setCurTeacher(teacherName);
        curriculum.setCurSection(curSection);
        curriculum.setCurUnit(unit);
        curriculum.setCurDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        return curriculumDao.updateDateCurriculum(curriculum);
    }
    /**
     * 删除周课表
     */
    @Transactional
    public void delWeekCurriculum(int hostid, int curSection, int week_id) throws Exception{
        curriculumDao.delWeekCurriculum(hostid, curSection, week_id);
    }
    /**
     *删除总课表
     */
    @Transactional
    public void delDateCurriculum(int hostid, int curSection, String date) throws Exception{
        curriculumDao.delDateCurriculum(hostid, curSection, date);
    }
    /**
     *删除课表
     */
    @Transactional
    public boolean delCurriculum(int curId) throws Exception{
        return curriculumDao.delete(curId);
    }
    /**
     *根据nas路径获取nas信息
     */
    public List<Object[]> getNasByPath(String nasPath) throws Exception{
        return nasDao.getNasByPath(nasPath);
    }

    /**
     * 根据指定日期, 当前时间获取课表信息
     * @param hostid
     * @param startRecordTime
     * @return
     */
    public List<Object[]> getCurriculum(Integer hostid, String startRecordTime,Integer curriculumType){
        try {
            return curriculumDao.getCurriculum(hostid, startRecordTime,curriculumType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
    }

    public List getCurriculum() {
        try {
            return curriculumDao.getCurriculum();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
    /**
     * 根据授權嗎判斷當前版本 1-學校版 2-區域版
     * @return
     */
    public int getLicenseVersion() {
        try {
            Map<String,String> license = LicenseUtils.findLicense();
            Integer licenseNum = 0;
            if(null!=license&&!license.isEmpty()){
                licenseNum = Integer.parseInt(license.get("hhtrec_device_num"));
            }
            return licenseNum < LicenseUtils.HHTREC_DEVICE_MAXNUM ? 1:2;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 修改Tbox通道名称
     * @param token
     * @param deviceName
     */
    public void updateTboxDeviceName (String deviceName, String token) {
        Integer deviceId = null;
        try {
            deviceId = deviceDao.getTBOXDeviceId(token);
            if (deviceId != null) {
                deviceDao.updateTBOXDeviceName(deviceName, deviceId);
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    /*public void updateTboxDeviceConfigName(String deviceName, String token) {
        Integer deviceId = null;
        try {
            deviceId = deviceDao.getTBOXDeviceConfigId(token);
            if (deviceId != null) {
                deviceDao.updateTBOXDeviceConfigName(deviceName, deviceId);
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }*/
}
