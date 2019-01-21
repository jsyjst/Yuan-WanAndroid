package com.example.yuan_wanandroid.model.entiy;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 网络请求返回的基本类
 * </pre>
 */


public class BaseResponse<T> {
    /**
     * data : []
     * errorCode : 0
     * errorMsg :
     */

    //errorCode = 0代表执行成功，非0都为失败
    //errorCode = -1001 代表登录失效，需要重新登录。
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
