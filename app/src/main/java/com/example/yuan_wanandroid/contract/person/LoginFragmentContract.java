package com.example.yuan_wanandroid.contract.person;

import android.widget.EditText;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 登录
 * </pre>
 */


public interface LoginFragmentContract {
    interface View extends BaseView{
        void showLoginSuccess();//登录成功
        String getEditText(EditText editText);
    }
    interface Presenter extends IPresenter<View>{
        void login(String username,String password);
    }
}
