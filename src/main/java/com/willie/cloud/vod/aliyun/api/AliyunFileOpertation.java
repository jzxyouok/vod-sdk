package com.willie.cloud.vod.aliyun.api;

import com.aliyuncs.exceptions.ClientException;

import java.io.UnsupportedEncodingException;
import java.util.List;
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

    /**
     * 删除视频，并且支持批量删除
     *
     * @param videoIds 视频id
     * @return RequestId 请求ID
     * @throws ClientException
     */
    Map<String, Object> deleteFileFormServer(List<String> videoIds) throws ClientException;

    /**
     * 修改视频信息
     *
     * @param videoId 视频id
     * @param title   视频标题
     * @param cateId  分类id
     * @return RequestId 请求ID
     */
    Map<String, Object> updateVideoInfo(String videoId, String title, String cateId) throws ClientException, UnsupportedEncodingException;

    /**
     * 通过视频ID获取视频的基本信息，包括：视频标题、描述、时长、封面URL、状态、创建时间、大小、截图、分类和标签等信息。
     *
     * @param ids 视频id
     * @return RequestId 请求ID， Video 视频信息
     */
    Map<String, Object> getVideoInfo(List<String> ids) throws ClientException;

    /**
     * 获取视频信息列表
     *
     * @param cateId
     * @return RequestId 请求ID,VideoList 视频信息列表,Total 视频总条数
     */
    Map<String, Object> getVideoListRequest(String cateId) throws ClientException;
}
