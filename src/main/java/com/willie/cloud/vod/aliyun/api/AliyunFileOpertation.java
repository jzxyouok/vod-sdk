package com.willie.cloud.vod.aliyun.api;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 16:58</p>
 */
public interface AliyunFileOpertation {

    /**
     * 本地文件上传
     *
     * @param title    视频标题
     * @param fileName 文件名
     * @return
     */
    Map<String, Object> uoloadFile2Server(String title, String fileName);

    /**
     * 网络流上传文件接口
     *
     * @param title    视频标题
     * @param fileName 文件名
     * @param url      待上传视频的网络流地址
     * @return
     */
    Map<String, Object> uoloadFile2Server(String title, String fileName, String url);
}
