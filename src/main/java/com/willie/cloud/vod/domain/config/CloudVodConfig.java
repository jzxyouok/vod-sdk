package com.willie.cloud.vod.domain.config;

import javax.persistence.*;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 10:47</p>
 */
@Entity
@Table(name = "cloud_vod_config")
public class CloudVodConfig {
    private Integer id;//主键
    private String appName;//应用名称
    private String appId; //应用编码
    private String accessKey;//公钥
    private String secretKey;//密钥
    private Integer expires;//token 有效期
    private Long enable; //是否启用

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
    public String toString() {
        return "CloudVodConfig{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", appId='" + appId + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", expires=" + expires +
                ", enable=" + enable +
                '}';
    }
}
