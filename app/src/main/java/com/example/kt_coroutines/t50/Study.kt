package com.example.kt_coroutines.t50

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/28 16:33
 * @Description:
 */
/*
    协程Flow防listOf构建
 */

private fun <T> flows(value: T) = flow { emit(value) }
private fun <T> T.flows2() = flow { emit(this@flows2) }
private fun <T> flowOfs(vararg values:T) = flow {
    repeat(values.size){
        emit(values[it])
    }
}


fun main() = runBlocking<Unit> {
    "YGQ".flows2().collect{
        println(it)
    }

    flows("YGQ").collect{ println(it)}

    flowOfs("张三","李四","王五").collect{ println(it)}
    flowOf("张三1","李四1","王五1").collect{ println(it)}
}