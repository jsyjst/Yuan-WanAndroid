package com.example.yuan_wanandroid.view.wx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.home.ArticlesAdapter;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.wx.WxArticlesFragmentContract;
import com.example.yuan_wanandroid.di.module.fragment.WxArticlesFragmentModule;
import com.example.yuan_wanandroid.di.module.fragment.WxFragmentModule;
import com.example.yuan_wanandroid.model.entity.Article;
import com.example.yuan_wanandroid.presenter.system.SystemArticlesFragmentPresenter;
import com.example.yuan_wanandroid.presenter.wx.WxArticlesFragmentPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.view.MainActivity;
import com.example.yuan_wanandroid.view.home.ArticleActivity;
import com.example.yuan_wanandroid.view.system.SystemArticlesFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   :
 * </pre>
 */


public class WxArticlesFragment extends BaseMvpFragment<WxArticlesFragmentPresenter>
        implements WxArticlesFragmentContract.View{

    @Inject
    WxArticlesFragmentPresenter mPresenter;
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

    private int mId;
    private int mPageNum = 1;  //用于刷新
    private boolean isRefresh = false;  //是否为向上刷新

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

    @Override
    protected WxArticlesFragmentPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showToast(String msg) {
        CommonUtils.toastShow(msg);
    }

    @Override
    public void showWxArticles(List<Article> articleList) {
        if (!CommonUtils.isEmptyList(mArticleList)) {
            mArticleList.clear();
        }
        mArticleList.addAll(articleList);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreWxArticles(List<Article> articleList) {
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
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mArticlesAdapter);

        //文章点击效果
        mArticlesAdapter.setOnItemClickListener(((adapter, view, position) -> {
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
            mPresenter.loadMoreWxArticlesData(mPageNum, mId);
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPageNum = 0;
            isRefresh = true;
            mPresenter.loadMoreWxArticlesData(1, mId);
        });
    }

    @Override
    protected void inject() {
        ((MainActivity) getActivity())
                .getComponent()
                .getWxArticlesFragmentComponent(new WxArticlesFragmentModule())
                .inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.loadWxArticlesData(1,mId);
    }



    /**
     * 获取数据
     */
    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getInt(Constant.KEY_SYSTEM_SECOND_ID, -1);
        }
    }

    public static Fragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_SYSTEM_SECOND_ID, id);
        Fragment fragment= new WxArticlesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
