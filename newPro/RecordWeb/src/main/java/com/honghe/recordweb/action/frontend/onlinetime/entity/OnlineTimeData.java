package com.honghe.recordweb.action.frontend.onlinetime.entity;

/**
 * Created by qinzhihui on 2015/12/17.
 * 数据显示到报表统计页面上使用
 */
public class OnlineTimeData {
    private String mac;//设备MAC地址
    private String name;//设备名称
    private String ip;//设备ip
    private String openRate;//开机率
    private String openDuration;//开机时长
    private String acitivity;//活跃度
    //--------------------------------------下面是set get方法-------------------------------------------------------
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenRate() {
        return openRate;
    }

    public void setOpenRate(String openRate) {
        this.openRate = openRate;
    }

    public String getOpenDuration() {
        return openDuration;
    }

    public void setOpenDuration(String openDuration) {
        this.openDuration = openDuration;
    }

    public String getAcitivity() {
        return acitivity;
    }

    public void setAcitivity(String liveness) {
        this.acitivity = liveness;
    }
}
