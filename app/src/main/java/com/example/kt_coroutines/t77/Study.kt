package com.example.kt_coroutines.t77

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/15
 *Description:
 */

/**
 * 深刻理解-互相协作的程序之Channel
 */

fun main(): Unit = runBlocking<Unit> {//单表达式函数
    //互相协作的程序 协程Coroutine  前面一个人，后面一个人 同时协作干活
    val phones = createPhone(this)
    getPhone(phones)
    //输出结果：
//    开始生产华为P10
//    消费了一台 华为P10
//    开始生产华为P20
//    消费了一台 华为P20
//    开始生产华为P30
//    消费了一台 华为P30
}

//=====================================
fun createPhone(scope: CoroutineScope) = scope.produce<String> {
    println("开始生产华为P10")
//    GlobalScope.launch {
//        delay(2000)
//    }
    send("华为P10")

    println("开始生产华为P20")
//    GlobalScope.launch {
//        delay(2000)
//    }
    send("华为P20")

    println("开始生产华为P30")
//    GlobalScope.launch {
//        delay(2000)
//    }
    send("华为P30")
}

suspend fun getPhone(phones: ReceiveChannel<String>) { println("消费了一台 ${phones.receive()}")
    delay(2000)
    println("消费了一台 ${phones.receive()}")
    delay(2000)
    println("消费了一台 ${phones.receive()}")
}