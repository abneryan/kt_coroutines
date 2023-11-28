package com.example.kt_coroutines.t31

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
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
    Android CoroutineContext组合协程上下文的继承
    协程继承关系的结论：对于子父协程的情况，子协程会继承父协程的所有元素
    （除了Job,它是子协程自己的实例, 发现子线程名字有时候与父协程名字不同）
 */
fun main() = runBlocking<Unit> {

    //这样才是子父类协程关系
    /*launch {

    }*/

    val scope =
        CoroutineScope(context = Job() + Dispatchers.IO + CoroutineName("自定义协程"))
    val job = scope.launch {
        println(
            "launch 从上下文获取协程信息：${coroutineContext.get(Job)} " +
                    "---当前线程与协程：${Thread.currentThread().name}"
        )
        async {
            println("async 从上下文获取协程信息：${coroutineContext.get(Job)} " +
                    "---当前线程与协程：${Thread.currentThread().name}")
            "YGQ"
        }.await()
        async {
            println("async 从上下文获取协程信息：${coroutineContext.get(Job)} " +
                    "---当前线程与协程：${Thread.currentThread().name}")
        }.await()
        async {
            println("async 从上下文获取协程信息：${coroutineContext.get(Job)} " +
                    "---当前线程与协程：${Thread.currentThread().name}")
            "YGQ"
        }.await()
    }
    job.join()

    /*
        launch 从上下文获取协程信息："自定义协程#2":StandaloneCoroutine{Active}@1f295d0a ---当前线程与协程：DefaultDispatcher-worker-1 @自定义协程#2
        async 从上下文获取协程信息："自定义协程#3":DeferredCoroutine{Active}@2d5087ea ---当前线程与协程：DefaultDispatcher-worker-3 @自定义协程#3
        async 从上下文获取协程信息："自定义协程#4":DeferredCoroutine{Active}@71525e2e ---当前线程与协程：DefaultDispatcher-worker-1 @自定义协程#4
        async 从上下文获取协程信息："自定义协程#5":DeferredCoroutine{Active}@52f843ce ---当前线程与协程：DefaultDispatcher-worker-1 @自定义协程#5
     */
}