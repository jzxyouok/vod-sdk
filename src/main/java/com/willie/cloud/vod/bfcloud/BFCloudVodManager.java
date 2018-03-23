package com.willie.cloud.vod.bfcloud;

import com.willie.cloud.vod.CloudVodConfig;
import com.willie.cloud.vod.CloudVodManager;
import com.willie.cloud.vod.bfcloud.api.BFCloudAlbum;
import com.willie.cloud.vod.bfcloud.api.BFCloudCategory;
import com.willie.cloud.vod.bfcloud.api.BFVodCallback;
import com.willie.cloud.vod.bfcloud.api.FileOpertation;
import com.willie.cloud.vod.bfcloud.constent.BFConstent;
import com.willie.cloud.vod.bfcloud.util.GenerateTokenUtil;
import com.willie.cloud.vod.bfcloud.util.HttpUtil;
import com.willie.cloud.vod.bfcloud.util.URLUtil;

import java.util.Map;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 14:48</p>
 */
public class BFCloudVodManager extends CloudVodManager implements FileOpertation, BFVodCallback, BFCloudCategory, BFCloudAlbum {

    private static volatile BFCloudVodManager vodBFManager = null;

    private BFCloudVodManager() {

    }

    @Override
    public Map<String, Object> deleteFile(String fileId, long expires) {

        return null;
    }

    @Override
    public Map<String, Object> addCategory(String name, String parentCategoryId, long expires) {
        String param = "pcatid=" + parentCategoryId +
                "&name=" +
                name +
                "&expires=" +
                (System.currentTimeMillis() / 1000 + expires);
        logger.info("增加分类请求参数param:{}", param);
        String token = GenerateTokenUtil.getBFCloudToken(accessKey, secretKey, param);
        String url = BFConstent.API_URL + BFCloudCategory.ADD_CATEGORY + URLUtil.makeUrl(appId, token);
        return HttpUtil.transferGetResult(url);
    }

    @Override
    public Map<String, Object> deleteCategory(String categoryId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> addFile2Category(String categoryId, String fileId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> deleteFileFromCategory(String categoryId, String fileId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> getFileFromCategory(String categoryId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> addAlbum(String name, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> deleteAlbum(String albumId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> addFile2Album(String fileId, String albumId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> deleteFileFromAlbum(String fileId, String albumId, long expires) {
        return null;
    }

    @Override
    public Map<String, Object> getFileFromAlbum(String albumId, long expires) {
        return null;
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
                    appId = config.getAppId();
                    accessKey = config.getAccessKey();
                    secretKey = config.getSecretKey();
                    expires = config.getExpires();
                }
            }
        }
        return vodBFManager;
    }
}
