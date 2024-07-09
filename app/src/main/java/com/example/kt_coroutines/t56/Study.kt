package com.example.kt_coroutines.t56

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/10
 *Description:
 */

/**
 * 协程Flow的take
 * 快键键control + shift +p: 查看局部选中内容返回的值
 */

/**
 * 限长操作符take
 */
private fun <INPUT> Flow<INPUT>.toTake(number: Int) : Flow<INPUT> {
    require(number > 0){"Requested element count 0 should be positive"}
    return flow {
        var i = 0
        collect{
            if (i++<number){
                return@collect emit(it)
            }
        }
    }
}

fun main() = runBlocking {//单表达式函数

    //Flow的transform变换
    listOf(100, 200, 300, 400).asFlow()
        .toTake(3) //发射4个，但是只取3个
        .collect {
            println(it)
        }
//    100
//    200
//    300
}
