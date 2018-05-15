package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2015/2/4.
 */
@Entity
@Table(name = "device_log_type")
public class DeviceLogType {
    private int logtypeId;
    private String logtypeKey;
    private String logtypeName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "logtype_id")
    public int getLogtypeId() {
        return logtypeId;
    }

    public void setLogtypeId(int logtypeId) {
        this.logtypeId = logtypeId;
    }

    @Basic
    @Column(name = "logtype_key")
    public String getLogtypeKey() {
        return logtypeKey;
    }

    public void setLogtypeKey(String logtypeKey) {
        this.logtypeKey = logtypeKey;
    }

    @Basic
    @Column(name = "logtype_name")
    public String getLogtypeName() {
        return logtypeName;
    }

    public void setLogtypeName(String logtypeName) {
        this.logtypeName = logtypeName;
    }
}
