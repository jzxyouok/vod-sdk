package com.willie.cloud.vod.repository.video;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ----------------------------------------------
 * <p>类    名：</p>
 * <p>版    权：copyright© firegy.willie</p>
 * <p>作    者: liang</p>
 * <p>创建时间: 2018/4/10 20:23</p>
 * <p>描    述: </p>
 * <p>修 改 人：</p>
 * <p>修改时间：</p>
 * -----------------------------------------------
 */
public interface CustomRepository {
    Page<Video> search(CloudVodConfig enableConfig, Pageable pageable);
}
