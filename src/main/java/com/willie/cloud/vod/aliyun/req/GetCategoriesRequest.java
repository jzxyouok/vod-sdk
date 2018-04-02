package com.willie.cloud.vod.aliyun.req;

import com.aliyuncs.RpcAcsRequest;
import com.willie.cloud.vod.aliyun.resp.GetCategoriesResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 16:37</p>
 */
public class GetCategoriesRequest extends RpcAcsRequest<GetCategoriesResponse> {
    private Long ownerId;
    private String resourceOwnerAccount;
    private Long resourceOwnerId;
    private String cateId;
    private Long pageNo;
    private Long pageSize;

    public GetCategoriesRequest() {
        super("vod", "2018-04-02", "GetCategories");
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

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
        this.putQueryParameter("PageNo", pageNo);
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        this.putQueryParameter("PageSize", pageSize);
    }

    @Override
    public Class<GetCategoriesResponse> getResponseClass() {
        return GetCategoriesResponse.class;
    }
}
