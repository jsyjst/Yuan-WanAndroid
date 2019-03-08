package com.example.yuan_wanandroid.model;

import com.example.yuan_wanandroid.model.db.DbHelper;
import com.example.yuan_wanandroid.model.db.DbHelperImpl;
import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BannerData;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.Collections;
import com.example.yuan_wanandroid.model.entity.FirstSystem;
import com.example.yuan_wanandroid.model.entity.HotKey;
import com.example.yuan_wanandroid.model.entity.Tab;
import com.example.yuan_wanandroid.model.entity.Login;
import com.example.yuan_wanandroid.model.http.NetworkHelper;
import com.example.yuan_wanandroid.model.http.NetworkHelperImpl;
import com.example.yuan_wanandroid.model.prefs.PreferencesHelper;
import com.example.yuan_wanandroid.model.prefs.PreferencesHelperImpl;

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


public class DataModel implements NetworkHelper ,DbHelper,PreferencesHelper{

    private NetworkHelper mNetworkHelper;
    private DbHelper mDbHelper;
    private PreferencesHelper mPreferencesHelper;


    @Inject
    public DataModel(NetworkHelperImpl networkHelper, DbHelperImpl dbHelp, PreferencesHelperImpl preferencesHelper){
        mNetworkHelper = networkHelper;
        mDbHelper =dbHelp;
        mPreferencesHelper = preferencesHelper;
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

    @Override
    public Observable<BaseResponse<Collections>> getCollectionsData(int pageNum) {
        return mNetworkHelper.getCollectionsData(pageNum);
    }

    @Override
    public Observable<BaseResponse> unCollection(int id,int originId) {
        return mNetworkHelper.unCollection(id,originId);
    }

    @Override
    public Observable<BaseResponse<List<HotKey>>> getHotKey() {
        return mNetworkHelper.getHotKey();
    }

    @Override
    public Observable<BaseResponse<Articles>> getSearchArticles(String key, int pageNum) {
        return mNetworkHelper.getSearchArticles(key, pageNum);
    }

    @Override
    public boolean addHistory(String key) {
        return mDbHelper.addHistory(key);
    }

    @Override
    public int deleteOneHistory(String key) {
        return mDbHelper.deleteOneHistory(key);
    }

    @Override
    public int deleteAllHistory() {
        return mDbHelper.deleteAllHistory();
    }

    @Override
    public boolean isExistHistory(String key) {
        return mDbHelper.isExistHistory(key);
    }

    @Override
    public List<String> getAllHistory() {
        return mDbHelper.getAllHistory();
    }

    @Override
    public void setNightStyleState(boolean isNight) {
        mPreferencesHelper.setNightStyleState(isNight);
    }

    @Override
    public boolean getNightStyleState() {
        return mPreferencesHelper.getNightStyleState();
    }

    @Override
    public void setNoImgState(boolean isNoImg) {
        mPreferencesHelper.setNoImgState(isNoImg);
    }

    @Override
    public boolean getNoImgStyleState() {
        return mPreferencesHelper.getNoImgStyleState();
    }
}
