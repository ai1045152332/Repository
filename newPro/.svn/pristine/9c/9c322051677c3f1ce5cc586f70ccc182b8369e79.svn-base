package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordhibernate.dao.NewsDao;
import com.honghe.recordhibernate.entity.tools.FileUtil;
import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import com.honghe.recordweb.action.frontend.news.entity.Page;
import com.honghe.recordweb.action.frontend.news.entity.Program;
import com.honghe.recordweb.util.base.util.GlobalParameter;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/25
 */
@Service("PageService")
public class PageService {
    private final static Logger logger = Logger.getLogger(PageService.class);
    @Resource
    NewsService newsService;
    @Resource
    ItemService itemService;
    @Resource
    NewsDao newsDao;

    /**
     * todo 加注释
     * @param savePath
     * @param page
     */
    public void writePageXml(String savePath, com.honghe.recordweb.action.frontend.news.entity.Page page) {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("root");//添加文档根

            Element pageId = root.addElement("pid");
            pageId.addText(page.getPid());

            Element index = root.addElement("idx");
            index.addText(page.getIndex());

            Element time = root.addElement("time");
            time.addText(page.getTime());

            newsService.persistenceXml(document, savePath);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * //todo 加注释
     * @param userName
     * @param page
     * @param copyPid
     * @return
     */
    public int addPage(String userName, com.honghe.recordweb.action.frontend.news.entity.Page page, String copyPid) {
        String prid = page.getPrid();
        String path = SearchPathTools.getEditProgram(userName, prid, false);

        File rootFolder = new File(path);
        File[] folders = rootFolder.listFiles();

        int maxPage = 1;

        for (File i : folders) {
            if (i.isDirectory() && (!i.getName().equals("preview")
                    && (!i.getName().equals("previewSingle")
                    && (!i.getName().equals("media")
                    && (!i.getName().equals("source")
                    && (!i.getName().equals("mapped"))))))) {
                int pageNum = Integer.parseInt(i.getName());
                if (pageNum > maxPage) {
                    maxPage = pageNum;
                }
            }
        }
        String desPath = path + (maxPage + 1);
        File desFile = new File(desPath);
        desFile.mkdir();

        //copy
        if (copyPid != null) {
            String srcPngPath = rootFolder + "\\" + copyPid + "\\" + copyPid + ".png";
            String targetPngPath = desPath + "\\" + (maxPage + 1) + ".png";
            FileUtil.copyFileByPath(srcPngPath, targetPngPath);
        }

        page.setPid("" + (maxPage + 1));
        page.setTime(GlobalParameter.DEFAULT_PROJECT_PAGE_DURATION + "");
        page.setIndex((maxPage + 1) + "");
        String pagePath = SearchPathTools.getEditPage(userName, prid,
                page.getPid(), true);
        this.writePageXml(pagePath, page);

        String folderPath = SearchPathTools
                .getEditProgram(userName, prid, true);
        this.modifyPageSeq(folderPath, page);
        newsService.modifyProgramXml(folderPath, new Program());

        return maxPage + 1;
    }

    /**
     * //todo 加注释
     * @param savePath
     * @param page
     */
    public void modifyPageSeq(String savePath, com.honghe.recordweb.action.frontend.news.entity.Page page) {
        try {
            int index = Integer.parseInt(page.getIndex()) - 1;
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点

            Element seqElement = root.element("pageSequence");

            List<String> pageList = new ArrayList<String>();
            if (seqElement != null) {
                Iterator<?> iterator = seqElement.elementIterator();
                while (iterator.hasNext()) {
                    Element pageElement = (Element) iterator.next();
                    pageList.add(pageElement.getText());
                }
            }

            if (index >= pageList.size() + 1) {
                index = pageList.size();
            }

            pageList.add(index, page.getPid());
            root.remove(seqElement);

            Element pageSeq = root.addElement("pageSequence");
            for (String pageString : pageList) {
                Element pageElement = pageSeq.addElement("page");
                pageElement.addText(pageString);
            }

            newsService.persistenceXml(xmlDoc, savePath);
        } catch (DocumentException e) {
            //                    e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * //todo 加注释
     * @param userId
     * @param page
     * @return
     */
    public boolean removePage(String userId, com.honghe.recordweb.action.frontend.news.entity.Page page) {
        String pagePath = SearchPathTools.getEditPage(userId, page.getPrid(), page.getPid(), false);
        FileUtil.delFolder(pagePath);

        String filePath = SearchPathTools.getEditProgram(userId, page.getPrid(), true);
        this.delPageSeq(filePath, page);
        newsService.modifyProgramXml(filePath, new Program());
        return true;
    }

    /**
     * //todo 加注释
     * @param savePath
     * @param page
     */
    public void delPageSeq(String savePath, com.honghe.recordweb.action.frontend.news.entity.Page page) {
        try {
            SAXReader sax = new SAXReader();
            Document xmlDoc = sax.read(new File(savePath));
            Element root = xmlDoc.getRootElement();//根节点

            Element seqElement = root.element("pageSequence");

            if (seqElement != null) {
                Iterator<?> iterator = seqElement.elementIterator();
                while (iterator.hasNext()) {
                    Element pageElement = (Element) iterator.next();
                    if (pageElement.getText().equals(page.getPid())) {
                        seqElement.remove(pageElement);
                    }
                }
            }
            if (seqElement.element("page") == null) {
                root.remove(seqElement);
            }

            newsService.persistenceXml(xmlDoc, savePath);
        } catch (DocumentException e) {
            //                    e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @param time
     * @return
     */
    public boolean updatePage(String userName, String prid, String pid, String time) {
        boolean updateFlag = true;
        try {
            com.honghe.recordweb.action.frontend.news.entity.Page page = getPageInfo(userName, prid, pid, true);
            page.setPrid(prid);
            page.setPid(pid);
            //page.setIndex(pageForm.getIdx());
            page.setTime(time);
            updatePageFile(userName, page);

            // 如果 preview/project.html文件存在的话  则更新  update 20150203
            String previewPath = SearchPathTools.editPathRelative + userName + "/" + prid + "/preview";
            File file = new File(previewPath);
            if (file.exists()) {
                String proPath = SearchPathTools.getEditProgram(userName, prid, true);
                Program program = newsService.parseProgramXml(proPath);
                List<String> pageList = program.getPageList();

                String pagesHtml = SearchPathTools.getEditPreviewPath(true, 2, userName, prid, "", 0, "");
                JSONArray jsonArray = new JSONArray();
                for (String pids : pageList) {
                    com.honghe.recordweb.action.frontend.news.entity.Page p = this.getPageInfo(userName, prid, pids, true);
                    String pageHtml = this.getPageHtml(userName, prid, pids, program);

                    long timestamp = System.currentTimeMillis();
                    String path = "page-" + pids + "(" + timestamp + ").html";
                    JSONObject obj = new JSONObject();
                    obj.put("path", path);
                    obj.put("during", time);
                    obj.put("pid", pids);
                    jsonArray.put(obj);
                    FileUtil.writeFileContent(SearchPathTools.getEditPreviewPath(false, 3, userName, prid, pids, timestamp, ""), pageHtml, "UTF-8");
                }
                Map<String, String> map = newsService.getProjectMap(prid, "", "", jsonArray.toString(), prid, userName);
                String projectHtml = newsService.getProjectHtml(map);
                FileUtil.writeFileContent(SearchPathTools.getEditPreviewPath(false, 2, userName, prid, "", 0, ""), projectHtml, "UTF-8");
            }
        } catch (Exception e) {
            updateFlag = false;
        }
        return updateFlag;

    }

    /**
     * //todo 加注释
     * @param userName
     * @param page
     * @throws Exception
     */
    private void updatePageFile(String userName, com.honghe.recordweb.action.frontend.news.entity.Page page) throws Exception {
        String path = SearchPathTools.getEditPage(userName, page.getPrid(), page.getPid(), true);
        this.writePageXml(path, page);
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pageId
     * @param flag
     * @return
     * @throws Exception
     */
    public com.honghe.recordweb.action.frontend.news.entity.Page getPageInfo(String userName, String prid, String pageId, boolean flag) throws Exception {
        String path = SearchPathTools.getEditPage(userName, prid, pageId, true);
        com.honghe.recordweb.action.frontend.news.entity.Page page = new com.honghe.recordweb.action.frontend.news.entity.Page();
        Program program = new Program();
        SAXReader sax = new SAXReader();
        Document xmlDoc = sax.read(new File(path));
        Element root = xmlDoc.getRootElement();//根节点
        Element pid = root.element("pid");
        Element idx = root.element("idx");
        Element time = root.element("time");
        page.setIndex(idx.getTextTrim());
        page.setPrid(prid);
        page.setPid(pid.getTextTrim());
        page.setTime(time.getTextTrim());
        return page;
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @param program
     * @return
     */
    public String getPageHtml(String userName, String prid, String pid, Program program) {
        List<Map<String, Object>> attList = itemService.getItemList(userName, prid, pid);
        String itemsHtml = "";
        for (Map<String, Object> map : attList) {
            try {
                String resourcePath = "";
                // 倒计时功能
                String tymd = (String) map.get("tymd");
                String tymdhms = (String) map.get("tymdhms");

                // 倒计时功能
                String thirdstream = (String) map.get("thirdstream");
                String honghestream = (String) map.get("honghestream");
                // 倒计时
                if (tymd != null || tymdhms != null) {
                    if (!tymd.equals("") && tymd.equals("2")) {
                        resourcePath = SearchPathTools.getModelComponentPath("26tymd");
                    } else if (!tymdhms.equals("") && tymdhms.equals("2")) {
                        resourcePath = SearchPathTools.getModelComponentPath("26tymdhms");
                    }
                }
                // 流媒体
                if (thirdstream != null || honghestream != null) {
                    if (!thirdstream.equals("") && thirdstream.equals("2")) {
                        resourcePath = SearchPathTools.getModelComponentPath("14");
                    } else if (!honghestream.equals("") && honghestream.equals("2")) {
                        resourcePath = SearchPathTools.getModelComponentPath("14honghestream");
                    }
                }
                if (resourcePath.equals("")) {
                    resourcePath = SearchPathTools.getModelComponentPath((String) map.get("t"));
                }
                String itemHtml = itemService.mergeItem(resourcePath, map, userName, prid, program);
                itemsHtml = itemsHtml + "\n" + itemHtml;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String pageModelHtml = FileUtil.getFileContent(SearchPathTools.getModelPath(3), "UTF-8");

        String pageHtml = pageModelHtml.replace("<div class=\"container\"></div>", "<div class=\"container\">" + itemsHtml + "</div>");

        return pageHtml;
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @param program
     * @return
     */
    public String getPublishPageHtml(String userName, String prid, String pid, Program program) {
        List<Map<String, Object>> attList = itemService.getPublishItemList(userName, prid, pid);
        String itemsHtml = "";
        for (Map<String, Object> map : attList) {
            String resourcePath = "";
            // 倒计时功能
            String tymd = (String) map.get("tymd");
            String tymdhms = (String) map.get("tymdhms");
            // 倒计时功能
            String thirdstream = (String) map.get("thirdstream");
            String honghestream = (String) map.get("honghestream");
            if (tymd != null || tymdhms != null) {
                if (!tymd.equals("") && tymd.equals("2")) {
                    resourcePath = SearchPathTools.getModelComponentPath("26tymd");
                } else if (!tymdhms.equals("") && tymdhms.equals("2")) {
                    resourcePath = SearchPathTools.getModelComponentPath("26tymdhms");
                }
            }
            // 流媒体
            if (thirdstream != null || honghestream != null) {
                if (!thirdstream.equals("") && thirdstream.equals("2")) {
                    resourcePath = SearchPathTools.getModelComponentPath("14");
                } else if (!honghestream.equals("") && honghestream.equals("2")) {
                    resourcePath = SearchPathTools.getModelComponentPath("14honghestream");
                }
            }
            if (resourcePath.equals("")) {
                resourcePath = SearchPathTools.getModelComponentPath((String) map.get("t"));
            }
            String itemHtml = itemService.mergeItem(resourcePath, map, userName, prid, program);
            itemsHtml = itemsHtml + "\n" + itemHtml;
        }
        String pageModelHtml = FileUtil.getFileContent(SearchPathTools.getModelPath(3), "UTF-8");

        String pageHtml = pageModelHtml.replace("<div class=\"container\"></div>", "<div class=\"container\">" + itemsHtml + "</div>");

        return pageHtml;
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pid
     * @param program
     * @return
     */
    public String getTemplatePageHtml(String userName, String prid, String pid, Program program) {
        List<Map<String, Object>> attList = itemService.getTemplateItemList(userName, prid, pid);
        String itemsHtml = "";
        for (Map<String, Object> map : attList) {
            String resourcePath = "";
            // 倒计时功能
            String tymd = (String) map.get("tymd");
            String tymdhms = (String) map.get("tymdhms");
            if (tymd != null || tymdhms != null) {
                if (!tymd.equals("") && tymd.equals("2")) {
                    resourcePath = SearchPathTools.getModelComponentPath("26tymd");
                } else if (!tymdhms.equals("") && tymdhms.equals("2")) {
                    resourcePath = SearchPathTools.getModelComponentPath("26tymdhms");
                }
            }
            if (resourcePath.equals("")) {
                resourcePath = SearchPathTools.getModelComponentPath((String) map.get("t"));
            }
            String itemHtml = itemService.mergeItem(resourcePath, map, userName, pid, program);
            itemsHtml = itemsHtml + "\n" + itemHtml;
        }
        String pageModelHtml = FileUtil.getFileContent(SearchPathTools.getModelPath(3), "UTF-8");

        String pageHtml = pageModelHtml.replace("<div class=\"container\"></div>", "<div class=\"container\">" + itemsHtml + "</div>");

        return pageHtml;
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pageId
     * @param flag
     * @return
     * @throws Exception
     */
    public Page getTemplatePageInfo(String userName, String prid, String pageId, boolean flag) throws Exception {
        String path = SearchPathTools.getTemplatePage(userName, prid, pageId, true);
        Page page = new Page();
        Program program = new Program();
        SAXReader sax = new SAXReader();
        Document xmlDoc = sax.read(new File(path));
        Element root = xmlDoc.getRootElement();//根节点
        Element pid = root.element("pid");
        Element idx = root.element("idx");
        Element time = root.element("time");
        page.setIndex(idx.getTextTrim());
        page.setPrid(prid);
        page.setPid(pid.getTextTrim());
        page.setTime(time.getTextTrim());
        return page;
    }

    /**
     * //todo 加注释
     * @param userName
     * @param prid
     * @param pageId
     * @param flag
     * @return
     * @throws Exception
     */
    public Page getPublishPageInfo(String userName, String prid, String pageId, boolean flag) throws Exception {
        String path = SearchPathTools.getPublishPage(userName, prid, pageId, true);
        Page page = new Page();
        Program program = new Program();
        SAXReader sax = new SAXReader();
        Document xmlDoc = sax.read(new File(path));
        Element root = xmlDoc.getRootElement();//根节点
        Element pid = root.element("pid");
        Element idx = root.element("idx");
        Element time = root.element("time");
        page.setIndex(idx.getTextTrim());
        page.setPrid(prid);
        page.setPid(pid.getTextTrim());
        page.setTime(time.getTextTrim());
        return page;
    }

    /**
     * //todo 加注释
     * @param userName
     * @param page
     * @return
     */
    public boolean sortPage(String userName, com.honghe.recordweb.action.frontend.news.entity.Page page) {
        String prid = page.getPrid();

        String filePath = SearchPathTools.getEditProgram(userName, prid, true);

        newsService.sortPageXml(filePath, page);
        newsService.modifyProgramXml(filePath, new Program());
        if (page.getIndex().equals("1")) {
            // 当移动page的位置的时候  如果移动到了第一页的位置   则要修改封面图
            String thumbPath = SearchPathTools.getThumb(userName, prid, false);
            String srcthumbPath = SearchPathTools.getEditProgram(userName, page.getPrid(), false) + page.getPid() + "/" + "thumb.png";
            String targetthumbPath = thumbPath;
            File oldFile = new File(srcthumbPath);
            File targetFile = new File(targetthumbPath);
            FileUtil.copyFile(oldFile, targetFile);
        }
        return true;
    }
}
