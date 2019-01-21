package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entiy.BannerData;
import com.example.yuan_wanandroid.model.entiy.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/21
 *     desc   : 首页模块的网络api集合
 * </pre>
 */


public interface HomeApis {

    /**
     * 首页banner，即轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();
}
