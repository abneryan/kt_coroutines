package com.example.kt_coroutines.t68

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/12
 *Description:
 */

/**
 * 协程Channel的结束
 */



fun main() = runBlocking<Unit> {//单表达式函数
    val channel = Channel<Int>(0)
    //生产者
    launch {
        (1..6).forEach {
            if(!channel.isClosedForSend){
                channel.send(it)
                println("我生产了$it")
                if (it ==3) channel.close()
            }
        }
    }
    //消费者
    launch {
        for (i in channel)
            println("我消费了$i")
    }

//    输出结果：
//    我消费了1
//    我生产了1
//    我生产了2
//    我消费了2
//    我消费了3
//    我生产了3

    //总结
    //channel.close() 之前 channel.isClosedForSend == false
    //channel.close() 之后 channel.isClosedForSend == true

    //如果消费完了channel.isClosedForReceive ==true 否则就是false
    //如果缓冲区里面有还有内容，没有消费完也是false

}

