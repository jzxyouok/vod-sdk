package com.willie.cloud.vod.tencent.api;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>功能 描述:腾讯云分类管理接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/29 9:39</p>
 */
public interface QCloudCategory {


    JSONObject addCategory(String className, String parentId);
}
