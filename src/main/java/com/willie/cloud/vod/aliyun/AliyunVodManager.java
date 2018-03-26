package com.willie.cloud.vod.aliyun;

import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.domain.config.CloudVodConfig;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 14:50</p>
 */
public class AliyunVodManager extends CloudVodManager {
    private static volatile AliyunVodManager aliyunVodManager;

    private AliyunVodManager() {
    }

    /**
     * 取得阿里云点播管理
     *
     * @param cloudVodConfig 阿里云vod相关配置信息
     * @return 阿里云点播管理
     */
    public static AliyunVodManager getAliyunCloudVodManagerInstance(CloudVodConfig cloudVodConfig) {
        if (null == aliyunVodManager) {
            synchronized (AliyunVodManager.class) {
                if (null == aliyunVodManager) {
                    aliyunVodManager = new AliyunVodManager();
                }
            }
            appId = cloudVodConfig.getAppId();
            accessKey = cloudVodConfig.getAccessKey();
            secretKey = cloudVodConfig.getSecretKey();
            expires = cloudVodConfig.getExpires();
        }
        return aliyunVodManager;
    }
}
