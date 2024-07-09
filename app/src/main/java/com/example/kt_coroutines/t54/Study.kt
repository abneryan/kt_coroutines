package com.example.kt_coroutines.t54

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *@author yanguoqing
 *@data 2024/7/7
 *Description:
 */

/**
 * 协程Flow缓冲和flowOn处理背压
 * 1. buffer(100)  设置缓冲区减少背压
 * 2.flowOn(Dispatchers.IO) 切换异步线程
 */

fun getFlow() = flow {
    (1..10).forEach {
        delay(1000)
        emit(it)
        println("生成了$it thread:${Thread.currentThread().name}")
    }
}/*.buffer(100)*/
    .flowOn(Dispatchers.IO)

fun main() = runBlocking {//单表达式函数
//    getFlow().collect{
//        delay(3000)
//        println("接收了$it thread:${Thread.currentThread().name}")
//    }
    val time  = measureTimeMillis {
        getFlow().collect{
            delay(3000)
            println("接收了$it thread:${Thread.currentThread().name}")
        }
    }
    println(time)
}
