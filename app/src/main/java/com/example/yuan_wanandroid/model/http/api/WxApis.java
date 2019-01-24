package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.Tab;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 公共号api集合
 * </pre>
 */


public interface WxApis {
    /**
     * 获得公众号tab
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<Tab>>> getWxTabs();

    /**
     * 获得某个公共号的文章列表
     * http://wanandroid.com/wxarticle/list/408/1/json
     * pageNum默认从1开始
     */
    @GET("wxarticle/list/{id}/{pageNum}/json")
    Observable<BaseResponse<Articles>> getWxArticles(
            @Path("pageNum") int pageNum,//某个公众号的页码
            @Path("id") int id //某个公众号id
    );
}
