package com.willie.cloud.vod.service.impl;

import com.willie.cloud.vod.aliyun.constent.AliyunConstent;
import com.willie.cloud.vod.bfcloud.BFCloudVodManager;
import com.willie.cloud.vod.bfcloud.api.BFCloudAlbum;
import com.willie.cloud.vod.bfcloud.api.BFCloudCategory;
import com.willie.cloud.vod.bfcloud.constent.BFConstent;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.exception.ParameterException;
import com.willie.cloud.vod.factory.CloudVodFactory;
import com.willie.cloud.vod.service.CloudVodService;
import com.willie.cloud.vod.service.CloudVodUpdateService;
import com.willie.cloud.vod.tencent.constent.QCloudConstent;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:52</p>
 */
@Service
public class CloudVodUpdateServiceImpl extends CloudVodService implements CloudVodUpdateService {

    @Override
    public Map<String, Object> deleteFile(String fileId, Long expires) {
        if (!StringUtils.hasText(fileId)) {
            throw new ParameterException("fileId could not be null");
        }
        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());
        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.deleteFile(fileId, expires);
        }
    }

    //TODO 稍后需要加入子分类，最多2级分类，分类个数最多200个的验证
    @Override
    public Map<String, Object> addCategory(String name, String parentCategoryId, Long expires) throws UnsupportedEncodingException {
        if (!StringUtils.hasText(name)) {
            throw new ParameterException("categoryName could not be null");
        }

        if (BFCloudCategory.CATEGORY_NAME_MAXLENGTH_BIT < name.getBytes(BFConstent.CHARSET).length) {
            throw new ParameterException("categoryName`s length less then 128");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());
        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.addCategory(name, parentCategoryId, expires);
        }
    }

    @Override
    public Map<String, Object> deleteCategory(String categoryId, Long expires) {
        if (!StringUtils.hasText(categoryId)) {
            throw new ParameterException("categoryId could not be null");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务

        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.deleteCategory(categoryId, expires);
        }
    }

    @Override
    public Map<String, Object> addFile2Category(String categoryId, String fileId, Long expires) {
        if (!StringUtils.hasText(categoryId)) {
            throw new ParameterException("categoryId could not be null");
        }

        if (!StringUtils.hasText(fileId)) {
            throw new ParameterException("fileId could not be null");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务

        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.addFile2Category(categoryId, fileId, expires);
        }
    }

    @Override
    public Map<String, Object> deleteFileFromCategory(String categoryId, String fileId, Long expires) {
        if (!StringUtils.hasText(categoryId)) {
            throw new ParameterException("categoryId could not be null");
        }

        if (!StringUtils.hasText(fileId)) {
            throw new ParameterException("fileId could not be null");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务

        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.deleteFileFromCategory(categoryId, fileId, expires);
        }
    }

    //TODO 专辑个数最多200个验证
    @Override
    public Map<String, Object> addAlbum(String name, Long expires) throws UnsupportedEncodingException {
        if (!StringUtils.hasText(name)) {
            throw new ParameterException("albumName could not be null");
        }

        if (BFCloudAlbum.ALBUM_NAMEL_MAXLENGTH_BIT < name.getBytes(BFConstent.CHARSET).length) {
            throw new ParameterException("albumName`s length less then 128");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务

        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.addAlbum(name, expires);
        }

    }

    @Override
    public Map<String, Object> deleteAlbum(String albumId, Long expires) {
        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();
        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.deleteAlbum(albumId, expires);
        }
    }

    @Override
    public Map<String, Object> addFile2Album(String fileId, String albumId, Long expires) {
        if (!StringUtils.hasText(fileId)) {
            throw new ParameterException("fileId could not be null");
        }

        if (!StringUtils.hasText(albumId)) {
            throw new ParameterException("albumId could not be null");
        }
        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();

        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.addFile2Album(fileId, albumId, expires);
        }
    }

    @Override
    public Map<String, Object> deleteFileFromAlbum(String fileId, String albumId, Long expires) {
        if (!StringUtils.hasText(fileId)) {
            throw new ParameterException("fileId could not be null");
        }

        if (!StringUtils.hasText(albumId)) {
            throw new ParameterException("albumId could not be null");
        }
        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        String appName = enableCloudVodConfig.getAppName();

        if (AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            return null;
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = CloudVodFactory.getBaoFengCloudVodManager(enableCloudVodConfig);
            return bfCloudVodManager.deleteFileFromAlbum(fileId, albumId, expires);
        }
    }

}
