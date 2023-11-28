package com.example.kt_coroutines.t38

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 00:58
 * @Description:
 */
// 38-Android协程异常捕获时机
// 默认情况下：顶级父协程launch，可以和 exception 关联来捕获  OK
suspend fun main() {
    val exceptionHandler = CoroutineExceptionHandler { _, e ->
        println("你的协程发生了异常e:S$e")
    }
    GlobalScope.launch(exceptionHandler) {
        launch {
            launch {
                launch {
                    throw KotlinNullPointerException("launch 我错了")
                }
            }
        }
    }

    val deferred = GlobalScope.async(exceptionHandler) {
        async {
            async {
                async {
                    throw KotlinNullPointerException("async 我错了")
                }
            }
        }
    }
    try {
        deferred.await()
    } catch (e: Exception) {
        println("deferred 异常 e:$e")
    }

    Thread.sleep(2000)

}