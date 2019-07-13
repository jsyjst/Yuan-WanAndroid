package com.example.yuan_wanandroid.view.person;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.SpeedDialog.dialog.SpeedDialog;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.person.LoginFragmentContract;
import com.example.yuan_wanandroid.di.component.fragment.DaggerLoginFragmentComponent;
import com.example.yuan_wanandroid.presenter.person.LoginFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;

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
    RippleView mLoginBtn;
    @BindView(R.id.registerBtn)
    TextView mRegisterBtn;

    private SpeedDialog dialog;

    @Override
    public void showLoading() {
        dialog = new SpeedDialog(mActivity,SpeedDialog.PROGRESS_TYPE);
        dialog.setProgressColor(CommonUtils.randomTagColor()).setProgressText("加载中...").show();
    }

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
        //触摸反馈结束后执行
        mLoginBtn.setOnRippleCompleteListener(rippleView -> {
            if (TextUtils.isEmpty(getEditText(mUsernameEdit))) {
                showToast(mActivity.getString(R.string.login_username_empty));
            } else if (TextUtils.isEmpty(getEditText(mPasswordEdit))) {
                showToast(mActivity.getString(R.string.login_password_empty));
            } else {
                showLoading();
                mPresenter.login(getEditText(mUsernameEdit), getEditText(mPasswordEdit));
            }
        });
        mRegisterBtn.setOnClickListener(v -> ((LoginActivity) getActivity()).toRegisterFragment());
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showErrorView() {
        super.showErrorView();
        dialog.cancel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void showLoginSuccess() {
        dialog.cancel();
        showToast(getString(R.string.person_login_success));
        getActivity().setResult(Activity.RESULT_OK);
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
