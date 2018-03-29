package com.willie.cloud.vod.tencent.api;

import com.willie.cloud.vod.tencent.VideoInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 16:56</p>
 */
public interface QCloudFileOpertation {
    String DELETE_METHOD = "DeleteVodFile";//删除视频
    String CONVERT_VOD_FILE = "ConvertVodFile";//转码
    String GET_VIDEO_INFO = "GetVideoInfo";//获取视频信息
    String MODIFY_VOD_INFO = "ModifyVodInfo";//修改视视频属性

    /**
     * 该接口可以获取单个视频的多种信息，包括：
     * <li>基础信息（basicInfo）：包括视频名称、大小、时长、封面图片等；</li>
     * <li>元信息（metaData）：包括视频流信息、音频流信息等；</li>
     * <li>加密信息（drm）：对视频加密之后，相关的加密信息；</li>
     * <li>转码结果信息（transcodeInfo）：包括该视频转码生成的各种码率的视频的地址、规格、码率、分辨率等；</li>
     * <li>采样截图信息（sampleSnapshotInfo）：对视频采样截图后，相关截图信息；</li>
     * <li>雪碧图信息（imageSpriteInfo）：对视频截取雪碧图之后，雪碧的相关信息；</li>
     * <li>指定时间点截图信息（snapshotByTimeOffsetInfo）：对视频依照指定时间点截图后，各个截图的信息。</li>
     * <li>视频打点信息（keyFrameDescInfo）：对视频设置的各个打点信息。</li>
     *
     * @param fileId     视频的ID
     * @param infoFilter 指定需要返回的信息，可同时指定多个信息，n 从0开始递增。如果未填写该字段，默认返回所有信息。备选项：basicInfo（基础信息）、元信息（metaData）、加密信息（drm）、transcodeInfo（转码结果信息）、imageSpriteInfo（雪碧图信息）、snapshotByTimeOffsetInfo（指定时间点截图信息）、sampleSnapshotInfo（采样截图信息）、keyFrameDescInfo(视频打点信息)。
     *                   示例：https://vod.api.qcloud.com/v2/index.php?Action=GetVideoInfo&fileId=12345&infoFilter.0=basicInfo&infoFilter.1=transcodeInfo&COMMON_PARAMS
     * @return 视频信息
     */
    Map<String, Object> getVideoInfo(String fileId, List<VideoInfo> infoFilter);

    /**
     * 简单上传
     *
     * @param videoName 文件名称
     * @param expires   token有效期
     * @return code为0表示成功，否则失败
     */
    Map<String, Object> uploadFile2Server(String videoName, Long expires) throws Exception;

    /**
     * 上传后自动转码
     *
     * @param videoName 文件名称
     * @param coverName 封面名
     * @param expires   token有效期
     * @return code为0表示成功，否则失败
     */
    Map<String, Object> uploadFile2Server(String videoName, String coverName, Long expires) throws Exception;

    /**
     * 根据文件id从服务器删除文件
     *
     * @param fileId     文件id
     * @param isFlushCdn 删除文件时是否及时刷新cdn缓存文件；默认不刷新，指定为1时刷新
     * @param priority   可填0；优先级0:中 1：高 2：低
     * @return code 错误码, 0: 成功, 其他值: 失败
     */
    Map<String, Object> deleteFileFormServer(String fileId, int isFlushCdn, Integer priority);

    /**
     * 依照控制台中的转码配置，对视频文件进行转码
     *
     * @param fileId       文件id
     * @param isScreenshot 是否截图：0，不需要；1，需要
     * @param isWatermark  是否添加水印：0：不需要；1，需要
     * @return code         错误码, 0: 成功；其他值: 失败
     */
    Map<String, Object> convertVodFile(String fileId, Integer isScreenshot, Integer isWatermark);

    /**
     * 修改视频文件的描述信息，包括分类、名称、描述等
     *
     * @param fileId     文件id
     * @param fileName   文件名称
     * @param fileIntro  文件描述
     * @param classId    分类id
     * @param expireTime 视频过期时间，格式: Y-m-d H:i:s，如2017-10-01 00:00:00。视频过期之后，该视频及其所有附属对象（转码结果、雪碧图等）将都被删除
     * @return
     */
    Map<String, Object> modifyVodInfo(String fileId, String fileName, String fileIntro, String classId, String expireTime);
}
