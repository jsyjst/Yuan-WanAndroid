package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.Articles;
import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.Tab;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/24
 *     desc   : 项目api集合
 * </pre>
 */


public interface ProjectApis {
    /**
     * 获得大概项目列表
     * http://www.wanandroid.com/project/tree/json
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<Tab>>> getProjectTab();

    /**
     * 获得详细项目列表
     * http://www.wanandroid.com/project/list/1/json?cid=294
     */
    @GET("project/list/{pageNum}/json")
    Observable<BaseResponse<Articles>> getProjectArticles(@Path("pageNum") int pageNum,//页数
                                                   @Query("cid") int id//具体项目列表的id
    );
}
