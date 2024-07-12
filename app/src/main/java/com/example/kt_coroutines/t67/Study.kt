package com.example.kt_coroutines.t67

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/12
 *Description:
 */

/**
 * 协程的快捷方式
 */



fun main() = runBlocking<Unit> {//单表达式函数

//    var produce = produce {
//        (1..20).forEach {
//            delay(2000)
//            send(it)
//        }
//    }
//    //普通消费
//    launch {
//        for (item in produce){
//            println("消费了： $item")
//        }
//    }

    //==========================

    //协程Channel 消费的快捷方式

    //消费者
    val producer = actor<Int>{
        (1..200).forEach{
            println("消费了： $it")
        }
    }
    //普通的生成
    launch {
        (1..2).forEach {
            producer.send(it)
        }
    }
}

