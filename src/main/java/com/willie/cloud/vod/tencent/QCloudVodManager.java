package com.willie.cloud.vod.tencent;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.vod.VodApi;
import com.qcloud.vod.response.VodUploadCommitResponse;
import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.tencent.api.QCloudFileOpertation;

import java.util.Map;

/**
 * <p>功能 描述:腾讯云点播管理</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 16:32</p>
 */
public class QCloudVodManager extends CloudVodManager implements QCloudFileOpertation {

    private static volatile QCloudVodManager qCloudVodManager = null;

    private QCloudVodManager() {
    }

    @Override
    public Map<String, Object> uploadFile2Server(String videoName, Long expires) throws Exception {
        int tokenExpiresTime = (int) getExpires(expires);//token有效期
        VodApi vodApi = new VodApi(accessKey, secretKey, tokenExpiresTime);
        /**
         * VodApi
         *  <li>upload(String videoPath)  简单上传</li>
         *  <li>upload(String videoPath, String coverPath) 带封面上传</li>
         *  <>liupload(String videoPath, String coverPath, String procedure)封面和任务流</li>
         *  任务流：
         *      <p>将单个视频的处理过程划分为一系列串行的阶段</p>
         * */
        VodUploadCommitResponse vodResponse = vodApi.upload(videoName);//上传video
        JSONObject vodResponseJSON = (JSONObject) JSONObject.toJSON(vodResponse);
        logger.info("文件上传响应信息:info{}", vodResponseJSON);
        return vodResponseJSON;
    }

    /**
     * 取得暴腾讯云点播管理的实例
     *
     * @param config 腾讯云vod相关配置信息
     * @return 腾讯云点播管理的实例
     */
    public static QCloudVodManager getQCloudVodManagerInstance(CloudVodConfig config) {
        if (null == qCloudVodManager) {
            synchronized (QCloudVodManager.class) {
                if (null == qCloudVodManager) {
                    qCloudVodManager = new QCloudVodManager();
                }
            }
            /*这里属性赋值放在双重校验外，主要防止反射生成的实例*/
            appId = config.getAppId();
            accessKey = config.getAccessKey();
            secretKey = config.getSecretKey();
            expires = config.getExpires();
        }
        return qCloudVodManager;
    }
}
