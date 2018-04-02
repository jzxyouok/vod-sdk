package com.willie.cloud.vod.aliyun.req;

import com.aliyuncs.RpcAcsRequest;
import com.willie.cloud.vod.aliyun.resp.DeleteCategoryResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:18</p>
 */
public class DeleteCategoryRequest extends RpcAcsRequest<DeleteCategoryResponse> {
    private Long ownerId;
    private String resourceOwnerAccount;
    private Long resourceOwnerId;
    private String cateId;

    public DeleteCategoryRequest() {
        super("vod", "2018-04-02", "DeleteCategory");
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        this.putQueryParameter("OwnerId", ownerId);
    }

    public String getResourceOwnerAccount() {
        return resourceOwnerAccount;
    }

    public void setResourceOwnerAccount(String resourceOwnerAccount) {
        this.resourceOwnerAccount = resourceOwnerAccount;
        this.putQueryParameter("ResourceOwnerAccount", resourceOwnerAccount);
    }

    public Long getResourceOwnerId() {
        return resourceOwnerId;
    }

    public void setResourceOwnerId(Long resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
        this.putQueryParameter("ResourceOwnerId", resourceOwnerId);
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
        this.putQueryParameter("CateId", cateId);
    }

    @Override
    public Class<DeleteCategoryResponse> getResponseClass() {
        return DeleteCategoryResponse.class;
    }
}
