package com.example.kt_coroutines.t64

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/11
 *Description:
 */

/**
 * 协程Flow的channel 进行协程通信
 */



fun main() = runBlocking<Unit> {//单表达式函数
    val channel:Channel<Int> = Channel<Int>()
    launch {
        (1..6).forEach {
            delay(1000)
            channel.send(it)
            println("我生产了一个： $it")
        }
    }

    launch {
        (1..6).forEach {
            val receive = channel.receive()
            println("我消费了一个： $receive")
        }
    }
}
