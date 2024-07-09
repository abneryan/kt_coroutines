package com.example.kt_coroutines.t58

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/10
 *Description:
 */

/**
 * 协程Flow的末端操作符 reduce
 */

fun main() = runBlocking {//单表达式函数
    val result = (1..100).asFlow()
        .reduce { p1, p2 -> p1 + p2 }
    println(result)
}
