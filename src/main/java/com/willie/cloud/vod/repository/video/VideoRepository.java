package com.willie.cloud.vod.repository.video;

import com.willie.cloud.vod.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>功能 描述:视频仓库</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 10:54</p>
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    /**
     * 根据点播应用id查询上传成功的视频
     *
     * @param appId
     * @return
     */
    List<Video> findVideosByAppIdAndVideoIdIsNotNull(String appId);
}
