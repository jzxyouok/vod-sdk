package com.willie.cloud.vod.service.vod.impl;

import com.alibaba.fastjson.JSONObject;
import com.willie.cloud.vod.bfcloud.BFCloudVodManager;
import com.willie.cloud.vod.bfcloud.api.BFCloudAlbum;
import com.willie.cloud.vod.bfcloud.api.BFCloudCategory;
import com.willie.cloud.vod.constent.vod.Vod;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.exception.ParameterException;
import com.willie.cloud.vod.factory.AbstractCloudVodMangerFactory;
import com.willie.cloud.vod.factory.CloudVodManagerFactory;
import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;
import com.willie.cloud.vod.service.vod.CloudVodService;
import com.willie.cloud.vod.service.vod.CloudVodUpdateService;
import com.willie.cloud.vod.tencent.QCloudVodManager;
import com.willie.cloud.vod.util.Charset;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:52</p>
 */
@Service
public class CloudVodUpdateServiceImpl extends CloudVodService implements CloudVodUpdateService {

    @Override
    public Map<String, Object> uploadFile2Server(String videoName, Integer expires) throws Exception {
        if (!StringUtils.hasText(videoName)) {
            throw new ParameterException("videoName could not be null");
        }

        String name = videoName.substring(videoName.lastIndexOf("\\") + 1, videoName.indexOf("."));//取得文件名
        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());

        if (null != expires && 0 < expires) {
            enableCloudVodConfig.setExpires(expires);
        }

        String appName = enableCloudVodConfig.getAppName();
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            Video qVideo = new Video();
            qVideo.setVideoName(name);
            qVideo.setAppId(enableCloudVodConfig.getAppId());
            Video newVideo = videoRepository.save(qVideo);

            QCloudVodManager qCloudVodManager = cloudVodMangerFactory.getCloudVodManger(QCloudVodManager.class,
                    enableCloudVodConfig);
            Map<String, Object> resultInfo = qCloudVodManager.uploadFile2Server(videoName,
                    null);//上传video
            JSONObject resultJsonInfo = (JSONObject) resultInfo;
            if (0 == resultJsonInfo.getIntValue("code")) {
                newVideo.setUploadDate(new Timestamp(new Date().getTime()));
                JSONObject videoInfo = resultJsonInfo.getJSONObject("video");
                newVideo.setVideoRemotePath(videoInfo.getString("url"));
                newVideo.setVideoId(resultJsonInfo.getString("fileId"));
                videoRepository.saveAndFlush(newVideo);
            }
            return null;
        } else {//暴风云服务
            return null;
        }
    }

    @Override
    public Map<String, Object> deleteFile(String fileId, Long expires) {
        if (!StringUtils.hasText(fileId)) {
            throw new ParameterException("fileId could not be null");
        }
        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());
        String appName = enableCloudVodConfig.getAppName();
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            QCloudVodManager qCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(QCloudVodManager.class, enableCloudVodConfig);
            return qCloudVodManager.deleteFileFormServer(fileId, 1, 0);//删除文件并刷新cdn
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
            return bfCloudVodManager.deleteFile(fileId, expires);
        }
    }

    //TODO 稍后需要加入子分类，最多2级分类，分类个数最多200个的验证
    @Override
    public Map<String, Object> addCategory(String name, String parentCategoryId, Long expires) throws UnsupportedEncodingException {
        if (!StringUtils.hasText(name)) {
            throw new ParameterException("categoryName could not be null");
        }

        if (BFCloudCategory.CATEGORY_NAME_MAXLENGTH_BIT < name.getBytes(Charset.UTF8).length) {
            throw new ParameterException("categoryName`s length less then 128");
        }

        CloudVodConfig enableCloudVodConfig = getEnableCloudVodManager();//可用点播服务
        logger.info("可用点播服务名称:{}", enableCloudVodConfig.getAppName());
        String appName = enableCloudVodConfig.getAppName();
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            QCloudVodManager qCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(QCloudVodManager.class, enableCloudVodConfig);
            return qCloudVodManager.addCategory(name, parentCategoryId);
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
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
        cloudVodMangerFactory = new CloudVodManagerFactory();

        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            QCloudVodManager qCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(QCloudVodManager.class, enableCloudVodConfig);
            return qCloudVodManager.deleteCategory(categoryId);
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager =
                    cloudVodMangerFactory.getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
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
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            QCloudVodManager qCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(QCloudVodManager.class, enableCloudVodConfig);
            return qCloudVodManager.modifyVodInfo(fileId, null, null, categoryId, null);
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
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
        AbstractCloudVodMangerFactory cloudVodMangerFactory = new
                CloudVodManagerFactory();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {//阿里云服务
            return null;
        } else if (Vod.QCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {//腾讯云服务
            QCloudVodManager qCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(QCloudVodManager.class,
                            enableCloudVodConfig);
            return qCloudVodManager.modifyVodInfo(fileId, null, null, null, null);//删除分类中的文件
        } else {//暴风云服务
            BFCloudVodManager bfCloudVodManager = cloudVodMangerFactory
                    .getCloudVodManger(BFCloudVodManager.class,
                            enableCloudVodConfig);
            return bfCloudVodManager.deleteFileFromCategory(categoryId, fileId, expires);
        }
    }

    //TODO 专辑个数最多200个验证
    @Override
    public Map<String, Object> addAlbum(String name, Long expires) throws UnsupportedEncodingException {
        if (!StringUtils.hasText(name)) {
            throw new ParameterException("albumName could not be null");
        }

        if (BFCloudAlbum.ALBUM_NAMEL_MAXLENGTH_BIT < name.getBytes(Charset.UTF8).length) {
            throw new ParameterException("albumName`s length less then 128");
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
            return bfCloudVodManager.addAlbum(name, expires);
        }

    }

    @Override
    public Map<String, Object> deleteAlbum(String albumId, Long expires) {
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
            return bfCloudVodManager.deleteFileFromAlbum(fileId, albumId, expires);
        }
    }

    @Override
    public CloudVodConfigRepository getCloudVodConfigRepository() {
        return super.getCloudVodConfigRepository();
    }

}
