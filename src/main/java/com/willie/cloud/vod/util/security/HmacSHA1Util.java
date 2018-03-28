package com.willie.cloud.vod.util.security;

import com.willie.cloud.vod.constent.charset.Charset;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 17:09</p>
 */
public class HmacSHA1Util {
    private static final String MAC_NAME = "HmacSHA1";

    /**
     * 取得加密后签名
     *
     * @param data       加密数据
     * @param encryptKey 私钥
     * @return 加密后的签名
     */
    public static String getEncodedSign(String data, String encryptKey) {
        byte[] sign = HmacSHA1Encrypt(data, encryptKey);
        return Base64Util.getBase64UrlEncodeResoult(sign);
    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param text       加密数据
     * @param encryptKey 密钥
     * @return 加密后的字节码
     */
    public static byte[] HmacSHA1Encrypt(String text, String encryptKey) {
        try {
            byte[] data = encryptKey.getBytes(Charset.CHARSET);
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(MAC_NAME);
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);
            //完成 Mac 操作
            return mac.doFinal(text.getBytes(Charset.CHARSET));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
