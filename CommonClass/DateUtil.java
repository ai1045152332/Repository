package com.honghe.managerTool.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 日期时间工具类 
 *  
 * @author zhaowj
 * @mender libing
 * @Date 2018年2月6日 11点29分
 */  
public class DateUtil {

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");  
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");  
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            "HH:mm:ss");  
    
    private static final SimpleDateFormat datetimeShortFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat dateShortFormat = new SimpleDateFormat("yyyyMMdd");

    private static final SimpleDateFormat timeShortFormat = new SimpleDateFormat(
    		"HHmmss");

    private DateUtil(){}

    static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 获得当前日期时间 
     * <p> 
     * 日期时间格式yyyy-MM-dd HH:mm:ss 
     *  
     * @return 
     */  
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }  
  
    /** 
     * 格式化日期时间 
     * <p> 
     * 日期时间格式yyyy-MM-dd HH:mm:ss 
     *  
     * @return 
     */  
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);  
    }  
  
    /** 
     * 格式化日期时间 
     *  
     * @param date 
     * @param pattern 
     *            格式化模式，详见{@link SimpleDateFormat}构造器
     *            <code>SimpleDateFormat(String pattern)</code>
     * @return
     */
    public static String formatDatetime(Date date, String pattern) {
        SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat
                .clone();
        customFormat.applyPattern(pattern);
        return customFormat.format(date);
    }

    /**
     * 获得当前日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String currentDate() {
        return dateFormat.format(now());
    }

    /**
     * 获得当前日期 (短)
     * <p>
     * 日期格式yyyyMMdd
     *
     * @return
     */
    public static String currentShortDate() {
        return dateShortFormat.format(now());
    }


    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 获得当前时间
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        return timeFormat.format(now());
    }


    /**
     * 获得当前时间 (短)
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentShortTime() {
        return timeShortFormat.format(now());
    }


    /**
     * 获得当前日期时间 (短)
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String currentShortDateTime() {
        return datetimeShortFormat.format(now());
    }

    /**
     * 格式化时间
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 获得当前时间的<code>java.com.honghe.dmanager.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     *
     * 获得当前Chinese月份
     *
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     *判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     *判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     *判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate
     *            日期范围开始
     * @param endDate
     *            日期范围结束
     * @param src
     *            需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     * <p>
     * HH:mm:ss为0，毫秒为999
     *
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     * <p>
     * HH:mm:ss SS为零
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.com.honghe.dmanager.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.com.honghe.dmanager.util.Date类型
     *<p>
     * 日期时间格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 将字符串日期转换成java.com.honghe.dmanager.util.Date类型
     *<p>
     * 时间格式 HH:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.com.honghe.dmanager.util.Date类型
     *
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */  
    public static Date parseDatetime(String datetime, String pattern)  
            throws ParseException {  
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();  
        format.applyPattern(pattern);  
        return format.parse(datetime);  
    }

    /**
     * 传入日期，之前或之后的日期
     * @param date 日期
     * @param day  之后、之前的天数
     * @return yyyy-MM-dd
     */
    public static Date getDateAfterOrBefore(Date date,int day){
		Calendar now = Calendar.getInstance();
	    now.setTime(date);
		now.add(Calendar.DAY_OF_MONTH,day);
		return now.getTime();
	}
    
    /**
	 * 
	 * @reason:计算两个日期差几天，也可比较两个日期谁在前，谁在后
	 * @param:只支持yyyyMMdd格式
	 * @throws Exception 
	 * @return：int 如果firstDate在secondDate之前，返回一个负整数；反之返回正整数
	 */
	public static int getDiffBetweenTwoDate(String firstDate,String secondDate)  {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");//计算两天之差
		Date date1=null;
		Date date2=null;
		int cha=0;
		try{
			date1 = myFormatter.parse(firstDate);//起始日期
			date2 = myFormatter.parse(secondDate);//终止日期
			long  seconds=date1.getTime()-date2.getTime();//起始日期-终止日期=毫秒 
	        cha=(int)(seconds/(24*60*60*1000));//再除以每天多少毫秒(24*60*60*1000) ＝差几天
		}catch(Exception e){
            logger.error("获取时间失败",e);
		}
		return cha;
	}

    /**
     * 获取两个日期之间的天数  yyyy-MM-dd
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 日期set
     */
	public static List<String> getSetBetweenDate(Date beginDate, Date endDate){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        List<String> dateList = new ArrayList<>() ;
        while (calendar.getTime().getTime()<=endDate.getTime()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(calendar.getTime());
            dateList.add(dateStr);
            calendar.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份加1
        }
        return dateList;
    }


    /**
     * 排列排列周的次序(1,2,3,4,5,6,7)
     * @param weekNum
     * @return 按升序排列
     */
    public static String sortWeek(String weekNum) {

        String[] strs = weekNum.split(",");// 使用正则表达式进行分割
        int[] is = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {// 遍历String数组，赋值给int数组
            is[i] = Integer.parseInt(strs[i]);
        }

        Arrays.sort(is);// 使用数组工具类进行排序，也可以自己使用冒泡或选择排序来进行排序

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < is.length; i++) {// 遍历进行拼接
            if (i == is.length - 1) {
                sb.append(is[i]);
            } else {
                sb.append(is[i] + ",");
            }
        }
        return sb.toString();
    }

    public static int getWeekOfDate() {
        Date dt=new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        dateFm.format(dt);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        else if(w == 0){
            w = 7;
        }
        return w;
    }


}  