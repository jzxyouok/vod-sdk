package com.willie.cloud.vod.domain.category;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/4 9:54</p>
 */
@Entity
public class Category {
    private int categoryId;
    private String categoryName;
    private Integer videoId;
    private Integer categoryParentId;
    private Timestamp createDate;
    private Integer appId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_name", nullable = true, length = 100)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "video_id", nullable = true)
    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "category_parent_id", nullable = true)
    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "app_id", nullable = true)
    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(videoId, category.videoId) &&
                Objects.equals(categoryParentId, category.categoryParentId) &&
                Objects.equals(createDate, category.createDate) &&
                Objects.equals(appId, category.appId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryId, categoryName, videoId, categoryParentId, createDate, appId);
    }
}
