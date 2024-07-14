package com.example.kt_coroutines.t73

import com.example.kt_coroutines.t70.Home
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/14
 *Description:
 */

/**
 * 协程手写Flow合并
 */

fun CoroutineScope.getHomeLocalData(userName: String) = async (Dispatchers.IO){
    delay(3000)
    Home("数据1", "数据2")
}

fun CoroutineScope.getHomeRemoteData(userName: String) = async (Dispatchers.IO){
    delay(6000)
    Home("数据3", "数据4")
}


fun main() : Unit= runBlocking<Unit> {//单表达式函数
    //1.把多个函数拿过来
    //2,组装成协程
    //包装成Flow
    //Flow合并，得到结果

    coroutineScope {
        var r = listOf(::getHomeLocalData, ::getHomeRemoteData)
            .map {
                it.call("Abner 用户")
            }.map {
                flow { emit(it.await()) }
            }

        var r2= r.merge()
        r2.collect{
            println(it)
        }
    }

    //输出结果：
//    Home(info1=数据1, info2=数据2)
//    Home(info1=数据3, info2=数据4)

}

