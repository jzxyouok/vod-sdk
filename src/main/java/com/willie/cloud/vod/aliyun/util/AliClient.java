package com.willie.cloud.vod.aliyun.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.willie.cloud.vod.constent.vod.Vod;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 13:53</p>
 */
public abstract class AliClient {

    /**
     * 取得默认客户端
     *
     * @param accessKey 公钥
     * @param secretKey 私钥
     * @return 客户端
     */
    public static DefaultAcsClient getDefaultAcsClient(String accessKey, String secretKey) {
        DefaultProfile profile = DefaultProfile.getProfile(Vod.AliyunConstent.REGION_ID, accessKey, secretKey);
        return new DefaultAcsClient(profile);
    }
}
