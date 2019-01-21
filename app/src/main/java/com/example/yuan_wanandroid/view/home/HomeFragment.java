package com.example.yuan_wanandroid.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.base.fragment.BaseMvpFragment;
import com.example.yuan_wanandroid.contract.HomeFragmentContract;
import com.example.yuan_wanandroid.di.module.fragment.HomeFragmentModule;
import com.example.yuan_wanandroid.model.entiy.BannerData;
import com.example.yuan_wanandroid.presenter.HomeFragmentPresenter;
import com.example.yuan_wanandroid.utils.BannerImageLoader;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.view.MainActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.yuan_wanandroid.utils.LogUtil.TAG_COMMON;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 首页
 * </pre>
 */


public class HomeFragment extends BaseMvpFragment<HomeFragmentPresenter> implements HomeFragmentContract.View {

    @Inject
    HomeFragmentPresenter mPresenter;
    @Inject
    @Named("bannerTitles")
    List<String> bannerTitles;
    @Inject
    @Named("bannerImages")
    List<String> bannerImages;
    @BindView(R.id.banner)
    Banner banner;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav_home;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void inject() {
        ((MainActivity) getActivity())
                .getComponent()
                .getHomeFragmentComponent(new HomeFragmentModule())
                .inject(this);
    }

    @Override
    protected HomeFragmentPresenter getPresenter() {
        return mPresenter;
    }


    @Override
    protected void loadData() {
        mPresenter.loadBannerData();
    }

    @Override
    public void showToast(String msg) {
        CommonUtils.toastShow(msg);
    }


    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        if (!CommonUtils.isEmptyList(bannerTitles)) {
            bannerTitles.clear();
            bannerImages.clear();

        }

        //获得标题,图片
        for (BannerData bannerData : bannerDataList) {
            bannerTitles.add(bannerData.getTitle());
            bannerImages.add(bannerData.getImagePath());
        }
        LogUtil.d(TAG_COMMON, bannerTitles.get(0));
        //设置banner
        banner.setImageLoader(new BannerImageLoader())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)//显示圆形指示器和标题（水平显示）
                .setImages(bannerImages)//设置图片集合
                .setBannerAnimation(Transformer.BackgroundToForeground)//设置轮播动画
                .setBannerTitles(bannerTitles)//设置标题集合
                .setIndicatorGravity(BannerConfig.RIGHT)//设置指示器位置，右边
                .setDelayTime(2000)//设置轮播事件间隔
                .start();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }
}
