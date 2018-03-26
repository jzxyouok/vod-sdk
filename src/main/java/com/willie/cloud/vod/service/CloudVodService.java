package com.willie.cloud.vod.service;

import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 13:42</p>
 */
public interface CloudVodService {


    /**
     * 取得配置仓库
     * @return
     */
    CloudVodConfigRepository getCloudVodConfigRepository();
}
