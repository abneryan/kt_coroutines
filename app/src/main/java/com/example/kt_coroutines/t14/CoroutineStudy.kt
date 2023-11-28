package com.example.kt_coroutines.t14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 23:52
 * @Description:
 */
/*
    协程join 与await 挂起工作流程
 */

private suspend fun requestLoadUser() = withContext(Dispatchers.IO) { delay(1000 * 10) }
private suspend fun requestLoadUserAssets() = withContext(Dispatchers.IO) { delay(1000 * 6) }
private suspend fun requestLoaderUserAssetsDetail() =
    withContext(Dispatchers.IO) { delay(1000 * 3) }

fun main() = runBlocking<Unit> {
    /*
    日志输出：

    第一次请求准备开始中>>>
    请求用户数据
    第一次请求准全部完成<<<
    第二次请求准备开始中>>>
    请求用户资产数据
    第二次请求准备开始中<<<
    第三次请求准备开始中>>>
    请求用户资产详情数据
    第三次请求准备开始中<<<

    val job1 = launch {
        requestLoadUser()
        println("请求用户数据")
    }
    //协程join 挂起
    println("第一次请求准备开始中>>>")
    job1.join() //等待job1 协程执行完毕后 挂起恢复
    println("第一次请求准全部完成<<<")

    val job2 = launch {
        requestLoadUserAssets()
        println("请求用户资产数据")
    }
    //协程join 挂起
    println("第二次请求准备开始中>>>")
    job2.join()
    println("第二次请求准备开始中<<<")

    val job3 = launch {
        requestLoaderUserAssetsDetail()
        println("请求用户资产详情数据")
    }
    println("第三次请求准备开始中>>>")
    job3.join()
    println("第三次请求准备开始中<<<")
    */
    //=========================================


    /*
    第一次请求准备开始中>>>
    请求用户数据
    第一次请求准全部完成<<<
    第二次请求准备开始中>>>
    请求用户资产数据
    第二次请求准备开始中<<<
    第三次请求准备开始中>>>
    请求用户资产详情数据
    第三次请求准备开始中<<<

    val d1 = async {
        requestLoadUser()
        println("请求用户数据")
    }
    //协程join 挂起
    println("第一次请求准备开始中>>>")
    d1.await() //等待job1 协程执行完毕后 挂起恢复
    println("第一次请求准全部完成<<<")

    val d2 = async {
        requestLoadUserAssets()
        println("请求用户资产数据")
    }
    //协程join 挂起
    println("第二次请求准备开始中>>>")
    d2.await()
    println("第二次请求准备开始中<<<")

    val d3 = async {
        requestLoaderUserAssetsDetail()
        println("请求用户资产详情数据")
    }
    println("第三次请求准备开始中>>>")
    d3.await()
    println("第三次请求准备开始中<<<")
    */
    //=======================================

    /*
       日志输出：
        1
        async start
        async end
        zhangsan
        2
     */
    val d = async {
        println("async start") //第2步
        delay(1000*10)
        println("async end")//第3步
        "zhangsan" //第4步
    }

    println("1") //第1步
    println(d.await()) //第5步
    println("2") //第6步

}