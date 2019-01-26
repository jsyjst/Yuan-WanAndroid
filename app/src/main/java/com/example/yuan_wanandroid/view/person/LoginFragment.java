package com.example.yuan_wanandroid.view.person;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/25
 *     desc   : 登录
 * </pre>
 */


public class LoginFragment extends BaseFragment {
    @BindView(R.id.usernameEdit)
    EditText mUsernameEdit;
    @BindView(R.id.passwordEdit)
    EditText mPasswordEdit;
    @BindView(R.id.loginBtn)
    TextView mLoginBtn;
    @BindView(R.id.registerBtn)
    TextView mRegisterBtn;

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {
        mRegisterBtn.setOnClickListener(v -> ((LoginActivity)getActivity()).toRegisterFragment());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    public static Fragment newInstance(){
        return new LoginFragment();
    }
}
