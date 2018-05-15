package com.honghe.recordweb.action.frontend.device;

import com.honghe.recordhibernate.entity.Device;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by chby on 2014/10/10.
 */
@Controller

@Scope(value = "prototype")
public class DeviceAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(BaseAction.class);

    @Resource
    private NVR nvr;

    private String hostIp;

    private String hostId;

    private String goBackUrl;

    private String deviceId;

    private String mediaAddr;

    @Resource
    private DeviceService deviceService;
    @Resource
    private HostDevice hostDevice;


    public String getHostId() {
        return hostId;
    }

    public String getGoBackUrl() {
        return goBackUrl;
    }

    public void setGoBackUrl(String goBackUrl) {
        this.goBackUrl = goBackUrl;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMediaAddr() {
        return mediaAddr;
    }

    public void setMediaAddr(String mediaAddr) {
        this.mediaAddr = mediaAddr;
    }

    public String getHostIp() { return hostIp; }

    public void setHostIp(String hostIp) { this.hostIp = hostIp; }

    /**
     * 获取设备信息
     * @return
     * @throws Exception
     */
    public String deviceList() throws Exception {
        if (hostId == null) {
            hostId = "0";
            //todo 加注释 0
        }
        Map hostInfo = hostDevice.getHostInfoById(this.hostId);
        String hostFactory = "";
        if (hostInfo != null && !hostInfo.isEmpty()) {
            hostFactory = hostInfo.get("host_factory").toString();
        }
        ActionContext.getContext().put("hostFactory", hostFactory);
        List<Object[]> deviceList = deviceService.getDeviceListService(Integer.parseInt(hostId));
        ActionContext.getContext().put("deviceList", deviceList);
        return "cameralist";
    }

    /**
     * 预览某个镜头
     * <p/>
     * 从外网设备获取视频流仍为内网地址，故改为从数据库读取视频流 Alter:wuzhenzhen 2015-10-12
     */
    public String cameraInfo() {
        Device device = deviceService.getDeviceInfoService(Integer.parseInt(this.deviceId));
        String mediaUrl = "";
        mediaUrl = device.getDeviceSubStream();
        if (mediaUrl.equals("")) {
            mediaUrl = device.getDeviceMainStream();
        }
        ActionContext.getContext().put("mediaUrl", mediaUrl);
        return "cameraInfo";
    }


    /**
     * 爱录客设备VGA、Movie模式媒体流地址重定向为：当前浏览器IP地址+端口号
     */
    public void updateCameraInfo() {
        JSONObject json = new JSONObject();
        try {
            if (nvr.isOnline(this.hostIp)) {
                DefaultHttpClient httpclient = new DefaultHttpClient();
                boolean result_http = false;
                Device device = deviceService.getDeviceInfoService(Integer.parseInt(this.deviceId));
                if (arecLogin(httpclient, this.hostIp)) {
                    result_http = setDirectorConfig(httpclient, this.hostIp, this.mediaAddr, device.getDeviceName());
                }
                if (result_http) {
                    device.setDeviceMainStream(this.mediaAddr);
                    device.setDeviceSubStream(this.mediaAddr);
                    Boolean result = deviceService.updateDeviceService(device);
                    if (result) {
                        json.put("success", true);
                        json.put("msg", "重定向成功");
                    } else {
                        json.put("success", false);
                        json.put("msg", "重定向失败");
                    }
                } else {
                    json.put("success", false);
                    json.put("msg", "重定向失败");
                }
            } else {
                json.put("success", false);
                json.put("msg", "设备离线，重定向失败");
            }
        } catch (Exception e) {
            logger.error("媒体流地址定向异常：", e);
            json.put("success", false);
            json.put("msg", "重定向失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 爱录客设备登录
     */
    public boolean arecLogin(DefaultHttpClient httpclient, String hostIp) throws Exception {
        String login_url = "http://" + hostIp + "/api/login";
        HttpPost httppost = new HttpPost(login_url);
        httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
        JSONObject login_json = new JSONObject();
        String userName = "director";
        String strPwd = new BASE64Encoder().encode(userName.getBytes());
        login_json.put("username", userName);
        login_json.put("password", strPwd);
        StringEntity entity = new StringEntity(login_json.toString());
        httppost.setEntity(entity);
        // 执行
        HttpResponse httpresponse = httpclient.execute(httppost);
        HttpEntity entity1 = httpresponse.getEntity();
        String body = EntityUtils.toString(entity1);
        JSONObject jsonObject = JSONObject.fromObject(body);
        String result = jsonObject.get("code").toString();
        if (result.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 爱录客设备媒体流地址重定向
     */
    public boolean setDirectorConfig(DefaultHttpClient httpclient, String hostIp, String mediaUrl, String tokenName) throws Exception {
        // 目标地址
        String url = "http://" + hostIp + "/api/set_director_config";
        HttpPost httppost = new HttpPost(url);
        httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
        JSONObject director_json = new JSONObject();
        if (tokenName.equals("VGA")) {
            director_json.put("channel", 3);
        } else {
            director_json.put("channel", 4);
        }
        director_json.put("url", mediaUrl);
        StringEntity entity = new StringEntity(director_json.toString());
        httppost.setEntity(entity);
        // 执行
        HttpResponse httpresponse = httpclient.execute(httppost);
        HttpEntity entity1 = httpresponse.getEntity();
        String body = EntityUtils.toString(entity1);
        JSONObject jsonObject = JSONObject.fromObject(body);
        String result = jsonObject.get("code").toString();
        if (result.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

}
