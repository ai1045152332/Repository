package com.honghe.recordweb.action;


import com.alibaba.fastjson.JSON;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.HttpServiceUtil;
import com.honghe.recordweb.util.ThreadUtil;
import com.honghe.recordweb.util.base.util.JsonHelper;
import com.honghe.service.client.Result;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Moon on 2014/9/15.
 */
public class BaseAction {
    private final static Logger logger = Logger.getLogger(BaseAction.class);
    private final static String STATUS_OFFLINE = "Offline";
    /**
     * todo 加注释
     * @param jsonstr
     */
    public void writeJSON(String jsonstr) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonstr);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            logger.error("", e);
            //e.printStackTrace();
        }
    }

    /**
     * 判断是否从app 来
     *
     * @return
     */
    public final Boolean app_source() {
        String hhtc = ServletActionContext.getRequest().getParameter(CommonName.DEVICE_TYPE_SCREEN);
        if (hhtc != null && hhtc.equals("ctrldevice")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * web页面用于回写json格式字串
     *
     * @param object 需要回写的对象
     */
    public void writeJson(Object object) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            logger.error("", e);
            //e.printStackTrace();
        }
    }

    /**
     * add by dingshangjun
     * 通过此页面向编辑界面传某些特定字符串，主要用于节目边界相关操作
     *
     * @param content 要回写的字符串
     */
    public void sendMsg(String content) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST,GET");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            //以下代码从JSON.java中拷过来的
            response.setContentType("text/html");
            response.setContentType("text/html;charset=utf-8");

            PrintWriter out = response.getWriter();
            out.print(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("", e);
            //e.printStackTrace();
        }
    }


    /**
     * 获取设备树
     *
     * @param type 设备类型
     * @param uid  用户ID
     * @return
     */
    protected Map<String, Object> getTreeMap(final int uid, final String type, final HostgroupService hostgroupService) {
        Collection<Callable<Map<String, Object>>> thread = new ArrayList();
        thread.add(new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("groupTree", hostgroupService.getGroupService(uid, type));
                return result;
            }
        });
        thread.add(new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("unknowGroup", hostgroupService.getUnknowGroup(type));
                return result;
            }
        });
        return ThreadUtil.invokeAll(2, thread);
    }
    protected String getTreeMap(Integer userId, String deviceType) {
        StringBuilder sb = new StringBuilder(); //声明字符串变量对象
        Object obj = new Object();//用于类型转化
        try {
            Map<String, String> params = new HashMap<>();
            params.put("userId", userId.toString());
            params.put("type", "d");
            params.put("dtypeName", deviceType);
                Result result = HttpServiceUtil.service(HttpServiceUtil.SERVICE_AD, "user2DeviceGroup", params);
            if (result != null&&result.getCode()==0) {
                JSONObject resultValue = (JSONObject) result.getValue();//将value转化成JSON
                if (result.getValue() != null) {
                    obj = JsonHelper.toJavaBean(new Directory(), resultValue);//将Json转化成JavaBean 因为value与directory类型不符
                }
                Map mapStatus = new HashMap();
                Map<String,String> statusParams = new HashMap<>();
                if (obj instanceof Directory) {
                    Directory directory = (Directory)obj;
                    if(directory.getDirectories().size()!=0){
                        StringBuilder sbStatus = new StringBuilder();
                        String hostIpStr = getIpStrByRecursion(directory,sbStatus);
                        if(!"".equals(hostIpStr)&&hostIpStr.endsWith(",")){
                            hostIpStr = hostIpStr.substring(0,hostIpStr.lastIndexOf(","));
                        }
                        statusParams.put("ip",hostIpStr);

                        Result statusResult = HttpServiceUtil.service(HttpServiceUtil.SERVICE_DEVICE,"getDeviceStatus",statusParams);
                        if (statusResult.getCode()==0&&statusResult.getValue()!=null){
                            List<Map<String,String>> list = (List<Map<String, String>>) statusResult.getValue();
                            if (list!=null&&list.size()>0){
                                for (Map<String,String> statusMap:list){
                                    mapStatus.put(statusMap.get("ip"),statusMap.get("deviceStatus"));
                                }

                            }
                        }
                        sb.append("<ul id='left_nav_ul'>");
                        recursive((Directory)obj,sb,0,mapStatus);
                        sb.append("</ul>");
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error("设备树根节点获取失败：" + e);
            sb.delete(0,sb.length()-1);
        }
        return sb.toString();
    }
    private void recursive(Directory directory,StringBuilder sb, int level,Map mapStatus){
        level++;

        //添加地点
        sb.append("<li>");
//        去掉树形多选框
//        sb.append("<a href='javascript:void(0)' id='" + directory.getId() + "' typeId='"+ directory.getTypeId() +"'><i count='"+ level +"'></i><div class='kb-check'><span class='m-check'></span></div><h6 title='"+directory.getName()+"'>" + directory.getName() + "</h6></a>");
        sb.append("<a href='javascript:void(0)' id='" + directory.getId() + "' typeId='"+ directory.getTypeId() +"'><i count='"+ level +"'></i><h6 title='"+directory.getName()+"'>" + directory.getName() + "</h6></a>");
        List directories = directory.getDirectories();
        sb.append("<ul>");
        for(Object directory1:directories){
            Directory obj = (Directory) JsonHelper.toJavaBean(new Directory(), (JSONObject) directory1);//Json转化成Javabean
            recursive(obj,sb,level,mapStatus);
        }
        List<Map<String,String>> datas = directory.getData();
        for(Map<String,String> data:datas){
            sb.append("<li class='tree_contenta'>");
            sb.append(packageDevice(data,level,mapStatus));
//            sb.append("<div class='hostoverflow' id='hostoverflow"+data.get("hostId")+"'></div>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        sb.append("</li>");

    }
    /**
     *封装设备
     * @param data
     * @param level
     * @return
     */
    private String packageDevice(Map<String,String> data,int level,Map<String,String> mapStatus){
        StringBuilder sb = new StringBuilder();
        Map<String, String> params = new HashMap<>();
        String sysType = "";
        String hostCode = data.get("hostIp");
        try {
            sysType = data.get("hostSysType");
        } catch (Exception e) {}
        String deviceType = data.get("deviceType").toLowerCase();
        String dspecId = data.get("dspecId");
        String icon = CommonName.getDeviceIcon(deviceType,dspecId);
        params.put("ip", hostCode);

        String status = STATUS_OFFLINE;
        if (mapStatus!=null && mapStatus.size()>0) {
            status = (mapStatus.get(data.get("hostIp")));
        }
        sb.append(
                "<a href='javascript:void(0)'><i count='" +level + "'></i>" +
                "<div class='hostoverflow' id='hostoverflow"+data.get("hostId")+"'></div>" +
                "<span id='spanhost' hostid='"+data.get("hostId")+"' hostip='"+data.get("hostIp")+"' sysType='"+sysType+"' dspec='"+data.get("dspecId")+"' status='"+status+"' desc='"+data.get("hostDesc")+"' mac='"+data.get("hostHhtcmac")+"'></span>" +
                "<span class= '"+icon+"'></span>" +
                "<h6 title='"+ data.get("hostName") +"'>" + data.get("hostName") + "</h6>" +
                "</a>"
        );
//        Result result = HttpServiceUtil.service(HttpServiceUtil.SERVICE_DEVICE, "getStatus", params);
//        String status = "";
//        if (result!=null && result.getCode() == 0) {
//            status = (String) result.getValue();
//        }
        return sb.toString();
    }

    /**
     * 递归遍历设备树，获取树上所有设备的设备编码，并组串返回；
     */
    private String getIpStrByRecursion(Directory directory,StringBuilder sb){
        List directories = directory.getDirectories();
        for(Object directory1:directories){
            Directory obj = (Directory) JsonHelper.toJavaBean(new Directory(), (JSONObject) directory1);//Json转化成Javabean
            getIpStrByRecursion(obj,sb);
        }
        List<Map<String,String>> datas = directory.getData();
        for(Map<String,String> data:datas){
            String hostIp =  data.get("hostIp");
            sb.append(hostIp);
            sb.append(",");
        }
        return String.valueOf(sb);
    }
}
