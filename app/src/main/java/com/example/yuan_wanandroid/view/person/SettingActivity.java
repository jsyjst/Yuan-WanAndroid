package com.example.yuan_wanandroid.view.person;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.base.activity.BaseMvpActivity;
import com.example.yuan_wanandroid.contract.person.SettingActivityContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerSettingActivityComponent;
import com.example.yuan_wanandroid.presenter.person.SettingActivityPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.DownloadUtil;
import com.example.yuan_wanandroid.utils.FileUtil;
import com.example.yuan_wanandroid.utils.ShareUtils;
import com.example.yuan_wanandroid.view.MainActivity;
import com.example.yuan_wanandroid.widget.ConfirmDialog;
import com.suke.widget.SwitchButton;

import java.io.File;

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
    @BindView(R.id.autoCacheSwitchBtn)
    SwitchButton autoCacheSwitchBtn;
    @BindView(R.id.clearCacheRv)
    RelativeLayout clearCacheRv;
    @BindView(R.id.checkVersionRv)
    RelativeLayout checkVersionRv;
    @BindView(R.id.cacheSizeTv)
    TextView cacheSizeTv;
    @BindView(R.id.versionTv)
    TextView versionTv;
    @Inject
    SettingActivityPresenter mPresenter;
    @BindView(R.id.feedBackRv)
    RelativeLayout feedBackRv;


    private boolean isChangeNightStyle;
    private File mCacheFile;
    private String mCacheSize;

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
        initSettings();
    }

    private void initToolbar() {
        setSupportActionBar(mCommonToolbar);
        mToolbarTitle.setText(getString(R.string.person_setting));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);//不使用原始的toolbar的title
        }
        mCommonToolbar.setNavigationOnClickListener(v -> {
            goBack();
        });
    }

    public void initSettings() {
        mPresenter.subscribeEvent();
        mCacheFile = new File(Constant.PATH_NET_CACHE);
        //基本设置
        nightSwitchBtn.setChecked(mPresenter.getNightStyleState());
        noImageSwitchBtn.setChecked(mPresenter.getNoImgStyleState());
        nightSwitchBtn.setOnCheckedChangeListener((view, isChecked) ->
                mPresenter.setNightStyleState(isChecked));
        noImageSwitchBtn.setOnCheckedChangeListener(((view, isChecked) -> mPresenter.setNoImgStyleState(isChecked)));
        autoCacheSwitchBtn.setChecked(mPresenter.getAutoCacheState());
        autoCacheSwitchBtn.setOnCheckedChangeListener(((view, isChecked) -> mPresenter.setAutoCacheStyleState(isChecked)));
        //其他设置
        feedBackRv.setOnClickListener(view -> ShareUtils.sendEmail(this,Constant.EMAIL_ADDRESS,getString(R.string.setting_send_to)));
        mCacheSize = FileUtil.getCacheSize(mCacheFile);
        cacheSizeTv.setText(mCacheSize);
        clearCacheRv.setOnClickListener(view -> {
            ConfirmDialog dialog = new ConfirmDialog(this);
            dialog.setTitle(getString(R.string.setting_clear_cache))
                    .setText("确定清空" + mCacheSize + "缓存吗?")
                    .show();
            dialog.setOnClickListener(() -> {
                FileUtil.deleteDir(mCacheFile);
                CommonUtils.toastShow(getString(R.string.setting_clear_cache_success));
                cacheSizeTv.setText(getString(R.string.setting_empty_cache));
            });
        });

        versionTv.setText(DownloadUtil.getVersionName(this));//版本号

    }

    @Override
    protected void initData() {

    }


    @Override
    public void showChangeNightStyle() {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra(Constant.KEY_NIGHT_CHANGE, true);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_animo_alph_open, R.anim.anim_animo_alph_close);
        finish();
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    private void goBack() {
        isChangeNightStyle = getIntent().getBooleanExtra(Constant.KEY_NIGHT_CHANGE, false);
        if (isChangeNightStyle) {  //  如果改变了夜间模式，则重启MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.KEY_NIGHT_CHANGE, true);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        finish();
    }
}
