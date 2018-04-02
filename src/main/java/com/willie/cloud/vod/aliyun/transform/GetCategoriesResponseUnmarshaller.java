package com.willie.cloud.vod.aliyun.transform;

import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.Category;
import com.willie.cloud.vod.aliyun.resp.GetCategoriesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:38</p>
 */
public class GetCategoriesResponseUnmarshaller {
    public static GetCategoriesResponse unmarshall(GetCategoriesResponse getCategoriesResponse, UnmarshallerContext context) {
        getCategoriesResponse.setRequestId(context.stringValue("GetCategoriesResponse.RequestId"));
        List<Category> categoryList = new ArrayList();

        Category category = new Category();
        category.setCateId(context.longValue("GetCategoriesResponse.Category.CateId"));
        category.setCateId(context.longValue("GetCategoriesResponse.Category.CateId"));
        category.setCateName(context.stringValue("GetCategoriesResponse.Category.CateName"));
        category.setParentId(context.longValue("GetCategoriesResponse.Category.ParentId"));
        category.setLevel(context.longValue("GetCategoriesResponse.Category.Level"));
        getCategoriesResponse.setCategory(category);

        getCategoriesResponse.setSubTotal(context.longValue("GetCategoriesResponse.SubTotal"));

        for (int i = 0; i < context.lengthValue("GetCategoriesResponse.SubCategories.Length"); ++i) {
            Category cate = new Category();
            cate.setCateId(context.longValue("GetCategoriesResponse.SubCategories[" + i + "].CateId"));
            cate.setCateId(context.longValue("GetCategoriesResponse.SubCategories[" + i + "].CateId"));
            cate.setCateName(context.stringValue("GetCategoriesResponse.SubCategories[" + i + "].CateName"));
            cate.setParentId(context.longValue("GetCategoriesResponse.SubCategories[" + i + "].ParentId"));
            cate.setLevel(context.longValue("GetCategoriesResponse.SubCategories[" + i + "].Level"));
            categoryList.add(cate);
        }

        getCategoriesResponse.setSubCategories(categoryList);
        return getCategoriesResponse;
    }
}
