package com.honghe.recordweb.util.base.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;

/**
 * 字符串工具类
 * 其他的一些常用方法参照中的类：import org.apache.commons.lang3.StringUtils
 *
 * @author zhuangwei
 */
public class StringUtil {

    private static Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private StringUtil() {
    }

    /**
     * 以字符串flag为标识分割一定规则的字符串，保存到List中
     *
     * @param name :分割的字符串
     * @param flag ：指定分割的字符
     * @return：返回集合
     */
    public static List<String> stringToList(String name, String flag) {
        // 结果数据
        List<String> list = new ArrayList<String>();
        if (name != null && name.length() > 0) {
            String[] arrays = name.split(flag);
            if (arrays != null && arrays.length > 0) {
                for (String array : arrays) {
                    list.add(array);
                }
            }
        }
        return list;
    }

    /**
     * 日期类型转换成String类型，格式yyyy-MM-dd
     *
     * @param date java.util.Date类型 需要转换的日期
     * @return 字符串 格式1999-12-12
     */
    public static String dateToString(Date date) {
        SimpleDateFormat myDateFormate = new SimpleDateFormat("yyyy-MM-dd");
        return (myDateFormate.format(date));
    }

    /**
     * 日期类型转换成String类型，格式yyyy-MM-dd format为自定义格式
     *
     * @param date   java.util.Date类型 需要转换的日期
     * @param format 字符串 自定义转换结果格式 yyyy MM dd hh mm ss SSS
     * @return 字符串 返回的相应格式日期
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat myDateFormate = new SimpleDateFormat(format);
        return (myDateFormate.format(date));
    }

    /**
     * String类型转换成日期类型，传入字符串格式yyyy-MM-dd
     *
     * @param sDate 字符串 格式为yyyy-MM-dd
     * @return java.util.Date 日期
     */
    public static Date stringToDate(String sDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String类型转换成日期类型，传入字符串格式为format
     *
     * @param sDate  字符串
     * @param format 字符串的格式
     * @return 转换的java.util.Date
     */
    public static Date stringToDate(String sDate, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否为空
     *
     * @param input 需要判断的字符串
     * @return 如果字符串为空 返回true 否则返回false
     */
    public static final boolean isEmpty(final String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        if (input.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static final String CHARS = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";

    /**
     * 获取长度为length的随机字符串
     *
     * @param length 需要获取的字符串长度
     * @return 随机产生的字符串
     */
    public static final String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int srcLength = CHARS.length();
        for (int i = 0; i < length; i++) {
            int index = (int) (srcLength * Math.random());
            sb.append(CHARS.charAt(index == srcLength ? index - 1 : index));
        }
        return sb.toString();
    }

    /**
     * 字符串转换为int 如果字符串为空 返回默认值def
     *
     * @param str 需要转换的字符串
     * @param def 默认结果
     * @return 如果字符串不为空，返回转换结果，否则返回默认值def
     */
    public static int string2Int(String str, int def) {
        if (str == null)
            return def;
        int result = def;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 字符串转换为long 如果字符串为空 返回默认值def
     *
     * @param str 需要转换的字符串
     * @param def 默认结果
     * @return 如果字符串不为空，返回转换结果，否则返回默认值def
     */
    public static long string2Long(String str, long def) {
        if (str == null)
            return def;
        long result = def;
        try {
            result = Long.parseLong(str);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 根据戳秒来统计具体时间
     *
     * @param second long类型整数
     * @return 根据戳秒转换的字符串
     */
    public static String secondToDateText(long second) {
        Date date = new Date();
        date.setTime(second * 1000);
        SimpleDateFormat myDateFormate = new SimpleDateFormat("yyyy-MM-dd");
        return (myDateFormate.format(date));
    }

    /**
     * 根据戳秒来统计具体时间,转换格式为format
     *
     * @param second long类型整数
     * @param format 需要转换的格式
     * @return 根据戳秒和相应格式转换的字符串
     */
    public static String secondToDateString(long second, String format) {
        Date date = new Date();
        date.setTime(second * 1000);
        SimpleDateFormat myDateFormate = new SimpleDateFormat(format);
        return (myDateFormate.format(date));
    }

    /**
     * 判断是一个数是否是整数
     *
     * @param str 字符串
     * @return 是整数返回true 否则返回false
     */
    public static boolean isNumber(String str) {
        if (str == null)
            return false;
        try {
            Long.parseLong(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 取得当前时间
     *
     * @return Date java.util.Date类型
     */
    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 字符串数组中是否包含指定的字符串。
     *
     * @param strings       字符串数组
     * @param string        字符串
     * @param caseSensitive 是否大小写敏感
     * @return 包含时返回true，否则返回false
     */
    public static boolean contains(String[] strings, String string,
                                   boolean caseSensitive) {
        for (int i = 0; i < strings.length; i++) {
            if (caseSensitive == true) {
                if (strings[i].equals(string)) {
                    return true;
                }
            } else {
                if (strings[i].equalsIgnoreCase(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串数组中是否包含指定的字符串。大小写敏感。
     *
     * @param strings 字符串数组
     * @param string  字符串
     * @return 包含时返回true，否则返回false
     */
    public static boolean contains(String[] strings, String string) {
        return contains(strings, string, true);
    }

    /**
     * 不区分大小写判定字符串数组中是否包含指定的字符串。
     *
     * @param strings 字符串数组
     * @param string  字符串
     * @return 包含时返回true，否则返回false
     */
    public static boolean containsIgnoreCase(String[] strings, String string) {
        return contains(strings, string, false);
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     *
     * @param array 字符串数组
     * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     */
    public static String combineStringArray(String[] array, String delim) {
        int length = array.length - 1;
        if (delim == null) {
            delim = "";
        }
        StringBuffer result = new StringBuffer(length * 8);
        for (int i = 0; i < length; i++) {
            result.append(array[i]);
            result.append(delim);
        }
        result.append(array[length]);
        return result.toString();
    }

    /**
     * 去除左边多余的空格。
     *
     * @param value 待去左边空格的字符串
     * @return 去掉左边空格后的字符串
     */
    public static String trimLeft(String value) {
        String result = value;
        if (result == null)
            return result;
        char ch[] = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isWhitespace(ch[i])) {
                index = i;
            } else {
                break;
            }
        }
        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }

    /**
     * 去除右边多余的空格。
     *
     * @param value 待去右边空格的字符串
     * @return 去掉右边空格后的字符串
     */
    public static String trimRight(String value) {
        String result = value;
        if (result == null)
            return result;
        char ch[] = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (Character.isWhitespace(ch[i])) {
                endIndex = i;
            } else {
                break;
            }
        }
        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    /**
     * 根据转义列表对字符串进行转义。
     *
     * @param source        待转义的字符串
     * @param escapeCharMap 转义列表
     * @return 转义后的字符串
     */
    public static String escapeCharacter(String source, HashMap escapeCharMap) {
        if (source == null || source.length() == 0)
            return source;
        if (escapeCharMap.size() == 0)
            return source;
        StringBuffer sb = new StringBuffer();
        StringCharacterIterator sci = new StringCharacterIterator(source);
        for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci
                .next()) {
            String character = String.valueOf(c);
            if (escapeCharMap.containsKey(character))
                character = (String) escapeCharMap.get(character);
            sb.append(character);
        }
        return sb.toString();
    }

    /**
     * 得到字符串的字节长度。
     *
     * @param source 字符串
     * @return 字符串的字节长度
     */
    public static int getByteLength(String source) {
        int len = 0;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            int highByte = c >>> 8;
            len += highByte == 0 ? 1 : 2;
        }
        return len;
    }

    /**
     * 仿javascript的escape方法
     *
     * @param src
     * @return
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j)) {
                tmp.append(j);
            } else if (j < 256) {
                tmp.append("%");
                if (j < 16) {
                    tmp.append("0");
                }
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * 仿javascript的unescape方法
     *
     * @param src
     * @return
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 字节码转换成16进制字符串
     *
     * @param b 需要转换的字节流
     * @return 以“:”分割的字符串
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + ":";
        }
        return hs.toUpperCase();
    }

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    logger.error(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 此方法将给出的字符串source使用delim划分为单词数组。
     *
     * @param source 需要进行划分的原字符串
     * @param delim  单词的分隔字符串
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
     * 如果delim为null则使用逗号作为分隔字符串。
     */
    public static String[] split(String source, String delim) {
        String[] wordLists;
        if (source == null) {
            wordLists = new String[1];
            wordLists[0] = source;
            return wordLists;
        }
        if (delim == null) {
            delim = ",";
        }
        StringTokenizer st = new StringTokenizer(source, delim);
        int total = st.countTokens();
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = st.nextToken();
        }
        return wordLists;
    }

    /**
     * 此方法将给出的字符串source使用delim划分为单词数组。
     *
     * @param source 需要进行划分的原字符串
     * @param delim  单词的分隔字符
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     */
    public static String[] split(String source, char delim) {
        return split(source, String.valueOf(delim));
    }

    /**
     * 此方法将给出的字符串source使用逗号划分为单词数组。
     *
     * @param source 需要进行划分的原字符串
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     */
    public static String[] split(String source) {
        return split(source, ",");
    }

    public static String iso2Utf(String src) {
        String target = "";
        try {
            target = new String(src.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return target;
    }

    public static String getTheCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        logger.debug(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getBrowser(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
//		int ind = agent.indexOf("(Windows");
//		agent = agent.substring(0,ind);
        return agent;
    }

    /**
     * 此方法用于比较时间格式为yyyy-MM-dd
     */
    public static int compare_date(String dayleft, String dayright) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date dt1 = null;
            Date dt2 = null;
            try {
                dt1 = df.parse(dayleft);
                dt2 = df.parse(dayright);
            } catch (Exception e) {
                logger.error("解析格式不正确！", e);
            }

            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 此方法用于比较时间格式为yyyy-MM-dd hh:mm:ss
     */
    public static int compare_time(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                logger.debug("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                logger.debug("dt1在dt2前");
                return -1;
            } else {
                logger.debug("同一天");
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;

    }

    public static String getUtf8Str(String content) {
        if (content == null) return null;
        String rtn = content;
        try {
            String encoding = StringUtil.getEncoding(content);
            rtn = new String(content.getBytes(encoding), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    /**
     * 判断字符串的编码
     */
    public static String getEncoding(String str) {
        String encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        return "";
    }

    /**
     * 字符串数组加分隔符转换为字符串
     */
    public static String join(String[] str, String spliter) {
        StringBuffer result = new StringBuffer();
        if (str.length <= 0) {
            return null;
        }
        if (spliter == null || "".equals(spliter)) {
            return null;
        }
        for (int i = 0; i < str.length - 1; i++) {

            if (str[i] != "") {
                result.append(str[i]);
                result.append(spliter);
            }
        }
        result.append(str[str.length - 1]);
        return result.toString();
    }

}
