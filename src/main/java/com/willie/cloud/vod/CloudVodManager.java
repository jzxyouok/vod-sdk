package com.willie.cloud.vod;

import com.willie.cloud.vod.constent.vod.Vod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 15:01</p>
 */
public abstract class CloudVodManager {
    protected static Logger logger = LoggerFactory.getLogger(CloudVodManager.class);
    protected String appId;//用户id
    protected String accessKey;//公钥
    protected String secretKey;//密钥
    protected long expires;//token失效时常

    /**
     * 取得过期时间
     *
     * @param expires 过期时间
     * @return 过期时间
     */
    protected long getExpires(Long expires) {
        long time = System.currentTimeMillis() / 1000;
        return expires == null ? time + Vod.DEFAULT_EXPIRES : time + expires.longValue();
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
}
