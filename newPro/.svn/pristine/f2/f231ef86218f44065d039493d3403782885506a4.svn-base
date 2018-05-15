package com.honghe.recordweb.action.frontend.report;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.onlinetime.OnlineTimeService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by lichunming on 2015/2/28.
 */
@Controller //action注解
@Scope(value = "prototype")
public class ReportAction extends BaseAction {

    @Resource
    private HostDevice hostDevice;
    @Resource
    private SyslogService syslogService;
    @Resource
    private UserService userService;

    private Integer pageSize = 10;

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
     * 获取设备信息统计列表
     * @return
     */
    public String deviceLogList() {
//    public void deviceLogList() {

        HttpServletRequest request = ServletActionContext.getRequest();
//        Integer pageSize = 5;
        // 分页获取设备分组信息(包括分组名称, 设备数量, 每个分组含有多少个设备(host_id)) add mz
	    long start = System.currentTimeMillis();

	    Page page = syslogService.getGroupInfo(pageSize, Integer.parseInt(currentPage));
	    if (page != null && page.getResult() != null &&  !page.getResult().isEmpty()) {
            List<Map<String, Object>>listhrec_online = hostDevice.getHostInfoByType(CommonName.DEVICE_TYPE_RECOURD);//通过类型获取设备信息
            long end = System.currentTimeMillis();
            System.out.println("调用服务时间: " + (end - start));
            start = System.currentTimeMillis();

            List<String> hosts = new ArrayList<>();
            for (Map<String, Object> hrecMap : listhrec_online) {
                if ("Offline".equals(hrecMap.get("status"))) {
                    continue;
                }
                hosts.add(hrecMap.get("id").toString());
            }

            List<ReportDTO> reportDTOs = new ArrayList<>();
            List<Object[]> groups = page.getResult();
            for (Object[] group : groups) {
                ReportDTO reportDTO = new ReportDTO();
                int onlineCount = 0;
                String groupName = group[0].toString();
                String count = group[1].toString();
                String hostids = group[2].toString();
                List<String> groupHosts = new ArrayList<>();
                if (hostids != null && !"".equals(hostids)) {
                    String[] hostIds = hostids.split(",");
                    for (String hostid : hostIds) {
                        if (hosts.contains(hostid)) {
                            onlineCount++;
                            groupHosts.add(hostid);
                        }
                    }
                }
                hosts.removeAll(groupHosts);
                reportDTO.setGroupName(groupName);
                reportDTO.setCount(Integer.parseInt(count));
	            if ("0".equals(count)) {
		            reportDTO.setRate("0%");
	            } else {
		            reportDTO.setRate(onlineCount * 1.0 / Integer.parseInt(count) * 100 + "%");
	            }
                reportDTOs.add(reportDTO);
            }
            pageCount = page.getTotalPageSize();
            request.setAttribute("reportDTOs", reportDTOs);
            end = System.currentTimeMillis();
            System.out.println("循环时间: " + (end - start));
	    }

	    return "deviceloglist";

    }


	public String deviceLogListAjax() {
		JSONObject json = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
//		Integer pageSize = 5;
		// 分页获取设备分组信息(包括分组名称, 设备数量, 每个分组含有多少个设备(host_id)) add mz
		long start = System.currentTimeMillis();

		Page page = syslogService.getGroupInfo(pageSize, Integer.parseInt(currentPage));
		if (page != null && page.getResult() != null &&  !page.getResult().isEmpty()) {
			List<Map<String, Object>>listhrec_online = hostDevice.getHostInfoByType(CommonName.DEVICE_TYPE_RECOURD);//通过类型获取设备信息
			long end = System.currentTimeMillis();
			System.out.println("调用服务时间: " + (end - start));
			start = System.currentTimeMillis();

			List<String> hosts = new ArrayList<>();
			for (Map<String, Object> hrecMap : listhrec_online) {
				if ("Offline".equals(hrecMap.get("status"))) {
					continue;
				}
				hosts.add(hrecMap.get("id").toString());
			}

			List<ReportDTO> reportDTOs = new ArrayList<>();
			List<Object[]> groups = page.getResult();
			for (Object[] group : groups) {
				ReportDTO reportDTO = new ReportDTO();
				int onlineCount = 0;
				String groupName = group[0].toString();
				String count = group[1].toString();
				String hostids = group[2].toString();
				List<String> groupHosts = new ArrayList<>();
				if (hostids != null && !"".equals(hostids)) {
					String[] hostIds = hostids.split(",");
					for (String hostid : hostIds) {
						if (hosts.contains(hostid)) {
							onlineCount++;
							groupHosts.add(hostid);
						}
					}
				}
				hosts.removeAll(groupHosts);
				reportDTO.setGroupName(groupName);
				reportDTO.setCount(Integer.parseInt(count));
//				reportDTO.setRate(onlineCount * 1.0 / Integer.parseInt(count) * 100 + "%");
				if ("0".equals(count)) {
					reportDTO.setRate("0.0%");
				} else {
					reportDTO.setRate(onlineCount * 1.0 / Integer.parseInt(count) * 100 + "%");
				}
				reportDTOs.add(reportDTO);
			}
			pageCount = page.getTotalPageSize();
//			json.put("reportDTOs", reportDTOs);
//			writeJSON(json.toString());
			request.setAttribute("reportDTOs", reportDTOs);
			end = System.currentTimeMillis();
			System.out.println("循环时间: " + (end - start));
		}
        return "devicelog";
	}

}

