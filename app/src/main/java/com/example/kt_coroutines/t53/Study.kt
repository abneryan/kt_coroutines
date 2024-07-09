package com.example.kt_coroutines.t53

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/7
 *Description:
 */

/**
 * 协程Flow取消和检测
 */

fun getFlow() = flow {
    (1..100).forEach{emit(it)}
}.onEach { delay(100) }

fun main() = runBlocking {//单表达式函数
    (1..100).asFlow().cancellable().collect{
        println(it)
        if(it == 6) cancel()
    }
//    getFlow().collect{
//        println(it)
//        if (it ==5) cancel()
//    }
}
