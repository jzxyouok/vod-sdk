package com.willie.cloud.vod.aliyun.util;

import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/30 10:46</p>
 */
public class AliRequestUtil {
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
        request.setShowWaterMark(true);             //是否使用默认水印

        if (params.containsKey("callback")) {
            request.setCallback(params.get("callback"));  //设置上传完成后的回调URL(可选)
        }

        if (params.containsKey("cateId")) {
            request.setCateId(Integer.parseInt(params.get("cateId")));  //视频分类ID(可选)
        }

        if (params.containsKey("tags")) {
            request.setTags(params.get("tags"));        //视频标签,多个用逗号分隔(可选)
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
            request.setPartSize(DEFAULT_PART_SIZE);     //分片的大小，默认为10M字节
        }

        if (!params.containsKey("taskNum")) {
            request.setTaskNum(DEFAULT_TASK_NUM);      //分片上传时的并发线程数
        }

        if (params.containsKey("callback")) {
            request.setCallback(params.get("callback"));  //设置上传完成后的回调URL(可选)
        }

        if (params.containsKey("cateId")) {
            request.setCateId(Integer.parseInt(params.get("cateId")));  //视频分类ID(可选)
        }

        if (params.containsKey("tags")) {
            request.setTags(params.get("tags"));              //视频标签,多个用逗号分隔(可选)
        }

        if (params.containsKey("description")) {
            request.setDescription(params.get("description"));          //视频描述(可选)
        }

        if (params.containsKey("coverURL")) {
            request.setCoverURL(params.get("coverURL")); //封面图片(可选)
        }

        return request;
    }

}
