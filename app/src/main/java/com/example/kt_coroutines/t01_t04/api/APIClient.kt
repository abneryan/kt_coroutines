package com.example.kt_coroutines.t01_t04.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/20 22:31
 * @Description: 访问服务器的API接口(WanAndroidAPI) 的客户端管理
 */
class APIClient private constructor() {
    //对应Java的静态内部类的方式
    private object Holder {
        val INSTANCE = APIClient()
    }

    companion object {
        val instance = Holder.INSTANCE;
    }

    fun <T> instanceRetrofit(apiInterface: Class<T>): T {
        var okHttpClient = OkHttpClient().newBuilder().mApply{
            readTimeout(10000, TimeUnit.SECONDS) //读取超时时间
            connectTimeout(10000, TimeUnit.SECONDS)//连接超时时间
            writeTimeout(10000, TimeUnit.SECONDS)//写超时时间
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(okHttpClient)//请求方
            //响应方
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Rxjava来处理
            .addConverterFactory(GsonConverterFactory.create()) //Gson 来解析====JavaBean
            .build()

        return retrofit.create(apiInterface)
    }

    //泛型拓展函数
    private fun <T> T.mApply(mm: T.() -> Unit): T {
        mm(this)
        return this
    }
}
