package com.honghe.recordweb.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanghongbin on 2014/12/5.
 */
public final class DateUtil {

    private final static Logger logger = Logger.getLogger(DateUtil.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Calendar cal = Calendar.getInstance();

    private DateUtil() {

    }

    public final static long get(String hms) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String h = year + "-" + month + "-" + day + " " + hms;
        try {
            return simpleDateFormat.parse(h).getTime();
        } catch (Exception e) {
            logger.error("获取时间异常：", e);
            return 0l;
        }

    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static String StrToDate(String str) {
        if ("".equals(str)) {
            return "";
        }
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);

        } catch (Exception e) {
            logger.error("字符串转换成日期异常：", e);
//            e.printStackTrace();
        }
        String string = DateUtil.DateToStr(date);
        return string;
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        String str = simpleDateFormat.format(date);
        return str;
    }

    public final static int getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }
    public static String dateToString(Date date, String format) {
        SimpleDateFormat myDateFormate = new SimpleDateFormat(format);
        return (myDateFormate.format(date));
    }
    public static void main(String[] args) throws Exception {
        System.out.println(getOneHoursAgoTime());
    }

    public final static String now() {
        return simpleDateFormat.format(new Date());
    }

    /**
     * todo 加注释
     * @return
     */
    public final static Calendar nowUTC() {
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal;
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH)+1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int minute = cal.get(Calendar.MINUTE);
//        int sencond = cal.get(Calendar.SECOND);
    }

    //    public static void main(String[] args) {
//        Calendar cal =   nowUTC();
//                int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH)+1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int minute = cal.get(Calendar.MINUTE);
//        int sencond = cal.get(Calendar.SECOND);
//        System.out.println(year + "--" + month + "--" + day + "--" + hour + "--" + minute + "---" + sencond);
//    }

    /**
     * todo 加注释
     * @return
     */
    public static String getOneHoursAgoTime() {
        String oneHoursAgoTime = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, Calendar.SECOND - 180);
        //cal.set(Calendar.HOUR, Calendar.HOUR ); //把时间设置为当前时间-1小时，同理，也可以设置其他时间
        //cal.set(Calendar.MONTH, Calendar.MONTH - 1); //当前月前一月
        oneHoursAgoTime = simpleDateFormat.format(cal.getTime());//获取到完整的时间
        return oneHoursAgoTime;
    }


}
