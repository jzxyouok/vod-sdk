package com.willie.cloud.vod.aliyun.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.UploadVideo;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/30 11:13</p>
 */
public class AliUploadVideoUtil {

    /**
     * 本地视频上传
     *
     * @param request 本地上传请求
     * @return
     */
    public static Map<String, Object> uploadVideo(UploadVideoRequest request) {
        UploadVideo uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        JSONObject resultInfo = (JSONObject) JSONObject.toJSON(response);
        return resultInfo;
    }

    /**
     * 网络流上传
     *
     * @param request 网络流请求
     * @return
     */
    public static Map<String, Object> uploadURLStream(UploadURLStreamRequest request) {
        UploadVideo uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        JSONObject resultInfo = (JSONObject) JSONObject.toJSON(response);
        return resultInfo;
    }
}
