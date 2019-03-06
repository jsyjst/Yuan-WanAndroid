package com.example.yuan_wanandroid.view.person;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.activity.BaseActivity;
import com.example.yuan_wanandroid.base.activity.BaseMvpActivity;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.person.SettingActivityContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerSettingActivityComponent;
import com.example.yuan_wanandroid.event.AutoRefreshEvent;
import com.example.yuan_wanandroid.presenter.person.SettingActivityPresenter;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.suke.widget.SwitchButton;

import javax.inject.Inject;

import butterknife.BindView;

public class SettingActivity extends BaseMvpActivity<SettingActivityPresenter>
        implements SettingActivityContract.View {


    @BindView(R.id.commonToolbar)
    Toolbar mCommonToolbar;
    @BindView(R.id.commonToolbarTv)
    TextView mToolbarTitle;
    @BindView(R.id.nightSwitchBtn)
    SwitchButton nightSwitchBtn;
    @BindView(R.id.noImageSwitchBtn)
    SwitchButton noImageSwitchBtn;
    @Inject
    SettingActivityPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void inject() {
        DaggerSettingActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected SettingActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolbar();
        initSwitch();
    }

    private void initToolbar(){
        setSupportActionBar(mCommonToolbar);
        mToolbarTitle.setText(getString(R.string.person_setting));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);//不使用原始的toolbar的title
        }
        mCommonToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    public void initSwitch(){
        mPresenter.subscribeEvent();
        nightSwitchBtn.setChecked(mPresenter.getNightStyleState());
        nightSwitchBtn.setOnCheckedChangeListener((view, isChecked) -> {
            mPresenter.setNightStyleState(isChecked);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showChangeNightStyle() {

    }

    @Override
    public void changeNightStyle(boolean isNight) {
        // 切换模式
        getDelegate().setDefaultNightMode(isNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        startActivity(new Intent(this,SettingActivity.class));
        overridePendingTransition(R.anim.anim_animo_alph_open, R.anim.anim_animo_alph_close);
        finish();
    }
}
