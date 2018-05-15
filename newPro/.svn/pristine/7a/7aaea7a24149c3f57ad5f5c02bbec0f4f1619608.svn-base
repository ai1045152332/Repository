package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordhibernate.dao.NewsDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.News;
import com.honghe.recordhibernate.entity.tools.FileUtil;
import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import com.honghe.recordweb.action.frontend.news.entity.*;
import com.honghe.recordweb.action.frontend.news.form.ProjectPageForm;
import com.honghe.recordweb.service.frontend.DBHelper.SqlLiteTool;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.base.util.GlobalParameter;
import com.honghe.recordweb.util.base.util.StringUtil;
import com.honghe.recordweb.util.base.util.ZipUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hthwx on 2015/2/27.
 */
@Service("newsService")
public class NewsService {
    private final static Logger logger = Logger.getLogger(NewsService.class);

    public static enum ReleaseMode {
        FullScreen("常驻桌面"), Marquee("滚动显示"), Bubble("气泡弹出显示");
        private String mode;

        ReleaseMode(String mode) {
            this.mode = mode;
        }

        public String toString() {
            return this.mode;
        }
    }

    public static enum Font {
        SONG("宋体"), BLACK("黑体");
        private String fontType;

        Font(String fontType) {
            this.fontType = fontType;
        }

        public String toString() {
            return this.fontType;
        }
    }

    @Resource
    private NewsDao newsDao;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private SyslogService syslogService;
    @Resource
    private UserService userService;
    @Resource
    private PageService pageService;
    @Resource
    private ItemService itemService;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    private SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
    private final String SQL_CREATE_CMDCODE = "CREATE TABLE `command_code` (" +
            "  `code_id` int(11)," +
            "  `code_name` varchar(50)," +
            "  `code_type` varchar(20)," +
            "  `code_result` varchar(50)," +
            "  `code_remark` varchar(100)," +
            "  `code_instruction` varchar(200)," +
            "  `dspec_id` int(11)," +
            "  `code_code` varchar(50)," +
            "`code_flag`  varchar(20), " +
            "`code_group`  varchar(20) " +
            ")";
    private final String SQL_CREATE_CMDCODE_UPDATE = "CREATE TABLE `cmdcode_update` (" +
            "  `id` int(11)," +
            "  `dspec_id` int(11)," +
            "  `cmdcode_update` varchar(50)" +
            ")";

    /**
     * //todo 加注释
     * @param newsInfoMap
     * @param hostIdList
     * @param newId
     * @return
     */
    @Transactional
    public boolean sendNews(Map<String, String> newsInfoMap, List<String> hostIdList, String newId) {
        boolean bool = false;
        News news;
        if (newsInfoMap == null || newsInfoMap.size() < 1)
            return false;
        else {
            if (hostIdList == null || hostIdList.size() < 1) {
                return false;
            } else {
                try {
                    if (newId != null && !newId.equals("")) //如果newId不为空，则为信息修改
                    {
                        news = newsDao.getNewsInfoById(Integer.parseInt(newId));
                    } else {
                        news = new News();
                        if (newsInfoMap.containsKey("uid") && newsInfoMap.get("uid") != null && !newsInfoMap.get("uid").trim().equals("")) {
                            news.setUid(Integer.valueOf(newsInfoMap.get("uid").toString()));
                        }
                        if (newsInfoMap.containsKey("username") && newsInfoMap.get("username") != null && !newsInfoMap.get("username").toString().trim().equals("")) {
                            news.setUsername(newsInfoMap.get("username").toString());
                        }
                    }
                    if (newsInfoMap.containsKey("n_cont") && newsInfoMap.get("n_cont") != null && !newsInfoMap.get("n_cont").toString().trim().equals("")) {
                        news.setnCont(newsInfoMap.get("n_cont").toString());
                    }
                    if (newsInfoMap.containsKey("n_begintime") && newsInfoMap.get("n_begintime") != null && !newsInfoMap.get("n_begintime").toString().trim().equals("")) {
                        news.setnBegintime(df.parse(newsInfoMap.get("n_begintime").toString()));
                    }
                    if (newsInfoMap.containsKey("n_endtime") && newsInfoMap.get("n_endtime") != null && !newsInfoMap.get("n_endtime").toString().trim().equals("")) {
                        news.setnEndtime(df.parse(newsInfoMap.get("n_endtime").toString()));
                    }
                    if (newsInfoMap.containsKey("n_showtype") && newsInfoMap.get("n_showtype") != null && !newsInfoMap.get("n_showtype").toString().trim().equals("")) {
                        news.setnShowtype(newsInfoMap.get("n_showtype").toString());
                    }
                    if (newsInfoMap.containsKey("n_font") && newsInfoMap.get("n_font") != null && !newsInfoMap.get("n_font").toString().trim().equals("")) {
                        news.setnFont(newsInfoMap.get("n_font").toString());
                    }
                    if (newsInfoMap.containsKey("n_fontsize") && newsInfoMap.get("n_fontsize") != null && !newsInfoMap.get("n_fontsize").toString().trim().equals("")) {
                        news.setnFontsize(newsInfoMap.get("n_fontsize").toString());
                    }
                    if (newsInfoMap.containsKey("n_fontcolor") && newsInfoMap.get("n_fontcolor") != null && !newsInfoMap.get("n_fontcolor").toString().trim().equals("")) {
                        news.setnFontcolor(newsInfoMap.get("n_fontcolor").toString());
                    }
                    news.setnTimeline(df.parse(df.format(new Date())));
                    if (newsInfoMap.containsKey("n_loop") && newsInfoMap.get("n_loop") != null && !newsInfoMap.get("n_loop").toString().trim().equals("")) {
                        news.setnLoop(newsInfoMap.get("n_loop").toString());
                    }
                    if (newsInfoMap.containsKey("n_start") && newsInfoMap.get("n_start") != null && !newsInfoMap.get("n_start").toString().trim().equals("")) {
                        news.setnStart(newsInfoMap.get("n_start").toString());
                    }
                    if (newsInfoMap.containsKey("n_finish") && newsInfoMap.get("n_finish") != null && !newsInfoMap.get("n_finish").toString().trim().equals("")) {
                        news.setnFinish(newsInfoMap.get("n_finish").toString());
                    }
                    if (newsInfoMap.containsKey("n_week") && newsInfoMap.get("n_week") != null && !newsInfoMap.get("n_week").toString().trim().equals("")) {
                        news.setnWeek(Integer.parseInt(newsInfoMap.get("n_week").toString()));
                    }
                    if (newsInfoMap.containsKey("n_month") && newsInfoMap.get("n_month") != null && !newsInfoMap.get("n_month").toString().trim().equals("")) {
                        news.setnMonth(Integer.parseInt(newsInfoMap.get("n_month").toString()));
                    }
                    if (newsInfoMap.containsKey("n_type") && newsInfoMap.get("n_type") != null && !newsInfoMap.get("n_type").toString().trim().equals("")) {
                        news.setnType(Integer.parseInt(newsInfoMap.get("n_type").toString()));
                    }
                    if (newsInfoMap.containsKey("n_status") && newsInfoMap.get("n_status") != null && !newsInfoMap.get("n_status").toString().trim().equals("")) {
                        news.setnStatus(Integer.parseInt(newsInfoMap.get("n_status").toString()));
                    }
                    if (newsInfoMap.containsKey("n_devicetype") && newsInfoMap.get("n_devicetype") != null && !newsInfoMap.get("n_devicetype").toString().trim().equals("")) {
                        news.setnDevicetype(newsInfoMap.get("n_devicetype").toString());
                    }
                    String hostIdStrOld = "";
                    if (newId != null && !newId.equals("")) //如果newId不为空，则为信息修改
                    {
                        newsDao.updateNews(news);
                        Map<String, Object> mapTmp = newsDao.getHostsByNewId(Integer.parseInt(newId));
                        if (mapTmp != null && mapTmp.containsKey("hostIds") && mapTmp.get("hostIds").toString().trim() != "") {
                            hostIdStrOld = "," + mapTmp.get("hostIds").toString() + ","; //格式化字符串为",1,12,30,"
                        }
                        newsDao.executeBySql("update host2news set status = 2 where n_id=" + newId, "no data for deleteing~~~");//软删除所有设备
                    } else {
                        if (newsDao.addNews(news) < 1)
                            return false;
                    }
                    int newsId = news.getnId();
                    String updateHostIds = "";
                    for (String hostId : hostIdList) {
                        if (hostIdStrOld.indexOf("," + hostId + ",") > -1) {
                            updateHostIds += hostId + ",";//保留的设备ID
                        } else {
                            Host host = hostgroupService.getHostInfo(Integer.valueOf(hostId));
                            if (host != null) {
                                newsDao.sendNews(newsId, Integer.parseInt(hostId), host.getHostName(), 0);//表中无当前记录，则添加
                            }
                        }
                        //===增加测试消息日志
                        try {
                            if (newId != null && !newId.equals("")) //如果newId不为空，则为信息修改
                            {
                                //syslogService.addDeviceLog(hostgroupService.HostInfoService(hostId).getHostIpaddr(),"平台发送消息成功，等待接收","SYSTEM");
                            }
                        } catch (Exception e) {
                            //syslogService.addDeviceLog("0.0.0.0","设备id:" + hostId + "，平台发送消息失败，原因获取ip异常","SYSTEM");
                        }
                        //==========
                    }
                    if (!updateHostIds.trim().equals("")) {
                        //保留设备的状态重置为0
                        newsDao.executeBySql("update host2news set status = 0 where n_id=" + newId + " and host_id in (" + updateHostIds + "-1)", "no data for deleteing~~~");//软删除所有设备
                    }
                    //异步发送命令
                    Map<String, String> map = newsMap(news);
                    sendNews(newsId, map);//调用线程发送命令方法
                    return true;
                } catch (ParseException pe) {
//                    pe.printStackTrace();
                    logger.error("data format exception", pe);
                } catch (Exception e) {
//                    e.printStackTrace();
                    logger.error("", e);
                }
            }
        }
        return bool;
    }

    /**
     * 根据信息的数据，按照设备端的格式生成map作为发布到设备的参数
     *
     * @param news
     * @return
     */
    public Map<String, String> newsMap(News news) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        try {
            map.put("Content", news.getnCont());
            map.put("NewsId", String.valueOf(news.getnId()));
            map.put("UserName", news.getUsername());
            if (news.getnShowtype() == null) {
                map.put("DisplayMode", "");
            } else {
                map.put("DisplayMode", news.getnShowtype());
            }
            map.put("IsLoop", "Y");  //      信息一直为循环显示
            String cMode = "";
            String eValue = "";
            if (news.getnLoop() != null && !news.getnLoop().equals("")) {
                if (news.getnLoop().equals("day")) {
                    cMode = "Day";
                    eValue = "";
                } else if (news.getnLoop().equals("week")) {
                    cMode = "Week";
                    eValue = news.getnWeek().toString();
                } else if (news.getnLoop().equals("month")) {
                    cMode = "Month";
                    eValue = news.getnMonth().toString();
                }
            }
            map.put("CycleMode", cMode);
            map.put("ExecValue", eValue);
            map.put("StartDate", df1.format(news.getnBegintime()));
            map.put("EndDate", df1.format(news.getnEndtime()));
            map.put("StartTime", news.getnStart() == null || news.getnStart().equals("") ? "08:00:01" : news.getnStart() + ":00");
            map.put("EndTime", news.getnFinish() == null || news.getnFinish().equals("") ? "19:00:00" : news.getnFinish() + ":00");
            map.put("FontSize", news.getnFontsize() != null ? news.getnFontsize() : "");
            map.put("FontColor", news.getnFontcolor() != null ? news.getnFontcolor() : "");
            map.put("Font", news.getnFont() != null ? news.getnFont() : "");
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return map;
    }

    /**
     * 调用发送信息的接口
     *
     * @param newId
     * @param
     * @return
     */
    public boolean sendNews(final int newId, final Map<String, String> map) {
        //调用信息发布方法，返回是否成功
        try {
            final List<Object[]> list = newsDao.querryNewsBySql(" select a.host_id,a.status,b.n_type from host2news a,news b where a.n_id = b.n_id and a.n_id =" + newId);
            for (Object[] objArr : list) {
                if (objArr != null) {
                    Map<String, String> hostMap = new HashMap<>();
                    hostMap.putAll(map);
                    if (objArr[1] != null && (objArr[1].toString().equals("2") || objArr[1].toString().equals("3"))) {
                        hostMap.put("Control", "Delete");
                    } else {
                        hostMap.put("Control", "");
                    }
                    if (objArr[2].toString().equals("1")) {
                        hostMap.put("nType", "1");
                    } else {
                        hostMap.put("nType", "0");
                    }
                    String hostId = "";
                    if (objArr[0] != null && !objArr[0].toString().equals("")) {
                        hostId = objArr[0].toString();
                    }
                    if (!hostId.equals("") && objArr[0] != null) {
                        String hostIp = "";
                        Map<String, String> hostInfo = hostDevice.getHostInfoById(hostId);

                        hostIp = hostInfo != null && !hostInfo.isEmpty() ? hostInfo.get("host_ipaddr") : hostIp;

                        if ("".equals(hostIp)) {
                            continue;
                        }
                        //ip地址不为空则发送命令
                        if (computerCommand.sendMessage(hostIp, hostMap)) {
                            try {
                                //信息发送成功之后修改数据库状态
                                if (objArr[1] != null && (objArr[1].toString().equals("2") || objArr[1].toString().equals("3"))) {
                                    newsDao.executeBySql("update host2news set status = 2 where n_id =" + newId + " and host_id =" + objArr[0].toString(), "no data for update~~~~~~~~~~~~~");
                                    syslogService.addDeviceLog(hostIp, df.format(new Date()) + " 删除信息 ：" + hostMap.get("Content"), "MESSAGE");
                                } else {
                                    newsDao.executeBySql("update host2news set status = 1 where n_id =" + newId + " and host_id =" + objArr[0].toString(), "no data for update~~~~~~~~~~~~~");
                                    syslogService.addDeviceLog(hostIp, df.format(new Date()) + " 收到信息 ：" + hostMap.get("Content"), "MESSAGE");

                                }
                            } catch (Exception e) {
                                logger.error("", e);
//                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return true;
    }

    /**
     * 软删除信息，只是修改关系表中的status状态，置为2，信息表数据不动；
     *
     * @param newId
     * @param userId
     * @return
     */
    @Transactional
    public boolean delNews(String newId, String userId, HashMap<String, Integer> roleMap, String newsType) {
        try {
            // newsDao.delNews(Integer.parseInt(newId));
            //  String sql = " delete from  host2news where n_id =" + newId;
            String sql = " update host2news set status = 3 where n_id =" + newId;
            if (!roleMap.containsKey("1") && !roleMap.containsKey("2") && !roleMap.containsKey("3"))  //如果用户不是超级管理员,工程实施，校园管理员，则根据权限显示
            {
                sql += " and host_id in (select b.host_id from group2host b right join group2user c on b.group_id = c.group_id where c.user_id = " + userId + ") ";
            }

            if (newsType.equals("1")) { //富文本消息置成删状态
                String sqlnews = " update news set n_status = -1 where n_id = " + newId;
                newsDao.executeBySql(sqlnews, "no required data for deleting~~~~~~~~~~~~");
            }
            newsDao.executeBySql(sql, "no required data for deleting~~~~~~~~~~~~");
            News delNews = newsDao.getNewsInfoById(Integer.parseInt(newId));
            //异步发送删除命令
            Map<String, String> map = newsMap(delNews);
            sendNews(Integer.parseInt(newId), map);//调用线程发送命令方法
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("delete news failed", e);
            return false;
        }
    }

    /**
     * 强制根据hostId删除信息关系；（当在平台删除某台设备之前调用）
     *
     * @param hostId 设备id
     * @param hostIp 设备ip
     * @return
     */
    @Transactional
    public boolean deleteNewsForce(String hostId, String hostIp) {
        try {
            String sql = " delete from  host2news where host_id =" + hostId;
            newsDao.executeBySql(sql, "no required data for deleting~~~~~~~~~~~~");//强制删除对应设备与消息的关系
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("Content", "");
            map.put("NewsId", "");
            map.put("UserName", "");
            map.put("DisplayMode", "");
            map.put("IsLoop", "");
            map.put("CycleMode", "");
            map.put("ExecValue", "");
            map.put("StartDate", "");
            map.put("EndDate", "");
            map.put("StartTime", "");
            map.put("EndTime", "");
            map.put("FontSize", "");
            map.put("FontColor", "");
            map.put("Font", "");
            map.put("Control", "DeleteAll");
            if (hostId == null || hostId.equals("")) {
                Map<String, String> hostInfo = hostDevice.getHostInfoById(hostId);
                if (hostInfo != null && !hostInfo.isEmpty()) {
                    hostIp = hostInfo.get("host_ipaddr");
                }
            }
            computerCommand.sendMessage(hostIp, map);//给设备发一通知，清空消息表
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("force delete news failed", e);
            return false;
        }
    }

    /**
     * //todo 加注释
     * @param userId
     * @param newsType
     * @param pageSize
     * @param currentPage
     * @param deviceType
     * @return
     */
    public Page getNewsList(String userId, String newsType, String pageSize, String currentPage, String deviceType) {
        try {
            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            page = newsDao.getRichNewsByUserId(page, userId, newsType, deviceType);
            return page;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 分页获取news数据
     *
     * @param pageSize    每页数据
     * @param currentPage 当前为第几页
     * @param userId      用户id；如果具有超级权限，则userId =1；否则根据用户分组对应host权限获取news数据
     * @return
     */
    public Page getNewsPageListByUser(String pageSize, String currentPage, String userId, HashMap<String, Integer> roleMap, String type, String newsType, String deviceType) {
        List<Object[]> groupIdList = new LinkedList<Object[]>();
        String groupIds = "";
        try {
            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            if (roleMap == null || roleMap.size() <= 0) {
                groupIds = "";
            }
            if (roleMap != null && roleMap.size() > 0 && (roleMap.containsKey("1") || roleMap.containsKey("2") || roleMap.containsKey("3")))  //如果用户不是超级管理员,工程实施，校园管理员，则根据权限显示
            {
                groupIds = "0";
            } else {
                groupIdList = hostgroupService.getHostgroupList2Service(userId, type);//获取用户有权限的分组
                if (groupIdList != null && groupIdList.size() > 0) {
                    if (groupIdList.size() == 1) {
                        groupIds = " =" + groupIdList.get(0)[0];//直接获取数据
                    } else {
                        //根据权限获取用户权限下的分组集合字符串
                        for (Object[] objArr : groupIdList) {
                            groupIds += objArr[0] + ",";
                        }
                        groupIds = " in (" + groupIds + "-1) ";
                    }
                }
            }
            newsDao.getNewsListByPage(page, groupIds, newsType, deviceType);
            if (page.getTotalPageSize() < Integer.parseInt(currentPage)) {
                page = new Page<List<Map<String, String>>>(page.getTotalPageSize(), Integer.parseInt(pageSize));
                newsDao.getNewsListByPage(page, groupIds, newsType, deviceType);
            }
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * //todo 加注释
     * @param pageSize
     * @param currentPage
     * @param userId
     * @param type
     * @param newsType
     * @param deviceType
     * @return
     */
    public Page appGetNewsPageListByUser(String pageSize, String currentPage, Integer userId, String type, String newsType, String deviceType) {
        List<Object[]> groupIdList = new LinkedList<Object[]>();
        Integer role = userService.getRoleByUserid(userId);
        String groupIds = "";
        try {
            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            if (role == null) {
                groupIds = "";
            }
            if (role != null && (role == 1 || role == 2 || role == 3)) {
                groupIds = "0";//如果用户不是超级管理员,工程实施，校园管理员，则根据权限显示
            } else {
                groupIdList = hostgroupService.getHostgroupList2Service(userId.toString(), type);//获取用户有权限的分组
                if (groupIdList != null && groupIdList.size() > 0) {
                    if (groupIdList.size() == 1) {
                        groupIds = " =" + groupIdList.get(0)[0];//直接获取数据
                    } else {
                        //根据权限获取用户权限下的分组集合字符串
                        for (Object[] objArr : groupIdList) {
                            groupIds += objArr[0] + ",";
                        }
                        groupIds = " in (" + groupIds + "-1) ";
                    }
                }
            }
            newsDao.getNewsListByPage(page, groupIds, newsType,deviceType);
            if (page.getTotalPageSize() < Integer.parseInt(currentPage)) {
                page = new Page<List<Map<String, String>>>(page.getTotalPageSize(), Integer.parseInt(pageSize));
                newsDao.getNewsListByPage(page, groupIds, "0",deviceType);
            }
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据Id获取信息内容
     *
     * @param newsId
     * @return
     */
    public News getNewsInfoById(String newsId) {
        try {
            return newsDao.getNewsInfoById(Integer.parseInt(newsId));
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据newsId获取有效分配的设备id字符串
     *
     * @param newsId
     * @return
     */
    public Map<String, Object> getHost2NewsById(String newsId) {
        try {
            return newsDao.getHostsByNewId(Integer.parseInt(newsId));
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据newsId获取有效分配的设备
     *
     * @param
     * @return
     */
    /*public List<Object[]> getHost2NewsById(String newsId)
    {
        try
        {
            return newsDao.querryNewsBySql("select host_id from host2news where status <> 2 and n_id =" + newsId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }*/

    /**
     * //todo 加注释
     * @param ip
     * @param dbPath
     */
    public void generateSqLiteDbFile(String ip, String dbPath) {
        try {
            String dSpecIdStr = "";
            String dbName = "test.db";
            String sql = SQL_CREATE_CMDCODE;
            SqlLiteTool sqlLiteTool = new SqlLiteTool();
            String aa = "INSERT INTO command_code(code_id,code_name,code_type,code_result,code_remark,code_instruction,dspec_id,code_code,code_flag,code_group) VALUES (";
            List<Object[]> listNews = newsDao.querryNewsBySql("select a.code_id,a.code_name,a.code_type,a.code_result,a.code_remark,a.code_instruction,a.dspec_id,a.code_code,a.code_flag,a.code_group from command_code a left join host b on a.dspec_id = b.dspec_id where b.host_ipaddr = '" + ip + "'");
            //syslogService.addDeviceLog(ip,"生成对应sql文件" + "select a.code_id,a.code_name,a.code_type,a.code_result,a.code_remark,a.code_instruction,a.dspec_id,a.code_code,a.code_flag,a.code_group from command_code a left join host b on a.dspec_id = b.dspec_id where b.host_ipaddr = '" + ip + "'" + listNews.size(),"test");
            if (listNews != null && listNews.size() > 0) {
                if (listNews.get(0) != null && listNews.get(0).length > 7) {
                    dSpecIdStr = listNews.get(0)[6].toString();
                    dbName = dSpecIdStr + ".db";
                    //syslogService.addDeviceLog(ip,"生成对应sql文件" + ip + "generateSqLiteDbFile 中的dbName=" + dbName,"test");
                }
            }
            sqlLiteTool.setDB_PATH(dbPath + dbName);
            //sqlLiteTool.sqLiteConn(dbName,false);
            sqlLiteTool.sqLiteStatement(dbName, false);
            boolean createTableBool = sqlLiteTool.sqLiteCreateTable("command_code", SQL_CREATE_CMDCODE, false);
            if (createTableBool) {
                for (Object[] objArr : listNews) {
                    if (objArr != null) {
                        sqlLiteTool.sqLiteExecuteSqlSimple(aa + " '" + objArr[0] + "','" + objArr[1] + "','" + objArr[2] + "','" + objArr[3] + "','" + objArr[4] + "','" + objArr[5] + "','" + objArr[6] + "','" + objArr[7] + "','" + objArr[8] + "','" + objArr[9] + "')");
                    }
                }
            }
            sqlLiteTool.sqLiteConnCommit();
            createTableBool = sqlLiteTool.sqLiteCreateTable("cmdcode_update", SQL_CREATE_CMDCODE_UPDATE, false);
            List<Object[]> cmdUpdateFlagList = newsDao.querryNewsBySql("select cmdcode_update,id from cmdcode_update where dspec_id =" + dSpecIdStr);
            if (cmdUpdateFlagList != null && cmdUpdateFlagList.size() > 0 && cmdUpdateFlagList.get(0) != null) {
                String cmdUpdateFlag = cmdUpdateFlagList.get(0)[0].toString();
                sqlLiteTool.sqLiteExecuteSql("INSERT INTO cmdcode_update (id,dspec_id,cmdcode_update) values (1," + dSpecIdStr + ",'" + cmdUpdateFlag + "')", true);
                //syslogService.addDeviceLog(ip,"生成对应sql文件" + ip + "cmdcode_update写入记录","test");
            }
            sqlLiteTool.sqLiteClose();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("生成对应sql文件异常 ip=" + ip, e);
            //syslogService.addDeviceLog(ip,"生成对应sql文件" + ip + "异常","test");
        }
    }

    /**
     * //todo 加注释
     * @param ip
     * @return
     */
    public String generateSql(String ip) {
        String sql = SQL_CREATE_CMDCODE;
        String dSpecIdStr = "";
        //    SqlTool sqlTool = new SqlTool(SqlTool.SqlType.INSERT,new CommandCode());
        // String aa = sqlTool.getSqlBuffer();
        String aa = "INSERT INTO command_code(code_id,code_name,code_type,code_result,code_remark,code_instruction,dspec_id,code_code) VALUES (";
        try {
            List<Object[]> listNews = newsDao.querryNewsBySql("select a.code_id,a.code_name,a.code_type,a.code_result,a.code_remark,a.code_instruction,a.dspec_id,a.code_code from command_code a left join host b on a.dspec_id = b.dspec_id where b.host_ipaddr = '" + ip + "'");
            for (Object[] objArr : listNews) {
                if (objArr != null) {
                    sql += aa + " '" + objArr[0] + "','" + objArr[1] + "','" + objArr[2] + "','" + objArr[3] + "','" + objArr[4] + "','" + objArr[5] + "','" + objArr[6] + "','" + objArr[7] + "');\n";
                    dSpecIdStr = objArr[6].toString();
                }
            }
            sql += SQL_CREATE_CMDCODE_UPDATE;
            List<Object[]> cmdUpdateFlagList = newsDao.querryNewsBySql("select cmdcode_update,id from cmdcode_update where dspec_id =" + dSpecIdStr);
            if (cmdUpdateFlagList != null && cmdUpdateFlagList.size() > 0) {
                String cmdUpdateFlag = cmdUpdateFlagList.get(0)[0].toString();
                sql += "INSERT INTO cmdcode_update (id,dspec_id,cmdcode_update) values (1," + dSpecIdStr + ",'" + cmdUpdateFlag + "');\n";
            }
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
        }
        return sql;
    }

    /**
     * 获取当前有限信息及删除失败的信息
     * @param hostId
     * @param hostIp
     */
    public void sendNewsInitial(String hostId, String hostIp) {
        String sql = "select a.host_id,a.status,c.n_id,c.n_cont,c.username,c.n_showtype,c.n_loop,c.n_week,c.n_month,"
                + "c.n_begintime,c.n_endtime,c.n_start,c.n_finish,c.n_fontsize,c.n_fontcolor,c.n_font,c.n_type from host2news a left join news c on a.n_id = c.n_id where a.host_id ='" + hostId + "' and a.status in(0,1) and CURDATE() BETWEEN n_begintime and n_endtime";
        try {
            final List<Object[]> list = newsDao.querryNewsBySql(sql);
            logger.debug("所有有效的消息,ip为:" + hostIp + ",id为:" + hostId + ",消息为:" + list);
            int i = 0;
            for (Object[] objArr : list) {
                if (i % 10 == 0) {
                    Thread.sleep(10000);
                }
                if (objArr != null && objArr[2] != null) {
                    Map<String, String> hostMap = new HashMap<>();
                    hostMap.put("Content", objArr[3] != null ? objArr[3].toString() : "");
                    hostMap.put("NewsId", objArr[2].toString());
                    hostMap.put("UserName", objArr[4] != null ? objArr[4].toString() : "");
                    hostMap.put("DisplayMode", objArr[5] != null ? objArr[5].toString() : "");
                    hostMap.put("IsLoop", "Y");  //      信息一直为循环显示
                    String cMode = "";
                    String eValue = "";
                    if (objArr[6] != null && !objArr[6].toString().trim().equals("")) {
                        if (objArr[6].toString().trim().equals("day")) {
                            cMode = "Day";
                            eValue = "";
                        } else if (objArr[6].toString().trim().equals("week")) {
                            cMode = "Week";
                            eValue = objArr[7] != null ? objArr[7].toString().trim().toString() : "";
                        } else if (objArr[6].toString().trim().equals("month")) {
                            cMode = "Month";
                            eValue = objArr[8] != null ? objArr[8].toString().trim().toString() : "";

                        }
                    }
                    hostMap.put("CycleMode", cMode);
                    hostMap.put("ExecValue", eValue);
                    hostMap.put("StartDate", objArr[9] != null && !objArr[9].toString().trim().equals("") ? df1.format(df.parse(objArr[9].toString().trim())) : null);
                    hostMap.put("EndDate", objArr[10] != null && !objArr[10].toString().trim().equals("") ? df1.format(df.parse(objArr[10].toString().trim())) : null);
                    hostMap.put("StartTime", objArr[11] == null || objArr[11].toString().trim().equals("") ? "08:00:01" : objArr[11].toString().trim() + ":00");
                    hostMap.put("EndTime", objArr[12] == null || objArr[12].toString().trim().equals("") ? "19:00:00" : objArr[12].toString().trim() + ":00");
                    hostMap.put("FontSize", objArr[13] != null ? objArr[13].toString().trim() : "");
                    hostMap.put("FontColor", objArr[14] != null ? objArr[14].toString().trim() : "");
                    hostMap.put("Font", objArr[15] != null ? objArr[15].toString().trim() : "");
                    hostMap.put("nType", objArr[16] != null ? objArr[16].toString().trim() : "");
                    if (objArr[1] != null && (objArr[1].toString().equals("2") || objArr[1].toString().equals("3"))) {
                        hostMap.put("Control", "Delete");
                    } else {
                        hostMap.put("Control", "");
                    }
                    if (!hostIp.equals("") && objArr[0] != null) {
                        if (computerCommand.sendMessage(hostIp, hostMap)) {
                            logger.debug("设备上线发送有效消息成功,ip为:" + hostIp + ",内容为:" + hostMap);
                            try {
                                //信息发送成功之后修改数据库状态
                                if (objArr[1] != null && objArr[1].toString().equals("3")) {
                                    newsDao.executeBySql("update host2news set status = 2 where n_id =" + objArr[2] + " and host_id =" + objArr[0].toString(), "no data for update~~~~~~~~~~~~~");
                                } else {
                                    newsDao.executeBySql("update host2news set status = 1 where n_id =" + objArr[2] + " and host_id =" + objArr[0].toString(), "no data for update~~~~~~~~~~~~~");
                                }
                            } catch (Exception e) {
                                //e.printStackTrace();
                                logger.error("更新数据库异常", e);
//                                logger.info(e.getMessage());
                            }
                        } else {
                            logger.debug("设备上线发送有效消息失败,ip为:" + hostIp + ",内容为:" + hostMap);
                        }
                    }
                }
                i++;
            }
        } catch (Exception e) {
            logger.error("发送消息异常", e);
//            logger.info(e.getMessage());
        }
    }

    /**
     * 判断当前ip的设备的commandCode表是否有效
     *
     * @param ip            设备的ip地址
     * @param cmdCodeUpdate 设备本地数据库中cmdCodeUpdate中的标识符值
     * @return 是否超时，默认无效返回true,如果相等返回false
     */
    public boolean isDeviceCmdCodeTimeOut(String ip, String cmdCodeUpdate) {
        boolean bool = true;
        try {
            int i = newsDao.countNewsBySql("select count(*) from cmdcode_update a ,host b where a.dspec_id = b.dspec_id and a.cmdcode_update = '" + cmdCodeUpdate + "' and b.host_ipaddr='" + ip + "'");
            if (i > 0) {
                bool = false;
            }
        } catch (Exception e) {
            logger.error("根据Ip与命令表更新标识符查询异常", e);
//            e.printStackTrace();
        }
        return bool;
    }


    /**
     * testComcode,更新数据库命令格式
     */
    public void testCmdCode() {
        try {
            List<Object[]> resultList = (List<Object[]>) newsDao.querryNewsBySql("select code_id,code_name,code_result from command_code");
            if (resultList != null && resultList.size() > 0) {
                for (Object[] objArr : resultList) {
                    if (objArr != null && objArr.length > 2 && objArr[1] != null) {
                        String codeName = objArr[1].toString();
                        String newName = "";
                        int len = codeName.length();
                        if (len < 25) {
                            for (int i = 0; i < len / 2; i++) {
                                newName += codeName.substring(i * 2, (i + 1) * 2);
                                if (i < len / 2 - 1) {
                                    newName += ' ';
                                }
                            }
                            String newResult = "";
                            if (objArr[2] != null && !objArr[2].equals("") && objArr[2].toString().length() > 2) {
                                for (int i = 0; i < objArr[2].toString().length() / 2; i++) {
                                    newResult += objArr[2].toString().substring(i * 2, (i + 1) * 2);
                                    if (i < len / 2 - 1) {
                                        newResult += ' ';
                                    }
                                }
                            }
                            newName.trim();
                            newResult.trim();
                            if (!newName.equals("")) {
                                newsDao.executeBySql("update command_code set code_name ='" + newName + "',code_result = '" + newResult + "' where code_id =" + objArr[0].toString(), "no data update for this id = " + objArr[0].toString());
                            }
                            //   System.out.println("-----------" + codeName + "-------------");
                            //  System.out.println("-----------" + newName + "-------------");
                        }

                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }


    /**
     * 生成所有类型设备的sqlite文件到本地
     * @param dbPath
     */
    public void generateAllSqLiteByDspecId(String dbPath) {
        try {
            String dSpecIdStr = "";
            String dbName = "test.db";
            String sql = SQL_CREATE_CMDCODE;
            String aa = "INSERT INTO command_code(code_id,code_name,code_type,code_result,code_remark,code_instruction,dspec_id,code_code,code_flag,code_group) VALUES (";
            List<Object[]> listDspecId = newsDao.querryNewsBySql("SELECT dspec_id,dspec_name FROM `dspecification`");
            if (listDspecId != null && listDspecId.size() > 0) {
                for (Object[] objD : listDspecId) {

                    if (objD != null && objD.length > 1 && !objD[0].toString().equals("")) {
                        try {
                            List<Object[]> listNews = newsDao.querryNewsBySql("select a.code_id,a.code_name,a.code_type,a.code_result,a.code_remark,a.code_instruction,a.dspec_id,a.code_code,a.code_flag,a.code_group from command_code a where a.dspec_id =" + objD[0].toString());
                            //    syslogService.addDeviceLog(ip,"生成对应sql文件" + "select a.code_id,a.code_name,a.code_type,a.code_result,a.code_remark,a.code_instruction,a.dspec_id,a.code_code,a.code_flag,a.code_group from command_code a left join host b on a.dspec_id = b.dspec_id where b.host_ipaddr = '" + ip + "'" + listNews.size(),"test");
                            if (listNews != null && listNews.size() > 0) {
                                if (listNews.get(0) != null && listNews.get(0).length > 7) {
                                    SqlLiteTool sqlLiteTool = new SqlLiteTool();
                                    dSpecIdStr = listNews.get(0)[6].toString();
                                    dbName = dSpecIdStr + ".db";
                                    //    syslogService.addDeviceLog(ip,"生成对应sql文件" + ip + "generateSqLiteDbFile 中的dbName=" + dbName,"test");
                                    sqlLiteTool.setDB_PATH(dbPath + dbName);
                                    //sqlLiteTool.sqLiteConn(dbName,false);
                                    sqlLiteTool.sqLiteStatement(dbName, false);
                                    boolean createTableBool = sqlLiteTool.sqLiteCreateTable("command_code", SQL_CREATE_CMDCODE, false);
                                    if (createTableBool) {
                                        for (Object[] objArr : listNews) {
                                            if (objArr != null) {
                                                sqlLiteTool.sqLiteExecuteSqlSimple(aa + " '" + objArr[0] + "','" + objArr[1] + "','" + objArr[2] + "','" + objArr[3] + "','" + objArr[4] + "','" + objArr[5] + "','" + objArr[6] + "','" + objArr[7] + "','" + objArr[8] + "','" + objArr[9] + "')");
                                            }
                                        }
                                    }
                                    sqlLiteTool.sqLiteConnCommit();
                                    createTableBool = sqlLiteTool.sqLiteCreateTable("cmdcode_update", SQL_CREATE_CMDCODE_UPDATE, false);
                                    List<Object[]> cmdUpdateFlagList = newsDao.querryNewsBySql("select cmdcode_update,id from cmdcode_update where dspec_id =" + dSpecIdStr);
                                    if (cmdUpdateFlagList != null && cmdUpdateFlagList.size() > 0 && cmdUpdateFlagList.get(0) != null) {
                                        String cmdUpdateFlag = cmdUpdateFlagList.get(0)[0].toString();
                                        sqlLiteTool.sqLiteExecuteSql("INSERT INTO cmdcode_update (id,dspec_id,cmdcode_update) values (1," + dSpecIdStr + ",'" + cmdUpdateFlag + "')", true);
                                    }
                                    sqlLiteTool.sqLiteClose();
                                }
                            }

                        } catch (Exception e) {
//                            e.printStackTrace();
                            logger.error("", e);
                        }

                    }
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param userId
     * @param prid
     * @return
     */
    public Program queryProgram(String userId, String prid) {
        try {
            String path = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userId, prid, true);
            String thumb = com.honghe.recordhibernate.entity.tools.SearchPathTools.getThumb(userId, prid, true);
            Program program = this.parseProgramXml(path);
            program.setThumb(thumb); // 另存为模板时，弹出框的左侧显示节目的第一页
            program.setId(Integer.parseInt(prid));
            return program;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return new Program();
        }
    }

    /**
     * todo 加注释
     * @param savePath
     * @return
     */
    public Program parseProgramXml(String savePath) {
        try {
            Program program = new Program();
            SAXReader sax = new SAXReader();
            File f = new File(savePath);
            if (!f.exists()) {
                return null;
            }
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点
//	        Element ratio = root.element("ratio");
//	        program.setRatio(ratio.getText());

            Element curIdx = root.element("curIdx");

//            program.setCurIdx(curIdx.getText());
//            Element name = root.element("name");
//            program.setName(name.getText());
//
            program.setCurIdx(curIdx.getText());
            Element w = root.element("w");
            program.setW(w.getText());

            Element h = root.element("h");
            program.setH(h.getText());
//
//            Element theme = root.element("theme");
//            program.setTheme(theme.getText());
//
//            Element note= root.element("note");
//            program.setNote(note.getText());
//
//            Element updateTime= root.element("updateTime");
//            program.setUpdateTime(updateTime.getText());
//
//            Element state = root.element("state");
//            program.setState(state.getText());
//
//            Element editstate = root.element("editstate");
//            program.setEditstate(editstate.getText());

            Element pageSequence = root.element("pageSequence");

            List<String> pageList = new ArrayList<String>();

            if (pageSequence != null) {
                Iterator<?> iterator = pageSequence.elementIterator();
                while (iterator.hasNext()) {
                    Element pageElement = (Element) iterator.next();
                    pageList.add(pageElement.getText());
                }
            }

            program.setPageList(pageList);
            return program;
        } catch (DocumentException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @return
     */
    public List<String> queryPage(String userName, String prid) {
        String path = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, true);
        Program program = this.parseProgramXml(path);
        List<String> pageList = program.getPageList();
        return pageList;
    }

    /**
     * todo 加注释
     * @param userName
     * @param pageForm
     * @return
     * @throws DocumentException
     */
    public com.honghe.recordweb.action.frontend.news.entity.Page getPageInfo(String userName, ProjectPageForm pageForm) throws DocumentException {
        String path = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPage(userName, StringUtil.getUtf8Str(pageForm.getPrid()), pageForm.getPid(), true);
        com.honghe.recordweb.action.frontend.news.entity.Page page = new com.honghe.recordweb.action.frontend.news.entity.Page();
        Program program = new Program();
        SAXReader sax = new SAXReader();
        Document xmlDoc = sax.read(new File(path));
        Element root = xmlDoc.getRootElement();//根节点
        Element pid = root.element("pid");
        Element idx = root.element("idx");
        Element time = root.element("time");
        page.setIndex(idx.getTextTrim());
        page.setPrid(StringUtil.getUtf8Str(pageForm.getPrid()));
        page.setPid(pid.getTextTrim());
        page.setTime(time.getTextTrim());
        return page;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @return
     * @throws DocumentException
     */
    public com.honghe.recordweb.action.frontend.news.entity.Page getPageInfo(String userName, String prid, String pid) throws DocumentException {
        String path = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPage(userName, prid, pid, true);
        com.honghe.recordweb.action.frontend.news.entity.Page page = new com.honghe.recordweb.action.frontend.news.entity.Page();
        Program program = new Program();
        SAXReader sax = new SAXReader();
        Document xmlDoc = sax.read(new File(path));
        Element root = xmlDoc.getRootElement();//根节点
        Element pids = root.element("pid");
        Element idx = root.element("idx");
        Element time = root.element("time");
        page.setIndex(idx.getTextTrim());
        page.setPrid(prid);
        page.setPid(pids.getTextTrim());
        page.setTime(time.getTextTrim());
        return page;
    }

    /**
     * todo 加注释
     * @param userId
     * @param ntype
     * @param userName
     * @param deviceType
     * @return
     */
    @Transactional
    public int add(int userId, int ntype, String userName, String deviceType) {
        try {
            News news = new News();
            news.setUid(userId);
            news.setnType(ntype);
            news.setUsername(userName);
            news.setnStatus(0);
            news.setnTimeline(df.parse(df.format(new Date())));
            news.setnDevicetype(deviceType);
            return newsDao.addNews(news);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return 0;
        }
    }

    /**
     * todo 加注释
     * @return
     */
    public String checkProSubState() {
        return "false";
    }

    /**
     * todo 加注释
     * @param savePath
     * @param width
     * @param height
     * @param user
     */
    public void writeProgramXml(String savePath, String width, String height, String user) {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("root");//添加文档根

//            Element blockId = root.addElement("name");
//            blockId.addText(program.getName());
            Element userName = root.addElement("username");
            userName.addText(user);
//			Element ratio = root.addElement("ratio");
//			ratio.addText(program.getRatio());
//
            Element w = root.addElement("w");
            w.addText(width);

            Element h = root.addElement("h");
            h.addText(height);

//            Element theme = root.addElement("theme");
//            theme.addText(program.getTheme());

//            Element note = root.addElement("note");
//            note.addText(program.getNote());

            Element pageSeq = root.addElement("pageSequence");
            Element pageElement = pageSeq.addElement("page");
            pageElement.addText("1");

            Element curIdx = root.addElement("curIdx");
            curIdx.addText("1");

            Element updateTime = root.addElement("updateTime");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            updateTime.addText(df.parse(df.format(new Date())).toString());

//            Element state = root.addElement("state");
//            state.addText(""+GlobalParameter.STATE_PROJECT_CREATE);

//            Element editstate = root.addElement("editstate");
//            editstate.addText(""+GlobalParameter.STATE_PROJECT_CREATE);
//
//            Element isEdit = root.addElement("isEdit");
//            isEdit.addText(program.getIsEdit());

            persistenceXml(document, savePath);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    public void persistenceXml(Document xml, String path) {
        try {
            //输出全部原始数据，在编译器中显示
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
//			XMLWriter writer = new XMLWriter(System.out, format);
//			writer.write(xml);
//			writer.close();
            // 输出全部原始数据，并用它生成新的我们需要的XML文件
            FileOutputStream fos = new FileOutputStream(new File(path));
            XMLWriter writer2 = new XMLWriter(fos, format);
//			XMLWriter writer2 = new XMLWriter(new FileWriter(new File(path)), format);
            writer2.write(xml); //输出到文件
            writer2.close();
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param savePath
     * @param program
     */
    public void modifyProgramXml(String savePath, Program program) {
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点

//            Element w = root.element("w");
//            if(program.getW()!=null){
//                w.setText(program.getW());
//            }
//
//            Element h = root.element("h");
//            if(program.getH()!=null){
//                h.setText(program.getH());
//            }

//            Element theme = root.element("theme");
//            if(program.getTheme()!=null){
//                theme.setText(program.getTheme());
//            }
//
//            Element note = root.element("note");
//            if(program.getNote()!=null){
//                note.setText(program.getNote());
//            }

            Element curIdx = root.element("curIdx");
            if (program.getCurIdx() != null) {
                curIdx.setText(program.getCurIdx());
            }
//
//            Element name = root.element("name");
//            if(program.getName()!=null){
//                name.setText(program.getName());
//            }

//            Element updateTime = root.element("updateTime");

//            String time = ConvertStringTools.getTimestamp();
//            updateTime.setText(time);

//            Element state = root.element("state");
//            if(program.getState()!=null){
//                state.setText(program.getState());
//            }
//
//            Element editstate = root.element("editstate");
//            if(program.getEditstate()!=null){
//                editstate.setText(program.getState());
//            }
//
//            Element isEdit = root.element("isEdit");
//            if(program.getIsEdit()!=null){
//                isEdit.setText(program.getIsEdit());
//            }

            persistenceXml(xmlDoc, savePath);

            //new IndexBuilder().updateIndex(savePath);更新其它包中的索引
        } catch (DocumentException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param savePath
     * @param page
     */
    public void sortPageXml(String savePath, com.honghe.recordweb.action.frontend.news.entity.Page page) {
        try {
            int index = Integer.parseInt(page.getIndex()) - 1;
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点

            Element seqElement = root.element("pageSequence");

            List<String> pageList = new ArrayList<String>();
            if (seqElement != null) {
                Iterator<?> iterator = seqElement.elementIterator();
                while (iterator.hasNext()) {
                    Element pageElement = (Element) iterator.next();
                    if (!pageElement.getText().equals(page.getPid())) {
                        pageList.add(pageElement.getText());
                    }

                }
            }

            pageList.add(index, page.getPid());
            root.remove(seqElement);

            Element pageSeq = root.addElement("pageSequence");
            for (String pageString : pageList) {
                Element pageElement = pageSeq.addElement("page");
                pageElement.addText(pageString);
            }

            persistenceXml(xmlDoc, savePath);
        } catch (DocumentException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param path
     * @return
     */
    public String loadUserIndo(String path) {
        File file = new File(path);
        if (!file.exists()) {
            createButton(path, "program_new,program_open,button_undo,button_redo,button_resource,program_preview,program_publish");
        }

        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(path));
            Element root = xmlDoc.getRootElement();//根节点

            Element btns = root.element("btns");
            String btnList = btns.getText();

            return btnList;
        } catch (DocumentException e) {
//            e.printStackTrace();
            logger.error("", e);
        }

        return null;
    }

    /**
     * todo 加注释
     * @param path
     * @param btnList
     */
    public void createButton(String path, String btnList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");//添加文档根

        Element btns = root.addElement("btns");
        btns.addText(btnList);

        persistenceXml(document, path);
    }

    /**
     * todo 加注释
     * @param path
     * @param btnList
     */
    public void saveUserInfo(String path, String btnList) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                createButton(path, btnList);
            }

            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(path));
            Element root = xmlDoc.getRootElement();//根节点

            Element btns = root.element("btns");
            btns.setText(btnList);

            persistenceXml(xmlDoc, path);
        } catch (DocumentException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param isSnap
     * @param virtualPath
     * @param mapped
     * @return
     */
    public String getPathByVirtual2Ppt(boolean isSnap, String virtualPath, String mapped) {
        if (virtualPath.equals("media/cat.mp4") || virtualPath.equals("images/test1.jpg") || virtualPath.equals("{}") || virtualPath.equals("")) {
            return "";
        }
        if ("@dx".equals(mapped)) {
            return "";
        }
        String tempStr = "../media/";
        String[] temp = mapped.split("/");
        String nameStr = temp[temp.length - 2];
        tempStr += nameStr;
        String[] temp1 = virtualPath.split("/");
        String typeStr = temp1[temp1.length - 1];
        String suffix = typeStr.substring(typeStr.lastIndexOf(".") + 1);
        int type = getType(suffix);
        String extName = GlobalParameter.getExtName(type, "." + suffix);

        String[] tempMaps = mapped.split("/");
        extName = tempMaps[tempMaps.length - 1];
        return tempStr + "/" + extName;
    }

    /**
     * todo 加注释
     * @param isSnap
     * @param virtualPath
     * @param mapped
     * @return
     */
    public String getPathByVirtual2(boolean isSnap, String virtualPath, String mapped) {
        if (virtualPath.equals("media/cat.mp4") || virtualPath.equals("images/test1.jpg") || virtualPath.equals("{}") || virtualPath.equals("")) {
            return "";
        }
        if ("@dx".equals(mapped)) {
            return "";
        }
        String tempStr = "../media/";
        String[] temp = mapped.split("/");
        String nameStr = temp[temp.length - 2];
        tempStr += nameStr;
        String[] temp1 = virtualPath.split("/");
        String typeStr = temp1[temp1.length - 1];
        String suffix = typeStr.substring(typeStr.lastIndexOf(".") + 1);
        int type = getType(suffix);
        String extName = GlobalParameter.getExtName(type, "." + suffix);

        if (isSnap) {
            return tempStr + "/thumb.png";
        } else {
            return tempStr + "/" + extName;
        }


    }

    /**
     * todo 加注释
     * @param str1
     * @param str2
     * @return
     */
    public static long getDistanceDays(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return days;
    }


    /**
     * 倒计时 功能 按天时分秒计算的方式
     * @param str1 todo 加注释
     * @param str2 todo 加注释
     * @return
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec, diff};
        return times;
    }

    /**
     * todo 加注释
     * @param path
     * @param username
     * @param prid
     * @return
     */
    public String getSrcList(String path, String username, String prid) {
        String[] strs = path.split("/");
        if (strs == null || strs[2] == null) {
            return "";
        }
        String dstSrc = strs[2];
        dstSrc = com.honghe.recordhibernate.entity.tools.SearchPathTools.editPathRelative + username + "/" + prid + "/media/" + dstSrc;
        File file = new File(dstSrc);

        String files = "";
        File[] fileLists = file.listFiles();
        if (fileLists.length >= 3) {
            for (int i = 1; i <= fileLists.length - 2; i++) {
                String[] namestr = dstSrc.split("/");
                String allName = "../media/" + namestr[namestr.length - 1] + "/" + i + ".png";
    /*			String allName = dstSrc.replace("../webapps/ManagementCenter/data", "")+"/"+i+".png";*/
                files = files + allName + ",";
            }
        } else {
            for (File subFile : fileLists) {
                String name = subFile.getName();
                if (!name.equals("thumb.png") && !name.equals("word.zip") && !name.equals("excel.zip") && !name.equals("pdf.zip")) {
                    String[] namestr = dstSrc.split("/");
                    String allName = "../media/" + namestr[namestr.length - 1] + "/" + name;
                    files = files + allName + ",";
                }
            }
        }
        files = files.substring(0, files.length() - 1);
        return files;
    }

    /**
     * todo 加注释
     * @param suffix
     * @return
     */
    public int getType(String suffix) {
        String videoType = "avi,wmv,mov,rm,rmvb,mpg,mpeg,mp4,vob,flv,f4v,mkv,3gp,m4v,ts";
        String imgType = "jpg,jpeg,png,bmp,gif";
        String wordType = "docx,doc";
        String excelType = "xlsx,xls";
        String pdfType = "pdf";
        String pptType = "pptx,ppt,pps";
        String flashType = "swf";
        int type = 0;
        int vValue = videoType.indexOf(suffix);
        if (vValue != -1) {
            type = 1;
        }
        int wValue = wordType.indexOf(suffix);
        if (wValue != -1) {
            type = 2;
        }
        int eValue = excelType.indexOf(suffix);
        if (eValue != -1) {
            type = 3;
        }

        int imgValue = imgType.indexOf(suffix);
        if (imgValue != -1) {
            type = 4;
        }
        int pValue = pdfType.indexOf(suffix);
        if (pValue != -1) {
            type = 5;
        }
        int pptValue = pptType.indexOf(suffix);
        if (pptValue != -1) {
            type = 6;
        }
        int swfValue = flashType.indexOf(suffix);
        if (swfValue != -1) {
            type = 7;
        }
        return type;
    }

    /**
     * todo 加注释
     * @param mapped
     * @param globalPath
     * @return
     */
    public String getPath(String mapped, String globalPath) {
        if (globalPath == null || "".equals(globalPath)) {
            return "";
        }
        String[] strs = mapped.split("/");
        if (strs.length < 2) {
            return "";
        }
        String name = strs[strs.length - 2];
        String suffix = globalPath.substring(globalPath.lastIndexOf(".") + 1);
        String glob = "../media/" + name + "/" + GlobalParameter.getExtName(getType(suffix.toLowerCase()), suffix.toLowerCase());
        return glob;

    }

    /**
     * todo 加注释
     * @param prid
     * @param pid
     * @param path
     * @param pages
     * @param cur
     * @param name
     * @return
     */
    public Map<String, String> getProjectMap(String prid, String pid, String path, String pages, String cur, String name) {
        String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(name, prid, true);
        Map<String, String> fileMap = this.parseProgramAttr(proPath);
        String h = fileMap.get("h");
        if (h == null) {
            h = "1600";
        }
        String w = fileMap.get("w");
        if (w == null) {
            w = "900";
        }
        String curIdx = fileMap.get("curIdx");

        Map<String, String> map = new HashMap<String, String>();
        map.put("prid", prid);
        map.put("pid", pid);
        map.put("path", path);
        map.put("pages", pages);

        map.put("cur", curIdx);
        map.put("w", w);
        map.put("h", h);
        return map;
    }

    /**
     * todo 加注释
     * @param attrHash
     * @return
     */
    public String getProjectHtml(Map<String, String> attrHash) {
        String programModelHtml = FileUtil.getFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(2), "UTF-8");
        Set<Map.Entry<String, String>> set = attrHash.entrySet();
        Iterator<Map.Entry<String, String>> itor = set.iterator();
        while (itor.hasNext()) {
            Map.Entry<String, String> entry = itor.next();
            String value = "";
            if (entry.getValue() != null) {
                value = entry.getValue();
            }
            programModelHtml = programModelHtml.replace("%@!^" + entry.getKey() + "^!@%", value);
        }
        return programModelHtml;
    }

    /**
     * todo 加注释
     * @param savePath
     * @return
     */
    public Map<String, String> parseProgramAttr(String savePath) {
        Map<String, String> hash = new HashMap<String, String>();
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));

            Element root = xmlDoc.getRootElement();//根节点

            Iterator<?> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element ele = (Element) iterator.next();

                String value = ele.getText();
                hash.put(ele.getName(), value);
            }

            return hash;
        } catch (DocumentException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * todo 加注释
     * @param username
     * @param prid
     * @param quipub
     * @return
     * @throws Exception
     */
    public String checkBeforePublish(String username, String prid, String quipub) throws Exception {
        String path = "";
        //if(quipub.equals("1")){
        //    path = SearchPathTools.getPublishProgram( prid, false);
        //}else if(quipub.equals("0")){
        path = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(username, prid, false);
        // }
        File rootFolder = new File(path);
        File[] folders = rootFolder.listFiles();
        for (File i : folders) {
            if (i.isDirectory() && (!i.getName().equals("preview")
                    && (!i.getName().equals("previewSingle")
                    && (!i.getName().equals("media")
                    && (!i.getName().equals("source")
                    && (!i.getName().equals("mapped"))))))) {
                // 判断节目是否为空的   如果节目没有任何的操作  则不允许发布
                if (i.listFiles().length > 2) {
                    return "0";
                }
            }
        }
        return "-1";
    }

    /**
     * 根据消息ID获取消息类
     *
     * @param id id
     * @return
     */
    public News getNewsById(int id) {
        try {
            return newsDao.getNewsInfoById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param quipub
     * @throws Exception
     */
    @Transactional
    public void publish(String userName, String prid, String quipub) throws Exception {
        News project = this.getNewsById(Integer.parseInt(prid));
        //project.setEditstate(GlobalParameter.STATE_PROJECT_PUBLISH);
        //project.setState(GlobalParameter.STATE_PROJECT_PUBLISH);
//        newsDao.updateNews(project);
//        if(quipub.equals("1")){
//            updateStateQ(userName,prid,GlobalParameter.STATE_PROJECT_PUBLISH);
//            packageFolderQ(userName,prid);
//        }else {
        updateState(userName, prid, GlobalParameter.STATE_PROJECT_PUBLISH);
        packageFolder(userName, prid);
//        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @throws Exception
     */
    public void packageFolder(String userName, String prid) throws Exception {
        String editString = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreview(userName, prid);
        FileUtil.delFolder(editString);

        this.previewProgram(userName, prid, "-1", "publish");
        String publish = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, false);
        FileUtil.delFolder(publish);

        String srcPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, false);
        String dstPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, false);
        //压缩文件，产生下载文件包
        List<String> exceptFile = new ArrayList<String>();
        exceptFile.add("media");
        exceptFile.add("downloadList.xml");
        exceptFile.add("project.zip");
        exceptFile.add("source");
        String zipName = "project";
        ZipUtil.zipFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, false), com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, false), exceptFile, zipName, "");
        //end
        FileUtil.copyFolder(srcPath, dstPath);
        zipFile(prid);
    }

    /**
     * todo 加注释
     * @param prid
     */
    private void zipFile(String prid) {
        String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishZip(false, prid);

        try {
            File proFile = new File(proPath);
            //if(proFile.exists()){
            //FileUtil.deleteFile(proPath);
            //}
            ZipUtil.zip(proPath, com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreview(prid));
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param state
     * @throws Exception
     */
    public void updateState(String userName, String prid, short state) throws Exception {
        String path = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, true);
        Program program = this.parseProgramXml(path);
        this.updateProjectState(path, state);
    }

    /**
     * todo 加注释
     * @param savePath
     * @param state
     */
    public void updateProjectState(String savePath, short state) {
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点

            Element proState = root.element("state");
            Element proEditstate = root.element("editstate");
            if (proState == null) {
                proState = root.addElement("state");
            }
            if (proEditstate == null) {
                proEditstate = root.addElement("editstate");
            }
            proState.setText("" + state);
            proEditstate.setText("" + state);


            persistenceXml(xmlDoc, savePath);
            //new IndexBuilder().updateIndex(savePath); //更新索引？
        } catch (DocumentException e) {
            //            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param path
     * @param suffix
     */
    public void mergeFile(String path, String suffix) {
        String componentPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelComponentPath("0");
        String baseText = "";
        if (suffix.equals("js")) {
            baseText = FileUtil.getFileContent(componentPath + "/comp.js", "UTF-8");
        }

        File rootCssFile = new File(componentPath);
        //遍历所有css或js
        File[] files = rootCssFile.listFiles();
        try {
            String textContent = "";
            if (files != null) {
                for (File folder : files) {
                    if (folder.isDirectory()) {
                        String fileContent = FileUtil.getFileContent(componentPath + folder.getName() + "/comp." + suffix, "UTF-8");
                        textContent = textContent + fileContent;
                    }
                }
            }
            //合并
            FileUtil.writeFileContent(path + "comp." + suffix, baseText + textContent, "UTF-8");
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param updateTime
     * @param nvaFlag
     * @return
     * @throws ParseException
     */
    public String initPre(String userName, String prid, String updateTime, String nvaFlag) throws ParseException {
        String delFolderSign = "1"; // 1-->不删除     0-->删除
        String beforeUpdateTime = "";
        if (nvaFlag.equals("template")) {
            if (!beforeUpdateTime.equals(updateTime)) {
                delFolderSign = "0";
                FileUtil.delFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 1, userName, prid, "", 0));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(4), com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 4, userName, prid, "", 0));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(5), com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 5, userName, prid, "", 0));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/excellentWorks/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 6, userName, prid, "", 0));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/courseList/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 6, userName, prid, "", 0));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/weather/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 6, userName, prid, "", 0));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/classdynamics/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 6, userName, prid, "", 0));

                FileUtil.copyFileByPath(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/streaming/flvplayer.swf", com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 1, userName, prid, "", 0) + "/flvplayer.swf");

                FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 1, userName, prid, "", 0) + "/js/util.js");

                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 4, userName, prid, "", 0), "css");
                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 5, userName, prid, "", 0), "js");
            }
            String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getTempProgram(userName, prid, true);
            Program program = this.parseProgramXml(proPath);
            beforeUpdateTime = program.getUpdateTime();
        } else {
            if (nvaFlag.equals("single")) {
                FileUtil.delFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "single"));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(4), com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 4, userName, prid, "", 0, "single"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(5), com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 5, userName, prid, "", 0, "single"));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/excellentWorks/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, "single"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/courseList/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, "single"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/weather/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, "single"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/classdynamics/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, "single"));
                FileUtil.copyFileByPath(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/streaming/flvplayer.swf", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "single") + "/flvplayer.swf");

                FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "single") + "/js/util.js");

                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 4, userName, prid, "", 0, "single"), "css");
                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 5, userName, prid, "", 0, "single"), "js");

            } else if (nvaFlag.equals("singleQ")) {
                FileUtil.delFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "singleQ"));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(4), com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 4, userName, prid, "", 0, "singleQ"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(5), com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 5, userName, prid, "", 0, "singleQ"));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/excellentWorks/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, "singleQ"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/courseList/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, "singleQ"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/weather/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, "singleQ"));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/classdynamics/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, "singleQ"));
                FileUtil.copyFileByPath(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/streaming/flvplayer.swf", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "singleQ") + "/flvplayer.swf");

                FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "singleQ") + "/js/util.js");

                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 4, userName, prid, "", 0, "singleQ"), "css");
                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 5, userName, prid, "", 0, "singleQ"), "js");
            } else if (nvaFlag.equals("public")) {// 在公共节目列表  点击预览的情况下  如果该节目进行过快速发布的编辑  则preview重新生成
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                if (beforeUpdateTime.equals("")) {
                    beforeUpdateTime = "1900-01-01 00:00:00";
                }
                Date dt1 = df.parse(beforeUpdateTime);
                Date dt2 = df.parse(updateTime);
                if (dt1.getTime() < dt2.getTime()) {
                    delFolderSign = "0";
                    FileUtil.delFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, ""));

                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(4), com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 4, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(5), com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 5, userName, prid, "", 0, ""));

                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/excellentWorks/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/courseList/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/weather/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/classdynamics/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFileByPath(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/streaming/flvplayer.swf", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "") + "/flvplayer.swf");

                    FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "") + "/js/util.js");

                    this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 4, userName, prid, "", 0, ""), "css");
                    this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 5, userName, prid, "", 0, ""), "js");
                }
                String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, true);
                Program program = this.parseProgramXml(proPath);
                beforeUpdateTime = program.getUpdateTime();
            } else if (nvaFlag.equals("publicQuiPub")) {
                delFolderSign = "0";
                FileUtil.delFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, ""));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(4), com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 4, userName, prid, "", 0, ""));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(5), com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 5, userName, prid, "", 0, ""));

                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/excellentWorks/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/courseList/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/weather/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/classdynamics/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 6, userName, prid, "", 0, ""));
                FileUtil.copyFileByPath(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/streaming/flvplayer.swf", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "") + "/flvplayer.swf");

                FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 1, userName, prid, "", 0, "") + "/js/util.js");

                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 4, userName, prid, "", 0, ""), "css");
                this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 5, userName, prid, "", 0, ""), "js");

                String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, true);
                Program program = this.parseProgramXml(proPath);
                beforeUpdateTime = program.getUpdateTime();
            } else {
                if (!beforeUpdateTime.equals(updateTime)) {
                    delFolderSign = "0";
                    FileUtil.delFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, ""));

                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(4), com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 4, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(5), com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 5, userName, prid, "", 0, ""));

                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/excellentWorks/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/courseList/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/weather/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
                    FileUtil.copyFolder(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/classdynamics/images/", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));

                    FileUtil.copyFileByPath(com.honghe.recordhibernate.entity.tools.SearchPathTools.modelPath + "components/streaming/flvplayer.swf", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "") + "/flvplayer.swf");

                    FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "") + "/js/util.js");

                    this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 4, userName, prid, "", 0, ""), "css");
                    this.mergeFile(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 5, userName, prid, "", 0, ""), "js");
                }
                String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, true);
                Program program = this.parseProgramXml(proPath);
                beforeUpdateTime = program.getUpdateTime();
            }
        }
        return delFolderSign;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     */
    public void init(String userName, String prid) {
        FileUtil.delFolder(SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, ""));

        FileUtil.copyFolder(SearchPathTools.getModelPath(4), SearchPathTools.getEditPreviewPath(false, 4, userName, prid, "", 0, ""));
        FileUtil.copyFolder(SearchPathTools.getModelPath(5), SearchPathTools.getEditPreviewPath(false, 5, userName, prid, "", 0, ""));

        FileUtil.copyFolder(SearchPathTools.modelPath + "components/excellentWorks/images/", SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
        FileUtil.copyFolder(SearchPathTools.modelPath + "components/courseList/images/", SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
        FileUtil.copyFolder(SearchPathTools.modelPath + "components/weather/images/", SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
        FileUtil.copyFolder(SearchPathTools.modelPath + "components/classdynamics/images/", SearchPathTools.getEditPreviewPath(false, 6, userName, prid, "", 0, ""));
        FileUtil.copyFileByPath(SearchPathTools.modelPath + "components/streaming/flvplayer.swf", SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "") + "/flvplayer.swf");

        FileUtil.copyFileByPath(ServletActionContext.getServletContext().getRealPath("pages") + "/frontend/news/editor/js/util/util.js", SearchPathTools.getEditPreviewPath(false, 1, userName, prid, "", 0, "") + "/js/util.js");

        this.mergeFile(SearchPathTools.getEditPreviewPath(false, 4, userName, prid, "", 0, ""), "css");
        this.mergeFile(SearchPathTools.getEditPreviewPath(false, 5, userName, prid, "", 0, ""), "js");
    }

    /**
     * todo 加注释
     * @param prid
     * @param pid
     * @param path
     * @param pages
     * @param cur
     * @param name
     * @return
     */
    private Map<String, String> getTemplateMap(String prid, String pid, String path, String pages, String cur, String name) {
        String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getTempProgram(name, prid, true);
        Map<String, String> fileMap = this.parseProgramAttr(proPath);
        String h = fileMap.get("h");
        String w = fileMap.get("w");
        String curIdx = fileMap.get("curIdx");

        Map<String, String> map = new HashMap<String, String>();
        map.put("prid", prid);
        map.put("pid", pid);
        map.put("path", path);
        map.put("pages", pages);

        map.put("cur", curIdx);
        map.put("w", w);
        map.put("h", h);
        return map;
    }

    /**
     * 返回json
     * @param userName
     * @param prid
     * @param curId
     * @param navFlag
     * @return
     * @throws Exception
     */
    public String previewProgram(String userName, String prid, String curId, String navFlag) throws Exception {
        String pagesHtml = "";
        if (navFlag != null && navFlag.equals("template")) {
            News project = newsDao.getNewsInfoById(Integer.parseInt(prid));
            String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPreTemplateProgram(userName, prid, true);
            Program program = this.parseProgramXml(proPath);
            initPre(userName, prid, program.getUpdateTime(), "template");

            List<String> pageList = program.getPageList();

            pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(true, 2, userName, prid, "", 0);

            JSONArray jsonArray = new JSONArray();
            for (String pid : pageList) {
                com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getTemplatePageInfo(userName, prid, pid, true);
                String pageHtml = getTemplatePageHtml(userName, prid, pid, program);

                long timestamp = System.currentTimeMillis();
                String path = "page-" + pid + "(" + timestamp + ").html";
                net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                obj.put("path", path);
                obj.put("during", page.getTime());
                obj.put("pid", pid);
                jsonArray.put(obj);
                FileUtil.writeFileContent(SearchPathTools.getTemplatePreviewPath(false, 3, userName, prid, pid, timestamp), pageHtml, "UTF-8");
            }
            Map<String, String> map = getTemplateMap(prid, "", "", jsonArray.toString(), curId, userName);
            String projectHtml = getProjectHtml(map);
            FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getTemplatePreviewPath(false, 2, userName, prid, "", 0), projectHtml, "UTF-8");
        } else/* if(navFlag.equals("suspend") || navFlag.equals("private"))*/ {
            String delFolderSign = "";
            News project = new News();
            Program program = new Program();
            // 快速发布 ---> 单页预览
            if (navFlag.equals("singleQ")) {
                project = newsDao.getNewsInfoById(Integer.parseInt(prid));
                String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, true);
                program = this.parseProgramXml(proPath);
            } else if (navFlag.equals("public") || navFlag.equals("publicQuiPub")) {// 快速发布  ----> 快速发布按钮      公共节目 ----> 列表处的预览  （因为快速发布会导致公共节目本地文件的变化，所以公共节目处的预览，也改成从本地文件的公共节目的目录下预览）
                project = newsDao.getNewsInfoById(Integer.parseInt(prid));
                String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, true);
                program = this.parseProgramXml(proPath);
            } else {// 其它情况 ：我的节目 ----> 发布  单页预览  整个节目预览
                project = newsDao.getNewsInfoById(Integer.parseInt(prid));
                String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, true);
                program = this.parseProgramXml(proPath);
            }

            if (navFlag.equals("publish")) { // 普通节目编辑页面的发布按钮  （点击发布按钮同时会生成预览）
                init(userName, prid);
            } else if (navFlag.equals("public")) { // 公共节目列表处的处的预览
                delFolderSign = initPre(userName, prid, program.getUpdateTime(), "public");
                // 节目内容在没有变动的情况下  不重新生成文件
                if (delFolderSign.equals("0")) {
                    List<String> pageList = program.getPageList();

                    pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(true, 2, userName, prid, "", 0, "");

                    JSONArray jsonArray = new JSONArray();

                    for (String pid : pageList) {
                        com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getPublishPageInfo(userName, prid, pid, true);
                        String pageHtml = pageService.getPublishPageHtml(userName, prid, pid, program);
                        long timestamp = System.currentTimeMillis();
                        String path = "page-" + pid + "(" + timestamp + ").html";
                        net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                        obj.put("path", path);
                        obj.put("during", page.getTime());
                        obj.put("pid", pid);
                        jsonArray.put(obj);
                        FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 3, userName, prid, pid, timestamp, ""), pageHtml, "UTF-8");
                    }
                    Map<String, String> map = getPublishProjectMap(prid, "", "", jsonArray.toString(), curId, userName);
                    String projectHtml = getProjectHtml(map);
                    FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 2, userName, prid, "", 0, ""), projectHtml, "UTF-8");
                }
                return pagesHtml;
            } else if (navFlag.equals("publicQuiPub")) { // 快速发布按钮 （点击快速发布时  要重新生成publish/prid/preview ）
                initPre(userName, prid, program.getUpdateTime(), "publicQuiPub");
                List<String> pageList = program.getPageList();

                pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(true, 2, userName, prid, "", 0, "");

                JSONArray jsonArray = new JSONArray();

                for (String pid : pageList) {
                    com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getPublishPageInfo(userName, prid, pid, true);
                    String pageHtml = pageService.getPublishPageHtml(userName, prid, pid, program);
                    long timestamp = System.currentTimeMillis();
                    String path = "page-" + pid + "(" + timestamp + ").html";
                    net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                    obj.put("path", path);
                    obj.put("during", page.getTime());
                    obj.put("pid", pid);
                    jsonArray.put(obj);
                    FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 3, userName, prid, pid, timestamp, ""), pageHtml, "UTF-8");
                }
                Map<String, String> map = getPublishProjectMap(prid, "", "", jsonArray.toString(), curId, userName);
                String projectHtml = getProjectHtml(map);
                FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 2, userName, prid, "", 0, ""), projectHtml, "UTF-8");

                return pagesHtml;
            } else { // 其他的地方的预览
                // 普通节目编辑页面的单个页面预览
                if (navFlag.equals("single")) {
                    initPre(userName, prid, program.getUpdateTime(), "single");
                    pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(true, 7, userName, prid, "", 0, "single");
                    JSONArray jsonArray = new JSONArray();
                    com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getPageInfo(userName, prid, curId, true);
                    String pageHtml = pageService.getPageHtml(userName, prid, curId, program);
                    long timestamp = System.currentTimeMillis();
                    String path = "page-" + curId + "(" + timestamp + ").html";
                    net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                    obj.put("path", path);
                    obj.put("during", page.getTime());
                    obj.put("pid", curId);
                    jsonArray.put(obj);
                    FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 3, userName, prid, curId, timestamp, "single"), pageHtml, "UTF-8");

                    Map<String, String> map = getProjectMap(prid, "", "", jsonArray.toString(), curId, userName);
                    String projectHtml = getProjectHtml(map);
                    FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 7, userName, prid, "", 0, "single"), projectHtml, "UTF-8");
                    return pagesHtml;
                } else if (navFlag.equals("singleQ")) {// 节目快速发布页面的单页预览
                    initPre(userName, prid, program.getUpdateTime(), "singleQ");
                    pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(true, 7, userName, prid, "", 0, "singleQ");
                    JSONArray jsonArray = new JSONArray();
                    com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getPublishPageInfo(userName, prid, curId, true);
                    String pageHtml = pageService.getPublishPageHtml(userName, prid, curId, program);
                    long timestamp = System.currentTimeMillis();
                    String path = "page-" + curId + "(" + timestamp + ").html";
                    net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                    obj.put("path", path);
                    obj.put("during", page.getTime());
                    obj.put("pid", curId);
                    jsonArray.put(obj);
                    FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 3, userName, prid, curId, timestamp, "singleQ"), pageHtml, "UTF-8");

                    Map<String, String> map = getPublishProjectMap(prid, "", "", jsonArray.toString(), curId, userName);
                    String projectHtml = getProjectHtml(map);
                    FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishPreviewPath(false, 7, userName, prid, "", 0, "singleQ"), projectHtml, "UTF-8");
                    return pagesHtml;
                } else {// 整个节目预览（我的节目列表处的预览   、 普通节目编辑处的预览）
                    delFolderSign = initPre(userName, prid, program.getUpdateTime(), "");
                    // 节目内容在没有变动的情况下  不重新生成文件
                    if (delFolderSign.equals("0")) {
                        List<String> pageList = program.getPageList();

                        pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(true, 2, userName, prid, "", 0, "");

                        JSONArray jsonArray = new JSONArray();

                        for (String pid : pageList) {
                            com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getPageInfo(userName, prid, pid, true);
                            String pageHtml = pageService.getPageHtml(userName, prid, pid, program);

                            long timestamp = System.currentTimeMillis();
                            String path = "page-" + pid + "(" + timestamp + ").html";
                            net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                            obj.put("path", path);
                            obj.put("during", page.getTime());
                            obj.put("pid", pid);
                            jsonArray.put(obj);
                            FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 3, userName, prid, pid, timestamp, ""), pageHtml, "UTF-8");
                        }

                        Map<String, String> map = getProjectMap(prid, "", "", jsonArray.toString(), curId, userName);
                        String projectHtml = getProjectHtml(map);
                        FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 2, userName, prid, "", 0, ""), projectHtml, "UTF-8");
                        //			if(navFlag.equals("publish")){  // 从节目制作-->发布进来的
                        //				pagesHtml = delFolderSign;
                        //			}
                    }
                    return pagesHtml;
                }
            }

            // 发布节目
            List<String> pageList = program.getPageList();

            pagesHtml = com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(true, 2, userName, prid, "", 0, "");

            JSONArray jsonArray = new JSONArray();

            for (String pid : pageList) {
                com.honghe.recordweb.action.frontend.news.entity.Page page = pageService.getPageInfo(userName, prid, pid, true);
                String pageHtml = pageService.getPageHtml(userName, prid, pid, program);

                long timestamp = System.currentTimeMillis();
                String path = "page-" + pid + "(" + timestamp + ").html";
                net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                obj.put("path", path);
                obj.put("during", page.getTime());
                obj.put("pid", pid);
                jsonArray.put(obj);
                FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 3, userName, prid, pid, timestamp, ""), pageHtml, "UTF-8");
            }

            Map<String, String> map = getProjectMap(prid, "", "", jsonArray.toString(), curId, userName);
            String projectHtml = getProjectHtml(map);
            FileUtil.writeFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditPreviewPath(false, 2, userName, prid, "", 0, ""), projectHtml, "UTF-8");
//			if(navFlag.equals("publish")){  // 从节目制作-->发布进来的
//				pagesHtml = delFolderSign;
//			}
        }
        return pagesHtml;
    }

    /**
     * todo 加注释
     * @param prid
     * @param pid
     * @param path
     * @param pages
     * @param cur
     * @param name
     * @return
     */
    private Map<String, String> getPublishProjectMap(String prid, String pid, String path, String pages, String cur, String name) {
        String proPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, true);
        Map<String, String> fileMap = this.parseProgramAttr(proPath);
        String h = fileMap.get("h");
        String w = fileMap.get("w");
        String curIdx = fileMap.get("curIdx");

        Map<String, String> map = new HashMap<String, String>();
        map.put("prid", prid);
        map.put("pid", pid);
        map.put("path", path);
        map.put("pages", pages);

        map.put("cur", curIdx);
        map.put("w", w);
        map.put("h", h);
        return map;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param quipub
     * @throws Exception
     */
    public void removeFolder(String userName, String prid, String quipub) throws Exception {
        //读取xml获取md5
        List<String> folders = new ArrayList();
        String targetFolderPath = "";
        if (quipub.equals("1")) {
            targetFolderPath = SearchPathTools.getPublishProgram(prid, false);
        } else if (quipub.equals("0")) {
            targetFolderPath = SearchPathTools.getEditProgram(userName, prid, false);
        }
        File folder = new File(targetFolderPath);
        File[] files = folder.listFiles();

        for (File f : files) {
            if (StringUtil.isNumber(f.getName())) {
                //页下x所有文件
                File[] files2 = f.listFiles();
                for (File f2 : files2) {
                    if (f2.getName().indexOf("blk") != -1) {
                        SAXReader reader = new SAXReader();
                        Document document = reader.read(f2);
                        Element root = document.getRootElement();
                        String type = "";
                        //确定type
                        for (Iterator iterInner = root.elementIterator(); iterInner.hasNext(); ) {
                            Element element = (Element) iterInner.next();
                            if ("t".equals(element.getName())) {
                                type = element.getTextTrim();
                            }

                        }
                        //选择相对应的读取方式
                        if ("1".equals(type) || "2".equals(type) || "11".equals(type) || "12".equals(type)) {
                            for (Iterator iterInner = root.elementIterator(); iterInner.hasNext(); ) {
                                Element element = (Element) iterInner.next();
                                if ("mapped".equals(element.getName())) {
                                    String path = element.getTextTrim();
                                    String[] pathStr = path.split("/");
                                    if (pathStr[pathStr.length - 2] != null) {
                                        folders.add(pathStr[pathStr.length - 2]);
                                    }
                                }
                            }
                        } else if ("18".equals(type)) { // "18" --> PPT
                            for (Iterator iterInner = root.elementIterator(); iterInner.hasNext(); ) {
                                Element element = (Element) iterInner.next();
                                if ("imgLoop".equals(element.getName())) {
                                    for (Iterator iterImgloop = element.elementIterator(); iterImgloop.hasNext(); ) {
                                        Element element2 = (Element) iterImgloop.next();
                                        if ("image".equals(element2.getName())) {
                                            for (Iterator iterImg = element2.elementIterator(); iterImg.hasNext(); ) {
                                                Element element3 = (Element) iterImg.next();
                                                if ("mapped".equals(element3.getName())) {
                                                    String path = element3.getTextTrim();
                                                    String[] pathStr = path.split("/");
                                                    if (pathStr[pathStr.length - 2] != null) {
                                                        folders.add(pathStr[pathStr.length - 2]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else if ("10".equals(type) || "8".equals(type) || "9".equals(type) || "4".equals(type)) {
                            for (Iterator iterInner = root.elementIterator(); iterInner.hasNext(); ) {
                                Element element = (Element) iterInner.next();
                                if ("imgLoop".equals(element.getName())) {
                                    for (Iterator iterImgloop = element.elementIterator(); iterImgloop.hasNext(); ) {
                                        Element element2 = (Element) iterImgloop.next();
                                        if ("image".equals(element2.getName())) {
                                            for (Iterator iterImg = element2.elementIterator(); iterImg.hasNext(); ) {
                                                Element element3 = (Element) iterImg.next();
                                                if ("mapped".equals(element3.getName())) {
                                                    String path = element3.getTextTrim();
                                                    String[] pathStr = path.split("/");
                                                    if (pathStr[pathStr.length - 2] != null) {
                                                        folders.add(pathStr[pathStr.length - 2]);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }

                            }
                        } else if ("22".equals(type)) {
                            for (Iterator iterInner = root.elementIterator(); iterInner.hasNext(); ) {
                                Element element = (Element) iterInner.next();
                                if ("bgMapped".equals(element.getName())) {
                                    String path = element.getTextTrim();
                                    if (!path.equals("")) {
                                        String[] pathStr = path.split("/");
                                        if (pathStr[pathStr.length - 2] != null) {
                                            folders.add(pathStr[pathStr.length - 2]);
                                        }
                                    }
                                }

                            }
                        } else if ("15".equals(type)) {
                            for (Iterator iterInner = root.elementIterator(); iterInner.hasNext(); ) {
                                Element element = (Element) iterInner.next();
                                if ("mapped".equals(element.getName())) {
                                    String path = element.getTextTrim();
                                    String[] pathStr = path.split("/");
                                    if (pathStr[pathStr.length - 2] != null) {
                                        folders.add(pathStr[pathStr.length - 2]);
                                    }
                                }
                                if ("articleLoop".equals(element.getName())) {
                                    for (Iterator iterImgloop = element.elementIterator(); iterImgloop.hasNext(); ) {
                                        Element element2 = (Element) iterImgloop.next();
                                        if ("mapped".equals(element2.getName())) {
                                            String path = element2.getTextTrim();
                                            String[] pathStr = path.split("/");
                                            if (pathStr[pathStr.length - 2] != null) {
                                                folders.add(pathStr[pathStr.length - 2]);
                                            }
                                        }
                                        if ("article".equals(element2.getName())) {
                                            for (Iterator iterImg = element2.elementIterator(); iterImg.hasNext(); ) {
                                                Element element3 = (Element) iterImg.next();
                                                if ("mapped".equals(element3.getName())) {
                                                    String path = element3.getTextTrim();
                                                    String[] pathStr = path.split("/");
                                                    if (pathStr[pathStr.length - 2] != null) {
                                                        folders.add(pathStr[pathStr.length - 2]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //清空未用到的md5文件夹
        String mediaPath = targetFolderPath + "media/";
        File mediaFolder = new File(mediaPath);
        File[] mediaFiles = mediaFolder.listFiles();
        if (mediaFiles != null) {
            for (File f3 : mediaFiles) {
                if (!folders.contains(f3.getName())) {
                    FileUtil.delFile(f3);
                    f3.delete();
                }
            }
        }

        //清空未用到的resource文件
        String resourcePath = targetFolderPath + "source/";
        File resourceFolder = new File(resourcePath);
        File[] resourceFiles = resourceFolder.listFiles();
        if (resourceFiles != null) {
            for (File f4 : resourceFiles) {
                String nameString = f4.getName().substring(0, f4.getName().lastIndexOf("."));
                if (!folders.contains(nameString)) {
                    FileUtil.delFile(f4);
                }
            }
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @param program
     * @return
     */
    public String getTemplatePageHtml(String userName, String prid, String pid, Program program) {
        List<Map<String, Object>> attList = itemService.getTemplateItemList(userName, prid, pid);
        String itemsHtml = "";
        for (Map<String, Object> map : attList) {
            String resourcePath = "";
            // 倒计时功能
            String tymd = (String) map.get("tymd");
            String tymdhms = (String) map.get("tymdhms");
            if (tymd != null || tymdhms != null) {
                if (!tymd.equals("") && tymd.equals("2")) {
                    resourcePath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelComponentPath("26tymd");
                } else if (!tymdhms.equals("") && tymdhms.equals("2")) {
                    resourcePath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelComponentPath("26tymdhms");
                }
            }
            if (resourcePath.equals("")) {
                resourcePath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelComponentPath((String) map.get("t"));
            }
            String itemHtml = itemService.mergeItem(resourcePath, map, userName, pid, program);
            itemsHtml = itemsHtml + "\n" + itemHtml;
        }
        String pageModelHtml = FileUtil.getFileContent(com.honghe.recordhibernate.entity.tools.SearchPathTools.getModelPath(3), "UTF-8");

        String pageHtml = pageModelHtml.replace("<div class=\"container\"></div>", "<div class=\"container\">" + itemsHtml + "</div>");

        return pageHtml;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param quipub
     * @throws Exception
     */
    public void writeFileList(String userName, String prid, String quipub) throws Exception {
        String targetFolderPath = "";
        if (quipub.equals("1")) {
            targetFolderPath = com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, false);
        } else if (quipub.equals("0")) {
            //targetFolderPath= com.honghe.recordhibernate.entity.tools.SearchPathTools.getEditProgram(userName, prid, false);
            //targetFolderPath= com.honghe.recordhibernate.entity.tools.SearchPathTools.getPublishProgram(prid, false);
            targetFolderPath = ServletActionContext.getServletContext().getRealPath("/msgResource") + "/" + userName + "/" + prid + "/";
        }
//		String targetFolderPath=SearchPathTools.getEditProgram(userName,prid,false);
        String savePath = targetFolderPath + "downloadList.xml";
        File folder = new File(targetFolderPath);
        DownloadFileList fileList = new DownloadFileList();
        //String resourcePath="/data/projects/publish/";
        //String resourcePath=ServletActionContext.getServletContext().getRealPath("/msgResource") + "/"+userName+"/"+prid+"/";
        //String donwloadPath= com.honghe.recordhibernate.entity.tools.SearchPathTools.getDownloadPath(resourcePath);
        String donwloadPath = ServletActionContext.getServletContext().getContextPath() + "/msgResource" + "/" + userName + "/" + prid + "/";
        //文件列表赋值

        fileList.setResource_path(donwloadPath);
        fileList.setType("MESSAGE_PROJECT_DOWNLOAD_SUCCESS");
        fileList.setProject_id(prid);
        //fileList.setProject_name(implProgram.findProjectByID(Integer.parseInt(prid)).getName());

        File projectXml = new File(targetFolderPath);
        SAXReader reader2 = new SAXReader();
        Document document2 = null;
        File[] files2 = projectXml.listFiles();
        if (files2 != null) {
            for (File f2 : files2) {
                if (f2.getName().equals("project.xml")) {
                    document2 = reader2.read(f2);
                    Element root2 = document2.getRootElement();
                    for (Iterator iterInner2 = root2.elementIterator(); iterInner2.hasNext(); ) {
                        Element element2 = (Element) iterInner2.next();
                        if (element2.getName().equals("updateTime")) {
                            fileList.setUpdate_time(element2.getText());
                        }
                    }
                }
            }
        }
        String media = targetFolderPath + "media/";
        File mediaFolder = new File(media);
        File[] files = mediaFolder.listFiles();
        List<ResourceItem> items = new ArrayList();
        if (files != null) {
            for (File f : files) {
                ResourceItem item = new ResourceItem();
                item.setMd5(f.getName());
                List<ResourceFile> filesInFolder = new ArrayList();
                File[] files3 = f.listFiles();

                for (File f3 : files3) {
                    ResourceFile rf = new ResourceFile();
                    rf.setType("0");
                    rf.setSrc_path(prid + "/media/" + f.getName() + "/" + f3.getName());
                    filesInFolder.add(rf);
                }
                for (ResourceFile rf : filesInFolder) {
                    if (rf.getSrc_path().indexOf("zip") != -1) {
                        ResourceFile rf2 = rf;
                        filesInFolder.clear();
                        filesInFolder.add(rf2);
                        break;
                    }
                }

                item.setResourceFile(filesInFolder);
                items.add(item);

            }
        }
        ResourceFile zipFile = new ResourceFile();
        zipFile.setSrc_path(prid + "/project.zip");
        zipFile.setType("1");
        List fileList2 = new ArrayList();
        fileList2.add(zipFile);
        ResourceItem zipItem = new ResourceItem();
        zipItem.setMd5("-1");
        zipItem.setResourceFile(fileList2);
        items.add(zipItem);
        fileList.setItems(items);
        this.writeFileList(savePath, fileList);
    }

    /**
     * todo 加注释
     * @param savePath
     * @param fileList
     */
    public void writeFileList(String savePath, DownloadFileList fileList) {
        try {

            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("root");//文档root
            Element type = root.addElement("type");
            type.addText(fileList.getType());

            Element project = root.addElement("project");

            Element project_id = project.addElement("project_id");
            project_id.addText(fileList.getProject_id());
            //Element project_name=project.addElement("project_name");
            //project_name.addText(fileList.getProject_name());
            Element resource_path = project.addElement("resource_path");
            resource_path.addText(fileList.getResource_path());
            //Element update_time=project.addElement("update_time");
            //update_time.addText(fileList.getUpdate_time());
            Element items = project.addElement("items");
            for (ResourceItem f : fileList.getItems()) {
                Element item = items.addElement("item");
                Element md5 = item.addElement("md5");
                md5.addText(f.getMd5());
                Element files = item.addElement("files");
                for (ResourceFile r : f.getResourceFile()) {
                    Element file = files.addElement("file");
                    Element srcPath = file.addElement("src_path");
                    srcPath.addText(r.getSrc_path());
                    Element type2 = file.addElement("type");
                    type2.addText(r.getType());

                }

            }
            persistenceXml(document, savePath);
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param name
     * @param prid
     * @param quipub
     * @return
     * @throws Exception
     */
    @Transactional
    public News deliver(String name, String prid, String quipub) throws Exception {
        //db operate
        //Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        News isExit = newsDao.getNewsInfoById(Integer.parseInt(prid));
        if (isExit != null) {
            isExit.setnTimeline(df.parse(df.format(new Date())));
            //isExit.setEditstate(GlobalParameter.STATE_PROJECT_SUBMIT);
//			isExit.setState(GlobalParameter.STATE_PROJECT_SUBMIT);
            //isExit.setCreator(operUser);
            // isExit.setUpdateuser(operUser);
            newsDao.updateNews(isExit);
        } else {
            isExit.setnTimeline(df.parse(df.format(new Date())));
            //News project = new News();
            //project.setnTimeline(df.parse(df.format(new Date())));
            // project.setUpdatetime(currentTime);
            //project.setId(Integer.parseInt(prid));
            //News pp=newsDao.getNewsInfoById(Integer.parseInt(prid));
            // project.setName(pp.getName());
            // project.setTitle(pp.getName());
            // System.out.println(123+"......................");
            // project.setEditstate(GlobalParameter.STATE_PROJECT_SUBMIT);
//			isExit.setState(GlobalParameter.STATE_PROJECT_SUBMIT);
            // project.setCreator(operUser);
            // project.setUpdateuser(operUser);
            //  projectDao.save(project);
            newsDao.updateNews(isExit);
        }
        // if(quipub.equals("1")){
        //     updateStateQ(name,prid,GlobalParameter.STATE_PROJECT_SUBMIT);
        // }else if(quipub.equals("0")){
        updateState(name, prid, GlobalParameter.STATE_PROJECT_SUBMIT);
        // }
        // update by lihuimin 返回值void-->Project 拿到新增节目的名称 pro.getName() begin
        News pro = newsDao.getNewsInfoById(Integer.parseInt(prid));
        return pro;
    }

    /**
     * todo 加注释
     * @param news
     */
    @Transactional
    public void saveNews(News news) {
        try {
            newsDao.updateNews(news);
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param program
     * @return
     */
    public boolean editProgram(String userName, Program program) {
        String path = SearchPathTools.getEditProgram(userName,
                program.getPrid(), true);
        modifyProgramXml(path, program);
        return true;
    }

    /**
     * todo 加注释
     * @param userName
     * @param page
     * @param scX
     * @param scY
     */
    public void updateRadio(String userName, com.honghe.recordweb.action.frontend.news.entity.Page page, Float scX, Float scY) {
        String path = SearchPathTools.getEditPage(userName, page.getPrid(), page.getPid(), false);
        try {
            File forlder = new File(path);
            File[] files = forlder.listFiles();

            if (files == null || files.length == 0) {
                return;
            }

            for (File file : files) {
                if (file.getName().startsWith("blk") && file.getName().endsWith(".xml")) {
                    Item item = itemService.parseItemXml(path + file.getName());
                    item.updateRadio(scX, scY);
                    itemService.writeItemXml(path + file.getName(), item);
                }
            }

        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param newsId
     * @return
     */
    public Map<String, Object> getNewsInfo(String newsId) {
        try {
            return newsDao.getNewsInfo(newsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * 根据设备Id删除所有消息，删除设备时用
     * @param hid
     * @param hostIp
     * @return
     */
    public boolean delNewsByHostService(int hid, String hostIp) {
        try {
            if (newsDao.isNewsHostExists(hid)) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                map.put("Content", "");
                map.put("NewsId", "");
                map.put("UserName", "");
                map.put("DisplayMode", "");
                map.put("IsLoop", "");
                map.put("CycleMode", "");
                map.put("ExecValue", "");
                map.put("StartDate", "");
                map.put("EndDate", "");
                map.put("StartTime", "");
                map.put("EndTime", "");
                map.put("FontSize", "");
                map.put("FontColor", "");
                map.put("Font", "");
                map.put("Control", "DeleteAll");
                map.put("Ntype", "");
                computerCommand.sendMessage(hostIp, map);//给设备发一通知，清空消息表
                newsDao.delNewsHostByHost(hid);
                return true;
            }
            return true;
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param news
     * @return
     */
    @Transactional
    public Boolean updateNews(News news) {
        try {
            newsDao.updateNews(news);
            return true;
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }
}