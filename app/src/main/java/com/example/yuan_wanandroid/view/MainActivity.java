package com.example.yuan_wanandroid.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.widget.FrameLayout;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.base.activity.BaseMvpActivity;
import com.example.yuan_wanandroid.base.fragment.BaseFragment;
import com.example.yuan_wanandroid.contract.MainContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerMainActivityComponent;
import com.example.yuan_wanandroid.di.component.activity.MainActivityComponent;
import com.example.yuan_wanandroid.model.entity.Version;
import com.example.yuan_wanandroid.presenter.MainActivityPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.DownloadUtil;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.utils.StatusBarUtil;
import com.example.yuan_wanandroid.view.home.HomeFragment;
import com.example.yuan_wanandroid.view.person.PersonFragment;
import com.example.yuan_wanandroid.view.project.ProjectFragment;
import com.example.yuan_wanandroid.view.system.SystemFragment;
import com.example.yuan_wanandroid.view.wx.WxFragment;
import com.example.yuan_wanandroid.widget.VersionUpdateDialog;
import com.tencent.bugly.crashreport.CrashReport;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainActivityPresenter> implements MainContract.View {

    @BindView(R.id.frameContain)
    FrameLayout mFrameContain;
    @BindView(R.id.BottomBnv)
    BottomNavigationView mBottomBnv;
    @Inject
    MainActivityPresenter mPresenter;

    private int mPreFragmentPosition = 0;//上一个被选中的Fragment位置
    private Fragment[] mFragments;
    private MainActivityComponent mMainActivityComponent;
    private VersionUpdateDialog mVersionDialog;
    private String mApkUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new BaseFragment[5];
        mVersionDialog = new VersionUpdateDialog();
        boolean isNightChange = getIntent().getBooleanExtra(Constant.KEY_NIGHT_CHANGE, false);
        if (savedInstanceState == null) {
            mFragments[0] = new HomeFragment();
            mFragments[1] = new SystemFragment();
            mFragments[2] = new WxFragment();
            mFragments[3] = new ProjectFragment();
            mFragments[4] = new PersonFragment();
            loadMultipleFragment(R.id.frameContain, 0, mFragments);
            if (isNightChange) mBottomBnv.setSelectedItemId(R.id.personItem);
            AppCompatDelegate.setDefaultNightMode(mPresenter.getNightStyleState() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            //屏幕翻转后回到当前页面，夜间模式开启后重新登录app
            mFragments[0] = findFragmentByTag(HomeFragment.class.getName());
            mFragments[1] = findFragmentByTag(SystemFragment.class.getName());
            mFragments[2] = findFragmentByTag(WxFragment.class.getName());
            mFragments[3] = findFragmentByTag(ProjectFragment.class.getName());
            mFragments[4] = findFragmentByTag(PersonFragment.class.getName());
            mBottomBnv.setSelectedItemId(getSelectedItemId(mPresenter.getNavCurrentItem()));
        }
    }

    /**
     * 屏幕翻转后记录当前的页面状态
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.d(LogUtil.TAG_COMMON,"navItem:"+mPreFragmentPosition);
        mPresenter.setNavCurrentItem(mPreFragmentPosition);
    }


    @Override
    protected void inject() {
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .build();
        mMainActivityComponent.inject(this);
    }

    @Override
    protected MainActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        initBottomNavigationView();
    }

    @Override
    protected void initData() {
        mPresenter.subscribeEvent();
    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.immersiveInFragments(this, getResources().getColor(R.color.colorPrimaryDark), 1);
    }


    private void initBottomNavigationView() {
        mBottomBnv.setItemIconTintList(null);
        mBottomBnv.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.homeItem:
                    showAndHideFragment(mFragments[0], mFragments[mPreFragmentPosition]);
                    mPreFragmentPosition = 0;
                    setStatusBarColor();
                    break;
                case R.id.systemItem:
                    LogUtil.d(LogUtil.TAG_COMMON, "system");
                    showAndHideFragment(mFragments[1], mFragments[mPreFragmentPosition]);
                    mPreFragmentPosition = 1;
                    setStatusBarColor();
                    break;
                case R.id.wxItem:
                    showAndHideFragment(mFragments[2], mFragments[mPreFragmentPosition]);
                    mPreFragmentPosition = 2;
                    setStatusBarColor();
                    break;
                case R.id.projectItem:
                    showAndHideFragment(mFragments[3], mFragments[mPreFragmentPosition]);
                    mPreFragmentPosition = 3;
                    setStatusBarColor();
                    break;
                case R.id.personItem:
                    showAndHideFragment(mFragments[4], mFragments[mPreFragmentPosition]);
                    mPreFragmentPosition = 4;
                    StatusBarUtil.immersiveInImage(this);
                    break;
                default:
                    break;
            }
            return true;
        });

        //检查版本更新
        mPresenter.checkVersion(DownloadUtil.getVersionName(this));
    }


    public MainActivityComponent getComponent() {
        return mMainActivityComponent;
    }


    //设置夜间模式改变的处理
    @Override
    public void showNightStyle(boolean isNight) {
        super.showNightStyle(isNight);
        finish();
    }


    @Override
    public void showVersionUpdateDialog(String versionDetail) {
        mVersionDialog.setContentText(versionDetail);
        mVersionDialog.show(getSupportFragmentManager(), "update");
    }

    @Override
    public void downloadApk() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            DownloadUtil.downloadApk(this, mApkUrl);
        }

    }

    @Override
    public void setApkUrl(String apkUrl) {
        mApkUrl = apkUrl;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            DownloadUtil.downloadApk(this, mApkUrl);
        } else {
            CommonUtils.toastShow("拒绝存储权限，无法更新版本");
        }
    }

    /**
     * 根据tag找到fragment实例
     */
    private Fragment findFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * 显示和隐藏fragment
     */
    private void showAndHideFragment(Fragment show, Fragment hide) {
        LogUtil.d(LogUtil.TAG_COMMON,"show:"+show.toString());
        LogUtil.d(LogUtil.TAG_COMMON,"hide:"+hide.toString());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show != hide)
            transaction.show(show).hide(hide).commitAllowingStateLoss();
    }

    /**
     * 装载Fragments
     */
    private void loadMultipleFragment(int containerId, int showFragment, Fragment... fragments) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            //最后一个参数为tag,add的时候添加一个tag来可以通过findFragmentByTag来查找fragment的实例
            transaction.add(containerId, fragment,fragment.getClass().getName());
            if (i != showFragment) {
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    //根据保存的页面状态获取底部导航id
    private int getSelectedItemId(int position) {
        switch (position) {
            case 0:
                return R.id.homeItem;
            case 1:
                return R.id.systemItem;
            case 2:
                return R.id.wxItem;
            case 3:
                return R.id.projectItem;
            case 4:
                return R.id.personItem;
            default:
                break;
        }
        return 0;
    }
}
