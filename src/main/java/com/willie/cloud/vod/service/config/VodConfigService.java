package com.willie.cloud.vod.service.config;

import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;

/**
 * <p>功能 描述:云点播配置业务接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 10:44</p>
 */
public interface VodConfigService {
    /**
     * 取得云点播配置仓库
     *
     * @return 云点播配置仓库
     */
    CloudVodConfigRepository getCloudVodConfigRepository();
}
