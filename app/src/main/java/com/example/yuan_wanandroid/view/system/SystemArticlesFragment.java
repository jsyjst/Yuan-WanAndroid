package com.example.yuan_wanandroid.view.system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.ArticlesAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.app.User;
import com.example.yuan_wanandroid.base.fragment.BaseLoadingFragment;
import com.example.yuan_wanandroid.contract.system.SystemArticlesFragmentContract;
import com.example.yuan_wanandroid.di.component.fragment.DaggerSystemArticlesFragmentComponent;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.presenter.system.SystemArticlesFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.view.home.ArticleActivity;
import com.example.yuan_wanandroid.view.person.LoginActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/23
 *     desc   :
 * </pre>
 */


public class SystemArticlesFragment extends BaseLoadingFragment<SystemArticlesFragmentPresenter>
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
    @BindView(R.id.normalView)
    SmartRefreshLayout mRefreshLayout;

    private int mId;
    private int mPageNum = 0;  //用于刷新
    private boolean isRefresh = false;  //是否为向上刷新
    private int mArticlesPosition = 0;//文章的序号

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_articles;
    }


    @Override
    protected void initView() {
        super.initView();
        getData();
        initRecyclerView();
        initRefresh();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mArticlesAdapter);


        //文章点击效果
        mArticlesAdapter.setOnItemClickListener(((adapter, view, position) -> {
            mArticlesPosition = position;
            ArticleActivity.startActivityForResultByFragment(mActivity,
                    this,
                    mArticleList.get(position).getLink(),
                    mArticleList.get(position).getTitle(),
                    mArticleList.get(position).getId(),
                    mArticleList.get(position).isCollect(),
                    Constant.REQUEST_ARTICLE_ACTIVITY);
        }));

        //文章收藏
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                    mArticlesPosition = position;
                    if(!User.getInstance().isLoginStatus()){
                        showToast(getString(R.string.first_login));
                        startActivity(new Intent(mActivity, LoginActivity.class));
                    }else{
                        if(mArticleList.get(position).isCollect()) {
                            mPresenter.unCollectArticles(mArticleList.get(position).getId());
                        }else{
                            mPresenter.collectArticles(mArticleList.get(position).getId());
                        }
                        CommonUtils.collectAnimator(mActivity, view);
                    }
                }
        );
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
            mPresenter.loadMoreSystemArticlesData(0, mId);
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
        if (!CommonUtils.isEmptyList(mArticleList)) {
            mArticleList.clear();
        }
        mArticleList.addAll(articlesList);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreSystemArticles(List<Article> articlesList) {
        if (isRefresh) {
            if (!CommonUtils.isEmptyList(mArticleList)) {
                mArticleList.clear();
            }
            mRefreshLayout.finishRefresh();
        } else {
            if (CommonUtils.isEmptyList(articlesList)) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore(500);
            }
        }
        mArticleList.addAll(articlesList);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectSuccess() {
        showToast(getString(R.string.collect_success));
        mArticleList.get(mArticlesPosition).setCollect(true);
        mArticlesAdapter.notifyItemChanged(mArticlesPosition+mArticlesAdapter.getHeaderLayoutCount());
    }

    @Override
    public void showUnCollectSuccess() {
        showToast(getString(R.string.uncollect_success));
        mArticleList.get(mArticlesPosition).setCollect(false);
        mArticlesAdapter.notifyItemChanged(mArticlesPosition+mArticlesAdapter.getHeaderLayoutCount());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        Article article = mArticleList.get(mArticlesPosition);
        switch (requestCode) {
            case Constant.REQUEST_ARTICLE_ACTIVITY:
                boolean isCollect = data.getBooleanExtra(Constant.KEY_ARTICLE_COLLECT, false);
                if (isCollect != article.isCollect()) {
                    article.setCollect(isCollect);
                    mArticlesAdapter.notifyItemChanged(mArticlesPosition + mArticlesAdapter.getHeaderLayoutCount());
                }
                break;
            default:
                break;
        }
    }


    /**
     * 获取数据
     */
    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getInt(Constant.KEY_ARTICLE_ID, -1);
        }
    }

    public static Fragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_ARTICLE_ID, id);
        SystemArticlesFragment systemArticlesFragment = new SystemArticlesFragment();
        systemArticlesFragment.setArguments(bundle);
        return systemArticlesFragment;
    }

}
