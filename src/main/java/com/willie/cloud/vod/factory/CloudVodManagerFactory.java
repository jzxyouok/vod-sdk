package com.willie.cloud.vod.factory;

import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.domain.config.CloudVodConfig;

/**
 * ----------------------------------------------
 * <p>类    名：</p>
 * <p>版    权：copyright© firegy.willie</p>
 * <p>作    者: liang</p>
 * <p>创建时间: 2018/3/29 22:19</p>
 * <p>描    述: </p>
 * <p>修 改 人：</p>
 * <p>修改时间：</p>
 * -----------------------------------------------
 */
public class CloudVodManagerFactory extends AbstractCloudVodMangerFactory {

    @Override
    public <T extends CloudVodManager> T getCloudVodManger(Class<T> clazz,
                                                           CloudVodConfig cloudVodConfig) {
        CloudVodManager cloudVodManager = null;
        try {
            cloudVodManager = (CloudVodManager) Class.forName(clazz.getName()).newInstance();
            cloudVodManager.setAppId(cloudVodConfig.getAppId());
            cloudVodManager.setAccessKey(cloudVodConfig.getAccessKey());
            cloudVodManager.setSecretKey(cloudVodConfig.getSecretKey());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) cloudVodManager;
    }
}
