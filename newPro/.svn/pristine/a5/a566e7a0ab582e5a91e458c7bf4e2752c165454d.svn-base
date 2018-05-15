package com.honghe.recordweb.service.frontend.resource;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chby on 2014/10/28.
 */
//@WebServlet("/resource")
@WebServlet(name = "resource", urlPatterns = "/resourceService")
public class ResourceWebService extends HttpServlet {
    private final static Logger logger = Logger.getLogger(ResourceWebService.class);
    private final String JSON_FLAG = "@@@@@@";
   /* public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     //   request.setCharacterEncoding("UTF-8");
        String hostIp = request.getParameter("ip");//设备ip
        String videoName=request.getParameter("name");//文件名
        videoName =new String(videoName.getBytes("ISO8859-1"), "UTF-8");
        //String a2= new String(videoName.getBytes("ISO8859-1"), "GBK");
        String videoTime =request.getParameter("time");//录制时间(转换为int型）
        String videoResolution =request.getParameter("resolution");//分辨率1080/720
        String videoPath = request.getParameter("path");//录制视频的FTP地址
        String videoRemarks =request.getParameter("remarks");//备注
        String hostName =request.getParameter("classroom");//备注
        Integer vedioClass = Integer.parseInt(request.getParameter("section"));
        boolean result= insertVideo(hostIp,videoTime,videoName,videoResolution,videoPath,videoRemarks,hostName,vedioClass);
        System.out.println(hostIp+"__________________");
        //response响应请求，输出请求的一些内容
        response.setContentType( "text/html;charset=gbk" ) ;  //设置响应页面字符编码
        PrintWriter out = response.getWriter() ;
        out.println( "<font color=red ><h1>调用视频文件数据写入接口</h1></font>" ) ;
        out.println("<div>"+"现在用response输出请求相关信息如下：");
        out.println("<li>"+"ip="+hostIp+";name="+videoName+";time="+videoTime+";resolution="+videoResolution+";path="+videoPath+";remarks="+videoRemarks+";classroom="+hostName+";</li>");
        if(result==true)
        {
            out.println("<li> 数据写入成功 </li>");
        }
        else
        {
            out.println("<li> 数据写入失败 </li>");
        }
        out.println("</div>");
        out.close() ;
    }*/

    /**
     * todo 加注释
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> resMap = new HashMap<String, String>();
        //   request.setCharacterEncoding("UTF-8");
        String resName = request.getParameter("res_name");//文件夹名称
        String hostName = request.getParameter("host_name");//班级名称
        hostName = new String(hostName.getBytes("ISO8859-1"), "UTF-8");
        String hostIp = request.getParameter("host_ip");//主机ip
        String resTitle = request.getParameter("res_title");//标题
        String resPath = request.getParameter("res_path");//文件夹路径
        String resResolution = request.getParameter("res_resolution");//视频分辨率
        String resClass = request.getParameter("res_class");//第几节课
        String resGrade = request.getParameter("res_grade");//年级
        String resSubject = request.getParameter("res_subject");//科目
        String resCourse = request.getParameter("res_course");//主题课程
        String resSpeaker = request.getParameter("res_speaker");//主讲人
        String resSchool = request.getParameter("res_school");//学校
        String resSize = request.getParameter("res_size");//文件大小
        String resUpdateTime = request.getParameter("res_updatetime");//修改时间
        String resThumb = request.getParameter("res_thumb");//缩略图路径
        resMap.put("res_name", resName);
        resMap.put("host_name", hostName);
        resMap.put("host_ip", hostIp);
        resMap.put("res_title", resTitle);
        resMap.put("res_path", resPath);
        resMap.put("res_resolution", resResolution);
        resMap.put("res_class", resClass);
        resMap.put("res_grade", resGrade);
        resMap.put("res_subject", resSubject);
        resMap.put("res_course", resCourse);
        resMap.put("res_speaker", resSpeaker);
        resMap.put("res_school", resSchool);
        resMap.put("res_updatetime", resUpdateTime);
        resMap.put("res_size", resSize);
        resMap.put("res_thumb", resThumb);
        if (this.insertResource(resMap)) {
            try {
                String videoJson = request.getParameter("json_data");//视频文件的json字符串
                if (videoJson != null && videoJson.indexOf(JSON_FLAG) > 0) {
                    String[] vjArr = videoJson.split(JSON_FLAG);
                    if (vjArr != null && vjArr.length > 0) {
                        for (String vj : vjArr) {
                            if (vj != null && !vj.equals("")) {
                                jsonManage(vj);
                            }
                        }
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("", e);
            }
        } else {

        }
    }

    /**
     * todo 加注释
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }



    /**
     * 解析json数据
     * @param jsonStr
     * @return
     */
    private boolean jsonManage(String jsonStr) {
         /*JSONArray json = JSONArray.fromObject(personstr);
            JSONObject jsonobject = JSONObject.fromObject(str);
            PassportLendsEntity passportlends = null;
            //获取一个json数组
            JSONArray array = jsonobject.getJSONArray("passports");*/


        try {
            org.codehaus.jettison.json.JSONObject jsb = new org.codehaus.jettison.json.JSONObject(jsonStr);
            String videoName = jsb.has("video_name") ? jsb.getString("video_name") : "";
            String videoTime = jsb.has("video_time") ? jsb.getString("video_time") : "";
            String videoSize = jsb.has("video_size") ? jsb.getString("video_size") : "";
            String videoThumb = jsb.has("video_thumb") ? jsb.getString("video_thumb") : "";
            Map<String, String> videoMap = new HashMap<>();
            videoMap.put("video_name", videoName);
            videoMap.put("video_time", videoTime);
            videoMap.put("video_size", videoSize);
            videoMap.put("video_thumb", videoThumb);
        } catch (JSONException e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return false;
    }


    /**
     * 添加视频文件记录
     * @return
     */
    private boolean insertVideo() {
        return true;
    }

    /**
     * 添加资源记录
     * @param map
     * @return
     */
    private boolean insertResource(Map<String, String> map) {
        ResourceService resourceService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResourceService.class);
        return resourceService.addResource(map);
        // return true;
    }

    public void test() {
        logger.info("test");
    }

    /**
     * todo 加注释
     * @param aa
     * @return
     */
    public String getString(String aa) {
        aa = "%E4%B8%AD%E6%96%87%E6%B5%8B%E8%AF%95";
        String st = "";
        /*if( _uUseForInner + nLen > uDCellLength )
        {
            return "";
        }
        byte[] charr = new byte[nLen];
        for(int nCurr = 0; nCurr < nLen ; nCurr++)
            charr[nCurr] = (byte) uDCellData[_uUseForInner++];

        String strReturn = null;
        try
        {
            strReturn = new String(charr,"UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }*/
        try {
            //  st = new String(aa.getBytes(), "gbk");
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return st;
    }

}
