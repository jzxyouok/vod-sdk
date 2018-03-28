package com.willie.cloud.vod.tencent.api;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 16:56</p>
 */
public interface FileOpertation {
    Map<String, Object> uploadFile2Server(String videoName, Integer expires);
}
