package com.honghe.recordweb.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wj on 2014-09-30.
 */
public class CommonManager {
    private final static Logger logger = Logger.getLogger(CommonManager.class);
    private static HashMap<String,String> actionMap ;
    private static HashMap<String,String> pageMap ;
    private static final String DEFAULT_AUTHORITY_FILE = "authority.xml";
    private static final String DEFAULT_AUTHORITY_NODE_ACTIONS = "actions";
    private static final String DEFAULT_AUTHORITY_NODE_PAGES = "pages";
    private static final String DEFAULT_AUTHORITY_NODE_KEY = "id";
    private static final String DEFAULT_AUTHORITY_NODE_value = "name";
    public static HashMap<String,String> getActionMap(){
        return actionMap;
    }
    public static HashMap<String,String> getPageMap(){
        return pageMap;
    }


    static {
        init();
    }

    /**
     * todo 加注释
     */
    public static void init()
    {
        try {
            Document document = getDocument(DEFAULT_AUTHORITY_FILE);
            actionMap = getMap(document, DEFAULT_AUTHORITY_NODE_ACTIONS, DEFAULT_AUTHORITY_NODE_KEY, DEFAULT_AUTHORITY_NODE_value);
            pageMap = getMap(document, DEFAULT_AUTHORITY_NODE_PAGES, DEFAULT_AUTHORITY_NODE_KEY, DEFAULT_AUTHORITY_NODE_value);
        }
        catch (Exception e) {
            logger.error("",e);
            //e.printStackTrace();
        }
    }

    public CommonManager(){

    }



//    public static void main(String[] args) {
//
//    }

    /**
     * todo 加注释
     * @param fileName
     * @return
     */
    private static Document getDocument(String fileName) {
        try {

            SAXReader reader = new SAXReader();
            Document document = null;
            document = reader.read(CommonManager.class.getClassLoader().getResourceAsStream(fileName));
            return document;
        }
        catch (Exception e) {
            logger.error("",e);
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * todo 加注释
     * @param document
     * @param nodeName
     * @return
     */
    private static Element getElement(Document document,String nodeName)
    {
        try {
            Element rootElm = document.getRootElement();
            Element nodeElm = rootElm.element(nodeName);
            return nodeElm;
        }
        catch (Exception e)
        {
            logger.error("",e);
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * todo 加注释
     * @param document
     * @param nodeName
     * @param key
     * @param value
     * @return
     */
    //权限对应的actionname部分组成map，键值为actionname,值为id。
    private static HashMap<String,String> getMap(Document document,String nodeName,String key,String value)
    {
        HashMap<String,String> map = new HashMap<String,String>();
        try
        {
            Element nodeElm = getElement(document,nodeName);
            if(nodeElm !=null) {
                for (Iterator i = nodeElm.elementIterator(); i.hasNext(); ) {
                    Element element = (Element) i.next();
                    String id = element.attributeValue(key);
                    List<Element> temp = element.elements();
                    for (int j = 0, length = temp.size(); j < length; j++) {
                        String actionName = temp.get(j).attribute(value).getText();
                        //action是否重名
                        if (map.containsKey(actionName)) {
                            map.put(actionName, map.get(actionName) + " " + id);
                        } else {
                            map.put(actionName, id);
                        }
                    }
                }
            }
            return map;
        }
       catch (Exception e)
       {
           logger.error("",e);
           //e.printStackTrace();
           return null;
       }
    }
}