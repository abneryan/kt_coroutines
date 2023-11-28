package com.example.kt_coroutines.t49

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/28 10:41
 * @Description:
 */
/*
    协程Flow 简化发射源
 */
fun getFlowValue() = "YGQ"
suspend fun getFlowValueSuspend(): String {
    withContext(Dispatchers.IO) {
        delay(1000)
    }
    return "YGQ-suspend"
}


fun <T> (() -> T).toFlow() = flow {
    emit(invoke())
}

private fun String.toFlow() = flow {
    emit(this@toFlow)
}

private fun <O> (suspend () -> O).toFlow() = flow {
    emit(invoke())
}

private fun <E> Iterable<E>.toFlow() = flow {
    this@toFlow.forEach { emit(it) }
}

private fun <T> Sequence<T>.toFlow() = flow {
    this@toFlow.forEach { emit(it) }
}

private fun <T> Array<T>.toFlow() = flow {
    //this@toFlow.forEach { emit(it) }
    repeat(this@toFlow.size) {
        emit(this@toFlow[it])
    }
}

private fun IntArray.toFlow() = flow {
    for (i in this@toFlow) {
        emit(i)
    }
}

private fun LongArray.toFlow() = flow {
    for (i in this@toFlow) {
        emit(i)
    }
}


private fun IntRange.toFlow() = flow {
    this@toFlow.forEach {
        emit(it)
    }
}

private fun LongRange.toFlow() = flow {
    this@toFlow.forEach {
        emit(it)
    }
}


fun main() = runBlocking<Unit> {
    var r: () -> String = ::getFlowValue
    r.toFlow().collect { println(it) }
    getFlowValue().toFlow().collect { println(it) }
    ::getFlowValueSuspend.toFlow().collect { println(it) }

    listOf(1, 2, 3, 4).toFlow().collect { println(it) }

    //sequence方法构建一个序列发生器
    sequence {
        yield("YGQ-1")
        yield("YGQ-2")
        yield("YGQ-3")
    }.toFlow().collect { println(it) }

    arrayOf('A', 'B', 'C').toFlow().collect {
        println(it)
    }
    (1000..1008).toFlow().collect {
        println(it)
    }

    val intArray = intArrayOf(9, 8, 7)
    intArray.toFlow().collect { println(it) }


    //Flow特点  元素是连续的（过滤元素）
    var i = 1
    (100..200).toFlow()
        .filter { it % 2 == 0 }
        .map { "${i++} 好质检员，检查了 第$it 台机器" }
        .map { "[$it]" }
        .collect(::println)
}