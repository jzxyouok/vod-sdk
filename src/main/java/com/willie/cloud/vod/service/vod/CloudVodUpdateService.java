package com.willie.cloud.vod.service.vod;

import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <p>功能 描述:点播服务更新操作统一外部调用接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:51</p>
 */
public interface CloudVodUpdateService {

    /**
     * 上传文件到服务器
     *
     * @param videoName 视频文件
     * @param expires   token 有效期
     * @return status 0表示成功,其它信息
     */
    Map<String, Object> uploadFile2Server(String videoName, Integer expires) throws Exception;

    /**
     * 删除一个文件，如果文件不存在则会返回成功
     *
     * @param fileId  32字节文件id，唯一标识符
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteFile(String fileId, Long expires);

    /**
     * 添加一个分类，可以是子分类，最多2级分类，分类个数最多200个
     *
     * @param name             分类名称，最大128字节,必输参数
     * @param parentCategoryId 上级分类id，如果为空或id不存在，则认为是顶层分类
     * @param expires          token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败,categoryId 分类id，status为0时返回
     */
    Map<String, Object> addCategory(String name, String parentCategoryId, Long expires) throws UnsupportedEncodingException;

    /**
     * 删除一个分类，如果不存在则返回0，如果包含子分类则会删除所有子分类
     *
     * @param categoryId 分类id,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteCategory(String categoryId, Long expires);

    /**
     * 在分类中添加一个文件，如果分类不存在则添加失败
     *
     * @param categoryId 分类id,必输参数
     * @param fileId     32字节文件id，唯一标识符,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> addFile2Category(String categoryId, String fileId, Long expires);

    /**
     * 在分类中删除一个文件，如果分类不存在则删除失败
     *
     * @param categoryId 分类id,必输参数
     * @param fileId     32字节文件id，唯一标识符,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteFileFromCategory(String categoryId, String fileId, Long expires);

    /**
     * 添加一个专辑，专辑个数最多200个
     *
     * @param name    分类名称，最大128字节,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败;albumId 专辑id，status为0时返回
     */
    Map<String, Object> addAlbum(String name, Long expires) throws UnsupportedEncodingException;

    /**
     * 删除一个专辑，如果不存在，则返回0；如果包含子专辑，则删除所有子分类
     *
     * @param albumId 专辑id,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteAlbum(String albumId, Long expires);

    /**
     * 在专辑中添加一个文件，如果专辑不存在则添加失败
     *
     * @param fileId  32字节文件id，唯一标识符,必输参数
     * @param albumId 专辑id ,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> addFile2Album(String fileId, String albumId, Long expires);

    /**
     * 在专辑中删除一个文件，如果专辑不存在则删除失败
     *
     * @param fileId  32字节文件id，唯一标识符,必输参数
     * @param albumId 专辑id ,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteFileFromAlbum(String fileId, String albumId, Long expires);

    /**
     * 取得云点播配置仓库
     *
     * @return 云点播配置仓库
     */
    CloudVodConfigRepository getCloudVodConfigRepository();
}
