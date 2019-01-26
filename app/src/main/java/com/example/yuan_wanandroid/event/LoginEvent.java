package com.example.yuan_wanandroid.event;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 登录成功事件
 * </pre>
 */


public class LoginEvent {
    private boolean isLogin;

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
