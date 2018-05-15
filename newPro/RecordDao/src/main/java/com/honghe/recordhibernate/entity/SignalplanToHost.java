package com.honghe.recordhibernate.entity;

/**
 * Created by yanxue on 2015-06-25.
 */
public class SignalplanToHost {

    private String signalId;
    private String signal;
    private String signalCode;
    private String signalLoop;
    private String signalLooptype;

    private String signalDate;
    private String signalWeek;
    private String signalTime;
    private String signalSingletime;
    private String signalUid;

    private String signalUname;
    private String signalCreatetime;
    private String hostids;
    private String hosts;

    public String getSignalId() {
        return signalId;
    }

    public void setSignalId(String signalId) {
        if (signalId.equals("null")) {
            signalId = "";
        }
        this.signalId = signalId;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        if (signal.equals("null")) {
            signal = "";
        }
        this.signal = signal;
    }

    public String getSignalCode() {
        return signalCode;
    }

    public void setSignalCode(String signalCode) {
        if (signalCode.equals(null)) {
            signalCode = "";
        }
        this.signalCode = signalCode;
    }

    public String getSignalLoop() {
        return signalLoop;
    }

    public void setSignalLoop(String signalLoop) {
        if (signalLoop.equals("null")) {
            signalLoop = "";
        }
        this.signalLoop = signalLoop;
    }

    public String getSignalLooptype() {
        return signalLooptype;
    }

    public void setSignalLooptype(String signalLooptype) {
        if (signalLooptype.equals("null")) {
            signalLooptype = "";
        }
        this.signalLooptype = signalLooptype;
    }

    public String getSignalDate() {
        return signalDate;
    }

    public void setSignalDate(String signalDate) {
        if (signalDate.equals("null")) {
            signalDate = "";
        }
        this.signalDate = signalDate;
    }

    public String getSignalWeek() {
        return signalWeek;
    }

    public void setSignalWeek(String signalWeek) {
        if (signalWeek.equals("null")) {
            signalWeek = "";
        }
        this.signalWeek = signalWeek;
    }

    public String getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(String signalTime) {
        if (signalTime.equals("null")) {
            signalTime = "";
        }
        this.signalTime = signalTime;
    }

    public String getSignalSingletime() {
        return signalSingletime;
    }

    public void setSignalSingletime(String signalSingletime) {
        if (signalSingletime.equals("null")) {
            signalSingletime = "";
        }
        this.signalSingletime = signalSingletime;
    }

    public String getSignalUid() {
        return signalUid;
    }

    public void setSignalUid(String signalUid) {
        if (signalUid.equals("null")) {
            signalUid = "";
        }
        this.signalUid = signalUid;
    }

    public String getSignalCreatetime() {
        return signalCreatetime;
    }

    public void setSignalCreatetime(String signalCreatetime) {
        if (signalCreatetime.equals("null")) {
            signalCreatetime = "";
        }
        this.signalCreatetime = signalCreatetime;
    }

    public String getHostids() {
        return hostids;
    }

    public void setHostids(String hostids) {
        if (hostids.equals("null")) {
            hostids = "";
        }
        this.hostids = hostids;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        if (hosts.equals("null")) {
            hosts = "";
        }
        this.hosts = hosts;
    }

    public String getSignalUname() {
        return signalUname;
    }

    public void setSignalUname(String signalUname) {
        if(signalUname.equals("null")){
            signalUname = "";
        }
        this.signalUname = signalUname;
    }


}
