package com.example.yuan_wanandroid.view.search;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.ArticlesAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.app.User;
import com.example.yuan_wanandroid.base.activity.BaseLoadingActivity;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.search.SearchArticlesActivityContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerSearchArticlesActivityComponent;
import com.example.yuan_wanandroid.event.AutoRefreshEvent;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.presenter.search.SearchArticlesActivityPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.view.home.ArticleActivity;
import com.example.yuan_wanandroid.view.person.LoginActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchArticlesActivity extends BaseLoadingActivity<SearchArticlesActivityPresenter>
        implements SearchArticlesActivityContract.View {

    @Inject
    SearchArticlesActivityPresenter mPresenter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    List<Article> mArticleList;
    @Inject
    ArticlesAdapter mArticlesAdapter;
    @BindView(R.id.commonToolbarTv)
    TextView mCommonToolbarTv;
    @BindView(R.id.commonToolbar)
    Toolbar mCommonToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.normalView)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.searchEmpty)
    TextView mEmptyTv;

    private String mKey; //搜索关键词
    private int mPageNum = 0;//首页文章页数
    private boolean isRefresh = false; //是否在上拉刷新
    private int mArticlesPosition = 0;//文章的序号
    private boolean isUnCollect = false;//是否取消收藏


    @Override
    protected SearchArticlesActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_articles;
    }

    @Override
    protected void inject() {
        DaggerSearchArticlesActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        getData();
        loadData();
        initToolbar();
        initRecyclerView();
        initRefresh();
    }

    private void loadData() {
        mPresenter.loadArticles(0, mKey);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void showArticles(List<Article> articles) {
        if (CommonUtils.isEmptyList(articles)) showEmptyView();
        else {
            if (!CommonUtils.isEmptyList(mArticleList)) {
                mArticleList.clear();
            }
            mArticleList.addAll(articles);
            mArticlesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showMoreArticles(List<Article> articles) {
        if (isRefresh) {
            if (!CommonUtils.isEmptyList(mArticleList)) {
                mArticleList.clear();
            }
            mRefreshLayout.finishRefresh();
        } else {
            if (CommonUtils.isEmptyList(articles)) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore(500);
            }
        }
        mArticleList.addAll(articles);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectSuccess() {
        showToast(getString(R.string.collect_success));
        mArticleList.get(mArticlesPosition).setCollect(true);
        mArticlesAdapter.notifyItemChanged(mArticlesPosition + mArticlesAdapter.getHeaderLayoutCount());
    }

    @Override
    public void showUnCollectSuccess() {
        showToast(getString(R.string.uncollect_success));
        mArticleList.get(mArticlesPosition).setCollect(false);
        mArticlesAdapter.notifyItemChanged(mArticlesPosition + mArticlesAdapter.getHeaderLayoutCount());
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(mCommonToolbar);
        mCommonToolbarTv.setText(mKey);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);//不使用原始的toolbar的title
        }
        mCommonToolbar.setNavigationOnClickListener(v -> {
            //如果有进行取消收藏的操作，退出时将更新文章
            if (isUnCollect) RxBus.getInstance().post(new AutoRefreshEvent(true));
            finish();
        });
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mArticlesAdapter);

        //文章点击效果
        mArticlesAdapter.setOnItemClickListener(((adapter, view, position) -> {
            mArticlesPosition = position;
            ArticleActivity.startActivityForResultByActivity(this,
                    mArticleList.get(position).getLink(),
                    mArticleList.get(position).getTitle(),
                    mArticleList.get(position).getId(),
                    mArticleList.get(position).isCollect(),
                    Constant.REQUEST_ARTICLE_ACTIVITY);
        }));

        //文章收藏
        mArticlesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                    mArticlesPosition = position;
                    if (!User.getInstance().isLoginStatus()) {
                        showToast(getString(R.string.first_login));
                        startActivity(new Intent(this, LoginActivity.class));
                    } else {
                        if (mArticleList.get(position).isCollect()) {
                            mPresenter.unCollectArticles(mArticleList.get(position).getId());
                        } else {
                            mPresenter.collectArticles(mArticleList.get(position).getId());
                        }
                        CommonUtils.collectAnimator(this, view);
                    }
                }
        );
    }

    @Override
    public void initRefresh() {
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageNum++;
            isRefresh = false;
            mPresenter.loadMoreArticles(mPageNum, mKey);
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPageNum = 1;
            isRefresh = true;
            mPresenter.loadMoreArticles(0, mKey);
        });
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

    private void showEmptyView() {
        mRefreshLayout.setVisibility(View.GONE);
        mEmptyTv.setVisibility(View.VISIBLE);
    }


    /**
     * 得到关键字
     */
    private void getData() {
        mKey = getIntent().getStringExtra(Constant.KEY_ARTICLE_TITLE);
    }

    /**
     * 给搜索活动调用
     *
     * @param activity
     * @param key
     */
    public static void startActivityBySearchActivity(Activity activity, String key) {
        Intent intent = new Intent(activity, SearchArticlesActivity.class);
        intent.putExtra(Constant.KEY_ARTICLE_TITLE, key);
        activity.startActivity(intent);
    }

    @Override
    public void changeNightStyle(boolean isNight) {

    }
}
