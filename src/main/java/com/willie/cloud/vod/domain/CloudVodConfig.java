package com.willie.cloud.vod.domain;

import java.io.Serializable;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 10:19</p>
 */
public class CloudVodConfig implements Serializable {
    private String appName;//云点播应用名
    private String appId;//用户id
    private String accessKey;//公钥
    private String secretKey;//私钥
    private Long expires = 60 * 60 * 24L;//token有效期，默认24小时失效

    public CloudVodConfig() {
    }

    public CloudVodConfig(String appName, String appId, String accessKey, String secretKey, Long expires) {
        this.appName = appName;
        this.appId = appId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.expires = expires;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "Config{" +
                "appName='" + appName + '\'' +
                ", appId='" + appId + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", expires=" + expires +
                '}';
    }
}
