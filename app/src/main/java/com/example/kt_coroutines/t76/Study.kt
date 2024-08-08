package com.example.kt_coroutines.t76

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import okhttp3.internal.wait
import java.util.concurrent.atomic.AtomicInteger

/**
 *@author yanguoqing
 *@data 2024/7/15
 *Description:
 */

/**
 * 深刻理解-互相协作的程序
 */

fun main(): Unit = runBlocking<Unit> {//单表达式函数
    //非 互相协作的程序 传统方式 前面一个人干完，后面的人才能接着干
    //getPhone(createPhone())

    //互相协作的程序 协程Coroutine  前面一个人，后面一个人 同时协作干活
    getPhone(createPhone())
    //输出结果：
//        开始生产华为P10
//        消费了一台 华为P10
//        开始生产华为P20
//        消费了一台 华为P20
//        开始生产华为P30
//        消费了一台 华为P30
}

/*fun createPhone(): List<String> {
    val phones = mutableListOf<String>()
    println("开始生产华为P10")
    phones.add("华为P10")
    println("开始生产华为P20")
    phones.add("华为P20")
    println("开始生产华为P30")
    phones.add("华为P30")
    return phones
}

fun getPhone(phones: List<String>) {
    var s = phones[0]
    println("消费了一台 $s")
    s = phones[1]
    println("消费了一台 $s")
    s = phones[2]
    println("消费了一台 $s")
}*/


//=====================================
fun createPhone() = sequence<String> {
    println("开始生产华为P10")
    GlobalScope.launch {
        delay(2000)
    }
    yield("华为P10")

    println("开始生产华为P20")
    GlobalScope.launch {
        delay(2000)
    }
    yield("华为P20")

    println("开始生产华为P30")
    GlobalScope.launch {
        delay(2000)
    }
    yield("华为P30")
}

fun getPhone(phones: Sequence<String>) {
    println("getPhone start")

    var iterator = phones.iterator()

    println("消费了一台 ${iterator.next()}")

    println("消费了一台 ${iterator.next()}")

    println("消费了一台 ${iterator.next()}")
}

