package com.example.yuan_wanandroid.view.system;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.system.FirstSystemAdapter;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.SystemFragmentContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerMainActivityComponent;
import com.example.yuan_wanandroid.di.module.fragment.SystemFragmentModule;
import com.example.yuan_wanandroid.model.entity.FirstSystem;
import com.example.yuan_wanandroid.presenter.SystemFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.view.MainActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 知识体系模块
 * </pre>
 */


public class SystemFragment extends BaseMvpFragment<SystemFragmentPresenter>
        implements SystemFragmentContract.View {

    @Inject
    SystemFragmentPresenter mPresenter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    List<FirstSystem> mFirstSystemList;
    @Inject
    FirstSystemAdapter mFirstSystemAdapter;
    @BindView(R.id.searchTv)
    TextView mSearchTv;
    @BindView(R.id.searchRelative)
    RelativeLayout mSearchRelative;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private boolean isRefresh=false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_system;
    }

    @Override
    public void initView(){
        super.initView();
        initRecyclerView();
        initRefresh();
    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirstSystemAdapter);
    }

    private void initRefresh(){
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
           mPresenter.loadSystemData();
           isRefresh =true;
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.finishLoadMoreWithNoMoreData());
    }

    @Override
    protected void inject() {
        ((MainActivity)getActivity())
                .getComponent()
                .getSystemFragmentComponent(new SystemFragmentModule())
                .inject(this);
    }


    @Override
    protected SystemFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showToast(String msg) {
        CommonUtils.toastShow(msg);
    }


    @Override
    protected void loadData() {
        mPresenter.loadSystemData();
    }


    @Override
    public void showSystemData(List<FirstSystem> firstSystemList) {
        if(isRefresh){
            isRefresh = false;
            mRefreshLayout.finishRefresh(500);
        }
        if(!CommonUtils.isEmptyList(mFirstSystemList)){
            mFirstSystemList.clear();
        }
        mFirstSystemList.addAll(firstSystemList);
        mFirstSystemAdapter.notifyDataSetChanged();
    }
}
