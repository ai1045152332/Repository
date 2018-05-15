package com.honghe.recordweb.service.frontend.event;

import com.honghe.recordhibernate.entity.Eventfield;
import com.honghe.recordhibernate.entity.Hostevent;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hthwx on 2015/2/3.
 */
public class EventHelper {
    private final static Logger logger = Logger.getLogger(EventHelper.class);
    private Map<String, String> eventMap = new HashMap<String, String>();
    private List<Hostevent> eventList = new LinkedList<Hostevent>();
    private List<Eventfield> fieldList = new LinkedList<Eventfield>();
    private static final String Event_TEXT_SEPARATOR = ":::::::";//事件字符串间隔符
    private static final String EVENT_FIELD_SEPARATOR = ";"; //字段属性间隔符
    private static final String EVENT_FIELD_FLAG = "=";//字段属性内容分隔符，
    private static final String LOG_FIELD_SEPARATOR = "------";
    private static final String LOG_FIELD_FLAG = ":";//日志属性内容间隔；
    private Map<String, String> currentEventFields = new HashMap<String, String>();//当前事件字段属性集合；为录制命令非日志的事件处理提供数据
    private String eventType = "";//当前事件的类型
    private static EventFieldService eventFieldService;
    private static HostEventService hostEventService;

    static {
        eventFieldService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(EventFieldService.class);
        hostEventService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostEventService.class);
    }

    /**
     * todo 加注释
     */
    public EventHelper() {
        eventMap = hostEventService.getMapService();
        eventList = hostEventService.getListService();
        fieldList = eventFieldService.getListService();
    }


    /**
     * 事件字符串整体拆分
     * @param eventTexts
     * @param separator
     * @return
     */
    public String[] stringSeparate(String eventTexts, String separator) {
        if (separator == null || separator.equals("")) {
            separator = this.Event_TEXT_SEPARATOR;//如果未指定分隔符
        }
        if (eventTexts == null || eventTexts.trim().equals("")) {
            return null;
        } else if (eventTexts.indexOf(separator) > -1) {
            return eventTexts.split(separator);
        } else {
            String[] tmp = new String[1];
            tmp[0] = eventTexts;
            return tmp;
        }
    }

    /**
     * 根据事件参数拼接日志内容字符串
     *
     * @param eventText 事件第三个参数（具体内容）
     * @param eventName 事件第四个参数（事件key）
     * @return String 最终拼接的字符串
     */
    public String generateLog(String eventText, String eventName) {
        String logStr = "";
        eventType = "";//重置当前事件的类型；
        currentEventFields.clear();//重置当前事件的字段属性
        Hostevent event = null;
        if (eventMap.containsKey(eventName)) {
            int eventId = 0;
            try {
                eventId = Integer.parseInt(eventMap.get(eventName));
                for (Hostevent he : eventList) {
                    if (he.getEventId() == eventId) {
                        eventType = he.getEventType();
                        logStr += he.getEventContent() + LOG_FIELD_SEPARATOR + generateFieldLog(logStr, eventText, he);
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("", e);
            }
        }
        return logStr;
    }

    /**
     * 根据字段内容字符串,解析生成日志内容
     *
     * @param logStr    字符串返回值
     * @param eventText 事件第三个参数（具体内容）
     * @param he        事件key对应的详细信息
     * @return String 拼接的字符串
     */
    public String generateFieldLog(String logStr, String eventText, Hostevent he) {
        List<Eventfield> list = new LinkedList<Eventfield>();
        for (Eventfield ef : fieldList) {
            if (ef.getFieldBelong() == he.getEventId()) {
                // list.add(ef);
                logStr = logByEventField(logStr, ef, eventText);
            }
        }
        return logStr;
    }

    /**
     * 根据某一字符串信息生成一段日志信息
     *
     * @param logStr    字符串返回值
     * @param ef        对应一条字段内容的信息
     * @param eventText 事件第三个参数（具体内容）
     * @return string 拼接的部分字符串
     */
    public String logByEventField(String logStr, Eventfield ef, String eventText) {
        if (eventText != null && !eventText.equals("") && eventText.length() > 0) {
            String[] strArr = eventText.split(EVENT_FIELD_SEPARATOR);
            for (String str : strArr) {
                if (str.indexOf(ef.getFieldName()) > -1) {
                    String value = str.toUpperCase().trim().replaceAll(ef.getFieldName().toUpperCase(), "").trim().replaceAll(EVENT_FIELD_FLAG, "").trim();
                    if (ef.getFieldName().equals("NASPath")) {
                        value = str.trim().replaceAll(ef.getFieldName(), "").trim().replaceAll(EVENT_FIELD_FLAG, "").trim();
                       /*if(value.substring(0,1).equals(":"))
                       {
                           value.replace(":","");
                       }*/
                    }
                    logStr += ef.getFieldContent() + LOG_FIELD_FLAG + value + ";";
                    currentEventFields.put(ef.getFieldName(), value);//记录当前字段对应的值
                }
            }
        }
        return logStr;
    }

    public Map<String, String> getCurrentEventFields() {
        return currentEventFields;
    }

    public String getEventType() {
        return eventType;
    }

}

