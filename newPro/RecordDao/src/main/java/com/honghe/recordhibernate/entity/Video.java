package com.honghe.recordhibernate.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by hthwx on 2015/3/24.
 */
@Entity
@Table(name = "video")
public class Video {
    private int videoId;
    private String videoName;
    private Date videoTime;
    private Integer videoDownloads;
    private Integer videoVisits;
    private String videoSize;
    private Integer videoStatus;
    private String videoThumb;
    private Integer videoUpload;
    private String videoUrl;
    private String videoPath;
    private Integer videoDuration;
    private String videoResolution;
    private String videoCode;
    private Resource resource;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "video_id")
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "video_name")
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "video_time")
    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
    }

    @Basic
    @Column(name = "video_downloads")
    public Integer getVideoDownloads() {
        return videoDownloads;
    }

    public void setVideoDownloads(Integer videoDownloads) {
        this.videoDownloads = videoDownloads;
    }

    @Basic
    @Column(name = "video_visits")
    public Integer getVideoVisits() {
        return videoVisits;
    }

    public void setVideoVisits(Integer videoVisits) {
        this.videoVisits = videoVisits;
    }

    @Basic
    @Column(name = "video_size")
    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    @Basic
    @Column(name = "video_status")
    public Integer getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    @Basic
    @Column(name = "video_thumb")
    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }

    @Basic
    @Column(name = "video_upload")
    public Integer getVideoUpload() {
        return videoUpload;
    }

    public void setVideoUpload(Integer videoUpload) {
        this.videoUpload = videoUpload;
    }

    @Basic
    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    @Basic
    @Column(name = "video_duration")
    public Integer getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Integer videoDuration) {
        this.videoDuration = videoDuration;
    }
    @Basic
    @Column(name = "video_resolution")
    public String getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(String videoResolution) {
        this.videoResolution = videoResolution;
    }
    @Basic
    @Column(name = "video_code")
    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    @Basic
    @Column(name = "video_path")
    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video that = (Video) o;

        if (videoId != that.videoId) return false;
        if (videoDownloads != null ? !videoDownloads.equals(that.videoDownloads) : that.videoDownloads != null)
            return false;
        if (videoName != null ? !videoName.equals(that.videoName) : that.videoName != null) return false;
        if (videoSize != null ? !videoSize.equals(that.videoSize) : that.videoSize != null) return false;
        if (videoStatus != null ? !videoStatus.equals(that.videoStatus) : that.videoStatus != null) return false;
        if (videoThumb != null ? !videoThumb.equals(that.videoThumb) : that.videoThumb != null) return false;
        if (videoTime != null ? !videoTime.equals(that.videoTime) : that.videoTime != null) return false;
        if (videoVisits != null ? !videoVisits.equals(that.videoVisits) : that.videoVisits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = videoId;
        result = 31 * result + (videoName != null ? videoName.hashCode() : 0);
        result = 31 * result + (videoTime != null ? videoTime.hashCode() : 0);
        result = 31 * result + (videoDownloads != null ? videoDownloads.hashCode() : 0);
        result = 31 * result + (videoVisits != null ? videoVisits.hashCode() : 0);
        result = 31 * result + (videoSize != null ? videoSize.hashCode() : 0);
        result = 31 * result + (videoStatus != null ? videoStatus.hashCode() : 0);
        result = 31 * result + (videoThumb != null ? videoThumb.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY,targetEntity=Resource.class)
    @JoinColumn(name = "res_id")
    public Resource getResource()
    {
        return this.resource;
    }

    public void setResource(Resource resource)
    {
        this.resource = resource;
    }
}
