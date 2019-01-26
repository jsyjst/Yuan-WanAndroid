package com.example.yuan_wanandroid.model.http.api;

import com.example.yuan_wanandroid.model.entity.BaseResponse;
import com.example.yuan_wanandroid.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 个人模块接口集合
 * </pre>
 */


public interface PersonApis {
    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<User>> login(
            @Field("username") String userName,
            @Field("password") String password
    );


    /**
     * 注册
     * http://www.wanandroid.com/user/register
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<User>> register(@Field("username") String username,
                                                      @Field("password") String password,
                                                      @Field("repassword") String rePassword//确认密码
    );
}
