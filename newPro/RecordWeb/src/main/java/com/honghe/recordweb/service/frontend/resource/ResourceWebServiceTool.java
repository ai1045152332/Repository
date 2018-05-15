package com.honghe.recordweb.service.frontend.resource;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.service.frontend.settings.FtpSettingService;
import jodd.http.HttpRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 资源模块调用精品的数据采用webservice方式获取，该类为辅助调用对应接口</p>
 * <p>接口关键字：serviceType</p>
 * <p>1,resource列表；2,获取课程信息;3，课程信息修改；4，resource删除；5，video列表；</p>
 * <p>6，video上传；7，video下载；8，video删除；9，video预览</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author hthwx
 * @date 2015/9/8
 */
@Service("resourceWebServiceTool")
public class ResourceWebServiceTool {
    private final static Logger logger = Logger.getLogger(ResourceWebServiceTool.class);

    /**
     * 根据ip获取对应精品主机的资源数据，resource列表
     *
     * @param params
     * @return
     */
    public Page getResourceByPageWithIp(Map<String, String> params) {
        try {
            String currentPage = params.get("currentPage");
            String pageSize = params.get("pageSize");
            String hostIp = params.get("hostIp"); //设备IP
            String resId = params.get("resId");//资源id
            String str = params.get("str");
            String order = params.get("order");
            String beginDate = params.get("beginDate");
            String endDate = params.get("endDate");
            if (str != null && !str.equals("")) {
                str = URLEncoder.encode(str, "UTF-8");
            }
            if (beginDate != null && beginDate.length() > 10) {
                beginDate = beginDate.substring(0, 10);
            }
            if (endDate != null && endDate.length() > 10) {
                endDate = endDate.substring(0, 10);
            }
            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            String url = "http://" + hostIp + "/resourceService?serviceType=1&currentPage=" + currentPage + "&pageSize=" + pageSize +
                    "&hostIp=" + hostIp + "&resId=" + resId + "&str=" + str + "&order=" + order + "&beginDate=" + beginDate + "&endDate=" + endDate;
            String content = postHttp(url);
            if (content != null && !content.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(content);
                if (jsonObject.get("result").equals("exp") || jsonObject.get("result").equals("")) {
                    logger.info("调用精品" + hostIp + "资源列表异常!");
                } else {
                    // page = (Page)JSONObject.toBean(jsonObject,Page.class);
                    String resultStr = jsonObject.getString("result");
                    JSONArray jsonArray = JSONArray.fromObject(resultStr);
                    String totalSize = jsonObject.get("totalPageSize").toString();
                    List t = new ArrayList();
                    for (Object o : jsonArray) {
                        JSONObject jo = JSONObject.fromObject(o);
                        Map map = new HashMap();
                        map.put("res_grade", jo.get("res_grade"));
                        map.put("host_ip", jo.get("host_ip"));
                        map.put("res_path", jo.get("res_path"));
                        map.put("res_subject", jo.get("res_subject"));
                        map.put("res_speaker", jo.get("res_speaker"));
                        map.put("res_size", jo.get("res_size"));
                        map.put("res_name", jo.get("res_name"));
                        map.put("res_title", jo.get("res_title"));
                        map.put("res_course", jo.get("res_course"));
                        map.put("res_id", jo.get("res_id"));
                        map.put("res_thumb", jo.get("res_thumb"));
                        map.put("host_name", jo.get("host_name"));
                        map.put("res_updatetime", jo.get("res_updatetime"));
                        map.put("res_school", jo.get("res_school"));
                        t.add(map);
                    }
                    page.setResult(t);
                    page.setTotalPageSize(Integer.parseInt(totalSize) * Integer.parseInt(pageSize));
                }
            }
            return page;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * 根据资源id获取精品资源的课程信息
     *
     * @param hostIp 精品ip
     * @param resId  资源id
     * @return Map
     */
    public Map<String, String> getCourseInfo(String hostIp, String resId) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=2" + "&hostIp=" + hostIp + "&resId=" + resId;
            String content = postHttp(url);
            if (content != null && !content.equals("") && content.indexOf("res_id") > -1) {
                JSONObject jsonObject = JSONObject.fromObject(content);
                resultMap.put("res_id", resId);
                resultMap.put("res_title", jsonObject.get("res_title").toString());
                resultMap.put("res_grade", jsonObject.get("res_grade").toString());
                resultMap.put("res_course", jsonObject.get("res_course").toString());
                resultMap.put("res_subject", jsonObject.get("res_subject").toString());
                resultMap.put("res_speaker", jsonObject.get("res_speaker").toString());
                resultMap.put("res_school", jsonObject.get("res_school").toString());
            }
        } catch (Exception e) {
            logger.error("获取精品课程信息异常！", e);
//            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 修改精品的课程信息
     *
     * @param param 参数Map
     * @return boolean
     */
    public boolean updateCourseInfo(Map<String, String> param) {
        try {
            String resId = param.get("res_id");
            String hostIp = param.get("host_ip");
            String resTitle = param.get("res_title");
            String resGrade = param.get("res_grade");
            String resCourse = param.get("res_course");
            String resSubject = param.get("res_subject");
            String resTeacher = param.get("res_speaker");
            String resSchool = param.get("res_school");
            if (resTitle != null && !resTitle.equals("")) {
                resTitle = URLEncoder.encode(resTitle, "UTF-8");
            }
            if (resGrade != null && !resGrade.equals("")) {
                resGrade = URLEncoder.encode(resGrade, "UTF-8");
            }
            if (resCourse != null && !resCourse.equals("")) {
                resCourse = URLEncoder.encode(resCourse, "UTF-8");
            }
            if (resSubject != null && !resSubject.equals("")) {
                resSubject = URLEncoder.encode(resSubject, "UTF-8");
            }
            if (resTeacher != null && !resTeacher.equals("")) {
                resTeacher = URLEncoder.encode(resTeacher, "UTF-8");
            }
            if (resSchool != null && !resSchool.equals("")) {
                resSchool = URLEncoder.encode(resSchool, "UTF-8");
            }
            String url = "http://" + hostIp + "/resourceService?serviceType=3" + "&hostIp=" + hostIp + "&resId=" + resId +
                    "&resTitle=" + resTitle + "&resGrade=" + resGrade + "&resCourse=" + resCourse + "&resSubject=" + resSubject +
                    "&resTeacher=" + resTeacher + "&resSchool=" + resSchool;
            String content = postHttp(url);
            if (content != null && content.indexOf("success") > -1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("修改课程信息异常！", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据resId删除精品资源
     *
     * @param hostIp 精品主机ip
     * @param resId  资源id
     * @return boolean
     */
    public boolean delResource(String hostIp, String resId) {
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=4" + "&hostIp=" + hostIp + "&resId=" + resId;
            String content = postHttp(url);
            if (content != null && content.indexOf("success") > -1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 获取精品视频列表
     *
     * @param params 参数map
     * @return Page
     */
    public Page getVideoByPage(Map<String, String> params) {
        try {
            String currentPage = params.get("currentPage");
            String pageSize = params.get("pageSize");
            String hostIp = params.get("hostIp"); //设备IP
            String resId = params.get("resId");//资源id
            String order = params.get("order");
            if (order != null && !order.equals("")) {
                order = URLEncoder.encode(order, "UTF-8");
            }
            Page<List<Map<String, String>>> page = new Page<List<Map<String, String>>>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            String url = "http://" + hostIp + "/resourceService?serviceType=5&currentPage=" + currentPage + "&pageSize=" + pageSize +
                    "&hostIp=" + hostIp + "&resId=" + resId + "&order=" + order;
            String content = postHttp(url);
            if (content != null && !content.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(content);
                if (jsonObject.get("result").equals("exp") || jsonObject.get("result").equals("")) {
                    logger.info("调用精品" + hostIp + "视频列表异常!");
                } else {
                    String resultStr = jsonObject.getString("result");
                    JSONArray jsonArray = JSONArray.fromObject(resultStr);
                    String totalSize = jsonObject.get("totalPageSize").toString();
                    List t = new ArrayList();
                    for (Object o : jsonArray) {
                        JSONObject jo = JSONObject.fromObject(o);
                        Map map = new HashMap();
                        map.put("video_id", jo.get("video_id"));
                        map.put("video_name", jo.get("video_name"));
                        map.put("video_time", jo.get("video_time"));
                        map.put("video_downloads", jo.get("video_downloads"));
                        map.put("video_visits", jo.get("video_visits"));
                        map.put("video_size", jo.get("video_size"));
                        map.put("video_status", jo.get("video_status"));
                        map.put("video_upload", jo.get("video_upload"));
                        map.put("video_url", jo.get("video_url"));
                        map.put("video_path", jo.get("video_path"));
                        map.put("video_thumb", jo.get("video_thumb"));
                        t.add(map);
                    }
                    page.setResult(t);
                    page.setTotalPageSize(Integer.parseInt(totalSize) * Integer.parseInt(pageSize));
                }
            }
            return page;
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
        }
        return null;
    }

    /**
     * 精品视频上传
     *
     * @param hostIp  录播主机ip
     * @param resId   资源id
     * @param videoId 视频id
     * @return JSONObject
     */
    public JSONObject videoUpload(String hostIp, String resId, String videoId) {
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=6" + "&hostIp=" + hostIp + "&resId=" + resId + "&videoId=" + videoId;
            String content = postHttp(url);
            JSONObject tmpjo = JSONObject.fromObject(content);
            if (tmpjo == null) {
                jsonObject.put("success", false);
                jsonObject.put("msg", "无效数据产生异常！");
            } else if (tmpjo.get("result").equals("ok")) {
                jsonObject.put("success", true);
                jsonObject.put("msg", tmpjo.get("msg"));
            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", tmpjo.get("msg"));
            }
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "无法有效连接录播主机！");
        }
        return jsonObject;
    }

    /**
     * 下载
     *
     * @param hostIp  录播主机ip
     * @param resId   资源id
     * @param videoId 视频id
     * @return JSONObject
     */
    public JSONObject videoDownload(String hostIp, String resId, String videoId) {
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=7" + "&hostIp=" + hostIp + "&resId=" + resId + "&videoId=" + videoId;
            String content = postHttp(url);
            JSONObject tmpjo = JSONObject.fromObject(content);
            if (tmpjo == null) {
                jsonObject.put("success", false);
                jsonObject.put("msg", "无效数据产生异常！");
            } else if (tmpjo.get("result").equals("success")) {
                jsonObject.put("success", true);
                jsonObject.put("msg", tmpjo.get("msg")); //如果成功，返回下载地址
                jsonObject.put("name", tmpjo.get("name"));
            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", tmpjo.get("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("success", false);
            jsonObject.put("msg", "无法有效连接录播主机！");
        }
        return jsonObject;
    }

    /**
     * 精品资源删除
     *
     * @param hostIp  录播主机ip
     * @param resId   资源id
     * @param videoId 视频id
     * @return JSONObject
     */
    public JSONObject videoDelete(String hostIp, String resId, String videoId) {
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=8" + "&hostIp=" + hostIp + "&resId=" + resId + "&videoId=" + videoId;
            String content = postHttp(url);
            JSONObject tmpjo = JSONObject.fromObject(content);
            if (tmpjo == null) {
                jsonObject.put("success", false);
                jsonObject.put("msg", "无效数据产生异常！");
            } else if (tmpjo.get("result").equals("success")) {
                jsonObject.put("success", true);
                jsonObject.put("msg", tmpjo.get("msg")); //如果成功，返回下载地址
            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", tmpjo.get("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("success", false);
            jsonObject.put("msg", "无法有效连接录播主机！");
        }
        return jsonObject;
    }

    /**
     * todo 加注释
     * @param hostIp
     * @param resId
     * @param videoId
     * @return
     */
    public String videoPlay(String hostIp, String resId, String videoId) {
        String playAddress = "";
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=9" + "&hostIp=" + hostIp + "&resId=" + resId + "&videoId=" + videoId;
            String content = postHttp(url);
            JSONObject tmpjo = JSONObject.fromObject(content);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return playAddress;
    }

    /**
     * 根据resId获取资源信息
     *
     * @param hostIp 设备ip
     * @param resId  资源id
     * @return Json
     */
    public JSONObject getResourceById(String hostIp, String resId) {
        JSONObject jsonObject = new JSONObject();
        try {
            String url = "http://" + hostIp + "/resourceService?serviceType=10" + "&hostIp=" + hostIp + "&resId=" + resId;
            String content = postHttp(url);
            jsonObject = JSONObject.fromObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", "exp");
        }
        return jsonObject;
    }

    /**
     * todo 加注释
     * @param url
     * @return
     */
    public String postHttp(String url) {
        try {
            String content = HttpRequest.post(url).send().bodyText();
            content = StringEscapeUtils.unescapeJava(content).trim();
            return content;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return "exp";
        }
    }

    /**
     * 远程下载文件
     *
     * @param path
     * @param response
     * @return
     */
    public boolean downloadVideo(String path, HttpServletResponse response) {
        if (path != null && !path.trim().equals("")) {
            URL urlfile = null;
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                OutputStream out = response.getOutputStream();
                //bis = new BufferedInputStream(new FileInputStream(path));
                urlfile = new URL(path);
                httpUrl = (HttpURLConnection) urlfile.openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                bos.flush();
                bis.close();
                httpUrl.disconnect();

            } catch (UnsupportedEncodingException uee) {
                logger.error("no Encoding for download video path", uee);
            } catch (Exception e) {
                logger.error("", e);
//                e.printStackTrace();
            } finally {
                try {
                    if (bis != null)
                        bis.close();
                    if (bos != null)
                        bos.close();
                } catch (IOException e) {
                    logger.error("", e);
//                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 根据录播平台的FTP参数设置精品的FTP及ResourceIp
     *
     * @param hostIp 设备ip
     * @return Json
     */
    public JSONObject resetFtp(String hostIp) {
        JSONObject jsonObject = new JSONObject();
        try {
            FtpSettingService ftpSettingService =(FtpSettingService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(FtpSettingService.class);
            Map<String, String> params = ftpSettingService.getFtp();
            String ftpAddr = params.get("ftp_addr");
            String ftpPort = params.get("ftp_port");
            String ftpUser = params.get("ftp_user");
            String ftpPass = params.get("ftp_pass");
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String resourceIp = ConfigUtil.get("ResourceIp");
            if (ftpUser != null && !ftpUser.equals("")) {
                ftpUser = URLEncoder.encode(ftpUser, "UTF-8");
            }
            if (ftpPass != null && !ftpPass.equals("")) {
                ftpPass = URLEncoder.encode(ftpPass, "UTF-8");
            }
            String url = "http://" + hostIp + "/resourceService?serviceType=11" + "&hostIp=" + hostIp + "&ftpAddr=" + ftpAddr + "&ftpPort=" + ftpPort + "&ftpUser=" + ftpUser + "&ftpPass=" + ftpPass + "&resourceIp=" + resourceIp;
            String content = postHttp(url);
            jsonObject = JSONObject.fromObject(content);
        } catch (Exception e) {
            // e.printStackTrace();
            jsonObject.put("result", "exp");
        }
        return jsonObject;
    }
}
