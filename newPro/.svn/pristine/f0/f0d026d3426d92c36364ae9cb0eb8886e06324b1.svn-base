package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by hthwx on 2015/3/24.
 */
@Entity
@Table(name = "resource")
public class Resource {

    private Set<Video> videos = new HashSet<Video>(0);

    private int resId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @javax.persistence.Column(name = "res_id")
    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    private String resName;

    @Basic
    @javax.persistence.Column(name = "res_name")
    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    private String hostName;

    @Basic
    @javax.persistence.Column(name = "host_name")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    private String hostIp;

    @Basic
    @javax.persistence.Column(name = "host_ip")
    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    private String resTitle;

    @Basic
    @javax.persistence.Column(name = "res_title")
    public String getResTitle() {
        return resTitle;
    }

    public void setResTitle(String resTitle) {
        this.resTitle = resTitle;
    }

    private String resPath;

    @Basic
    @javax.persistence.Column(name = "res_path")
    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    private String resResolution;

    @Basic
    @javax.persistence.Column(name = "res_resolution")
    public String getResResolution() {
        return resResolution;
    }

    public void setResResolution(String resResolution) {
        this.resResolution = resResolution;
    }

    private String resClass;

    @Basic
    @javax.persistence.Column(name = "res_class")
    public String getResClass() {
        return resClass;
    }

    public void setResClass(String resClass) {
        this.resClass = resClass;
    }

    private String resGrade;

    @Basic
    @javax.persistence.Column(name = "res_grade")
    public String getResGrade() {
        return resGrade;
    }

    public void setResGrade(String resGrade) {
        this.resGrade = resGrade;
    }

    private String resSubject;

    @Basic
    @javax.persistence.Column(name = "res_subject")
    public String getResSubject() {
        return resSubject;
    }

    public void setResSubject(String resSubject) {
        this.resSubject = resSubject;
    }

    private String resCourse;

    @Basic
    @javax.persistence.Column(name = "res_course")
    public String getResCourse() {
        return resCourse;
    }

    public void setResCourse(String resCourse) {
        this.resCourse = resCourse;
    }

    private String resSpeaker;

    @Basic
    @javax.persistence.Column(name = "res_speaker")
    public String getResSpeaker() {
        return resSpeaker;
    }

    public void setResSpeaker(String resSpeaker) {
        this.resSpeaker = resSpeaker;
    }

    private String resSchool;

    @Basic
    @javax.persistence.Column(name = "res_school")
    public String getResSchool() {
        return resSchool;
    }

    public void setResSchool(String resSchool) {
        this.resSchool = resSchool;
    }

    private String resSize;

    @Basic
    @javax.persistence.Column(name = "res_size")
    public String getResSize() {
        return resSize;
    }

    public void setResSize(String resSize) {
        this.resSize = resSize;
    }

    private Date resUpdatetime;

    @Basic
    @javax.persistence.Column(name = "res_updatetime")
    public Date getResUpdatetime() {
        return resUpdatetime;
    }

    public void setResUpdatetime(Date resUpdatetime) {
        this.resUpdatetime = resUpdatetime;
    }

    private Integer resStatus;

    @Basic
    @javax.persistence.Column(name = "res_status")
    public Integer getResStatus() {
        return resStatus;
    }

    public void setResStatus(Integer resStatus) {
        this.resStatus = resStatus;
    }

    private Integer resDownloads;

    @Basic
    @javax.persistence.Column(name = "res_downloads")
    public Integer getResDownloads() {
        return resDownloads;
    }

    public void setResDownloads(Integer resDownloads) {
        this.resDownloads = resDownloads;
    }

    private Integer resVisits;

    @Basic
    @javax.persistence.Column(name = "res_visits")
    public Integer getResVisits() {
        return resVisits;
    }

    public void setResVisits(Integer resVisits) {
        this.resVisits = resVisits;
    }

    private String resThumb;

    @Basic
    @javax.persistence.Column(name = "res_thumb")
    public String getResThumb() {
        return resThumb;
    }

    public void setResThumb(String resThumb) {
        this.resThumb = resThumb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (resId != resource.resId) return false;
        if (hostIp != null ? !hostIp.equals(resource.hostIp) : resource.hostIp != null) return false;
        if (hostName != null ? !hostName.equals(resource.hostName) : resource.hostName != null) return false;
        if (resClass != null ? !resClass.equals(resource.resClass) : resource.resClass != null) return false;
        if (resCourse != null ? !resCourse.equals(resource.resCourse) : resource.resCourse != null) return false;
        if (resDownloads != null ? !resDownloads.equals(resource.resDownloads) : resource.resDownloads != null)
            return false;
        if (resGrade != null ? !resGrade.equals(resource.resGrade) : resource.resGrade != null) return false;
        if (resName != null ? !resName.equals(resource.resName) : resource.resName != null) return false;
        if (resPath != null ? !resPath.equals(resource.resPath) : resource.resPath != null) return false;
        if (resResolution != null ? !resResolution.equals(resource.resResolution) : resource.resResolution != null)
            return false;
        if (resSchool != null ? !resSchool.equals(resource.resSchool) : resource.resSchool != null) return false;
        if (resSize != null ? !resSize.equals(resource.resSize) : resource.resSize != null) return false;
        if (resSpeaker != null ? !resSpeaker.equals(resource.resSpeaker) : resource.resSpeaker != null) return false;
        if (resStatus != null ? !resStatus.equals(resource.resStatus) : resource.resStatus != null) return false;
        if (resSubject != null ? !resSubject.equals(resource.resSubject) : resource.resSubject != null) return false;
        if (resThumb != null ? !resThumb.equals(resource.resThumb) : resource.resThumb != null) return false;
        if (resTitle != null ? !resTitle.equals(resource.resTitle) : resource.resTitle != null) return false;
        if (resUpdatetime != null ? !resUpdatetime.equals(resource.resUpdatetime) : resource.resUpdatetime != null)
            return false;
        if (resVisits != null ? !resVisits.equals(resource.resVisits) : resource.resVisits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resId;
        result = 31 * result + (resName != null ? resName.hashCode() : 0);
        result = 31 * result + (hostName != null ? hostName.hashCode() : 0);
        result = 31 * result + (hostIp != null ? hostIp.hashCode() : 0);
        result = 31 * result + (resTitle != null ? resTitle.hashCode() : 0);
        result = 31 * result + (resPath != null ? resPath.hashCode() : 0);
        result = 31 * result + (resResolution != null ? resResolution.hashCode() : 0);
        result = 31 * result + (resClass != null ? resClass.hashCode() : 0);
        result = 31 * result + (resGrade != null ? resGrade.hashCode() : 0);
        result = 31 * result + (resSubject != null ? resSubject.hashCode() : 0);
        result = 31 * result + (resCourse != null ? resCourse.hashCode() : 0);
        result = 31 * result + (resSpeaker != null ? resSpeaker.hashCode() : 0);
        result = 31 * result + (resSchool != null ? resSchool.hashCode() : 0);
        result = 31 * result + (resSize != null ? resSize.hashCode() : 0);
        result = 31 * result + (resUpdatetime != null ? resUpdatetime.hashCode() : 0);
        result = 31 * result + (resStatus != null ? resStatus.hashCode() : 0);
        result = 31 * result + (resDownloads != null ? resDownloads.hashCode() : 0);
        result = 31 * result + (resVisits != null ? resVisits.hashCode() : 0);
        result = 31 * result + (resThumb != null ? resThumb.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
    public Set<Video> getVideos()
    {
        return this.videos;
    }

    public void setVideos(Set<Video> videos)
    {
        this.videos = videos;
    }
}
