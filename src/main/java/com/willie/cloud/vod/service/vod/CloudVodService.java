package com.willie.cloud.vod.service.vod;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.factory.AbstractCloudVodMangerFactory;
import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;
import com.willie.cloud.vod.repository.video.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>功能 描述:点播服务统一外部调用接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 13:42</p>
 */
public abstract class CloudVodService {
    protected static Logger logger = LoggerFactory.getLogger(CloudVodService.class);
    protected AbstractCloudVodMangerFactory cloudVodMangerFactory;

    @Autowired
    protected CloudVodConfigRepository cloudVodConfigRepository;
    @Autowired
    protected VideoRepository videoRepository;

    /**
     * 取得可用点播服务配置
     *
     * @return 点播服务配置
     */
    protected CloudVodConfig getEnableCloudVodManager() {
        return cloudVodConfigRepository.findCloudVodConfigByEnable(1);
    }

    /**
     * 取得配置仓库
     *
     * @return 配置仓库
     */
    protected CloudVodConfigRepository getCloudVodConfigRepository() {
        return cloudVodConfigRepository;
    }
}
