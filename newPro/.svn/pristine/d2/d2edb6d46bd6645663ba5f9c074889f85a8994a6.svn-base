package com.honghe.recordweb.service.frontend.devicemanager.message;

import net.sf.json.JSONObject;

/**
 * 设备消息响应类
 * User: zhanghongbin
 * Date: 14-10-11
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public class DeviceResponseMessage {


    public enum Type {
        EVENT, RESPONSE;
    }

    public String getDesc() {
        return desc;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getToken() {
        return token;

    }

    public Type getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String desc;


    private String eventType;

    private String token;
    private Type type;
    private String userId;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    private String deviceType;

    public DeviceResponseMessage(String token, String deviceType,Type type, String desc) {
        this.token = token;
        this.type = type;
        this.desc = desc;
        this.deviceType = deviceType;
    }

    /**
     * 转换成json格式数据
     *
     * @return
     */
    public String toJson() {
        return JSONObject.fromObject(this).toString();
    }
//
//    public static void main(String[] args) {
//        DeviceResponseMessage deviceResponseMessage = new DeviceResponseMessage("sdfsd",Type.EVENT);
//        System.out.println(deviceResponseMessage.toJson());
//    }
}
