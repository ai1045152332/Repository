package com.honghe.recordweb.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * Created by lxy on 2015/12/30.
 * <p/>
 * Property文件读写工具类
 */
public class GetparameterUtil {

    private final static Logger logger = Logger.getLogger(GetparameterUtil.class);

    //属性文件的路径
    private static String profilepath = "";
    /**
     * 采用静态方法
     */
    private static Properties props = new Properties();


    /**
     * 加载properties文件
     *
     * @param _profilepath
     */
    public static void Load(String _profilepath) throws IOException {
        profilepath = _profilepath;
            props.load(new FileInputStream(profilepath));
    }


    /**
     * 读取属性文件中相应键的值
     *
     * @param key 主键
     * @return String
     */
    public static String getKeyValue(String key) {
        return props.getProperty(key);
    }


    /**
     * 更新（或插入）一对properties信息(主键及其键值)
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     *
     * @param keyname  键名
     * @param keyvalue 键值
     */
    public static void writeProperties(String keyname, String keyvalue) {
        try {
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            logger.error("属性文件更新错误", e);
        }
    }


    /**
     * 更新properties文件的键值对
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     *
     * @param keyname  键名
     * @param keyvalue 键值
     */
    public void updateProperties(String keyname, String keyvalue) {
        try {
            props.load(new FileInputStream(profilepath));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            logger.error("属性文件更新错误", e);
        }
    }


    /**
     * 根据主键key读取主键的值value
     *
     * @param filePath 属性文件路径
     * @param key      键名
     */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    filePath));
            props.load(in);
            String value = props.getProperty(key);
            logger.debug(key + "键的值是：" + value);
            return value;
        } catch (Exception e) {
            logger.error("读取主键错误：filePath=" + filePath + " key=" + key, e);
            return null;
        }
    }


    //测试代码
    public static void main(String[] args) {
        try {
            GetparameterUtil.Load("D:/project_web/DManager/RecordWeb/src/config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = GetparameterUtil.getKeyValue("ResourceIp");
        logger.debug("操作完成 str=" + str);
    }
}
