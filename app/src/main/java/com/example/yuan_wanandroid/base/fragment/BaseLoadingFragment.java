package com.example.yuan_wanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.utils.TypefacesUtil;
import com.example.yuan_wanandroid.widget.Lead;
import com.example.yuan_wanandroid.widget.LeadTextView;

import static com.example.yuan_wanandroid.app.Constant.ERROR_STATE;
import static com.example.yuan_wanandroid.app.Constant.LOADING_STATE;
import static com.example.yuan_wanandroid.app.Constant.NORMAL_STATE;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/25
 *     desc   : 布局切换的碎片
 * </pre>
 */


public abstract class BaseLoadingFragment<T extends IPresenter> extends BaseMvpFragment<T>{
    private View mLoadingView; //加载布局
    private ViewGroup mNormalView; //正常布局
    private View mErrorView; //错误布局
    private LeadTextView mLoadingText;
    private Lead lead ;

    private int mCurrentState = NORMAL_STATE;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        if(getView()==null) return;
        mNormalView = view.findViewById(R.id.normalView);
        if(mNormalView == null) {
            throw new IllegalStateException("The subclass of BaseLoadFragment must contain a View it's id is named normal_view");
        }
        if(!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("mNormalView's ParentView should be a ViewGroup");
        }
        ViewGroup parent = (ViewGroup)mNormalView.getParent();
        View.inflate(mActivity,R.layout.loading_view,parent);
        View.inflate(mActivity,R.layout.error_view,parent);

        //loading
        mLoadingView = parent.findViewById(R.id.loadingView);
        mLoadingText = mLoadingView.findViewById(R.id.loadingText);
        mLoadingText.setTypeface(TypefacesUtil.get(mActivity,"Satisfy-Regular.ttf"));
        lead = new Lead(1000);
        //error
        mErrorView = parent.findViewById(R.id.errorView);
        //重试
        TextView mErrorRetry = mErrorView.findViewById(R.id.retryTv);
        mErrorRetry.setOnClickListener(view1 -> loadData());


        mNormalView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);

    }



    @Override
    public void showLoading() {
        if(mCurrentState == LOADING_STATE) return;
        hideCurrentViewByState();
        mCurrentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
        lead.start(mLoadingText);
    }

    @Override
    public void showNormalView() {
        if(mCurrentState == NORMAL_STATE) return;
        hideCurrentViewByState();
        mCurrentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        if(mCurrentState == ERROR_STATE) return;
        hideCurrentViewByState();
        mCurrentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        if (lead != null) {
            lead.cancel();
        }
        super.onDestroyView();
    }

    /**
     * 隐藏当前布局根据mCurrentState
     */
    private void hideCurrentViewByState() {
        switch (mCurrentState) {
            case NORMAL_STATE:
                if(mNormalView ==null) return;
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATE:
                if(mLoadingView == null) return;
                lead.cancel();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                if(mErrorView == null) return;
                mErrorView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
