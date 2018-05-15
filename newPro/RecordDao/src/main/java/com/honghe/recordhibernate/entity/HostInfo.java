package com.honghe.recordhibernate.entity;

/**
 * Created by yanxue on 2015-06-24.
 */
public class HostInfo {

    private String id;
    private String name;
    private String token;
    private String dspec;
    private String type;

    private String status;
    private String host_desc;
    private String host_ip;
    private String dspecid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDspec() {
        return dspec;
    }

    public void setDspec(String dspec) {
        this.dspec = dspec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHost_desc() {
        return host_desc;
    }

    public void setHost_desc(String host_desc) {
        this.host_desc = host_desc;
    }

    public String getHost_ip() {
        return host_ip;
    }

    public void setHost_ip(String host_ip) {
        this.host_ip = host_ip;
    }

    public String getDspecid() {
        return dspecid;
    }

    public void setDspecid(String dspecid) {
        this.dspecid = dspecid;
    }
}
