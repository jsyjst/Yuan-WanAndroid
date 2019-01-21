package com.example.yuan_wanandroid.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.activity.BaseActivity;
import com.example.yuan_wanandroid.utils.BottomNavigationViewHelper;
import com.example.yuan_wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.searchTv)
    TextView mSearchTv;
    @BindView(R.id.frameContain)
    FrameLayout mFrameContain;
    @BindView(R.id.BottomBnv)
    BottomNavigationView mBottomBnv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.setStatusColor(getWindow(), getResources().getColor(R.color.colorPrimaryDark), 1f);
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {
        //通过反射去除BottomNav的位移效果
        BottomNavigationViewHelper.disableShiftMode(mBottomBnv);
        mBottomBnv.setItemIconTintList(null);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void reLoad() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showToast() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.searchTv, R.id.BottomBnv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchTv:
                break;
            case R.id.BottomBnv:
                break;
        }
    }
}
