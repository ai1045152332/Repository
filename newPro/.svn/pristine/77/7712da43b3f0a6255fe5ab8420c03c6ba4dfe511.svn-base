package com.honghe.recordweb.action.frontend.onlinetime;

import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.action.frontend.onlinetime.entity.DeviceName;
import com.honghe.recordweb.action.frontend.onlinetime.entity.OnlineTimeData;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.monitor.MonitorService;
import com.honghe.recordweb.service.frontend.onlinetime.OnlineTimeService;
import com.honghe.recordweb.util.ExcelTools;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 用于报表统计模块
 * Created by qinzhihui on 2015/12/14.
 */
@Controller //action注解
@Scope(value = "prototype")
public class OnlineTimeAction extends BaseAction {
    @Resource
    private OnlineTimeService onlineTimeService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private MonitorService monitorService;
    private List<OnlineTimeData> onlineTimeData;//发送给报表统计页面
    private List<DeviceName> deviceList = new LinkedList<DeviceName>();//设备名称选择
    private String deviceMac;//设备名称 用于查询 实际值为MAC地址
    private String ip;//设备IP 用于查询
    private String startTime;//页面上选择开始时间
    private String endTime;//页面上选择的结束时间
    private String fileName;//下载时的名称
    private InputStream inputStream;
    private long fileLength;
    private static final Object lock_excel = new Object();
    private String chartType;//表格类型
    private String deletetime;
    private String softMac;//设备MAC地址
    private String deviceType;
    private String pageSize = "10";
    private String currentPage = "1";

    public List<Object[]> getCountRecordList() {
        return countRecordList;
    }

    public void setCountRecordList(List<Object[]> countRecordList) {
        this.countRecordList = countRecordList;
    }

    //录制次数
    private List<Object[]> countRecordList = null;


    private int pageCount;

    public void setHostDevice(HostDevice hostDevice) {
        this.hostDevice = hostDevice;
    }


    public HostDevice getHostDevice() {
        return hostDevice;
    }

    //--------------------------下面是set get方法--------------------------------------



    public OnlineTimeService getOnlineTimeService() {
        return onlineTimeService;
    }

    public void setOnlineTimeService(OnlineTimeService onlineTimeService) { this.onlineTimeService = onlineTimeService; }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public MonitorService getMonitorService() {
        return monitorService;
    }

    public void setMonitorService(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    public List<OnlineTimeData> getOnlineTimeData() {
        return onlineTimeData;
    }

    public void setOnlineTimeData(List<OnlineTimeData> onlineTimeData) {
        this.onlineTimeData = onlineTimeData;
    }

    public List<DeviceName> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceName> deviceList) {
        this.deviceList = deviceList;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceName) {
        this.deviceMac = deviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String beginTime) {
        this.startTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public static Object getLock_excel() {
        return lock_excel;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(String deletetime) {
        this.deletetime = deletetime;
    }

    public String getSoftMac() {
        return softMac;
    }

    public void setSoftMac(String softMac) {
        this.softMac = softMac;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    //------------------------------下面是action----------------------------------------------------

    /**
     * 导出设备使用时长，活跃度，开机率及图片excel
     *
     * @return export_success导出成功
     */
    public String exportExcel() {
         deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(),SessionManager.Key.DeviceType);
        //先确定需要的参数 比如查询的参数  然后调用方法
        Map map = hostDevice.getAllHostInfo(deviceType);
        List list = (List) map.get("hostInfo");
        long start = 0;
        long end = 0;
        //先判断时间是否为空  若为空 则显示当天的记录
        if (startTime != null && endTime != null && !"0".equals(startTime) && !"0".equals(endTime)) {
            start = Integer.valueOf(startTime);
            end = Integer.valueOf(endTime);
        } else {
            //若没有选择时间则默认为当天 如需更改将getbeforetime的参数修改即可
            start = onlineTimeService.getBeforeTime(1).getTime() / 1000;
            end = start+86400-1;//默认为当天全天时间
        }
        try {
            synchronized (lock_excel) {
                List<OnlineTimeData> dataList = onlineTimeService.findOnlineTimeData(list, start, end, deviceMac, "", "",deviceType);
                ExcelTools excel = new ExcelTools();
                //这里需要写获得导出数据的代码
                //设置标题
                String headers[] = {"设备名称", "设备IP", "开机率", "开机时长", "活跃度"};
                List<String[]> listexcel = new ArrayList<String[]>();
                //这里可以for循环添加
                for (int i = 0; i < dataList.size(); i++) {
                    //这里按顺序添加内容
                    OnlineTimeData onlineTimeData = dataList.get(i);
                    String name = onlineTimeData.getName();
                    String ip = onlineTimeData.getIp();
                    String openRate = onlineTimeData.getOpenRate();
                    String openDuration = onlineTimeData.getOpenDuration();
                    String activity = onlineTimeData.getAcitivity();
                    //写入数据
                    listexcel.add(new String[]{name, ip, openRate, openDuration, activity});
                }

            //设置总标题名称 表头 生成位置 生成名称
            excel.exportTableByFile("设备统计", headers, listexcel, "./temp/", "tempexcel");
            //列宽已设置成自动适应列宽 如需更改参见base/ExcelTools中的数据
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //下面为下载excel
        File file = new File("./temp/","tempexcel.xls");

        try {
            inputStream = new FileInputStream(file);
            fileName = "report.xls";
            fileLength = file.length();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "export_success";
    }

    /**
     * 查看图表方法 控制页面跳转
     *
     * @return String viewChart
     */
    public String viewChart() {
        return "viewChart";
    }

    /**
     * 查看图表方法 ajax刷新数据
     */
    public void viewChartAjax() {
        long start = 0;
        long end = 0;
        deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(),SessionManager.Key.DeviceType);
        //先判断时间是否为空  若为空 则显示当天的记录
        if (startTime != null && endTime != null && !"0".equals(startTime) && !"0".equals(endTime)) {
            start = Integer.valueOf(startTime);
            end = Integer.valueOf(endTime);
        } else {
            //若没有选择时间则默认为当天 如需更改将getbeforetime的参数修改即可
            start = onlineTimeService.getBeforeTime(0).getTime() / 1000;
            end = start+86400-1;//默认为当天全天时间
        }
        Map map = hostDevice.getAllHostInfo(deviceType);
        List list = (List) map.get("hostInfo");
        //图表横坐标值 设备名称
        String[] dataName = new String[list.size()];
        //每个设备的数据  分别为开机时长 开机率 活跃度 由chartType控制
        double[] dataPoint;
        for (int i = 0; i < list.size(); i++) {
            Map device = (Map) list.get(i);
            String name = (String) device.get("name");//设备名称
            dataName[i] = name;
        }
        dataPoint = onlineTimeService.chartCount(chartType, list, start, end,deviceType);
        //将数据以json形式发给页面
        JSONObject json = new JSONObject();
        json.put("dataName", dataName);
        json.put("dataPoint", dataPoint);
        writeJson(json);
    }

    /**
     * 获得报表统计数据
     *
     * @return String report查询全部数据  reportAjax查询单个数据
     */
    public String report() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("进入报表" + df.format(new Date()));
        deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(),SessionManager.Key.DeviceType);
        Map map = hostDevice.getAllHostInfo(deviceType);
        List list = (List) map.get("hostInfo");
        this.pageCount = (list.size() + Integer.parseInt(this.pageSize) - 1) / Integer.parseInt(this.pageSize);
        System.out.println("获取班级列表" +list.size() +"  "+ df.format(new Date()));
        List listByPage = new ArrayList();
        int beginIndex = Integer.parseInt(pageSize) * (Integer.parseInt(currentPage)-1);
        int endIndex = Integer.parseInt(pageSize) * Integer.parseInt(currentPage);
        for(int i=0;i<list.size();i++)
        {
            if (i >= beginIndex && i < endIndex)
            {
                listByPage.add(list.get(i));
            }
        }
        long start = 0;
        long end = 0;
        //先判断时间是否为空  若为空 则显示当天的记录
        if (startTime != null && endTime != null && !"0".equals(startTime) && !"0".equals(endTime)) {
            start = Integer.valueOf(startTime);
            end = Integer.valueOf(endTime);
        } else {
            //若没有选择时间则默认为当天 如需更改将getbeforetime的参数修改即可
            start = onlineTimeService.getBeforeTime(0).getTime() / 1000;
            end = start+86400-1;//默认为当天全天时间
        }
        System.out.println("获取开始时间"+df.format(start*1000)+"结束时间"+df.format(end*1000));
        //将查询到的设备名称存入deviceList 这个方法不会变化 用于下拉框选择处
        for (int i = 0; i < list.size(); i++) {
            DeviceName deviceName = new DeviceName();
            Map device = (Map) list.get(i);
            String name = (String) device.get("name");//设备名称
            String mac = (String) device.get("host_mac");//设备MAC地址 用于查看软件使用情况
            deviceName.setMac(mac);
            deviceName.setName(name);
            deviceList.add(deviceName);
        }
        String deviceIp = null;
        String deviceName = null;
        //若传入设备名称不为空 则查询IP 和name
        if (deviceMac != null) {
            Map macMap = hostDevice.getNameByMac(deviceMac);
            deviceIp = (String) macMap.get("host_ipaddr");
            deviceName = (String) macMap.get("host_name");
        }
        try {
            System.out.println("开始查询报表"+df.format(new Date()));
            onlineTimeData = onlineTimeService.findOnlineTimeData(listByPage, start, end, deviceMac, deviceIp, deviceName,deviceType);
            System.out.println("结束查询报表"+df
                    .format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "report";
    }

    /**
     * 获得报表统计数据
     * reportAjax刷新数据
     */
    public String reportAjax(){
        deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(),SessionManager.Key.DeviceType);
        Map map = hostDevice.getAllHostInfo(deviceType);
        List list = (List)map.get("hostInfo");
        this.pageCount = (list.size() + Integer.parseInt(this.pageSize) - 1) / Integer.parseInt(this.pageSize);
        List listByPage = new ArrayList();
        int beginIndex = Integer.parseInt(pageSize) * (Integer.parseInt(currentPage)-1);
        int endIndex = Integer.parseInt(pageSize) * Integer.parseInt(currentPage);
        for(int i=0;i<list.size();i++)
        {
            if (i >= beginIndex && i < endIndex)
            {
                listByPage.add(list.get(i));
            }
        }
        long start=0;
        long end=0;
        //先判断时间是否为空  若为空 则显示当天的记录
        if (startTime!=null&&endTime!=null&&!"0".equals(startTime)&&!"0".equals(endTime)) {
            start = Integer.valueOf(startTime);
            end = Integer.valueOf(endTime);
        }else {
            //若没有选择时间则默认为当天 如需更改将getbeforetime的参数修改即可
            start =onlineTimeService.getBeforeTime(0).getTime()/1000;
            end = start+86400-1;//默认为当天全天时间
        }
        //将查询到的设备名称存入deviceList 这个方法不会变化 用于下拉框选择处
        for (int i=0;i<list.size();i++){
            DeviceName deviceName=new DeviceName();
            Map device = (Map)list.get(i);
            String name = (String)device.get("name");//设备名称
            String mac = (String)device.get("host_mac");//设备MAC地址 用于查看软件使用情况
            deviceName.setMac(mac);
            deviceName.setName(name);
            deviceList.add(deviceName);
        }
        String deviceIp=null;
        String deviceName=null;
        //若传入设备名称不为空 则查询IP 和name
        if (deviceMac!=null){
            Map macMap = hostDevice.getNameByMac(deviceMac);
            deviceIp = (String)macMap.get("host_ipaddr");
            deviceName = (String)macMap.get("host_name");
        }
        try {
            onlineTimeData = onlineTimeService.findOnlineTimeData(listByPage, start, end, deviceMac,deviceIp,deviceName,deviceType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "reportAjax";
    }
    /**
     * 用于清理过时的信息 为保证数据库流畅
     *
     */
    public void clear() {
        int time = Integer.valueOf(deletetime);
        JSONObject json = new JSONObject();
        try {
            onlineTimeService.deleteDataBefore(time,deviceType);
            json.put("msg", "清理成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "清理失败");
        }
        writeJson(json);
    }

    /**
     * 软件使用情况图表显示 ajax刷新页面
     *
     */
    public void softInUseAjax() {
        //若时间为空 则以当天计算
        if (startTime == null || endTime == null || " 00:00:00".equals(startTime) || " 23:59:59".equals(endTime)) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            startTime = df.format(new Date()) + " 00:00:00";
            endTime = df.format(new Date()) + " 23:59:59";
        }
        List<Object[]> soft = monitorService.getSoftConditionByHostMac(softMac, startTime, endTime);
        String[] dataName = new String[soft.size()];//软件名称
        double[] dataPoint1 = new double[soft.size()];//软件时长
        double[] dataPoint2 = new double[soft.size()];//软件活跃度
        if (soft != null) {
            for (int i = 0; i < soft.size(); i++) {
                dataName[i] = String.valueOf(soft.get(i)[0]);
                String a = String.valueOf(soft.get(i)[1]);
                dataPoint1[i] = Double.valueOf(a) / 60 / 60;
            }
        }
        double alltime = 0;//总时长 用于计算活跃度
        for (int i = 0; i < soft.size(); i++) {
            double time = dataPoint1[i];
            alltime += time;
        }
        for (int i = 0; i < soft.size(); i++) {
            double time = dataPoint1[i];
            dataPoint2[i] = time * 100 / alltime;
        }
        //通过chartType判断选择的是那种类型的图表 使用时长 活跃度
        Map macMap = hostDevice.getNameByMac(softMac);
        String deviceName = (String) macMap.get("host_name");
        JSONObject json = new JSONObject();
        json.put("dataName", dataName);
        if ("shichang".equals(chartType)) {
            json.put("dataPoint", dataPoint1);
        } else {
            json.put("dataPoint", dataPoint2);
        }
        json.put("deviceName", deviceName);
        writeJSON(json.toString());
    }

    /**
     * 控制跳转页面 并将软件MAC地址传到页面 softMac
     *
     * @return String softinuse跳转页面
     */
    public String softInUse() {
        return "softinuse";
    }

    /**
     * 导出软件使用时长及活跃度excel
     *
     * @return export_success导出成功
     */
    public String exportSoftExcel() {
        //若时间为空 则以当天计算
        if (startTime == null || endTime == null || " 00:00:00".equals(startTime) || " 23:59:59".equals(endTime)) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            startTime = df.format(new Date()) + " 00:00:00";
            endTime = df.format(new Date()) + " 23:59:59";
        }
        List<Object[]> soft = monitorService.getSoftConditionByHostMac(softMac, startTime, endTime);
        String[] dataName = new String[soft.size()];//软件名称
        double[] dataPoint1 = new double[soft.size()];//软件时长
        double[] dataPoint2 = new double[soft.size()];//软件活跃度
        if (soft != null) {
            for (int i = 0; i < soft.size(); i++) {
                dataName[i] = String.valueOf(soft.get(i)[0]);
                String a = String.valueOf(soft.get(i)[1]);
                dataPoint1[i] = Double.valueOf(a) / 60 / 60;
            }
        }
        double alltime = 0;//总时长 用于计算活跃度
        for (int i = 0; i < soft.size(); i++) {
            double time = dataPoint1[i];
            alltime += time;
        }
        for (int i = 0; i < soft.size(); i++) {
            double time = dataPoint1[i];
            dataPoint2[i] = time * 100 / alltime;
        }
        //通过chartType判断选择的是那种类型的图表 使用时长 活跃度
        Map macMap = hostDevice.getNameByMac(softMac);
        String deviceName = (String) macMap.get("host_name");
        try {
            synchronized (lock_excel) {
                ExcelTools excel = new ExcelTools();
                //这里需要写获得导出数据的代码
                //设置标题
                String headers[] = {"设备名称", "软件名称", "使用时长", "活跃度"};
                List<String[]> listexcel = new ArrayList<String[]>();
                //这里可以for循环添加
                for (int i = 0; i < soft.size(); i++) {
                    //这里按顺序添加内容
                    String useTime;
                    String act;
                    DecimalFormat df   = new DecimalFormat("#.00");
                    if (dataPoint1[i]<1){
                        String useTime1 = df.format(dataPoint1[i]);
                        useTime="0"+useTime1+"小时";
                    }else {
                        useTime  = df.format(dataPoint1[i])+"小时";
                    }
                    if (dataPoint2[i]<1){
                        String activity1 = df.format(dataPoint2[i]);
                        act="0"+activity1;
                    }else {
                        act  = df.format(dataPoint2[i]);
                    }
                    //写入数据
                    listexcel.add(new String[]{deviceName, dataName[i], useTime, act});
                }
                //设置总标题名称 表头 生成位置 生成名称
                excel.exportTableByFileSoft("软件统计", headers, listexcel, "./temp/", "tempsoftexcel");
                //列宽已设置成自动适应列宽 如需更改参见base/ExcelTools中的数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //下面为下载excel
        File file = new File("./temp/","tempsoftexcel.xls");
        try {
            inputStream = new FileInputStream(file);
            fileName = "soft.xls";
            fileLength = file.length();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "export_success";
    }
    /**
     * 2018/3/31 -zgh
     * 录制次数(前十)
     */
    public void getCountRecordVedio(){

        String type ="record";
        List countRecordList = new ArrayList();
        List name = new ArrayList();
        List counts = new ArrayList();
        List<Object[]> result = onlineTimeService.findCountRecordByGroup(startTime, endTime, type);
        for (int i = 0; i <result.size() ; i++) {
            name.add(result.get(i)[0]);
            counts.add(result.get(i)[1]);
        }
        countRecordList.add(name);
        countRecordList.add(counts);
        JSONObject json = new JSONObject();
        json.put("data",countRecordList);
        writeJSON(json.toString());
    }

}
