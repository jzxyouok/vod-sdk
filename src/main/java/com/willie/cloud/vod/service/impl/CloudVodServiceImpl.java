package com.willie.cloud.vod.service.impl;

import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;
import com.willie.cloud.vod.service.CloudVodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 13:46</p>
 */
@Service
public class CloudVodServiceImpl implements CloudVodService {
    @Autowired
    private CloudVodConfigRepository cloudVodConfigRepository;

    @Override
    public CloudVodConfigRepository getCloudVodConfigRepository() {
        return cloudVodConfigRepository;
    }
}
