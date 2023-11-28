package com.example.kt_coroutines.t12.repository

import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponse
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponseWrapper
import com.example.kt_coroutines.t12.api.APIClient
import com.example.kt_coroutines.t12.api.WanAndroidAPI

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 22:20
 * @Description: 仓库层
 */
class APIRepository {

    //如果你想调用一个挂起函数，自身必须是一个挂起函数
    suspend fun requestLogin(userName:String,userPwd :String):LoginRegisterResponseWrapper<LoginRegisterResponse>{
        return APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java).loginActionCoroutine(userName, userPwd)
    }
}