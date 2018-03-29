package com.willie.cloud.vod.tencent;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/29 15:10</p>
 */
public enum VideoInfo {
    /**
     * 视频基础信息
     */
    BASIC_INFO("basicInfo"),
    /**
     * 视频元信息
     */
    DRM("drm"),
    /**
     * 文件加密信息
     */
    TRANS_CODE_INFO("transcodeInfo"),
    /**
     * 视频转码结果信息
     */
    SAMPLE_SNAPSHOT_INFO("sampleSnapshotInfo"),
    /**
     * 采样截图信息
     */
    META_DATA("metaData"),
    /**
     * 视频雪碧图信息
     */
    IMAGE_SPRITE_INFO("imageSpriteInfo"),
    /**
     * 指定时间点截图信息
     */
    SNAPSHOT_BY_TIME_OFFSET_INFO("snapshotByTimeOffsetInfo"),
    /**
     * 视频关键帧描述（打点）信息
     */
    KEY_FRAME_DESC_INFO("keyFrameDescInfo");

    private String name;

    VideoInfo(String name) {
        this.name = name;
    }
}
