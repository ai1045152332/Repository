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
@Table(name = "t_resource_catalog")
public class TResourceCatalog {
    private int id;
    private String name;
    private int type;
    private Integer duration;
    private String format;
    private Integer status;
    private String virtual;
    private Integer fid;
    private Boolean folder;
    private String deep;
    private Boolean edit;
    private String subitems;
    private String layer;
    private Boolean islock;
    private String md5;
    private Integer delFlag;

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
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "virtual")
    public String getVirtual() {
        return virtual;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }

    @Basic
    @Column(name = "fid")
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "folder")
    public Boolean getFolder() {
        return folder;
    }

    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    @Basic
    @Column(name = "deep")
    public String getDeep() {
        return deep;
    }

    public void setDeep(String deep) {
        this.deep = deep;
    }

    @Basic
    @Column(name = "edit")
    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    @Basic
    @Column(name = "subitems")
    public String getSubitems() {
        return subitems;
    }

    public void setSubitems(String subitems) {
        this.subitems = subitems;
    }

    @Basic
    @Column(name = "layer")
    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    @Basic
    @Column(name = "islock")
    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    @Basic
    @Column(name = "md5")
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Basic
    @Column(name = "delFlag")
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    private Integer resourceCatalog_id;
    public Integer getResourceCatalog_id() {
        return resourceCatalog_id;
    }

    public void setResourceCatalog_id(Integer resourceCatalog_id) {
        this.resourceCatalog_id = resourceCatalog_id;
    }
    private Integer resourceIndex_id;
    public Integer getResourceIndex_id() {
        return resourceIndex_id;
    }

    public void setResourceIndex_id(Integer resourceIndex_id) {
        this.resourceIndex_id = resourceIndex_id;
    }
    private TResourceIndex resourceIndex;

	/*
	 * private Integer indexId;
	 *
	 *
	 * public Integer getIndexId() { return indexId; }
	 *
	 *
	 * public void setIndexId(Integer indexId) { this.indexId = indexId; }
	 */

    @ManyToOne(targetEntity = TResourceIndex.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
/*	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)*/
    public TResourceIndex getResourceIndex() {
        return resourceIndex;
    }

    public void setResourceIndex(TResourceIndex resourceIndex) {
        this.resourceIndex = resourceIndex;
    }
}
