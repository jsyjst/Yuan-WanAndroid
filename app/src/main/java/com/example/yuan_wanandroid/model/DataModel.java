package com.example.yuan_wanandroid.model;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BannerData;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.http.NetworkHelper;
import com.example.yuan_wanandroid.model.http.NetworkHelperImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   :
 * </pre>
 */


public class DataModel implements NetworkHelper {

    private NetworkHelper mNetworkHelper;

    @Inject
    public DataModel(NetworkHelperImpl networkHelper){
        mNetworkHelper = networkHelper;
    }

    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mNetworkHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<Articles>> getArticles(int pageNum) {
        return mNetworkHelper.getArticles(pageNum);
    }
}
