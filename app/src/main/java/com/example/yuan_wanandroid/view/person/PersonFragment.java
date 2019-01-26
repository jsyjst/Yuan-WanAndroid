package com.example.yuan_wanandroid.view.person;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.User;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.person.PersonFragmentContract;
import com.example.yuan_wanandroid.presenter.person.PersonFragmentPresenter;
import com.example.yuan_wanandroid.utils.StatusBarUtil;
import com.example.yuan_wanandroid.view.MainActivity;
import com.example.yuan_wanandroid.widget.ConfirmDialog;

import javax.inject.Inject;

import butterknife.BindView;
import info.hoang8f.widget.FButton;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 个人模块
 * </pre>
 */


public class PersonFragment extends BaseMvpFragment<PersonFragmentPresenter>
        implements PersonFragmentContract.View {


    @Inject
    PersonFragmentPresenter mPresenter;
    @BindView(R.id.personLoginButton)
    FButton mPersonLoginButton;
    @BindView(R.id.personUsernameTv)
    TextView mPersonUsernameTv;
    @BindView(R.id.personLogout)
    TextView mPersonLogout;

    @Override
    protected void inject() {
        ((MainActivity) getActivity())
                .getComponent()
                .getPersonFragmentComponent()
                .inject(this);
    }

    @Override
    protected PersonFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        if(User.getInstance().isLoginStatus()){
            showLogin();
        }else{
            showLogout();
        }
        mPersonLoginButton.setButtonColor(getResources().getColor(R.color.colorPrimaryDark));
        mPersonLoginButton.setOnClickListener(v -> startActivity(new Intent(mActivity, LoginActivity.class)));
        mPersonLogout.setOnClickListener(v -> logout());
    }

    @Override
    protected void loadData() {
        mPresenter.subscribeEvent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_person;
    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.immersiveInFragments(mActivity, Color.TRANSPARENT, 1);
    }

    @Override
    public void showLogin() {
        mPersonLoginButton.setVisibility(View.GONE);
        mPersonUsernameTv.setVisibility(View.VISIBLE);
        mPersonLogout.setVisibility(View.VISIBLE);
        mPersonUsernameTv.setText(User.getInstance().getUsername());
    }

    @Override
    public void showLogout() {
        mPersonLoginButton.setVisibility(View.VISIBLE);
        mPersonLogout.setVisibility(View.GONE);
        mPersonUsernameTv.setVisibility(View.GONE);
    }

    private void logout(){
        ConfirmDialog dialog = new ConfirmDialog(mActivity);
        dialog.setOnClickListener(new ConfirmDialog.OnClickListener() {
            @Override
            public void selectSure() {
                mPresenter.logout();
            }

            @Override
            public String setTitle() {
                return mActivity.getString(R.string.dialog_title);
            }

            @Override
            public String setText() {
                return mActivity.getString(R.string.dialog_text);
            }
        });
        dialog.show();
    }
}
