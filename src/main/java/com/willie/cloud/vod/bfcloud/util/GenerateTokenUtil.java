package com.willie.cloud.vod.bfcloud.util;

import com.willie.cloud.vod.util.security.Base64Util;
import com.willie.cloud.vod.util.security.HmacSHA1Util;

/**
 * <p>功能 描述:密钥生成工具类</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 17:41</p>
 */
public class GenerateTokenUtil {
    /**
     * 取得密钥
     *
     * @param accessKey 公钥
     * @param secretKey 密钥
     * @param data      操作参数
     * @return 加密生成的密钥
     */
    public static String getBFCloudToken(String accessKey, String secretKey, String data) {
        String encodedData = Base64Util.getEncodedData(data);
        String encodedSign = HmacSHA1Util.getEncodedSign(encodedData, secretKey);
        String token = accessKey + ":" + encodedSign + ":" + encodedData;
        return token;
    }
}
