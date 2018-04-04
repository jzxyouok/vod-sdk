package com.willie.cloud.vod.factory;

import com.willie.cloud.vod.aliyun.AliyunVodManager;
import com.willie.cloud.vod.bfcloud.BFCloudVodManager;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.tencent.QCloudVodManager;

/**
 * <p>功能 描述:云点播服务工厂</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 13:58</p>
 */
public abstract class CloudVodFactory {

    /**
     * 腾讯云点播管理
     *
     * @param cloudVodConfig 云点播服务配置
     * @return 云点播服务配置
     */
    public static QCloudVodManager getQCloudVodManager(CloudVodConfig cloudVodConfig) {
        return QCloudVodManager.getQCloudVodManagerInstance(cloudVodConfig);
    }

    /**
     * 暴风云点播管理
     *
     * @param cloudVodConfig 云点播服务配置
     * @return 云点播管理
     */
    public static BFCloudVodManager getBfCloudVodManager(CloudVodConfig cloudVodConfig) {
        return BFCloudVodManager.getBFCloudVodManagerInstance(cloudVodConfig);
    }

    /**
     * 阿里云点播管理
     *
     * @param cloudVodConfig 云点播服务配置
     * @return 云点播管理
     */
    public static AliyunVodManager getAliyunVodManager(CloudVodConfig cloudVodConfig) {
        return AliyunVodManager.getAliyunCloudVodManagerInstance(cloudVodConfig);
    }

    private CloudVodFactory() {
    }
}
