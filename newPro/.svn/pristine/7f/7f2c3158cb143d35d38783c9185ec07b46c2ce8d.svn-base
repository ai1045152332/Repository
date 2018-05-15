package com.honghe.recordweb.action.frontend.news;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.action.frontend.news.entity.Item;
import com.honghe.recordweb.service.frontend.news.ItemService;
import com.honghe.recordweb.service.frontend.news.NewsService;
import com.honghe.recordweb.util.base.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.faceless.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/13
 */
@Controller
@Scope(value = "prototype")
public class ItemAction extends BaseAction{
    private final static Logger logger = Logger.getLogger(ItemAction.class);
    private String soupa;
    private String params;
    private String prid;
    private String pid;
    private String repa;
    @Resource
    NewsService newsService;
    @Resource
    private ItemService itemService;
    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    private static HashMap synObjectMap=new HashMap();

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRepa() {
        return repa;
    }

    public void setRepa(String repa) {
        this.repa = repa;
    }

    public String getSoupa() {
        return soupa;
    }

    public void setSoupa(String soupa) {
        this.soupa = soupa;
    }


    /**
     * //todo 加注释
     */
    public static Object getSynObject(String username){
        Set<Map.Entry> entrySet=synObjectMap.entrySet();
        for(Map.Entry e:entrySet){
            if(e.getKey().equals(username)){
                return e.getValue();
            }
        }
        return new Object();
    }

    /**
     * //todo 加注释
     */
    public void edit(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        String quipub = "0";
        String s = "true";
        try {
            JSONArray itemArray = new JSONArray(params);
            JSONObject itemObject = itemArray.getJSONObject(0);
            Item item = new Item();
//            if(quipub.equals("1")){
//                item = itemService.parseJson2ItemQ(prid, pid, itemObject, userId, prid, quipub);
//            }else{
                item = itemService.parseJson2Item(prid, pid, itemObject, userId, prid);
//            }
            String t = itemObject.getString("t");
            // 视频  图片 flash
            if(t.equals("2") || t.equals("11") || t.equals("1") || t.equals("15") ||
                    t.equals("4") || t.equals("8") || t.equals("18") || t.equals("9")){// “18”-->PPT
                String blockId = itemObject.getString("bid");
                s = "true-"+blockId;
            }
           // newsService.mkdirFtp(item);
            synchronized (getSynObject(userId)) {
//                if(quipub.equals("1")){
//                    newsService.modifyItemQ(userId,item);
//                }else{
                    itemService.modifyItem(userId,item);
//                }
            }
        } catch (JSONException e) {
            logger.error("",e);
            sendMsg("false");
        }
        sendMsg(s);
    }

    /**
     * //todo 加注释
     * @throws Exception
     */
    public void add() throws Exception{
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        String s= "true";
        try {
            List<Item> itemList = new ArrayList<Item>();
            JSONArray itemArray = new JSONArray(params);
            for(int i=0;i<itemArray.length();i++){
                JSONObject itemObject = itemArray.getJSONObject(i);
                String t = itemObject.getString("t");
                Item item = new Item();
                // 视频  图片 flash
                if(t.equals("2") || t.equals("1") || t.equals("11")){
                    String blockId = itemObject.getString("bid");
                    item = itemService.parseJson2Item(prid, pid, itemObject, userId, prid);
                    //newsService.mkdirFtp(item);
                    s = "true-"+blockId;
                }else{
                    item = itemService.parseJson2Item(prid, pid, itemObject, userId, prid);
                    //newsService.mkdirFtp(item);
                }
               // synchronized (ProjectAction.getSynObject(userId)) {
                    itemList.add(item);
               // }

            }
            itemService.addItem(userId,itemList);
        } catch (JSONException e) {
            logger.error("",e);
        //    e.printStackTrace();
            sendMsg("false");
        }
        sendMsg(s);
    }

    /**
     * //todo 加注释
     */
    public void del(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        try {
            List<Item> itemList = new ArrayList<Item>();
            JSONArray itemArray = new JSONArray(params);
            for(int i=0;i<itemArray.length();i++){
                JSONObject itemObject = itemArray.getJSONObject(i);

                Item item = new Item();
                item.setPid(pid);
                item.setPrid(prid);
                item.setBid(itemObject.getString("bid"));
                itemList.add(item);
            }

            itemService.removeItem(userId, itemList);
        } catch (JSONException e) {
            logger.error("",e);
            sendMsg("false");
        }

        sendMsg("true");
    }

    /**
     * //todo 加注释
     */
    public void sort(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
//        String quipub = "0";
//        if(itemForm.getQuipub()!=null && !itemForm.getQuipub().equals("")){
//            quipub = itemForm.getQuipub(); // 快速发布的标志
//        }
        try {
            List<Item> itemList = new ArrayList<Item>();
            JSONArray itemArray = new JSONArray(params);
            for(int i=0;i<itemArray.length();i++){
                JSONObject itemObject = itemArray.getJSONObject(i);

                Item item = new Item();

                item.setPrid(prid);
                item.setPid(pid);

                item.setBid(itemObject.getString("bid"));
                item.setIdx(itemObject.getString("idx"));
                itemList.add(item);
            }
           // if(quipub.equals("1")){
            //    newsService.sortItemQ(userId, itemList);
           // }else{
                itemService.sortItem(userId, itemList);
            //}

        } catch (JSONException e) {
            logger.error("",e);
            sendMsg("false");
        }
        sendMsg("true");
    }

    // 当用户选择PPT文件后  同时获取到该PPT对应的所有Image文件
    public void selectPpt(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        try {
            itemService.copyPPtResource(userId,prid,repa,soupa);
            String[] tempMaps=repa.split("/");
            String folderName=tempMaps[tempMaps.length-2];
            String oldPath=repa.substring(0, repa.lastIndexOf("/"));
            String path= SearchPathTools.getResources(oldPath);
            File f = new File(path);
            File[] files = f.listFiles();
            List<HashMap> listHam = new ArrayList<HashMap>();
            if(files != null) {
                for (int n = 0; n < files.length - 2; n++) {
                    HashMap<String, String> hamp = new HashMap<String, String>();
                    hamp.put("title", "");
                    hamp.put("path", soupa);
                    hamp.put("mapped", "/msgResource/" + userId + "/" + prid + "/media/" + folderName + "/" + (n + 1) + ".png?uuid=" + org.faceless.util.UUID.getUUID());
                    listHam.add(hamp);
                }
            }
            writeJson(listHam);
        } catch (Exception e) {
            logger.error("",e);
       //     e.printStackTrace();
        }
    }

}
