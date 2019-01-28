package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.HotKey;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/28
 *     desc   : 搜索
 * </pre>
 */


public interface SearchApis {
    /**
     * 获取搜索热词
     * http://www.wanandroid.com//hotkey/json
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKey>>> getHotKey();
    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     */
    @POST("article/query/{pageNum}/json")
    @FormUrlEncoded
    Observable<BaseResponse<Articles>> getSearchArticles(@Field("k") String key,//关键字
                                                        @Path("pageNum") int pageNum//页数
    );
}
