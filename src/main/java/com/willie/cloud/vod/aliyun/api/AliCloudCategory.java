package com.willie.cloud.vod.aliyun.api;

import com.aliyuncs.exceptions.ClientException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 15:25</p>
 */
public interface AliCloudCategory {
    /**
     * 创建视频分类，最大支持三级分类，每个分类最多支持创建100个子分类
     *
     * @param cateName 分类名称。
     *                 <li>不能超过64个字节。</li>
     *                 <li>UTF8编码。</li>
     * @param parentId 父分类ID 若不填，则默认生成一级分类，根节点分类ID为-1
     * @return RequestId 请求ID,视频分类信息
     */
    Map<String, Object> addCategory(String cateName, String parentId) throws UnsupportedEncodingException, ClientException;

    /**
     * 删除视频分类
     *
     * @param cateId 分类ID
     * @return RequestId 请求ID
     */
    Map<String, Object> deleteCategory(String cateId) throws ClientException;

    /**
     * @param cateId   分类ID
     * @param cateName 分类名称。
     *                 <li>不能超过64个字节</li>
     *                 <li>UTF8编码</li>
     * @return
     */
    Map<String, Object> updateCategory(String cateId, String cateName) throws UnsupportedEncodingException, ClientException;

    /**
     * 获取指定的分类信息，及其子分类（即下一级分类）的列表。
     *
     * @param cateId 分类ID。
     *               默认为根节点分类ID，即-1
     * @return RequestId    请求ID。Category 指定分类的信息。SubTotal 子分类总数。SubCategories 子分类列表。
     */
    Map<String, Object> getCategories(String cateId) throws ClientException;
}
