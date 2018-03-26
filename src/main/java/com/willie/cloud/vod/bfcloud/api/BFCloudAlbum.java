package com.willie.cloud.vod.bfcloud.api;

import java.util.Map;

/**
 * <p>功能 描述:暴风云专辑接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 15:24</p>
 */
public interface BFCloudAlbum {
    String ADD_ALBUM = "/api/addalbum";
    String DELETE_ALBUM = "/api/deletealbum";
    String ADD_FILE_2_ALBUM = "/api/albumaddfile";
    String DELETE_FILE_FROM_ALBUM = "/api/albumdelfile";
    String GET_FILE_FROM_ALBUM = "/api/getalbumfile";
    long ALBUM_NAMEL_MAXLENGTH_BIT = 128;

    /**
     * <li>接口名：/api/addalbum</li>
     * <li>请求方法：GET</li>
     * 添加一个专辑，专辑个数最多200个
     *
     * @param name    分类名称，最大128字节,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败;albumId 专辑id，status为0时返回
     */
    Map<String, Object> addAlbum(String name, Long expires);

    /**
     * <li>接口名：/api/deletealbum/li>
     * <li>请求方法：GET</li>
     * 删除一个专辑，如果不存在，则返回0；如果包含子专辑，则删除所有子分类
     *
     * @param albumId 专辑id,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteAlbum(String albumId, Long expires);

    /**
     * <li>接口名：/api/albumaddfile</li>
     * <li>请求方法：GET</li>
     * 在专辑中添加一个文件，如果专辑不存在则添加失败
     *
     * @param fileId  32字节文件id，唯一标识符,必输参数
     * @param albumId 专辑id ,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> addFile2Album(String fileId, String albumId, Long expires);

    /**
     * <li>接口名：/api/albumdelfile</li>
     * <li>请求方法：GET</li>
     * 在专辑中删除一个文件，如果专辑不存在则删除失败
     *
     * @param fileId  32字节文件id，唯一标识符,必输参数
     * @param albumId 专辑id ,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteFileFromAlbum(String fileId, String albumId, Long expires);

    /**
     * <li>接口名：/api/getalbumfile</li>
     * <li>请求方法：GET</li>
     * 获取专辑中的文件列表，最多1000个文件
     *
     * @param albumId 专辑id ,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败;filelist 文件列表，status为0时返回
     * 执行结果：获取专辑中的文件列表，最多1000个文件
     */
    Map<String, Object> getFileFromAlbum(String albumId, Long expires);
}
