package com.willie.cloud.vod.domain.video;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 11:19</p>
 */
@Entity
public class Video implements Serializable {
    private Integer id;
    private String videoName;
    private String videoId;
    private String videoRemotePath;
    private String coverId;
    private Timestamp uploadDate;
    private String appId;

    public Video() {
    }

    public Video(Integer id, String videoName, String videoId, String videoRemotePath, String coverId, Timestamp uploadDate, String appId) {
        this.id = id;
        this.videoName = videoName;
        this.videoId = videoId;
        this.videoRemotePath = videoRemotePath;
        this.coverId = coverId;
        this.uploadDate = uploadDate;
        this.appId = appId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "video_name", length = 100)
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "video_id", length = 100)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "video_remote_path", length = 200)
    public String getVideoRemotePath() {
        return videoRemotePath;
    }

    public void setVideoRemotePath(String videoRemotePath) {
        this.videoRemotePath = videoRemotePath;
    }

    @Basic
    @Column(name = "cover_id", length = 50)
    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    @Basic
    @Column(name = "upload_date")
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "app_id",length = 50)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videoName='" + videoName + '\'' +
                ", videoId='" + videoId + '\'' +
                ", videoRemotePath='" + videoRemotePath + '\'' +
                ", coverId='" + coverId + '\'' +
                ", uploadDate=" + uploadDate +
                ", appId='" + appId + '\'' +
                '}';
    }
}
