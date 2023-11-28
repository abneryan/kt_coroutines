package com.example.kt_coroutines.t28

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
// Android 协程超时的任务
fun main() = runBlocking<Unit> {

    /*
        Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 6000 ms
	at kotlinx.coroutines.TimeoutKt.TimeoutCancellationException(Timeout.kt:184)
	at kotlinx.coroutines.TimeoutCoroutine.run(Timeout.kt:154)
	at kotlinx.coroutines.EventLoopImplBase$DelayedRunnableTask.run(EventLoop.common.kt:508)
	at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:284)
	at kotlinx.coroutines.DefaultExecutor.run(DefaultExecutor.kt:108)
	at java.lang.Thread.run(Thread.java:748)
     */
  /* withTimeout(6000){
       for(i in 1..Int.MAX_VALUE) {
           println("item i:$i")
           delay(1000)
       }
   }*/

    //=============================

    var  result = withTimeoutOrNull(6000){
        for(i in 1..6) {
            println("item i:$i")
            delay(1000)
        }
        "执行完成"
    }

    println("result:${result ?: "执行出现问题！"}")
}