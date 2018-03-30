package com.willie.cloud.vod.util.security;

import com.willie.cloud.vod.util.Charset;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 17:08</p>
 */
public final class Base64Util {

    /**
     * 取得加密数据
     *
     * @param data
     * @return
     */
    public static String getEncodedData(String data) {
        String result = null;
        if (data == null || data.length() == 0) {
            return result;
        }
        try {
            result = new BASE64Encoder().encode(data.getBytes(Charset.UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getBase64UrlEncodeResoult(byte[] str) {
        return new BASE64Encoder().encode(str);
    }

}
