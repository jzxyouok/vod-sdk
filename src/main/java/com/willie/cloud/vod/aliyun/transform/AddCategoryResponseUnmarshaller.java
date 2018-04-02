package com.willie.cloud.vod.aliyun.transform;

import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.Category;
import com.willie.cloud.vod.aliyun.resp.AddCategoryResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 15:35</p>
 */
public class AddCategoryResponseUnmarshaller {
    public AddCategoryResponseUnmarshaller() {
    }

    public static AddCategoryResponse unmarshall(AddCategoryResponse addCategoryResponse, UnmarshallerContext context) {
        addCategoryResponse.setRequestId(context.stringValue("AddCategoryResponse.RequestId"));
        Category category = new Category();
        category.setCateId(context.longValue("AddCategoryResponse.Category.CateId"));
        category.setCateName(context.stringValue("AddCategoryResponse.Category.CateName"));
        category.setParentId(context.longValue("AddCategoryResponse.Category.ParentId"));
        category.setLevel(context.longValue("AddCategoryResponse.Category.Level"));
        addCategoryResponse.setCategory(category);
        return addCategoryResponse;
    }
}
