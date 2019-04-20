package com.example.yuan_wanandroid.view.person;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.Constant;
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

import static android.app.Activity.RESULT_OK;

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
    @BindView(R.id.collectionRelative)
    RelativeLayout mCollectionRelative;
    @BindView(R.id.settingRelative)
    RelativeLayout mSettingRelative;
    @BindView(R.id.aboutUsRelative)
    RelativeLayout mAboutUsRelative;

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
        if (User.getInstance().isLoginStatus()) {
            showLogin();
        } else {
            showLogout();
        }
        mPersonLoginButton.setButtonColor(getResources().getColor(R.color.colorPrimaryDark));
        mPersonLoginButton.setOnClickListener(v -> startActivity(new Intent(mActivity, LoginActivity.class)));
        mPersonLogout.setOnClickListener(v -> logout());
        mCollectionRelative.setOnClickListener(v -> {
            if(User.getInstance().isLoginStatus()){
                startActivity(new Intent(mActivity,CollectionActivity.class));
            }else{
                LoginActivity.startActivityForResultByFragment(mActivity,this, Constant.REQUEST_COLLECTION_ACTIVITY);
                showToast(getString(R.string.first_login));
            }
        });
        mSettingRelative.setOnClickListener(v -> startActivity(new Intent(mActivity,SettingActivity.class)));
        mAboutUsRelative.setOnClickListener(v -> startActivity(new Intent(mActivity,AboutUsActivity.class)));

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

    private void logout() {
        ConfirmDialog dialog = new ConfirmDialog(mActivity);
        dialog.setTitle(getString(R.string.dialog_title))
                .setText(getString(R.string.dialog_text))
                .show();
        dialog.setOnClickListener(() -> mPresenter.logout());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case Constant.REQUEST_COLLECTION_ACTIVITY:
                startActivity(new Intent(mActivity,CollectionActivity.class));
                break;
            default:
                break;
        }
    }


}
