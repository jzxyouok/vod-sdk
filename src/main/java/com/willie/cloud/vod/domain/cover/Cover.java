package com.willie.cloud.vod.domain.cover;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 11:20</p>
 */
@Entity
public class Cover implements Serializable {
    private Integer id;
    private Integer coverId;
    private String coverName;
    private String coverRemotePath;
    private String videoId;
    private Timestamp uploadDate;

    public Cover() {
    }

    public Cover(Integer id, Integer coverId, String coverName, String coverRemotePath, String videoId, Timestamp uploadDate) {
        this.id = id;
        this.coverId = coverId;
        this.coverName = coverName;
        this.coverRemotePath = coverRemotePath;
        this.videoId = videoId;
        this.uploadDate = uploadDate;
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
    @Column(name = "cover_id", nullable = true)
    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    @Basic
    @Column(name = "cover_name", nullable = true, length = 50)
    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    @Basic
    @Column(name = "cover_remote_path", nullable = true, length = 100)
    public String getCoverRemotePath() {
        return coverRemotePath;
    }

    public void setCoverRemotePath(String coverRemotePath) {
        this.coverRemotePath = coverRemotePath;
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
    @Column(name = "upload_date", nullable = true)
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Cover{" +
                "id=" + id +
                ", coverId=" + coverId +
                ", coverName='" + coverName + '\'' +
                ", coverRemotePath='" + coverRemotePath + '\'' +
                ", videoId='" + videoId + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
