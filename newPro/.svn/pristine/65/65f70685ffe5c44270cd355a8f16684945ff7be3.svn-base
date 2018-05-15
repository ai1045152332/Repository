package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sky on 2016/5/19.
 */
@Entity
@Table(name = "touchscreen")
public class Touchscreen {

    private int tid;
    private Integer touchtype;
    private Integer touchLoop;
    private String tLooptype;

    private Integer touchDate;
    private Integer touchWeek;
    private String touchTime;
    private Date tSingletime;
    private String tDevicetype;
    private Integer touchUid;
    private String touchUname;
    private Date tCreatetime;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "t_id", unique = true, nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
    @Basic
    @Column(name="t_createtime")
    public Date gettCreatetime() {
        return tCreatetime;
    }

    public void settCreatetime(Date tCreatetime) {
        this.tCreatetime = tCreatetime;
    }


    @Basic
    @Column(name="t_type")
    public Integer getTouchtype() {
        return touchtype;
    }

    public void setTouchtype(Integer touchtype) {
        this.touchtype = touchtype;
    }
    @Basic
    @Column(name="t_loop")
    public Integer getTouchLoop() {
        return touchLoop;
    }

    public void setTouchLoop(Integer touchLoop) {
        this.touchLoop = touchLoop;
    }
    @Basic
    @Column(name="t_looptype")
    public String gettLooptype() {
        return tLooptype;
    }

    public void settLooptype(String tLooptype) {
        this.tLooptype = tLooptype;
    }
    @Basic
    @Column(name="t_date")
    public Integer getTouchDate() {
        return touchDate;
    }

    public void setTouchDate(Integer touchDate) {
        this.touchDate = touchDate;
    }
    @Basic
    @Column(name="t_week")
    public Integer getTouchWeek() {
        return touchWeek;
    }

    public void setTouchWeek(Integer touchWeek) {
        this.touchWeek = touchWeek;
    }
    @Basic
    @Column(name="t_time")
    public String getTouchTime() {
        return touchTime;
    }

    public void setTouchTime(String touchTime) {
        this.touchTime = touchTime;
    }
    @Basic
    @Column(name="t_singletime")
    public Date gettSingletime() {
        return tSingletime;
    }

    public void settSingletime(Date tSingletime) {
        this.tSingletime = tSingletime;
    }
    @Basic
    @Column(name="t_devicetype")
    public String gettDevicetype() {
        return tDevicetype;
    }

    public void settDevicetype(String tDevicetype) {
        this.tDevicetype = tDevicetype;
    }

    @Basic
    @Column(name="t_uid")
    public Integer getTouchUid() {
        return touchUid;
    }

    public void setTouchUid(Integer touchUid) {
        this.touchUid = touchUid;
    }
    @Basic
    @Column(name="t_uname")
    public String getTouchUname() {
        return touchUname;
    }

    public void setTouchUname(String touchUname) {
        this.touchUname = touchUname;
    }
}
