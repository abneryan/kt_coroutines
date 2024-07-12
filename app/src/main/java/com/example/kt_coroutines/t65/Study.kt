package com.example.kt_coroutines.t65

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
 * 协程Flow的channel 的capacity
 */



fun main() = runBlocking<Unit> {//单表达式函数
    val channel:Channel<Int> = Channel<Int>()
    launch {
        (1..100).forEach {
            delay(1000)
            channel.send(it) //容量是0，会挂起2s,知道消费完成后，才恢复
            println("我生产了一个： $it")
        }
    }

    launch {
        (1..1000).forEach {
            delay(2000)
            val receive = channel.receive()
            println("我消费了一个： $receive")
        }
      }
    //总结：
    //Channel 是一个队列，队列中是有缓冲区的，默认是0
    //如果缓冲区满了，receive 并没有消费掉，此时send 会被挂起，直到reveive消费完成后，send再次恢复，再生产一个，以此类推
}
