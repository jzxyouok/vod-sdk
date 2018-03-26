package com.willie.cloud.vod.domain.config;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 10:47</p>
 */
@Entity
@Table(name = "cloud_vod_config")
public class CloudVodConfig {
    private int id;
    private String appName;
    private String appId;
    private String accessKey;
    private String secretKey;
    private Integer expires;
    private Long enable;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "app_name", nullable = true, length = 50)
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Basic
    @Column(name = "app_id", nullable = true, length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "access_key", nullable = true, length = 100)
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Basic
    @Column(name = "secret_key", nullable = true, length = 100)
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Basic
    @Column(name = "expires", nullable = true)
    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    @Basic
    @Column(name = "enable", nullable = true)
    public Long getEnable() {
        return enable;
    }

    public void setEnable(Long enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudVodConfig that = (CloudVodConfig) o;
        return id == that.id &&
                Objects.equals(appName, that.appName) &&
                Objects.equals(appId, that.appId) &&
                Objects.equals(accessKey, that.accessKey) &&
                Objects.equals(secretKey, that.secretKey) &&
                Objects.equals(expires, that.expires) &&
                Objects.equals(enable, that.enable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, appName, appId, accessKey, secretKey, expires, enable);
    }
}
