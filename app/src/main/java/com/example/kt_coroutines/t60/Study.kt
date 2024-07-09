package com.example.kt_coroutines.t60

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
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
 * 协程Flow的flatMap 展平操作符
 *
 * FLow 是异步流
 */

fun getNubmers() = (1..6).asFlow()

fun runWork(inputValue:Int) = flow {
    emit("$inputValue 号员工开始工作了")
    delay(1000)
    emit("$inputValue 号员工结束工作了")
}

fun main() = runBlocking {//单表达式函数
    getNubmers()
        .onEach { delay(1000) }
        .flatMapConcat { runWork(it) }
        .collect{ println(it) }

//输出结果：
//            1 号员工开始工作了
//            1 号员工结束工作了
//            2 号员工开始工作了
//            2 号员工结束工作了
//            3 号员工开始工作了
//            3 号员工结束工作了
//            4 号员工开始工作了
//            4 号员工结束工作了
//            5 号员工开始工作了
//            5 号员工结束工作了
//            6 号员工开始工作了
//            6 号员工结束工作了
}
