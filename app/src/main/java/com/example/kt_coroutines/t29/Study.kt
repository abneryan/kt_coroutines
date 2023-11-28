package com.example.kt_coroutines.t29

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
    Android 协程CoroutineContext
    协程必须有协程上下文协程CoroutineContext
    Job: 控制协程的生命周期， 协程行为的元素-CoroutineContext
    CoroutineDisPathcher:向合适的线程分发协程任务-CoroutineContext
    CoroutineName:协程名称，测试调试时候有用
    CoroutineExceptionHandler : 处理未被捕捉的异常--CoroutineContext
 */
fun main() = runBlocking<Unit> {
    val job = launch(context = EmptyCoroutineContext) {
        println(Thread.currentThread().name)
    }
}