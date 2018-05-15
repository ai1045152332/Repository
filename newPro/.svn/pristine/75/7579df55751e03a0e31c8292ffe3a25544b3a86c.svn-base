package com.honghe.recordweb.action.frontend.monitor;

import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.Monitor;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.deviceuse.DeviceuseService;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.monitor.MonitorService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yanxue on 2015-07-07.
 * <p/>
 * 监控软件统计
 */
@Controller
@Scope(value = "prototype")
public class MonitorAction extends BaseAction {
    private final String MONITORLIST = "monitorList";
    private final String MONITORSEARCHLIST = "monitorSearchList";
    private final String MONITORSEARCH = "monitorSearch";
    private final String HOSTLIST = "hostlist";
    private final String SUCCESS = "success";
    private final String MSG = "msg";
    private final String STR = "str";
    private int pageCount; //每页显示条目
    private int currentPage; //当前页
    private String startTime; //起始时间
    private String endTime; //结束时间
    private String mac; //设备物理地址
    private String hostNames;// 主机名
    private String hostMacs;//主机Mac名
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private String hostID;//主机ID
    private List monitorList;
    private String softName;
    private String useTime;
    private String softUse;

    @Resource
    private UserService userService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private MonitorService monitorService;
    @Resource
    private DeviceuseService deviceuseService;

    public List getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List deviceLogtypeList) {
        this.monitorList = deviceLogtypeList;
    }


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getHostNames() {
        return hostNames;
    }

    public void setHostNames(String hostNames) {
        this.hostNames = hostNames;
    }

    public String getHostMacs() {
        return hostMacs;
    }

    public void setHostMacs(String hostMacs) {
        this.hostMacs = hostMacs;
    }

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getSoftUse() {
        return softUse;
    }

    public void setSoftUse(String softUse) {
        this.softUse = softUse;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取监控软件信息列表
     *
     * @return
     */
    public String monitorList() {
        int pagesize = 5;
        int uid = 0;
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        uid = userService.getAuthorityValueByUserid(userId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Monitor> list = new ArrayList<>();
        PageBean<Monitor> pageBean = new PageBean<>();
        if (uid == 0) {

            if (this.currentPage == 0) {
                this.currentPage = 1;
            }

            try {
                pageBean = monitorService.listsService(currentPage, pagesize, null, null, null);
                list = pageBean.getRecordList();
            } catch (Exception e) {
                pageBean = new PageBean();
                e.printStackTrace();
            }
            pageCount = pageBean.getPageCount();

        }
        ActionContext.getContext().put(MONITORLIST, list);
        List<Host> hosts = hostgroupService.getHostService();
        if (hosts == null) {
            hosts = new ArrayList<>();
        }
        StringBuffer namebuf = new StringBuffer();
        StringBuffer macbuf = new StringBuffer();
        for (Host host : hosts) {
            namebuf.append(host.getHostName() + ",");
            macbuf.append(host.getHostMac() + ",");
        }
        hostNames = namebuf.substring(0, namebuf.length() - 1);
        hostMacs = macbuf.substring(0, macbuf.length() - 1);
        return MONITORLIST;
    }


    /**
     * 获取监控软件信息列表
     *
     * @return
     */
    public String monitorSearch() {
        int pagesize = 5;
        int uid = 0;
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        uid = userService.getAuthorityValueByUserid(userId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Monitor> list = new ArrayList<>();
        PageBean<Monitor> pageBean = new PageBean<>();
        if (0 == uid) {

            if (this.currentPage == 0) {
                this.currentPage = 1;
            }
            try {
                pageBean = monitorService.listsService(currentPage, pagesize, mac, startTime, endTime);
                list = pageBean.getRecordList();
            } catch (Exception e) {
                pageBean = new PageBean();
                e.printStackTrace();
            }
            pageCount = pageBean.getPageCount();

        }
        ActionContext.getContext().put(MONITORLIST, list);
        return MONITORSEARCH;
    }

    /**
     * 更新监控软件信息列表
     *
     * @return
     */
    public String monitorSearchList() {
        int pagesize = 5;
        int uid = 0;
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        uid = userService.getAuthorityValueByUserid(userId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Monitor> list = new ArrayList<>();
        PageBean<Monitor> pageBean = new PageBean<>();
        if (0 == uid) {

            if (this.currentPage == 0) {
                this.currentPage = 1;
            }
            try {
                pageBean = monitorService.listsService(currentPage, pagesize, mac, startTime, endTime);
                list = pageBean.getRecordList();
            } catch (Exception e) {
                pageBean = new PageBean();
                e.printStackTrace();
            }
            pageCount = pageBean.getPageCount();

        }
        ActionContext.getContext().put(MONITORLIST, list);
        return MONITORSEARCHLIST;
    }

    /**
     * 添加监控
     */
    public void addMonitor() {

        JSONObject json = new JSONObject();
        Monitor monitor = new Monitor();

        /**
         封装接口传送的数据
         */
        boolean result = monitorService.addMonitorService(monitor);
        if (result) {
            json.put(SUCCESS, true);
            json.put(MSG, "添加成功");
        } else {
            json.put(SUCCESS, false);
            json.put(MSG, result);
        }
        writeJSON(json.toString());
    }


    /**
     * 获取某一设备上的软件的活跃度
     */
    public String getLivenessByHost() {
//        Host host = null;
//        try {
//            host = hostgroupService.getHostInfo(54);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        host = null==host?new Host():host;
//
//        List<Object[]> devices = deviceuseService.getRanking(startTime, endTime);
//        StringBuffer devicesName = new StringBuffer();
//        StringBuffer devicesUse = new StringBuffer();
//
//        if (devices != null) {
//            for (int i = 0; i < devices.size(); i++) {
//                devicesName.append(String.valueOf(devices.get(i)[0]) + ",");
//                devicesUse.append(String.valueOf(devices.get(i)[1]) + ",");
//            }
//        }
//
//        ActionContext.getContext().put("devicesName", devicesName.toString().isEmpty() ? "" : devicesName.substring(0, devicesName.length() - 1));
//        ActionContext.getContext().put("devicesUse", devicesUse.toString().isEmpty() ? "" : devicesUse.substring(0, devicesUse.length() - 1));

        monitorList = monitorService.getMonitorList();



//        ActionContext.getContext().put("softName", softName.toString().isEmpty() ? "" : softName.substring(0, softName.length() - 1));
//        ActionContext.getContext().put("softUse", softUse.toString().isEmpty() ? "" : softUse.substring(0, softUse.length() - 1));


   //            ActionContext.getContext().put("softName",softName);
 //      ActionContext.getContext().put("softUse", softUse);


        return MONITORLIST;
    }

    /**
     * todo 加注释
     */
    public void getMonitor() {
        List<Object[]> soft = monitorService.getSoftConditionByHostMac(mac,startTime,endTime);
        StringBuffer softNameBuf = new StringBuffer();
        StringBuffer softUseBuf = new StringBuffer();
        if (soft != null) {

            for (int i = 0; i < soft.size(); i++) {
                softNameBuf.append(String.valueOf(soft.get(i)[0]) + ",");
                softUseBuf.append(String.valueOf(soft.get(i)[1]) + ",");
            }
        }
        softName = softNameBuf.toString().isEmpty() ? "" : softNameBuf.substring(0, softNameBuf.length() - 1).toString();
        softUse = softUseBuf.toString().isEmpty() ? "" : softUseBuf.substring(0, softUseBuf.length() - 1).toString();
        String[] softall = softUse.split(",");
        int timeall = 0;

        for (int i = 0; i < softall.length; i++){
            String _timeall = softall[i];
            if("".equals(_timeall)){
                _timeall = "0";
            }
                timeall = timeall+Integer.parseInt(_timeall);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < softall.length; i++){
            if("".equals(softall[i])){
                stringBuffer.append("0");
            }else {
                stringBuffer.append(String.valueOf((Integer.parseInt(softall[i]) * 100) / timeall) + ",");
            }
        }
        String str = "";
        if(stringBuffer!=null){
           str = stringBuffer.substring(0,stringBuffer.length()-1);
        }
        JSONObject json = new JSONObject();
        json.put(SUCCESS,softName);
        json.put(MSG,timeall/60/60);
        json.put(STR,str);
        writeJSON(json.toString());
    }

}
