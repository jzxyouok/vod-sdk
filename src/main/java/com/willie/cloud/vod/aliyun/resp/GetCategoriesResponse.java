package com.willie.cloud.vod.aliyun.resp;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.Category;
import com.willie.cloud.vod.aliyun.transform.GetCategoriesResponseUnmarshaller;

import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:38</p>
 */
public class GetCategoriesResponse extends AcsResponse {
    private String requestId;
    private Category category;
    private Long subTotal;
    private List<Category> subCategories;

    public GetCategoriesResponse() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public GetCategoriesResponse getInstance(UnmarshallerContext context) throws ClientException, ServerException {
        return GetCategoriesResponseUnmarshaller.unmarshall(this, context);
    }

}
