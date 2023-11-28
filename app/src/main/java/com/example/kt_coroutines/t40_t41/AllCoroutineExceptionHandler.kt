package com.example.kt_coroutines.t40_t41

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 12:02
 * @Description:
 */
/*
    怎样全局应用该AllCoroutineExceptionHandler
        1，新建resources 文件夹
        2，在resources 文件夹下建META-INF文件夹
        3,在META-INF文件夹下建kotlinx.coroutines.CoroutineExceptionHandler文件
        4，在kotlinx.coroutines.CoroutineExceptionHandler文件中添加com.example.kt_coroutines.t40.AllCoroutineExceptionHandler
 */
class AllCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.d("YGQ", "全局异常捕获 handleException exception:$exception")
    }
}