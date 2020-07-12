package com.anibaba.core.exception;

import javax.servlet.http.HttpServletResponse;

public enum CustomErrorCode {

    NOT_FOUND_ENTITY_EXCEPTION("无法找到数据库实体类");

    private String message;


    CustomErrorCode() {
    }

    CustomErrorCode(String message) {
        this.message = message;
    }





    public CustomException runtimeException(Object... params) {
        return new CustomException(this, params);
    }

    /**
     * 创建运行时异常.<br>
     *
     * @param cause  异常原因
     * @param params 异常信息参数
     * @return 运行时异常
     */
    public CustomException runtimeException(Throwable cause, Object... params) {
        return new CustomException(this, cause, params);
    }

    /**
     * 创建运行时异常.<br>
     *
     * @param cause  异常原因
     * @param params 异常信息参数
     * @return 运行时异常
     */
    public CustomException runtimeException(int responseCode, Throwable cause, Object... params) {
        return new CustomException(this, cause, params);
    }

    protected int responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
