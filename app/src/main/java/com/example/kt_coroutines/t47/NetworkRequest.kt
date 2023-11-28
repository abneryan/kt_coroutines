package com.example.kt_coroutines.t47

import kotlinx.coroutines.flow.flow

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/28 00:57
 * @Description:
 */
object NetworkRequest {
    fun uploadRequestAction() = flow {
        println("uploadRequestAction thread：${Thread.currentThread().name}")
        for (item in 1..100) {
            kotlinx.coroutines.delay(1000L)
            emit(item)
        }
    }
}