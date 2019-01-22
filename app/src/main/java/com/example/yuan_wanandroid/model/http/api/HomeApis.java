package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BannerData;
import com.example.yuan_wanandroid.model.entity.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


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


    /**
     * 首页文章列表
     * http://www.wanandroid.com/article/list/1/json
     */
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<Articles>> getArticles(@Path("pageNum") int pageNum);
}
