package com.example.kt_coroutines.t51

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 *@author yanguoqing
 *@data 2024/7/7
 *Description:
 */

/**
 * 协程Flow上下文保存机制
 */

fun getFlow() = flow<String> {
    delay(2000)
    println("上游发送 context: ${Thread.currentThread().name}")
    emit("我是abner")
}
fun getFlow2() = flow<String> {
    withContext(Dispatchers.IO){//打破上下文的保持
        delay(2000)
        println("上游发送 context: ${Thread.currentThread().name}")
        emit("我是abner")
    }
}

fun getFlow3() = flow<String> {
    delay(2000)
    println("上游发送 context: ${Thread.currentThread().name}")
    emit("我是abner")
}.flowOn(Dispatchers.IO) //子线程

fun main() = runBlocking {//单表达式函数
    //可以收集的原因：上游发射 与下游收集是同一个上下文

    //下游收集 收集时候的挂起操作是依靠上游发送的上下文，所以上游发射与下游收集必须保持同一个上下文（上下文保持机制）
    //getFlow2() 没有遵循上下文保存机制
    getFlow3().collect{
        println(it)
        println("下游接收 context: ${Thread.currentThread().name}")

    }
// getFlow()输出结果：
//    上游发送 context: main @coroutine#1
//    我是abner
//    下游接收 context: main @coroutine#1
//=========================================
// getFlow3()输出结果：
//    上游发送 context: DefaultDispatcher-worker-1 @coroutine#2
//    我是abner
//    下游接收 context: main @coroutine#1



}
