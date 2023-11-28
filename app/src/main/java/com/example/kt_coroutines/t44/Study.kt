package com.example.kt_coroutines.t44

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 12:19
 * @Description:
 */
//协程为了引入Flow登场
fun main() = runBlocking<Unit> {//顶级父协程
    //目标：异步返回多个值，协作的一个一个的得到
    fun getlist() = listOf (100,200,300)

    //同步返回多个值，协作的一个一个得到
    //sequence方法构建一个序列发生器
    fun getSequence() = sequence{
        for (item in 100 ..300 step 100){
            yield(item)
        }
    }
    val job = GlobalScope.launch{
       //getlist().forEach{println(it)}
        getSequence().forEach { println(it) }
    }
    job.join()

}