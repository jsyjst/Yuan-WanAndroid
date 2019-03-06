package com.example.yuan_wanandroid.view.project;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.TabAdapter;
import com.example.yuan_wanandroid.base.fragment.BaseLoadingFragment;
import com.example.yuan_wanandroid.contract.project.ProjectFragmentContract;
import com.example.yuan_wanandroid.di.module.fragment.ProjectFragmentModule;
import com.example.yuan_wanandroid.model.entity.Tab;
import com.example.yuan_wanandroid.presenter.project.ProjectFragmentPresenter;
import com.example.yuan_wanandroid.utils.StatusBarUtil;
import com.example.yuan_wanandroid.view.MainActivity;
import com.example.yuan_wanandroid.view.search.SearchActivity;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   :
 * </pre>
 */


public class ProjectFragment extends BaseLoadingFragment<ProjectFragmentPresenter>
        implements ProjectFragmentContract.View{

    @Inject
    ProjectFragmentPresenter mPresenter;
    @Inject
    List<String> mProjectTabList;
    @Inject
    List<Fragment> mFragmentList;
    @Inject
    List<Integer> mIdList;
    @BindView(R.id.searchTv)
    EditText mSearchTv;
    @BindView(R.id.searchIv)
    ImageView mSearchIv;
    @BindView(R.id.searchRelative)
    RelativeLayout mSearchRelative;
    @BindView(R.id.projectTabLayout)
    SlidingTabLayout mProjectTabLayout;
    @BindView(R.id.projectPager)
    ViewPager mProjectViewPager;
    @BindView(R.id.statusBarView)
    View view;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_project;
    }

    @Override
    public void initView(){
        super.initView();
        StatusBarUtil.addStatusBarView(mActivity,view);
        mSearchTv.setOnClickListener(v -> toSearchActivity());
    }

    @Override
    protected void inject() {
        ((MainActivity) getActivity())
                .getComponent()
                .getProjectFragmentComponent(new ProjectFragmentModule())
                .inject(this);
    }

    @Override
    protected ProjectFragmentPresenter getPresenter() {
        return mPresenter;
    }


    @Override
    protected void loadData() {
        mPresenter.loadProjectTabData();
    }

    @Override
    public void showProjectTab(List<Tab> tabList) {
        for(Tab tab : tabList){
            mProjectTabList.add(tab.getName());
            mIdList.add(tab.getId());
        }
        for(int i=0;i<mIdList.size();i++){
            mFragmentList.add(ProjectArticlesFragment.newInstance(mIdList.get(i)));
        }
        TabAdapter adapter = new TabAdapter(getChildFragmentManager(),mFragmentList,mProjectTabList);
        mProjectViewPager.setAdapter(adapter);
        mProjectTabLayout.setViewPager(mProjectViewPager);
    }

    private void toSearchActivity(){
        Intent intent = new Intent(mActivity,SearchActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,
                Pair.create(mSearchTv,getString(R.string.share_edit)),
                Pair.create(mSearchIv,getString(R.string.share_image))
        );
        startActivity(intent,options.toBundle());
    }

    @Override
    public void changeNightStyle(boolean isNight) {

    }
}
