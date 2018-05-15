package com.honghe.recordhibernate.entity;

import javax.persistence.*;

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
@Table(name = "t_resource_index")
public class TResourceIndex {
    private int id;
    private String tName;
    private Integer tType;
    private String tPath;
    private String tResult;
    private String tDesc;
    private String tVersion;
    private Integer tStatus;
    private String tConvert;
    private String tSituation;
    private String tMd5;
    private Integer resourceId;
    private Integer resouceStatusId;

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
    @Column(name = "t_name")
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Basic
    @Column(name = "t_type")
    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }

    @Basic
    @Column(name = "t_path")
    public String gettPath() {
        return tPath;
    }

    public void settPath(String tPath) {
        this.tPath = tPath;
    }

    @Basic
    @Column(name = "t_result")
    public String gettResult() {
        return tResult;
    }

    public void settResult(String tResult) {
        this.tResult = tResult;
    }

    @Basic
    @Column(name = "t_desc")
    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    @Basic
    @Column(name = "t_version")
    public String gettVersion() {
        return tVersion;
    }

    public void settVersion(String tVersion) {
        this.tVersion = tVersion;
    }

    @Basic
    @Column(name = "t_status")
    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Basic
    @Column(name = "t_convert")
    public String gettConvert() {
        return tConvert;
    }

    public void settConvert(String tConvert) {
        this.tConvert = tConvert;
    }

    @Basic
    @Column(name = "t_situation")
    public String gettSituation() {
        return tSituation;
    }

    public void settSituation(String tSituation) {
        this.tSituation = tSituation;
    }

    @Basic
    @Column(name = "t_md5")
    public String gettMd5() {
        return tMd5;
    }

    public void settMd5(String tMd5) {
        this.tMd5 = tMd5;
    }

    @Basic
    @Column(name = "resouceStatus_id")
    public Integer getResouceStatusId() {
        return resouceStatusId;
    }

    public void setResouceStatusId(Integer resouceStatusId) {
        this.resouceStatusId = resouceStatusId;
    }
    @Basic
    @Column(name = "resource_id")
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    private TResource resource;
    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    public TResource getResource() {
        return resource;
    }

    public void setResource(TResource resource) {
        this.resource = resource;
    }

    private TResourceStatus resouceStatus;
    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    public TResourceStatus getResouceStatus() {
        return resouceStatus;
    }

    public void setResouceStatus(TResourceStatus resouceStatus) {
        this.resouceStatus = resouceStatus;
    }
}
