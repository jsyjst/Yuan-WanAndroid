package com.example.yuan_wanandroid.contract.person;

import android.widget.EditText;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 注册接口集合
 * </pre>
 */


public interface RegisterFragmentContract {
    interface View extends BaseView{
        void showSuccess();  //注册成功
        void toLoginFragment();
        String getEditText(EditText editText);
    }
    interface Persenter extends IPresenter<RegisterFragmentContract.View>{
        void register(String username,String password,String rePassword); //注册
    }
}
