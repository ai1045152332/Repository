package com.honghe.recordweb.service.frontend.settings;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by wj on 2014-12-06.
 * 分辨率对应的具体数值
 */
public  class Resolution {


    private static HashMap<Integer,ResolutionValue> resolutionMap = new HashMap<Integer, ResolutionValue>();
    static {
        init();
    }

    /**
     * todo 加注释
     */
    public static void init()
    {
        ResolutionValue resValue1080 = new ResolutionValue();
        resValue1080.setX(1920);
        resValue1080.setY(1080);
        resolutionMap.put(1080,resValue1080);
        ResolutionValue resValue720 = new ResolutionValue();
        resValue720.setX(1280);
        resValue720.setY(720);
        resolutionMap.put(720,resValue720);
    }

    public Resolution(){

    }

    /**
     * todo 加注释
     * @param resolutionValue
     * @return
     */
    public final static Integer getResolutionKey(ResolutionValue resolutionValue)
    {
        Iterator<Integer> it= resolutionMap.keySet().iterator();
        while(it.hasNext())
        {
            Integer key=it.next();
            ResolutionValue resolutionValue1 = (ResolutionValue)resolutionMap.get(key);
            if(resolutionValue.getX() == resolutionValue1.getX() && resolutionValue.getY() == resolutionValue1.getY())
            {
                return key;
            }
        }
        return null;
    }

    /**
     * todo 加注释
     * @param key
     * @return
     */
    public final static ResolutionValue getResolutionValue(Integer key)
    {
        return resolutionMap.get(key);
    }
}
