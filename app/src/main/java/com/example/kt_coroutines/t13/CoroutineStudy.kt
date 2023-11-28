package com.example.kt_coroutines.t13

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 22:48
 * @Description:
 */
/*
    = runBlocking<Unit>{//把main thread 变成在mian thread 依附执行的协程
    一般在学习和测试时候会使用runBlocking 因为测试 学习非常方便，不需要额外的睡眠等待协程体
    父协程等子协程

    以前说的协程是非阻塞的，而现在runBlocking 是阻塞的？
    答：说的是runBlocking 会阻塞main thread ，为什么要阻塞main thread，是为了等待所有的子协程完成后，才能结束 main thread
    注意：协程块永远是非阻塞的
 */

fun main() = runBlocking<Unit>{//父协程
//    GlobalScope.launch {
//        println("start")
//        delay(6000L)
//        println("end")
//    }
    //main thread 是不可能等协程体的，所以下面的代码是等一等协程体
    //Thread.sleep(10000L)

    // 子协程
    val job = launch(){
        println("coroutine launch start thread:${Thread.currentThread().name}") // ②  // 他到底是不是第一次打印，就会非常纠结，因为以前说的是 协程非阻塞 而现在 runBlocking是阻塞的
        delay(10000)
        println("coroutine launch end thread:${Thread.currentThread().name}") // ④
    }

    // 子协程
    val deferred = async {
        println("coroutine async start thread:${Thread.currentThread().name}") // ③
        delay(11000)
        println("coroutine async end thread:${Thread.currentThread().name}") // ⑤
        "李元霸"
    }

    println("deferred 零 test derry thread:${Thread.currentThread().name}") // ①

    println("deferred 一 :${deferred.await()}") // ⑥ // TODO 他是不是 第二个打印，我们说不是，为什么，因为他是挂起函数 挂起多久 什么时候恢复 等下讲

    delay(300) // 睡眠300毫秒

    println("deferred 二 :${deferred.await()}") // ⑦  直接返回数据

    // 父协程 等 子协程
    // 一般学习和测试会用 runBlocking，因为测试和学习非常方便，不需要额外去 睡眠等待 协程体

    /*
        deferred 零 test derry thread:main
        coroutine launch start thread:main
        coroutine async start thread:main
        coroutine launch end thread:main
        coroutine async end thread:main
        deferred 一 :李元霸
        deferred 二 :李元霸
     */
}