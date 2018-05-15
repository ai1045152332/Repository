package com.honghe.recordweb.action.frontend.video;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.ftp.FtpVideoUploadService;
import com.honghe.recordweb.service.frontend.resource.ResourceService;
import com.honghe.recordweb.service.frontend.resource.ResourceWebServiceTool;
import com.honghe.recordweb.service.frontend.settings.FtpSettingService;
import com.honghe.recordweb.service.frontend.video.VideoService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lichunming on 2015/3/25.
 */
@Controller
@Scope(value = "prototype")
public class VideoAction extends BaseAction {
    @Resource
    private VideoService videoService;
    @Resource
    private FtpSettingService ftpSettingService;
    @Resource
    private ResourceService resourceService;
    @Resource
    private ResourceWebServiceTool resourceWebServiceTool;
    private final static Logger logger = Logger.getLogger(VideoAction.class);
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private final String PAGE_SIZE = "6";
    private String videoId;
    private String userId;
    private String userName;
    private Page pageVideo;
    private String currentPage = "1";
    private String orderCondition;
    private String rId;
    private List reslist;
    private String mediaUrl;
    private String videoUrl;
    private String path;
    private com.honghe.recordhibernate.entity.Resource resources;
    private ExecutorService threadExecutor =  Executors. newSingleThreadExecutor();
    private String ftpName;
    private String ftpPort;
    private String uName;
    private String uPwd;
    private String hostIp;//是否根据ip获取的资源页面跳转
    private String recordType;//当前选中的录播主机是否为精品，精品为1

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getFtpName() {
        return ftpName;
    }

    public void setFtpName(String ftpName) {
        this.ftpName = ftpName;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }
    private Integer nums;

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String resPath;

    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public String getUPwd() {
        return uPwd;
    }

    public void setUPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public VideoService getVideoService() {
        return videoService;
    }

    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Page getPageVideo() {
        return pageVideo;
    }

    public void setPageVideo(Page pageVideo) {
        this.pageVideo = pageVideo;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getOrderCondition() {
        return orderCondition;
    }

    public void setOrderCondition(String orderCondition) {
        this.orderCondition = orderCondition;
    }

    public String getRId() {
        return rId;
    }

    public void setRId(String rId) {
        this.rId = rId;
    }

    public List getReslist() {
        return reslist;
    }

    public void setReslist(List reslist) {
        this.reslist = reslist;
    }

    public com.honghe.recordhibernate.entity.Resource getResources() {
        return resources;
    }

    public void setResources(com.honghe.recordhibernate.entity.Resource resources) {
        this.resources = resources;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取分类文件
     * @return
     */
    public String videoList() {
        JSONObject resInfo = new JSONObject();
        if(rId != null && !rId.trim().equals("")) {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            userId = user.getUserId().toString();
            userName = user.getUserName();
            if(recordType != null && recordType.equals("1")){
                //如果是精品录播主机，则调用webService，获取精品的数据
                Map<String,String> params = new HashMap<>();
                params.put("currentPage",currentPage);
                params.put("pageSize",PAGE_SIZE);
                params.put("hostIp",hostIp);
                params.put("resId",rId);
                params.put("order",orderCondition == null ? "" : orderCondition);
                pageVideo = resourceWebServiceTool.getVideoByPage(params);
                resInfo = resourceWebServiceTool.getResourceById(hostIp,rId);
                path = resInfo.getString("resPath");
            }
            else {
                resources = resourceService.getResourceInfoById(rId);
                if(resources != null) {
                    path = resources.getResPath();
                    if (path != null && path.endsWith("/")) {
                        path.substring(0, path.length() - 1);
                    }
                    resInfo.put("resId",String.valueOf(resources.getResId()));
                    resInfo.put("resName",resources.getResName());
                    resInfo.put("resCourse",resources.getResCourse());
                    resInfo.put("resSubject",resources.getResSubject());
                    resInfo.put("resSpeaker",resources.getResSpeaker());
                    resInfo.put("hostName",resources.getHostName());
                }
                pageVideo = videoService.getVideoByPage(PAGE_SIZE, currentPage, Integer.parseInt(rId), orderCondition);
            }
        }
        else
        {
            resources=null;
            pageVideo =null;
        }
        ActionContext.getContext().put("resInfo",resInfo);
        if(hostIp == null || hostIp.equals("")) {
            return "videoList";
        }
        else
        {
            return "videolistip";
        }
    }

    /**
     * 删除文件
     */
    public void deleteVideo()
    {
        if(videoId != null && !videoId.trim().equals(""))
        {
            if(recordType != null && recordType.equals("1")) {
                //如果是精品录播主机，则调用webService，获取精品的数据
                JSONObject jsonObject = resourceWebServiceTool.videoDelete(hostIp,rId,videoId);
                writeJSON(jsonObject.toString());
            }
            else {
                if (videoService.deleteVideo(videoId)) {
                    generateJSON(true, "删除成功！");
                } else {
                    generateJSON(false, "删除失败！");
                }
            }
        }
        else
        {
            generateJSON(false,"没有数据");
        }
    }

    /**
     * FTP设置
     * @return
     */
    public String ftp()
    {
        Map<String,String> mapftp = ftpSettingService.getFtp();
        if(mapftp != null && mapftp.containsKey("ftp_addr"))
        {
            this.ftpName = mapftp.get("ftp_addr");
            this.ftpPort = mapftp.get("ftp_port");
            this.uName = mapftp.get("ftp_user");
            this.uPwd = mapftp.get("ftp_pass");
        }
        return "videoftp";
    }

    /**
     * 直播
     * @return
     */
    public String livePlay()
    {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId().toString();
        resPath = resourceService.queryResPath();
        return "videoplay";
    }
    /**
     * 预览某个镜头
     *
     * @return
     */
    public String cameraInfo() {

        if(videoId != null && !videoId.trim().equals(""))
        {
            try {
                mediaUrl= URLDecoder.decode(videoUrl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("",e);
                //e.printStackTrace();
            }
        }
        return "cameraInfo";
    }

    /**
     * 上传文件到校园电视台
     */
    public void uploadVideo()
    {
        if(videoId != null && rId!= null && !videoId.trim().equals(""))
        {
            if(recordType != null && recordType.equals("1")) {
                //如果是精品录播主机，则调用webService，获取精品的数据
                JSONObject jsonObject = resourceWebServiceTool.videoUpload(hostIp,rId,videoId);
                writeJSON(jsonObject.toString());
            }
            else {
                resources = resourceService.getResourceInfoById(rId);
                Map<String, String> videoUploadInfoMap = new HashMap<>();
                Map<String, String> mapVideo = videoService.getVideoInfoById(videoId);
                if (resources != null && mapVideo != null && mapVideo.containsKey("video_name")) {
                    String tmppath = resources.getResPath();
                    if (tmppath.endsWith("/")) {
                        tmppath.substring(0, tmppath.length() - 1);
                    }
                    final String path = tmppath;
                    File f = new File(path + "/" + mapVideo.get("video_name"));
                    if (!f.exists()) {
                        generateJSON(false, "未找到文件!");
                        return;
                    }
                    String resourceName = resources.getResName();
                    videoUploadInfoMap.put("title", resources.getResTitle());
                    videoUploadInfoMap.put("teacher", resources.getResSpeaker());
                    videoUploadInfoMap.put("school", resources.getResSchool());
                    videoUploadInfoMap.put("duration", mapVideo.containsKey("video_duration") ? mapVideo.get("video_duration") : "");
                    Map<String, String> map = ftpSettingService.getFtp();
                    if (map != null) {
                        String ftp_addr = map.containsKey("ftp_addr") ? map.get("ftp_addr") : "";
                        String ftp_port = map.containsKey("ftp_port") ? map.get("ftp_port") : "";
                        String ftp_user = map.containsKey("ftp_user") ? map.get("ftp_user") : "";
                        String ftp_pass = map.containsKey("ftp_pass") ? map.get("ftp_pass") : "";
                        final String oldVideoName = mapVideo.get("video_name");
                        final String newVideoName = resourceName + "-" + mapVideo.get("video_name");
                        //  String postUrl = map.containsKey("ftp_remark") ? map.get("ftp_remark") : "";
                        //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
                        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
                        if (!resourceBundle.containsKey("RtmpServerIp")
                                || resourceBundle.getString("ResourceIp") == null
                                || resourceBundle.getString("ResourceIp").equals("")) {
                            generateJSON(false, "config.properties配置错误");
                            return;
                        }
                        String postUrl = "http://" +resourceBundle.getString("ResourceIp");
                        try {
                            final FtpVideoUploadService ftpVideoUploadService = new FtpVideoUploadService(videoId, ftp_addr, Integer.parseInt(ftp_port), ftp_user, ftp_pass, postUrl, videoUploadInfoMap);
                            if (ftpVideoUploadService.getFtpClient() != null) {
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ftpVideoUploadService.ftpUploadVideo(path, oldVideoName, "/", newVideoName);
                                    }
                                });
                                threadExecutor.execute(thread);
                                generateJSON(true, "开始上传!");
                            } else {
                                generateJSON(false, "上传失败，FTP连接失败或超时！");
                            }
                        } catch (Exception e) {
                            logger.error("",e);
                            generateJSON(false, "FTP配置错误！");
                        }
                    } else {
                        generateJSON(false, "未设置FTP地址，无法上传！");
                    }

                } else {
                    generateJSON(false, "未找到数据！");
                }
            }
        }
        else
        {
            generateJSON(false,"未找到数据！");
        }
    }
    /**
     * 下载文件到本地
     */
    public void downloadVideo()
    {
        if(recordType != null && recordType.equals("1")) {
            //如果是精品录播主机，则调用webService，获取精品的数据
            /*JSONObject jsonObject = resourceWebServiceTool.videoDownload(hostIp,rId,videoId);
            if(!Boolean.parseBoolean(jsonObject.get("success").toString())){
                writeJSON(jsonObject.toString());
            }*/
            try {
                JSONObject jsonObject = resourceWebServiceTool.videoDownload(hostIp, rId, videoId);
                if (Boolean.parseBoolean(jsonObject.getString("success"))) {
                    HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
                    // 设置输出的格式
                    httpServletResponse.reset();
                    //   response.setContentType("bin");
                    //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                    httpServletResponse.setContentType("multipart/form-data;");
                    //httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
                    String fileName = jsonObject.getString("name");
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                    httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
                    resourceWebServiceTool.downloadVideo(jsonObject.getString("msg"), httpServletResponse);
                }
            }
            catch (Exception e){
               logger.debug("远程文件下载失败！");
               // e.printStackTrace();
                logger.error("",e);
            }
        }
        else {
            try {
                resources = resourceService.getResourceInfoById(rId);
                Map<String, String> mapVideo = videoService.getVideoInfoById(videoId);
                String resPath = resources.getResPath();
                if (resPath.endsWith("/")) {
                    resPath.substring(0, resPath.length() - 1);
                }
                String videoPath = resPath + "/" + mapVideo.get("video_name");
                String fileName = mapVideo.get("video_name");
                HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
                // 设置输出的格式
                httpServletResponse.reset();
                //   response.setContentType("bin");
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                httpServletResponse.setContentType("multipart/form-data;");
                //httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
                // httpServletResponse.setCharacterEncoding("gbk");
                videoService.downloadVideo(videoId, videoPath, httpServletResponse);
            } catch (Exception e) {
                logger.error("file download failed!",e);
            }
        }
    }

    /**
     * todo 加注释
     */
    public void isDownload()
    {
        if(rId != null && videoId != null && !rId.equals("") && !videoId.equals("")) {
            if(recordType != null && recordType.equals("1")) {
                //如果是精品录播主机，直接调用下载接口
                // generateJSON(true,"ok");
                try {
                    JSONObject jsonObject = resourceWebServiceTool.videoDownload(hostIp, rId, videoId);
                    if (!Boolean.parseBoolean(jsonObject.getString("success"))) {
                        writeJSON(jsonObject.toString());
                    }
                }
                catch (Exception e){
                    logger.error("远程文件下载失败！",e);
                   // e.printStackTrace();
                    generateJSON(false,"远程获取文件失败，无法下载！");
                }
            }
            else {
                resources = resourceService.getResourceInfoById(rId);
                Map<String, String> mapVideo = videoService.getVideoInfoById(videoId);
                if (resources != null && resources.getResPath() != null && !resources.getResPath().equals("") && mapVideo != null && mapVideo.containsKey("video_name")) {
                    String resPath = resources.getResPath();
                    if (resPath.endsWith("/")) {
                        resPath.substring(0, resPath.length() - 1);
                    }
                    String videoPath = resPath + "/" + mapVideo.get("video_name");
                    File f = new File(videoPath);
                    if (!f.exists()) {
                        generateJSON(false, "未找到文件!");
                    } else {
                        generateJSON(true, "ok");
                    }
                } else {
                    generateJSON(false, "未找到数据！");
                }
            }
        }
        else
        {
            generateJSON(false,"未找到数据！");
        }
    }

    /**
     * todo 加注释
     */
    public void uploadNumber()
    {
        nums = videoService.countUploadNumber();
        generateJSON(true,nums.toString());
    }

    /**
     * 给页面返回json数据
     * @param isSuccess
     * @param msg
     */
    private void generateJSON(boolean isSuccess,String msg)
    {
        JSONObject json = new JSONObject();
        json.put("success", isSuccess);
        json.put("msg", msg);
        writeJSON(json.toString());
    }

}
