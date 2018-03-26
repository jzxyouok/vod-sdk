package com.willie.cloud.vod.exception;

/**
 * <p>功能 描述:参数异常</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:24</p>
 */
public class ParameterException extends BaseException {

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String paramName, String message) {
        super(paramName, message);
    }
}
