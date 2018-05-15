package com.honghe.recordweb.service.frontend.resource;

import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.util.RWProperties;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author hthwx
 * @date 2016/8/2
 */
public class RWNasProperties
{

    private final static Logger logger = Logger.getLogger(RWNasProperties.class);
    private static final String FILE_NAME_CONFIG = "nas_auto_del.properties"; //配置文件

    public static Map<String, String> getNasMap() {
        return NAS_MAP;
    }

    private static Map<String,String> NAS_MAP = new HashMap<>(); //管理配置文件内容
    private RWNasProperties() {

    }

    /**
     * todo 加注释
     */
    public static void init() {
        Properties properties = RWProperties.readPropertiesFile(FILE_NAME_CONFIG);
        List<Object[]> nasList = NasAutoDel.getNasList();
        if(properties != null && nasList != null)
        {
            for(Object[] objs : nasList)
            {
                String nasId = objs[0].toString();
                Object delTime = properties.get("nas" + nasId);
                if(delTime != null)
                {
                    //System.setProperty("nas" + nasId,String.valueOf(delTime));
                    NAS_MAP.put("nas" + nasId,String.valueOf(delTime));
                }
            }
        }
    }

    /**
     * 设置时间
     * @param key
     * @param value
     */
    public static void setNasDelTime(String key,String value)
    {
        if(key != null)
        {
            NAS_MAP.put("nas" + key,value);
            writePropertiesFile(NAS_MAP);
            logger.error("nas自动清理时间写入配置文件：key=" + key + ",value=" + value);
        }
    }

    /**
     * 获取时间设置
     * @param key
     * @return
     */
    public static String getNasDelTime(String key)
    {
        return NAS_MAP.get("nas" + key);
    }

    /**
     * 删除时间设置
     * @param key
     */
    public static void removeNasDelTime(String key)
    {
        if(key != null && NAS_MAP.containsKey(key))
        {
            NAS_MAP.remove(key);
            writePropertiesFile(NAS_MAP);
        }
    }

    /**
     * 写资源文件，含中文
     * @param params
     */
    private static void writePropertiesFile(Map<String,String> params)
    {
        Properties properties = new Properties();
        try
        {

            OutputStream outputStream = new FileOutputStream(ConfigUtil.class.getResource("/" + FILE_NAME_CONFIG).getPath());
            for(String key : params.keySet())
            {
                properties.setProperty(key, params.get(key));
            }
            properties.store(outputStream, "");
            outputStream.close();
        } catch (IOException e) {
            logger.error("写资源文件异常", e);
//            e.printStackTrace();
        }
    }


}
