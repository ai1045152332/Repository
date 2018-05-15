package com.honghe.recordweb.util;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lch on 2015/1/16.
 */
public class RWProperties {

    private final static Logger logger = Logger.getLogger(RWProperties.class);

    private RWProperties() {

    }

    /**
     * 初始化
     */
    //去掉 system.properties配置文件
    public static void init() {
//        Properties properties = RWProperties.readPropertiesFile("system.properties");
//        if (properties != null) {
//            Object ftpUserName = properties.get("FtpUserName");
//            Object ftpPassword = properties.get("FtpPassword");
//            Object resourceIp = properties.get("ResourceIp");
//            Object rtmpServerIp = properties.get("RtmpServerIp");
//            Object rtmpServerPort = properties.get("RtmpServerPort");
//            if (ftpUserName != null && !ftpUserName.toString().equals("")) {
//                System.setProperty("FtpUserName", ftpUserName.toString());
//            } else {
//                System.setProperty("FtpUserName", "");
//            }
//            if (ftpPassword != null && !ftpPassword.toString().equals("")) {
//                System.setProperty("FtpPassword", ftpPassword.toString());
//            } else {
//                System.setProperty("FtpPassword", "");
//            }
//            if (resourceIp != null && !resourceIp.toString().equals("")) {
//                System.setProperty("ResourceIp", resourceIp.toString());
//            } else {
//                System.setProperty("ResourceIp", "localhost");
//            }
//            if (rtmpServerIp != null && !rtmpServerIp.toString().equals("")) {
//                System.setProperty("RtmpServerIp", rtmpServerIp.toString());
//            } else {
//                System.setProperty("RtmpServerIp", "localhost");
//            }
//            if (rtmpServerPort != null && !rtmpServerPort.toString().equals("")) {
//                System.setProperty("RtmpServerPort", rtmpServerPort.toString());
//            } else {
//                System.setProperty("RtmpServerPort", "6000");
//            }
//        }
    }

    /**
     * 读取资源文件,并处理中文乱码
     * @param filename
     * @param key
     * @return
     */
    public static String readPropertiesFile(String filename, String key) {
        Properties properties = new Properties();
        try {
            properties.load(RWProperties.class.getResourceAsStream("/" + filename));
        } catch (IOException e) {
            logger.error("", e);
        }
        String res = properties.getProperty(key);
        if (res == null) {
            res = "";
        } else {
            try {
                res = new String(res.getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
            } catch (UnsupportedEncodingException e) {
                logger.error("处理中文乱码异常", e);
                res = "";
            }
        }
        return res;
    }

    /**
     * todo 加注释
     * @param filename
     * @return
     */
    public static Properties readPropertiesFile(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(RWProperties.class.getResourceAsStream("/" + filename));
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
        return properties;
    }

    /**
     * 写资源文件，含中文
     * @param filename
     * @param key
     * @param value
     */
    public static void writePropertiesFile(String filename, String key, String value) {
        Properties properties = new Properties();
        try {

            OutputStream outputStream = new FileOutputStream(RWProperties.class.getResource("/" + filename).getPath());
            properties.setProperty(key, value);
            properties.store(outputStream, "");
            outputStream.close();
        } catch (IOException e) {
            logger.error("写资源文件异常", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(RWProperties.readPropertiesFile("system.properties", "NetworkInterface"));
        RWProperties.writePropertiesFile("system.properties", "aa", "bb");
    }
}
