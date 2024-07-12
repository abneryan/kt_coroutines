package com.example.kt_coroutines.t70

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 *@author yanguoqing
 *@data 2024/7/12
 *Description:
 */

/**
 * 协程Channel 的select 择优选择数据
 */
data class HomeRequestResponseResultData(val code:Int,var msg:String, val data: Home)

data class Home(val info1:String, val info2: String)

fun CoroutineScope.getHomeLocalData() = async (Dispatchers.IO){
    delay(3000)
    Home("数据1", "数据2")
}

fun CoroutineScope.getHomeRemoteData() = async (Dispatchers.IO){
    delay(6000)
    Home("数据3", "数据4")
}


fun main() = runBlocking<Unit> {//单表达式函数

    //select操作符： getHomeLocalData getHomeRemoteData 谁先返回就取谁的值
    val homeLocalData = getHomeLocalData()
    val homeRemoteData = getHomeRemoteData()
    val result = select {
        homeLocalData.onAwait
        homeRemoteData.onAwait{
            HomeRequestResponseResultData(200,"💐恭喜你",it)
        }
    }
    println("response: $result")

//    输出结果：
//    respose: HomeRequestResponseResultData(code=200, msg=💐恭喜你, data=Home(info1=数据3, info2=数据4))

}

