package com.honghe.recordweb.action.frontend.devicelog;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lch on 2015/1/14.
 */
@Controller //action注解
@Scope(value = "prototype")
public class DeviceLogAction extends BaseAction {

    @Resource
    private SyslogService deviceLogService;

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

    private List deviceLogtypeList;

    private String startTime; //开始时间
    private String endTime; //结束时间
    private String ip;//主机地址
    private String logType;//日志类型

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 获取日志
     * @return
     */
    public String deviceLogList() {
        //判断如果是ajax请求
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestType = request.getHeader("X-Requested-With");//todo 加注释 requestType
        Integer pageSize = 10;
        Page page = null;
        deviceLogList = null;

        pageCount = 0;
        int flag;
        HashMap<String, Integer> roleMap = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.ROLE);
        if (roleMap.containsKey("1")) {
            flag = 0;//开发人员
        } else {
            flag = 1;
        }
        deviceLogtypeList = deviceLogService.getLogtype(flag);
        if (requestType != null) {
            String stime = this.startTime;
            String ipaddr = this.ip;
            if (stime == null && ipaddr == null) {//首页的ajax分页不是点击搜索按钮的
                page = deviceLogService.getAllDeviceLogList(pageSize, Integer.parseInt(currentPage), flag);
            } else { //点击搜索按钮的 ajax请求分页
                Integer startTime = Integer.parseInt(this.startTime);
                Integer endTime = Integer.parseInt(this.endTime);
                String logType = this.logType;
                String ip = this.ip;
                if (startTime.equals(0) && endTime.equals(0)) {
                    page = deviceLogService.devicelogListByTypeAndIp(logType, ip, pageSize, Integer.parseInt(currentPage), flag);
                } else if (!startTime.equals(0) && !endTime.equals(0)) {
                    page = deviceLogService.devicelogListByTimeAndIP(startTime, endTime, logType, ip, pageSize, Integer.parseInt(currentPage), flag);
                }
            }
            if (page != null) {
                deviceLogList = page.getResult();
                pageCount = page.getTotalPageSize();
            }
            return "deviceloglist";
        } else {
            page = deviceLogService.getAllDeviceLogList(pageSize, Integer.parseInt(currentPage), flag);
            if (page != null) {
                deviceLogList = page.getResult();
                pageCount = page.getTotalPageSize();
            }
            return "devicelog";
        }
    }
}
