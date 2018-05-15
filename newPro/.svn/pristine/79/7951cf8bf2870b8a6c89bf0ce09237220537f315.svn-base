package com.honghe.recordhibernate.entity;


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by yanxue on 2015-07-07.
 * 监控软件统计类
 */
@Entity
@Table(name = "monitor")
public class Monitor {

    private int id;
    private String mac;//物理地址，唯一标识
    private String deviceName;//设备名称
    private String softName;//软件名
    private int useTime; //软件使用时间

    private Date createTime;//创建时间


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "m_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "m_mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Basic
    @Column(name = "m_devicename")
    public String getDeviceName() {
        return deviceName;
    }


    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "m_softname")
    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    @Basic
    @Column(name = "m_usetime")
    public int getUseTime() {
        return useTime;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    @Basic
    @Column(name = "m_createtime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
