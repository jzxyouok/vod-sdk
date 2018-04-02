package com.willie.cloud.vod.aliyun.req;

import com.aliyuncs.RpcAcsRequest;
import com.willie.cloud.vod.aliyun.resp.AddCategoryResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 15:26</p>
 */
public class AddCategoryRequest extends RpcAcsRequest<AddCategoryResponse> {
    private Long ownerId;
    private String resourceOwnerAccount;
    private Long resourceOwnerId;
    private String cateName;
    private String parentId;

    public AddCategoryRequest() {
        super("vod", "2018-04-02", "AddCategory");
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

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
        this.putQueryParameter("CateName", cateName);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
        this.putQueryParameter("ParentId", parentId);
    }

    @Override
    public Class<AddCategoryResponse> getResponseClass() {
        return AddCategoryResponse.class;
    }
}
