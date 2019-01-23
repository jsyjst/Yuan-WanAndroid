package com.example.yuan_wanandroid.view.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.base.activity.BaseActivity;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.ShareUtils;
import com.example.yuan_wanandroid.utils.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;

public class ArticleActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.containerFrameLayout)
    FrameLayout mContainerFrameLayout;
    @BindView(R.id.webLinear)
    LinearLayout mWebLinear;

    private String mUrl;
    private String mTitle;
    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {
        getBundleData();
        mToolbar.setTitle(Html.fromHtml(mTitle));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContainerFrameLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mUrl);

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();
        mSettings.setAppCacheEnabled(true);
        mSettings.setDomStorageEnabled(true);
        mSettings.setDatabaseEnabled(true);

        //有网络
        mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.shareItem:
                shareLink();
                break;
            case R.id.collectionItem:
                collection();
                break;
            case R.id.browserItem:
                openArticlesByBrowser();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public void setStatusBarColor() {
        StatusBarUtil.setStatusColor(getWindow(), getResources().getColor(R.color.colorPrimaryDark), 1f);
    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void reLoad() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    /**
     * 分享链接
     */
    private void shareLink() {
        if (!TextUtils.isEmpty(mTitle) || !TextUtils.isEmpty(mUrl)) {
            ShareUtils.shareText(this,
                    getString(R.string.article_share_link) + "\n" + "【" + mTitle + "】" + "\n" + mUrl,
                    getString(R.string.article_share_title));
        }else{
            CommonUtils.toastShow(getString(R.string.article_share_error));
        }
    }

    /**
     * 收藏
     */
    private void collection() {

    }

    /**
     * 使用系统浏览器打开
     */
    private void openArticlesByBrowser() {
        ShareUtils.openBrowser(this, mUrl);
    }

    /**
     * 获取传入的数据
     */
    private void getBundleData() {
        mUrl = getIntent().getStringExtra(Constant.KEY_ARTICLE_URL);
        mTitle = getIntent().getStringExtra(Constant.KEY_ARTICLE_TITLE);
    }

    /**
     * 给其他需要传入数据的碎片使用
     * @param activity
     * @param fragment
     * @param url
     * @param title
     */
    public static void startActivityByFragment(Activity activity, Fragment fragment, String url, String title) {
        Intent intent = new Intent(activity, ArticleActivity.class);
        intent.putExtra(Constant.KEY_ARTICLE_URL, url);
        intent.putExtra(Constant.KEY_ARTICLE_TITLE, title);
        fragment.startActivity(intent);
    }
}
