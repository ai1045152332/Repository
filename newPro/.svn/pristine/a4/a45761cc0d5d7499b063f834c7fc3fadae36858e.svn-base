package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import javax.print.attribute.IntegerSyntax;

/**
 * Created by zhanghongbin on 2015/1/13.
 */
@Entity
@Table(name="setting")
public class Setting {
    private int id = 1;
    private Integer recordinfoResolution=1080;
    private Integer recordinfoBitRate=0;
    private String nvrUsername = "";
    private String nvrPassword = "";
    private String liveAddr = "";
    private Integer curriculumType = 2;
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "recordinfo_resolution")
    public Integer getRecordinfoResolution() {
        return recordinfoResolution;
    }
    public void setRecordinfoResolution(Integer recordinfoResolution) {
        this.recordinfoResolution = recordinfoResolution;
    }

    @Basic
    @Column(name = "recordinfo_bit_rate")
    public Integer getRecordinfoBitRate() {
        return recordinfoBitRate;
    }
    public void setRecordinfoBitRate(Integer recordinfoBitRate) {
        this.recordinfoBitRate = recordinfoBitRate;
    }

    @Basic
    @Column(name = "nvr_username")
    public String getNvrUsername() {
        return nvrUsername;
    }
    public void setNvrUsername(String nvrUsername) {
        this.nvrUsername = nvrUsername;
    }

    @Basic
    @Column(name = "nvr_password")
    public String getNvrPassword() {
        return nvrPassword;
    }
    public void setNvrPassword(String nvrPassword) {
        this.nvrPassword = nvrPassword;
    }

    @Basic
    @Column(name = "live_addr")
    public String getLiveAddr() {
        return liveAddr;
    }
    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    @Basic
    @Column(name = "curriculum_type")
    public Integer getCurriculumType() {
        return curriculumType;
    }
    public void setCurriculumType(Integer curriculumType) {
        this.curriculumType = curriculumType;
    }
}
