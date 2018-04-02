package com.willie.cloud.vod.aliyun.resp;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.Category;
import com.willie.cloud.vod.aliyun.transform.AddCategoryResponseUnmarshaller;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 15:27</p>
 */
public class AddCategoryResponse extends AcsResponse {
    private String requestId;
    private Category category;

    public AddCategoryResponse() {
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

    @Override
    public AddCategoryResponse getInstance(UnmarshallerContext context) throws ClientException, ServerException {
        return AddCategoryResponseUnmarshaller.unmarshall(this, context);
    }

}
