package com.willie.cloud.vod.service.vod.impl;

import com.willie.cloud.vod.bfcloud.BFCloudVodManager;
import com.willie.cloud.vod.constent.vod.Vod;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.exception.ParameterException;
import com.willie.cloud.vod.factory.AbstractCloudVodMangerFactory;
import com.willie.cloud.vod.factory.CloudVodManagerFactory;
import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import com.willie.cloud.vod.service.vod.CloudVodService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 16:00</p>
 */
@Service
public class CloudVodQueryServiceImpl extends CloudVodService implements CloudVodQueryService {

    @Override
    public Map<String, Object> getFileFromCategory(String categoryId, Long expires) {
        if (!StringUtils.hasText(categoryId)) {
            throw new ParameterException("categoryId could not be null");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());
        String appName = enableCloudVodConfig.getAppName();
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
            return bfCloudVodManager.getFileFromCategory(categoryId, expires);
        }
    }

    @Override
    public Map<String, Object> getFileFromAlbum(String albumId, Long expires) {
        if (!StringUtils.hasText(albumId)) {
            throw new ParameterException("albumId could not be null");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());
        String appName = enableCloudVodConfig.getAppName();
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
            return bfCloudVodManager.getFileFromAlbum(albumId, expires);
        }
    }

    @Override
    public CloudVodConfig getEnableCloudVodManager() {
        return super.getEnableCloudVodManager();
    }

    @Override
    public CloudVodConfigRepository getCloudVodConfigRepository() {
        return super.getCloudVodConfigRepository();
    }

}
