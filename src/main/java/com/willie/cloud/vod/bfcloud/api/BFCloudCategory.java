package com.willie.cloud.vod.bfcloud.api;

import java.util.Map;

/**
 * <p>功能 描述:暴风云分类接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 14:55</p>
 */
public interface BFCloudCategory {
    String ADD_CATEGORY = "/api/addcategory";
    String DELETE_CATEGORY = "/api/deletecategory";
    String ADD_FILE_2_CATEGORY = "/api/categoryaddfile";
    String DELETE_FILE_FROM_CATEGORY = "/api/categorydelfile";
    String GET_FILE_FROM_CATEGORY = "/api/getcategoryfile";
    long CATEGORY_NAME_MAXLENGTH_BIT = 128;//分类名称最大字节数

    /**
     * <li>接口名：/api/addcategory</li>
     * <li>请求方法：GET</li>
     * 添加一个分类，可以是子分类，最多2级分类，分类个数最多200个
     *
     * @param name             分类名称，最大128字节,必输参数
     * @param parentCategoryId 上级分类id，如果为空或id不存在，则认为是顶层分类
     * @param expires          token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败,categoryId 分类id，status为0时返回
     */
    Map<String, Object> addCategory(String name, String parentCategoryId, Long expires);

    /**
     * <li>接口名：/api/deletecategory</li>
     * <li>请求方法：GET</li>
     * 删除一个分类，如果不存在则返回0，如果包含子分类则会删除所有子分类
     *
     * @param categoryId 分类id,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteCategory(String categoryId, Long expires);

    /**
     * <li>接口名：/api/categoryaddfile</li>
     * <li>请求方法：GET</li>
     * 在分类中添加一个文件，如果分类不存在则添加失败
     *
     * @param categoryId 分类id,必输参数
     * @param fileId     32字节文件id，唯一标识符,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> addFile2Category(String categoryId, String fileId, Long expires);

    /**
     * <li>接口名：/api/categorydelfile</li>
     * <li>请求方法：GET</li>
     * 在分类中删除一个文件，如果分类不存在则删除失败
     *
     * @param categoryId 分类id,必输参数
     * @param fileId     32字节文件id，唯一标识符,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteFileFromCategory(String categoryId, String fileId, Long expires);

    /**
     * <li>接口名：/api/getcategoryfile</li>
     * <li>请求方法：GET</li>
     * 获取分类中的文件列表，最多1000个文件
     *
     * @param categoryId 分类id,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败；filelist 文件列表，status为0时返回
     */
    Map<String, Object> getFileFromCategory(String categoryId, Long expires);
}
