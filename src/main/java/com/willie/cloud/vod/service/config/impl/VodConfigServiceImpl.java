package com.willie.cloud.vod.service.config.impl;

import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;
import com.willie.cloud.vod.service.config.VodConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能 描述:云点播配置业务接口实现</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 10:46</p>
 */
@Service
public class VodConfigServiceImpl implements VodConfigService {
    private final CloudVodConfigRepository cloudVodConfigRepository;

    @Override
    public CloudVodConfigRepository getCloudVodConfigRepository() {
        return cloudVodConfigRepository;
    }

    @Autowired
    public VodConfigServiceImpl(CloudVodConfigRepository cloudVodConfigRepository) {
        this.cloudVodConfigRepository = cloudVodConfigRepository;
    }
}
