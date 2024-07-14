package com.example.kt_coroutines.t72

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 *@author yanguoqing
 *@data 2024/7/13
 *Description:
 */

/**
 * 协程selec 与onJoin
 */


fun main() = runBlocking<Unit> {//单表达式函数

    var job1 =launch {
        delay(3000)
        println("launch1 run")
    }
    val job2 = launch {
        delay(5000)
        println("launch2 run")
    }

     select<Unit> {
         job1.onJoin{
             println("launch1 执行完成")
         }
         job2.onJoin{
             println("launch2 执行完成")
         }
    }
}

