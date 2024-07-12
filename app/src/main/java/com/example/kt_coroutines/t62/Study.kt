package com.example.kt_coroutines.t62

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/11
 *Description:
 */

/**
 * 协程Flow的completion
 *
 * FLow 是异步流
 */

fun getNumbers() = arrayOf(1, 2, 3, 4, 5, 6).asFlow().onEach { delay(1000) }

fun getNumbers2() = flow {
    for (i in arrayOf(1)) {
        emit(i)
        throw KotlinNullPointerException("上游抛出了异常")
    }
}


fun main() = runBlocking<Unit> {//单表达式函数
    //1,正常的结束（命令式）
    /* try {
         getNumbers().collect{
             println(it)
         }
     } finally {
         println("协程Flow结束了")
     }*/

    //2,正常结束（声明式）
//    getNubmers().onCompletion { println("协程Flow结束了") }.collect{println(it)}

    //3.异常的结束（声明式） 上游发生了异常
    /* getNumbers2().onCompletion {
         if (it != null) {
             println("上游发生了异常：$it")
         }
     }
         .catch { println("被catch 到了 上游发生了异常 $it") } //catch 是能捕获到上游抛出的异常
         .collect {
             println(it)
         }*/
//    1
//    上游发生了异常：kotlin.KotlinNullPointerException: 上游抛出了异常
//    被catch 到了 上游发生了异常 kotlin.KotlinNullPointerException: 上游抛出了异常

    // 4. 异常的结束（声明式）下游发生异常
    getNumbers()
        .onCompletion {
            if (it!= null){
                println("下游 发生了异常 $it")
            }
        }
        .catch { println("被catch到了下游发生了异常 it$it") }//.cathc 是不能捕获到下游异常的
        .collect{
            println(it)
            if(it == 5){
                throw KotlinNullPointerException("下游异常抛出")
            }
        }

//    总结：
//    上游的异常抛出，可以使用声明式
//    下游的异常退出, 可以使用命令式
//    onCompletion（声明式） 上游与下游的异常信息都能够知道，获取到
//    onCompletion （声明式）正常的结束 还是异常的结束，都能知道
//    finally 能够知道正常的结束（命令式）
}
