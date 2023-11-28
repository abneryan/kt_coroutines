package com.example.kt_coroutines.t24

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
/*
    协程CPU密集型运算时取消之yield
    yield 函数：
        1，会检测协程的状态，如果已经被取消了，会抛出异常CancellactionException 来响应。
        2, 尝试让出线程疯狂抢占的执行权，给其他协程提供权利（能够 job.cancel）
            尝试让出线程疯狂抢占的执行权，给其他协程提供权利,说白了让出执行权，当我们其他协程空闲时，权利还会是会要回来的
 */
fun main() = runBlocking<Unit> {
    var value = 0
    //Default == 每循环一次计算一次 = CPU密集型运算 = 很密集的运算工作
    val job = launch(context = Dispatchers.Default) {
        val start   = System.currentTimeMillis()
        while (value<Int.MAX_VALUE){
            yield()
            if (System.currentTimeMillis() > start){
                println("while value:${value++}")
            }
        }
    }
    job.cancel()
    launch {
        println("========================")
    }
    println("***********************")

}