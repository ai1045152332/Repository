package com.honghe.recordweb.action.frontend.news;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.News;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.action.frontend.news.entity.Program;
import com.honghe.recordweb.action.frontend.news.form.ProjectPageForm;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.news.NewsService;
import com.honghe.recordweb.service.frontend.news.PageService;
import com.honghe.recordweb.service.frontend.news.SearchPathTools;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.RoleUtil;
import com.honghe.recordweb.util.base.util.GlobalFlag;
import com.honghe.recordweb.util.base.util.GlobalParameter;
import com.honghe.recordweb.util.base.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hthwx on 2015/2/27.
 */
@Controller
@Scope(value = "prototype")
public class NewsAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(NewsAction.class);
    @Resource
    private NewsService newsService;
    @Resource
    private PageService pageService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private UserService userService;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    private String PAGE_SIZE = "5";
    private String currentPage = "1";
    private String userId;
    private String userName;
    private Page pageNews;
    private String newsCont;
    private String newsShowType;
    private String newsFont;
    private String newsFontSize;
    private String newsFontColor;
    private String newsBeginDate;
    private String newsEndDate;
    private String newsStartTime;
    private String newsFinishTime;
    private String newsPolicy;
    private String newsWeek;
    private String newsMonth;
    private String newsId;
    private String hostIdsStr;//发送信息时选中的设备id字符串集合
    private String prid;
    private String w; //todo 加注释
    private final String HOST_ID_FLAG = ",";
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    private String uuid;//todo 加注释
    private String flag;//todo 加注释
    private String quipub;//todo 加注释
    private String publishflag;//todo 加注释

    public String getPublishflag() {
        return publishflag;
    }

    public void setPublishflag(String publishflag) {
        this.publishflag = publishflag;
    }

    public String getQuipub() {
        return quipub;
    }

    public void setQuipub(String quipub) {
        this.quipub = quipub;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    private String h;
    public Page getPageNews() {
        return pageNews;
    }

    public void setPageNews(Page pageNews) {
        this.pageNews = pageNews;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPAGE_SIZE() {
        return PAGE_SIZE;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsMonth() {
        return newsMonth;
    }

    public void setNewsMonth(String newsMonth) {
        this.newsMonth = newsMonth;
    }

    public String getNewsWeek() {
        return newsWeek;
    }

    public void setNewsWeek(String newsWeek) {
        this.newsWeek = newsWeek;
    }

    public String getNewsPolicy() {
        return newsPolicy;
    }

    public void setNewsPolicy(String newsPolicy) {
        this.newsPolicy = newsPolicy;
    }

    public String getNewsFinishTime() {
        return newsFinishTime;
    }

    public void setNewsFinishTime(String newsFinishTime) {
        this.newsFinishTime = newsFinishTime;
    }

    public String getNewsStartTime() {
        return newsStartTime;
    }

    public void setNewsStartTime(String newsStartTime) {
        this.newsStartTime = newsStartTime;
    }

    public String getNewsEndDate() {
        return newsEndDate;
    }

    public void setNewsEndDate(String newsEndDate) {
        this.newsEndDate = newsEndDate;
    }

    public String getNewsBeginDate() {
        return newsBeginDate;
    }

    public void setNewsBeginDate(String newsBeginDate) {
        this.newsBeginDate = newsBeginDate;
    }

    public String getNewsFontColor() {
        return newsFontColor;
    }

    public void setNewsFontColor(String newsFontColor) {
        this.newsFontColor = newsFontColor;
    }

    public String getNewsFontSize() {
        return newsFontSize;
    }

    public void setNewsFontSize(String newsFontSize) {
        this.newsFontSize = newsFontSize;
    }

    public String getNewsFont() {
        return newsFont;
    }

    public void setNewsFont(String newsFont) {
        this.newsFont = newsFont;
    }

    public String getNewsShowType() {
        return newsShowType;
    }

    public void setNewsShowType(String newsShowType) {
        this.newsShowType = newsShowType;
    }

    public String getNewsCont() {
        return newsCont;
    }

    public void setNewsCont(String newsCont) {
        this.newsCont = newsCont;
    }

    public String getHOST_ID_FLAG() {
        return HOST_ID_FLAG;
    }

    public String getHostIdsStr() {
        return hostIdsStr;
    }

    public void setHostIdsStr(String hostIdsStr) {
        this.hostIdsStr = hostIdsStr;
    }
    private String newsType;

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }




    /**
     * news首页显示，获取分组tree信息，信息分页列表
     *
     * @return
     */
    public void appNewsManage() {
        if (app_source()) {
            Map<String, Object> hashmap = new HashMap<String, Object>();
            int uid = Integer.parseInt(this.userId);
            String pagesize = ServletActionContext.getRequest().getParameter("pagesize");
            hashmap.put("messageList", newsService.appGetNewsPageListByUser(pagesize, currentPage, uid, "hhtc", "0", "hhtc"));
            if (userService.getRoleByUserid(uid) == 1) {
                uid = 0;
            }
            hashmap.put("groupTree", hostgroupService.getGroupService(uid, "hhtc"));
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * news首页显示，获取分组tree信息，信息分页列表
     *
     * @return
     */
    public String newsManage() {
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        String newsIdUpdate = this.newsIdUpdate;
        //获取分组信息
        HashMap<String, Integer> roleMap = new HashMap<>();
        Integer userId = user.getUserId();
        Map<String, String> roleName = userService.getRoleByUid(userId.toString());
        final String type = deviceType;
        userId = RoleUtil.RoleToPermission(userId, roleName);

        if (userId == 0) {
            roleMap.put("1", 1);
        }
        pageNews = newsService.getNewsPageListByUser(PAGE_SIZE, currentPage, userId.toString(), roleMap, type, "0", deviceType);
        if (Integer.parseInt(currentPage) > pageNews.getTotalPageSize()) {
            currentPage = pageNews.getTotalPageSize() + "";
        }
        if (userId == 0) {
            final Integer uid = userId;
            Map<String, Object> result = super.getTreeMap(uid, type, hostgroupService);
            if (result.isEmpty()) {
                ActionContext.getContext().put("", Collections.EMPTY_LIST);
            } else {
                List<Map> groupTree = (List<Map>) result.get("groupTree");
                Map unknowGroup = (Map) result.get("unknowGroup");
                List<Map> hostList = (List<Map>) unknowGroup.get("host_list");
                if (!hostList.isEmpty()) {
                    groupTree.add(0, unknowGroup);
                }
                ActionContext.getContext().put("groupTree", groupTree);
            }
        } else {

            // User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(user.getUserId(), type));
        }
        return "newsmanage";
    }

    /**
     * 发布信息
     */
    public void addNews() {
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        Map<String, String> newsInfoMap = new LinkedHashMap<String, String>();
        if (app_source()) {
//            userId = this.userId;
//            userName = this.userName;
        } else {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            userId = user.getUserId().toString();
            userName = user.getUserName();
        }
        JSONObject json = new JSONObject();
        try {
            newsCont = URLDecoder.decode(newsCont, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //     e.printStackTrace();
        }
        newsInfoMap.put("n_cont", newsCont);
        newsInfoMap.put("uid", userId);
        newsInfoMap.put("username", userName);
        newsInfoMap.put("n_showtype", newsShowType);
        newsInfoMap.put("n_font", newsFont);
        newsInfoMap.put("n_fontsize", newsFontSize);
        newsInfoMap.put("n_fontcolor", newsFontColor);
        newsInfoMap.put("n_begintime", newsBeginDate);
        newsInfoMap.put("n_endtime", newsEndDate);
        newsInfoMap.put("n_loop", newsPolicy);
        newsInfoMap.put("n_start", newsStartTime);
        newsInfoMap.put("n_finish", newsFinishTime);
        newsInfoMap.put("n_week", newsWeek);
        newsInfoMap.put("n_month", newsMonth);
        newsInfoMap.put("n_type", "0");
        newsInfoMap.put("n_devicetype", deviceType);
        List<String> hostList = new LinkedList<String>();
        if (!hostIdsStr.trim().equals("")) {
            String[] arrTmp = hostIdsStr.split(HOST_ID_FLAG);
            if (arrTmp != null && arrTmp.length > 0) {
                for (String hostId : arrTmp) {
                    hostList.add(hostId);
                }
                if (newsService.sendNews(newsInfoMap, hostList, newsId)) {
                    generateJSON(true, "发布成功");
                }
            } else {
                generateJSON(false, "未选择设备，信息发布失败！");
            }
        } else {
            generateJSON(false, "未选择设备，信息发布失败！");
        }
    }


    /**
     * 删除信息
     */
    public void delNews() {
        HashMap<String, Integer> roleMap = new HashMap<>();
        String newsType = "0";
        if (app_source()) {
//            userId = ServletActionContext.getRequest().getParameter("userId");
            //userName = ServletActionContext.getRequest().getParameter("userName");
            User user = userService.getUserinfoByIdService(Integer.parseInt(userId));
            roleMap = userService.getUserRoles(user);
        } else {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            if (user == null) user = new User();
            int uid = userService.getAuthorityValueByUserid(user.getUserId());
            userId = user.getUserId().toString();
            newsType = this.newsType == null ? "0" : this.newsType;
            if (0 == uid) {
                roleMap.put("1", 1);
            } else {
                roleMap.put("0", 0);
            }
        }

        if (newsId != null && !newsId.equals("")) {
            if (newsService.delNews(newsId, userId, roleMap, newsType)) {
                generateJSON(true, "删除成功！");
            } else {
                generateJSON(false, "删除失败！");
            }
        } else {
            generateJSON(false, "未选择需要删除的信息！");
        }
    }

    /**
     * 更新信息
     */
    public void updateNews() {
        if (newsId != null && !newsId.equals("")) {
            News newInfo = newsService.getNewsInfoById(newsId);
            //   List<Object[]> hostList = newsService.getHost2NewsById(newsId);
            Map<String, Object> hostsMap = newsService.getHost2NewsById(newsId);
            if (newInfo != null) {
                JSONObject json = new JSONObject();
                json.put("cont", newInfo.getnCont());
                json.put("beginDate", newInfo.getnBegintime() != null ? DATE_FORMAT.format(newInfo.getnBegintime()) : "");
                json.put("endDate", newInfo.getnEndtime() != null ? DATE_FORMAT.format(newInfo.getnEndtime()) : "");
                json.put("showType", newInfo.getnShowtype());
                json.put("font", newInfo.getnFont() != null ? NewsService.Font.valueOf(newInfo.getnFont()) : "");
                json.put("fontSize", newInfo.getnFontsize());
                json.put("fontColor", newInfo.getnFontcolor() != null ? newInfo.getnFontcolor().toString().replace("#", "") : "");
                json.put("loop", newInfo.getnLoop());
                json.put("week", newInfo.getnWeek());
                json.put("month", newInfo.getnMonth());
                json.put("start", newInfo.getnStart());
                json.put("finish", newInfo.getnFinish());
                if (hostsMap != null) {
                    json.put("hostIds", hostsMap.get("hostIds"));
                } else {
                    json.put("hostIds", null);
                }
                writeJSON(json.toString());
            } else {
                generateJSON(false, "未找到数据！");
            }
        } else {
            generateJSON(false, "未找到数据！");
        }
    }

    /**
     * //todo 加注释
     * @param isSuccess
     * @param msg
     */
    private void generateJSON(boolean isSuccess, String msg) {
        JSONObject json = new JSONObject();
        json.put("success", isSuccess);
        json.put("msg", msg);
        writeJSON(json.toString());
    }


    /**
     * //todo 加注释
     */
    public void select() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId() + "";
        JSONObject programJson = new JSONObject();
        try {
            //Program xmlProgram = newsService.queryProgram(user.getUserId()+"", programId);
            programJson.put("id", prid);
//			programJson.put("ratio", xmlProgram.getRatio());
            String currentPage = "1";
            //programJson.put("curIdx", xmlProgram.getCurIdx());
            programJson.put("curIdx", currentPage);
            //programJson.put("w", xmlProgram.getW());
            programJson.put("w", 1920);
            //programJson.put("h", xmlProgram.getH());
            programJson.put("h", 1080);
            List<String> pageList = newsService.queryPage(userId, prid);
            HttpServletRequest request = ServletActionContext.getRequest();
            String href = request.getServletPath().toString();
            if (href.indexOf("http://") == -1) {
                href = "http://" + href;
            }
            List<Map<String, String>> figueList = new ArrayList<Map<String, String>>();
            for (String pageId : pageList) {
                //String path = SearchPathTools.getEditFirstPageThumb(userId, programId, pageId, true)+"?random="+Math.random();
                ProjectPageForm pageForm = new ProjectPageForm();
                pageForm.setPrid(prid);
                pageForm.setPid(pageId);
                com.honghe.recordweb.action.frontend.news.entity.Page page = newsService.getPageInfo(userId, pageForm);
                Map<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("pid", pageId);

                //String[] s1 = this.splip(href.toString());
                //String[] s2 = this.splip(path);
                //s2[0] = s1[0];
                //path = "http://"+s2[0]+"/"+s2[1];

                hashMap.put("path", "");
                hashMap.put("time", page.getTime());
                figueList.add(hashMap);
            }

            programJson.put("page", figueList);
        } catch (Exception e) {
            logger.error("", e);
            //     e.printStackTrace();
        }
        sendMsg(programJson.toString());
    }

    /**
     * todo 加注释
     * @param url
     * @return
     */
    public static String[] splip(String url) {
        String strs[] = new String[2];
        String regex = "//(.*?)/(.*)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        while (m.find()) {
            strs[0] = m.group(1);
            strs[1] = m.group(2);
        }
        return strs;
    }

    /**
     * todo 加注释
     */
    public void add() {
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        int ntype = 1;
        newsId = newsService.add(Integer.parseInt(userId), ntype, userName, deviceType) + "";
        String rootpath = ServletActionContext.getServletContext().getRealPath("/msgResource");
        String newPath = rootpath + "/" + user.getUserId() + "/" + newsId + "/";
        File file = new File(newPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String w = "1920";
        String h = "1080";
        String filePath = SearchPathTools.getEditProgram(user.getUserId() + "", newsId, true);
        newsService.writeProgramXml(filePath, w, h, userName);
        String firstPagePath = SearchPathTools.getEditPage(user.getUserId() + "",
                newsId + "", "1", false);
        addPage(firstPagePath);

        //return newsId;
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            HttpServletRequest request = ServletActionContext.getRequest();
            response.sendRedirect(request.getContextPath() + "/pages/frontend/news/editor/index.jsp?prid=" + newsId + "&w=" + w + "&h=" + h);
        } catch (Exception e) {
            logger.error("", e);
            //   e.printStackTrace();
        }
        //sendMsg("" + newsId);
    }

    //    public void textNews(){
//        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
//        userId = user.getUserId().toString();
//        userName = user.getUserName();
//        int ntype = 1;
//        newsId = newsService.add(Integer.parseInt(userId),ntype,userName)+"";
//        String rootpath = ServletActionContext.getServletContext().getRealPath("/msgResource");
//        String newPath  = rootpath+"/"+user.getUserId()+"/"+newsId+"/";
//        File file = new File(newPath);
//        if(!file.exists()){
//            file.mkdirs();
//        }
//        String filePath = SearchPathTools.getEditProgram(user.getUserId()+"", newsId, true);
//        newsService.writeProgramXml(filePath);
//        String firstPagePath = SearchPathTools.getEditPage(user.getUserId() + "",
//                newsId + "", "1", false);
//        addPage(firstPagePath);
//        try {
//            HttpServletResponse response = ServletActionContext.getResponse();
//            HttpServletRequest request = ServletActionContext.getRequest();
//            response.sendRedirect(request.getContextPath() + "/pages/frontend/news/editor/index.jsp?prid=" + newsId);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     * //todo 加注释
     * @param path
     * @return
     */
    private boolean addPage(String path) {
        File desFile = new File(path);
        desFile.mkdir();

        com.honghe.recordweb.action.frontend.news.entity.Page page = new com.honghe.recordweb.action.frontend.news.entity.Page();
        page.setPid("1");//todo 加注释
        page.setIndex("1");//todo 加注释
        page.setTime(GlobalParameter.DEFAULT_PROJECT_PAGE_DURATION + "");
        pageService.writePageXml(path + "/page.xml", page);
        return true;
    }


    /**
     * todo 加注释
     *
     */
    public synchronized void publishbebusy() {
        // HttpServletRequest request = ServletActionContext.getRequest();
        String publishflag = this.publishflag;
        String prid = publishflag.split("@")[0];
        String quipub = this.quipub;
        if (quipub != null && quipub.equals("1")) {
            if (GlobalFlag.getPublishQ(prid).equals("")) {
                sendMsg("ok");
            } else if (!GlobalFlag.getPublishQ(prid).equals("") && GlobalFlag.getPublishQ(prid).equals("start")) {
                sendMsg("busy");
            }
        } else {
            if (GlobalFlag.getPublish(prid).equals("")) {
                sendMsg("ok");
            } else if (!GlobalFlag.getPublish(prid).equals("") && GlobalFlag.getPublish(prid).equals("start")) {
                sendMsg("busy");
            }
        }
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public void checkProState() throws Exception {
        //String checkFlag=newsService.checkProSubState();
        String checkFlag = "false";
        //boolean b = sysManager.verifyProgramStatus();节目审核是否开启
        boolean b = true;
        boolean isCampus = GlobalParameter.isCampusVersion();
        sendMsg(checkFlag + "@" + b + "@" + isCampus);
    }
    /**
     * todo 加注释
     *
     */
    public void loadBtn() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        String path = SearchPathTools.getUserInfo(userId);
        String btnList = newsService.loadUserIndo(path);
        sendMsg(btnList);
    }

    private String btns;

    public String getBtns() {
        return btns;
    }

    public void setBtns(String btns) {
        this.btns = btns;
    }
    /**
     * todo 加注释
     *
     */
    public void saveBtn() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        String path = SearchPathTools.getUserInfo(userId);
        String btnList = btns;
        newsService.saveUserInfo(path, btnList);
    }



    /**
     * todo 加注释
     * @throws Exception
     */
    public synchronized void checkBeforePublish() throws Exception {

        String publishflag = this.publishflag;
        String prid = publishflag.split("@")[0];
        //String quipub =request.getParameter("quipub");
        String quipub = "0";
        // 遍历该节目下的所有page页
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        String cs = newsService.checkBeforePublish(userId, prid, quipub);
        sendMsg(cs);
    }



    /**
     * todo 加注释
     * @throws Exception
     */
    public void controlState() throws Exception {
        String uuid = this.uuid;
        String flag = this.flag;
        if ("add".equals(flag)) {
            GlobalFlag.push(uuid, "process");
        } else if ("remove".equals(flag)) {
            GlobalFlag.pop(uuid);
        }
    }
    /**
     * todo 加注释
     * @throws Exception
     */
    public void queryState() throws Exception {

        String uuid = this.uuid;
        if (GlobalFlag.getVal(uuid) != null) {
            if (GlobalFlag.getVal(uuid).contains("process")) {
                sendMsg("process");
            } else {
                sendMsg(GlobalFlag.getVal(uuid));
                logger.debug(GlobalFlag.getVal(uuid));
                /*GlobalFlag.pop(uuid);*/
            }
        }
    }
    /**
     * todo 加注释
     */
    public void publish() {
        try {
            String uuid = this.uuid;
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            userId = user.getUserId().toString();
            String programId = prid;
            programId = StringUtil.getUtf8Str(programId);
            GlobalFlag.publishPush(programId);
            News news = newsService.getNewsById(Integer.parseInt(programId));
            //Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            news.setnTimeline(df.parse(df.format(new Date())));
            newsService.saveNews(news);
            try {
                //清空未用到的md5文件夹
                newsService.removeFolder(userId, programId, "0");
                //end

                //往要发布的节目中写入下载文件列表 add-by-dx
                newsService.writeFileList(userId, programId, "0");
                //end
            } catch (Exception e) {
                logger.error("", e);
                //    e.printStackTrace();
            }
            News pro = null;
            if (true) {
                // update by lihuimin 返回值void-->Project 拿到新增节目的名称 pro.getName() begin
                pro = newsService.deliver(userId, programId, "0");
            }
            //若是审核功能关闭则将节目直接进行发布，状态直接置为publish状态
            //if (!sysManager.verifyProgramStatus()) { // sysManager.verifyProgramStatus() = false  关闭审核
            newsService.publish(userId, programId, "0");
            // 因为节目名称是可以重复的 所以要获取所要发布节目的ID update by lihuimin 20141020
            GlobalFlag.change(uuid, pro.getnId() + "");
            GlobalFlag.publishPop(programId);
            sendMsg(programId + "@CLOSE" + uuid);
            //} else if (sysManager.verifyProgramStatus()) {// sysManager.verifyProgramStatus() = true  打开审核
            //生成预览以及压缩
            // implPublish.unVerifyPackage(userName, programId);
            //GlobalFlag.change(uuid, programId);
            // GlobalFlag.publishPop(programId);
            //sendMsg(programId + "@OPEN" + uuid);
            //}
        } catch (Exception e) {
            logger.error("", e);
            // e.printStackTrace();
        }
    }
    /**
     * todo 加注释
     */
    public void updateRadio() {
        try {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            userId = user.getUserId().toString();
            Program program = newsService.queryProgram(userId, prid);
            program.setPrid(prid);
            int ow = Integer.parseInt(program.getW());
            int oh = Integer.parseInt(program.getH());
            program.setH(h);
            program.setW(w);
            newsService.editProgram(userId, program);
            float scX = Float.parseFloat(w) / ow;
            float scY = Float.parseFloat(h) / oh;
            List<String> pageList = newsService.queryPage(userId, prid);
            for (String pageId : pageList) {
                com.honghe.recordweb.action.frontend.news.entity.Page page = newsService.getPageInfo(userId, prid, pageId);
                newsService.updateRadio(userId, page, scX, scY);
            }
            sendMsg("true");
        } catch (Exception e) {
            logger.error("", e);
            // e.printStackTrace();
        }
    }


    /**
     * 跳到发送富文本消息页面
     *
     * @return
     */
    public String selectPolicy() {
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        String newsId = this.prid;
        Map<String, Object> newsInfo = newsService.getNewsInfo(newsId);
        ActionContext.getContext().put("newsInfo", newsInfo);
        //获取分组信息
        HashMap<String, Integer> roleMap = new HashMap<>();
        Integer userId = user.getUserId();
        Map<String, String> roleName = userService.getRoleByUid(userId.toString());
        final String type = deviceType;
        userId = RoleUtil.RoleToPermission(userId, roleName);
        if (userId == 0) {
            roleMap.put("1", 1);
        }
        if (userId == 0) {
            final Integer uid = userId;
            Map<String, Object> result = super.getTreeMap(uid, type, hostgroupService);
            if (result.isEmpty()) {
                ActionContext.getContext().put("", Collections.EMPTY_LIST);
            } else {
                List<Map> groupTree = (List<Map>) result.get("groupTree");
                Map unknowGroup = (Map) result.get("unknowGroup");
                List<Map> hostList = (List<Map>) unknowGroup.get("host_list");
                if (!hostList.isEmpty()) {
                    groupTree.add(0, unknowGroup);
                }
                ActionContext.getContext().put("groupTree", groupTree);
            }
        } else {
            // User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(user.getUserId(), type));
        }
        return "selectPolicy";
    }

    private String newsIdUpdate;

    public String getNewsIdUpdate() {
        return newsIdUpdate;
    }

    public void setNewsIdUpdate(String newsIdUpdate) {
        this.newsIdUpdate = newsIdUpdate;
    }
    /**
     * todo 加注释
     */
    public String richNewsList() {
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        int uid = user.getUserId();
        userName = user.getUserName();
        String newsIdUpdate = this.newsIdUpdate;
        //获取分组信息
        HashMap<String, Integer> roleMap = new HashMap<>();
        Map<String, String> roleName = userService.getRoleByUid(userId);
        final String type = deviceType;
        uid = RoleUtil.RoleToPermission(uid, roleName);

        if (uid == 0) {
            roleMap.put("1", 1);
        }
        PAGE_SIZE = "10";
        //已经发布的消息
        Page publishNewsList = newsService.getNewsPageListByUser(PAGE_SIZE, currentPage, userId, roleMap, type, "1", deviceType);
        //当前用户制作的消息
        //Page newsList = newsService.getNewsList(userId,"1",PAGE_SIZE,currentPage);
        ActionContext.getContext().put("publishNewsList", publishNewsList);
        //ActionContext.getContext().put("newsList", newsList);
        return "richNewsList";
    }
    /**
     * todo 加注释
     */
    public void publishNews() {
        Map<String, String> newsInfoMap = new LinkedHashMap<String, String>();
        if (app_source()) {
//            userId = ServletActionContext.getRequest().getParameter("userId");
//            userName = ServletActionContext.getRequest().getParameter("userName");
        } else {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            userId = user.getUserId().toString();
            userName = user.getUserName();
        }
        //String ip = System.getProperty("NetworkInterface");
        // String ip = hostgroupService.getIp();
        String ip = ServletActionContext.getRequest().getLocalAddr();
        newsCont = "http://" + ip + ":8080/msgResource/publish/" + newsId + "/preview/project.html";
        newsInfoMap.put("n_cont", newsCont);
        newsInfoMap.put("uid", userId);
        newsInfoMap.put("n_begintime", newsBeginDate);
        newsInfoMap.put("n_endtime", newsEndDate);
        newsInfoMap.put("n_loop", newsPolicy);
        newsInfoMap.put("n_start", newsStartTime);
        newsInfoMap.put("n_finish", newsFinishTime);
        newsInfoMap.put("n_week", newsWeek);
        newsInfoMap.put("n_month", newsMonth);
        newsInfoMap.put("n_type", "1");
        newsInfoMap.put("n_status", "1");
        List<String> hostList = new LinkedList<String>();
        if (!hostIdsStr.trim().equals("")) {
            String[] arrTmp = hostIdsStr.split(HOST_ID_FLAG);
            if (arrTmp != null && arrTmp.length > 0) {
                for (String hostId : arrTmp) {
                    hostList.add(hostId);
                }
                if (newsService.sendNews(newsInfoMap, hostList, newsId)) {
                    generateJSON(true, "发布成功");
                }
            } else {
                generateJSON(false, "未选择设备，信息发布失败！");
            }
        } else {
            generateJSON(false, "未选择设备，信息发布失败！");
        }
    }
    /**
     * todo 加注释
     */
    public String myRichNews() {
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        PAGE_SIZE = "10";
        //当前用户制作的消息
        Page newsList = newsService.getNewsList(userId, "1", PAGE_SIZE, currentPage, deviceType);
        ActionContext.getContext().put("newsList", newsList);
        return "myrichnews";
    }
}
