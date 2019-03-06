package com.example.yuan_wanandroid.view.system;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.TabAdapter;
import com.example.yuan_wanandroid.base.activity.BaseActivity;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.yuan_wanandroid.app.Constant.KEY_SYSTEM_FIRST_NAME;
import static com.example.yuan_wanandroid.app.Constant.KEY_SYSTEM_SECOND_ID_LIST;
import static com.example.yuan_wanandroid.app.Constant.KEY_SYSTEM_SECOND_NAME_LIST;

public class SystemArticlesActivity extends BaseActivity {

    private static final String TAG = "SystemArticlesActivity";

    @BindView(R.id.commonToolbar)
    Toolbar mCommonToolbar;
    @BindView(R.id.commonToolbarTv)
    TextView mToolbarTitle;
    @BindView(R.id.secondSystemTabs)
    SlidingTabLayout mSecondSystemTabs;
    @BindView(R.id.secondSystemAppbar)
    AppBarLayout mSecondSystemAppbar;
    @BindView(R.id.secondSystemViewPager)
    ViewPager mSecondSystemViewPager;


    private List<String> mSecondSystemNameList;
    private List<Integer> mIdList;
    private String mFirstSystemName;
    private List<Fragment> mFragmentList;
    private TabAdapter mTabAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_articles;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {
        getData();
        initToolBar();
        initViewPage();
    }

    private void initToolBar() {
        setSupportActionBar(mCommonToolbar);
        mToolbarTitle.setText(mFirstSystemName);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);//不使用原始的toolbar的title
        }
        mCommonToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initViewPage() {
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < mIdList.size(); i++) {
            mFragmentList.add(SystemArticlesFragment.newInstance(mIdList.get(i)));
        }
        mTabAdapter = new TabAdapter(getSupportFragmentManager(), mFragmentList, mSecondSystemNameList);
        mSecondSystemViewPager.setAdapter(mTabAdapter);
        mSecondSystemTabs.setViewPager(mSecondSystemViewPager);
    }

    @Override
    protected void initData() {

    }

    /**
     * 获取一级知识Fragment传入的数据
     */
    private void getData() {
        Intent intent = getIntent();
        mFirstSystemName = intent.getStringExtra(KEY_SYSTEM_FIRST_NAME);
        mSecondSystemNameList = intent.getStringArrayListExtra(KEY_SYSTEM_SECOND_NAME_LIST);
        mIdList = intent.getIntegerArrayListExtra(KEY_SYSTEM_SECOND_ID_LIST);
    }

    /**
     * 给其他活动需要传入数据并跳转到该活动时调用
     *
     * @param context              活动
     * @param firstSystemName      一级知识的名字
     * @param secondSystemNameList 二级知识的名字集合
     * @param idList               二级知识的id集合
     */
    public static void startActivityByData(Context context,
                                           String firstSystemName,
                                           List<String> secondSystemNameList,
                                           List<Integer> idList) {
        Intent intent = new Intent(context, SystemArticlesActivity.class);
        intent.putExtra(KEY_SYSTEM_FIRST_NAME, firstSystemName);
        intent.putStringArrayListExtra(KEY_SYSTEM_SECOND_NAME_LIST, (ArrayList<String>) secondSystemNameList);
        intent.putIntegerArrayListExtra(KEY_SYSTEM_SECOND_ID_LIST, (ArrayList<Integer>) idList);
        context.startActivity(intent);
    }

    @Override
    public void changeNightStyle(boolean isNight) {

    }
}
