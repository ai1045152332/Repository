package com.honghe.recordhibernate.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by lch on 2014/9/26.
 */
@Entity
@Table(name="timeplan")
public class Timeplan {
    private int timeplanId;
    private Byte timeplanWeek;
    private Date timeplanDate;
    private Byte timeplanTurnoff;
    private Byte timeplanAutooff;
    private Byte timeplanTurnon;
    private int hostId;
    private int sectionId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "timeplan_id")
    public int getTimeplanId() {
        return timeplanId;
    }

    public void setTimeplanId(int timeplanId) {
        this.timeplanId = timeplanId;
    }

    @Basic
    @Column(name = "timeplan_week")
    public Byte getTimeplanWeek() {
        return timeplanWeek;
    }

    public void setTimeplanWeek(Byte timeplanWeek) {
        this.timeplanWeek = timeplanWeek;
    }

    @Basic
    @Column(name = "timeplan_date")
    public Date getTimeplanDate() {
        return timeplanDate;
    }

    public void setTimeplanDate(Date timeplanDate) {
        this.timeplanDate = timeplanDate;
    }

    @Basic
    @Column(name = "timeplan_turnoff")
    public Byte getTimeplanTurnoff() {
        return timeplanTurnoff;
    }

    public void setTimeplanTurnoff(Byte timeplanTurnoff) {
        this.timeplanTurnoff = timeplanTurnoff;
    }

    @Basic
    @Column(name = "timeplan_autooff")
    public Byte getTimeplanAutooff() {
        return timeplanAutooff;
    }

    public void setTimeplanAutooff(Byte timeplanAutooff) {
        this.timeplanAutooff = timeplanAutooff;
    }

    @Basic
    @Column(name = "timeplan_turnon")
    public Byte getTimeplanTurnon() {
        return timeplanTurnon;
    }

    public void setTimeplanTurnon(Byte timeplanTurnon) {
        this.timeplanTurnon = timeplanTurnon;
    }


    @Basic
    @Column(name = "host_id")
    public int getHostId(){ return hostId; }

    public void setHostId(int hostId){ this.hostId = hostId; }

    @Basic
    @Column(name = "section_id")
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

}
