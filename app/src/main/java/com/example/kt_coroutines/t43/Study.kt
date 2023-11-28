package com.example.kt_coroutines.t43

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 12:19
 * @Description:
 */
//协程的异常聚合
fun main() = runBlocking<Unit> {//顶级父协程
    val exception = CoroutineExceptionHandler { _, e ->
        println("你错了:$e  多个异常:${e.suppressed.contentToString()}")
    }

    // 当一个 子协程抛出 非 JobCancellationException 的时候：
    // 1.子协程 上报给父协程 直到 顶级协程为止
    // 2.顶级协程 会 取消他所有的 子协程
    // 3.顶级协程 取消自己
    // 4.处理异常 如果没有处理，就奔溃，  如果处理了，CoroutineExceptionHandler 就会捕捉

    //顶级协程
    val job = GlobalScope.launch(exception) {
        launch {
            delay(3000)
            throw KotlinNullPointerException("launch1 异常")
        }

        launch {
            try {
                delay(20000000)
            } finally {
                throw KotlinNullPointerException("launch2 异常")
            }
        }

        // 不属于 子协程
        // 父子协程的 结构化并发被你打乱了
        launch(Job()) {
            try {
                delay(20000000)
            } finally {
                throw KotlinNullPointerException("launch3 异常")
            }
        }

        launch {
            try {
                delay(20000000)
            } finally {
                throw KotlinNullPointerException("launch4 异常")
            }
        }

        launch {
            try {
                delay(20000000)
            } finally {
                throw KotlinNullPointerException("launch5 异常")
            }
        }

        launch {
            try {
                delay(20000000)
            } finally {
                throw KotlinNullPointerException("launch6 异常")
            }
        }
    }
    job.join()
    /*
        日志输出：
        你错了:kotlin.KotlinNullPointerException: launch1 异常  多个异常:[kotlin.KotlinNullPointerException: launch2 异常, kotlin.KotlinNullPointerException: launch6 异常, kotlin.KotlinNullPointerException: launch5 异常, kotlin.KotlinNullPointerException: launch4 异常]
     */
}