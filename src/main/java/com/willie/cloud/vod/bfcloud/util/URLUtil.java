package com.willie.cloud.vod.bfcloud.util;

import com.willie.cloud.vod.base.util.Charset;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 17:53</p>
 */
public class URLUtil {

    public static String makeUrl(String appId, String token) {
        StringBuilder url = new StringBuilder("?");
        url.append("userid=");
        url.append(appId);
        url.append("&token=");
        try {
            url.append(URLEncoder.encode(token, Charset.UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url.toString();
    }
}
