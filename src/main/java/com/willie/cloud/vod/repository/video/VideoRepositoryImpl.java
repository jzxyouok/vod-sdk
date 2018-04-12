package com.willie.cloud.vod.repository.video;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.repository.base.impl.BaseRepositroyImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/************************************************
 * <p>Copyright © by whxxykj</p>
 * <p>All right reserved.</p>
 * <p>Create Author: Willie</p>
 * <p> Create Date  : 2018/4/12</p>
 * <p>Last version : 1.0</p>
 * <p>Description  : </p>
 * <p>Last Update Date:</p>
 * <p>Change Log:</p>
 **************************************************/
@Repository
public class VideoRepositoryImpl extends BaseRepositroyImpl<Video, Integer> implements CustomRepository {
    public VideoRepositoryImpl(EntityManager em) {
        super(Video.class, em);
    }

    @Override
    public Page<Video> search(CloudVodConfig enableConfig, Pageable pageable) {
//        String jpql = " select t from Video as t where t.videoId is not null ";
        String sql = " select v.* from video as v where v.video_id is not null ";
        Page<Video> videoPage = buildNativeBasePage(sql, pageable);
//        Page<Video> videoPage = buildBasePage(jpql, pageable);
        System.out.println(videoPage.getTotalPages());
        return videoPage;
    }
}
