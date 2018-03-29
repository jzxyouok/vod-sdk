package com.willie.cloud.vod.tencent.api;

import java.util.Map;

/**
 * <p>功能 描述:腾讯云分类管理接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/29 9:39</p>
 */
public interface QCloudCategory {

    String CREATE_CLASS = "CreateClass";//增加分类
    String DESCRIBE_ALL_CLASS = "DescribeAllClass";//视频分类层次结构
    String DESCRIBE_CLASS = "DescribeClass";//视频分类信息
    String MODIFY_CLASS = "ModifyClass";//修改视频分类
    String DELETE_CLASS = "DeleteClass";//删除视频分类

    /**
     * 用于管理视频文件，增加分类
     *
     * @param className 分类信息
     * @param parentId  父分类的id号，若不填，默认生成一级分类
     * @return code 错误码, 0: 成功, 其他值: 失败;newClassId 新生成的分类id，最上层分类的id为-1
     */
    Map<String, Object> addCategory(String className, String parentId);

    /**
     * 获得当前用户所有的分类层级关系
     *
     * @return <li>code 错误码, 0: 成功, 其他值: 失败；</li>
     * <li> data 当前用户分类层级关系列表；</li>
     * <li> data.info 一级分类的信息；</li>
     * <li> data.subclass 一级分类下子类的信息</li>
     */
    Map<String, Object> getAllCategorys();

    /**
     * 获取全局分类列表，以及每个分类的具体信息
     *
     * @return code 错误码, 0: 成功, 其他值: 失败
     */
    Map<String, Object> getCategoryInfo();

    /**
     * 修改视频分类的属性，包括名称
     *
     * @param classId   待修改的分类id
     * @param className 新的分类名
     * @return code 错误码, 0: 成功, 其他值: 失败
     */
    Map<String, Object> ModifyCategory(String classId, String className);

    /**
     * 删除视频分类
     *
     * @param classId 待删除视频分类的ID
     * @return code 错误码, 0: 成功, 其他值: 失败
     */
    Map<String, Object> deleteCategory(String classId);
}
