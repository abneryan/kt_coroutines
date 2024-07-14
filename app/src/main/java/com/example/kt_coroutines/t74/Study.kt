package com.example.kt_coroutines.t74

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/14
 *Description:
 */

/**
 * 协程安全并发问题
 */

fun main() : Unit= runBlocking<Unit> {//单表达式函数
    var i = 0

    List(10000){
        GlobalScope.launch {
            i++
        }
    }
    println(i)
}

