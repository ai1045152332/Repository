package com.honghe.recordweb.service.frontend.ftp;

import com.honghe.recordweb.service.frontend.video.VideoService;
import it.sauronsoftware.ftp4j.FTPClient;
import jodd.http.HttpRequest;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hthwx on 2015/3/30.
 */
public class FtpVideoUploadService {
    private final static Logger logger = Logger.getLogger(FtpVideoUploadService.class);
    private String videoId;
    private FTPClient ftpClient;
    private String ftpAddr;
    private int ftpPort;
    private String ftpUser;
    private String ftpPass;
    @Resource
    private Ftp4jService ftp4jService;
    @Resource
    private VideoService videoService;
    private final String VIDEO_UPLOAD_FINISH = "9";
    private final String VIDEO_UPLOAD_START = "1";
    private String newFileName;
    private String oldFileName;
    private String postUrl;
    private String videoPath;//文件路径
    private String videoNewPath;//文件新路径
    private static final String POST_URL = "/apps/server_api.php?cmd=import_record&key=hellokey&file=";
    private Map<String, String> otherInfoMap;

    public FtpVideoUploadService(String videoId, String ftpAddr, int ftpPort, String ftpUser, String ftpPass, String postUrl, Map<String, String> otherInfoMap) {
        this.videoId = videoId;
        this.ftpAddr = ftpAddr;
        this.ftpPort = ftpPort;
        this.ftpUser = ftpUser;
        this.ftpPass = ftpPass;
        this.postUrl = postUrl + POST_URL;
        this.otherInfoMap = otherInfoMap;
        this.ftp4jService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(Ftp4jService.class);
        ftpClient = ftp4jService.run(this.ftpAddr, this.ftpPort, this.ftpUser, this.ftpPass);
        this.videoService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(VideoService.class);
    }

    /**
     * 视频文件上传
     *
     * @param videoPath    原地址
     * @param videoName    文件名称
     * @param videoNewPath FTP目录，default "/"
     * @param newName      重命名
     * @return bool true 上传成功；false 上传失败
     */
    public boolean ftpUploadVideo(String videoPath, String videoName, String videoNewPath, String newName) {
        boolean resultFlag = false;
        if (ftpClient != null) {
            this.videoPath = videoPath;
            this.videoNewPath = videoNewPath;
            this.newFileName = newName;
            this.oldFileName = videoName;
            resultFlag = ftp4jService.uploadFile(ftpClient, videoPath + "/" + videoName, videoNewPath, new videoTransferListener());
        }
        return resultFlag;
    }

    /**
     * post数据到校园管理平台
     *
     * @param fileName 文件名称
     * @param duration 时长
     * @param title    标题
     * @param teacher  主讲人
     * @param school   主讲人学校或者班级
     * @return String
     */
    private String postToSchoolPlatform(String fileName, String duration, String title, String teacher, String school) {
        String content;
        /*String url = postUrl + fileName + "&duration=" + duration + "&title="+title + "&teacher_name=" +teacher+
                "&school=" + school;*/
        try {
            String url = postUrl + URLEncoder.encode(fileName, "UTF-8") + "&duration=" + duration + "&title=" + URLEncoder.encode(title, "UTF-8") + "&teacher_name=" + URLEncoder.encode(teacher, "UTF-8") +
                    "&school=" + URLEncoder.encode(school, "UTF-8");
            logger.debug("文件上传地址：" + url);
            content = HttpRequest.post(url).send().bodyText();
            content = StringEscapeUtils.unescapeJava(content).trim();
            logger.debug("文件上传完成，提交数据到接口" + content);
        } catch (Exception e) {
            logger.error("文件上传异常：" + e.toString());
            content = "{'state':'fail'}";
        }

        return content;
        //  return new OperationRequest[]{new OperationRequestSetLive(content)};
    }

    /**
     * ftp上传监听类
     */
    private class videoTransferListener extends MyTransferListener {
        @Override
        public void started() {
            super.started();
            videoService.updateVideoUploadBySql(videoId, VIDEO_UPLOAD_START);//开始上传修改文件状态
        }

        @Override
        public void failed() {
            super.failed();
            videoService.updateVideoUploadBySql(videoId, "0");//失败则恢复状态
            closeFTPClient();
        }

        @Override
        public void aborted() {
            super.aborted();
        } //传输放弃时

        @Override
        public void completed() {
            super.completed();
            videoService.updateVideoUploadBySql(videoId, VIDEO_UPLOAD_FINISH); //完成之后修改状态
//            if(!oldFileName.toLowerCase().trim().equals(newFileName.toLowerCase().trim())) {
//                ftp4jService.rename(ftpClient, oldFileName, newFileName);
//            }
            if (otherInfoMap != null) {
                if (oldFileName.toUpperCase().startsWith("MOVIE")) {
                    /**
                     * 上传MOVIE文件时，同时上传打点信息
                     * 简易录播的打点信息在KeyNote.xml;精品在与MOVIE同名的.hcl文件中
                     */
                    String keyNote = videoPath + "\\KeyNote.xml";
                    //   System.out.println("begin to search keynote file~~~~" + keyNote + "~~~oldFileName=" + oldFileName);
                    File keyNoteFile = new File(keyNote);
                    if (keyNoteFile.exists()) {
                        //    System.out.println("this is KeyNote.xml!!!" + keyNote);
                        //简易录播
                        if (!oldFileName.toLowerCase().trim().equals(newFileName.toLowerCase().trim())) {
                            ftp4jService.rename(ftpClient, oldFileName, newFileName);
                        }
                        ftp4jService.uploadFile(ftpClient, keyNote, videoNewPath);
                    } else {
                        if (oldFileName.indexOf(".MP4") > 0) {
                            keyNote = videoPath + "\\" + oldFileName.substring(0, oldFileName.length() - 4) + ".hcl";
                            keyNoteFile = new File(keyNote);
                            newFileName = oldFileName;
                            //   System.out.println("jingpin file~~~~" + keyNote);
                            if (keyNoteFile.exists()) {
                                //精品录播
                                ftp4jService.uploadFile(ftpClient, keyNote, videoNewPath);
                            }
                        } else {
                            //简易录播(不设置打点信息的话，简易不生产打点文件)
                            if (!oldFileName.toLowerCase().trim().equals(newFileName.toLowerCase().trim())) {
                                ftp4jService.rename(ftpClient, oldFileName, newFileName);
                            }
                        }
                    }
                } else {
                    if (!oldFileName.toLowerCase().trim().equals(newFileName.toLowerCase().trim())) {
                        ftp4jService.rename(ftpClient, oldFileName, newFileName);
                    }
                }
                String duration = otherInfoMap.containsKey("duration") && otherInfoMap.get("duration") != null ? otherInfoMap.get("duration") : "";
                String title = otherInfoMap.containsKey("title") && otherInfoMap.get("title") != null ? otherInfoMap.get("title") : "";
                String teacher = otherInfoMap.containsKey("teacher") && otherInfoMap.get("teacher") != null ? otherInfoMap.get("teacher") : "";
                String school = otherInfoMap.containsKey("school") && otherInfoMap.get("school") != null ? otherInfoMap.get("school") : "";
                String postResult = postToSchoolPlatform(newFileName, duration, title, teacher, school);
                // System.out.println(postResult + "~~~~~~~~~post result");
            }
            closeFTPClient();
        }

        @Override
        public void transferred(int length) {
            super.transferred(length);//传输中
            logger.debug("发送" + length + "字节");
        }
    }

    private void closeFTPClient() {
        ftp4jService.disconnect(ftpClient, true);
    }

    public String getVideoNewPath() {
        return videoNewPath;
    }

    public void setVideoNewPath(String videoNewPath) {
        this.videoNewPath = videoNewPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public Map<String, String> getOtherInfoMap() {
        return otherInfoMap;
    }

    public void setOtherInfoMap(Map<String, String> otherInfoMap) {
        this.otherInfoMap = otherInfoMap;
    }

    /**
     * todo 加注释
     * @param videoId
     * @param ftpAddr
     * @param ftpPort
     * @param ftpUser
     * @param ftpPass
     * @param postUrl
     * @param otherInfoMap
     * @param name
     */
    public FtpVideoUploadService(String videoId, String ftpAddr, int ftpPort, String ftpUser, String ftpPass, String postUrl, Map<String, String> otherInfoMap, String name) {
        this.videoId = videoId;
        this.ftpAddr = ftpAddr;
        this.ftpPort = ftpPort;
        this.ftpUser = ftpUser;
        this.ftpPass = ftpPass;
        this.postUrl = postUrl + POST_URL;
        this.otherInfoMap = otherInfoMap;
        this.ftp4jService = new Ftp4jService();
        ftpClient = ftp4jService.run(this.ftpAddr, this.ftpPort, this.ftpUser, this.ftpPass);
        this.videoService = new VideoService();
    }


    public static void main(String[] args) {
        String ftp_addr = "192.168.18.105";
        String ftp_port = "21";
        String ftp_user = "hhtftp";
        String ftp_pass = "hht.123456";
        final String oldVideoName = "move.mp4";
        String videoId = "208";
        final String path = "";
        Map<String, String> videoUploadInfoMap = new HashMap<>();

        videoUploadInfoMap.put("title", "");
        videoUploadInfoMap.put("teacher", "");
        videoUploadInfoMap.put("school", "");
        videoUploadInfoMap.put("duration", "");

        String postUrl = "http://" + System.getProperty("ResourceIp", ftp_addr);
        try {
            final FtpVideoUploadService ftpVideoUploadService = new FtpVideoUploadService(videoId, ftp_addr, Integer.parseInt(ftp_port), ftp_user, ftp_pass, postUrl, videoUploadInfoMap);
            if (ftpVideoUploadService.getFtpClient() != null) {
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                ftpVideoUploadService.ftpUploadVideo(path, oldVideoName, "/", oldVideoName);
//                    }
//                });
//                threadExecutor.execute(thread);
                System.out.println("开始上传!");
            } else {
                System.out.println("上传失败，FTP连接失败或超时！");
            }
        } catch (Exception e) {
            logger.error("", e);
            System.out.println("FTP配置错误！" + e.toString());
        }

    }
}
