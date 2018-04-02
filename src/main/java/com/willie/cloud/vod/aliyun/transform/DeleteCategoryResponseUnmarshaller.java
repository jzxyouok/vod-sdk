package com.willie.cloud.vod.aliyun.transform;

import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.resp.DeleteCategoryResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:20</p>
 */
public class DeleteCategoryResponseUnmarshaller {

    public static DeleteCategoryResponse unmarshall(DeleteCategoryResponse deleteCategoryResponse, UnmarshallerContext context) {
        deleteCategoryResponse.setRequestId(context.stringValue("DeleteCategoryResponse.RequestId"));
        return deleteCategoryResponse;
    }
}
