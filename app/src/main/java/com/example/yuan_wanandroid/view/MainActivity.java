package com.example.yuan_wanandroid.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.activity.BaseActivity;
import com.example.yuan_wanandroid.base.fragment.BaseFragment;
import com.example.yuan_wanandroid.di.component.activity.DaggerMainActivityComponent;
import com.example.yuan_wanandroid.di.component.activity.MainActivityComponent;
import com.example.yuan_wanandroid.di.scope.PerFragment;
import com.example.yuan_wanandroid.utils.BottomNavigationViewHelper;
import com.example.yuan_wanandroid.utils.StatusBarUtil;
import com.example.yuan_wanandroid.view.home.HomeFragment;
import com.example.yuan_wanandroid.view.person.PersonFragment;
import com.example.yuan_wanandroid.view.project.ProjectFragment;
import com.example.yuan_wanandroid.view.system.SystemFragment;
import com.example.yuan_wanandroid.view.wx.WxFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.frameContain)
    FrameLayout mFrameContain;
    @BindView(R.id.BottomBnv)
    BottomNavigationView mBottomBnv;

    private int mPreFragmentPosition = 0;//上一个被选中的Fragment位置
    private Fragment[] mFragments;
    private MainActivityComponent mMainActivityComponent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new BaseFragment[5];
        if (savedInstanceState == null) {
            mFragments[0] = new HomeFragment();
            mFragments[1] = new SystemFragment();
            mFragments[2] = new WxFragment();
            mFragments[3] = new ProjectFragment();
            mFragments[4] = new PersonFragment();
            loadMultipleFragment(R.id.frameContain, 0, mFragments);
        } else{
            mFragments[0] = findFragmentByTag(HomeFragment.class.getName());
            mFragments[1] = findFragmentByTag(SystemFragment.class.getName());
            mFragments[2] = findFragmentByTag(WxFragment.class.getName());
            mFragments[3] = findFragmentByTag(ProjectFragment.class.getName());
            mFragments[4] = findFragmentByTag(PersonFragment.class.getName());
        }
    }


    @Override
    protected void inject() {
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .build();
        mMainActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initBottomNavigationView();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.immersiveInFragments(this, getResources().getColor(R.color.colorPrimaryDark), 1);
    }

    private void initBottomNavigationView() {
        //通过反射去除BottomNav的位移效果
        BottomNavigationViewHelper.disableShiftMode(mBottomBnv);
        mBottomBnv.setItemIconTintList(null);
        mBottomBnv.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.homeItem:
                    showAndHideFragment(mFragments[0], mFragments[mPreFragmentPosition]);
                    mPreFragmentPosition = 0;
                    setStatusBarColor();
                    break;
                case R.id.systemItem:
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
            transaction.add(containerId, fragment);
            if (i != showFragment) {
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    public MainActivityComponent getComponent() {
        return mMainActivityComponent;
    }
}
