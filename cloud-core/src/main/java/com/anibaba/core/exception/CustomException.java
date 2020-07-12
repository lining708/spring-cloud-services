package com.anibaba.core.exception;

import javax.servlet.http.HttpServletResponse;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -3876319857610564892L;

    protected String errorCode = "Internal Server Error";
    protected String message = "No value present";
    protected int responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    public CustomException(CustomErrorCode errorCode) {
        this(errorCode, (Object[]) null);
    }

    /**
     * @param errorCode 异常码
     * @param params    消息参数
     */
    public CustomException(CustomErrorCode errorCode, Object... params) {
        this(errorCode, null, params);
    }

    /**
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param params    消息参数
     */
    public CustomException(CustomErrorCode errorCode, Throwable cause, Object... params) {
        this(errorCode.name(), errorCode.getResponseCode(), errorCode.getMessage(), cause);
    }

    /**
     * 构造方法
     *
     * @param errorCode
     * @param responseCode
     * @param message
     * @param cause
     */
    public CustomException(String errorCode, int responseCode, String message, Throwable cause) {
        this.errorCode = errorCode;
        this.responseCode = responseCode;
        this.message = message;
        if (cause != null) {
            initCause(cause);
        }
    }
}
