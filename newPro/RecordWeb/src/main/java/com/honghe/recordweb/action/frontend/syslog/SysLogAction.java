package com.honghe.recordweb.action.frontend.syslog;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.user.UserService;
import net.sf.json.JSONSerializer;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lichunming on 2015/2/28.
 */
@Controller //action注解
@Scope(value = "prototype")
public class SysLogAction extends BaseAction {
    @Resource
    private SyslogService syslogService;
    @Resource
    private UserService userService;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    private int pageCount;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    private String currentPage = "1";

    public List getDeviceLogList() {
        return deviceLogList;
    }

    public void setDeviceLogList(List deviceLogList) {
        this.deviceLogList = deviceLogList;
    }

    private List deviceLogList;

    public List getDeviceLogtypeList() {
        return deviceLogtypeList;
    }

    public void setDeviceLogtypeList(List deviceLogtypeList) {
        this.deviceLogtypeList = deviceLogtypeList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String flag;
    private List deviceLogtypeList;
    private String userId; //用户ID
    private String startTime; //开始时间
    private String endTime; //结束时间
    private String logType; //日志类型
    private String pagesize; //每页显示条目

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    /**
     * 获取日志列表
     * @return
     */
    public String deviceLogList() {
        //判断如果是ajax请求
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestType = request.getHeader("X-Requested-With");
        Integer pageSize = 10;
        Page page = null;
        deviceLogList = null;

        pageCount = 0;
        int tag;
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        //HashMap<String, Integer> roleMap = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.ROLE);
        if (user.getUserName().equals("admin")) {
            tag = 0;//开发人员
        } else {
            tag = 1;
        }
        deviceLogtypeList = syslogService.getLogtype(tag);
        if (requestType != null) {
            String stime = this.startTime;
            String ipaddr = ServletActionContext.getRequest().getParameter("ip");
            if (stime == null && ipaddr == null) {//首页的ajax分页不是点击搜索按钮的
                page = syslogService.getAllDeviceLogList(pageSize, Integer.parseInt(currentPage), tag);
            } else { //点击搜索按钮的 ajax请求分页
                Integer startTime = Integer.parseInt(this.startTime);
                Integer endTime = Integer.parseInt(this.endTime);
                String logType = this.logType;
                String ip = ServletActionContext.getRequest().getParameter("ip");
                if (startTime.equals(0) && endTime.equals(0)) {
                    page = syslogService.devicelogListByTypeAndIp(logType, ip, pageSize, Integer.parseInt(currentPage), tag);
                } else if (!startTime.equals(0) && !endTime.equals(0)) {
                    page = syslogService.devicelogListByTimeAndIP(startTime, endTime, logType, ip, pageSize, Integer.parseInt(currentPage), tag);
                }
            }
            if (page != null) {
                deviceLogList = page.getResult();
                pageCount = page.getTotalPageSize();
            }
            return "deviceloglist";
        } else {
            page = syslogService.getAllDeviceLogList(pageSize, Integer.parseInt(currentPage), tag);
            if (page != null) {
                deviceLogList = page.getResult();
                pageCount = page.getTotalPageSize();
            }
            return "devicelog";
        }
    }

    /**
     * app提供loglist接口
     */
    public void appDeviceLogList() {
        if (app_source()) {
            int flag;
            int userId = Integer.parseInt(this.userId);
            Map<String, String> roleName = userService.getRoleByUid(String.valueOf(userId));
            //int roleid = userService.getRoleByUserid(userid);
            if (userId == 1 || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
                flag = 0;
            } else {
                flag = 1;
            }
            // deviceLogtypeList = syslogService.getLogtype(flag);
            Page page = null;
            String starttime = this.startTime;
            Integer startTime = 0;
            if (starttime != null && !"".equals(starttime)) {
                startTime = Integer.parseInt(starttime);
            }
            String endtime = this.endTime;
            Integer endTime = 0;
            if (endtime != null && !"".equals(endtime)) {
                endTime = Integer.parseInt(endtime);
            }
            String logType = this.logType;
            if (logType == null || "".equals(logType)) {
                logType = "ALL";
            }
            String ip = ServletActionContext.getRequest().getParameter("Ip");
            int pageSize = Integer.parseInt(this.pagesize);
            if (startTime == 0 && (ip == null || "".equals(ip)) && "ALL".equals(logType)) {
                if ("1".equals(currentPage)) {
                    page = syslogService.getAllDeviceLogList(pageSize, Integer.parseInt(currentPage), flag);
                    deviceLogList = page.getResult();
                } else {
                    deviceLogList = syslogService.getAllLogListById(pageSize, Integer.parseInt(currentPage), flag);
                }
            } else if (startTime == 0 && endTime == 0) {
                if ("1".equals(currentPage)) {
                    page = syslogService.devicelogListByTypeAndIp(logType, ip, pageSize, Integer.parseInt(currentPage), flag);
                    deviceLogList = page.getResult();
                } else {
                    deviceLogList = syslogService.getAllLogListtByTypeAndIp(logType, ip, pageSize, Integer.parseInt(currentPage), flag);
                }
            } else if (startTime > 0 && endTime > 0) {
                if ("1".equals(currentPage)) {
                    page = syslogService.devicelogListByTimeAndIP(startTime, endTime, logType, ip, pageSize, Integer.parseInt(currentPage), flag);
                    deviceLogList = page.getResult();
                } else {
                    deviceLogList = syslogService.getAllLogListtByTimeAndIp(startTime, endTime, logType, ip, pageSize, Integer.parseInt(currentPage), flag);
                }
            }
            //  pageCount = page.getTotalPageSize();
            Map<String, Object> hashmap = new HashMap<String, Object>();
            //  hashmap.put("pageCount", pageCount);
            if (deviceLogList == null) {
                deviceLogList = Collections.EMPTY_LIST;
            }
            hashmap.put("deviceLogList", deviceLogList);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    //    //app获取所有日志
//    public void appAllLogList(){
//        int flag;
//        int userId = Integer.valueOf(ServletActionContext.getRequest().getParameter("userId"));
//        Map<String,String> roleName = userService.getRoleByUid(String.valueOf(userId));
//        //int roleid = userService.getRoleByUserid(userid);
//        Page page=null ;
//        if(userId ==1 || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())){
//            flag = 0;
//        }else{
//            flag=1;
//        }
//       // deviceLogtypeList = syslogService.getLogtype(flag);
//        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("pagesize"));
//        if("1".equals(currentPage)) {
//            page = syslogService.getAllDeviceLogList(pageSize, Integer.parseInt(currentPage), flag);
//            deviceLogList = page.getResult();
//         //   pageCount = page.getTotalPageSize();
//        }else{
//            deviceLogList = syslogService.getAllLogListById(pageSize,Integer.parseInt(currentPage),flag);
//        }
//        Map<String, Object> hashmap = new HashMap<String, Object>();
//     //   hashmap.put("deviceLogtypeList", deviceLogtypeList);
//     //   hashmap.put("pageCount", pageCount);
//        hashmap.put("deviceLogList", deviceLogList);
//        writeJSON(JSONSerializer.toJSON(hashmap).toString());
//    }

    /**
     * app获取所有日志类型
     */
    public void appGetLogtypeList() {
        int flag;
        int userId = Integer.valueOf(this.userId);
        Map<String, String> roleName = userService.getRoleByUid(String.valueOf(userId));
        //int roleid = userService.getRoleByUserid(userid);
        Page page = null;
        if (userId == 1 || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
            flag = 0;
        } else {
            flag = 1;
        }
        deviceLogtypeList = syslogService.getLogtype(flag);
        Map<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("deviceLogtypeList", deviceLogtypeList);
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }
}

