package com.willie.cloud.vod.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.aliyun.api.AliCloudCategory;
import com.willie.cloud.vod.aliyun.api.AliyunFileOpertation;
import com.willie.cloud.vod.aliyun.req.AddCategoryRequest;
import com.willie.cloud.vod.aliyun.req.DeleteCategoryRequest;
import com.willie.cloud.vod.aliyun.req.GetCategoriesRequest;
import com.willie.cloud.vod.aliyun.req.UpdateCategoryRequest;
import com.willie.cloud.vod.aliyun.resp.AddCategoryResponse;
import com.willie.cloud.vod.aliyun.resp.DeleteCategoryResponse;
import com.willie.cloud.vod.aliyun.resp.GetCategoriesResponse;
import com.willie.cloud.vod.aliyun.resp.UpdateCategoryResponse;
import com.willie.cloud.vod.aliyun.util.AliClient;
import com.willie.cloud.vod.aliyun.util.AliRequestUtil;
import com.willie.cloud.vod.aliyun.util.AliUploadVideoUtil;
import com.willie.cloud.vod.constent.charset.Charset;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.exception.ParameterException;
import com.willie.cloud.vod.util.string.ExtStringUtil;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/20 14:50</p>
 */
public class AliyunVodManager extends CloudVodManager implements AliyunFileOpertation, AliCloudCategory {
    private static volatile AliyunVodManager aliyunVodManager = null;

    private AliyunVodManager() {
    }

    @Override
    public Map<String, Object> uoloadFile2Server(String title, String fileName) {
        Assert.hasText(title, "fileName could not be null");

        if (!StringUtils.hasText(title)) {
            title = "未知视频";
        }

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
        Assert.hasText(title, "fileName could not be null");
        Assert.hasText(url, "url could not be null");

        if (!StringUtils.hasText(title)) {
            title = "未知视频";
        }

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

    @Override
    public Map<String, Object> deleteFileFormServer(List<String> videoIds) throws ClientException {
        Assert.notEmpty(videoIds, "videoIds could not be null");
        String vIds = ExtStringUtil.concatenateStringWithCommas(videoIds);//视频ids

        DeleteVideoRequest deleteVideoRequest = AliRequestUtil.getDeleteVideoRequest(vIds);//取得删除请求
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        DeleteVideoResponse deleteVideoResponse = client.getAcsResponse(deleteVideoRequest);//得到删除响应
        return (Map<String, Object>) JSONObject.toJSON(deleteVideoResponse);
    }

    @Override
    public Map<String, Object> getVideoInfo(List<String> videoIds) throws ClientException {
        Assert.notEmpty(videoIds, "videoIds could not be null");
        String vIds = ExtStringUtil.concatenateStringWithCommas(videoIds);//视频ids

        GetVideoInfoRequest getVideoInfoRequest = AliRequestUtil.getVideoInfoRequest(vIds);
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        GetVideoInfoResponse getVideoInfoResponse = client.getAcsResponse(getVideoInfoRequest);
        return (Map<String, Object>) JSONObject.toJSON(getVideoInfoResponse);
    }

    @Override
    public Map<String, Object> updateVideoInfo(String videoId, String title, String cateId) throws ClientException, UnsupportedEncodingException {
        Assert.hasText(videoId, "videoId could not be null");

        if (StringUtils.hasText(title) && title.getBytes(Charset.CHARSET).length > 128) {
            throw new ParameterException("title`s length should not be more then 128 bit");
        }

        UpdateVideoInfoRequest updateVideoInfoRequest = AliRequestUtil.getUpdateVideoInfoRequest(videoId, title, cateId);
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        UpdateVideoInfoResponse updateVideoInfoResponse = client.getAcsResponse(updateVideoInfoRequest);
        return (Map<String, Object>) JSONObject.toJSON(updateVideoInfoResponse);
    }

    @Override
    public Map<String, Object> getVideoListRequest(String cateId) throws ClientException {
        GetVideoListRequest getVideoListRequest = AliRequestUtil.getVideoListRequest(cateId);
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        GetVideoListResponse getVideoListResponse = client.getAcsResponse(getVideoListRequest);
        return (Map<String, Object>) JSONObject.toJSON(getVideoListResponse);
    }

    @Override
    public Map<String, Object> addCategory(String cateName, String parentId) throws UnsupportedEncodingException, ClientException {
        Assert.hasText(cateName, "cateName should not be null");

        byte[] cateNameByte = cateName.getBytes(Charset.CHARSET);
        if (cateNameByte.length > 64) {
            throw new ParameterException("cateName`s length should not be more then 64 bit");
        }

        AddCategoryRequest addCategoryRequest = AliRequestUtil.getAddCategoryRequest(new String(cateNameByte, Charset.CHARSET), parentId);
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        AddCategoryResponse addCategoryResponse = client.getAcsResponse(addCategoryRequest);
        return (Map<String, Object>) JSONObject.toJSON(addCategoryResponse);
    }

    @Override
    public Map<String, Object> deleteCategory(String cateId) throws ClientException {
        Assert.hasText(cateId, "cateId should not be null");

        DeleteCategoryRequest deleteCategoryRequest = AliRequestUtil.getDeleteCategoryRequest(cateId);
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        DeleteCategoryResponse deleteCategoryResponse = client.getAcsResponse(deleteCategoryRequest);
        return (Map<String, Object>) JSONObject.toJSON(deleteCategoryResponse);
    }

    @Override
    public Map<String, Object> updateCategory(String cateId, String cateName) throws UnsupportedEncodingException, ClientException {
        Assert.hasText(cateId, "cateId should not be null");
        Assert.hasText(cateName, "cateName should not be null");

        byte[] cateNameByte = cateName.getBytes(Charset.CHARSET);

        if (cateNameByte.length > 64) {
            throw new ParameterException(" cateName`s length should not be more then 64 bit ");
        }

        UpdateCategoryRequest updateCategoryRequest = AliRequestUtil.getUpdateCategoryRequest(cateId, new String(cateNameByte, Charset.CHARSET));
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        UpdateCategoryResponse updateCategoryResponse = client.getAcsResponse(updateCategoryRequest);
        return (Map<String, Object>) JSONObject.toJSON(updateCategoryResponse);
    }

    @Override
    public Map<String, Object> getCategories(String cateId) throws ClientException {
        GetCategoriesRequest getCategoriesRequest = AliRequestUtil.getCategoriesRequest(cateId);
        DefaultAcsClient client = AliClient.getDefaultAcsClient(accessKey, secretKey);//取得客户端
        GetCategoriesResponse getCategoriesResponse = client.getAcsResponse(getCategoriesRequest);
        return (Map<String, Object>) JSONObject.toJSON(getCategoriesResponse);
    }

    /**
     * 取得阿里云点播管理
     *
     * @param cloudVodConfig 阿里云vod相关配置信息
     * @return 阿里云点播管理
     */
    public static AliyunVodManager getAliyunCloudVodManagerInstance
    (CloudVodConfig cloudVodConfig) {
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
