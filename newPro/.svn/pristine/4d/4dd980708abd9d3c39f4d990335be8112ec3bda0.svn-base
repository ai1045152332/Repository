package com.honghe.recordweb.service.frontend.devicemanager.message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * 设备消息
 * User: zhanghongbin
 * Date: 14-10-10
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class DeviceRequestMessage {

    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    private String uuid;
    private String time;

    public String getToken() {
        return token;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCmdId() {
        return cmdId;
    }

    public JSONArray getParams() {
        return params;
    }

    private String cmdId;
    private JSONArray params = null;

    public String getDeviceToken() {
        return deviceToken;
    }

    private String deviceToken = "";

    public String getFunc() {
        return func;
    }

    public String getHostId() {
        return hostId;
    }

    private String func = "";
    private String hostId = "";

    private DeviceRequestMessage() {

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getTime() {
        return time;
    }

    public static final DeviceRequestMessage parse(String data) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        DeviceRequestMessage deviceRequestMessage = new DeviceRequestMessage();
        deviceRequestMessage.cmdId = jsonObject.getString("cmdId");
        deviceRequestMessage.token = jsonObject.getString("token");
        deviceRequestMessage.hostId = jsonObject.getString("hostId");
        deviceRequestMessage.func = jsonObject.getString("func");
        if (jsonObject.containsKey("deviceToken")) {
            deviceRequestMessage.deviceToken = jsonObject.getString("deviceToken");
        }
        deviceRequestMessage.uuid = UUID.randomUUID().toString();
        deviceRequestMessage.time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        if (jsonObject.containsKey("params")) {
            deviceRequestMessage.params = jsonObject.getJSONArray("params");
        }
        return deviceRequestMessage;
    }


    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }


}

