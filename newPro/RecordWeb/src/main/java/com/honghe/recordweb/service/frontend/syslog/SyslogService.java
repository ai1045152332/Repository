package com.honghe.recordweb.service.frontend.syslog;

import com.honghe.recordhibernate.dao.HostDao;
import com.honghe.recordhibernate.dao.LogtypeDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.SyslogDao;
import com.honghe.recordhibernate.entity.Syslog;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SyslogService {

    private final static Logger logger = LoggerFactory.getLogger(SyslogService.class);

    public static enum LogType {
        ERROR("错误信息"), WARING("警告信息");
        private String type;

        LogType(String type) {
            this.type = type;
        }

        public String toString() {
            return this.type;
        }
    }

    @Resource
    private SyslogDao syslogDao;

    @Resource
    private LogtypeDao logtypeDao;

    @Resource
    HostgroupService hostgroupService;
    @Resource
    HostDevice hostDevice;
    @Resource
    private HostDao hostDao;


    //
//    public List<Object[]> getGroupInfo(Integer pageSize, int currentPage) {
//	    Page page = new Page(currentPage, pageSize);
////        List<Object[]> list = syslogDao.getGroupInfo(pageSize, currentPage);
//        List<Object[]> list = syslogDao.getGroupInfo(page);
//        System.out.println(list);
//        return syslogDao.getGroupInfo(pageSize, currentPage);
//    }

	public Page getGroupInfo(Integer pageSize, int currentPage) {
		Page page = new Page(currentPage, pageSize);
		syslogDao.getGroupInfo(page);
		return page;
	}

    /**
     * 获取所有日志
     *
     * @param pageSize
     * @param currentPage
     * @return
     */
    public Page getAllDeviceLogList(int pageSize, int currentPage, int flag) {
        Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
        try {
            syslogDao.syslogList(page, flag);
            return page;
        } catch (Exception e) {
            logger.error("获取所有日志异常", e);
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取id以后日志
     *
     * @param id
     * @return
     */
    public List<Object[]> getAllLogListById(int count, int id, int flag) {
        List<Object[]> result = syslogDao.syslogListById(count, id, flag);
        return result;
    }

    /**
     * 根据班级ip，日志类型
     * 获取id以后的日志
     *
     * @param id
     * @return
     */
    public List<Object[]> getAllLogListtByTypeAndIp(String logType, String ip, int count, int id, int flag) {
        List<Object[]> result = syslogDao.syslogListByTypeAndIpafterId(logType, ip, count, id, flag);
        return result;
    }

    /**
     * 根据班级ip，日志类型，时间
     * 获取id以后的日志
     *
     * @param id
     * @return
     */
    public List<Object[]> getAllLogListtByTimeAndIp(int startTime, int endTime, String logType, String ip, int count, int id, int flag) {
        List<Object[]> result = syslogDao.syslogListByTimeAndIPafterId(startTime, endTime, logType, ip, count, id, flag);
        return result;
    }

    /**
     * 根据ip查
     *
     * @param logType
     * @param ip
     * @param pageSize
     * @param currentPage
     * @return
     */
    public Page devicelogListByTypeAndIp(String logType, String ip, int pageSize, int currentPage, int flag) {
        Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
        try {
            //syslogDao.syslogListByTimeAndIP(logType, page, ip,flag);
            syslogDao.syslogListByTypeAndIp(logType, page, ip, flag);
            return page;
        } catch (Exception e) {
            logger.error("根据ip查询日志异常 ", e);
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据ip时间查
     *
     * @param startTime
     * @param endTime
     * @param logType
     * @param ip
     * @param pageSize
     * @param currentPage
     * @return
     */
    public Page devicelogListByTimeAndIP(int startTime, int endTime, String logType, String ip, int pageSize, int currentPage, int flag) {
        Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
        try {
            syslogDao.syslogListByTimeAndIP(startTime, endTime, logType, page, ip, flag);
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("根据时间查日志异常", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param deviceip
     * @param logdesc
     * @return
     */
    @Transactional
    public boolean addDeviceSystemLog(String deviceip, String logdesc) {
        return this.addDeviceLog(deviceip, logdesc, "SYSTEM");
    }

    /**
     * 添加日志
     *
     * @param deviceip
     * @param logdesc
     * @param logtype
     * @return
     */
    @Transactional
    public boolean addDeviceLog(String deviceip, String logdesc, String logtype) {
        try {

            Map<String, String> hostInfo = new HashMap<>();

            hostInfo = hostDao.getHostInfoByIp(deviceip);
            //hostInfo = hostDevice.getHostInfoById(deviceip);

            String username = "System";
            try {
                HttpServletRequest request = ServletActionContext.getRequest();
                User user = SessionManager.get(request.getSession(), SessionManager.Key.USER);
                if (user != null) {
                    username = user.getUserName();
                }
            } catch (Exception e) {

            }
            String devicename = deviceip;
            if (hostInfo != null && !hostInfo.isEmpty()) {
                devicename = hostInfo.get("host_name").toString();
            }

            int time = Integer.parseInt(((new Date().getTime()) / 1000) + "");

            Syslog deviceLog = new Syslog();
            deviceLog.setLogUser(username);
            deviceLog.setLogHost(devicename);
            deviceLog.setLogContent(logdesc);
            deviceLog.setLogTime(time);
            deviceLog.setLogIp(deviceip);
            deviceLog.setLogType(logtype);
            return syslogDao.save(deviceLog);

        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("添加日志异常", e);
            return false;
        }
    }

    /**
     * 获取日志类型
     *
     * @return
     */
    public List getLogtype(int flag) {
        try {
            return logtypeDao.getLogType(flag);
        } catch (Exception e) {
            logger.error(" 获取日志类型异常", e);
            return null;
        }
    }

}
