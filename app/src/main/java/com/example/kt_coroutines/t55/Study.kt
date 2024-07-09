package com.example.kt_coroutines.t55

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *@author yanguoqing
 *@data 2024/7/7
 *Description:
 * conflate():合并
 * collectLatest:最新、最后一个
 */

/**
 * 协程Flow合并和处理最新值
 */

fun getFlow() = flow {
    (1..10).forEach {
        delay(1000)
        emit(it)
        println("生成了$it thread:${Thread.currentThread().name}")
    }
}

fun main() = runBlocking {//单表达式函数
    val time = measureTimeMillis {
        getFlow()
            //.conflate() 合并
            .collectLatest {
                delay(3000)
                println("接收了$it thread:${Thread.currentThread().name}")
            }
    }
    println(time)
}
