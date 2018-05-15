package com.honghe.recordweb.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by wuzhenzhen on 2017/03/14.
 */
public final class JSONUtil {

    private final static Logger logger = Logger.getLogger(JSONUtil.class);

    private JSONUtil() {

    }

    /**
     * 封装将json对象转换为java集合对象
     *
     * @param clazz java集合类型
     * @param jsonArray json对象
     * @return java集合对象
     */
    public static  <T> List<T> getJavaCollection(T clazz, JSONArray jsonArray) {
        List<T> objs=null;
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List) JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject= JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }

    /**
     * 将JSONObjec对象转换成Map集合
     *
     * @param json
     * @return
     */
    public static HashMap<String, Object> jsonToMap(JSONObject json) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Set keys = json.keySet();
        for (Object key : keys) {
            Object o = json.get(String.valueOf(key));
            if (o instanceof JSONArray)
                map.put((String) key, jsonToList(JSONArray.fromObject(o)));
            else if (o instanceof JSONObject)
                map.put((String) key, jsonToMap((JSONObject) o));
            else
                map.put((String) key, o);
        }
        return map;
    }

    public static Object jsonToList(JSONArray json) {
        List<Object> list = new ArrayList<Object>();
        if (json != null && json.size()!=0) {
            for (Object o : json) {
                if (o instanceof JSONArray)
                    list.add(jsonToList(JSONArray.fromObject(o)));
                else if (o instanceof JSONObject)
                    list.add(jsonToMap((JSONObject) o));
                else
                    list.add(o);
            }
        }
        return list;
    }

    /**
     * 将Json格式字符串转换成Map
     *
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) {

        JSONObject jsonObject = JSONObject.fromObject(jsonString);

        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;

        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }
}
