package com.willie.cloud.vod.repository.cover;

import com.willie.cloud.vod.domain.cover.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 11:29</p>
 */
@Repository
public interface CoverRepository extends JpaRepository<Cover, Integer> {
}
