package com.example.kt_coroutines.t59

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *@author yanguoqing
 *@data 2024/7/10
 *Description:
 */

/**
 * 协程Flow的zip 合并
 *
 * FLow 是异步流
 */

private fun getName() = listOf("zhangsan", "lisi", "wangwu").asFlow().onEach { delay(1000) }
private fun getAge() = listOf("1", "2", "3").asFlow().onEach { delay(2000) }

fun main() = runBlocking {//单表达式函数
    val time = measureTimeMillis {
        getName().zip(getAge()) { p1, p2 ->
            "name:$p1, age:$p2"
        }.collect {
            println(it)
        }
    }
    println(time)

//    输出结果：
//    name:zhangsan, age:1
//    name:lisi, age:2
//    name:wangwu, age:3
//    6077
}
