package com.honghe.recordweb.service.frontend.resource;

import com.honghe.recordweb.util.base.util.ConfigUtil;
import java.util.List;


/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 * <p>增加NAS自动清理过期视频文件机制</p>
 * @author hthwx
 * @date 2016/8/2
 */
public class NasAutoDel
{


    private static List<Object[]> nasList;

    public static List<Object[]> getNasList() {
        return nasList;
    }

    public static void setNasList(List<Object[]> list) {
        nasList = list;
    }

    /**

     * 通过配置文件config获取设置nas自动删除时间间隔
     * @return
     */
   public static String[] autoDelTime()
   {
       try
       {
            String timeNasDelete = ConfigUtil.get("timeNasDelete");
           if(timeNasDelete != null && !"".equals(timeNasDelete))
           {
               return timeNasDelete.split(";");
           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return null;
   }
   /**
     * 在nas_auto_del根据key（nas+"id"）获取对应设置的nas自动删除时间周期
     * @param key
     * @return
     */
    public static String getValue(String key)
    {
        return RWNasProperties.getNasDelTime(key);
    }

    /**
     * 在nas_auto_del设置对应nas的自动删除时间周期
     * @param key
     * @param value
     */
    public static void setValue(String key,String value)
    {
        RWNasProperties.setNasDelTime(key,value);
    }

    /**
     * 在nas_auto_del 移除某个nas的自动删除时间周期
     * @param key
     */
    public static void removeValue(String key)
    {
        RWNasProperties.removeNasDelTime(key);
    }


    public static void main(String[] args) {
        try {
            String nasPath = "cifs://192.168.16.175/res";
            if(nasPath.toLowerCase().indexOf("cifs") > -1)
            {
                String nasIp = nasPath.substring(7,nasPath.length() -1);
                nasIp = nasIp.substring(0,nasIp.indexOf("/"));
                System.out.println(nasIp + "------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
