package com.example.yuan_wanandroid.view.person;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.person.LoginFragmentContract;
import com.example.yuan_wanandroid.di.component.fragment.DaggerLoginFragmentComponent;
import com.example.yuan_wanandroid.presenter.person.LoginFragmentPresenter;


import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/25
 *     desc   : 登录
 * </pre>
 */


public class LoginFragment extends BaseMvpFragment<LoginFragmentPresenter>
        implements LoginFragmentContract.View {

    @Inject
    LoginFragmentPresenter mPresenter;
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
        DaggerLoginFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected LoginFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        mLoginBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(getEditText(mUsernameEdit))) {
                showToast(mActivity.getString(R.string.login_username_empty));
            } else if (TextUtils.isEmpty(getEditText(mPasswordEdit))) {
                showToast(mActivity.getString(R.string.login_password_empty));
            } else {
                mPresenter.login(getEditText(mUsernameEdit), getEditText(mPasswordEdit));
            }
        });
        mRegisterBtn.setOnClickListener(v -> ((LoginActivity) getActivity()).toRegisterFragment());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void showLoginSuccess() {
        showToast(getString(R.string.person_login_success));
        getActivity().finish();
    }

    @Override
    public String getEditText(EditText editText) {
        return editText.getText().toString();
    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }
}
