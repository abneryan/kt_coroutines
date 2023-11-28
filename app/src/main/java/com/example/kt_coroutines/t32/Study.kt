package com.example.kt_coroutines.t32

import kotlinx.coroutines.CoroutineExceptionHandler
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

fun main() {
    val exception = CoroutineExceptionHandler { _, e ->
        println("协程发生了异常：e:$e")
    }

    // CoroutineName = coroutine                   如果子协程没有，就用父类的，  如果子协程有，就有子类的
    // CoroutineDispatcher == Dispatchers.Default  如果子协程没有，就用父类的   如果子协程有，就有子类的
    // CoroutineExceptionHandler                   如果子协程没有，就用父类的   如果子协程有，就有子类的

    // Job == 子协程自己的Job实例                    如果子协程没有，就用实例化子类的Job，   如果子协程有，就有子类的
    val scope =
        CoroutineScope(Job() + exception + Dispatchers.Main + CoroutineName("Coroutine FU"))
    scope.launch(Dispatchers.Default + CoroutineName("Coroutine ZI")) {
        launch {
            println(
                "launch1 从上下文获取协程信息：${coroutineContext.get(Job)} " +
                        "---当前线程与协程：${Thread.currentThread().name}"
            )
        }
        launch {
            println(
                "launch2 从上下文获取协程信息：${coroutineContext.get(Job)} " +
                        "---当前线程与协程：${Thread.currentThread().name}"
            )
        }
    }

    Thread.sleep(1000)
}