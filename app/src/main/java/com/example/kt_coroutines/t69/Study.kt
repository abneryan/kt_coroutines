package com.example.kt_coroutines.t69

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/12
 *Description:
 */

/**
 * 协程Channel 的broadcast
 */



fun main() = runBlocking<Unit> {//单表达式函数
    val channel = Channel<Int>()
    val broadcast = channel.broadcast(Channel.BUFFERED)

    //生产者
    launch {
        repeat(4){
            delay(1000)
            broadcast.send(it + 100000)
        }
    }
    //消费者1
//    repeat(4){
//        launch {
//            //消费者订阅了4次
//            val openSubscription: ReceiveChannel<Int> = broadcast.openSubscription()
//            for (i in openSubscription)
//                println("协程$it----消费了 $i")
//        }
//    }
    //输出结果：
//    协程0----消费者 100000
//    协程1----消费者 100000
//    协程2----消费者 100000
//    协程3----消费者 100000
//    协程0----消费者 100001
//    协程1----消费者 100001
//    协程2----消费者 100001
//    协程3----消费者 100001
//    协程0----消费者 100002
//    协程1----消费者 100002
//    协程2----消费者 100002
//    协程3----消费者 100002
//    协程0----消费者 100003
//    协程1----消费者 100003
//    协程2----消费者 100003
//    协程3----消费者 10000

    //=======================================

    //消费者2
    launch {
        repeat(4){
            val openSubscription: ReceiveChannel<Int> = broadcast.openSubscription()
            for (i in openSubscription)
                println("协程$it----消费了 $i")
        }
    }
//输出结果：
//    协程0----消费了 100000
//    协程0----消费了 100001
//    协程0----消费了 100002
//    协程0----消费了 100003

}