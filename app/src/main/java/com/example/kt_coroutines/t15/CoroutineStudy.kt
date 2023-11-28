package com.example.kt_coroutines.t15

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 23:52
 * @Description:
 */
/*
    协程join 与await 挂起工作流程
 */

suspend fun requestLoadUser() = withContext(Dispatchers.IO) {
    println("requestLoadUser-start")
    delay(1000 * 10)
}
suspend fun requestLoadUserAssets() = withContext(Dispatchers.IO) {
    println("requestLoadUserAssets-start")
    delay(1000 * 6)
}
suspend fun requestLoaderUserAssetsDetail() =
    withContext(Dispatchers.IO) {
        println("requestLoaderUserAssetsDetail-start")
        delay(1000 * 3)
    }

fun main() = runBlocking<Unit> {

    val d1 = async {
        println("请求用户数据-start")
        requestLoadUser()
        println("请求用户数据")
    }

    val d2 = async {
        println("请求用户资产数据-start")
        requestLoadUserAssets()
        println("请求用户资产数据")
    }

    val d3 = async {
        println("请求用户资产详情数据-start")
        requestLoaderUserAssetsDetail()
        println("请求用户资产详情数据")
    }

    // 并发执行
   /* println(" d1.join()")
    d1.join()
    println(" d2.join()")
    d2.join()
    println(" d3.join()")
    d3.join()*/

    //==========================
    //measureTimeMillis：10054
    val measureTimeMillis = measureTimeMillis {
        d1.await()
        d2.await()
        d3.await()
    }
    println("measureTimeMillis：$measureTimeMillis")
}