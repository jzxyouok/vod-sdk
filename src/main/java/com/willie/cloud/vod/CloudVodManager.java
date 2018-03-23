package com.willie.cloud.vod;

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

}
