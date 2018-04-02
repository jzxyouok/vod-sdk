package com.willie.cloud.vod.aliyun.resp;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.transform.UnmarshallerContext;
import com.willie.cloud.vod.aliyun.transform.UpdateCategoryResponseUnmarshaller;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:29</p>
 */
public class UpdateCategoryResponse extends AcsResponse {
    private String requestId;

    public UpdateCategoryResponse() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public AcsResponse getInstance(UnmarshallerContext context) throws ClientException, ServerException {
        return UpdateCategoryResponseUnmarshaller.unmarshall(this, context);
    }
}
