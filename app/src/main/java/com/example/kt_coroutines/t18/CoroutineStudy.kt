package com.example.kt_coroutines.t18

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
//Android 协程Job 生命周期
fun main() = runBlocking<Unit> {
    /*
    val job = launch {
        println("Active存活了")
        delay(1000 * 10)
        println("delay 完成了")

    }
    common("协程体被创建的正常情况",job)
    delay(20)
    job.cancel()
    common("协程体被取消了 cancel",job)
    delay(50)
    common("从取消到完成中的一个50ms的挂起",job)
    */
    //=============================================================
    //从创建活跃到自然完成
    val job = launch {
        println("launch Active存活了")
        delay(1000 * 10)
        println("launch delay 完成了")
    }
    common("协程体被创建的正常情况",job)

    delay(1000 * 5)
    common("5秒钟后，看看状态",job)

    delay(1000*4)
    common("4秒钟后，看看状态",job)

    delay(1000*2)
    common("2秒钟后，看看状态",job)

    /*
        协程体被创建的正常情况
        活跃中
        未取消
        未完成

        launch Active存活了
        5秒钟后，看看状态
        活跃中
        未取消
        未完成

        4秒钟后，看看状态
        活跃中
        未取消
        未完成

        launch delay 完成了
        2秒钟后，看看状态
        未活跃
        未取消
        已完成
     */

}

private fun common(value:String,job:Job){
    println("""
        $value
        ${if (job.isActive) "活跃中" else "未活跃"}
        ${if (job.isCancelled) "已取消" else "未取消"}
        ${if (job.isCompleted) "已完成" else "未完成"}
        
    """.trimIndent())
}