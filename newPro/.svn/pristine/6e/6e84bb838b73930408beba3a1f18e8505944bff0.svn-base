package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2015/6/18.
 *
 */
@Entity
@Table(name = "signalplan")
public class Signalplan {
    private int signalId;
    private String signal;
    private String signalCode;
    private Integer signalLoop;
    private String signalLooptype;
    private Integer signalDate;
    private Integer signalWeek;
    private String signalTime;
    private Timestamp signalSingletime;
    private Integer signalUid;
    private String signalUname;
    private Timestamp signalCreatetime;
    private String signalType;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "s_id")
    public int getSignalId() {
        return signalId;
    }

    public void setSignalId(int sId) {
        this.signalId = sId;
    }

    @Basic
    @Column(name = "s_signal")
    public String getSignal() {
        return signal;
    }

    public void setSignal(String sSignal) {
        this.signal = sSignal;
    }

    @Basic
    @Column(name = "s_signal_code")
    public String getSignalCode() {
        return signalCode;
    }

    public void setSignalCode(String sSignalCode) {
        this.signalCode = sSignalCode;
    }

    @Basic
    @Column(name = "s_loop")
    public Integer getSignalLoop() {
        return signalLoop;
    }

    public void setSignalLoop(Integer sLoop) {
        this.signalLoop = sLoop;
    }

    @Basic
    @Column(name = "s_looptype")
    public String getSignalLooptype() {
        return signalLooptype;
    }

    public void setSignalLooptype(String sLooptype) {
        this.signalLooptype = sLooptype;
    }

    @Basic
    @Column(name = "s_date")
    public Integer getSignalDate() {
        return signalDate;
    }

    public void setSignalDate(Integer sDate) {
        this.signalDate = sDate;
    }

    @Basic
    @Column(name = "s_week")
    public Integer getSignalWeek() {
        return signalWeek;
    }

    public void setSignalWeek(Integer sWeek) {
        this.signalWeek = sWeek;
    }

    @Basic
    @Column(name = "s_time")
    public String getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(String sTime) {
        this.signalTime = sTime;
    }

    @Basic
    @Column(name = "s_singletime")
    public Timestamp getSignalSingletime() {
        return signalSingletime;
    }

    public void setSignalSingletime(Timestamp sSingletime) {
        this.signalSingletime = sSingletime;
    }

    @Basic
    @Column(name = "s_uid")
    public Integer getSignalUid() {
        return signalUid;
    }

    public void setSignalUid(Integer sUid) {
        this.signalUid = sUid;
    }

    @Basic
    @Column(name = "s_username")
    public String getSignalUname() {
        return signalUname;
    }

    public void setSignalUname(String singalUname) {
        this.signalUname = singalUname;
    }

    @Basic
    @Column(name = "s_createtime")
    public Timestamp getSignalCreatetime() {
        return signalCreatetime;
    }

    public void setSignalCreatetime(Timestamp sCreatetime) {
        this.signalCreatetime = sCreatetime;
    }

    @Basic
    @Column(name = "s_type")
    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }
}
