package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2015/2/12.
 */
@Entity
@Table(name = "syslog")
public class Syslog {
    private int logId;
    private String logUser;
    private String logContent;
    private String logHost;
    private String logType;
    private int logTime;
    private String logIp;
    @Basic
    @Column(name = "log_ip")
    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "log_id")
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "log_user")
    public String getLogUser() {
        return logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    @Basic
    @Column(name = "log_content")
    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    @Basic
    @Column(name = "log_host")
    public String getLogHost() {
        return logHost;
    }

    public void setLogHost(String logHost) {
        this.logHost = logHost;
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
    @Column(name = "log_time")
    public int getLogTime() {
        return logTime;
    }

    public void setLogTime(int logTime) {
        this.logTime = logTime;
    }
}
