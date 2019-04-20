package com.example.yuan_wanandroid.view.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.adapter.HistoryAdapter;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.activity.BaseMvpActivity;
import com.example.yuan_wanandroid.contract.search.SearchActivityContract;
import com.example.yuan_wanandroid.di.component.activity.DaggerSearchActivityComponent;
import com.example.yuan_wanandroid.model.entity.HotKey;
import com.example.yuan_wanandroid.presenter.search.SearchActivityPresenter;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.KeyBoardUtil;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.widget.ConfirmDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchActivity extends BaseMvpActivity<SearchActivityPresenter> implements SearchActivityContract.View {

    @Inject
    SearchActivityPresenter mPresenter;
    @BindView(R.id.backIv)
    ImageView backIv;
    @BindView(R.id.searchEdit)
    EditText searchEdit;
    @BindView(R.id.searchTv)
    RippleView searchTv;
    @BindView(R.id.searchHotFlowLayout)
    TagFlowLayout searchHotFlowLayout;
    @BindView(R.id.searchClearAllIv)
    ImageView searchClearAllIv;
    @BindView(R.id.searchHistoryRecycler)
    RecyclerView searchHistoryRecycler;
    @BindView(R.id.searchHistoryEmptyTv)
    TextView searchHistoryEmptyTv;

    private List<HotKey> mHotKeys;
    private LinearLayoutManager mLinearLayoutManager;
    private HistoryAdapter mAdapter;
    private List<String> mHistoryList;

    private int mPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void inject() {
        DaggerSearchActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        mPresenter.loadSearchHotKeyData();
        backIv.setOnClickListener(v -> finish());
        searchTv.setOnRippleCompleteListener(v -> toSearchArticlesActivity(getSearchEditText()));
        searchClearAllIv.setOnClickListener(v -> deleteAllHistories());
    }

    @Override
    public void onStart(){
        super.onStart();
        searchEdit.setText("");
        mPresenter.loadHistoriesData();
    }
    private void initRecyclerView(){
        mLinearLayoutManager = new LinearLayoutManager(this);
        mHistoryList = new ArrayList<>();
        mAdapter = new HistoryAdapter(R.layout.item_search_history,mHistoryList);
        searchHistoryRecycler.setLayoutManager(mLinearLayoutManager);
        searchHistoryRecycler.setAdapter(mAdapter);

        //删除
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            mPresenter.deleteHistory(mHistoryList.get(position));
        });
        //点击
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            toSearchArticlesActivity(mHistoryList.get(position));
        });
    }

    @Override
    protected SearchActivityPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showSearchHotKeyFlow(List<HotKey> hotKeys) {
        mHotKeys = hotKeys;
        searchHotFlowLayout.setAdapter(new TagAdapter<HotKey>(mHotKeys) {
            @Override
            public View getView(FlowLayout parent, int position, HotKey hotKey) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.flow_layout_tv,
                        parent, false);
                String name = hotKey.getName();
                tv.setText(name);
                tv.setBackgroundColor(CommonUtils.randomTagColor());
                return tv;
            }
        });
        //标签点击事件
        searchHotFlowLayout.setOnTagClickListener((view, position, parent) -> {
                toSearchArticlesActivity(mHotKeys.get(position).getName());
                return true;
        });
    }

    @Override
    public void showHistoryData(List<String> historyList) {
        if(!CommonUtils.isEmptyList(mHistoryList)) mHistoryList.clear();
        mHistoryList.addAll(historyList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDeleteHistory() {
        mHistoryList.remove(mPosition);
        if(CommonUtils.isEmptyList(mHistoryList)) showHistoriesEmptyView();
        else mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHistoriesEmptyView() {
        searchHistoryEmptyTv.setVisibility(View.VISIBLE);
        searchHistoryRecycler.setVisibility(View.GONE);
    }

    @Override
    public void hideHistoriesEmptyView() {
        searchHistoryEmptyTv.setVisibility(View.GONE);
        searchHistoryRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDeleteAllHistoriesSuccess() {
        showHistoriesEmptyView();
    }

    @Override
    public void toSearchArticlesActivity(String key) {
        if(TextUtils.isEmpty(key)) {
            KeyBoardUtil.closeKeyboard(this,searchEdit);
            showToast(getString(R.string.search_first_write));
        } else {
            mPresenter.addHistory(key);
            SearchArticlesActivity.startActivityBySearchActivity(this,key);
        }
    }

    @Override
    public String getSearchEditText() {
        return searchEdit.getText().toString();
    }

    private void deleteAllHistories(){
        ConfirmDialog confirmDialog=new ConfirmDialog(this);
        confirmDialog.setTitle(getString(R.string.dialog_delete_title))
                .setText(getString(R.string.dialog_delete_text))
                .show();
        confirmDialog.setOnClickListener(() -> mPresenter.deleteAllHistories());

    }
}
