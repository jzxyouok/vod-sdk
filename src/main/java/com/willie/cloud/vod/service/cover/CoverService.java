package com.willie.cloud.vod.service.cover;

import com.willie.cloud.vod.repository.cover.CoverRepository;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 11:30</p>
 */
public interface CoverService {
    /**
     * 取得封面仓库
     *
     * @return
     */
    CoverRepository getCoverRepository();
}
