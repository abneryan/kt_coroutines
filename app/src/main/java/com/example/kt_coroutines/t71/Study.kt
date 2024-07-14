package com.example.kt_coroutines.t71

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 *@author yanguoqing
 *@data 2024/7/13
 *Description:
 */

/**
 * 协程selec 与channel
 */


fun main() = runBlocking<Unit> {//单表达式函数
    val channels = arrayOf(Channel<String>(), Channel<String>())

    launch {
        delay(5000)
        channels[0].send("login successful")
    }
    launch {
        delay(7000)
        channels[1].send("register successful")
    }

    val selectResult = select<String> {
        for (channel in channels) {
            channel.onReceive {
                it
            }
        }
    }
    println(selectResult)
}

