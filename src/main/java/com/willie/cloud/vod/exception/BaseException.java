package com.willie.cloud.vod.exception;

/**
 * <p>功能 描述:业务异常</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:32</p>
 */
public abstract class BaseException extends RuntimeException {
    private String paramName;//参数名称
    private String message;//异常信息

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String paramName, String message) {
        this.paramName = paramName;
        this.message = message;
    }
}
