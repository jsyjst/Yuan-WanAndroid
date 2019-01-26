package com.example.yuan_wanandroid.view.person;

import android.content.Intent;
import android.graphics.Color;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.fragment.BaseFragment;
import com.example.yuan_wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import info.hoang8f.widget.FButton;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 个人模块
 * </pre>
 */


public class PersonFragment extends BaseFragment {


    @BindView(R.id.personLoginButton)
    FButton mPersonLoginButton;

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {
        mPersonLoginButton.setButtonColor(getResources().getColor(R.color.colorPrimaryDark));
        mPersonLoginButton.setOnClickListener(v -> startActivity(new Intent(mActivity,LoginActivity.class)));
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_person;
    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.immersiveInFragments(mActivity, Color.TRANSPARENT, 1);
    }

}
