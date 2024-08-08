package com.example.kt_coroutines.t82

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

/**
 *@author yanguoqing
 *@data 2024/7/25
 *Description:
 *  1.挂起函数与普通函数式完成不同的类型，所以两者不能互相赋值
 *  2. 非挂起函数或者非协程，不能调用挂起函数
 *  3. 为什么挂起函数，只能被协程或者挂起函数调用？
 *      因为协程或者挂起函数有一个suspend ,会通过Kotlin 编译器隐式的转换成Continueation
 *      传给挂起函数
 */

//此函数类型是：(Double) ->String
fun action1(number: Double): String {
    return number.toString()
}

//此函数类型：suspend (Double) ->String
suspend fun action1Suspend(number: Double): String {
    withContext(Dispatchers.IO) {
        delay(2000)
    }
    return "abner"
}
suspend fun action11Suspend() {
    withContext(Dispatchers.IO) {
        delay(2000)
    }
}

interface ICallback {
    fun successfull(result: String)
}

fun action2Suspend(number: Double, callback: ICallback): Any? {
    thread {
        Thread.sleep(2000)
    }
    callback.successfull("abner")
    return Unit
}

fun action3Suspend(number: Double, continuation: Continuation<String>): Any? {
    thread {
        Thread.sleep(2000)
    }
    continuation.resume("abner")
    return Unit
}

suspend fun customCorouting(lambda: suspend ()->Unit){
    lambda()
}

//分析了Continuation源码，对比ICallback 几乎一模一样
//可以用Java代码调用，action1Suspend，会有两个参数, 所有的suspend挂起函数都有隐式的Continuation
fun main()= runBlocking<Unit> {
    var r1: (Double) -> String = ::action1
    var r2: suspend (Double) -> String = ::action1Suspend
    customCorouting{
       action1Suspend(33.3)
    }

    //挂起概念：
    //1,挂起与恢复，是协程的底层实现
    //2，挂起与恢复的上层实现是挂起suspend fun 来完成的

    //Continuation 是剩余没有执行 需要恢复执行的代码的负责
    GlobalScope.launch {
        val response1 = action1Suspend(1.0)
        //Main线程更新UI
        val response2 = action1Suspend(2.0)
        //Main线程更新UI
        val response3 = action1Suspend(3.0)
        //Main线程更新UI
    }
    Thread.sleep(1000)
}



