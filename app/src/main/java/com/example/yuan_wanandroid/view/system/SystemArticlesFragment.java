package com.example.yuan_wanandroid.view.system;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.home.ArticlesAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.SystemArticlesFragmentContract;
import com.example.yuan_wanandroid.di.component.fragment.DaggerSystemArticlesFragmentComponent;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.presenter.SystemArticlesFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   :
 * </pre>
 */


public class SystemArticlesFragment extends BaseMvpFragment<SystemArticlesFragmentPresenter>
        implements SystemArticlesFragmentContract.View {

    @Inject
    SystemArticlesFragmentPresenter mPresenter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    List<Article> mArticleList;
    @Inject
    ArticlesAdapter mArticlesAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private static int mId;
    private int mPageNum = 0;
    private boolean isRefresh = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system_articles;
    }



    @Override
    protected void initView() {
        getData();
        initRecyclerView();
        initRefresh();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mArticlesAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mArticlesAdapter);
    }

    private void initRefresh() {
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageNum++;
            isRefresh = false;
            mPresenter.loadMoreSystemArticlesData(mPageNum, mId);
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPageNum = 0;
            isRefresh = true;
            mPresenter.loadMoreSystemArticlesData(0,mId);
        });
    }

    @Override
    protected void loadData() {
        mPresenter.loadSystemArticlesData(0, mId);
    }

    @Override
    protected SystemArticlesFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void inject() {
        DaggerSystemArticlesFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void showSystemArticles(List<Article> articlesList) {
        if(!CommonUtils.isEmptyList(mArticleList)){
            mArticleList.clear();
        }
        LogUtil.d(LogUtil.TAG_ERROR,""+articlesList.size());
        mArticleList.addAll(articlesList);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreSystemArticles(List<Article> articlesList) {
        if(isRefresh){
            if(!CommonUtils.isEmptyList(mArticleList)){
                mArticleList.clear();
            }
            mRefreshLayout.finishRefresh();
        }else{
            mRefreshLayout.finishLoadMore();
        }
        mArticleList.addAll(articlesList);
        mArticlesAdapter.notifyDataSetChanged();
    }


    @Override
    public void showToast(String msg) {
        CommonUtils.toastShow(msg);
    }



    /**
     * 获取数据
     */
    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getInt(Constant.KEY_SYSTEM_SECOND_ID,-1);
        }
    }

    public static Fragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_SYSTEM_SECOND_ID, id);
        SystemArticlesFragment systemArticlesFragment = new SystemArticlesFragment();
        systemArticlesFragment.setArguments(bundle);
        return systemArticlesFragment;
    }

}
