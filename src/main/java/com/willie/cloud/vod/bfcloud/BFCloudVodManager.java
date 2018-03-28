package com.willie.cloud.vod.bfcloud;

import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.bfcloud.api.*;
import com.willie.cloud.vod.bfcloud.util.GenerateTokenUtil;
import com.willie.cloud.vod.bfcloud.util.URLUtil;
import com.willie.cloud.vod.constent.vod.Vod;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.util.http.HttpUtil;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 14:48</p>
 */
public class BFCloudVodManager extends CloudVodManager implements BfCloudFileOpertation, BFVodCallback, BFCloudCategory, BFCloudAlbum {

    private static volatile BFCloudVodManager vodBFManager = null;

    private BFCloudVodManager() {

    }

    @Override
    public Map<String, Object> deleteFile(String fileId, Long expires) {
        String param = "fileid=" + fileId + "&expires=" + getExpires(expires);
        logger.info("删除文件接口参数:{},token有效期:{}", param, getExpires(expires));
        String token = getToken(param);
        String url = getURL(BfCloudFileOpertation.DELETE_FILE, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> addCategory(String name, String parentCategoryId, Long expires) {
        String param = "pcatid=" + parentCategoryId +
                "&name=" +
                name +
                "&expires=" + getExpires(expires);
        logger.info("增加分类接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudCategory.ADD_CATEGORY, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> deleteCategory(String categoryId, Long expires) {
        String param = "catid=" + categoryId + "&expires=" + getExpires(expires);
        logger.info("删除分类接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudCategory.DELETE_CATEGORY, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> addFile2Category(String categoryId, String fileId, Long expires) {
        String param = "catid=" + categoryId + "&fileid=" + fileId + "&expires=" + getExpires(expires);
        logger.info("分类中添加文件接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudCategory.ADD_FILE_2_CATEGORY, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> deleteFileFromCategory(String categoryId, String fileId, Long expires) {
        String param = "catid=" + categoryId + "&fileid=" + fileId + "&expires=" + getExpires(expires);
        logger.info("分类中删除文件接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudCategory.DELETE_FILE_FROM_CATEGORY, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> getFileFromCategory(String categoryId, Long expires) {
        String param = "catid=" + categoryId + "&expires=" + getExpires(expires);
        logger.info("获取分类中文件接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudCategory.GET_FILE_FROM_CATEGORY, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> addAlbum(String name, Long expires) {
        String param = "name=" + name + "&expires=" + expires;
        logger.info("添加专辑接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudAlbum.ADD_ALBUM, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> deleteAlbum(String albumId, Long expires) {
        String param = "albumid=" + albumId + "&expires=" + getExpires(expires);
        logger.info("删除专辑接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudAlbum.DELETE_ALBUM, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> addFile2Album(String fileId, String albumId, Long expires) {
        String param = "fileid=" + fileId + "&albumid=" + albumId + "&expires=" + getExpires(expires);
        logger.info("专辑中添加文件接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudAlbum.ADD_FILE_2_ALBUM, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> deleteFileFromAlbum(String fileId, String albumId, Long expires) {
        String param = "fileid=" + fileId + "&albumid=" + albumId + "&expires=" + getExpires(expires);
        logger.info("专辑中删除文件接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudAlbum.DELETE_FILE_FROM_ALBUM, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> getFileFromAlbum(String albumId, Long expires) {
        String param = "albumid=" + albumId + "&expires=" + getExpires(expires);
        logger.info("获取专辑中文件接口参数param:{}", param);
        String token = getToken(param);
        String url = getURL(BFCloudAlbum.GET_FILE_FROM_ALBUM, token);
        return HttpUtil.transferGetResult(url);
    }

    /**
     * 取得token
     *
     * @param param 请求参数
     * @return token
     */
    private String getToken(String param) {
        return GenerateTokenUtil.getBFCloudToken(accessKey, secretKey, param);
    }

    /**
     * 取得接口调用地址
     *
     * @param apiURL 网关接口
     * @param token  令牌
     * @return 接口调用地址
     */
    private String getURL(String apiURL, String token) {
        return Vod.BfCloudConstent.API_URL + apiURL + URLUtil.makeUrl(appId, token);
    }

    /**
     * 取得暴风云点播管理的实例
     *
     * @param config 暴风云vod相关配置信息
     * @return 暴风云点播管理的实例
     */
    public static BFCloudVodManager getBFCloudVodManagerInstance(CloudVodConfig config) {
        if (null == vodBFManager) {
            synchronized (BFCloudVodManager.class) {
                if (null == vodBFManager) {
                    vodBFManager = new BFCloudVodManager();
                }
            }
            /*这里属性赋值放在双重校验外，主要防止反射生成的实例*/
            appId = config.getAppId();
            accessKey = config.getAccessKey();
            secretKey = config.getSecretKey();
            expires = config.getExpires();
        }
        return vodBFManager;
    }
}
