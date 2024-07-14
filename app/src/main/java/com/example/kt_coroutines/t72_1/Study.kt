package com.example.kt_coroutines.t72_1

import kotlinx.coroutines.Dispatchers
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
 * 协程selec 与onSend
 */


fun main() = runBlocking<Unit> {//单表达式函数
    val channels = arrayOf(Channel<Char>(), Channel<Char>())

    launch (Dispatchers.Default){
        select<Unit> {
            launch {
                delay(2000)
                channels[0].onSend('女'){
                    println("channels[0].send('女') $it")
                }
            }
            launch {
                delay(3000)
                channels[1].onSend('男'){
                    println("channels[1].send('南') $it")
                }

            }
        }
    }

    //下游接收
    launch {
        println("channel 1 下游接收${channels[0].receive()}")
    }
    launch {
        println("channel 2 下游接收${channels[1].receive()}")
    }
}

