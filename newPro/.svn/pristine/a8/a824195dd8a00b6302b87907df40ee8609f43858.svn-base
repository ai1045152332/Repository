package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lyx on 2015-08-22.
 */
@Entity
@Table(name = "programme")
public class Programme {

    private int proId;
    private String proSingnal;
    private String proType;
    private Integer proLoop;
    private String proLooptype;
    private Integer proDate;
    private Integer proWeek;
    private String proTime;
    private Date proSingletime;
    private Integer proUid;
    private String proUname;
    private Date proCreatetime;

    private Set<Host> hostSet = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "p_id", unique = true, nullable = false)
    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }


    @Basic
    @Column(name = "p_singnal")
    public String getProSingnal() {
        return proSingnal;
    }

    public void setProSingnal(String proSingnal) {
        this.proSingnal = proSingnal;
    }

    @Basic
    @Column(name = "p_type")
    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Basic
    @Column(name = "p_loop")
    public Integer getProLoop() {
        return proLoop;
    }

    public void setProLoop(Integer proLoop) {
        this.proLoop = proLoop;
    }

    @Basic
    @Column(name = "p_looptype")
    public String getProLooptype() {
        return proLooptype;
    }

    public void setProLooptype(String proLooptype) {
        this.proLooptype = proLooptype;
    }

    @Basic
    @Column(name = "p_date")
    public Integer getProDate() {
        return proDate;
    }

    public void setProDate(Integer proDate) {
        this.proDate = proDate;
    }

    @Basic
    @Column(name = "p_week")
    public Integer getProWeek() {
        return proWeek;
    }

    public void setProWeek(Integer proWeek) {
        this.proWeek = proWeek;
    }

    @Basic
    @Column(name = "p_time")
    public String getProTime() {
        return proTime;
    }

    public void setProTime(String proTime) {
        this.proTime = proTime;
    }


    @Basic
    @Column(name = "p_singletime")
    public Date getProSingletime() {
        return proSingletime;
    }

    public void setProSingletime(Date proSingletime) {
        this.proSingletime = proSingletime;
    }

    @Basic
    @Column(name = "p_uid")
    public Integer getProUid() {
        return proUid;
    }

    public void setProUid(Integer proUid) {
        this.proUid = proUid;
    }

    @Basic
    @Column(name = "p_username")
    public String getProUname() {
        return proUname;
    }

    public void setProUname(String proUname) {
        this.proUname = proUname;
    }

    @Basic
    @Column(name = "p_createtime")
    public Date getProCreatetime() {
        return proCreatetime;
    }

    public void setProCreatetime(Date proCreatetime) {
        this.proCreatetime = proCreatetime;
    }


}
