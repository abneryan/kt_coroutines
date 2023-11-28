package com.example.kt_coroutines.t19

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
//取消协程当前作用域
//runBlocking 会阻塞主线程来等待所有子协程任务全部结束
fun main() = runBlocking<Unit> {
    /*
       //在打
       //子协程用父协程作用域
       launch {
           println("launch start 1")
           delay(1000)
           println("launch end 1")
       }

       //子协程用父协程作用域
       launch {
           println("launch start 2")
           delay(2000)
           println("launch end 2")
       }
       delay(50)
       cancel() //在父协程中取消抛出JobCancellationException 所有子协程会跟着取消
       */

    val scope: CoroutineScope = CoroutineScope(context = Dispatchers.Default)
    println("调度前") //调度前
    scope.launch {
        println("launch start 1")//调度后 执行阶段
        delay(1000)
        println("launch end 1")

    }
    //子协程用父协程作用域
    scope.launch {
        println("launch start 2")//调度后 执行阶段
        delay(2000)
        println("launch end 2")
    }
    // scope.cancel() 会统一取消

    //main thread 结束了， runBlocking 不会等我们自己的scope，因为runBlocking只会等自己的子协程
}