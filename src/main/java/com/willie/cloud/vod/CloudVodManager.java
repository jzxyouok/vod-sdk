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
    protected static String appId;//用户id
    protected static String accessKey;//公钥
    protected static String secretKey;//密钥
    protected static long expires;//token失效时常

    /**
     * 取得过期时间
     *
     * @param expires 过期时间
     * @return 过期时间
     */
    protected long getExpires(Long expires) {
        long time = System.currentTimeMillis() / 1000;
        return expires == null ? time + Vod.BfCloudConstent.DEFAULT_EXPIRES : time + expires.longValue();
    }
}
