package com.example.kt_coroutines.t27

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileReader

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
// Android无法取消的协程任务
fun main() = runBlocking<Unit> {
    val job = launch {
        try {
            for (i in 1..Int.MAX_VALUE) {
                println("item i:$i")
                delay(1000)
            }
        } finally {
            //默认情况下，哪怕是写在finally里面的代码，还是可以被取消的
            /*for(i in 1..Int.MAX_VALUE){
                println("item i:$i")
                delay(1000)
            }*/
            //NonCancellable 不响应取消，不理睬job.cancel() ，所以里面的代码一定会执行，无法被取消
            withContext(NonCancellable){
                for(i in 1..Int.MAX_VALUE) {
                    println("item i:$i")
                    delay(1000)
                }
            }

        }
    }
    //并不是NonCancellable只能放在finally里面，只要你想这段代码不被取消，局可以用NonCancellable
    val job1 = launch {
        withContext(NonCancellable){
            for(i in 1..Int.MAX_VALUE) {
                println("item i:$i")
                delay(1000)
            }
        }
    }
    //调度前 被取消，协程会进入取消响应操作，然后取消
    //job.cancel()
    delay(3000)
    job.cancel()
    println("cancel 协程被取消了")
}