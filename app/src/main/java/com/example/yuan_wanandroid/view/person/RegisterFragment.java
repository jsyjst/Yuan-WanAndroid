package com.example.yuan_wanandroid.view.person;

import android.app.Instrumentation;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.person.RegisterFragmentContract;
import com.example.yuan_wanandroid.di.component.fragment.DaggerRegisterFragmentComponent;
import com.example.yuan_wanandroid.presenter.person.RegisterFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/25
 *     desc   : 注册
 * </pre>
 */


public class RegisterFragment extends BaseMvpFragment<RegisterFragmentPresenter>
        implements RegisterFragmentContract.View {

    @Inject
    RegisterFragmentPresenter mPresenter;
    @BindView(R.id.usernameEdit)
    EditText usernameEdit;
    @BindView(R.id.passwordEdit)
    EditText passwordEdit;
    @BindView(R.id.passwordRepeatEdit)
    EditText passwordRepeatEdit;
    @BindView(R.id.loginBtn)
    TextView loginBtn;
    @BindView(R.id.registerBtn)
    RippleView registerBtn;

    private SweetAlertDialog dialog;//加载框


    @Override
    protected void inject() {
        DaggerRegisterFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected RegisterFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        registerBtn.setOnRippleCompleteListener(
                v -> {
                    showLoading();
                    mPresenter.register(getEditText(usernameEdit),
                            getEditText(passwordEdit),
                            getEditText(passwordRepeatEdit));
                });
        loginBtn.setOnClickListener(v -> toLoginFragment());
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showLoading() {
        dialog = new SweetAlertDialog(mActivity,SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(CommonUtils.randomTagColor());
        dialog.setTitleText("Loading...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }


    @Override
    public void showSuccess() {
        dialog.cancel();
        CommonUtils.toastShow(mActivity.getString(R.string.person_register_success));
        toLoginFragment();
    }

    @Override
    public void showErrorView() {
        super.showErrorView();
        dialog.cancel();
    }

    @Override
    public void toLoginFragment() {
        //模拟返回键
        new Thread () {
            public void run () {
                try {
                    Instrumentation inst= new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent. KEYCODE_BACK);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public String getEditText(EditText editText){
        return editText.getText().toString();
    }

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

}
