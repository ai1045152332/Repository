package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Arrays;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/24
 */
@Entity
@Table(name = "t_resource_status")
public class TResourceStatus {
    private int id;
    private String name;
    private byte[] resourceIndex;
    private String uploadtime;
    private String uploader;
    private String uploadstatus;
    private String updater;
    private String checker;
    private String checktime;
    private String formatstatus;
    private String checkstatus;
    private String updatetime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "resourceIndex")
    public byte[] getResourceIndex() {
        return resourceIndex;
    }

    public void setResourceIndex(byte[] resourceIndex) {
        this.resourceIndex = resourceIndex;
    }

    @Basic
    @Column(name = "uploadtime")
    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Basic
    @Column(name = "uploader")
    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Basic
    @Column(name = "uploadstatus")
    public String getUploadstatus() {
        return uploadstatus;
    }

    public void setUploadstatus(String uploadstatus) {
        this.uploadstatus = uploadstatus;
    }

    @Basic
    @Column(name = "updater")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Basic
    @Column(name = "checker")
    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    @Basic
    @Column(name = "checktime")
    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    @Basic
    @Column(name = "formatstatus")
    public String getFormatstatus() {
        return formatstatus;
    }

    public void setFormatstatus(String formatstatus) {
        this.formatstatus = formatstatus;
    }

    @Basic
    @Column(name = "checkstatus")
    public String getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus;
    }

    @Basic
    @Column(name = "updatetime")
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}
