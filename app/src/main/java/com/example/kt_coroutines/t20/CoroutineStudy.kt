package com.example.kt_coroutines.t20

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
//Job取消兄弟协程
fun main() = runBlocking<Unit> {

    //  不用 runBlocking 的 协程协程作用域，自建协程作用域
    val scope = CoroutineScope(context = Dispatchers.Default)
    println("调度前") //调度前
    val job1 = scope.launch {
        //println("launch start 1")//调度后 执行阶段
        delay(1000)
        println("launch end 1")
    }
    //子协程用父协程作用域
    val job2 = scope.launch {
        //println("launch start 2")//调度后 执行阶段
        delay(2000)
        println("launch end 2")
    }
    // scope.cancel() 会统一取消
    job1.cancel() //不会影响兄弟协程 job2
    delay(5000L)
}