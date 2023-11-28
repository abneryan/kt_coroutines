package com.example.kt_coroutines.t21

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
//协程取消的异常
fun main() = runBlocking<Unit> {

    val job = GlobalScope.launch {
        try {
            delay(1000)
            println("GlobalScope.launch Success!")
        } catch (e: CancellationException) {
            println("协程被取消了：e:$e")
        }
    }
    job.cancel()//协程体中如果不捕获异常，会被静默处理，所以看不到
    job.join()

}