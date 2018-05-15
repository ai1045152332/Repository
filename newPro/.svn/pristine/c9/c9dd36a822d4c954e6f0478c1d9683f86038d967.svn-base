package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2015/2/12.
 */
@Entity
@Table(name = "policy")
public class Policy {
    private int pId;
    private Integer pType;
    private Integer pLoop;
    private String pLooptype;
    private Integer pDate;
    private Integer pWeek;
    private String pTime;
    private String pDevicetype;

    @Basic
    @Column(name = "p_username")
    public String getpUsername() {
        return pUsername;
    }

    public void setpUsername(String pUsername) {
        this.pUsername = pUsername;
    }

    private String pUsername;
    private Date pSingletime;
    private Integer pUid;
    private Date pCreateTime;
    /*many2many*/
    private Set<Host> hostSet = new HashSet<Host>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "p_id",unique = true, nullable = false)
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "p_type")
    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    @Basic
    @Column(name = "p_loop")
    public Integer getpLoop() {
        return pLoop;
    }

    public void setpLoop(Integer pLoop) {
        this.pLoop = pLoop;
    }

    @Basic
    @Column(name = "p_looptype")
    public String getpLooptype() {
        return pLooptype;
    }

    public void setpLooptype(String pLooptype) {
        this.pLooptype = pLooptype;
    }

    @Basic
    @Column(name = "p_date")
    public Integer getpDate() {
        return pDate;
    }

    public void setpDate(Integer pDate) {
        this.pDate = pDate;
    }

    @Basic
    @Column(name = "p_week")
    public Integer getpWeek() {
        return pWeek;
    }

    public void setpWeek(Integer pWeek) {
        this.pWeek = pWeek;
    }



    @Basic
    @Column(name = "p_devicetype")
    public String getpDevicetype() {
        return pDevicetype;
    }

    public void setpDevicetype(String pDevicetype) {
        this.pDevicetype = pDevicetype;
    }

    @Basic
    @Column(name = "p_time")
    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    @Basic
    @Column(name = "p_singletime")
    public Date getpSingletime() {
        return pSingletime;
    }

    public void setpSingletime(Date pSingletime) {
        this.pSingletime = pSingletime;
    }

    @Basic
    @Column(name = "p_uid")
    public Integer getpUid() {
        return pUid;
    }

    public void setpUid(Integer pUid) {
        this.pUid = pUid;
    }

    @Basic
    @Column(name = "p_createtime")
    public Date getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Date pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

//    @ManyToMany(mappedBy="policySet")
    @Transient
    public Set<Host> getHostSet()
    {
        return this.hostSet;
    }

    public void setHostSet(Set<Host> hostSet)
    {
        this.hostSet = hostSet;
    }
}
