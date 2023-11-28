package com.example.kt_coroutines.t16

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 11:01
 * @Description:
 */
/*
    CoroutineStart
 */
fun main() = runBlocking<Unit> {
    /*  launch{
          println("launch thread:${Thread.currentThread().name}")
          launch {
              println("launch1 thread:${Thread.currentThread().name}")

          }
      }*/

    /*
       DEFAULT 启动模式：协程体被创建后，协程立即开始调度（调度的准备阶段），在调度前 协程被取消，
                        协程会进入"协程取消响应状态" 然后才会取消协程
     */
    /*
    val job = launch(start = CoroutineStart.DEFAULT) {
          //调度后 执行阶段
          println("launch 在调度后（协程执行阶段）>>> 1")
          delay(1)
          println("launch 在调度后（协程执行阶段）>>> 2")

      }

      //调度前（先有调度准备阶段）
      println("协程立即开始调度中.") //先输出
      //job.cancel() //调度前取消协程
      delay(1)
      job.cancel() //调度中取消协程
      */

    //====================================================

    /*
      ATOMIC 启动模式：协程体被创建后，协程立即开始调度（调度的准备阶段），在调度前 协程被取消，
                       协程体里面不会响应取消状态（不理你），直到第一个挂起点（才理你）才能取消
    */

    /*
    val job = launch(start = CoroutineStart.ATOMIC) {
           //调度后 执行阶段
           println("launch 在调度后（协程执行阶段）>>> 1")
           println("launch 在调度后（协程执行阶段）>>> 2")
           println("launch 在调度后（协程执行阶段）>>> 3")
           delay(1) //协程中的第一个挂起点，才是取消状态响应中，然后取消，所以下面不会执行输出
           println("launch 在调度后（协程执行阶段）全部结束<<<")
       }

       //调度前（先有调度准备阶段）
       println("协程立即开始调度中.") //先输出
       println("协程立即开始调度中..") //先输出
       println("协程立即开始调度中...") //先输出
       job.cancel() //调度前取消协程
     */
    //=======================================

    /*
     LAZY 启动模式：协程体被创建后，协程不会开始调度，会一直等待我们来手动调度（start 非挂起）
     在调度前 协程被取消，协程会进入"协程取消响应状态" 然后才会取消协程 == Default模式
   */
 /*
    val job = launch(start = CoroutineStart.LAZY) {
        //调度后 执行阶段
        println("launch 在调度后（协程执行阶段）>>> 1")
        println("launch 在调度后（协程执行阶段）>>> 2")
        println("launch 在调度后（协程执行阶段）>>> 3")
        delay(1000 * 10) //协程中的第一个挂起点，才是取消状态响应中，然后取消，所以下面不会执行输出
        println("launch 在调度后（协程执行阶段）全部结束<<<")
    }

    job.start() //最常用 （非挂起）
//    job.join()
    println("协程立即开始调度中.") //先输出
    println("协程立即开始调度中..") //先输出
    println("协程立即开始调度中...") //先输出
    job.cancel() //调度前取消协程
    */

    /*
        UNDISPATCHED启动模式：协程体被创建后，立即执行（在当前调用栈线程中执行），没有调度，协程体里面{一直执行到第一个挂起点，然后再执行父协程代码}
        次模式，协程体被创建后，立即执行，没有调度，既然这么快，这个协程是依附在父协程所在的线程

       面试题：协程中 Dispatchers.IO，但是我却想让他在 main线程跑协程，有没有办法？
       答：CoroutineStart.UNDISPATCHED
       特点：由于没有调度中心调度，所以拿不到 Dispatchers.IO 干脆就用 在当前调用栈线程中执行协程，所以是main线程跑协程
     */
    val job = launch(start = CoroutineStart.UNDISPATCHED) {
        //调度后 执行阶段
        println("launch 在调度后（协程执行阶段）>>> 1")
        println("launch 在调度后（协程执行阶段）>>> 2")
        println("launch 在调度后（协程执行阶段）>>> 3")
        delay(1000 * 10) //协程中的第一个挂起点，才是取消状态响应中，然后取消，所以下面不会执行输出
        println("launch 在调度后（协程执行阶段）全部结束<<<")
    }

    println("协程立即开始调度中.") //先输出
    println("协程立即开始调度中..") //先输出
    println("协程立即开始调度中...") //先输出
    job.cancel()

}