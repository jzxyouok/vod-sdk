package com.willie.cloud.vod.factory;

import com.qcloud.vod.VodApi;
import com.willie.cloud.vod.domain.CloudVodConfig;
import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.aliyun.AliyunVodManager;
import com.willie.cloud.vod.bfcloud.BFCloudVodManager;

/**
 * <p>功能 描述:云点播服务工厂</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 13:58</p>
 */
public class CloudVodFactory {

    private static final String APP_NAME = "aliyun";

    /**
     * 腾讯云点播管理
     *
     * @param cloudVodConfig 云点播服务配置
     * @return 云点播服务配置
     */
    public static VodApi getQCloudVodManager(CloudVodConfig cloudVodConfig) {
        return new VodApi(cloudVodConfig.getAccessKey(), cloudVodConfig.getSecretKey(), cloudVodConfig.getExpires().intValue());
    }

    /**
     * 云点播管理
     *
     * @param cloudVodConfig 云点播服务配置
     * @return 云点播管理
     */
    public static CloudVodManager getCloudVodManager(CloudVodConfig cloudVodConfig) {
        if (APP_NAME.equalsIgnoreCase(cloudVodConfig.getAppName())) {
            return AliyunVodManager.getAliyunCloudVodManagerInstance(cloudVodConfig);
        } else {
            return BFCloudVodManager.getBFCloudVodManagerInstance(cloudVodConfig);
        }
    }
}
