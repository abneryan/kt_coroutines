package com.example.kt_coroutines.t45

 import kotlinx.coroutines.GlobalScope
 import kotlinx.coroutines.delay
 import kotlinx.coroutines.flow.flow
 import kotlinx.coroutines.launch
 import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 12:19
 * @Description:
 */
//协程Flow异步返回多个值
fun main() = runBlocking<Unit> {//顶级父协程
    //目标：异步返回多个值，协作的一个一个的得到
    suspend fun getFlow() = flow {
        for (item in 1..8){
            delay(1000)
            //flow 的发射端（起点Observable） 发射元素
            emit(item)
        }
    }
    //顶级协程
    val job = GlobalScope.launch{
        //flow的末尾端，（终点Obsever）
        getFlow().collect{
            println(it)
        }
    }
    job.join()

}