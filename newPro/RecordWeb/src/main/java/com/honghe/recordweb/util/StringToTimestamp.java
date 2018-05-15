package com.honghe.recordweb.util;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by yanxue on 2015-06-24.
 */
public class StringToTimestamp extends StrutsTypeConverter {

    private final static Logger logger = Logger.getLogger(StringToTimestamp.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * todo 加注释
     * @param map
     * @param strings
     * @param aClass
     * @return
     */
    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        String d = strings[0];

        if (!d.equals("")) {
            try {
                return new Timestamp(format.parse(d).getTime());

            } catch (ParseException e) {
                logger.error("", e);
//                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String convertToString(Map map, Object o) {
        return format.format(o);
    }
}
