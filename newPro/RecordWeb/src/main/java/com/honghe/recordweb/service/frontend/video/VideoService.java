package com.honghe.recordweb.service.frontend.video;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.VideoDao;
import com.honghe.recordhibernate.entity.Video;
import com.honghe.recordweb.service.frontend.resource.ResourceService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lichunming on 2015/3/25.
 */
@Service("videoService")
public class VideoService {

    private final static Logger logger = Logger.getLogger(VideoService.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Resource
    private VideoDao videoDao;
    @Resource
    private ResourceService resourceService;


    /**
     * 根据ID删除文件
     *
     * @param id 资源id
     * @return 布尔值  true，成功；false，失败
     */
    @Transactional
    public boolean deleteVideo(String id) {
        if (id == null || id.trim().equals(""))
            return false;
        try {
            Video video = videoDao.getVideoInfoByRid(Integer.parseInt(id));
            com.honghe.recordhibernate.entity.Resource res = video.getResource();
            if (res != null && res.getResPath() != null) {
                String fPath = res.getResPath();
                if (fPath.endsWith("/")) {
                    fPath.substring(0, fPath.length() - 1);
                }
                File file = new File(fPath + "/" + video.getVideoName());
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
                videoDao.delVideo(Integer.parseInt(id));
            }
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("根据ID删除文件异常，id=" + id, e);
            return false;
        }
    }

    /**
     * 根据ID删除文件
     *
     * @param rid 资源rid
     * @return 布尔值  true，成功；false，失败
     */
    @Transactional
    public boolean deleteVideoByRid(String rid) {
        if (rid == null || rid.trim().equals(""))
            return false;
        try {
            videoDao.delVideoByRid(Integer.parseInt(rid));
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("根据资源ID删除文件异常，rid=" + rid, e);
            return false;
        }
    }


    /**
     * 添加文件
     *
     * @param map 数据集合
     * @return 布尔值
     */
    @Transactional
    public boolean addVideo(Map<String, String> map) {
        if (map == null || map.isEmpty())
            return false;
        com.honghe.recordhibernate.entity.Video video = new com.honghe.recordhibernate.entity.Video();
        int iFlag = 0;
        if (map.containsKey("video_name")) {
            iFlag++;
            video.setVideoName(map.get("video_name"));
        }
        if (map.containsKey("video_downloads") && map.get("video_downloads") != null && !map.get("video_downloads").toString().equals("")) {
            iFlag++;
            video.setVideoDownloads(Integer.parseInt(map.get("video_downloads")));
        }
        if (map.containsKey("video_size")) {
            iFlag++;
            video.setVideoSize(map.get("video_size"));
        }
        if (map.containsKey("video_visits") && map.get("video_visits") != null && !map.get("video_visits").toString().equals("")) {
            iFlag++;
            video.setVideoVisits(Integer.parseInt(map.get("video_visits")));
        }
        if (map.containsKey("video_status") && map.get("video_status") != null && !map.get("video_status").toString().equals("")) {
            iFlag++;
            video.setVideoStatus(Integer.parseInt(map.get("video_status")));
        }
        if (map.containsKey("video_thumb")) {
            iFlag++;
            video.setVideoThumb(map.get("video_thumb"));
        }
        if (map.containsKey("video_duration") && map.get("video_duration") != null && !map.get("video_duration").toString().equals("")) {
            iFlag++;
            video.setVideoDuration(Integer.parseInt(map.get("video_duration")));
        }
        if (map.containsKey("video_code")) {
            iFlag++;
            video.setVideoCode(map.get("video_code"));
        }
        if (map.containsKey("video_resolution")) {
            iFlag++;
            video.setVideoResolution(map.get("video_resolution"));
        }
        if (map.containsKey("video_path")) {
            iFlag++;
            video.setVideoPath(map.get("video_path"));
        }
        if (map.containsKey("video_url")) {
            iFlag++;
            video.setVideoUrl(map.get("video_url"));
        }
        if (map.containsKey("video_upload")) {
            iFlag++;
            video.setVideoUpload(Integer.parseInt(map.get("video_upload")));
        }
        if (map.containsKey("res_id")) {
            iFlag++;
            video.setResource(resourceService.getResourceInfoById(map.get("res_id")));
        }
        if (map.containsKey("video_time") && map.get("video_time") != null && !map.get("video_time").trim().equals("")) {
            iFlag++;
            try {
                video.setVideoTime(sdf.parse(map.get("video_time")));
            } catch (ParseException e) {
                logger.error("", e);
//                e.printStackTrace();
            }
        } else {
            try {
                video.setVideoTime(sdf.parse(sdf.format(new Date())));
            } catch (ParseException e) {
//                e.printStackTrace();
                logger.error("", e);
            }
        }
        video.setVideoStatus(0);//初始化状态
        if (iFlag > 0) {
            try {
                videoDao.saveVideo(video);
                return true;
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("", e);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 分页获取资源数据
     *
     * @param pageSize    每页的数量
     * @param currentPage 当前页码
     * @param rid         分类ID
     * @param order       排序字段
     * @return
     */
    public Page getVideoByPage(String pageSize, String currentPage, int rid, String order) {
        Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        try {
            videoDao.getVideoListByPageSql(page, rid, order);
            //videoDao.getVideoByRid(1);
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("分页获取资源数据异常", e);
        }
        return null;
    }

    /**
     * todo 加注释
     * @param rid
     * @return
     */
    public List getVideoByRid(int rid) {
        try {
            if (rid > 0) {
                return videoDao.getVideoByRid(rid);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * todo 加注释
     * @param rId
     * @return
     */
    public Map<String, String> getVideoInfoById(String rId) {
        Map<String, String> map = new HashMap<>();
        if (rId != null && !rId.equals("")) {
            try {
                Video video = videoDao.getVideoInfoByRid(Integer.parseInt(rId));
                if (video != null && video.getVideoId() > 0) {
                    map.put("video_id", String.valueOf(video.getVideoId()));
                    map.put("video_name", video.getVideoName());
                    map.put("video_duration", String.valueOf(video.getVideoDuration()));
                }
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("", e);
            }
        }
        return map;
    }

    /**
     * 在文件上传时修改上传状态
     * @param videoId
     * @param videoUpload
     * @return
     */
    public boolean updateVideoUploadBySql(String videoId, String videoUpload) {
        if (videoId == null || videoId.trim().equals("")) {
            return false;
        }
        try {
            videoDao.updateVideoUpload(videoId, Integer.parseInt(videoUpload));
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }


    /**
     * 下载文件
     * @param vId
     * @param path
     * @param response
     * @return
     */
    public boolean downloadVideo(String vId, String path, HttpServletResponse response) {
        if (path != null && !path.trim().equals("")) {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                OutputStream out = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(path));
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (UnsupportedEncodingException uee) {
                logger.error("no Encoding for download video path", uee);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null)
                        bis.close();
                    if (bos != null)
                        bos.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                    logger.error("", e);
                }
            }
        }
        return true;
    }

    /**
     * 获取当前正在上传的文件数量
     * @return
     */
    public int countUploadNumber() {
        try {
            return videoDao.countUploadNumber();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return 0;
        }
    }

    @Transactional
    public boolean update(Map<String, String> map) {
        if (map == null || map.isEmpty())
            return false;
        //com.honghe.recordhibernate.entity.Video video = new com.honghe.recordhibernate.entity.Video();
        Video video = null;
        try {
            //video = videoDao.getVideoInfoByRid(Integer.parseInt(map.get("res_id")));
            video = videoDao.getVideo(Integer.parseInt(map.get("res_id")));
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        int iFlag = 0;
        if (map.containsKey("video_size")) {
            iFlag++;
            video.setVideoSize(map.get("video_size"));
        }
        if (iFlag > 0) {
            try {
                videoDao.updateVideo(video);
                return true;
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("", e);
                return false;
            }
        } else {
            return false;
        }
    }
}
