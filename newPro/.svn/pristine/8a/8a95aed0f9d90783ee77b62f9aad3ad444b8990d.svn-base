package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2015/6/18.
 */
@Entity
@Table(name="ringbell")
public class Ringbell {
    private int ringbellId;
    private Integer ringbellLoop;
    private String ringbellLooptype;
    private Integer ringbellDate;
    private Integer ringbellWeek;
    private String ringbellTime;
    private Timestamp ringbellSingletime;
    private String ringbellType;
    private Integer ringbellUid;
    private String ringbellUname;
    private Timestamp ringbellCreatetime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "r_id")
    public int getRingbellId() {
        return ringbellId;
    }

    public void setRingbellId(int rId) {
        this.ringbellId = rId;
    }

    @Basic
    @Column(name = "r_loop")
    public Integer getRingbellLoop() {
        return ringbellLoop;
    }

    public void setRingbellLoop(Integer rLoop) {
        this.ringbellLoop = rLoop;
    }

    @Basic
    @Column(name = "r_looptype")
    public String getRingbellLooptype() {
        return ringbellLooptype;
    }

    public void setRingbellLooptype(String rLooptype) {
        this.ringbellLooptype = rLooptype;
    }

    @Basic
    @Column(name = "r_date")
    public Integer getRingbellDate() {
        return ringbellDate;
    }

    public void setRingbellDate(Integer rDate) {
        this.ringbellDate = rDate;
    }

    @Basic
    @Column(name = "r_week")
    public Integer getRingbellWeek() {
        return ringbellWeek;
    }

    public void setRingbellWeek(Integer rWeek) {
        this.ringbellWeek = rWeek;
    }

    @Basic
    @Column(name = "r_time")
    public String getRingbellTime() {
        return ringbellTime;
    }

    public void setRingbellTime(String rTime) {
        this.ringbellTime = rTime;
    }

    @Basic
    @Column(name = "r_singletime")
    public Timestamp getRingbellSingletime() {
        return ringbellSingletime;
    }

    public void setRingbellSingletime(Timestamp rSingletime) {
        this.ringbellSingletime = rSingletime;
    }

    @Basic
    @Column(name = "r_type")
    public String getRingbellType() {
        return ringbellType;
    }

    public void setRingbellType(String ringbellType) {
        this.ringbellType = ringbellType;
    }

    @Basic
    @Column(name = "r_uid")
    public Integer getRingbellUid() {
        return ringbellUid;
    }

    public void setRingbellUid(Integer rUid) {
        this.ringbellUid = rUid;
    }


    @Basic
    @Column(name = "r_createtime")
    public Timestamp getRingbellCreatetime() {
        return ringbellCreatetime;
    }

    public void setRingbellCreatetime(Timestamp rCreatetime) {
        this.ringbellCreatetime = rCreatetime;
    }


    @Basic
    @Column(name = "r_username")
    public String getRingbellUname() {
        return ringbellUname;
    }

    public void setRingbellUname(String ringbellUname) {
        this.ringbellUname = ringbellUname;
    }


}
