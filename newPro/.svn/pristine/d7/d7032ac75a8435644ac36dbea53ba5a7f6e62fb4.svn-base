package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lyx on 2015-08-15.
 * 设备使用信息统计
 */
@Entity
@Table(name = "deviceuse")
public class DeviceUse {
    private int id;
    private String mac;//物理地址，唯一标识
    private String hostName;//设备名
    private int useTime;//使用时长

    private Date createTime;//创建时间

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="d_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "d_mac")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Basic
    @Column(name="d_hostname")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Basic
    @Column(name = "d_usetime")
    public int getUseTime() {
        return useTime;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    @Basic
    @Column(name = "d_createtime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
