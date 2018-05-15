package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2015/2/12.
 */
@Entity
@Table(name = "logtype")
public class Logtype {
    private int logtypeId;
    private String logType;
    private String logName;

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
    @Column(name = "log_type")
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "log_name")
    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}
