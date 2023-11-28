package com.example.kt_coroutines.t36

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 00:58
 * @Description:
 */
/*
    Android 协程SupervisorJob()
    //异常时候不会影响兄弟协程
 */
fun main() = runBlocking<Unit> {
   val coroutineScope = CoroutineScope(SupervisorJob())

    val job1 = coroutineScope.launch {
        println("lauch1 start")
        delay(1000)
        throw  KotlinNullPointerException("is null")
        println("lauch2 end")
    }
    val  job2 =coroutineScope.launch {
        println("lauch2 start")
        delay(1000)
        println("lauch2 end")
    }
    //runBlocking 协程作用域会等待 job1 job2的协程
    joinAll(job1,job2)
}