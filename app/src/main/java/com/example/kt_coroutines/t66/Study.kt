package com.example.kt_coroutines.t66

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/12
 *Description:
 */

/**
 * 协程Flow的channel让send 不挂起
 */



fun main() = runBlocking<Unit> {//单表达式函数
    //通道Channel
    val channel:Channel<Int> = Channel<Int>(Channel.UNLIMITED)

    launch {
        (1..5).forEach {
            delay(1000)
            channel.send(it) //容量为2147483647  ，不会让send挂起，因为直接一次性send到了缓冲区
            println("我生产了一个： $it")
        }
    }

    //消费者1
    /*launch {
        (1..5).forEach {
            delay(2000)
            val receive = channel.receive()
            println("我消费了一个： $receive")
        }
      }*/

//输出结果：
//    我生产了一个： 1
//    我生产了一个： 2
//    我消费了一个： 1
//    我生产了一个： 3
//    我生产了一个： 4
//    我消费了一个： 2
//    我生产了一个： 5
//    我消费了一个： 3
//    我消费了一个： 4
//    我消费了一个： 5

    //消费者2
//    val iterator = channel.iterator()
//    while (iterator.hasNext()){
//        val next = iterator.next()
//        delay(2000)
//        println("我消费了一个： $next")
//    }

    //消费者3
    for (item in channel){
        delay(2000)
        println("我消费了一个： $item")
    }


    //总结：
    //Channel 是一个队列，队列中是有缓冲区的，修改缓冲区为2147483647
    //如果缓冲区不可能满了，receive 并没有消费掉，此时send 不会被挂起，因为会写入缓存区
}

