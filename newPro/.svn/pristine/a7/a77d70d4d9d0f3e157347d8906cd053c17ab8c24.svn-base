package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordhibernate.entity.tools.FileUtil;
import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import com.honghe.recordweb.action.frontend.news.entity.*;
import com.honghe.recordweb.util.base.util.StringUtil;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/25
 */
@Service("ItemService")
public class ItemService {
    @Resource
    NewsService newsService;

    /**
     * todo 加注释
     * @param programId
     * @param pageId
     * @param json
     * @param userName
     * @param prid
     * @param quipub
     * @return
     */
    public Item parseJson2ItemQ(String programId,String pageId,JSONObject json,String userName,String prid,String quipub){
        try {
            String blockId = json.getString("bid");
            String xPos = json.getString("x");
            String yPos = json.getString("y");
            String index = json.getString("idx");
            String html = json.getString("iH");

            String t = json.getString("t");//todo 加注释
            if(t.equals("1")){//todo 加注释
                String rds = json.getString("rds");
                String path = json.getString("path");
                String w = json.getString("w");
                String h = json.getString("h");
                String mapped = json.getString("mapped");//resourceFileDao.getPathByVirtual(false, path);
                ItemImage itemImage = new ItemImage();
                itemImage.setPrid(programId);
                itemImage.setPid(pageId);
                itemImage.setBid(blockId);
                itemImage.setT(t);
                itemImage.setX(xPos);
                itemImage.setY(yPos);
                itemImage.setIdx(index);

                itemImage.setW(w);
                itemImage.setH(h);
                itemImage.setPath(path);
                itemImage.setRds(rds);
                if(mapped.indexOf("edit")>0){
                    itemImage.setiH(html);
                    itemImage.setMapped(mapped);
                }else{
                    //处理mapped
//                    String pcMap=mapped;
//                    String[] pcTemps =pcMap.split("/");
//                    String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                    itemImage.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
//                    String[] tempMaps=mapped.split("/");
//                    String folderName=tempMaps[tempMaps.length-2];
//                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
//                    String copyPath=SearchPathTools.getResources(oldPath);
//                    copyMD5Q(userName,prid,copyPath,folderName);
                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySourceQ(userName,prid,srcPath,name);
                    //建立源文件与原名称对应
//                    String[] names=path.split("/");
//                    String srcname =names[names.length-1];
//                    String content=oldPath2[3]+"="+srcname;
//                    getmappedQ(userName,prid,content);
                }
                return itemImage;
            }
            else if(t.equals("2")){//todo 加注释
                String path = json.getString("path");
                String mapped=json.getString("mapped");
                String w = json.getString("w");
                String h = json.getString("h");

                ItemVideo itemVideo = new ItemVideo();

                itemVideo.setPrid(programId);
                itemVideo.setPid(pageId);
                itemVideo.setBid(blockId);
                itemVideo.setT(t);
                itemVideo.setX(xPos);
                itemVideo.setY(yPos);
                itemVideo.setW(w);
                itemVideo.setH(h);
                itemVideo.setIdx(index);
                itemVideo.setPath(path);
                if(mapped.indexOf("edit")>0){
                    itemVideo.setMapped(mapped);
                    itemVideo.setiH(html);
                }else{
                    //处理mapped
//                    String pcMap=mapped;
//                    String[] pcTemps =pcMap.split("/");
//                    String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                    itemVideo.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
//                    String[] tempMaps=mapped.split("/");
//                    String folderName=tempMaps[tempMaps.length-2];
//                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
//                    String copyPath=SearchPathTools.getResources(oldPath);
//                    if(folderName.equals("defaultvideothumb")){
//                        copyPath = "../webapps/ManagementCenter/data/defaultvideothumb/";
//                    }
//                    copyMD5Q(userName,prid,copyPath,folderName);
                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySourceQ(userName,prid,srcPath,name);
                    //建立源文件与原名称对应
//                    if(path.indexOf("/") > -1){
//                        String[] names=path.split("/");
//                        if(names.length > 1){
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmappedQ(userName,prid,content);
//                        }
//                    }
                }
                return itemVideo;
            }
            else if(t.equals("3")){//todo 加注释
                String rds = json.getString("rds");
                String bdC = json.getString("bdC");
                String bgC = json.getString("bgC");
                String bdW = json.getString("bdW");
                String w = json.getString("w");
                String h = json.getString("h");

                ItemRectangular itemRectangular = new ItemRectangular();

                itemRectangular.setPrid(programId);
                itemRectangular.setPid(pageId);
                itemRectangular.setBid(blockId);
                itemRectangular.setT(t);
                itemRectangular.setX(xPos);
                itemRectangular.setY(yPos);
                itemRectangular.setIdx(index);
                itemRectangular.setiH(html);

                itemRectangular.setBdC(bdC);
                itemRectangular.setBgC(bgC);
                itemRectangular.setBdW(bdW);
                itemRectangular.setRds(rds);
                itemRectangular.setW(w);
                itemRectangular.setH(h);

                return itemRectangular;
            }
            else if(t.equals("4")||t.equals("9")||t.equals("10")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String isFtp = json.getString("isFtp");
                String during = json.getString("during");

                ItemCarousel caroursel = new ItemCarousel();
                caroursel.setPrid(programId);
                caroursel.setPid(pageId);
                caroursel.setBid(blockId);
                caroursel.setT(t);
                caroursel.setX(xPos);
                caroursel.setY(yPos);
                caroursel.setIdx(index);
                caroursel.setiH(html);

                caroursel.setScX(xSc);
                caroursel.setScY(ySc);
                caroursel.setIsFtp(isFtp);
                caroursel.setDuring(during);

                List<CarouseImage> imageList = new ArrayList<CarouseImage>();

                JSONArray imageArray = json.getJSONArray("imgLoop");
                if(imageArray!=null){
                    for(int i=0;i<imageArray.length();i++){
                        CarouseImage image = new CarouseImage();
                        JSONObject object = imageArray.getJSONObject(i);
                        String title = object.getString("title");
                        String path = object.getString("path");
                        String loopMap=object.getString("mapped");
                        if(loopMap.indexOf("edit")>0){
                            image.setMapped(loopMap);
                        }else{
                            //处理mapped
//                            String[] pcTemps =loopMap.split("/");
//                            String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                            String mapped = pcMapped;
//                            image.setMapped(mapped);
                            //end
                            //拷贝资源文件至md5文件夹下
//                            String[] tempMaps=loopMap.split("/");
//                            String folderName=tempMaps[tempMaps.length-2];
//                            String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
//                            String copyPath=SearchPathTools.getResources(oldPath);
//                            copyMD5Q(userName,prid,copyPath,folderName);
                            //拷贝源文件
//                            String[] oldPath2=loopMap.split("/");
//                            String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                            String name=oldPath2[3];
//                            String srcPath=SearchPathTools.getResources(respath);
//                            copySourceQ(userName,prid,srcPath,name);
                            //end
                            //建立源文件与原名称对应
//                            String[] names=path.split("/");
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmappedQ(userName,prid,content);
                        }
                        image.setTitle(title);
                        image.setPath(path);
                        imageList.add(image);
                    }
                }
                caroursel.setImageList(imageList);
                return caroursel;
            }else if(t.equals("18")){ // “18”-->PPT
                String h = json.getString("h");
                String w = json.getString("w");
                String isFtp = json.getString("isFtp");
                String during = json.getString("during");

                ItemSwipe caroursel = new ItemSwipe();
                caroursel.setPrid(programId);
                caroursel.setPid(pageId);
                caroursel.setBid(blockId);
                caroursel.setT(t);
                caroursel.setX(xPos);
                caroursel.setY(yPos);
                caroursel.setIdx(index);
                caroursel.setiH(html);
                caroursel.setIsFtp(isFtp);

                caroursel.setH(h);
                caroursel.setW(w);
                caroursel.setDuring(during);

                List<CarouseImage> imageList = new ArrayList<CarouseImage>();

                JSONArray imageArray = json.getJSONArray("imgLoop");
                if(imageArray!=null){
                    for(int i=0;i<imageArray.length();i++){
                        CarouseImage image = new CarouseImage();
                        JSONObject object = imageArray.getJSONObject(i);
                        String title = object.getString("title");
                        String path = object.getString("path");
                        String mapped=object.getString("mapped");
                        image.setTitle(title);
                        image.setPath(path);
                        //处理mapped
//                        String loopMap= resourceFileDao.getPathByVirtualPpt(false, path);
//                        String[] pcTemps =loopMap.split("/");
//                        String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                        image.setMapped(pcMapped);
                        //end
                        //拷贝资源文件至md5文件夹下
//                        String[] tempMaps=loopMap.split("/");
//                        String folderName=tempMaps[tempMaps.length-2];
//                        String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
//                        String copyPath=SearchPathTools.getResources(oldPath);
//                        copyMD5Q(userName,prid,copyPath,folderName);
                        //拷贝源文件
//                        String[] oldPath2=loopMap.split("/");
//                        String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                        String name=oldPath2[3];
//                        String srcPath=SearchPathTools.getResources(respath);
//                        copySourceQ(userName,prid,srcPath,name);
                        //end
                        //建立源文件与原名称对应
//                        String[] names=path.split("/");
//                        String srcname =names[names.length-1];
//                        String content=oldPath2[3]+"="+srcname;
//                        getmappedQ(userName,prid,content);

                        imageList.add(image);
                    }
                }
                caroursel.setImageList(imageList);
                return caroursel;
            }
            else if(t.equals("8")){//todo 加注释
                String h = json.getString("h");
                String w = json.getString("w");
                String isFtp = json.getString("isFtp");
                String during = json.getString("during");

                ItemSwipe caroursel = new ItemSwipe();
                caroursel.setPrid(programId);
                caroursel.setPid(pageId);
                caroursel.setBid(blockId);
                caroursel.setT(t);
                caroursel.setX(xPos);
                caroursel.setY(yPos);
                caroursel.setIdx(index);
                caroursel.setiH(html);
                caroursel.setIsFtp(isFtp);

                caroursel.setH(h);
                caroursel.setW(w);
                caroursel.setDuring(during);

                List<CarouseImage> imageList = new ArrayList<CarouseImage>();

                JSONArray imageArray = json.getJSONArray("imgLoop");
                if(imageArray!=null){
                    for(int i=0;i<imageArray.length();i++){
                        CarouseImage image = new CarouseImage();
                        JSONObject object = imageArray.getJSONObject(i);
                        String title = object.getString("title");
                        String path = object.getString("path");
                        String mapped=object.getString("mapped");
                        image.setTitle(title);
                        image.setPath(path);
                        if(mapped.indexOf("edit")>0){
                            image.setMapped(mapped);;
                        }else{
                            //处理mapped
//                            String loopMap= resourceFileDao.getPathByVirtual(false, path);
//                            String[] pcTemps =loopMap.split("/");
//                            String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                            image.setMapped(pcMapped);
                            //end
                            //拷贝资源文件至md5文件夹下
//                            String[] tempMaps=loopMap.split("/");
//                            String folderName=tempMaps[tempMaps.length-2];
//                            String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
//                            String copyPath=SearchPathTools.getResources(oldPath);
//                            copyMD5Q(userName,prid,copyPath,folderName);
                            //拷贝源文件
//                            String[] oldPath2=loopMap.split("/");
//                            String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                            String name=oldPath2[3];
//                            String srcPath=SearchPathTools.getResources(respath);
//                            copySourceQ(userName,prid,srcPath,name);
                            //end
                            //建立源文件与原名称对应
//                            String[] names=path.split("/");
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmappedQ(userName,prid,content);
                        }
                        imageList.add(image);
                    }
                }
                caroursel.setImageList(imageList);
                return caroursel;
            }
            else if(t.equals("5")){//todo 加注释
                String con = json.getString("con");
                String w = json.getString("w");
                String h = json.getString("h");
                String dim = json.getString("dim");
                String speed = json.getString("speed");

                ItemText text = new ItemText();

                text.setPrid(programId);
                text.setPid(pageId);
                text.setBid(blockId);
                text.setT(t);
                text.setX(xPos);
                text.setY(yPos);
                text.setCon(con);
                text.setIdx(index);
                text.setiH(html);

                text.setSpeed(speed);
                text.setDim(dim);
                text.setH(h);
                text.setW(w);
                return text;
            }
            else if(t.equals("25")){//todo 加注释
                String con = json.getString("con");
                String w = json.getString("w");
                String h = json.getString("h");
                String dim = json.getString("dim");
                String speed = json.getString("speed");

                ItemText text = new ItemText();

                text.setPrid(programId);
                text.setPid(pageId);
                text.setBid(blockId);
                text.setT(t);
                text.setX(xPos);
                text.setY(yPos);
                text.setCon(con);
                text.setIdx(index);
                text.setiH(html);

                text.setSpeed(speed);
                text.setDim(dim);
                text.setH(h);
                text.setW(w);
                return text;
            }
            else if(t.equals("6")||t.equals("7")){
                String bdS = json.getString("bdS");
                String w = json.getString("w");
                String h = json.getString("h");
                String bdC = json.getString("bdC");

                ItemLine text = new ItemLine();

                text.setPrid(programId);
                text.setPid(pageId);
                text.setBid(blockId);
                text.setT(t);
                text.setX(xPos);
                text.setY(yPos);
                text.setIdx(index);
                text.setiH(html);

                text.setH(h);
                text.setW(w);
                text.setBdS(bdS);
                text.setBdC(bdC);
                return text;
            }
            else if(t.equals("11")){//todo 加注释
                String path = json.getString("path");
                String w = json.getString("w");
                String h = json.getString("h");
                String mapped=json.getString("mapped");
                ItemFlash flash = new ItemFlash();

                flash.setPrid(programId);
                flash.setPid(pageId);
                flash.setBid(blockId);
                flash.setT(t);
                flash.setX(xPos);
                flash.setY(yPos);
                flash.setIdx(index);
                if(mapped.indexOf("edit")>0){
                    flash.setiH(html);
                    flash.setMapped(mapped);
                }else{
                    //处理mapped
//                    String pcMap=mapped;
//                    String[] pcTemps =pcMap.split("/");
//                    String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                    flash.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
//                    String[] tempMaps=mapped.split("/");
//                    String folderName=tempMaps[tempMaps.length-2];
//                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
//                    String copyPath=SearchPathTools.getResources(oldPath);
//                    copyMD5Q(userName,prid,copyPath,folderName);
                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySourceQ(userName,prid,srcPath,name);
                    //建立源文件与原名称对应
//                    String[] names=path.split("/");
//                    String srcname =names[names.length-1];
//                    String content=oldPath2[3]+"="+srcname;
//                    getmappedQ(userName,prid,content);
                }
                flash.setPath(path);
                flash.setH(h);
                flash.setW(w);
                return flash;
            }
            else if(t.equals("12")){//todo 加注释
                String path = json.getString("path");
                String w = json.getString("w");
                String h = json.getString("h");
                String mapped=json.getString("mapped");
                ItemPPT ppt = new ItemPPT();

                ppt.setPrid(programId);
                ppt.setPid(pageId);
                ppt.setBid(blockId);
                ppt.setT(t);
                ppt.setX(xPos);
                ppt.setY(yPos);
                ppt.setIdx(index);
                if(mapped.indexOf("edit")>0){
                    ppt.setiH(html);
                    ppt.setMapped(mapped);
                }else{
                    //处理mapped
//                    String pcMap=mapped;
//                    String[] pcTemps =pcMap.split("/");
//                    String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                    ppt.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
//                    String[] tempMaps=mapped.split("/");
//                    String folderName=tempMaps[tempMaps.length-2];
//                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
//                    String copyPath=SearchPathTools.getResources(oldPath);
//                    copyMD5Q(userName,prid,copyPath,folderName);
                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySourceQ(userName,prid,srcPath,name);
                    //建立源文件与原名称对应
//                    String[] names=path.split("/");
//                    String srcname =names[names.length-1];
//                    String content=oldPath2[3]+"="+srcname;
//                    getmappedQ(userName,prid,content);
                }

                ppt.setPath(path);
                ppt.setH(h);
                ppt.setW(w);
                return ppt;
            }
            else if(t.equals("13")){//todo 加注释
                String w = json.getString("w");
                String h = json.getString("h");
                String url = json.getString("url");

                ItemWebPage webPage = new ItemWebPage();

                webPage.setPrid(programId);
                webPage.setPid(pageId);
                webPage.setBid(blockId);
                webPage.setT(t);
                webPage.setX(xPos);
                webPage.setY(yPos);

                webPage.setIdx(index);
                webPage.setiH(html);

                webPage.setH(h);
                webPage.setW(w);
                webPage.setUrl(url);
                return webPage;
            }
            else if(t.equals("14")){//todo 加注释
                String w = json.getString("w");
                String h = json.getString("h");
                String url = json.getString("url");
                String thirdstream = json.getString("thirdstream");
                String honghestream = json.getString("honghestream");

                ItemStreaming streaming = new ItemStreaming();

                streaming.setPrid(programId);
                streaming.setPid(pageId);
                streaming.setBid(blockId);
                streaming.setT(t);
                streaming.setX(xPos);
                streaming.setY(yPos);

                streaming.setIdx(index);
                streaming.setiH(html);

                streaming.setH(h);
                streaming.setW(w);
                streaming.setUrl(url);
                streaming.setThirdstream(thirdstream);
                streaming.setHonghestream(honghestream);
                return streaming;
            }
            else if(t.equals("15")){//todo 加注释
                String h = json.getString("h");
                String w = json.getString("w");
                String workType = json.getString("workType");
                String backPic = json.getString("backPic");
                String backMapped = json.getString("backMapped");

                ItemExcellent excellent = new ItemExcellent();
                excellent.setPrid(programId);
                excellent.setPid(pageId);
                excellent.setBid(blockId);
                excellent.setT(t);
                excellent.setX(xPos);
                excellent.setY(yPos);
                excellent.setIdx(index);
                excellent.setiH(html);

                excellent.setH(h);
                excellent.setW(w);
                excellent.setWorkType(workType);
                excellent.setBackPic(backPic);

                if(backMapped.indexOf("add_detailBg")<0){
                    if(backMapped.indexOf("edit")>0){
                        excellent.setMapped(backMapped);
                    }else{
                        //处理mapped
//                        String pcMap=backMapped;
//                        String[] pcTemps =pcMap.split("/");
//                        String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                        excellent.setMapped(pcMapped);
                        //拷贝资源文件至md5文件夹下
//                        String[] tempMaps=backMapped.split("/");
//                        String folderName=tempMaps[tempMaps.length-2];
//                        String oldPath=backMapped.substring(0, backMapped.lastIndexOf("/"));
//                        String copyPath=SearchPathTools.getResources(oldPath);
//                        copyMD5Q(userName,prid,copyPath,folderName);
                        //拷贝源文件
//                        String[] oldPath2=backMapped.split("/");
//                        String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                        String name=oldPath2[3];
//                        String srcPath=SearchPathTools.getResources(respath);
//                        copySourceQ(userName,prid,srcPath,name);
                        //建立源文件与原名称对应
//                        String[] names=backPic.split("/");
//                        String srcname =names[names.length-1];
//                        String content=oldPath2[3]+"="+srcname;
//                        getmappedQ(userName,prid,content);
                    }
                }else{
                    excellent.setMapped(backMapped);
                }
                List<ExcellentArticle> articleList = new ArrayList<ExcellentArticle>();
                JSONArray articleArray = json.getJSONArray("articleList");
                if(articleArray!=null){
                    for(int i=0;i<articleArray.length();i++){
                        ExcellentArticle article = new ExcellentArticle();
                        JSONObject object = articleArray.getJSONObject(i);
                        String title = object.getString("title");
                        String author = object.getString("author");
                        String path = object.getString("path");
                        if(object.getString("mapped").indexOf("edit")>0){
                            article.setMapped(object.getString("mapped"));
                        }else{
                            //处理mapped
//                            String loopMap= object.getString("mapped");
//                            String[] pcTemps =loopMap.split("/");
//                            String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                            String mapped = pcMapped;
//                            article.setMapped(mapped);
                            //拷贝资源文件至md5文件夹下
//                            String[] tempMaps=loopMap.split("/");
//                            String folderName=tempMaps[tempMaps.length-2];
//                            String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
//                            String copyPath=SearchPathTools.getResources(oldPath);
//                            copyMD5Q(userName,prid,copyPath,folderName);
                            //拷贝源文件
//                            String[] oldPath2=loopMap.split("/");
//                            String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                            String name=oldPath2[3];
//                            String srcPath=SearchPathTools.getResources(respath);
//                            copySourceQ(userName,prid,srcPath,name);
                            //end
                            //建立源文件与原名称对应
//                            String[] names=path.split("/");
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmappedQ(userName,prid,content);
                        }
                        article.setTitle(title);
                        article.setAuthor(author);
                        article.setPath(path);
                        articleList.add(article);
                    }
                }
                excellent.setArticleList(articleList);
                return excellent;
            }
            else if(t.equals("16")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String type = json.getString("type");
                String bgC = json.getString("bgC");
                String ftC = json.getString("ftC");
                String bdW = json.getString("bdW");
                String bdC = json.getString("bdC");

                ItemTime itemTime = new ItemTime();
                itemTime.setPrid(programId);
                itemTime.setPid(pageId);
                itemTime.setBid(blockId);
                itemTime.setT(t);
                itemTime.setX(xPos);
                itemTime.setY(yPos);
                itemTime.setIdx(index);
                itemTime.setiH(html);

                itemTime.setScX(xSc);
                itemTime.setScY(ySc);
                itemTime.setType(type);
                itemTime.setBGC(bgC);
                itemTime.setFTC(ftC);
                itemTime.setBDW(bdW);
                itemTime.setBDC(bdC);

                return itemTime;
            }
            else if(t.equals("19")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String type = json.getString("type");
                String bgC = json.getString("bgC");
                String ftC = json.getString("ftC");
                String bdW = json.getString("bdW");
                String bdC = json.getString("bdC");
                String city = json.getString("city");
                String serverIp = json.getString("serverIp");

                ItemWeather itemWeather = new ItemWeather();
                itemWeather.setPrid(programId);
                itemWeather.setPid(pageId);
                itemWeather.setBid(blockId);
                itemWeather.setT(t);
                itemWeather.setX(xPos);
                itemWeather.setY(yPos);
                itemWeather.setIdx(index);
                itemWeather.setiH(html);

                itemWeather.setScX(xSc);
                itemWeather.setScY(ySc);
                itemWeather.setType(type);
                itemWeather.setBGC(bgC);
                itemWeather.setFTC(ftC);
                itemWeather.setBDW(bdW);
                itemWeather.setBDC(bdC);
                itemWeather.setCity(city);
                itemWeather.setServerIp(serverIp);

                return itemWeather;
            }
            else if(t.equals("26")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String type = json.getString("type");
                String tymd = json.getString("tymd");
                String tymdhms = json.getString("tymdhms");
                String tymdval = json.getString("tymdval");
                String tymdhmsval = json.getString("tymdhmsval");
                String fontcolor = json.getString("fontcolor");

                ItemCountdown itemCountdown = new ItemCountdown();
                itemCountdown.setPrid(programId);
                itemCountdown.setPid(pageId);
                itemCountdown.setBid(blockId);
                itemCountdown.setT(t);
                itemCountdown.setX(xPos);
                itemCountdown.setY(yPos);
                itemCountdown.setIdx(index);
                itemCountdown.setiH(html);
                itemCountdown.setScX(xSc);
                itemCountdown.setScY(ySc);
                itemCountdown.setType(type);
                itemCountdown.setTymd(tymd);
                itemCountdown.setTymdhms(tymdhms);
                itemCountdown.setTymdval(tymdval);
                itemCountdown.setTymdhmsval(tymdhmsval);
                itemCountdown.setFontcolor(fontcolor);
                return itemCountdown;
            }
            else if(t.equals("22")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String cListID = json.getString("cListID");
                String bgImg = json.getString("bgImg");
                String bgMapped = json.getString("bgMapped");
                String cData = json.getString("cData");
                String serverIp = json.getString("serverIp");

                ItemCourseList itemCourseList = new ItemCourseList();
                itemCourseList.setPrid(programId);
                itemCourseList.setPid(pageId);
                itemCourseList.setBid(blockId);
                itemCourseList.setT(t);
                itemCourseList.setX(xPos);
                itemCourseList.setY(yPos);
                itemCourseList.setIdx(index);
                itemCourseList.setiH(html);

                itemCourseList.setScX(xSc);
                itemCourseList.setScY(ySc);
                itemCourseList.setCListID(cListID);
                itemCourseList.setBGImg(bgImg);
                if(!StringUtil.isEmpty(bgMapped)){
                    if(bgMapped.indexOf("edit")>0){
                        itemCourseList.setBGMapped(bgMapped);
                    }else{
                        //处理mapped
//                        String pcMap=bgMapped;
//                        String[] pcTemps =pcMap.split("/");
//                        String pcMapped="/projects/publish"+"/"+prid+"/media/"+pcTemps[3]+"/"+pcTemps[4];
//                        itemCourseList.setBGMapped(pcMapped);
                        //拷贝资源文件至md5文件夹下
//                        String[] tempMaps=bgMapped.split("/");
//                        String folderName=tempMaps[tempMaps.length-2];
//                        String oldPath=bgMapped.substring(0, bgMapped.lastIndexOf("/"));
//                        String copyPath=SearchPathTools.getResources(oldPath);
//                        copyMD5Q(userName,prid,copyPath,folderName);
                        //拷贝源文件
//                        String[] oldPath2=bgMapped.split("/");
//                        String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                        String name=oldPath2[3];
//                        String srcPath=SearchPathTools.getResources(respath);
//                        copySourceQ(userName,prid,srcPath,name);
                        //建立源文件与原名称对应
//                        String[] names=bgImg.split("/");
//                        String srcname =names[names.length-1];
//                        String content=oldPath2[3]+"="+srcname;
//                        getmappedQ(userName,prid,content);
                    }
                }else{
                    itemCourseList.setBGMapped(bgMapped);
                }
                itemCourseList.setCData(cData);
                itemCourseList.setServerIp(serverIp);
                return itemCourseList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Item parseJson2Item(String programId,String pageId,JSONObject json,String userName,String prid){
        try {
            String blockId = json.getString("bid");
            String xPos = json.getString("x");
            String yPos = json.getString("y");
            String index = json.getString("idx");
            String html = json.getString("iH");
            String mediaPath = ServletActionContext.getServletContext().getRealPath("/msgResource")+"/data";

            String t = json.getString("t");
            if(t.equals("1")){//图片
                String rds = json.getString("rds");
                String path = json.getString("path");
                String w = json.getString("w");
                String h = json.getString("h");
//				String op = json.getString("op").equals("")?"1":json.getString("op");  // 部件的锁定 解锁
                String mapped = json.getString("mapped");//resourceFileDao.getPathByVirtual(false, path);
                ItemImage itemImage = new ItemImage();
                itemImage.setPrid(programId);
                itemImage.setPid(pageId);
                itemImage.setBid(blockId);
                itemImage.setT(t);
                itemImage.setX(xPos);
                itemImage.setY(yPos);
                itemImage.setIdx(index);
                itemImage.setW(w);
                itemImage.setH(h);
//				itemImage.setoP(op); // 部件的锁定 解锁
                itemImage.setPath(path);
                itemImage.setRds(rds);
                if(mapped.indexOf("edit")>0){
                    itemImage.setiH(html);
                    itemImage.setMapped(mapped);
                }else{
                    //处理html
                    String ih=html;
                    String[] strs=ih.split("/");
                    strs[1]="";
                    strs[2]= mediaPath;
                    String ih2=StringUtil.join(strs, "/");
                    itemImage.setiH(ih2);
                    //end
                    //处理mapped
                    String pcMap=mapped;
                    String[] pcTemps =pcMap.split("/");
                    String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                    itemImage.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
                    String[] tempMaps=mapped.split("/");
                    String folderName=tempMaps[tempMaps.length-2];
                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
                    String copyPath=SearchPathTools.getResources(oldPath);
                    copyMD5(userName,prid,copyPath,folderName);
//                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySource(userName,prid,srcPath,name);
//                    //建立源文件与原名称对应
//                    String[] names=path.split("/");
//                    String srcname =names[names.length-1];
//                    String content=oldPath2[3]+"="+srcname;
//                    getmapped(userName,prid,content);
                }
                return itemImage;
            } else if(t.equals("2")){//视频
                String path = json.getString("path");
			/*	String mapped = resourceFileDao.getPathByVirtual(false, path);*/
                String mapped=json.getString("mapped");
                String w = json.getString("w");
                String h = json.getString("h");
                ItemVideo itemVideo = new ItemVideo();
                itemVideo.setPrid(programId);
                itemVideo.setPid(pageId);
                itemVideo.setBid(blockId);
                itemVideo.setT(t);
                itemVideo.setX(xPos);
                itemVideo.setY(yPos);
                itemVideo.setW(w);
                itemVideo.setH(h);
                itemVideo.setIdx(index);
                itemVideo.setPath(path);
                if(mapped.indexOf("edit")>-1){
                    itemVideo.setMapped(mapped);
                    itemVideo.setiH(html);
                }else{
                    //处理html
                    String ih=html;
                    String[] strs=ih.split("/");
                    strs[1]="";
                    strs[2]=mediaPath;
                    String ih2=StringUtil.join(strs, "/");
                    itemVideo.setiH(ih2);
                    //处理mapped
                    String pcMap=mapped;
                    String[] pcTemps =pcMap.split("/");
                    String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                    itemVideo.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
                    String[] tempMaps=mapped.split("/");
                    String folderName=tempMaps[tempMaps.length-2];
                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
                    String copyPath=SearchPathTools.getResources(oldPath);
                    if(folderName.equals("defaultvideothumb")){
                        copyPath = "../webapps/ManagementCenter/data/defaultvideothumb/";
                    }
                    copyMD5(userName,prid,copyPath,folderName);
//                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySource(userName,prid,srcPath,name);
//                    //建立源文件与原名称对应
//                    if(path.indexOf("/") > -1){
//                        String[] names=path.split("/");
//                        if(names.length > 1){
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmapped(userName,prid,content);
//                        }
//                    }
                }
                return itemVideo;
            } else if(t.equals("3")){//todo 加注释
                String rds = json.getString("rds");
                String bdC = json.getString("bdC");
                String bgC = json.getString("bgC");
                String bdW = json.getString("bdW");
                String w = json.getString("w");
                String h = json.getString("h");

                ItemRectangular itemRectangular = new ItemRectangular();

                itemRectangular.setPrid(programId);
                itemRectangular.setPid(pageId);
                itemRectangular.setBid(blockId);
                itemRectangular.setT(t);
                itemRectangular.setX(xPos);
                itemRectangular.setY(yPos);
                itemRectangular.setIdx(index);
                itemRectangular.setiH(html);
                itemRectangular.setBdC(bdC);
                itemRectangular.setBgC(bgC);
                itemRectangular.setBdW(bdW);
                itemRectangular.setRds(rds);
                itemRectangular.setW(w);
                itemRectangular.setH(h);
                return itemRectangular;
            }
            else if(t.equals("4")||t.equals("9")||t.equals("10")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String isFtp = json.getString("isFtp");
                String during = json.getString("during");
                ItemCarousel caroursel = new ItemCarousel();
                caroursel.setPrid(programId);
                caroursel.setPid(pageId);
                caroursel.setBid(blockId);
                caroursel.setT(t);
                caroursel.setX(xPos);
                caroursel.setY(yPos);
                caroursel.setIdx(index);
                caroursel.setiH(html);
                caroursel.setScX(xSc);
                caroursel.setScY(ySc);
                caroursel.setIsFtp(isFtp);
                caroursel.setDuring(during);
                List<CarouseImage> imageList = new ArrayList<CarouseImage>();
                JSONArray imageArray = json.getJSONArray("imgLoop");
                if(imageArray!=null){
                    for(int i=0;i<imageArray.length();i++){
                        CarouseImage image = new CarouseImage();
                        JSONObject object = imageArray.getJSONObject(i);
                        String title = object.getString("title");
                        String path = object.getString("path");
						/*String loopMap= resourceFileDao.getPathByVirtual(false, path);*/
                        String loopMap=object.getString("mapped");
                        if(loopMap.indexOf("edit")>0){
                            image.setMapped(loopMap);
                        }else{
                            //处理mapped
                            String[] pcTemps =loopMap.split("/");
                            String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                            image.setMapped(pcMapped);
                            //end
                            //拷贝资源文件至md5文件夹下
                            String[] tempMaps=loopMap.split("/");
                            String folderName=tempMaps[tempMaps.length-2];
                            String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
                            String copyPath=SearchPathTools.getResources(oldPath);
                            copyMD5(userName,prid,copyPath,folderName);
//                            //拷贝源文件
//                            String[] oldPath2=loopMap.split("/");
//                            String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                            String name=oldPath2[3];
//                            String srcPath=SearchPathTools.getResources(respath);
//                            copySource(userName,prid,srcPath,name);
//                            //end
//                            //建立源文件与原名称对应
//                            String[] names=path.split("/");
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmapped(userName,prid,content);
                        }
                        image.setTitle(title);
                        image.setPath(path);
                        imageList.add(image);
                    }
                }
                caroursel.setImageList(imageList);
                return caroursel;
            } else if(t.equals("8") || t.equals("18")){// “18”-->PPT todo 8 ？
                String h = json.getString("h");
                String w = json.getString("w");
                String isFtp = json.getString("isFtp");
                String during = json.getString("during");

                ItemSwipe caroursel = new ItemSwipe();
                caroursel.setPrid(programId);
                caroursel.setPid(pageId);
                caroursel.setBid(blockId);
                caroursel.setT(t);
                caroursel.setX(xPos);
                caroursel.setY(yPos);
                caroursel.setIdx(index);
                caroursel.setiH(html);
                caroursel.setIsFtp(isFtp);
                caroursel.setH(h);
                caroursel.setW(w);
                caroursel.setDuring(during);
                List<CarouseImage> imageList = new ArrayList<CarouseImage>();
                JSONArray imageArray = json.getJSONArray("imgLoop");
                if(imageArray!=null){
                    for(int i=0;i<imageArray.length();i++){
                        CarouseImage image = new CarouseImage();
                        JSONObject object = imageArray.getJSONObject(i);
                        String title = object.getString("title");
                        String path = object.getString("path");
					/*	String mapped = resourceFileDao.getPathByVirtual(false, path);*/
                        String mapped=object.getString("mapped");
                        //String loopMap=object.getString("mapped");
                        image.setTitle(title);
                        image.setPath(path);
                        if(mapped.indexOf("edit")>0){
                            image.setMapped(mapped);
                        }else{
                            image.setMapped(mapped);
                            if(t.equals("8")) {//滑动轮播图
                                //处理mapped
                                String[] pcTemps =mapped.split("/");
                                String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                                image.setMapped(pcMapped);
                                //end
                                //拷贝资源文件至md5文件夹下
                                String[] tempMaps=mapped.split("/");
                                String folderName=tempMaps[tempMaps.length-2];
                                String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
                                String copyPath=SearchPathTools.getResources(oldPath);
                                copyMD5(userName,prid,copyPath,folderName);
//                            String loopMap= resourceFileDao.getPathByVirtual(false, path);
//                            String[] pcTemps =loopMap.split("/");
//                            String pcMapped=mediaPath+pcTemps[3]+"/"+pcTemps[4];
//                            image.setMapped(pcMapped);
                                //end
                                //拷贝资源文件至md5文件夹下
//                            String[] tempMaps=loopMap.split("/");
//                            String folderName=tempMaps[tempMaps.length-2];
//                            String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
//                            String copyPath=SearchPathTools.getResources(oldPath);
//                            copyMD5(userName,prid,copyPath,folderName);
//                            //拷贝源文件
//                            String[] oldPath2=loopMap.split("/");
//                            String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                            String name=oldPath2[3];
//                            String srcPath=SearchPathTools.getResources(respath);
//                            copySource(userName,prid,srcPath,name);
//                            //end
//                            //建立源文件与原名称对应
//                            String[] names=path.split("/");
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmapped(userName,prid,content);
                            }

                        }

                        imageList.add(image);
                    }
                }
                caroursel.setImageList(imageList);
                return caroursel;
            } else if(t.equals("5")){//todo 加注释
                String con = json.getString("con");
                String w = json.getString("w");
                String h = json.getString("h");
                String dim = json.getString("dim");
                String speed = json.getString("speed");
                ItemText text = new ItemText();
                text.setPrid(programId);
                text.setPid(pageId);
                text.setBid(blockId);
                text.setT(t);
                text.setX(xPos);
                text.setY(yPos);
                text.setCon(con);
                text.setIdx(index);
                text.setiH(html);
                text.setSpeed(speed);
                text.setDim(dim);
                text.setH(h);
                text.setW(w);
                return text;
            }
            else if(t.equals("25")){//todo 加注释
                String con = json.getString("con");
                String w = json.getString("w");
                String h = json.getString("h");
                String dim = json.getString("dim");
                String speed = json.getString("speed");
                ItemText text = new ItemText();
                text.setPrid(programId);
                text.setPid(pageId);
                text.setBid(blockId);
                text.setT(t);
                text.setX(xPos);
                text.setY(yPos);
                text.setCon(con);
                text.setIdx(index);
                text.setiH(html);
                text.setSpeed(speed);
                text.setDim(dim);
                text.setH(h);
                text.setW(w);
                return text;
            } else if(t.equals("6")||t.equals("7")){//todo 加注释
                String bdS = json.getString("bdS");
                String w = json.getString("w");
                String h = json.getString("h");
                String bdC = json.getString("bdC");
                ItemLine text = new ItemLine();
                text.setPrid(programId);
                text.setPid(pageId);
                text.setBid(blockId);
                text.setT(t);
                text.setX(xPos);
                text.setY(yPos);
                text.setIdx(index);
                text.setiH(html);
                text.setH(h);
                text.setW(w);
                text.setBdS(bdS);
                text.setBdC(bdC);
                return text;
            } else if(t.equals("11")){//flash
                String path = json.getString("path");
                String w = json.getString("w");
                String h = json.getString("h");
/*				String mapped = resourceFileDao.getPathByVirtual(false, path);*/
                String mapped=json.getString("mapped");
                ItemFlash flash = new ItemFlash();
                flash.setPrid(programId);
                flash.setPid(pageId);
                flash.setBid(blockId);
                flash.setT(t);
                flash.setX(xPos);
                flash.setY(yPos);
                flash.setIdx(index);
                if(mapped.indexOf("edit")>0){
                    flash.setiH(html);
                    flash.setMapped(mapped);
                }else{
                    //处理html
                    String ih=html;
                    String[] strs=ih.split("/");
                    strs[1]="";
                    strs[2]=mediaPath;
                    String ih2=StringUtil.join(strs, "/");
                    flash.setiH(ih2);
                    //处理mapped
                    String pcMap=mapped;
                    String[] pcTemps =pcMap.split("/");
                    String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                    flash.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
                    String[] tempMaps=mapped.split("/");
                    String folderName=tempMaps[tempMaps.length-2];
                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
                    String copyPath=SearchPathTools.getResources(oldPath);
                    copyMD5(userName,prid,copyPath,folderName);
//                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySource(userName,prid,srcPath,name);
//                    //建立源文件与原名称对应
//                    String[] names=path.split("/");
//                    String srcname =names[names.length-1];
//                    String content=oldPath2[3]+"="+srcname;
//                    getmapped(userName,prid,content);
                }
                flash.setPath(path);
                flash.setH(h);
                flash.setW(w);
                return flash;
            } else if(t.equals("12")){//todo 加注释
                String path = json.getString("path");
                String w = json.getString("w");
                String h = json.getString("h");
				/*String mapped = resourceFileDao.getPathByVirtual(false, path);*/
                String mapped=json.getString("mapped");
                ItemPPT ppt = new ItemPPT();
                ppt.setPrid(programId);
                ppt.setPid(pageId);
                ppt.setBid(blockId);
                ppt.setT(t);
                ppt.setX(xPos);
                ppt.setY(yPos);
                ppt.setIdx(index);
                if(mapped.indexOf("edit")>0){
                    ppt.setiH(html);
                    ppt.setMapped(mapped);
                }else{
                    //处理html
                    String ih=html;
                    String[] strs=ih.split("/");
                    strs[1]="";
                    strs[2]=mediaPath;
                    String ih2=StringUtil.join(strs, "/");
                    ppt.setiH(ih2);
                    //处理mapped
                    String pcMap=mapped;
                    String[] pcTemps =pcMap.split("/");
                    String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                    ppt.setMapped(pcMapped);
                    //拷贝资源文件至md5文件夹下
                    String[] tempMaps=mapped.split("/");
                    String folderName=tempMaps[tempMaps.length-2];
                    String oldPath=mapped.substring(0, mapped.lastIndexOf("/"));
                    String copyPath=SearchPathTools.getResources(oldPath);
                    copyMD5(userName,prid,copyPath,folderName);
//                    //拷贝源文件
//                    String[] oldPath2=mapped.split("/");
//                    String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                    String name=oldPath2[3];
//                    String srcPath=SearchPathTools.getResources(respath);
//                    copySource(userName,prid,srcPath,name);
//                    //建立源文件与原名称对应
//                    String[] names=path.split("/");
//                    String srcname =names[names.length-1];
//                    String content=oldPath2[3]+"="+srcname;
//                    getmapped(userName,prid,content);
                }
                ppt.setPath(path);
                ppt.setH(h);
                ppt.setW(w);
                return ppt;
            } else if(t.equals("13")){//todo 加注释
                String w = json.getString("w");
                String h = json.getString("h");
                String url = json.getString("url");
                ItemWebPage webPage = new ItemWebPage();
                webPage.setPrid(programId);
                webPage.setPid(pageId);
                webPage.setBid(blockId);
                webPage.setT(t);
                webPage.setX(xPos);
                webPage.setY(yPos);
                webPage.setIdx(index);
                webPage.setiH(html);
                webPage.setH(h);
                webPage.setW(w);
                webPage.setUrl(url);
                return webPage;
            }
            else if(t.equals("14")){//todo 加注释
                String w = json.getString("w");
                String h = json.getString("h");
                String url = json.getString("url");
                String thirdstream = json.getString("thirdstream");
                String honghestream = json.getString("honghestream");
                ItemStreaming streaming = new ItemStreaming();
                streaming.setPrid(programId);
                streaming.setPid(pageId);
                streaming.setBid(blockId);
                streaming.setT(t);
                streaming.setX(xPos);
                streaming.setY(yPos);
                streaming.setIdx(index);
                streaming.setiH(html);
                streaming.setH(h);
                streaming.setW(w);
                streaming.setUrl(url);
                streaming.setThirdstream(thirdstream);
                streaming.setHonghestream(honghestream);
                return streaming;
            } else if(t.equals("15")){//主题展示
                String h = json.getString("h");
                String w = json.getString("w");
                String workType = json.getString("workType");
                String backPic = json.getString("backPic");
                String backMapped = json.getString("backMapped");
                ItemExcellent excellent = new ItemExcellent();
                excellent.setPrid(programId);
                excellent.setPid(pageId);
                excellent.setBid(blockId);
                excellent.setT(t);
                excellent.setX(xPos);
                excellent.setY(yPos);
                excellent.setIdx(index);
                excellent.setiH(html);
                excellent.setH(h);
                excellent.setW(w);
                excellent.setWorkType(workType);
                excellent.setBackPic(backPic);
                //String backMapped = resourceFileDao.getPathByVirtual(false, backPic);
                if(backMapped.indexOf("add_detailBg")<0){
                    if(backMapped.indexOf("edit")>0){
                        excellent.setMapped(backMapped);
                    }else{
                        //处理mapped
                        String pcMap=backMapped;
                        String[] pcTemps =pcMap.split("/");
                        String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                        excellent.setMapped(pcMapped);
                        //拷贝资源文件至md5文件夹下
                        String[] tempMaps=backMapped.split("/");
                        String folderName=tempMaps[tempMaps.length-2];
                        String oldPath=backMapped.substring(0, backMapped.lastIndexOf("/"));
                        String copyPath=SearchPathTools.getResources(oldPath);
                        copyMD5(userName,prid,copyPath,folderName);
//                        //拷贝源文件
//                        String[] oldPath2=backMapped.split("/");
//                        String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                        String name=oldPath2[3];
//                        String srcPath=SearchPathTools.getResources(respath);
//                        copySource(userName,prid,srcPath,name);
//                        //建立源文件与原名称对应
//                        String[] names=backPic.split("/");
//                        String srcname =names[names.length-1];
//                        String content=oldPath2[3]+"="+srcname;
//                        getmapped(userName,prid,content);
                    }

                }else{
                    excellent.setMapped(backMapped);
                }
                List<ExcellentArticle> articleList = new ArrayList<ExcellentArticle>();

                JSONArray articleArray = json.getJSONArray("articleList");
                if(articleArray!=null){
                    for(int i=0;i<articleArray.length();i++){
                        ExcellentArticle article = new ExcellentArticle();
                        JSONObject object = articleArray.getJSONObject(i);
                        String title = object.getString("title");
                        String author = object.getString("author");
                        String path = object.getString("path");
                        //String mapped = resourceFileDao.getPathByVirtual(false, path);
                        if(object.getString("mapped").indexOf("edit")>0){
                            article.setMapped(object.getString("mapped"));
                        }else{
                            //处理mapped
                            String loopMap= object.getString("mapped");
                            String[] pcTemps =loopMap.split("/");
                            String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                            article.setMapped(pcMapped);
                            //拷贝资源文件至md5文件夹下
                            String[] tempMaps=loopMap.split("/");
                            String folderName=tempMaps[tempMaps.length-2];
                            String oldPath=loopMap.substring(0, loopMap.lastIndexOf("/"));
                            String copyPath=SearchPathTools.getResources(oldPath);
                            copyMD5(userName,prid,copyPath,folderName);
//                            //拷贝源文件
//                            String[] oldPath2=loopMap.split("/");
//                            String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                            String name=oldPath2[3];
//                            String srcPath=SearchPathTools.getResources(respath);
//                            copySource(userName,prid,srcPath,name);
//                            //end
//                            //建立源文件与原名称对应
//                            String[] names=path.split("/");
//                            String srcname =names[names.length-1];
//                            String content=oldPath2[3]+"="+srcname;
//                            getmapped(userName,prid,content);
                        }
                        article.setTitle(title);
                        article.setAuthor(author);
                        article.setPath(path);
                        articleList.add(article);
                    }
                }
                excellent.setArticleList(articleList);
                return excellent;
            } else if(t.equals("16")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String type = json.getString("type");
                String bgC = json.getString("bgC");
                String ftC = json.getString("ftC");
                String bdW = json.getString("bdW");
                String bdC = json.getString("bdC");
                ItemTime itemTime = new ItemTime();
                itemTime.setPrid(programId);
                itemTime.setPid(pageId);
                itemTime.setBid(blockId);
                itemTime.setT(t);
                itemTime.setX(xPos);
                itemTime.setY(yPos);
                itemTime.setIdx(index);
                itemTime.setiH(html);
                itemTime.setScX(xSc);
                itemTime.setScY(ySc);
                itemTime.setType(type);
                itemTime.setBGC(bgC);
                itemTime.setFTC(ftC);
                itemTime.setBDW(bdW);
                itemTime.setBDC(bdC);
                return itemTime;
            } else if(t.equals("19")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String type = json.getString("type");
                String bgC = json.getString("bgC");
                String ftC = json.getString("ftC");
                String bdW = json.getString("bdW");
                String bdC = json.getString("bdC");
                String city = json.getString("city");
                String serverIp = json.getString("serverIp");
                ItemWeather itemWeather = new ItemWeather();
                itemWeather.setPrid(programId);
                itemWeather.setPid(pageId);
                itemWeather.setBid(blockId);
                itemWeather.setT(t);
                itemWeather.setX(xPos);
                itemWeather.setY(yPos);
                itemWeather.setIdx(index);
                itemWeather.setiH(html);
                itemWeather.setScX(xSc);
                itemWeather.setScY(ySc);
                itemWeather.setType(type);
                itemWeather.setBGC(bgC);
                itemWeather.setFTC(ftC);
                itemWeather.setBDW(bdW);
                itemWeather.setBDC(bdC);
                itemWeather.setCity(city);
                itemWeather.setServerIp(serverIp);
                return itemWeather;
            } else if(t.equals("26")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String type = json.getString("type");
                String tymd = json.getString("tymd");
                String tymdhms = json.getString("tymdhms");
                String tymdval = json.getString("tymdval");
                String tymdhmsval = json.getString("tymdhmsval");
                String fontcolor = json.getString("fontcolor");
                ItemCountdown itemCountdown = new ItemCountdown();
                itemCountdown.setPrid(programId);
                itemCountdown.setPid(pageId);
                itemCountdown.setBid(blockId);
                itemCountdown.setT(t);
                itemCountdown.setX(xPos);
                itemCountdown.setY(yPos);
                itemCountdown.setIdx(index);
                itemCountdown.setiH(html);
                itemCountdown.setScX(xSc);
                itemCountdown.setScY(ySc);
                itemCountdown.setType(type);
                itemCountdown.setTymd(tymd);
                itemCountdown.setTymdhms(tymdhms);
                itemCountdown.setTymdval(tymdval);
                itemCountdown.setTymdhmsval(tymdhmsval);
                itemCountdown.setFontcolor(fontcolor);
                return itemCountdown;
            } else if(t.equals("22")){//todo 加注释
                String xSc = json.getString("scX");
                String ySc = json.getString("scY");
                String cListID = json.getString("cListID");
                String bgImg = json.getString("bgImg");
                String bgMapped = json.getString("bgMapped");
                String cData = json.getString("cData");
                String serverIp = json.getString("serverIp");

                ItemCourseList itemCourseList = new ItemCourseList();
                itemCourseList.setPrid(programId);
                itemCourseList.setPid(pageId);
                itemCourseList.setBid(blockId);
                itemCourseList.setT(t);
                itemCourseList.setX(xPos);
                itemCourseList.setY(yPos);
                itemCourseList.setIdx(index);
                itemCourseList.setiH(html);
                itemCourseList.setScX(xSc);
                itemCourseList.setScY(ySc);
                itemCourseList.setCListID(cListID);
                itemCourseList.setBGImg(bgImg);
                if(!StringUtil.isEmpty(bgMapped)){
                    if(bgMapped.indexOf("edit")>0){
                        itemCourseList.setBGMapped(bgMapped);
                    }else{
                        //处理mapped
                        String pcMap=bgMapped;
                        String[] pcTemps =pcMap.split("/");
                        String pcMapped="/msgResource/"+userName+"/"+prid+"/media/"+pcTemps[pcTemps.length-2]+"/"+pcTemps[pcTemps.length-1];
                        itemCourseList.setBGMapped(pcMapped);
                        //拷贝资源文件至md5文件夹下
                        String[] tempMaps=bgMapped.split("/");
                        String folderName=tempMaps[tempMaps.length-2];
                        String oldPath=bgMapped.substring(0, bgMapped.lastIndexOf("/"));
                        String copyPath=SearchPathTools.getResources(oldPath);
                        copyMD5(userName,prid,copyPath,folderName);
//                        //拷贝源文件
//                        String[] oldPath2=bgMapped.split("/");
//                        String respath="/"+oldPath2[1]+"/"+oldPath2[2]+"";
//                        String name=oldPath2[3];
//                        String srcPath=SearchPathTools.getResources(respath);
//                        copySource(userName,prid,srcPath,name);
//                        //建立源文件与原名称对应
//                        String[] names=bgImg.split("/");
//                        String srcname =names[names.length-1];
//                        String content=oldPath2[3]+"="+srcname;
//                        getmapped(userName,prid,content);
                    }
                }else{
                    itemCourseList.setBGMapped(bgMapped);
                }
                itemCourseList.setCData(cData);
                itemCourseList.setServerIp(serverIp);
                return itemCourseList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo 加注释
     * @param userName
     * @param item
     * @return
     */
    public boolean modifyItemQ(String userName,Item item){
        String prid = item.getPrid();
        prid=StringUtil.getUtf8Str(prid);
        String pid = item.getPid();
        String bid = item.getBid();

        String pagePath = SearchPathTools.getPublishPage(userName, prid, pid, false);
        File folder = new File(pagePath);

        File[] folders = folder.listFiles();

        for (File i : folders){
            if(i.getName().equals(item.getBid()+".xml")){
                i.delete();

                String blockPath = SearchPathTools.getPublishItem(prid, pid, bid);
                String projectPath = SearchPathTools.getPublishProgram(prid, true);
                this.writeItemXml(blockPath, item);
                newsService.modifyProgramXml(projectPath, new Program());
                return true;
            }
        }
        return false;
    }

    /**
     * todo 加注释
     * @param savePath
     * @param item
     */
    public void writeItemXml(String savePath,Item item){
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("root");//添加文档根

            Element blockId = root.addElement("bid");
            blockId.addText(item.getBid());

            Element type = root.addElement("t");
            type.addText(item.getT());

            Element xPos = root.addElement("x");
            xPos.addText(item.getX());

            Element yPos = root.addElement("y");
            yPos.addText(item.getY());

            Element index = root.addElement("idx");
            index.addText(item.getIdx());

            Element html = root.addElement("iH");
            html.addCDATA(item.getiH());

            if(item instanceof ItemImage){
                Element rds = root.addElement("rds");
                rds.addText(((ItemImage) item).getRds());

                Element path = root.addElement("path");
                path.addText(((ItemImage) item).getPath());

                Element mapped = root.addElement("mapped");
                mapped.addText(((ItemImage) item).getMapped());

                Element w = root.addElement("w");
                w.addText(((ItemImage) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemImage) item).getH());

//				Element op = root.addElement("op");
//				op.addText(((ItemImage) item).getoP());
            }
            else if(item instanceof ItemVideo){
                Element path = root.addElement("path");
                path.addText(((ItemVideo) item).getPath());

                Element mapped = root.addElement("mapped");
                mapped.addText(((ItemVideo) item).getMapped());

                Element w = root.addElement("w");
                w.addText(((ItemVideo) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemVideo) item).getH());
            }
            else if(item instanceof ItemRectangular){
                Element rds = root.addElement("rds");
                rds.addText(((ItemRectangular) item).getRds());

                Element bdC =  root.addElement("bdC");
                bdC.addText(((ItemRectangular) item).getBdC());

                Element bgC = root.addElement("bgC");
                bgC.addText(((ItemRectangular) item).getBgC());

                Element bdW = root.addElement("bdW");
                bdW.addText(((ItemRectangular) item).getBdW());

                Element w = root.addElement("w");
                w.addText(((ItemRectangular) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemRectangular) item).getH());
            }
            //4 and 9 deal
            else if(item instanceof ItemCarousel){
                Element xc = root.addElement("scX");
                xc.addText(((ItemCarousel) item).getScX());

                Element yc = root.addElement("scY");
                yc.addText(((ItemCarousel) item).getScY());

                Element isFtp = root.addElement("isFtp");
                isFtp.addText(((ItemCarousel) item).getIsFtp());

                Element during = root.addElement("during");
                during.addText(((ItemCarousel) item).getDuring());

                Element ftpPath = root.addElement("ftpPath");
                ftpPath.addText(((ItemCarousel) item).getFtpPath()==null?"":((ItemCarousel) item).getFtpPath());

                List<CarouseImage> iList = ((ItemCarousel) item).getImageList();
                if(iList!=null){
                    Element imgLoop = root.addElement("imgLoop");
                    for(CarouseImage carouseImage : iList){
                        Element imageElement = imgLoop.addElement("image");

                        Element titleElement = imageElement.addElement("title");
                        titleElement.addCDATA(carouseImage.getTitle());

                        Element pathElement = imageElement.addElement("path");
                        pathElement.addText(carouseImage.getPath());

                        Element mapped = imageElement.addElement("mapped");
                        mapped.addText(carouseImage.getMapped());
                    }
                }

            }
            else if(item instanceof ItemSwipe){
                Element h = root.addElement("h");
                h.addText(((ItemSwipe) item).getH());

                Element w = root.addElement("w");
                w.addText(((ItemSwipe) item).getW());


                Element isFtp = root.addElement("isFtp");
                isFtp.addText(((ItemSwipe) item).getIsFtp());

                Element ftpPath = root.addElement("ftpPath");
                ftpPath.addText(((ItemSwipe) item).getFtpPath()==null?"":((ItemSwipe) item).getFtpPath());

                Element during = root.addElement("during");
                during.addText(((ItemSwipe) item).getDuring());

                List<CarouseImage> iList = ((ItemSwipe) item).getImageList();
                if(iList!=null){
                    Element imgLoop = root.addElement("imgLoop");
                    for(CarouseImage carouseImage : iList){
                        Element imageElement = imgLoop.addElement("image");

                        Element titleElement = imageElement.addElement("title");
                        titleElement.addText(carouseImage.getTitle());

                        Element pathElement = imageElement.addElement("path");
                        pathElement.addText(carouseImage.getPath());

                        Element mapped = imageElement.addElement("mapped");
                        mapped.addText(carouseImage.getMapped());
                    }
                }
            }
            else if(item instanceof ItemText){
                Element w = root.addElement("w");
                w.addText(((ItemText) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemText) item).getH());

                Element con = root.addElement("con");
                con.addCDATA(((ItemText) item).getCon());

                Element dim = root.addElement("dim");
                dim.addText(((ItemText) item).getDim());

                Element speed = root.addElement("speed");
                speed.addText(((ItemText) item).getSpeed());
            }
            else if(item instanceof ItemLine){
                Element rds = root.addElement("bdS");
                rds.addText(((ItemLine) item).getBdS());

                Element bdC = root.addElement("bdC");
                bdC.addText(((ItemLine) item).getBdC());

                Element w = root.addElement("w");
                w.addText(((ItemLine) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemLine) item).getH());
            }
            else if(item instanceof ItemFlash){
                Element path = root.addElement("path");
                path.addText(((ItemFlash) item).getPath());

                Element mapped = root.addElement("mapped");
                mapped.addText(((ItemFlash) item).getMapped());

                Element w = root.addElement("w");
                w.addText(((ItemFlash) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemFlash) item).getH());
            }
            else if(item instanceof ItemPPT){
                Element path = root.addElement("path");
                path.addText(((ItemPPT) item).getPath());

                Element mapped = root.addElement("mapped");
                mapped.addText(((ItemPPT) item).getMapped());

                Element w = root.addElement("w");
                w.addText(((ItemPPT) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemPPT) item).getH());
            }
            else if(item instanceof ItemWebPage){

                Element w = root.addElement("w");
                w.addText(((ItemWebPage) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemWebPage) item).getH());

                Element url = root.addElement("url");
                url.addText(((ItemWebPage) item).getUrl());
            }
            else if(item instanceof ItemStreaming){

                Element w = root.addElement("w");
                w.addText(((ItemStreaming) item).getW());

                Element h = root.addElement("h");
                h.addText(((ItemStreaming) item).getH());

                Element url = root.addElement("url");
                url.addText(((ItemStreaming) item).getUrl());

                Element thirdstream = root.addElement("thirdstream");
                thirdstream.addText(((ItemStreaming) item).getThirdstream());

                Element honghestream = root.addElement("honghestream");
                honghestream.addText(((ItemStreaming) item).getHonghestream());
            }
            else if(item instanceof ItemExcellent){
                Element h = root.addElement("h");
                h.addText(((ItemExcellent) item).getH());

                Element w = root.addElement("w");
                w.addText(((ItemExcellent) item).getW());

                Element workType = root.addElement("workType");
                workType.addText(((ItemExcellent) item).getWorkType());

                Element backPic = root.addElement("backPic");
                backPic.addText(((ItemExcellent) item).getBackPic());

                Element backMapped = root.addElement("mapped");
                backMapped.addText(((ItemExcellent) item).getMapped());

                List<ExcellentArticle> aList = ((ItemExcellent) item).getArticleList();
                if(aList!=null){
                    Element articleLoop = root.addElement("articleLoop");
                    for(ExcellentArticle eArticle : aList){
                        Element articleElement = articleLoop.addElement("article");

                        Element titleElement = articleElement.addElement("title");
                        titleElement.addText(eArticle.getTitle());

                        Element authorElement = articleElement.addElement("author");
                        authorElement.addText(eArticle.getAuthor());

                        Element pathElement = articleElement.addElement("path");
                        pathElement.addText(eArticle.getPath());

                        Element mapped = articleElement.addElement("mapped");
                        mapped.addText(eArticle.getMapped());
                    }
                }
            }
            else if(item instanceof ItemTime){
                Element xc = root.addElement("scX");
                xc.addText(((ItemTime) item).getScX());

                Element yc = root.addElement("scY");
                yc.addText(((ItemTime) item).getScY());

                Element itemType = root.addElement("type");
                itemType.addText(((ItemTime) item).getType());
                Element bgC = root.addElement("bgC");
                bgC.addText(((ItemTime) item).getBGC());
                Element ftC = root.addElement("ftC");
                ftC.addText(((ItemTime) item).getFTC());
                Element bdW = root.addElement("bdW");
                bdW.addText(((ItemTime) item).getBDW());
                Element bdC = root.addElement("bdC");
                bdC.addText(((ItemTime) item).getBDC());
            }
            else if(item instanceof ItemWeather){
                Element xc = root.addElement("scX");
                xc.addText(((ItemWeather) item).getScX());

                Element yc = root.addElement("scY");
                yc.addText(((ItemWeather) item).getScY());

                Element itemType = root.addElement("type");
                itemType.addText(((ItemWeather) item).getType());
                Element bgC = root.addElement("bgC");
                bgC.addText(((ItemWeather) item).getBGC());
                Element ftC = root.addElement("ftC");
                ftC.addText(((ItemWeather) item).getFTC());
                Element bdW = root.addElement("bdW");
                bdW.addText(((ItemWeather) item).getBDW());
                Element bdC = root.addElement("bdC");
                bdC.addText(((ItemWeather) item).getBDC());
                Element city = root.addElement("city");
                city.addText(((ItemWeather) item).getCity());
                Element serverIp = root.addElement("serverIp");
                serverIp.addText(((ItemWeather) item).getServerIp());
            }
            else if(item instanceof ItemCountdown){
                Element xc = root.addElement("scX");
                xc.addText(((ItemCountdown) item).getScX());

                Element yc = root.addElement("scY");
                yc.addText(((ItemCountdown) item).getScY());

                Element itemType = root.addElement("type");
                itemType.addText(((ItemCountdown) item).getType());
                Element tymd = root.addElement("tymd");
                tymd.addText(((ItemCountdown) item).getTymd());
                Element tymdhms = root.addElement("tymdhms");
                tymdhms.addText(((ItemCountdown) item).getTymdhms());
                Element tymdval = root.addElement("tymdval");
                tymdval.addText(((ItemCountdown) item).getTymdval());
                Element tymdhmsval = root.addElement("tymdhmsval");
                tymdhmsval.addText(((ItemCountdown) item).getTymdhmsval());
                Element fontcolor = root.addElement("fontcolor");
                fontcolor.addText(((ItemCountdown) item).getFontcolor());
            }
            else if(item instanceof ItemCourseList){
                Element xc = root.addElement("scX");
                xc.addText(((ItemCourseList) item).getScX());
                Element yc = root.addElement("scY");
                yc.addText(((ItemCourseList) item).getScY());
                Element cListID = root.addElement("cListID");
                cListID.addText(((ItemCourseList) item).getCListID());
                Element bgImg = root.addElement("bgImg");
                bgImg.addText(((ItemCourseList) item).getBGImg());
                Element bgMapped = root.addElement("bgMapped");
                bgMapped.addText(((ItemCourseList) item).getBGMapped());
                Element cData = root.addElement("cData");
                cData.addText(((ItemCourseList) item).getCData());
                Element serverIp = root.addElement("serverIp");
                serverIp.addText(((ItemCourseList) item).getServerIp());
            }
            newsService.persistenceXml(document, savePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param item
     * @return
     */
    public boolean modifyItem(String userName,Item item){
        String prid = item.getPrid();
        prid=StringUtil.getUtf8Str(prid);
        String pid = item.getPid();
        String bid = item.getBid();

        String pagePath = SearchPathTools.getEditPage(userName, prid, pid, false);
        File folder = new File(pagePath);

        File[] folders = folder.listFiles();

        for (File i : folders){
            if(i.getName().equals(item.getBid()+".xml")){
                i.delete();

                String blockPath = SearchPathTools.getEditItem(userName, prid, pid, bid);
                String projectPath = SearchPathTools.getEditProgram(userName, prid, true);
                writeItemXml(blockPath,item);
                newsService.modifyProgramXml(projectPath, new Program());
                return true;
            }
        }
        return false;
    }

    /**
     * todo 加注释
     * @param userName
     * @param itemList
     * @return
     */
    public boolean addItem(String userName,List<Item> itemList){
        for(Item item : itemList){
            String prid = StringUtil.getUtf8Str(item.getPrid());
            String pid = item.getPid();
            String bid = item.getBid();

            String blockPath = SearchPathTools.getEditItem(userName, prid, pid, bid);
            this.writeItemXml(blockPath, item);

            String projectPath = SearchPathTools.getEditProgram(userName, prid, true);
            newsService.modifyProgramXml(projectPath, new Program());
        }
        return true;
    }

    /**
     * todo 加注释
     * @param userName
     * @param itemList
     * @return
     */
    public boolean removeItem(String userName,List<Item> itemList){
        String prid = StringUtil.getUtf8Str(itemList.get(0).getPrid());
        for(Item item : itemList){
            String pid = item.getPid();
            String bid = item.getBid();

            String pagePath = SearchPathTools.getEditPage(userName, prid, pid, false);
            File folder = new File(pagePath);

            File[] folders = folder.listFiles();
            for (File i : folders){
                if(i.getName().equals(bid+".xml")){
                    i.delete();
                }
            }

            folder.delete();
        }

        String projectPath = SearchPathTools.getEditProgram(userName, prid, true);
        newsService.modifyProgramXml(projectPath, new Program());
        return true;
    }

    /**
     * todo 加注释
     * @param userName
     * @param page
     * @param quipub
     * @return
     */
    public String queryItem(String userName,com.honghe.recordweb.action.frontend.news.entity.Page page,String quipub){
        String path = "";
        if(quipub.equals("1")){
            path =  SearchPathTools.getPublishPage(userName, page.getPrid(), page.getPid(), false);
        }else if(quipub.equals("0")){
            path = SearchPathTools.getEditPage(userName, page.getPrid(), page.getPid(), false);
        }
        try {
            File forlder = new File(path);
            File[] files = forlder.listFiles();

            JSONArray itemList  = new JSONArray();

            if(files==null||files.length==0){
                return itemList.toString();
            }
            for(File file : files){
                if(file.getName().startsWith("blk")&&file.getName().endsWith(".xml")){
                    Item item = this.parseItemXml(path + file.getName());
                    JSONObject itemJson = new JSONObject();
                    if(item!=null){
                        itemJson.put("bid", item.getBid());
                        itemJson.put("t", item.getT());
                        itemJson.put("x", item.getX());
                        itemJson.put("y", item.getY());
                        itemJson.put("iH", item.getiH());
                        itemJson.put("idx", item.getIdx());

                        if(item instanceof ItemImage){
                            itemJson.put("w", ((ItemImage) item).getW());
                            itemJson.put("h", ((ItemImage) item).getH());
                            itemJson.put("rds", ((ItemImage) item).getRds());
                            itemJson.put("path",  ((ItemImage) item).getPath());
//							itemJson.put("op", ((ItemImage) item).getoP()); // 部件的锁定 解锁
                            itemJson.put("mapped",  ((ItemImage) item).getMapped());//snapPath);
                        }
//                        else if(item instanceof ItemClassdynamics){
//                            itemJson.put("w", ((ItemClassdynamics) item).getW());
//                            itemJson.put("h", ((ItemClassdynamics) item).getH());
//                        }
//                        else if(item instanceof ItemNotice){
//                            itemJson.put("w", ((ItemNotice) item).getW());
//                            itemJson.put("h", ((ItemNotice) item).getH());
//                        }
                        else if(item instanceof ItemVideo){
                            itemJson.put("path",  ((ItemVideo) item).getPath());
                            itemJson.put("mapped",  ((ItemVideo) item).getMapped());

                            itemJson.put("w", ((ItemVideo) item).getW());
                            itemJson.put("h", ((ItemVideo) item).getH());
                        }
                        else if(item instanceof ItemRectangular){
                            itemJson.put("rds", ((ItemRectangular) item).getRds());
                            itemJson.put("bgC", ((ItemRectangular) item).getBgC());
                            itemJson.put("bdW", ((ItemRectangular) item).getBdW());
                            itemJson.put("bdC", ((ItemRectangular) item).getBdC());
                            itemJson.put("w", ((ItemRectangular) item).getW());
                            itemJson.put("h", ((ItemRectangular) item).getH());
                        }
                        else if(item instanceof ItemCarousel){
                            itemJson.put("scX", ((ItemCarousel) item).getScX());
                            itemJson.put("scY", ((ItemCarousel) item).getScY());
                            itemJson.put("isFtp", ((ItemCarousel) item).getIsFtp());
                            itemJson.put("ftpPath", StringUtil.iso2Utf(((ItemCarousel) item).getFtpPath()));
                            itemJson.put("during", ((ItemCarousel) item).getDuring());

                            List<CarouseImage> itemCarouselList = ((ItemCarousel) item).getImageList();
                            List<Map<String,String>> jsonList = new ArrayList<Map<String,String>>();
                            for(CarouseImage msg : itemCarouselList){
								/*String mapped = resourceFileDao.getPathByVirtual(true, msg.getPath());*/
                                String mapped=msg.getMapped();
                                Map<String,String> msgMap = new HashMap<String,String>();
                                msgMap.put("title", msg.getTitle());
                                msgMap.put("path", msg.getPath());
                                msgMap.put("mapped", mapped);
                                jsonList.add(msgMap);
                            }

                            itemJson.put("imgLoop", jsonList);
                        }
                        else if(item instanceof ItemSwipe){
                            itemJson.put("w", ((ItemSwipe) item).getW());
                            itemJson.put("h", ((ItemSwipe) item).getH());
                            itemJson.put("isFtp", ((ItemSwipe) item).getIsFtp());
                            itemJson.put("ftpPath", ((ItemSwipe) item).getFtpPath());
                            itemJson.put("during", ((ItemSwipe) item).getDuring());

                            List<CarouseImage> itemCarouselList = ((ItemSwipe) item).getImageList();
                            List<Map<String,String>> jsonList = new ArrayList<Map<String,String>>();
                            for(CarouseImage msg : itemCarouselList){
                                Map<String,String> msgMap = new HashMap<String,String>();
                                msgMap.put("title", msg.getTitle());
                                msgMap.put("path", msg.getPath());
                                msgMap.put("mapped", msg.getMapped());
                                jsonList.add(msgMap);
                            }
                            itemJson.put("imgLoop", jsonList);
                        }
                        else if(item instanceof ItemText){
                            itemJson.put("w", ((ItemText) item).getW());
                            itemJson.put("h", ((ItemText) item).getH());
                            itemJson.put("con",  ((ItemText) item).getCon());
                            itemJson.put("speed", ((ItemText) item).getSpeed());
                            itemJson.put("dim", ((ItemText) item).getDim());
                        }
                        else if(item instanceof ItemLine){
                            itemJson.put("w", ((ItemLine) item).getW());
                            itemJson.put("h", ((ItemLine) item).getH());
                            itemJson.put("bdS",  ((ItemLine) item).getBdS());
                            itemJson.put("bdC",  ((ItemLine) item).getBdC());
                        }
                        else if(item instanceof ItemFlash){
                            itemJson.put("w", ((ItemFlash) item).getW());
                            itemJson.put("h", ((ItemFlash) item).getH());
                            itemJson.put("path", ((ItemFlash) item).getPath());
                            itemJson.put("mapped", ((ItemFlash) item).getMapped());
                        }
                        else if(item instanceof ItemPPT){
                            itemJson.put("w", ((ItemPPT) item).getW());
                            itemJson.put("h", ((ItemPPT) item).getH());
                            itemJson.put("path", ((ItemPPT) item).getPath());
                            itemJson.put("mapped", ((ItemPPT) item).getMapped());
                        }
                        else if(item instanceof ItemWebPage){
                            itemJson.put("w", ((ItemWebPage) item).getW());
                            itemJson.put("h", ((ItemWebPage) item).getH());
                            itemJson.put("url", ((ItemWebPage) item).getUrl());
                        }
                        else if(item instanceof ItemStreaming){
                            itemJson.put("w", ((ItemStreaming) item).getW());
                            itemJson.put("h", ((ItemStreaming) item).getH());
                            itemJson.put("url", ((ItemStreaming) item).getUrl());
                            itemJson.put("thirdstream", ((ItemStreaming) item).getThirdstream());
                            itemJson.put("honghestream", ((ItemStreaming) item).getHonghestream());
                        }
                        else if(item instanceof ItemExcellent){
                            itemJson.put("w", ((ItemExcellent) item).getW());
                            itemJson.put("h", ((ItemExcellent) item).getH());
                            itemJson.put("workType", ((ItemExcellent) item).getWorkType());
                            itemJson.put("backPic", ((ItemExcellent) item).getBackPic());
                            itemJson.put("backMapped", ((ItemExcellent) item).getMapped());

                            List<ExcellentArticle> articleList = ((ItemExcellent) item).getArticleList();
                            List<Map<String,String>> jsonList = new ArrayList<Map<String,String>>();
                            for(ExcellentArticle msg : articleList){
                                Map<String,String> msgMap = new HashMap<String,String>();
                                msgMap.put("title", msg.getTitle());
                                msgMap.put("author", msg.getAuthor());
                                msgMap.put("path", msg.getPath());
                                //msgMap.put("mapped", mapped);
                                msgMap.put("mapped", msg.getMapped());
                                jsonList.add(msgMap);
                            }
                            itemJson.put("articleList", jsonList);
                        }
                        else if(item instanceof ItemTime){
                            itemJson.put("scX", ((ItemTime) item).getScX());
                            itemJson.put("scY", ((ItemTime) item).getScY());
                            itemJson.put("type", ((ItemTime) item).getType());
                            itemJson.put("bgC", ((ItemTime) item).getBGC());
                            itemJson.put("ftC", ((ItemTime) item).getFTC());
                            itemJson.put("bdW", ((ItemTime) item).getBDW());
                            itemJson.put("bdC", ((ItemTime) item).getBDC());
                        }
                        else if(item instanceof ItemWeather){
                            itemJson.put("scX", ((ItemWeather) item).getScX());
                            itemJson.put("scY", ((ItemWeather) item).getScY());
                            itemJson.put("type", ((ItemWeather) item).getType());
                            itemJson.put("bgC", ((ItemWeather) item).getBGC());
                            itemJson.put("ftC", ((ItemWeather) item).getFTC());
                            itemJson.put("bdW", ((ItemWeather) item).getBDW());
                            itemJson.put("bdC", ((ItemWeather) item).getBDC());
                            itemJson.put("city", ((ItemWeather) item).getCity());
                            itemJson.put("serverIp", ((ItemWeather) item).getServerIp());
                        }
                        else if(item instanceof ItemCountdown){
                            itemJson.put("scX", ((ItemCountdown) item).getScX());
                            itemJson.put("scY", ((ItemCountdown) item).getScY());
                            itemJson.put("type", ((ItemCountdown) item).getType());
                            itemJson.put("tymd", ((ItemCountdown) item).getTymd());
                            itemJson.put("tymdhms", ((ItemCountdown) item).getTymdhms());
                            itemJson.put("tymdval", ((ItemCountdown) item).getTymdval());
                            itemJson.put("tymdhmsval", ((ItemCountdown) item).getTymdhmsval());
//					    	itemJson.put("fontsize", ((ItemCountdown) item).getFontsize());
                            itemJson.put("fontcolor", ((ItemCountdown) item).getFontcolor());
//					    	itemJson.put("numbercolor", ((ItemCountdown) item).getNumbercolor());
                        }
                        else if(item instanceof ItemCourseList){
                            itemJson.put("scX", ((ItemCourseList) item).getScX());
                            itemJson.put("scY", ((ItemCourseList) item).getScY());
                            itemJson.put("cListID", ((ItemCourseList) item).getCListID());
                            itemJson.put("bgImg", ((ItemCourseList) item).getBGImg());
                            itemJson.put("bgMapped", ((ItemCourseList) item).getBGMapped());
                            itemJson.put("cData", ((ItemCourseList) item).getCData());
                            itemJson.put("serverIp", ((ItemCourseList) item).getServerIp());
                        }
                    }

                    itemList.put(itemJson);
                }
            }
            String string = itemList.toString();
            String result = StringUtil.getUtf8Str(string);//gbk
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * todo 加注释
     * @param savePath
     * @return
     */
    public Item parseItemXml(String savePath){
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点
            Element itemId = root.element("bid");
            Element index = root.element("idx");
            Element type = root.element("t");
            Element x = root.element("x");
            Element y = root.element("y");
            Element html = root.element("iH");

            if(type.getText().equals("1")){//todo 加注释
                ItemImage item = new ItemImage();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());
                item.setiH(html.getText());

                Element path = root.element("path");
                item.setPath(path.getText());

                Element mapped = root.element("mapped");
                item.setMapped(mapped.getText());

                Element w = root.element("w");
                item.setW(w.getText());

                Element h = root.element("h");
                item.setH(h.getText());

                // 部件的锁定 解锁
//		        Element op = root.element("op");
//		        item.setoP(op.getText());

                Element rds = root.element("rds");
                item.setRds(rds.getText());

                return item;
            }
            else if(type.getText().equals("2")){//todo 加注释
                ItemVideo item = new ItemVideo();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element path = root.element("path");
                item.setPath(path.getText());
                Element mapped = root.element("mapped");
                item.setMapped(mapped.getText());
                Element w = root.element("w");
                item.setW(w.getText());
                Element h = root.element("h");
                item.setH(h.getText());

                return item;
            }
            else if(type.getText().equals("3")){//todo 加注释
                ItemRectangular item = new ItemRectangular();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element rds = root.element("rds");
                item.setRds(rds.getText());

                Element bdC = root.element("bdC");
                item.setBdC(bdC.getText());

                Element bdW = root.element("bdW");
                item.setBdW(bdW.getText());

                Element bgC = root.element("bgC");
                item.setBgC(bgC.getText());

                Element w = root.element("w");
                item.setW(w.getText());

                Element h = root.element("h");
                item.setH(h.getText());

                return item;
            }
            else if(type.getText().equals("4")||type.getText().equals("9")||type.getText().equals("10")){//todo 加注释
                ItemCarousel item = new ItemCarousel();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element xc = root.element("scX");
                item.setScX(xc.getText());
                Element yc = root.element("scY");
                item.setScY(yc.getText());
                Element isFtp = root.element("isFtp");
                item.setIsFtp(isFtp.getText());
                Element ftpPath = root.element("ftpPath");
//		        if(ftpPath != null){
//		        	item.setFtpPath(ftpPath.getText());
//		        }
                item.setFtpPath(ftpPath.getText());
                Element during = root.element("during");
                item.setDuring(during.getText());

                Element imageElement = root.element("imgLoop");
                List<CarouseImage> imageList = new ArrayList<CarouseImage>();
                if(imageElement!=null){
                    List<?> list = imageElement.elements("image");
                    for(Object element : list){
                        String title = ((Element) element).elementText("title");
                        String path = ((Element) element).elementText("path");
                        String mapped = ((Element) element).elementText("mapped");

                        CarouseImage cImage = new CarouseImage();
                        cImage.setTitle(title);
                        cImage.setPath(path);
                        cImage.setMapped(mapped);
                        imageList.add(cImage);
                    }
                    item.setImageList(imageList);
                }
                return item;
            }
            else if(type.getText().equals("5")){//todo 加注释
                ItemText item = new ItemText();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());
                item.setiH(html.getText());

                Element con = root.element("con");
                item.setCon(con.getText());
                Element w = root.element("w");
                item.setW(w.getText());
                Element h = root.element("h");
                item.setH(h.getText());

                Element speed = root.element("speed");
                item.setSpeed(speed.getText());
                Element dim = root.element("dim");
                item.setDim(dim.getText());

                return item;
            }
            else if(type.getText().equals("25")){//todo 加注释
                ItemText item = new ItemText();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());
                item.setiH(html.getText());

                Element con = root.element("con");
                item.setCon(con.getText());
                Element w = root.element("w");
                item.setW(w.getText());
                Element h = root.element("h");
                item.setH(h.getText());

                Element speed = root.element("speed");
                item.setSpeed(speed.getText());
                Element dim = root.element("dim");
                item.setDim(dim.getText());

                return item;
            }
            else if(type.getText().equals("6")||type.getText().equals("7")){//todo 加注释
                ItemLine item = new ItemLine();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element bdS = root.element("bdS");
                item.setBdS(bdS.getText());

                Element bdC = root.element("bdC");
                item.setBdC(bdC.getText());

                Element w = root.element("w");
                item.setW(w.getText());

                Element h = root.element("h");
                item.setH(h.getText());

                return item;
            }
            else if(type.getText().equals("8") || type.getText().equals("18")){// “18”-->PPT
                ItemSwipe item = new ItemSwipe();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element h = root.element("h");
                item.setH(h.getText());
                Element w = root.element("w");
                item.setW(w.getText());
                Element isFtp = root.element("isFtp");
                item.setIsFtp(isFtp.getText());
                Element ftpPath = root.element("ftpPath");
                item.setFtpPath(ftpPath.getText());
                Element during = root.element("during");
                item.setDuring(during.getText());

                Element imageElement = root.element("imgLoop");
                List<CarouseImage> imageList = new ArrayList<CarouseImage>();
                if(imageElement!=null){
                    List<?> list = imageElement.elements("image");
                    for(Object element : list){
                        String title = ((Element) element).elementText("title");
                        String path = ((Element) element).elementText("path");
                        String mapped = ((Element) element).elementText("mapped");

                        CarouseImage cImage = new CarouseImage();
                        cImage.setTitle(title);
                        cImage.setPath(path);
                        cImage.setMapped(mapped);
                        imageList.add(cImage);
                    }
                    item.setImageList(imageList);
                }
                return item;
            }
            else if(type.getText().equals("11")){ //todo 加注释
                ItemFlash flash = new ItemFlash();

                flash.setBid(itemId.getText());
                flash.setIdx(index.getText());
                flash.setT(type.getText());
                flash.setX(x.getText());
                flash.setY(y.getText());
                flash.setiH(html.getText());

                Element path = root.element("path");
                flash.setPath(path.getText());
                Element mapped = root.element("mapped");
                flash.setMapped(mapped.getText());
                Element w = root.element("w");
                flash.setW(w.getText());
                Element h = root.element("h");
                flash.setH(h.getText());

                return flash;
            }
            else if(type.getText().equals("12")){//todo 加注释
                ItemPPT ppt = new ItemPPT();

                ppt.setBid(itemId.getText());
                ppt.setIdx(index.getText());
                ppt.setT(type.getText());
                ppt.setX(x.getText());
                ppt.setY(y.getText());
                ppt.setiH(html.getText());

                Element path = root.element("path");
                ppt.setPath(path.getText());
                Element mapped = root.element("mapped");
                ppt.setMapped(mapped.getText());
                Element w = root.element("w");
                ppt.setW(w.getText());
                Element h = root.element("h");
                ppt.setH(h.getText());

                return ppt;
            }
            else if(type.getText().equals("13")){//todo 加注释
                ItemWebPage webPage = new ItemWebPage();

                webPage.setBid(itemId.getText());
                webPage.setIdx(index.getText());
                webPage.setT(type.getText());
                webPage.setX(x.getText());
                webPage.setY(y.getText());
                webPage.setiH(html.getText());

                Element w = root.element("w");
                webPage.setW(w.getText());
                Element h = root.element("h");
                webPage.setH(h.getText());
                Element url = root.element("url");
                webPage.setUrl(url.getText());
                return webPage;
            }
            else if(type.getText().equals("14")){//todo 加注释
                ItemStreaming stream = new ItemStreaming();

                stream.setBid(itemId.getText());
                stream.setIdx(index.getText());
                stream.setT(type.getText());
                stream.setX(x.getText());
                stream.setY(y.getText());
                stream.setiH(html.getText());

                Element w = root.element("w");
                stream.setW(w.getText());
                Element h = root.element("h");
                stream.setH(h.getText());
                Element url = root.element("url");
                stream.setUrl(url.getText());
                Element thirdstream = root.element("thirdstream");
                stream.setThirdstream(thirdstream.getText());
                Element honghestream = root.element("honghestream");
                stream.setHonghestream(honghestream.getText());
                return stream;
            }
            else if(type.getText().equals("15")){//todo 加注释
                ItemExcellent item = new ItemExcellent();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element h = root.element("h");
                item.setH(h.getText());
                Element w = root.element("w");
                item.setW(w.getText());
                Element workType = root.element("workType");
                item.setWorkType(workType.getText());
                Element backPic = root.element("backPic");
                item.setBackPic(backPic.getText());
                Element backMapped = root.element("mapped");
                item.setMapped(backMapped.getText());

                Element articleElement = root.element("articleLoop");
                List<ExcellentArticle> articleList = new ArrayList<ExcellentArticle>();
                if(articleElement!=null){
                    List<?> list = articleElement.elements("article");
                    for(Object element : list){
                        String title = ((Element) element).elementText("title");
                        String author = ((Element) element).elementText("author");
                        String path = ((Element) element).elementText("path");
                        String mapped = ((Element) element).elementText("mapped");

                        ExcellentArticle cArticle = new ExcellentArticle();
                        cArticle.setTitle(title);
                        cArticle.setAuthor(author);
                        cArticle.setPath(path);
                        cArticle.setMapped(mapped);
                        articleList.add(cArticle);
                    }
                    item.setArticleList(articleList);
                }
                return item;
            }
            else if (type.getText().equals("16")) {//todo 加注释
                ItemTime item = new ItemTime();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element xc = root.element("scX");
                item.setScX(xc.getText());
                Element yc = root.element("scY");
                item.setScY(yc.getText());
                Element itemType = root.element("type");
                item.setType(itemType.getText());
                Element bgC = root.element("bgC");
                item.setBGC(bgC.getText());
                Element ftC = root.element("ftC");
                item.setFTC(ftC.getText());
                Element bdW = root.element("bdW");
                item.setBDW(bdW.getText());
                Element bdC = root.element("bdC");
                item.setBDC(bdC.getText());

                return item;
            }
            else if (type.getText().equals("19")) {//todo 加注释
                ItemWeather item = new ItemWeather();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element xc = root.element("scX");
                item.setScX(xc.getText());
                Element yc = root.element("scY");
                item.setScY(yc.getText());
                Element itemType = root.element("type");
                item.setType(itemType.getText());
                Element bgC = root.element("bgC");
                item.setBGC(bgC.getText());
                Element ftC = root.element("ftC");
                item.setFTC(ftC.getText());
                Element bdW = root.element("bdW");
                item.setBDW(bdW.getText());
                Element bdC = root.element("bdC");
                item.setBDC(bdC.getText());
                Element city = root.element("city");
                item.setCity(city.getText());
                Element serverIp = root.element("serverIp");
                item.setServerIp(serverIp.getText());

                return item;
            }
            else if (type.getText().equals("26")) {//todo 加注释
                ItemCountdown item = new ItemCountdown();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element xc = root.element("scX");
                item.setScX(xc.getText());
                Element yc = root.element("scY");
                item.setScY(yc.getText());
                Element itemType = root.element("type");
                item.setType(itemType.getText());
                Element tymd = root.element("tymd");
                item.setTymd(tymd.getText());
                Element tymdhms = root.element("tymdhms");
                item.setTymdhms(tymdhms.getText());
                Element tymdval = root.element("tymdval");
                item.setTymdval(tymdval.getText());
                Element tymdhmsval = root.element("tymdhmsval");
                item.setTymdhmsval(tymdhmsval.getText());
//		        Element fontsize = root.element("fontsize");
//		        item.setFontsize(fontsize.getText());
                Element fontcolor = root.element("fontcolor");
                item.setFontcolor(fontcolor.getText());
//		        Element numbercolor = root.element("numbercolor");
//		        item.setNumbercolor(numbercolor.getText());

                return item;
            }
            else if (type.getText().equals("22")) {//todo 加注释
                ItemCourseList item = new ItemCourseList();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element xc = root.element("scX");
                item.setScX(xc.getText());
                Element yc = root.element("scY");
                item.setScY(yc.getText());
                Element cListID = root.element("cListID");
                item.setCListID(cListID.getText());
                Element bgImg = root.element("bgImg");
                item.setBGImg(bgImg.getText());
                Element bgMapped = root.element("bgMapped");
                item.setBGMapped(bgMapped.getText());
                Element cData = root.element("cData");
                item.setCData(cData.getText());
                Element serverIp = root.element("serverIp");
                item.setServerIp(serverIp.getText());
                return item;
            }
            else if(type.getText().equals("27")){//todo 加注释
                ItemClassdynamics item = new ItemClassdynamics();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element w = root.element("w");
                item.setW(w.getText());
                Element h = root.element("h");
                item.setH(h.getText());

                return item;
            }
            else if(type.getText().equals("28")){//todo 加注释
                ItemNotice item = new ItemNotice();

                item.setBid(itemId.getText());
                item.setIdx(index.getText());
                item.setT(type.getText());
                item.setX(x.getText());
                item.setY(y.getText());

                item.setiH(html.getText());

                Element w = root.element("w");
                item.setW(w.getText());
                Element h = root.element("h");
                item.setH(h.getText());

                return item;
            }
            return null;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @return
     */
    public List<Map<String,Object>> getItemList(String userName,String prid,String pid) {
        String path = SearchPathTools.getEditPage(userName, prid, pid, false);

        File folder = new File(path);
        File[] folders = folder.listFiles();

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (File itemFile : folders){
            if(itemFile.getName().startsWith("blk")&&itemFile.getName().endsWith(".xml")){
                String xmlPath = path+"/"+itemFile.getName();
                Map<String,Object> attrHash = this.parseItemAttr(xmlPath);
                list.add(attrHash);
            }
        }
        return list;
    }

    /**
     * todo 加注释
     * @param savePath
     * @return
     */
    public Map<String,Object> parseItemAttr(String savePath){
        Map<String,Object> hash = new HashMap<String,Object>();
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));

            Element root = xmlDoc.getRootElement();
            Iterator<?> iterator = root.elementIterator();
            while(iterator.hasNext()){
                Element ele = (Element) iterator.next();
                if(ele.getName().equals("imgLoop")){
                    List<?> loopElement = ele.elements("image");
                    List<Map<String, String>> subItemList = new ArrayList<Map<String, String>>();

                    for(Object element : loopElement){
                        Element eo = (Element) element;

                        Iterator<?> imElement = eo.elementIterator();
                        Map<String, String> hashMap = new HashMap<String,String>();
                        while(imElement.hasNext()){
                            Element subItem = (Element) imElement.next();
                            hashMap.put(subItem.getName(), subItem.getText());
                        }
                        subItemList.add(hashMap);
                    }
                    hash.put(ele.getName(), subItemList);
                }
                else if(ele.getName().equals("articleLoop")){
                    List<?> loopElement = ele.elements("article");
                    List<Map<String, String>> subItemList = new ArrayList<Map<String, String>>();

                    for(Object element : loopElement){
                        Element eo = (Element) element;

                        Iterator<?> imElement = eo.elementIterator();
                        Map<String, String> hashMap = new HashMap<String,String>();
                        while(imElement.hasNext()){
                            Element subItem = (Element) imElement.next();
                            hashMap.put(subItem.getName(), subItem.getText());
                        }
                        subItemList.add(hashMap);
                    }
                    hash.put(ele.getName(), subItemList);
                }
                else {
                    String value = ele.getText();
                    hash.put(ele.getName(), value);
                }
            }
            return hash;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo 加注释
     * @param path
     * @param attrHash
     * @param username
     * @param prid
     * @param program
     * @return
     */
    public String mergeItem(String path,Map<String, Object> attrHash,String username,String prid,Program program){
        try {
            String html = FileUtil.getFileContent(path, "UTF-8");
            String type = (String) attrHash.get("t");
            String globalPath = (String) attrHash.get("path");
            String countdowntype = "";
            if (globalPath == null || "".endsWith(globalPath)) {
                globalPath = (String) attrHash.get("bgImg");
            }
            if (globalPath == null || "".endsWith(globalPath)) {
                globalPath = (String) attrHash.get("backPic");
            }
            if (attrHash.get("fontcolor") != null && ((String) attrHash.get("fontcolor")).equals("")) {  // 倒计时 如果没有设置字体颜色  则默认为白色
                html = html.replace("@#+fontcolor+#@", "rgb(250,250,250)");
            }

            Set<Map.Entry<String, Object>> set = attrHash.entrySet();
            String globalMapped = "";
            if (type.equals("2") && attrHash.get("mapped") != null && ((String) attrHash.get("mapped")).indexOf("defaultvideothumb") > -1) {
                // 在线视频
                globalMapped = (String) attrHash.get("path");
            } else {
                globalMapped = "@dx";
                for (Map.Entry e : set) {
                    if ("mapped".equals(e.getKey())) {
                        globalMapped = (String) e.getValue();
                        globalMapped = newsService.getPath(globalMapped, globalPath);
                    } else if ("bgMapped".equals(e.getKey())) {
                        globalMapped = (String) e.getValue();
                        globalMapped = newsService.getPath(globalMapped, globalPath);
                    }
                }
            }
            Iterator<Map.Entry<String, Object>> itor0 = set.iterator();
            String tymdval = (String) attrHash.get("tymdval");
            String tymdhmsval = (String) attrHash.get("tymdhmsval");
            Iterator<Map.Entry<String, Object>> itor = set.iterator();
            while (itor.hasNext()) {
                Map.Entry<String, Object> entry = itor.next();
                if (entry.getKey().equals("imgLoop")) {
                    if (type.equals("4") || type.equals("8") || type.equals("9")) {//todo 加注释
                        List<Map<String, String>> subItem = (List<Map<String, String>>) entry.getValue();
                        String subHtmls = "";
                        int startIdx = html.indexOf("@@##") + 4;
                        int endIdx = html.indexOf("##@@");
                        String modelHtml = html.substring(startIdx, endIdx);

                        for (Map<String, String> map : subItem) {
                            Set<Map.Entry<String, String>> hashset = map.entrySet();
                            Iterator<Map.Entry<String, String>> it = hashset.iterator();
                            String liHtml = modelHtml;
                            //处理预备数据，当xml读取的是导入后的文件时， 数据库并不存在响应的virtualPath,需拼出来
                            String srcPath = "";
                            for (Map.Entry<String, String> e : hashset) {
                                if ("mapped".equals(e.getKey())) {
                                    srcPath = e.getValue();
                                }
                            }
                            while (it.hasNext()) {
                                Map.Entry<String, String> entry1 = (Map.Entry<String, String>) it.next();
                                if (entry1.getKey().equals("path")) {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", newsService.getPathByVirtual2(false, entry1.getValue(), srcPath));
                                } else if (entry1.getKey().equals("bid")) {
                                    liHtml = liHtml.replaceAll("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                                } else if (entry1.getKey().equals("title")) {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue().replace(" ", "&nbsp;"));
                                } else {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                                }
                            }
                            subHtmls = subHtmls + liHtml;
                        }
                        html = html.replace("@@##" + modelHtml + "##@@", subHtmls);
                    }
                    if (type.equals("18")) {// “18”-->PPT  PPT预览资源的加载
                        List<Map<String, String>> subItem = (List<Map<String, String>>) entry.getValue();
                        String subHtmls = "";
                        int startIdx = html.indexOf("@@##") + 4;
                        int endIdx = html.indexOf("##@@");
                        String modelHtml = html.substring(startIdx, endIdx);

                        for (Map<String, String> map : subItem) {
                            Set<Map.Entry<String, String>> hashset = map.entrySet();
                            Iterator<Map.Entry<String, String>> it = hashset.iterator();
                            String liHtml = modelHtml;
                            //处理预备数据，当xml读取的是导入后的文件时， 数据库并不存在响应的virtualPath,需拼出来
                            String srcPath = "";
                            for (Map.Entry<String, String> e : hashset) {
                                if ("mapped".equals(e.getKey())) {
                                    srcPath = e.getValue();
                                }
                            }
                            while (it.hasNext()) {
                                Map.Entry<String, String> entry1 = (Map.Entry<String, String>) it.next();
                                if (entry1.getKey().equals("path")) {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", newsService.getPathByVirtual2Ppt(false, entry1.getValue(), srcPath));
                                } else if (entry1.getKey().equals("bid")) {
                                    liHtml = liHtml.replaceAll("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                                } else if (entry1.getKey().equals("title")) {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue().replace(" ", "&nbsp;"));
                                } else {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                                }
                            }
                            subHtmls = subHtmls + liHtml;
                        }
                        html = html.replace("@@##" + modelHtml + "##@@", subHtmls);
                    } else if (type.equals("10")) {
                        List<Map<String, String>> subItem = (List<Map<String, String>>) entry.getValue();
                        String subHtmls = "";
                        int startIdx = html.indexOf("@@##") + 4;
                        int endIdx = html.indexOf("##@@");
                        String modelHtml = html.substring(startIdx, endIdx);

                        for (Map<String, String> map : subItem) {

                            Set<Map.Entry<String, String>> hashset = map.entrySet();
                            Iterator<Map.Entry<String, String>> it = hashset.iterator();
                            String liHtml = modelHtml;
                            //处理预备数据，当xml读取的是导入后的文件时， 数据库并不存在响应的virtualPath,需拼出来
                            String srcPath = "";
                            for (Map.Entry<String, String> e : hashset) {
                                if ("mapped".equals(e.getKey())) {
                                    srcPath = e.getValue();
                                }
                            }
                            while (it.hasNext()) {
                                Map.Entry<String, String> entry1 = (Map.Entry<String, String>) it.next();

                                if (entry1.getKey().equals("path")) {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", newsService.getPathByVirtual2(false, entry1.getValue(), srcPath));
                                } else {
                                    liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                                }

                            }
                            subHtmls = subHtmls + liHtml;
                        }
                        html = html.replace("@@##" + modelHtml + "##@@", subHtmls);

                        int stIdx = html.indexOf("++@@") + 4;
                        int edIdx = html.indexOf("@@++");

                        String spotModelHtml = html.substring(stIdx, edIdx);
                        String soptSubHtml = "";
                        for (int i = 0; i < subItem.size(); i++) {
                            soptSubHtml = soptSubHtml + spotModelHtml;
                        }
                        html = html.replace("++@@" + spotModelHtml + "@@++", soptSubHtml);
                    }
                } else if (entry.getKey().equals("articleLoop")) {
                    List<Map<String, String>> subItem = (List<Map<String, String>>) entry.getValue();

                    String subHtmls = "";

                    int startIdx = html.indexOf("@@##") + 4;
                    int endIdx = html.indexOf("##@@");
                    String modelHtml = html.substring(startIdx, endIdx);

                    for (Map<String, String> map : subItem) {
                        Set<Map.Entry<String, String>> hashset = map.entrySet();
                        Iterator<Map.Entry<String, String>> it = hashset.iterator();
                        String liHtml = modelHtml;
                        //处理预备数据，当xml读取的是导入后的文件时， 数据库并不存在响应的virtualPath,需拼出来
                        String srcPath = "";
                        for (Map.Entry<String, String> e : hashset) {
                            if ("mapped".equals(e.getKey())) {
                                srcPath = e.getValue();
                            }
                        }
                        while (it.hasNext()) {
                            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) it.next();
                            if (entry1.getKey().equals("path")) {
                                String srcP = newsService.getPathByVirtual2(false, entry1.getValue(), srcPath);
                                String srcList = "";
                                srcList = newsService.getSrcList(srcP, username, prid);
                                liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", srcList);
                            } else if (entry1.getKey().equals("title")) {
                                liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                                liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                            } else if (entry1.getKey().equals("mapped")) {
                                String[] strs = entry1.getValue().split("/");
                                String str = "../media/" + strs[5] + "/" + strs[6];
                                liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", str);
                            } else {
                                liHtml = liHtml.replace("$%!+" + entry1.getKey() + "+!%$", entry1.getValue());
                            }
                        }
                        subHtmls = subHtmls + liHtml;
                    }
                    html = html.replace("@@##" + modelHtml + "##@@", subHtmls);

                } else if (entry.getKey().equals("backPic")) {

                    String backPic = globalMapped;
                    html = html.replace("@#+" + entry.getKey() + "+#@", backPic);
                } else if (entry.getKey().equals("bgImg")) {

                    String bgImg = (String) entry.getValue();
                    if (!bgImg.equals("images/cList_dftBg.png"))
                        bgImg = globalMapped;
                    html = html.replace("@#+" + entry.getKey() + "+#@", bgImg);
                } else if (entry.getKey().equals("fontcolor")) {
                    String fontcolor = (String) entry.getValue();
                    html = html.replace("@#+" + entry.getKey() + "+#@", fontcolor);
                } else if (entry.getKey().equals("tymd")) {
                    if (((String) entry.getValue()).equals("2") && !tymdval.equals("")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        long distanceTimes = newsService.getDistanceDays(sdf.format(new Date()), tymdval);
                        if (distanceTimes < 10) {
                            html = html.replace("@#+" + entry.getKey() + "+#@", "00" + distanceTimes + "");
                        } else if (distanceTimes < 100) {
                            html = html.replace("@#+" + entry.getKey() + "+#@", "0" + distanceTimes + "");
                        } else {
                            html = html.replace("@#+" + entry.getKey() + "+#@", distanceTimes + "");
                        }
                        html = html.replace("@#+cdms+#@", tymdval + "");
                    } else if (((String) entry.getValue()).equals("2") && tymdval.equals("")) {
                        html = html.replace("@#+" + entry.getKey() + "+#@", "000");
                        html = html.replace("@#+cdms+#@", "");
                    }
                } else if (entry.getKey().equals("tymdhms")) {
                    if (((String) entry.getValue()).equals("2") && !tymdhmsval.equals("")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        long[] distanceTimes = newsService.getDistanceTimes(sdf.format(new Date()), tymdhmsval);
                        if (distanceTimes[0] < 10) {
                            html = html.replace("@#+tymdhmsd+#@", "0" + distanceTimes[0] + "");
                        } else {
                            html = html.replace("@#+tymdhmsd+#@", distanceTimes[0] + "");
                        }
                        if (distanceTimes[1] < 10) {
                            html = html.replace("@#+tymdhmsh+#@", "0" + distanceTimes[1] + "");
                        } else {
                            html = html.replace("@#+tymdhmsh+#@", distanceTimes[1] + "");
                        }
                        if (distanceTimes[2] < 10) {
                            html = html.replace("@#+tymdhmsm+#@", "0" + distanceTimes[2] + "");
                        } else {
                            html = html.replace("@#+tymdhmsm+#@", distanceTimes[2] + "");
                        }
                        if (distanceTimes[3] < 10) {
                            html = html.replace("@#+tymdhmss+#@", "0" + distanceTimes[3] + "");
                        } else {
                            html = html.replace("@#+tymdhmss+#@", distanceTimes[3] + "");
                        }
                        html = html.replace("@#+cdms+#@", tymdhmsval + "");
                    } else if (((String) entry.getValue()).equals("2") && tymdhmsval.equals("")) {
                        html = html.replace("@#+tymdhmsd+#@", "00");
                        html = html.replace("@#+tymdhmsh+#@", "00");
                        html = html.replace("@#+tymdhmsm+#@", "00");
                        html = html.replace("@#+tymdhmss+#@", "00");
                        html = html.replace("@#+cdms+#@", "");
                    }
                } else if (entry.getKey().equals("tymdval") || entry.getKey().equals("tymdhmsval")) {

                } else {
                    if (entry.getKey().equals("path")) {
                        html = html.replace("@#+" + entry.getKey() + "+#@", globalMapped);
                    } else {
                        html = html.replace("@#+" + entry.getKey() + "+#@", (String) entry.getValue());
                    }

                }
            }
            return html;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    public boolean sortItem(String userName,List<Item> itemList){
        for(Item item : itemList){
            String prid = StringUtil.getUtf8Str(item.getPrid());
            String pid = item.getPid();
            String bid = item.getBid();

            String blockPath = SearchPathTools.getEditItem(userName, prid, pid, bid);
            String projectPath = SearchPathTools.getEditProgram(userName, prid, true);

            this.modifyItemXml(blockPath, item);
            newsService.modifyProgramXml(projectPath, new Program());
        }
        return true;
    }

    /**
     * todo 加注释
     * @param savePath
     * @param item
     */
    public void modifyItemXml(String savePath,Item item){
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点

            Element index = root.element("idx");
            if(item.getIdx()!=null&&!item.getIdx().equals("")){
                index.setText(item.getIdx());
            }

            Element x = root.element("x");
            if(item.getX()!=null&&!item.getX().equals("")){
                x.setText(item.getX());
            }

            Element y = root.element("y");
            if(item.getY()!=null&&!item.getY().equals("")){
                y.setText(item.getY());
            }

            newsService.persistenceXml(xmlDoc, savePath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @return
     */
    public List<Map<String,Object>> getTemplateItemList(String userName,String prid,String pid) {
        String path = SearchPathTools.getTemplatePage(userName, prid, pid, false);

        File folder = new File(path);
        File[] folders = folder.listFiles();

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (File itemFile : folders){
            if(itemFile.getName().startsWith("blk")&&itemFile.getName().endsWith(".xml")){
                String xmlPath = path+"/"+itemFile.getName();
                Map<String,Object> attrHash = this.parseItemAttr(xmlPath);
                list.add(attrHash);
            }
        }
        return list;
    }

    /**
     * todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @return
     */
    public List<Map<String,Object>> getPublishItemList(String userName,String prid,String pid) {
        String path = SearchPathTools.getPublishPage(userName, prid, pid, false);

        File folder = new File(path);
        File[] folders = folder.listFiles();

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (File itemFile : folders){
            if(itemFile.getName().startsWith("blk")&&itemFile.getName().endsWith(".xml")){
                String xmlPath = path+"/"+itemFile.getName();
                Map<String,Object> attrHash = this.parseItemAttr(xmlPath);
                list.add(attrHash);
            }
        }
        return list;
    }

    /**
     * PPT资源拷贝
     * @param userName
     * @param prid
     * @param repa
     * @param soupa
     * @throws Exception
     */
    public void copyPPtResource(String userName,String prid,String repa,String soupa) throws Exception{
        String[] tempMaps=repa.split("/");
        String folderName=tempMaps[tempMaps.length-2];
        String oldPath=repa.substring(0, repa.lastIndexOf("/"));
        String copyPath=SearchPathTools.getResources(oldPath);
        copyMD5(userName,prid,copyPath,folderName);
        //拷贝源文件
        String[] oldPath2=repa.split("/");
        String respath=oldPath2[1]+"/"+oldPath2[2]+"/"+oldPath2[3]+"/"+oldPath2[4]+"/"+oldPath2[5];
        String name=oldPath2[6];
        String srcPath=SearchPathTools.getResources(respath);
        copySource(userName,prid,srcPath,name);
        //end
        //建立源文件与原名称对应
        String[] names=soupa.split("/");
        String srcname =names[names.length-1];
        String content=oldPath2[3]+"="+srcname;
        getmapped(userName,prid,content);
    }
    /**
     * 拷贝md5资源文件到根目录下
     * */
    public boolean copyMD5(String userName,String programId,String oldPath,String folder){

        String targetPath="";
        try{

            targetPath=SearchPathTools.getMD5(userName, programId, folder,false);
            File fia = new File(targetPath);
            if(!fia.exists()){
                FileUtil.copyFolder(oldPath, targetPath);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    /**
     *建立源文件与md5名称对应关系
     *
     * */
    public void getmapped(String userName, String programId,String content)throws Exception{

        String	targetPath=SearchPathTools.getMappedPath(userName, programId,false);
        File file=new File(targetPath);
        file.mkdirs();

        FileWriter fw=new FileWriter(new File(targetPath+"map.txt"),true);
        PrintWriter pw=new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
    }
    /**
     * 拷贝源文件到根目录下
     * */
    public boolean copySource(String userName,String programId,String oldPath,String name){
        String targetPath="";
        try{
            targetPath=SearchPathTools.getSource(userName,programId, false);
            File oldFile=null;
            File folder=new File(oldPath);
            if(!folder.exists()||!folder.isDirectory()){
                return false;
            }
            for(File f:folder.listFiles()){
                if(f.getName().contains(name)&&f.isFile()){
                    oldFile=f;
                    break;
                }
            }
            String str=targetPath;
            File fia = new File(str+oldFile.getName());
            if(!fia.exists()){
                File tarFile=new File(str);
                tarFile.mkdirs();
                FileUtil.copyFileByPath(oldFile.getPath(), str+oldFile.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
