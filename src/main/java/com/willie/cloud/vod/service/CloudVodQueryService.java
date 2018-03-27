package com.willie.cloud.vod.service.vod;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.repository.config.CloudVodConfigRepository;

import java.util.Map;

/**
 * <p>功能 描述:点播服务查询统一外部调用接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:50</p>
 */
public interface CloudVodQueryService {

    /**
     * 获取分类中的文件列表，最多1000个文件
     *
     * @param categoryId 分类id,必输参数
     * @param expires    token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败；filelist 文件列表，status为0时返回
     */
    Map<String, Object> getFileFromCategory(String categoryId, Long expires);

    /**
     * 获取专辑中的文件列表，最多1000个文件
     *
     * @param albumId 专辑id ,必输参数
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数,必输参数
     * @return status 0表示成功，非0表示失败;filelist 文件列表，status为0时返回
     * 执行结果：获取专辑中的文件列表，最多1000个文件
     */
    Map<String, Object> getFileFromAlbum(String albumId, Long expires);

    /**
     * 取得可用点播服务配置
     *
     * @return 点播服务配置
     */
    CloudVodConfig getEnableCloudVodManager();

    /**
     * 取得点播服务仓库
     *
     * @return 点播服务仓库
     */
    CloudVodConfigRepository getCloudVodConfigRepository();
}


