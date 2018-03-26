package com.willie.cloud.vod.repository.config;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 10:59</p>
 */
@Repository
public interface CloudVodConfigRepository extends JpaRepository<CloudVodConfig, Integer> {
    /**
     * 查询配置信息
     *
     * @param appName 应用名称
     * @return 配置
     */
    CloudVodConfig findCloudVodConfigByAppName(String appName);
}
