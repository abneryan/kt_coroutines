package com.example.kt_coroutines.t22

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
//协程CPU密集型运算时取消之isActive
fun main() = runBlocking<Unit> {
    var value = 0
    //Default == 每循环一次计算一次 = CPU密集型运算 = 很密集的运算工作
    val job = launch(context = Dispatchers.Default) {
        val start   = System.currentTimeMillis()
        while (value<Int.MAX_VALUE && isActive){
            if (System.currentTimeMillis() > start){
                println("while value:${value++}")
            }
        }
    }
    //调度前
    job.cancel()
}