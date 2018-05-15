package com.honghe.recordweb.service.frontend.resource;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.ResourceDao;
import com.honghe.recordweb.service.frontend.video.VideoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chby on 2014/10/27.
 */
@Service("resourceService")
public class ResourceService {
    private final static Logger logger = Logger.getLogger(ResourceService.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Resource
    private ResourceDao resourceDao;
    @Resource
    private VideoService videoService;

    /**
     * 分页获取资源数据
     *
     * @param pageSize    每页的数量
     * @param currentPage 当前页码
     * @param str         搜索关键字
     * @param order       排序字段
     * @return
     */
    public Page getResourceByPage(String pageSize, String currentPage, String str, String order) {
        try {

            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            resourceDao.getResourceListByPageSql(page, str, order);
            if (page.getTotalPageSize() < Integer.parseInt(currentPage)) {
                int tmpCurrentPage = page.getTotalPageSize();
                int tmpPageSize = page.getPageSize();
                page = new Page<List<Map<String, String>>>(tmpCurrentPage, tmpPageSize);
                resourceDao.getResourceListByPageSql(page, str, order);
            }
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.debug("分页获取资源数据异常", e);
        }
        return null;
    }

    /**
     * 修改资源信息
     *
     * @param rId 资源id
     * @param map 数据集合，例{ {res_school:"XXXX学校"},{res_course:"语文"}}
     * @return 布尔值，true，修改成功；false ，失败或者异常
     */
    @Transactional
    public boolean updateResource(String rId, Map<String, String> map) {
        if (rId != null && !rId.equals("") && map != null && !map.isEmpty()) {
            try {
                com.honghe.recordhibernate.entity.Resource res = resourceDao.getResourceInfoById(Integer.parseInt(rId));
                int iFlag = 0; //判断是否有数据修改
                if (map.containsKey("res_subject")) {
                    iFlag++;
                    res.setResSubject(map.get("res_subject"));
                }
                if (map.containsKey("res_course")) {
                    iFlag++;
                    res.setResCourse(map.get("res_course"));
                }
                if (map.containsKey("res_title")) {
                    iFlag++;
                    res.setResTitle(map.get("res_title"));
                }
                if (map.containsKey("res_grade")) {
                    iFlag++;
                    res.setResGrade(map.get("res_grade"));
                }
                if (map.containsKey("res_speaker")) {
                    iFlag++;
                    res.setResSpeaker(map.get("res_speaker"));
                }
                if (map.containsKey("res_school")) {
                    iFlag++;
                    res.setResSchool(map.get("res_school"));
                }
                if (iFlag > 0) {
                    res.setResUpdatetime(sdf.parse(sdf.format(new Date())));
                    resourceDao.updateResourceInfo(res);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {

                //            e.printStackTrace();
                logger.debug("修改资源信息异常", e);
                return false;
            }

        } else {
            logger.debug("no data for update");
            return false;
        }
    }

    /**
     * 根据id删除某条资源
     *
     * @param rId 资源id
     * @return 布尔值  true，成功；false，失败
     */
    @Transactional
    public boolean deleteResource(String rId) {
        if (rId == null || rId.trim().equals(""))
            return false;
        try {
            com.honghe.recordhibernate.entity.Resource res = resourceDao.getResourceInfoById(Integer.parseInt(rId));
            String resPath = res.getResPath();
            videoService.deleteVideoByRid(rId);
            resourceDao.delResource(Integer.parseInt(rId));
            File file = new File(resPath);
            if (file.exists() && file.canWrite()) {
                this.recurDelete(file);
            }
            return true;
        } catch (NullPointerException n) {
            logger.error("Sorry,No such file for res delete", n);
          //  n.printStackTrace();
            return false;
        } catch (Exception e) {
            logger.error("deleteResource异常", e);
          //  e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加资源
     *
     * @param map 数据集合
     * @return 布尔值
     */
    @Transactional
    public boolean addResource(Map<String, String> map) {
        if (map == null || map.isEmpty())
            return false;
        com.honghe.recordhibernate.entity.Resource res = new com.honghe.recordhibernate.entity.Resource();
        int iFlag = 0;
        if (map.containsKey("res_name")) {
            iFlag++;
            res.setResName(map.get("res_name"));
        }
        if (map.containsKey("host_name")) {
            iFlag++;
            res.setHostName(map.get("host_name"));
        }
        if (map.containsKey("host_ip")) {
            iFlag++;
            res.setHostIp(map.get("host_ip"));
        }
        if (map.containsKey("res_title")) {
            iFlag++;
            res.setResTitle(map.get("res_title"));
        }
        if (map.containsKey("res_path")) {
            iFlag++;
            res.setResPath(map.get("res_path"));
        }
        if (map.containsKey("res_resolution")) {
            iFlag++;
            res.setResResolution(map.get("res_resolution"));
        }
        if (map.containsKey("res_class")) {
            iFlag++;
            res.setResClass(map.get("res_class"));
        }
        if (map.containsKey("res_grade")) {
            iFlag++;
            res.setResGrade(map.get("res_grade"));
        }
        if (map.containsKey("res_subject")) {
            iFlag++;
            res.setResSubject(map.get("res_subject"));
        }
        if (map.containsKey("res_course")) {
            iFlag++;
            res.setResCourse(map.get("res_course"));
        }
        if (map.containsKey("res_speaker")) {
            iFlag++;
            res.setResSpeaker(map.get("res_speaker"));
        }
        if (map.containsKey("res_school")) {
            iFlag++;
            res.setResSchool(map.get("res_school"));
        }
        if (map.containsKey("res_size")) {
            iFlag++;
            res.setResSize(map.get("res_size"));
        }
        if (map.containsKey("res_updatetime") && map.get("res_updatetime") != null && !map.get("res_updatetime").trim().equals("")) {
            iFlag++;
            try {
                res.setResUpdatetime(sdf.parse(map.get("res_updatetime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (map.containsKey("res_thumb")) {
            iFlag++;
            res.setResThumb(map.get("res_thumb"));
        }
        res.setResStatus(0);//初始化状态
        if (iFlag > 0) {
            try {
                resourceDao.addResource(res);
                return true;
            } catch (Exception e) {
                logger.error("", e);
//                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * todo 加注释
     * @param res
     * @return
     */
    @Transactional
    public boolean addResource2(com.honghe.recordhibernate.entity.Resource res) {
        try {
            resourceDao.addResource(res);
            return true;
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * todo 加注释
     * @param res
     * @return
     */
    @Transactional
    public String getIdByPath(com.honghe.recordhibernate.entity.Resource res) {
        String id ="-1";
        try {
            Object obj = resourceDao.getIdByPath(res);
            if(obj==null){
                id = "-1";
            }else {
                id = String.valueOf(obj);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return  id;
    }

    /**
     * todo 加注释
     * @param name
     * @param res_id
     * @return
     */
    @Transactional
    public String getDataexist(String name,String res_id ) {
        String id ="-1";
        try {
            Object obj = resourceDao.getDataexist(name, res_id);
            if(obj ==null){
                id = "-1";
            }else {
                id = String.valueOf(obj);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return id;
    }

    /**
     * 根据ID获取资源信息
     *
     * @param rId
     * @return
     */
    public com.honghe.recordhibernate.entity.Resource getResourceInfoById(String rId) {
        try {
            return resourceDao.getResourceInfoById(Integer.parseInt(rId));
        } catch (ParseException pe) {
            logger.error("no valid resourceId", pe);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * todo 加注释
     * @return
     */
    public String queryResPath() {
        try {
            Object object = resourceDao.queryResourcePath();
            if (object != null) {
                return object.toString();
            }
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件夹或者文件删除
     *
     * @param f
     */
    private static void recurDelete(File f) {
        try {
            for (File fi : f.listFiles()) {
                if (fi.isDirectory()) {
                    recurDelete(fi);
                } else {
                    fi.delete();
                }
            }
            f.delete();
        } catch (NullPointerException n) {
            logger.error("Sorry,No such file", n);
        }
    }

    /**
     * 根据设备ip获取资源信息
     * 分页获取资源数据
     *
     * @param pageSize    每页的数量
     * @param currentPage 当前页码
     * @param str         搜索关键字
     * @param order       排序字段
     * @return
     */
    public Page getResourceByPageWithIp(String pageSize, String currentPage, String str, String order, String ip, String beginDate, String endDate) {
        try {

            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            resourceDao.getResourceListByPageWithIpSql(page, str, order, ip, beginDate, endDate);
            if (page.getTotalPageSize() < Integer.parseInt(currentPage)) {
                int tmpCurrentPage = page.getTotalPageSize();
                int tmpPageSize = page.getPageSize();
                page = new Page<List<Map<String, String>>>(tmpCurrentPage, tmpPageSize);
                resourceDao.getResourceListByPageWithIpSql(page, str, order, ip, beginDate, endDate);
            }
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * 删除某个nas下，时间超过自动删除时间的资源
     * @param nasIp
     * @param dateTime
     * @return
     */
    @Transactional
    public void delResourceNasDel(String nasIp,String dateTime)
    {
        try
        {
            List<Object[]> resList = resourceDao.getResourceNasDelTime(nasIp,dateTime);
            if(resList == null || resList.size() == 0)
            {
                return;
            }
            for(Object[] objects : resList)
            {
                deleteResource(String.valueOf(objects[0]));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("delResourceNasDel自动清理过期nas视频资源异常:nas=" + nasIp + ",dateTime=" + dateTime ,e);
        }
    }
}
