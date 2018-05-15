package com.honghe.recordweb.service.frontend.resource;

import com.honghe.recordhibernate.entity.Curriculum;
import com.honghe.recordhibernate.entity.Setting;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.mediaprobe.MediaProbeInfo;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.video.VideoService;
import com.honghe.recordweb.util.HttpServiceClientFactory;
import com.honghe.service.client.Result;
import com.honghe.service.client.http.HttpServiceClient;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hthwx on 2015/4/2.
 */
public class NasScanThread  {
    private final static Logger logger = Logger
            .getLogger(NasScanThread.class);
    private static final String MOVIE_FILE_TYPE = "MOVIE.MP4";
    private String nasScanPath;
    private String duration;//时长
    private String bitrate;//码率
    private String code;//编码方式
    private String resolution;//分辨率
    private String samplerate;//采样率
    private String hostName;
    private String hostIp;
    private Integer hostid;
    private int desId;//设备具体型号
    private static final String COURSE_FILE_NAME = "CourseInfo.xml";
    private static final String COURSE_INFO_FILE_NAME = "\\CourseInfo.xml";
    private ResourceService resourceService;
    private VideoService videoService;
    private SettingsService settingsService;
    private HostgroupService hostgroupService;
    private List<Map<String, String>> nasConfigs;
    private List<Map<String, String>> mediaVideoList;
    private List<Map<String, String>> mediaAudioList;
    private String FFprobePath;
    private Date updateTime;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    /**
     * todo 加注释
     * @param settingsService
     * @param resourceService
     * @param videoService
     * @param nasScanPath
     * @param hostIp
     * @param hostName
     * @param desId
     */
    public NasScanThread(SettingsService settingsService, ResourceService resourceService, VideoService videoService, String nasScanPath, String hostIp, Integer hostid, String hostName, int desId) {
        this.settingsService = settingsService;
        this.resourceService = resourceService;
        this.videoService = videoService;
        this.nasScanPath = nasScanPath;
        this.hostIp = hostIp;
        this.hostName = hostName;
        this.desId = desId;
        this.hostid = hostid;
        try {
            updateTime = DATE_FORMAT.parse(DATE_FORMAT.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.FFprobePath = this.getClass().getClassLoader().getResource("").getPath() + "../../ffprobe";
        if (this.FFprobePath.substring(0, 1).equals("/")) {
            this.FFprobePath = this.FFprobePath.substring(1);
        }
        logger.debug("************进入NasScanThread***********11**********this ffprobepath =" + this.FFprobePath);
    }


    /**
     * todo 加注释
     */
    public void run() {
        {
                //接收事件时，Tbox每次生成一个文件就会发一次事件，并会将信息写入配置文件，保存过程需要时间，所以增加延时
                try {
                    Thread.currentThread().sleep(10000);
                } catch (Exception e) {
                    logger.error("线程延时异常", e);
                }

                if (null != nasScanPath && !("").equals(nasScanPath)) {
                    logger.info("NasScanThread is running~~~~~~~~~~~~12~~~~~~~~" + nasScanPath);
                    File videoFolder = new File(nasScanPath);
                    // nasScanPath = "\\192.168.16.175\res\223\20160905-132623";
                    nasScanPath = nasScanPath.endsWith("/") ? nasScanPath.substring(0, nasScanPath.length() - 1) : nasScanPath;

                    String infoFileFullName = nasScanPath + COURSE_INFO_FILE_NAME;
                    File videoCourseInfo = new File(infoFileFullName);
                    try {
                        logger.debug("NasScanThread is running~~~~~~~~~~~~13~~~~~~~~" + videoFolder.getName() + "---" + videoCourseInfo.getName());
                        logger.debug(videoFolder.exists() + "---" + videoFolder.isDirectory() + "---" + videoCourseInfo.exists());
                    } catch (Exception e) {
                        logger.error("异常信息", e);
//                e.printStackTrace();
//                System.out.println("异常信息---------");
                    }
                    if (videoFolder != null && videoFolder.exists() && videoFolder.isDirectory() && videoCourseInfo.exists()) {
                        logger.debug("videoFolder is not null~~~~~~~~22~~~~~~~~~");
                        String videoFolderSize = calculateFileSize(getDirSize(videoFolder));
                        File[] files = videoFolder.listFiles();
                        String folderName = videoFolder.getName();
                        if (files != null) {
                            //读取XML文件获取信息
                            try {
                                XMLReaderService xmlReaderService = new XMLReaderService(infoFileFullName);
                                xmlReaderService.readXml();
                                logger.debug("读取xml信息完成~~~~~~~~44~~~~~~~~~");
                                Map<String, String> courseInfoMap = xmlReaderService.getMap();
                                List<Map<String, String>> videoListMap = xmlReaderService.getList();
                                String movieName = xmlReaderService.getMovieName();
                                if (movieName != null && !movieName.equals("")) {
                                    String tmpFilePath = nasScanPath + "/" + movieName;//获取电影模式文件的路径
                                    //扫描电影模式文件的视频信息
                                    logger.debug("扫描视频文件media信息开始~~~~~~~~55~~~~~~~~~~~~");
                                    MediaProbeInfo mediaProbeInfo = new MediaProbeInfo(FFprobePath, tmpFilePath);
                                    if (mediaProbeInfo != null) {
                                        this.duration = mediaProbeInfo.getMainDuration() > 0 ? "" : String.valueOf(mediaProbeInfo.getMainDuration());
                                        this.bitrate = mediaProbeInfo.getMainBitrate() > 0 ? "" : String.valueOf(mediaProbeInfo.getMainBitrate());
                                        this.code = mediaProbeInfo.getMainCode();
                                        this.resolution = mediaProbeInfo.getMainResolution();
                                        this.samplerate = mediaProbeInfo.getMainSamplerate() > 0 ? "" : String.valueOf(mediaProbeInfo.getMainSamplerate());
                                        logger.debug("扫描视频文件media信息结束~~~~~~~~55~~~~~~~~~~~~");
                                    } else {
                                        this.duration = "";
                                        this.bitrate = "";
                                        this.code = "";
                                        this.resolution = "";
                                        this.samplerate = "";
                                    }
                                }
                                com.honghe.recordhibernate.entity.Resource res = new com.honghe.recordhibernate.entity.Resource();
                                if (courseInfoMap != null) {
                                    res.setResName(folderName);

                                    int indexTime = nasScanPath.indexOf("-");
                                    String min = nasScanPath.substring(indexTime+1, indexTime+3);
                                    String sec = nasScanPath.substring(indexTime+3, indexTime+5);
                                    String startRecordTime = min + ":" + sec;

                                    int index = nasScanPath.indexOf("\\res");
                                    String nasIp = nasScanPath.substring(2, index);
                                    nasConfigs = settingsService.getNasConfig();
                                    String disk = "";
                                    for (int i = 0; i < nasConfigs.size(); i++) {
                                        Map<String, String> maps = nasConfigs.get(i);
                                        if (maps.containsKey(nasIp)) {
                                            disk = maps.get(nasIp);
                                            nasScanPath = nasScanPath.replace(nasIp, disk+":");
                                        }
                                    }

                                    // 根据hostid去关联查询课表信息
                                    Setting setting = settingsService.getCurriculumType();
                                    List<Object[]> curriculumList = null;
                                    if(setting != null) {
                                        curriculumList = settingsService.getCurriculum(hostid, startRecordTime, setting.getCurriculumType());
                                    }
                                    Object[] curriculums = null;
                                    if (curriculumList != null) {
                                        curriculums = curriculumList.get(0);
                                    }

                                    index = nasScanPath.indexOf("\\res");
                                    StringBuffer sb = new StringBuffer();
                                    String start = nasScanPath.substring(0, index);
                                    sb.append(start);
                                    String end = nasScanPath.substring(index+4);
                                    sb.append(end);
                                    res.setResPath(sb.substring(2, sb.length()));
                                    res.setResSize(videoFolderSize);
                                    res.setResStatus(0);
                                    res.setResUpdatetime(updateTime);
                                    res.setHostIp(hostIp);
                                    res.setHostName(hostName);
                                    res.setResResolution(this.resolution);
                                    if (courseInfoMap.containsKey("res_title")) {
                                        res.setResTitle(courseInfoMap.get("res_title"));
                                    }
                                    if (courseInfoMap.containsKey("res_grade")) {
                                        String grade = courseInfoMap.get("res_grade");
                                        if ("".equals(grade) && curriculumList != null && curriculums != null) {
                                            if (curriculums[5] != null) {
                                                grade = curriculums[5].toString();
                                            }
                                        }
                                        res.setResGrade(grade);
                                    }
                                    if (courseInfoMap.containsKey("res_subject")) {
                                        String subject = courseInfoMap.get("res_subject");
                                        if ("".equals(subject) && curriculumList != null && curriculums != null) {
                                            if (curriculums[7] != null) {
                                                subject = curriculums[7].toString();
                                            }
                                        }
                                        res.setResSubject(subject);
                                    }
                                    if (courseInfoMap.containsKey("res_course")) {
                                        res.setResCourse(courseInfoMap.get("res_course"));
                                    }
                                    if (courseInfoMap.containsKey("res_speaker")) {
                                        String teacher = courseInfoMap.get("res_speaker");
                                        if ("".equals(teacher) && curriculumList!= null && curriculums != null) {
                                            if (curriculums[6] != null) {
                                                teacher = curriculums[6].toString();
                                            }
                                        }
                                        res.setResSpeaker(teacher);
                                    }
                                    if (courseInfoMap.containsKey("res_school")) {
                                        res.setResSchool(courseInfoMap.get("res_school"));
                                    }
                                    if (courseInfoMap.containsKey("res_thumb")) {
                                        res.setResThumb(courseInfoMap.get("res_thumb"));
                                    }
                                }
                                boolean bool = true;
                                int resid = Integer.valueOf(resourceService.getIdByPath(res));
                                if (resid == -1) {
                                    bool = resourceService.addResource2(res);
                                    resid = res.getResId();
                                }
                                // videoService.getVideoByRid(15);
                                if (bool) {
                                    logger.debug("添加resource信息完成~~~~~~~~~~~~~~~~~66~~~~~~~~~");
                                    String resId = String.valueOf(resid);
                                    if (this.desId != 11) {

                                        for (Map<String, String> vm : videoListMap) {
                                            vm.put("res_id", resId);
                                            // System.out.println(vm.get("video_name") + "----res_id=" + resId);
                                            String tmpVideoName = vm.get("video_name");
                                            try {
                                                /*int pos = nasScanPath.indexOf("\\res");
                                                nasScanPath = nasScanPath.substring(0, pos) + nasScanPath.substring(pos + "\\res".length());
                                                System.out.println(nasScanPath);*/
                                                // File vf = new File(nasScanPath + "/" + tmpVideoName);
                                                if (movieName != null && movieName.equals(vm.get("video_name"))) {
                                                    vm.put("video_code", this.code);
                                                    vm.put("video_duration", this.duration);
                                                    vm.put("video_resolution", this.resolution);
                                                } else {
                                                    MediaProbeInfo mediaProbeInfoTmp = new MediaProbeInfo(FFprobePath, nasScanPath + "/" + tmpVideoName);
                                                    vm.put("video_code", mediaProbeInfoTmp.getMainCode() == null ? "" : mediaProbeInfoTmp.getMainCode());
                                                    vm.put("video_duration", String.valueOf(mediaProbeInfoTmp.getMainDuration()));
                                                    vm.put("video_resolution", mediaProbeInfoTmp.getMainResolution() == null ? "" : mediaProbeInfoTmp.getMainResolution());
                                                }
                                                //vm.put("video_size", calculateFileSize(getDirSize(vf)));
                                                vm.put("video_size", videoFolderSize);
                                                // vm.put("video_thumb",vm.get("video_path"));
                                            } catch (Exception e) {
                                                logger.error("", e);
                                                //System.out.println("get video size error for NasScanThread");
                                            }
                                            vm.put("video_time", DATE_FORMAT.format(updateTime));
                                            System.out.println("添加视频信息开始~~~~~~77~~~~~~~~~~~~~~");
                                            if ("-1".equals(resourceService.getDataexist(tmpVideoName, resourceService.getIdByPath(res)))) {
                                                videoService.addVideo(vm);
                                            } else {
                                                videoService.update(vm);
                                            }
                                            System.out.println("添加视频信息结束~~~~~~77~~~~~~~~~~~~~~" + vm.get("video_time"));
                                            logger.debug("添加视频信息结束~~~~~~77~~~~~~~~~~~~~~" + vm.get("video_time"));
                                        }


                                    } else {
                                        logger.debug("添加Tbox录制资源");
                                        Map<String, String> vm = new HashMap<>();
                                        for (int i = 0; i < files.length; i++) {
                                            try {
                                                //    File file =  new File(files[i].getPath());
                                                int num = files[i].getPath().lastIndexOf("\\");
                                                int length = files[i].getPath().length();
                                                String tmpVideoName = files[i].getPath().substring(num + 1, length);
                                                if (tmpVideoName.toUpperCase().indexOf(".MP4") == -1) {
                                                    continue;
                                                }
                                                vm.put("res_id", resId);
                                                File file = new File(nasScanPath + "/" + tmpVideoName);
                                                vm.put("video_name", tmpVideoName);
                                                if (MOVIE_FILE_TYPE.equals(tmpVideoName.toUpperCase())) {
                                                    vm.put("video_code", this.code);
                                                    vm.put("video_duration", this.duration);
                                                    vm.put("video_resolution", this.resolution);
                                                } else {
                                                    MediaProbeInfo mediaProbeInfoTmp = new MediaProbeInfo(FFprobePath, nasScanPath + "/" + tmpVideoName);
                                                    vm.put("video_code", mediaProbeInfoTmp.getMainCode() == null ? "" : mediaProbeInfoTmp.getMainCode());
                                                    vm.put("video_duration", String.valueOf(mediaProbeInfoTmp.getMainDuration()));
                                                    vm.put("video_resolution", mediaProbeInfoTmp.getMainResolution() == null ? "" : mediaProbeInfoTmp.getMainResolution());
                                                }
                                                vm.put("video_size", calculateFileSize(getDirSize(file)));
                                                vm.put("video_time", DATE_FORMAT.format(updateTime));
                                                //System.out.println("添加视频信息开始~~~~~~77~~~~~~~~~~~~~~");
                                                if ("-1".equals(resourceService.getDataexist(tmpVideoName, resourceService.getIdByPath(res)))) {
                                                    videoService.addVideo(vm);
                                                    logger.debug("添加Tbox录制资源" + tmpVideoName + "成功");
                                                }

                                            } catch (Exception e) {
                                                logger.error("", e);
                                                //System.out.println("get video size error for NasScanThread");
                                            }

                                        }
                                        logger.debug("添加Tbox录制资源结束");
                                    }

                                } else {
                                    logger.debug("添加resource信息失败~~~~~~~~~~~~~~~~~66~~~~~~~~~");
                                }

                            }
                            catch (Exception e)
                            {
                                logger.error("扫描文件异常", e);
                            }
                        }

                    } else {
                        logger.debug("this event couldn't get files --nasScanPath=" + nasScanPath + "*****infoFileFullName=" + infoFileFullName);
                        //System.out.println("this event couldn't get files --nasScanPath=" + nasScanPath + "*****infoFileFullName=" + infoFileFullName);
                    }
                } else {
                    logger.debug("this event is invalid--nasScanPath=" + nasScanPath);
                    //System.out.println("this event is invalid--nasScanPath=" + nasScanPath);
                }
        }
    }

    /**
     * todo 加注释
     * @param fileSize
     * @return
     */
    private String calculateFileSize(long fileSize) {
        String result = "";
        DecimalFormat df = new DecimalFormat("######0");
        double size = 0;
        if (fileSize / 1024 < 0.1) {
            result = fileSize + "B";
        } else if (fileSize / (1024 * 1024) < 0.1) {
            size = (double) fileSize / 1024;
            result = df.format(size) + "K";
        } else if (fileSize / (1024 * 1024 * 1024) < 0.1) {
            size = (double) fileSize / (1024 * 1024);
            result = df.format(size) + "M";
        } else {
            size = (double) fileSize / (1024 * 1024 * 1024);
            result = df.format(size) + "G";
        }
        return result;
    }

    /**
     * todo 加注释
     * @param file
     * @return
     */
    public static long getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    if (f.getName().toLowerCase().indexOf("_vod.") > 0) {
                        continue;
                    } else {
                        size += getDirSize(f);
                    }
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位
                long size = file.length();
                return size;
            }
        } else {
            logger.debug("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
    }
}
