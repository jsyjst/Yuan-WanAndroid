package com.example.yuan_wanandroid.view.person;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.CollectionAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.activity.BaseLoadingActivity;
import com.example.yuan_wanandroid.base.activity.BaseMvpActivity;
import com.example.yuan_wanandroid.base.fragment.BaseLoadingFragment;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.person.CollectionActivityContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerCollectionActivityComponent;
import com.example.yuan_wanandroid.event.AutoRefreshEvent;
import com.example.yuan_wanandroid.model.entity.Collection;
import com.example.yuan_wanandroid.presenter.person.CollectionActivityPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.view.home.ArticleActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CollectionActivity extends BaseLoadingActivity<CollectionActivityPresenter>
        implements CollectionActivityContract.View {

    @Inject
    CollectionActivityPresenter mPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    List<Collection> mCollections;
    @Inject
    CollectionAdapter mAdapter;

    @BindView(R.id.commonToolbar)
    Toolbar mCommonToolbar;
    @BindView(R.id.commonToolbarTv)
    TextView mToolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.normalView)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.emptyRelative)
    RelativeLayout mEmptyRelative;

    private int mPageNum = 0;
    private boolean isRefresh = false;
    private int mPosition = 0;
    private boolean isUnCollect = false;//是否取消收藏

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void inject() {
        DaggerCollectionActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        initToolbar();
        initRecyclerView();
        initRefresh();
        mPresenter.loadCollections(0);
        mPresenter.subscribeEvent();
    }

    @Override
    public void reLoad() {
        mPresenter.loadCollections(0);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected CollectionActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showCollections(List<Collection> collections) {
        if (CommonUtils.isEmptyList(collections)) showEmptyView();
        else {
            if (!CommonUtils.isEmptyList(mCollections)) mCollections.clear();
            mCollections.addAll(collections);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showMoreCollections(List<Collection> collections) {
        if (isRefresh) {
            if (!CommonUtils.isEmptyList(mCollections)) {
                mCollections.clear();
            }
            mRefreshLayout.finishRefresh();
        } else {
            if (CommonUtils.isEmptyList(collections)) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore(500);
            }
        }
        mCollections.addAll(collections);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUnCollectSuccess() {
        isUnCollect = true;
        showToast(getString(R.string.uncollect_success));
        mCollections.remove(mPosition);
        if(CommonUtils.isEmptyList(mCollections)) showEmptyView();
        else mAdapter.notifyItemRemoved(mPosition);
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(mCommonToolbar);
        mToolbarTitle.setText(getString(R.string.person_collection));
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
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //文章点击
        mAdapter.setOnItemClickListener((adapter, view, position) ->
                ArticleActivity.startActivityByCollectionActivity(
                        this,
                        mCollections.get(position).getLink(),
                        mCollections.get(position).getTitle()));

        //取消收藏
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            mPresenter.unCollectArticles(mCollections.get(position).getId(), mCollections.get(position).getOriginId());
        });
    }

    @Override
    public void initRefresh() {
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageNum++;
            isRefresh = false;
            mPresenter.loadMoreCollections(mPageNum);
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPageNum = 0;
            isRefresh = true;
            mPresenter.loadMoreCollections(0);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //如果有进行取消收藏的操作，退出时将更新文章
        if (isUnCollect) RxBus.getInstance().post(new AutoRefreshEvent(true));
    }

    private void showEmptyView() {
        mRefreshLayout.setVisibility(View.GONE);
        mEmptyRelative.setVisibility(View.VISIBLE);
    }
}
