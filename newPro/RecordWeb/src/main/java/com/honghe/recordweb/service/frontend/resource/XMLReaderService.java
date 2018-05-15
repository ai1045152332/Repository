package com.honghe.recordweb.service.frontend.resource;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hthwx on 2015/3/31.
 */

public class XMLReaderService {
    private final static Logger logger = Logger.getLogger(XMLReaderService.class);
    private Map<String, String> map = new HashMap<>(); //courseInfo信息
    private List<Map<String, String>> list = new ArrayList<>(); //video信息
    private String xmlPath;
    private String movieName;
    private static final String MOVIE_FILE_TYPE = "MOVIE.MP4";

    public XMLReaderService(String xml) {
        this.xmlPath = xml;
    }

    /**
     * todo 加注释
     */
    public void readXml() {
        Element element = null;
        // 可以使用绝对路径
        File f = new File(xmlPath);
        if (!f.exists()) {
            return;
        }
        // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = null;
        int read_num = 0;
        while(read_num<4) {
            try {
                // 返回documentBuilderFactory对象
                dbf = DocumentBuilderFactory.newInstance();
                // 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
                db = dbf.newDocumentBuilder();
                // 得到一个DOM并返回给document对象
                Document dt = db.parse(f);
                // 得到一个elment根元素

                element = dt.getDocumentElement();
                // 获得根节点
                //    System.out.println("根元素：" + element.getNodeName() + "---" + element.getAttributes().getNamedItem("CourseTitle").getNodeValue());
                // 获得根元素下的子节点
                NodeList childNodes = element.getChildNodes();
                // 遍历这些子节点
                for (int i = 0; i < childNodes.getLength(); i++) {
                    // 获得每个对应位置i的结点
                    Node node1 = childNodes.item(i);
                    if ("CourseInfo".equals(node1.getNodeName())) {
                        //资源分类写入列表
//                    map.put("host_name", ((DeferredElementImpl) node1).hasAttribute("host") ? ((DeferredElementImpl) node1).getAttribute("host") : "");
                        map.put("res_course", ((DeferredElementImpl) node1).hasAttribute("MainTitle") ? ((DeferredElementImpl) node1).getAttribute("MainTitle") : "");
                        // map.put("res_url",node1.getAttributes().getNamedItem("res_url").getNodeValue());
                        //  map.put("res_path",node1.getAttributes().getNamedItem("res_path").getNodeValue());
                        // map.put("res_resolution",node1.getAttributes().getNamedItem("res_resolution").getNodeValue());
                        // map.put("res_class",node1.getAttributes().getNamedItem("ClassName").getNodeValue());
                        map.put("res_grade", ((DeferredElementImpl) node1).hasAttribute("ClassName") ? ((DeferredElementImpl) node1).getAttribute("ClassName") : "");
                        map.put("res_speaker", ((DeferredElementImpl) node1).hasAttribute("Teacher") ? ((DeferredElementImpl) node1).getAttribute("Teacher") : "");
                        map.put("res_subject", ((DeferredElementImpl) node1).hasAttribute("SubjectName") ? ((DeferredElementImpl) node1).getAttribute("SubjectName") : "");
                        map.put("res_title", ((DeferredElementImpl) node1).hasAttribute("CourseTitle") ? ((DeferredElementImpl) node1).getAttribute("CourseTitle") : "");
                        map.put("res_school", ((DeferredElementImpl) node1).hasAttribute("School") ? ((DeferredElementImpl) node1).getAttribute("School") : "");
                        map.put("res_thumb", ((DeferredElementImpl) node1).hasAttribute("Thumb") ? ((DeferredElementImpl) node1).getAttribute("Thumb") : "");
                    } else if ("Video".equals(node1.getNodeName())) {
                        //资源文件写入列表
                        Map<String, String> tempVideoMap = new HashMap<>();
                        try {
                            tempVideoMap.put("video_thumb", ((DeferredElementImpl) node1).hasAttribute("Thumb") ? ((DeferredElementImpl) node1).getAttribute("Thumb") : "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        try {
                            String videoName = ((DeferredElementImpl) node1).hasAttribute("Name") ? ((DeferredElementImpl) node1).getAttribute("Name") : ((DeferredElementImpl) node1).getAttribute("name");
                            if (!videoName.equals("") && videoName.indexOf(".") > 0) {
                                String tmpStr = videoName.substring(videoName.indexOf(".") + 1);
                                if (MOVIE_FILE_TYPE.equals(videoName.toUpperCase())) {
                                    this.movieName = videoName;
                                }
                            }
                            tempVideoMap.put("video_name", videoName);
                            logger.debug("video 信息读取~~~~~~~~~~~~~33-2~~~~~~~~~~~~~" + tempVideoMap.toString());
                            list.add(tempVideoMap);
                        } catch (Exception e) {
                            logger.error("", e);
//                        e.printStackTrace();
                        }

                    }
                }
            } catch (Exception e) {
                logger.error("", e);
//            e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            read_num++;
        }
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}


