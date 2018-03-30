package com.willie.cloud.vod.aliyun;

import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.aliyun.api.AliyunFileOpertation;
import com.willie.cloud.vod.aliyun.util.AliRequestUtil;
import com.willie.cloud.vod.aliyun.util.AliUploadVideoUtil;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 14:50</p>
 */
public class AliyunVodManager extends CloudVodManager implements AliyunFileOpertation {
    private static volatile AliyunVodManager aliyunVodManager = null;

    private AliyunVodManager() {
    }

    @Override
    public Map<String, Object> uoloadFile2Server(String title, String fileName) {
        Assert.hasText(title, "title could not be null");
        Assert.hasText(title, "fileName could not be null");

        //参数
        Map<String, String> params = new HashMap<>();
        params.put("accessKey", accessKey);
        params.put("secretKey", secretKey);
        params.put("title", title);
        params.put("fileName", fileName);

        UploadVideoRequest request = AliRequestUtil.getUploadVideoRequest(params);
        return AliUploadVideoUtil.uploadVideo(request);
    }

    @Override
    public Map<String, Object> uoloadFile2Server(String title, String fileName, String url) {
        Assert.hasText(title, "title could not be null");
        Assert.hasText(title, "fileName could not be null");
        Assert.hasText(url, "url could not be null");

        //参数
        Map<String, String> params = new HashMap<>();
        params.put("accessKey", accessKey);
        params.put("secretKey", secretKey);
        params.put("title", title);
        params.put("fileName", fileName);
        params.put("url", url);

        UploadURLStreamRequest request = AliRequestUtil.getUploadURLStreamRequest(params);
        return AliUploadVideoUtil.uploadURLStream(request);
    }

    /**
     * 取得阿里云点播管理
     *
     * @param cloudVodConfig 阿里云vod相关配置信息
     * @return 阿里云点播管理
     */
    public static AliyunVodManager getAliyunCloudVodManagerInstance(CloudVodConfig cloudVodConfig) {
        if (null == aliyunVodManager) {
            synchronized (AliyunVodManager.class) {
                if (null == aliyunVodManager) {
                    aliyunVodManager = new AliyunVodManager();
                }
            }
            appId = cloudVodConfig.getAppId();
            accessKey = cloudVodConfig.getAccessKey();
            secretKey = cloudVodConfig.getSecretKey();
            expires = cloudVodConfig.getExpires();
        }
        return aliyunVodManager;
    }
}
