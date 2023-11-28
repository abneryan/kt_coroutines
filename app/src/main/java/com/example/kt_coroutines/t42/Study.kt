package com.example.kt_coroutines.t42

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 12:19
 * @Description:
 */
//协程取消时异常处理
fun main() = runBlocking<Unit> {//顶级父协程

    //父协程
    /*
    launch {
         val job2 = launch {
             try {
                 delay(1000 * 10)
             } catch (e: Exception) {
                 println("你被取消了 e:$e")
             } finally {
                 println("子协程 finally end") // 子协程的 delay被取消了，抛出异常
             }
         }
         //取消子协程
         delay(1000)
         job2.cancel()

         job2.join()
         delay(3000)
         println("父协程 执行了")
     }
     */

    //==============================================

    // 当一个 子协程抛出 非 JobCancellationException 的时候：
    // 1.子协程 上报给父协程 直到 顶级协程为止
    // 2.顶级协程 会 取消他所有的 子协程(协程的finally会执行)
    // 3.顶级协程 取消自己
    // 4.处理异常 如果没有处理，就奔溃，  如果处理了，CoroutineExceptionHandler 就会捕捉

    // 非 JobCancellationException 他会上报给 父协程 父协程 直到 顶级协程为止，处理异常 或 奔溃
    val exception = CoroutineExceptionHandler { _, e ->
        println("你报错了")
    }

    //顶级父协程
    val job = GlobalScope.launch(exception) {
        //子协程
        launch {
            try {
                delay(1000 * 20)
                println("子协程1 launch")
            } catch (e: Exception) {

            } finally {
                println("子协程1 finally end") // 子协程的 delay被取消了，抛出异常   // 2
            }
        }

        // 子协程二
        launch {
            delay(3000)
            println("抛出 非 JobCancellationException") // 1
            throw CancellationException("子协程二 抛出的哦")
        }
    }
    job.join()
}