package com.example.yuan_wanandroid.view.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.ProjectAdapter;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.base.fragment.BaseLoadingFragment;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.project.ProjectArticlesFragmentContract;
import com.example.yuan_wanandroid.di.module.fragment.ProjectArticlesFragmentModule;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.presenter.project.ProjectArticlesFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.view.MainActivity;
import com.example.yuan_wanandroid.view.home.ArticleActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目文章
 * </pre>
 */


public class ProjectArticlesFragment extends BaseLoadingFragment<ProjectArticlesFragmentPresenter>
        implements ProjectArticlesFragmentContract.View{

    @Inject
    ProjectArticlesFragmentPresenter mPresenter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    List<Article> mArticleList;
    @Inject
    ProjectAdapter mProjectAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.normalView)
    SmartRefreshLayout mRefreshLayout;

    private int mId;
    private int mPageNum = 1;  //用于刷新
    private boolean isRefresh = false;  //是否为向上刷新
    @Override
    protected ProjectArticlesFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        getData();
        initRecyclerView();
        initRefresh();
    }

    @Override
    public void showProjectArticles(List<Article> articleList) {
        if (!CommonUtils.isEmptyList(mArticleList)) {
            mArticleList.clear();
        }
        mArticleList.addAll(articleList);
        mProjectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreProjectArticles(List<Article> articleList) {
        if (isRefresh) {
            if (!CommonUtils.isEmptyList(mArticleList)) {
                mArticleList.clear();
            }
            mRefreshLayout.finishRefresh();
        } else {
            if (CommonUtils.isEmptyList(articleList)) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore(500);
            }
        }
        mArticleList.addAll(articleList);
        mProjectAdapter.notifyDataSetChanged();
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mProjectAdapter);

        //文章点击效果
        mProjectAdapter.setOnItemClickListener(((adapter, view, position) -> {
            ArticleActivity.startActivityByFragment(mActivity,
                    this,
                    mArticleList.get(position).getLink(),
                    mArticleList.get(position).getTitle());
        }));
    }

    @Override
    public void initRefresh() {
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageNum++;
            isRefresh = false;
            mPresenter.loadMoreProjectArticlesData(mPageNum, mId);
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPageNum = 0;
            isRefresh = true;
            mPresenter.loadMoreProjectArticlesData(1, mId);
        });
    }

    @Override
    protected void inject() {
        ((MainActivity)getActivity())
                .getComponent()
                .getProjectArticlesFragmentComponent(new ProjectArticlesFragmentModule())
                .inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.loadProjectArticlesData(1,mId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_articles;
    }

    /**
     * 获取数据
     */
    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getInt(Constant.KEY_ARTICLES_ID, -1);
        }
    }

    public static Fragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_ARTICLES_ID, id);
        Fragment fragment= new ProjectArticlesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
