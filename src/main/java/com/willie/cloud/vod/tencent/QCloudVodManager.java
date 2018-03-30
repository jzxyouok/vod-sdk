package com.willie.cloud.vod.tencent;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.Common.Request;
import com.qcloud.vod.VodApi;
import com.qcloud.vod.response.VodUploadCommitResponse;
import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.constent.vod.Vod;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.tencent.api.QCloudCategory;
import com.willie.cloud.vod.tencent.api.QCloudFileOpertation;
import com.willie.cloud.vod.util.http.RequestMethod;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>功能 描述:腾讯云点播管理</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 16:32</p>
 */
public class QCloudVodManager extends CloudVodManager implements QCloudFileOpertation, QCloudCategory {

    private static volatile QCloudVodManager qCloudVodManager = null;

    private QCloudVodManager() {
    }

    @Override
    public Map<String, Object> getVideoInfo(String fileId, List<VideoInfo> infoFilter) {
        Assert.hasText(fileId, "fileId could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudFileOpertation.GET_VIDEO_INFO);//查询视频信息
        params.put("fileId", fileId);
        if (!CollectionUtils.isEmpty(infoFilter)) {
            final int[] index = {0};
            infoFilter.forEach(info -> params.put("infoFilter." + (index[0]++), info.name()));
        }

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> uploadFile2Server(String videoName, Long expires) throws Exception {
        Assert.hasText(videoName, "coverName could not be null");

        int tokenExpiresTime = (int) getExpires(expires);//token有效期
        VodApi vodApi = new VodApi(accessKey, secretKey, tokenExpiresTime);
        /*
          VodApi
           <li>upload(String videoPath)  简单上传</li>
           <li>upload(String videoPath, String coverPath) 带封面上传</li>
           <>liupload(String videoPath, String coverPath, String procedure)封面和任务流</li>
           任务流：
               <p>将单个视频的处理过程划分为一系列串行的阶段</p>
          */
        VodUploadCommitResponse vodResponse = vodApi.upload(videoName);//上传video
        Map vodResponseInfo = (Map) JSONObject.toJSON(vodResponse);
        logger.info("文件上传响应信息:info{}", vodResponseInfo);
        return vodResponseInfo;
    }

    @Override
    public Map<String, Object> uploadFile2Server(String videoName, String coverName, Long expires) throws Exception {
        Assert.hasText(videoName, "videoName could not be null");
        Assert.hasText(coverName, "coverName could not be null");

        int tokenExpiresTime = (int) getExpires(expires);//token有效期
        VodApi vodApi = new VodApi(accessKey, secretKey, tokenExpiresTime);
        //procedure参数 :QCVB_SimpleProcessFile(1, 1) 使用控制台默认转码、水印参数进行转码
        VodUploadCommitResponse vodResponse = vodApi.upload(videoName, coverName, "QCVB_SimpleProcessFile(1, 1)");
        JSONObject vodResponseInfo = (JSONObject) JSONObject.toJSON(vodResponse);
        logger.info("文件上传响应信息:info{}", vodResponseInfo);
        return vodResponseInfo;
    }

    @Override
    public Map<String, Object> deleteFileFormServer(String fileId, int isFlushCdn, Integer priority) {
        Assert.hasText(fileId, "fileId could not be null");
        Assert.notNull(priority, "priority could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudFileOpertation.DELETE_METHOD);//删除
        params.put("isFlushCdn", isFlushCdn);
        params.put("fileId", fileId);
        params.put("priority", priority);

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> convertVodFile(String fileId, Integer isScreenshot, Integer isWatermark) {
        Assert.hasText(fileId, "fileId could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudFileOpertation.CONVERT_VOD_FILE);//转码
        params.put("fileId", fileId);

        if (null != isScreenshot) {
            params.put("isScreenshot", isScreenshot);
        }

        if (null != isWatermark) {
            params.put("isWatermark", isWatermark);
        }

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> modifyVodInfo(String fileId, String fileName, String fileIntro, String classId, String expireTime) {
        Assert.hasText(fileId, "fileId could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudFileOpertation.MODIFY_VOD_INFO);//修改视频文件
        params.put("fileId", fileId);

        if (StringUtils.hasText(fileName)) {
            params.put("fileName", fileName);
        }

        if (StringUtils.hasText(fileIntro)) {
            params.put("fileIntro", fileIntro);
        }

        if (StringUtils.hasText(classId)) {
            params.put("classId", classId);
        }

        if (StringUtils.hasText(expireTime)) {
            params.put("expireTime", expireTime);
        }

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> addCategory(String className, String parentId) {
        Assert.hasText(className, "className could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudCategory.CREATE_CLASS);//增加分类
        params.put("className", className);
        if (StringUtils.hasText(parentId)) {
            params.put("parentId", parentId);
        }

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> getAllCategorys() {
        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudCategory.DESCRIBE_ALL_CLASS);//当前用户所有的分类层级关系

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> getCategoryInfo() {
        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudCategory.DESCRIBE_CLASS);//分类的具体信息

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> ModifyCategory(String classId, String className) {
        Assert.hasText(classId, "classId could not be null");
        Assert.hasText(className, "className could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudCategory.MODIFY_CLASS);//分类的具体信息
        params.put("classId", classId);
        params.put("className", className);

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    @Override
    public Map<String, Object> deleteCategory(String classId) {
        Assert.hasText(classId, "classId could not be null");

        TreeMap<String, Object> params = getParams();//请求参数
        params.put("Action", QCloudCategory.DELETE_CLASS);//分类的具体信息
        params.put("classId", classId);

        String url = Request.generateUrl(params, accessKey, secretKey, RequestMethod.GET.name(), Vod.QCloudConstent.API_URL, Vod.QCloudConstent.SERVER_URI);
        String resultInfo = Request.sendRequest(url, params, RequestMethod.GET.name(), null);//发送请求
        return JSONObject.parseObject(resultInfo);
    }

    /**
     * 取得请求参数
     *
     * @return 请求参数
     */
    private TreeMap<String, Object> getParams() {
        TreeMap<String, Object> params = new TreeMap<>();//请求参数
        params.put("RequestMethod", RequestMethod.GET.name());
        params.put("Region", "");
        return params;
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
