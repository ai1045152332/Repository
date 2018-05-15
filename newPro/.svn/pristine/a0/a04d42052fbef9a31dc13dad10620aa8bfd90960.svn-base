package com.honghe.recordweb.service.frontend.onlinetime;

import com.honghe.recordhibernate.dao.OnlineTimeDao;
import com.honghe.recordhibernate.entity.OnlineTime;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.action.frontend.onlinetime.entity.OnlineTimeData;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ExcelTools;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.*;
import java.util.*;

/**
 * 用于报表统计逻辑层
 * Created by qinzhihui on 2015/12/9.
 */
@Service("onlineTimeService")
public class OnlineTimeService {
    private final static Logger logger = Logger.getLogger(OnlineTimeService.class);
    @Resource
    private OnlineTimeDao onlineTimeDao;
//    private final static String CONTROL = "control";
//    private final static String RECORD = "record";
    /**
     * 保存开机时间
     * @param ip 设备IP地址 好像不太重要
     * @param mac 设备MAC地址 必要
     */
    @Transactional
    public void addOnTime(String ip,String mac,String devicetype, String operationType) throws Exception{
        OnlineTime onlineTime = new OnlineTime();
        Date date = new Date();
        long time = date.getTime()/1000;
        //将时间以毫秒的形式存到数据库 先把open和close时间存成一个 防止变成空值
        onlineTime.setOpentime(time);
        onlineTime.setClosetime(time);
        onlineTime.setIp(ip);
        // 18-03-30 增加录播主机开机记录 add mz
        if (CommonName.DEVICE_TYPE_RECOURD.equals(devicetype)) {
            onlineTime.setType(operationType);
        } else {
            onlineTime.setType(devicetype);
        }
        //此处需要存个MAC地址 非常重要 所有查询都需要根据mac地址查询
        onlineTime.setMac(mac);
        onlineTimeDao.save(onlineTime);
    }
    /**
     * 更新关机时间
     * @param mac 设备MAC地址
     */
    @Transactional
    public void updateOffTime(String mac) throws Exception{
        OnlineTime onlineTime = onlineTimeDao.findLastOpen(mac);
        Date date = new Date();
        long time = date.getTime()/1000;
        long start = onlineTime.getOpentime();
        long end = onlineTime.getClosetime();
        String type = onlineTime.getType();
        //这里最好还是判断一下上一条记录是否为上次的开机记录 不然可能会使开机时长变得非常长
        if (start==end){
            //这里只需把刚才开机的机器关机时间变成当前即可
            onlineTime.setClosetime(time);
            onlineTimeDao.update(onlineTime);
        }
    }
    /**
     * 计算开机时间 返回值为分钟
     * @param startTime 查找的开始时间
     * @param endTime 查找的结束时间
     * @param mac 某个设备的MAC地址
     * @return int 开机时间 单位：分钟
     */
    public int openDuration(String mac, long startTime,long endTime,String devicetype) throws Exception{
        //这个值的单位是分钟
        int duration=0;
        // 下面去数据库中查询
        List list=new ArrayList();
        if (mac!=null&&"".equals(mac)) {
            list = onlineTimeDao.findByMac(mac, startTime, endTime);
        }else{
            list = onlineTimeDao.findByType(mac,devicetype,startTime,endTime);
        }
        if (null!=list){
            for (int i=0;i<list.size();i++) {
                OnlineTime onlineTime = (OnlineTime) list.get(i);
                long closeTime = onlineTime.getClosetime();
                long openTime = onlineTime.getOpentime();
                if (closeTime>openTime){
                    //这里将秒转化为分钟 故/60 如需变成小时再/60即可
                    duration += (int) (closeTime - openTime) / 60;
                }
            }
        }
        return duration;
    }
    /**
     * 将开机时间转换成XX小时XX分钟的格式
     * @param duration 分钟数
     * @return String 时间 格式XX小时XX分钟
     */
    public String timeToString(int duration){
        String time ="";
        if (duration/60>0){
            time+=duration/60+"小时";
        }
        time+=duration%60+"分钟";
        return time;
    }
    /**
     * 计算开机率 即查询某天是否开机 直接返回开机率
     * @param startTime 查找的开始时间
     * @param endTime 查找的结束时间
     * @param mac 某个设备的MAC地址
     * @return openRate String开机率
     */
    public String openRate(String mac, long startTime,long endTime,String devicetype) throws Exception{

        OnlineTime onlineTime = null;
        int times=0;//记录总天数
        int openTimes=0;//记录开机次数
        while (startTime+86399<=endTime){
            //此处startTime为当天00:00:00的时间 +86399为当天23:59:59
            long endDayTime = startTime+86399;
            //如果mac地址为空的话就通过设备类型查询
            if (mac!=null&&"".equals(mac)) {
                onlineTime = onlineTimeDao.findWhetherOpen(mac, startTime, endDayTime);
            }else {
                onlineTime = onlineTimeDao.findWhetherOpenByType(devicetype,startTime,endDayTime);
            }
            times++;//查询一天 天数+1
            if (null!=onlineTime){
                openTimes++;//当有数据时 开机次数+1
            }
            startTime+=86400;//起始时间+1天
        }
        //将数据变成百分比格式
        if(times==0){
            times=1;
        }
        String openRate = percent(openTimes,times);
        return openRate;
    }

    /**
     * 此方法可以把数据库中的时间转换成Date类型 暂时无用
     * @param dateFormat 时间格式 其实可以不要
     * @param millSec 毫秒时间 需要从数据库中取出来后*1000再传入
     * @return 格式化后的String
     */
    public String transferLongToDate(String dateFormat,Long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date= new Date(millSec);
        return sdf.format(date);
    }
    /**
     * 这个方法是用来清理数据库里太多信息的 存得多往往比较卡 所以经常删着点有好处
     * 大概就是清除一个月（30天）以前啊  或者三个月 半年之类的情况
     * @param selectTime 这个跟上面一样 传进来的是个int 比如30 90 180这种
     */
    @Transactional
    public void deleteDataBefore(int selectTime,String type) throws Exception{
        //先获得需要删除的时间
        Date beforeTime = getBeforeTime(selectTime);
        //转换成数据库里的格式
        long time = beforeTime.getTime()/1000;
        //然后就删掉吧
        try{
            onlineTimeDao.deleteByTime(time,type);
        }catch(Exception e){
            logger.debug("删除数据失败", e);
        }
    }
    /**
     * 写个通用方法 计算N天之前的时间 可以复用-,- 现采用选取日期区间的方法
     * @param selectTime 查询多少时间内的数据 单位天 查询当天为1 一周为7 一个月为30
     * @return dateBefore 返回值是个Date类型的值 需要再用gettime()/1000 变成数据库中的格式 再继续使用
     */
    public Date getBeforeTime(int selectTime){
        //获得查询时的时间
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE)-selectTime);
        // 设置当前时刻的时钟为0
        date.set(Calendar.HOUR_OF_DAY, 0);
        // 设置当前时刻的分钟为0
        date.set(Calendar.MINUTE, 0);
        // 设置当前时刻的秒钟为0
        date.set(Calendar.SECOND, 0);
        // 设置当前的毫秒钟为0
        date.set(Calendar.MILLISECOND, 0);
        Date dateBefore = date.getTime();//这样获取的是需要查询天的时间 即当天0点
        return dateBefore;
    }
    /**
     * 获得百分比的方法
     * @param y 被除数
     * @param z 除数
     * @return baifenbi 转成百分比之后的数据
     */
    public String percent(int y, int z) {
        String baifenbi = "";// 返回百分比的值
        double baiy = y * 1.0;
        double baiz = z * 1.0;
        double fen = baiy / baiz;
        NumberFormat nf = NumberFormat.getPercentInstance();
        //保留到小数点后几位
        nf.setMinimumFractionDigits( 2 );
        // 百分比格式，后面不足2位的用0补齐
        baifenbi=nf.format(fen);
        return baifenbi;
    }
    /**
     * 获得统计报表中所需数据
     * @param deviceMac 设备MAC地址 可为空
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param hostInfo List 全部设备信息
     * @param deviceIp String 查询单个设备时的IP 可为空
     * @param deviceName String 查询单个设备时的name 可为空
     * @return List<OnlineTimeData> 需要返回页面的数据
     */
    public List  findOnlineTimeData(List hostInfo ,long startTime,long endTime,String deviceMac,String deviceIp,String deviceName,String devicetype) throws Exception{
        List<OnlineTimeData> list = new LinkedList<OnlineTimeData>();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //将传过来的日期转化成int类型 此处有可能在页面上已经计算 若已经计算 则可把下面计算时间删除
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//此处格式应和页面传来的格式对应
//        Date startDate = sdf.parse(startTime);
//        Date endDate = sdf.parse(endTime);
//        long begin = startDate.getTime()/1000;
//        long end = endDate.getTime()/1000;
        //若输入了设备名称  则不能直接调用接口遍历查询 所以单独写一下选择设备的情况
        if (null!=deviceMac&&!"ALL".equals(deviceMac)&&!"".equals(deviceMac)){
            for(int i = 0;i<hostInfo.size();i++){
                Map<String,String> map = (Map<String,String>)hostInfo.get(i);
                if(deviceMac.equals(map.get("host_mac"))){
                    deviceIp = map.get("token");
                    deviceName = map.get("name");
                    break;
                }
            }
            OnlineTimeData onlineTimeData=new OnlineTimeData();
            //计算需要的数据
            int duration = openDuration(deviceMac, startTime, endTime,devicetype);
            String openDuration = timeToString(duration);//开机时长
            String openRate = openRate(deviceMac, startTime, endTime,devicetype);//开机率
            String activity = getActivity(deviceMac,hostInfo.size(), startTime, endTime,devicetype);//活跃度
            //保存数据
            onlineTimeData.setOpenDuration(openDuration);
            onlineTimeData.setOpenRate(openRate);
            onlineTimeData.setAcitivity(activity);
            onlineTimeData.setMac(deviceMac);
            onlineTimeData.setName(deviceName);
            onlineTimeData.setIp(deviceIp);
            list.add(onlineTimeData);
        }else {
            //若没有查询单个设备 则显示全部

            for (int i = 0;i<hostInfo.size();i++){
                OnlineTimeData onlineTimeData=new OnlineTimeData();
                Map map = (Map)hostInfo.get(i);
                String name = (String)map.get("name");//设备名称
                String ip = (String)map.get("host_ip");//设备名称
                String mac = (String)map.get("host_mac");//设备MAC地址 用于查看软件使用情况
                //这里先获取全部设备列表 然后取出MAC地址来查询 并计算
                int duration = openDuration(mac, startTime, endTime,devicetype);
                String openDuration = timeToString(duration);//开机时长
                String openRate = openRate(mac, startTime, endTime,devicetype);//开机率
                String activity = getActivity(mac,hostInfo.size(), startTime, endTime,devicetype);//活跃度
                //保存数据
                onlineTimeData.setName(name);
                onlineTimeData.setIp(ip);
                onlineTimeData.setOpenRate(openRate);
                onlineTimeData.setOpenDuration(openDuration);
                onlineTimeData.setAcitivity(activity);
                onlineTimeData.setMac(mac);
                list.add(onlineTimeData);
                System.out.println("设备IP："+ip+"-----"+df.format(new Date()));
            }

        }
        return list;
    }
    /**
     * 下面写一个计算活跃度的方法
     * 整体思路为 设备总时长/(设备总时长+绝对值的和) 代表一个机器开机时间的稳定性 当设备总时长越长时 绝对值的和影响越小
     * 第二项 某台设备的开机时长X设备总数/全部设备的开机时长 代表一台机器的总时长占全部时长的比例
     * 当两个参数都比较大时 活跃度比较高 时长影响比例较大 开机时间稳定性影响较小
     * @param mac 设备MAC地址
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param deviceCount 设备总数量
     * @return String activity 活跃度
     */
    public String getActivity(String mac,int deviceCount,long startTime,long endTime,String devicetype) throws Exception{
        //------------------------------------下面为运算所需参数----------------------------------------------------
        //先获得某设备开机总时长 分钟
        int deviceDuration = openDuration(mac,startTime,endTime,devicetype);
        //计算总天数
        int dayCount = (int)(endTime-startTime)/86400+1;
        //计算平均时长
        int averageDuration = deviceDuration/dayCount;
        //绝对值
        int absolute=0;
        //获得所有设备的总时长
        int totalTime=0;
        //----------------------------------下面为参数获得过程--------------------------------------------------
        List<OnlineTime> totalList = onlineTimeDao.findTotalTime(startTime,endTime,devicetype);
        for (int i = 0;i<totalList.size();i++){
            OnlineTime onlineTime = totalList.get(i);
            long start = onlineTime.getOpentime();
            long end = onlineTime.getClosetime();
            int time = (int) (end - start) / 60;//获得一次的时间
            totalTime+=time;//相加得总时长
        }
        while (startTime+86399<=endTime){
            //此处startTime为当天00:00:00的时间 +86399为当天23:59:59
            long endDayTime = startTime+86399;
            List<OnlineTime> list=onlineTimeDao.findByMac(mac,startTime,endDayTime);
            if (null!=list) {
                int totalDayTime = 0;//当天总时长
                for (int i = 0; i < list.size(); i++) {
                    OnlineTime onlineTime = list.get(i);
                    long start = onlineTime.getOpentime();
                    long end = onlineTime.getClosetime();
                    int time = (int) (end - start) / 60;//获得一次的时间
                    totalDayTime += time;
                }
                absolute += Math.abs(totalDayTime - averageDuration);//取绝对值
            }
            startTime+=86400;//日期+1
        }
        //-------------------------参数获得完成 开始运算--------------------------------------------
        //第一项
        double first=0;
        //第二项
        double second=0;
        String activity="0.00";
        if (deviceDuration!=0){
            first = ((double)deviceDuration/((double)deviceDuration+(double)absolute));
            second = ((double)deviceDuration/(double)totalTime)*(double)deviceCount;
            //保留两位小数 并返回
            DecimalFormat df   = new DecimalFormat("#.00");
            if (first+second==0){
                activity="0.00";
            }else if(first+second<1){
                String activity1 = df.format(first + second);
                activity="0"+activity1;
            }else {
                activity  = df.format(first + second);
            }
        }
        return activity;
    }
    /**
     * 返回查看图表数据
     * @param chartType 选择表格类型
     * @param hostInfo 设备信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return double[] 需要显示的Y坐标值
     */
    public double[] chartCount(String chartType,List hostInfo,long startTime,long endTime,String devicetype){
        double [] count=new double[hostInfo.size()];
        //若查询为开机时长
        if ("kaijishichang".equals(chartType)){
            try {
                for (int i = 0;i<hostInfo.size();i++){
                    Map map = (Map)hostInfo.get(i);
                    String mac = (String)map.get("host_mac");//设备MAC地址 用于查看软件使用情况
                    double duration = openDuration(mac, startTime, endTime,devicetype);//获得开机时长
                    DecimalFormat df = new DecimalFormat("#.0");
                    double value = Double.parseDouble(df.format(duration / 60));//转换为double格式
                    count[i]=value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //若查询为开机率
        if ("kaijilv".equals(chartType)){
            try {
                for (int i = 0;i<hostInfo.size();i++){
                    Map map = (Map)hostInfo.get(i);
                    String mac = (String)map.get("host_mac");//设备MAC地址 用于查看软件使用情况
                    String openRate = openRate(mac, startTime, endTime,devicetype);//开机率 带%的形式
                    double value = Double.parseDouble(openRate.split("%")[0]);//转换为double格式
                    count[i]=value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //若查询为活跃度
        if ("huoyuedu".equals(chartType)){
            try {
                for (int i = 0;i<hostInfo.size();i++){
                    Map map = (Map)hostInfo.get(i);
                    String mac = (String)map.get("host_mac");//设备MAC地址 用于查看软件使用情况
                    String activity = getActivity(mac,hostInfo.size(), startTime, endTime,devicetype);
                    double value = Double.parseDouble(activity);//转换为double格式
                    count[i]=value;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     *2018/3/31 -zgh
     * 录制次数(前十)
     */
    public List<Object[]> findCountRecordByGroup(String str_startTime,String str_endTime,String type){
        List<Object[]> counts = null;
        int startTime = 0;
        int endTime = 0;
        if(str_startTime != null && str_endTime != null && !"0".equals(str_startTime) && !"0".equals(str_endTime)){
            str_startTime +=" 00:00:00";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(str_startTime, pos);
            long time = date.getTime();
            startTime = (int) (time/1000);
            //获取当前时间
            long lTime = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(new Date(lTime));
            //判断结束日期是否为今天
            if(str_endTime.equals(format)){
                endTime = Integer.parseInt(( lTime / 1000) + "");
            }else{
                str_endTime +=" 23:59:59";
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ParsePosition pos1 = new ParsePosition(0);
                Date date1 = formatter1.parse(str_endTime, pos1);
                long time1 = date1.getTime();
                endTime = (int) (time1/1000);
            }
        }else {
            //无开始结束时间默认现在起前七天内
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -7);
            Date day = c.getTime();
            String preDay = sdf.format(day) + "00:00:00";

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(preDay, pos);
            long time = date.getTime();
            startTime = (int) (time/1000);
            endTime = Integer.parseInt(((new Date().getTime()) / 1000) + "");
        }
        try{
            counts = onlineTimeDao.findCountRecordByGroup(startTime, endTime, type);
        }catch (Exception e) {
            logger.error("获取录制次数异常", e);
        }
        return counts;
    }

}
