package com.honghe.recordweb.action.frontend.resource;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.resource.ResourceService;
import com.honghe.recordweb.service.frontend.resource.ResourceWebServiceTool;
import com.honghe.recordweb.service.frontend.video.VideoService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongbin
 * Date: 14-10-21
 * Time: 下午1:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope(value = "prototype")
public class ResourceAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(ResourceAction.class);
    @Resource
    private ResourceService resourceService;
    @Resource
    private VideoService videoService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private ResourceWebServiceTool resourceWebServiceTool;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private final String PAGE_SIZE = "6";
    private String userId;
    private String userName;
    private Page pageResource;
    private String currentPage = "1";
    private String orderCondition = "";
    private String searchCondition = "";
    private String resId;
    private String resTitle;
    private String resCourse;
    private String resSubject;
    private String resGrade;
    private String resSpeaker;
    private String resSchool;
    private String pageType;//为记录页面加载方式0,表示页面加载；1,表示搜索
    private String hostIp;
    private String beginDate;
    private String endDate;
    private String callback;
    private Map<String, String> resMapUpdate = new HashMap<>();
    private String recordType;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public Map<String, String> getResMapUpdate() {
        return resMapUpdate;
    }

    public void setResMapUpdate(Map<String, String> resMapUpdate) {
        this.resMapUpdate = resMapUpdate;
    }

    public Page getPageResource() {
        return pageResource;
    }

    public void setPageResource(Page pageResource) {
        this.pageResource = pageResource;
    }

    public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getResTitle() {
        return resTitle;
    }

    public void setResTitle(String resTitle) {
        this.resTitle = resTitle;
    }

    public String getResCourse() {
        return resCourse;
    }

    public void setResCourse(String resCourse) {
        this.resCourse = resCourse;
    }

    public String getResSubject() {
        return resSubject;
    }

    public void setResSubject(String resSubject) {
        this.resSubject = resSubject;
    }

    public String getResGrade() {
        return resGrade;
    }

    public void setResGrade(String resGrade) {
        this.resGrade = resGrade;
    }

    public String getResSpeaker() {
        return resSpeaker;
    }

    public void setResSpeaker(String resSpeaker) {
        this.resSpeaker = resSpeaker;
    }

    public String getResSchool() {
        return resSchool;
    }

    public void setResSchool(String resSchool) {
        this.resSchool = resSchool;
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

    public String getOrderCondition() {
        return orderCondition;
    }

    public void setOrderCondition(String orderCondition) {
        this.orderCondition = orderCondition;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    /**
     * 资源文件夹列表
     *
     * @return
     */
    public String resourceList() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        pageType = "0";
        pageResource = resourceService.getResourceByPage(PAGE_SIZE, currentPage, "", orderCondition);
        return "resourcelist";
    }

    /**
     * 资源文件夹列表,集成精品和爱录客
     *
     * @return
     */
    public String resourceListNew() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        pageType = "0";
        pageResource = resourceService.getResourceByPage(PAGE_SIZE, currentPage, "", orderCondition);
        /*if (user.getUser_salt().equals("true")) {*/
//            Map<String, Object> result = super.getTreeMap(0, CommonName.DEVICE_TYPE_RECOURD, hostgroupService);
            String groupTree = super.getTreeMap(user.getUserId(),"NVR");
            ServletActionContext.getRequest().setAttribute("groupTree", groupTree);
   /*         if (result.isEmpty()) {
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

            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(user.getUserId(), "hhrec"));
        }*/
        return "resourcelistnew";
    }

    /**
     * 根据录播主机IP获取资源文件夹列表
     *
     * @return
     */
    public String resourceListByIp() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        pageType = "0";
        try {
            searchCondition = URLDecoder.decode(searchCondition, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (beginDate != null && !beginDate.equals("")) {
                beginDate = DATE_FORMAT.format(DATE_FORMAT.parse(beginDate));
            }
        } catch (Exception e) {
            beginDate = null;
        }
        try {
            if (endDate != null && !endDate.equals("")) {
                endDate = DATE_FORMAT.format(DATE_FORMAT.parse(endDate));
            }
        } catch (Exception e) {
            endDate = null;
        }
        if (recordType != null && recordType.equals("1")) {
            //如果是精品录播主机，则调用webService，获取精品的数据
            Map<String, String> map = new HashMap<>();
            map.put("currentPage", currentPage);
            map.put("pageSize", PAGE_SIZE);
            map.put("hostIp", hostIp);
            map.put("str", searchCondition == null ? "" : searchCondition);
            map.put("order", orderCondition == null ? "" : orderCondition);
            map.put("beginDate", beginDate == null ? "" : beginDate);
            map.put("endDate", endDate == null ? "" : endDate);
            pageResource = resourceWebServiceTool.getResourceByPageWithIp(map);
        } else {
            pageResource = resourceService.getResourceByPageWithIp(PAGE_SIZE, currentPage, searchCondition, orderCondition, hostIp, beginDate, endDate);
        }
        return "resourcelistbyip";
    }
    /**
     * 资源文件夹列表搜索
     * @return
     */
   /* public String resourceSearchByIp()
    {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        pageType = "1";
        try {
            searchCondition = URLDecoder.decode(searchCondition,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       // pageResource = resourceService.getResourceByPage(PAGE_SIZE,currentPage,searchCondition,orderCondition);
        pageResource = resourceService.getResourceByPageWithIp(PAGE_SIZE,currentPage,searchCondition,orderCondition,hostIp);
        return "resourcesearchbyip";
    }*/

    /**
     * 资源文件夹列表
     *
     * @return
     */
    public String resourceSearch() {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        userName = user.getUserName();
        pageType = "1";
        try {
            searchCondition = URLDecoder.decode(searchCondition, "utf-8");
        } catch (UnsupportedEncodingException e) {
            //   e.printStackTrace();
        }
        if (recordType != null && recordType.equals("1")) {
            //如果是精品录播主机，则调用webService，获取精品的数据
            Map<String, String> map = new HashMap<>();
            map.put("currentPage", currentPage);
            map.put("pageSize", PAGE_SIZE);
            map.put("hostIp", hostIp);
            map.put("str", searchCondition == null ? "" : searchCondition);
            map.put("order", orderCondition == null ? "" : orderCondition);
            map.put("beginDate", beginDate == null ? "" : beginDate);
            map.put("endDate", endDate == null ? "" : endDate);
            pageResource = resourceWebServiceTool.getResourceByPageWithIp(map);
        } else {
            pageResource = resourceService.getResourceByPageWithIp(PAGE_SIZE, currentPage, searchCondition, orderCondition, hostIp, beginDate, endDate);
        }
        // pageResource = resourceService.getResourceByPage(PAGE_SIZE,currentPage,searchCondition,orderCondition);
        return "resourcesearch";
    }

    /**
     * 获取资源
     * @return
     */
    public String getResource() {
        resMapUpdate.clear();
        if (resId != null && !resId.equals("")) {
            if (recordType != null && recordType.equals("1")) {
                //如果是精品录播主机，则调用webService，获取精品的数据
                resMapUpdate = resourceWebServiceTool.getCourseInfo(hostIp, resId);
            } else {
                com.honghe.recordhibernate.entity.Resource res = resourceService.getResourceInfoById(resId);
                if (res != null) {
                    resMapUpdate.put("res_id", resId);
                    resMapUpdate.put("res_title", res.getResTitle());
                    resMapUpdate.put("res_grade", res.getResGrade());
                    resMapUpdate.put("res_course", res.getResCourse());
                    resMapUpdate.put("res_subject", res.getResSubject());
                    resMapUpdate.put("res_speaker", res.getResSpeaker());
                    resMapUpdate.put("res_school", res.getResSchool());
                }
            }
        }
        return "resourceupdate";
    }

    /**
     * 修改资源信息
     */
    public void updateResource() {
        if (resId != null && !resId.equals("")) {
            Map<String, String> map = new HashMap<>();
            map.put("res_title", resTitle);
            map.put("res_grade", resGrade);
            map.put("res_course", resCourse);
            map.put("res_subject", resSubject);
            map.put("res_speaker", resSpeaker);
            map.put("res_school", resSchool);
            map.put("res_id", resId);
            map.put("host_ip", hostIp);
            if (recordType != null && recordType.equals("1")) {
                //如果是精品录播主机，则调用webService，获取精品的数据
                if (resourceWebServiceTool.updateCourseInfo(map)) {
                    generateJSON(true, "修改成功！");
                } else {
                    generateJSON(false, "修改失败！");
                }
            } else {
                try {
                    if (resourceService.updateResource(resId, map)) {
                        generateJSON(true, "修改成功！");
                    } else {
                        generateJSON(false, "修改失败！");
                    }
                } catch (Exception e) {
                    logger.error("资源信息修改失败", e);
                    e.printStackTrace();
                    generateJSON(false, "修改失败！");
                }
            }
        } else {
            generateJSON(false, "未找到数据！");
        }
    }

    /**
     * 删除资源
     */
    public void deleteResource() {
        if (resId != null && !resId.trim().equals("")) {
            if (recordType != null && recordType.equals("1")) {
                //如果是精品录播主机，则调用webService，获取精品的数据
                if (resourceWebServiceTool.delResource(hostIp, resId)) {
                    generateJSON(true, "删除成功！");
                } else {
                    generateJSON(false, "删除失败！");
                }
            } else {
                if (resourceService.deleteResource(resId)) {
                    generateJSON(true, "删除成功！");
                } else {
                    generateJSON(false, "删除失败！");
                }
            }
        } else {
            generateJSON(false, "未找到数据！");
        }
    }

    /**
     * 给页面返回json数据
     *
     * @param isSuccess
     * @param msg
     */
    private void generateJSON(boolean isSuccess, String msg) {
        JSONObject json = new JSONObject();
        json.put("success", isSuccess);
        json.put("msg", msg);
        writeJSON(json.toString());
    }


}
