package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by qinzhihui on 2015/12/7.
 * 用于储存在线时间
 */

@Entity
@Table(name = "onlinetime")
public class OnlineTime {

    private int id;
    private String ip;
    private String mac;
    private String type;
    private long opentime;
    private long closetime;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Basic
    @Column(name = "mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "opentime")
    public long getOpentime() {
        return opentime;
    }

    public void setOpentime(long opentime) {
        this.opentime = opentime;
    }
    @Basic
    @Column(name = "closetime")
    public long getClosetime() {
        return closetime;
    }

    public void setClosetime(long closetime) {
        this.closetime = closetime;
    }
}
