package com.willie.cloud.vod.base.util.http;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/29 14:25</p>
 */
public enum RequestMethod {
    GET("GET"), POST("POST");
    private String methodName;

    RequestMethod(String methodName) {
        this.methodName = methodName;
    }
}
