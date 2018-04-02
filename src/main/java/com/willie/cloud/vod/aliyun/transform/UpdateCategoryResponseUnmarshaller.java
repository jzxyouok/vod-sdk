package com.willie.cloud.vod.aliyun.transform;

import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.resp.UpdateCategoryResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:30</p>
 */
public class UpdateCategoryResponseUnmarshaller {

    public static UpdateCategoryResponse unmarshall(UpdateCategoryResponse updateCategoryResponse, UnmarshallerContext context) {
        updateCategoryResponse.setRequestId(context.stringValue("UpdateCategoryResponse.RequestId"));
        return updateCategoryResponse;
    }
}
