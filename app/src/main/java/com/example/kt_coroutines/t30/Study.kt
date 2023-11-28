package com.example.kt_coroutines.t30

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
/*
    Android 组合协程上下文元素

 */
fun main() = runBlocking<Unit> {
    val job = launch(context = Dispatchers.IO + CoroutineName("我是YGQ的协程")) {
        println(Thread.currentThread().name)
    }
}