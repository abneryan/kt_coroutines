package com.example.kt_coroutines.t56

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/7
 *Description:
 */

/**
 * 协程Flow的transform 转换
 */

fun <T, R> T.mapAction(action: T.() -> R): R = action(this)


fun main() = runBlocking {//单表达式函数
   /*
   //自己写的转换
   listOf(100,200,300,400).asFlow()
        .map {
            it.mapAction { "你好 $it" }
        }
        .collect{
            println(it)
        }*/
//    你好 100
//    你好 200
//    你好 300
//    你好 400

//===============================

    //Flow的transform变换
    listOf(100, 200, 300, 400).asFlow()
        .transform {
            emit("你好 $it")
        }
        .collect {
            println(it)
        }
//    你好 100
//    你好 200
//    你好 300
//    你好 400
}
