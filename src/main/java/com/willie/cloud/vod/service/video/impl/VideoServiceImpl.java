package com.willie.cloud.vod.service.video.impl;

import com.willie.cloud.vod.repository.video.VideoRepository;
import com.willie.cloud.vod.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能 描述:视频文件操作接口实现</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 10:53</p>
 */
@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public VideoRepository getVideoRepository() {
        return videoRepository;
    }

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
}
