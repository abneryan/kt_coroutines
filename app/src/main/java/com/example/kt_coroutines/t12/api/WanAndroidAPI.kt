package com.example.kt_coroutines.t12.api

import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponse
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponseWrapper
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/20 23:18
 * @Description: 客户端API 可以访问服务器的api
 */
interface WanAndroidAPI {
    //登录api
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(
        @Field("username") username: String,
        @Field("password") password: String
    )
            : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>

    //注册api
    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    )
            : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>

    //  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 下面是协程API

    /** https://www.wanandroid.com/blog/show/2
     * 登录API
     * username=Derry-vip&password=123456
     */
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun loginActionCoroutine(
        @Field("username") username: String,
        @Field("password") password: String
    )
            : LoginRegisterResponseWrapper<LoginRegisterResponse> // 返回值

    /** https://www.wanandroid.com/blog/show/2
     * 注册的API
     */
    @POST("/user/register")
    @FormUrlEncoded
    suspend fun registerActionCoroutine(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    )
            : LoginRegisterResponseWrapper<LoginRegisterResponse> // 返回值
}