package com.willie.cloud.vod.domain.video;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/4 9:55</p>
 */
@Entity
public class Video implements Serializable {
    private Integer id;
    private String videoName;
    private String videoId;
    private String videoRemotePath;
    private Timestamp uploadDate;
    private String appId;

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
    @Column(name = "video_name", nullable = true, length = 100)
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "video_id", nullable = true, length = 100)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "video_remote_path", nullable = true, length = 200)
    public String getVideoRemotePath() {
        return videoRemotePath;
    }

    public void setVideoRemotePath(String videoRemotePath) {
        this.videoRemotePath = videoRemotePath;
    }

    @Basic
    @Column(name = "upload_date", nullable = true)
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "app_id", nullable = true, length = 50)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return id == video.id &&
                Objects.equals(videoName, video.videoName) &&
                Objects.equals(videoId, video.videoId) &&
                Objects.equals(videoRemotePath, video.videoRemotePath) &&
                Objects.equals(uploadDate, video.uploadDate) &&
                Objects.equals(appId, video.appId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, videoName, videoId, videoRemotePath, uploadDate, appId);
    }
}
