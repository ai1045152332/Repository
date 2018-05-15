package com.honghe.recordweb.util;

import com.hht.smartcampus.sip.SIPManagerCallback;
import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.service.client.Result;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author lxy
 * @date 2016/9/16
 */
public class DmanagerSIPManagerCallback extends SIPManagerCallback {
    private  static final Logger logger = Logger.getLogger(DmanagerSIPManagerCallback.class);
    private static DeviceService deviceService = (DeviceService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(DeviceService.class);
    private static SettingsService settingsService = (SettingsService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(SettingsService.class);
    private static HostgroupService hostgroupService = (HostgroupService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostgroupService.class);
    private static HostDevice hostDevice = (HostDevice) ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostDevice.class);

    @Override
    public void onReceiveResponseMessage(String s, String s1, String s2, String s3) {

    }
    /**
     * todo 加注释
     * @param LocalSIPClientID
     * @param MessageReceived
     * @return
     */
    @Override
    public String onReceiveMessage(String LocalSIPClientID ,String abc, String MessageReceived) {
        JSONObject resultJS = new JSONObject();
        try {
            if(MessageReceived != null && MessageReceived.contains("rtsp://")){
                return MessageReceived;
            }
            //do some thing,
//            Thread.sleep(50);
            if(MessageReceived == null || "".equals(MessageReceived))
            {
                resultJS.put("cmdType","");
                resultJS.put("result","false");
                resultJS.put("msg","请求参数为空！");
                return resultJS.toString();
            }
            JSONObject queryParams = JSONObject.fromObject(MessageReceived);
            return mobileService(queryParams);
        } catch (Exception e) {
            resultJS.put("cmdType","");
            resultJS.put("result","false");
            resultJS.put("msg","数据处理异常！");
            logger.error("SIP接受数据处理异常",e);
        }
        return resultJS.toString();
    }

    /**
     * 根据接收的参数不同，进行不同的业务处理
     * @param queryParams
     * @return
     */
    public String mobileService(JSONObject queryParams)
    {
        JSONObject resultJS = new JSONObject();
        String cmdType = "";
        String cmd = "";
        try{
            cmdType = queryParams.getString("cmd_op");
            cmd = queryParams.getString("cmd");
            if(!"HREC".equals(cmd)){
                resultJS.put("type",cmdType);
                resultJS.put("code","-2");
                resultJS.put("result","");
                return resultJS.toString();
            }
        }catch(Exception e){
            resultJS.put("type",cmdType);
            resultJS.put("code","-1");
            resultJS.put("result","");
            return resultJS.toString();
        }
        try
        {
            if("getHRECDeviceOnlineInfo".equals(cmdType)) //根据IP获取设备在线状态
            {
                String cmdIp = "";
                try{
                    cmdIp  = queryParams.getString("IP");
                }catch(Exception e){
                    resultJS.put("type",cmdType);
                    resultJS.put("code","-1");
                    resultJS.put("result","");
                    return resultJS.toString();
                }

                String[] allIp = cmdIp.split(",");
                if(allIp.length<1){
                    resultJS.put("type",cmdType);
                    resultJS.put("code","-1");
                    resultJS.put("result","");
                    return resultJS.toString();
                }
                Map newMap = new HashMap();
                String isOnline = "0";//"0"离线，"1"在线
                boolean flag = false;
                for(int i=0; i < allIp.length; i++){
                    Map<String, String> params = new HashMap<>();
                    params.put("ip", allIp[i]);
                    Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Is_Online, params);
                    if (res.getCode() == 0 && res.getValue() != null) {
                        flag = (boolean) res.getValue();
                    }
                    if(flag){
                        isOnline = "1";
                    }
                    newMap.put(allIp[i],isOnline);
                    isOnline = "0";
                }
                resultJS.put("type",cmdType);
                resultJS.put("code","0");
                resultJS.put("result",newMap);
                return resultJS.toString();
            }
            else if("getHRECDeviceInfo".equals(cmdType)) //根据IP获取录播设备
            {
                List cmdList = new ArrayList<>();
                String cmdIp = "";
                try{
                    cmdIp  = queryParams.getString("IP");
                }catch(Exception e){
                    resultJS.put("type",cmdType);
                    resultJS.put("code","-1");
                    resultJS.put("result","");
                    return resultJS.toString();
                }
                String[] allIp = cmdIp.split(",");
                String isptz = "0";

                for(int i=0; i < allIp.length; i++){
                    try{
                        Map<String, String> params = new HashMap<>();
                        Map<String,Object> newMap = new HashMap<>();
                        Map<String,Object> hostinfo = new HashMap<>();
                        params.put("hostIp", allIp[i]);
                        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetHostInfoByIp, params);
                        hostinfo = (Map<String, Object>) result.getValue();
                        if(hostinfo.size()==0){
                            newMap.put("devicename","");
                            newMap.put("devicetype","");
                            newMap.put("deviceip",allIp[i]);
                            newMap.put("tokens",new ArrayList<>());
                            cmdList.add(newMap);
                            continue;
                        }
                        newMap.put("devicename",hostinfo.get("host_name").toString());
                        newMap.put("devicetype",hostinfo.get("dtype_name").toString());
                        newMap.put("deviceip",hostinfo.get("host_ipaddr").toString());
                        List<Object[]> tokenList = deviceService.getToken(Integer.parseInt(hostinfo.get("host_id").toString()));
                        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
                        String connect_param = settingsService.getConnect_param(hostinfo.get("host_id").toString());
                        Map hostInfo = hostgroupService.getHostInfoById(hostinfo.get("host_id").toString());
                        Map hostmap = hostDevice.getHostInfoById(hostinfo.get("host_id").toString());
                        String hostFactory = hostInfo.get("host_factory").toString();
                        String dspecid = hostmap.get("dspec_id").toString();
                        List tokensList = new ArrayList<>();
                        for(Object[] simpleList : tokenList){
                            String token = simpleList[2].toString();
                            String isPtz = "0";
                            if (!hostFactory.equals("Arec")) {//Arec设备不支持云台
                                if(dspecid.equals("17"))//虚拟设备云台控制全开
                                {
                                    isPtz = "1";
                                }
                                else {
                                    for (Object[] deviceConfig : deviceConfigList) {
                                        if (deviceConfig[1].toString().equals(simpleList[0].toString())) {
                                            if ((deviceConfig[2].toString().equals(token) || deviceConfig[3].toString().equals(token)) && connect_param.equals(deviceConfig[0].toString())) {
                                                isPtz = deviceConfig[4].toString();
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            Map<String,String> tokenMap2 = new HashMap<>();
                            Map<String,String> tokenMap3 = new HashMap<>();
                            tokenMap2.put("name",simpleList[0].toString());
                            tokenMap2.put("token",simpleList[1].toString());
                            tokenMap2.put("url",simpleList[3].toString());
                            tokenMap2.put("isptz",isPtz);
                            tokenMap2.put("tokentype","main");
                            tokenMap3.put("name",simpleList[0].toString());
                            tokenMap3.put("token",simpleList[2].toString());
                            tokenMap3.put("url",simpleList[4].toString());
                            tokenMap3.put("isptz",isPtz);
                            tokenMap3.put("tokentype","sub");
                            tokensList.add(tokenMap2);
                            tokensList.add(tokenMap3);
                        }
                        newMap.put("tokens", tokensList);
                        cmdList.add(newMap);
                    }catch(Exception e){
                        Map newMap = new HashMap();
                        newMap.put("devicename","");
                        newMap.put("devicetype","");
                        newMap.put("deviceip",allIp[i]);
                        newMap.put("tokens",new ArrayList<>());
                        cmdList.add(newMap);
                        continue;
                    }
                }
                resultJS.put("type",cmdType);
                resultJS.put("code","0");
                resultJS.put("result",cmdList);
                return resultJS.toString();
            }
            else if("ptzStart".equals(cmdType)) //云台控制
            {
                String cmdIp = "";
                String token = "";
                String xyz = "";
                String x = "";
                String y = "";
                String z = "";
                try{
                    cmdIp = queryParams.getString("IP");
                    token = queryParams.getString("token");
                    xyz = queryParams.getString("xyz");//云台坐标
                    String[] coordinate = xyz.split(":");
                    x = coordinate[0];
                    y = coordinate[1];
                    z = coordinate[2];
                }catch(Exception e){
                    resultJS.put("type",cmdType);
                    resultJS.put("code","-1");
                    return resultJS.toString();
                }

                Map<String, String> _params = new HashMap<>();
                _params.put("ip", cmdIp);
                _params.put("timeout", "1000");
                _params.put("x", x);
                _params.put("y", y);
                _params.put("profileToken", String.valueOf(token));
                _params.put("z", z);
                Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_PTZStartMove, _params);
                resultJS.put("type",cmdType);
                resultJS.put("code","0");
            }
            else if("ptzStop".equals(cmdType))//云台停止
            {
                String cmdIp = "";
                String token = "";
                try{
                    cmdIp = queryParams.getString("IP");
                    token = queryParams.getString("token");
                }catch(Exception e){
                    resultJS.put("type",cmdType);
                    resultJS.put("code","-1");
                    return resultJS.toString();
                }
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", cmdIp);
                _params.put("mediaToken", String.valueOf(token));
                Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_PTZStopMove, _params);
                resultJS.put("type",cmdType);
                resultJS.put("code","0");
            }
            else
            {
                resultJS.put("type",cmdType);
                resultJS.put("code","-2");
            }

        }
        catch (Exception e)
        {
            resultJS.put("type",cmdType);
            resultJS.put("code","-3");
            resultJS.put("result",new ArrayList<>());
            logger.error("SIP参数处理异常，type为"+cmdType,e);
        }
        return resultJS.toString();
    }
}

