package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lxy on 2016/7/6.
 */
@Entity
@Table(name = "setting_initialization")
public class Initialization implements java.io.Serializable{

    private Integer InitializationId;

    private String ChannelName;

    private String LockStatus;

    private String type;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getInitializationId() {
        return InitializationId;
    }

    public void setInitializationId(Integer initializationId) {
        InitializationId = initializationId;
    }

    @Column(name = "channel_name")
    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }

    @Column(name = "lock_status")
    public String getLockStatus() {
        return LockStatus;
    }

    public void setLockStatus(String lockStatus) {
        LockStatus = lockStatus;
    }

    @Column(name = "devicetype")

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
