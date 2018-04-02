package com.willie.cloud.vod.util.string;

import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/2 14:22</p>
 */
public abstract class ExtStringUtil {

    /**
     * 使用逗号拼接字符串
     *
     * @param list 待拼接参数
     * @return 拼接后的字符串
     */
    public static String concatenateStringWithCommas(List<String> list) {
        if (null == list || list.isEmpty()) {
            throw new NullPointerException();
        }
        StringBuilder stringBuilder = new StringBuilder();

        list.forEach(s -> {
            if (null != s) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(",").append(s);
                } else {
                    stringBuilder.append(s);
                }
            }
        });

        return stringBuilder.toString();
    }

    /**
     * 使用引号拼接字符串
     *
     * @param list 待拼接参数
     * @return 引号拼接字符串
     */
    public static String concatenateStringWithQuotationMarks(List<String> list) {
        if (null == list || list.isEmpty()) {
            throw new NullPointerException();
        }
        StringBuilder stringBuilder = new StringBuilder();

        list.forEach(s -> {
            if (null != s) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(",").append("'").append(s).append("'");
                } else {
                    stringBuilder.append("'").append(s).append("'");
                }
            }
        });

        return stringBuilder.toString();
    }
}
