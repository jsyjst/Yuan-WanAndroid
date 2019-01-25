package com.example.yuan_wanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.presenter.IPresenter;
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
 *     desc   :
 * </pre>
 */


public abstract class BaseLoadingFragment<T extends IPresenter> extends BaseMvpFragment<T>{
    private View mLoadingView;
    private ViewGroup mNormalView;
    private LeadTextView mLoadingText;
    private Lead lead ;

    private int mCurrentState = NORMAL_STATE;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
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
        mLoadingView = parent.findViewById(R.id.loadingView);
        mLoadingText = mLoadingView.findViewById(R.id.loadingText);
        mLoadingText.setTypeface(TypefacesUtil.get(mActivity,"Satisfy-Regular.ttf"));
        lead = new Lead(1000);
        mNormalView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);

        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (lead != null) {
            lead.cancel();
        }
        super.onDestroyView();
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

    /**
     * 隐藏当前布局根据mCurrentState
     */
    private void hideCurrentViewByState() {
        switch (mCurrentState) {
            case NORMAL_STATE:
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATE:
                lead.cancel();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                break;
            default:
                break;
        }
    }
}
