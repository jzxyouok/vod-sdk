package com.willie.cloud.vod.aliyun.util;

import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoListRequest;
import com.aliyuncs.vod.model.v20170321.UpdateVideoInfoRequest;
import com.willie.cloud.vod.aliyun.req.AddCategoryRequest;
import com.willie.cloud.vod.aliyun.req.DeleteCategoryRequest;
import com.willie.cloud.vod.aliyun.req.GetCategoriesRequest;
import com.willie.cloud.vod.aliyun.req.UpdateCategoryRequest;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/30 10:46</p>
 */
public abstract class AliRequestUtil {
    private static final long DEFAULT_PART_SIZE = 10 * 1024 * 1024L;//分片上传时每个分片的大小，默认为10M字节
    private static final int DEFAULT_TASK_NUM = 1;//分片上传时的并发线程数，默认为1

    /**
     * 取得网路流上传请求
     *
     * @param params 请求参数
     * @return 网路流上传请求
     */
    public static UploadURLStreamRequest getUploadURLStreamRequest(Map<String, String> params) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(params.get("accessKey"), params.get("secretKey"), params.get("title"), params.get("fileName"), params.get("url"));
        request.setShowWaterMark(true);  //是否使用默认水印

        if (params.containsKey("callback")) {
            request.setCallback(params.get("callback"));  //设置上传完成后的回调URL(可选)
        }

        if (params.containsKey("cateId")) {
            request.setCateId(Integer.parseInt(params.get("cateId")));  //视频分类ID(可选)
        }

        if (params.containsKey("tags")) {
            request.setTags(params.get("tags"));  //视频标签,多个用逗号分隔(可选)
        }

        if (params.containsKey("description")) {
            request.setDescription(params.get("description"));  //视频描述(可选)
        }

        if (params.containsKey("coverURL")) {
            request.setCoverURL(params.get("coverURL")); //封面图片(可选)
        }
        return request;
    }

    /**
     * 取得上传请求
     *
     * @param params 请求参数
     * @return 上传请求
     */
    public static UploadVideoRequest getUploadVideoRequest(Map<String, String> params) {
        UploadVideoRequest request = new UploadVideoRequest(params.get("accessKey"), params.get("secretKey"), params.get("title"), params.get("fileName"));
        request.setIsShowWaterMark(true);           //是否使用默认水印

        if (!params.containsKey("partSize")) {
            request.setPartSize(DEFAULT_PART_SIZE); //分片的大小，默认为10M字节
        }

        if (!params.containsKey("taskNum")) {
            request.setTaskNum(DEFAULT_TASK_NUM); //分片上传时的并发线程数
        }

        if (params.containsKey("callback")) {
            request.setCallback(params.get("callback"));  //设置上传完成后的回调URL(可选)
        }

        if (params.containsKey("cateId")) {
            request.setCateId(Integer.parseInt(params.get("cateId")));  //视频分类ID(可选)
        }

        if (params.containsKey("tags")) {
            request.setTags(params.get("tags"));  //视频标签,多个用逗号分隔(可选)
        }

        if (params.containsKey("description")) {
            request.setDescription(params.get("description")); //视频描述(可选)
        }

        if (params.containsKey("coverURL")) {
            request.setCoverURL(params.get("coverURL")); //封面图片(可选)
        }

        return request;
    }

    /**
     * 删除请求
     *
     * @param videoIds 视频id
     * @return 删除请求
     */
    public static DeleteVideoRequest getDeleteVideoRequest(String videoIds) {
        DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
        deleteVideoRequest.setVideoIds(videoIds);
        return deleteVideoRequest;
    }

    /**
     * 取得视频信息请求
     *
     * @param videoIds 视频id
     * @return 视频信息请求
     */
    public static GetVideoInfoRequest getVideoInfoRequest(String videoIds) {
        GetVideoInfoRequest getVideoInfoRequest = new GetVideoInfoRequest();
        getVideoInfoRequest.setVideoId(videoIds);
        return getVideoInfoRequest;
    }

    /**
     * 取得修改视频信息请求
     *
     * @param videoId 视频id
     * @param title   视频id
     * @param cateId  分类id
     * @return 修改视频信息请求
     */
    public static UpdateVideoInfoRequest getUpdateVideoInfoRequest(String videoId, String title, String cateId) {
        UpdateVideoInfoRequest updateVideoInfoRequest = new UpdateVideoInfoRequest();
        updateVideoInfoRequest.setVideoId(videoId);
        updateVideoInfoRequest.setTitle(title);
        updateVideoInfoRequest.setCateId(Integer.valueOf(cateId));
        return updateVideoInfoRequest;
    }

    /**
     * 获取视频信息列表
     *
     * @param cateId 分类id
     * @return 视频信息列表
     */
    public static GetVideoListRequest getVideoListRequest(String cateId) {
        GetVideoListRequest getVideoListRequest = new GetVideoListRequest();
        if (StringUtils.hasText(cateId)) {
            getVideoListRequest.setCateId(Integer.valueOf(cateId));
        }
        return getVideoListRequest;
    }

    /**
     * 取得增加分类请求
     *
     * @param cateName 分类名称
     * @param parentId 父类id
     * @return 增加分类请求
     */
    public static AddCategoryRequest getAddCategoryRequest(String cateName, String parentId) {
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest();
        addCategoryRequest.setCateName(cateName);
        if (StringUtils.hasText(parentId)) {
            addCategoryRequest.setParentId(parentId);
        }
        return addCategoryRequest;
    }

    /**
     * 取得删除分类请求
     *
     * @param cateId 分类id
     * @return 删除分类请求
     */
    public static DeleteCategoryRequest getDeleteCategoryRequest(String cateId) {
        DeleteCategoryRequest deleteCategoryRequest = new DeleteCategoryRequest();
        deleteCategoryRequest.setCateId(cateId);
        return deleteCategoryRequest;
    }

    /**
     * 取得修改分类请求
     *
     * @param cateId   分类id
     * @param cateName 分类名称
     * @return 修改分类请求
     */
    public static UpdateCategoryRequest getUpdateCategoryRequest(String cateId, String cateName) {
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();
        updateCategoryRequest.setCateId(cateId);
        updateCategoryRequest.setCateName(cateName);
        return updateCategoryRequest;
    }

    /**
     * 取得获取分类信息请求
     *
     * @param cateId 分类id
     * @return 分类信息请求
     */
    public static GetCategoriesRequest getCategoriesRequest(String cateId) {
        GetCategoriesRequest getCategoriesRequest = new GetCategoriesRequest();
        if (StringUtils.hasText(cateId)) {
            getCategoriesRequest.setCateId(cateId);
        }
        return getCategoriesRequest;
    }
}
