package com.honghe.recordweb.action.frontend.news;

import com.honghe.recordhibernate.entity.News;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordhibernate.entity.tools.FileUtil;
import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.action.frontend.news.entity.Page;
import com.honghe.recordweb.action.frontend.news.entity.Program;
import com.honghe.recordweb.action.frontend.news.form.ProjectPageForm;
import com.honghe.recordweb.action.frontend.news.form.ProjectPreviewForm;
import com.honghe.recordweb.service.frontend.news.ItemService;
import com.honghe.recordweb.service.frontend.news.NewsService;
import com.honghe.recordweb.service.frontend.news.PageService;
import com.honghe.recordweb.util.base.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

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
public class PageAction extends BaseAction{
    private final static Logger logger = Logger.getLogger(PageAction.class);
    @Resource
    NewsService newsService;
    @Resource
    private ItemService itemService;
    @Resource
    private PageService pageService;

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    private String prid;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    private String idx;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    private String pid;
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
    private String proName;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String uuid=null;

    /**
     * todo 加注释
     * @throws Exception
     */
    public void add() throws Exception{
        ProjectPageForm pageForm = new ProjectPageForm();
        //pageForm.setPrid(StringUtil.getUtf8Str(pageForm.getPrid()));
        pageForm.setPrid(prid);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        String programId = pageForm.getPrid();
        String index = pageForm.getIdx();
        Page page = new Page();
        page.setPrid(programId);
        page.setIndex(index);
        int pageNum = pageService.addPage(userId, page, null);
        sendMsg(""+pageNum);
    }

    /**
     * todo 加注释
     */
    public void snap(){
        try {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            String userId = user.getUserId().toString();
            String userName = user.getUserName().toString();
            String programId = prid;
            String pageId = pid;
//            String quipub = "0";
//            if(pageForm.getQuipub()!=null && !pageForm.getQuipub().equals("")){
//                quipub = pageForm.getQuipub(); // 快速发布的标志
//            }
            News news = newsService.getNewsById(Integer.parseInt(programId));
            news.setnStatus(2);
            newsService.updateNews(news);
            String postData = data.replace("data:image/png;base64,", "");
            String photoData = postData.replaceAll(" ", "+");
            byte[] result = Base64.decodeBase64(photoData.getBytes());
            String path = "";
            if(pageId.equals("[object Object]")){
                pageId = "1";
            }
//            if(quipub.equals("1")){
//                path = SearchPathTools.getPublishFirstPageThumb(programId, pageId, false);
//                //拷贝页下预览pic
//                FileUtil.writeFileArray(path, result);
//                //拷贝封面pic
//                setEditCoverPicQ(programId,pageId);
//            }else{
                path = SearchPathTools.getEditFirstPageThumb(userId, programId, pageId,false);
                //拷贝页下预览pic
                FileUtil.writeFileArray(path, result);
                //拷贝封面pic
                setEditCoverPic(userId,programId,pageId);
            //}
        } catch (Exception e) {
            logger.error("缩略图失败",e);
          //  org.apache.log4j.Logger.getLogger(PageAction.class).error("缩略图失败",e);
        }

        sendMsg("true");
    }

    /**
     * 设置封面图片
     * @param userName
     * @param programId
     * @param pageId
     * @return
     */
    public boolean setEditCoverPic(String userName,String programId,String pageId){
        // 解析project.xml文件  拿到pageSequence->page的第一个页面ID  只有第一个page被编辑后  才需要修改封面
        String path = SearchPathTools.getEditProgram(userName, programId, true);
        Program xmlProgram = newsService.parseProgramXml(path);
        List<String> list = xmlProgram.getPageList();
        for(int i =0;i<1;i++){
            if(list.get(i).equals(pageId)){
                // 说明是首页进行了编辑
                String oldPath = SearchPathTools.getEditFirstPageThumb(userName, programId, pageId,false);
                String targetPath=SearchPathTools.getThumb(userName,programId,false);
                File oldFile=new File(oldPath);
                File targetFile=new File(targetPath);
                FileUtil.copyFile(oldFile, targetFile);
            }
        }
        return true;
    }

    /**
     * 设置封面图片(快速发布)
     * @param programId
     * @param pageId
     * @return
     */
    public boolean setEditCoverPicQ(String programId,String pageId){
        // 解析project.xml文件  拿到pageSequence->page的第一个页面ID  只有第一个page被编辑后  才需要修改封面
        String path = SearchPathTools.getPublishProgram( programId, true);
        Program xmlProgram = newsService.parseProgramXml(path);
        List<String> list = xmlProgram.getPageList();
        for(int i =0;i<1;i++){
            if(list.get(i).equals(pageId)){
                // 说明是首页进行了编辑
                String oldPath = SearchPathTools.getPublishFirstPageThumb( programId, pageId,false);
                String targetPath=SearchPathTools.getPubLishThumb(programId,false);
                File oldFile=new File(oldPath);
                File targetFile=new File(targetPath);
                FileUtil.copyFile(oldFile, targetFile);
            }
        }
        return true;
    }
    /**
     * todo 加注释
     */
    public void del(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        Page page = new Page();
        page.setPid(pid);
        page.setPrid(prid);
        pageService.removePage(userId, page);
        sendMsg("true");
    }
    /**
     * todo 加注释
     * @throws Exception
     */
    public void select() throws Exception{
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        Page page = new Page();
        page.setPrid(prid);
        page.setPid(pid);
        String json = itemService.queryItem(userId, page, "0");
        sendMsg(json);
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public void updateDuration() throws Exception{
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        Page page = new Page();
        page.setPrid(prid);
        page.setPid(pid);
        page.setTime(time);

//		String path=SearchPathTools.getEditPage(userName, programId, pid, isFile)

        boolean updateFlag=pageService.updatePage(userId, prid,pid,time);
        sendMsg(updateFlag+"");
    }

    /**
     * 预览
     * @return
     * @throws Exception
     */
    public String preSingle() throws Exception{
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        String programId = prid;
        String pageId = pid;
        newsService.previewProgram(userId, programId, pageId, "single");
        setUid(userId);
        setUuid(UUID.randomUUID().toString());
        setProName(URLEncoder.encode(programId, "UTF-8"));
        return "program";
    }

    /**
     * 预览消息
     * @return
     * @throws Exception
     */
    public String preprogram() throws Exception{
        ProjectPreviewForm pageForm = new ProjectPreviewForm();
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        if(pageForm == null || pageForm.getNavFlag() == null || pageForm.getNavFlag().equals("")){
            pageForm.setNavFlag("private"); // 说明是从 节目制作页面的预览进入的此方法
        }

        if(!"jsp".equals(pageForm.getSource())){
            pageForm.setPrid(prid);
        }
        String programId = prid;
        String curId = pid;
        News project=newsService.getNewsById(Integer.parseInt(programId));
        String uid = project.getUid() == null ?"":userId;
        if(/*!"public".equals(pageForm.getNavFlag())&&*/!"template".equals(pageForm.getNavFlag())){
            newsService.previewProgram(uid, programId, curId, pageForm.getNavFlag());
        }
        setUid(uid);
        setUuid(UUID.randomUUID().toString());
        setProName(URLEncoder.encode(programId, "UTF-8"));
        if("public".equals(pageForm.getNavFlag())){
            return "publishprogram";
        }
        if("template".equals(pageForm.getNavFlag())){
            return "templateprogram";
        }
        return "programpreview";
    }

    /**
     * 页面排序
     */
    public void sort(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        String userId = user.getUserId().toString();
        String programId = prid;
        String pageId = pid;
        String index = idx;

        com.honghe.recordweb.action.frontend.news.entity.Page page = new com.honghe.recordweb.action.frontend.news.entity.Page();
        page.setPrid(programId);
        page.setPid(pageId);
        page.setIndex(index);

        pageService.sortPage(userId,page);
        sendMsg("true");
    }

}
