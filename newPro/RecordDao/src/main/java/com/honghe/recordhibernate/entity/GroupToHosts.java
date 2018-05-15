package com.honghe.recordhibernate.entity;

import java.util.List;

/**
 * Created by yanxue on 2015-06-24.
 */
public class GroupToHosts {

    private  String group_id;
    private  String group_name;

    private List<HostInfo> hostInfoList;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<HostInfo> getHostInfoList() {
        return hostInfoList;
    }

    public void setHostInfoList(List<HostInfo> hostInfoList) {
        this.hostInfoList = hostInfoList;
    }
}
