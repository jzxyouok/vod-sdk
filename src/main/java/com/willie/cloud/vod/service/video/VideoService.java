package com.willie.cloud.vod.service.video;

import com.willie.cloud.vod.repository.video.VideoRepository;

/**
 * <p>功能 描述:视频文件操作接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 10:42</p>
 */
public interface VideoService {

    /**
     * 取得视频仓库
     *
     * @return 视频仓库
     */
    VideoRepository getVideoRepository();
}
