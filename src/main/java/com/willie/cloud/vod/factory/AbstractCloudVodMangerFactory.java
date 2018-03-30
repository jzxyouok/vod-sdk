package com.willie.cloud.vod.factory;

import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.domain.config.CloudVodConfig;

/**
 * ----------------------------------------------
 * <p>类    名：</p>
 * <p>版    权：copyright© firegy.willie</p>
 * <p>作    者: liang</p>
 * <p>创建时间: 2018/3/29 22:15</p>
 * <p>描    述: </p>
 * <p>修 改 人：</p>
 * <p>修改时间：</p>
 * -----------------------------------------------
 */
public abstract class AbstractCloudVodMangerFactory {
    /**
     * 取得云点播服务管理器
     *
     * @param clazz
     * @param cloudVodConfig 点播服务配置
     * @param <T>
     * @return
     */
    public abstract <T extends CloudVodManager> T getCloudVodManger
    (Class<T> clazz, CloudVodConfig cloudVodConfig);
}
