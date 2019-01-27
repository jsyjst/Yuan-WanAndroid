package com.example.yuan_wanandroid.model;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BannerData;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.FirstSystem;
import com.example.yuan_wanandroid.model.entity.Tab;
import com.example.yuan_wanandroid.model.entity.Login;
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

    @Override
    public Observable<BaseResponse<List<FirstSystem>>> getFirstSystemData() {
        return mNetworkHelper.getFirstSystemData();
    }

    @Override
    public Observable<BaseResponse<Articles>> getSecondSystemArticles(int pageNum, int id) {
        return mNetworkHelper.getSecondSystemArticles(pageNum,id);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getWxTabs() {
        return mNetworkHelper.getWxTabs();
    }

    @Override
    public Observable<BaseResponse<Articles>> getWxArticles(int pageNum, int id) {
        return mNetworkHelper.getWxArticles(pageNum,id);
    }

    @Override
    public Observable<BaseResponse<List<Tab>>> getProjectTab() {
        return mNetworkHelper.getProjectTab();
    }

    @Override
    public Observable<BaseResponse<Articles>> getProjectArticles(int pageNum, int id) {
        return mNetworkHelper.getProjectArticles(pageNum,id);
    }

    @Override
    public Observable<BaseResponse<Login>> register(String username, String password, String rePassword) {
        return mNetworkHelper.register(username, password, rePassword);
    }

    @Override
    public Observable<BaseResponse<Login>> login(String username, String password) {
        return mNetworkHelper.login(username, password);
    }

    @Override
    public Observable<BaseResponse<Login>> logout() {
        return mNetworkHelper.logout();
    }

    @Override
    public Observable<BaseResponse> collectArticles(int id) {
        return mNetworkHelper.collectArticles(id);
    }

    @Override
    public Observable<BaseResponse> unCollectArticles(int id) {
        return mNetworkHelper.collectArticles(id);
    }
}
