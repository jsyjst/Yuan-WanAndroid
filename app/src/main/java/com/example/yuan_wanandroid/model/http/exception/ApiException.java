package com.example.yuan_wanandroid.model.http.exception;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 自定义api异常
 * </pre>
 */


public class ApiException extends Exception {
    private final int mErrorCode;
    private final String mErrorMessage;

    public ApiException(int errorCode, String errorMessage) {
        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
