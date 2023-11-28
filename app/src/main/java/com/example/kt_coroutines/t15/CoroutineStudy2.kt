package com.example.kt_coroutines.t15

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 23:52
 * @Description:
 */

fun main() = runBlocking<Unit> {
    val measureTimeMillis = measureTimeMillis {

      /*
      情况2：measureTimeMillis:19082

      val d1 =async {
            requestLoadUser()
            println("请求用户数据 1---successful")
        }
        d1.await()

        val d2 = async {
            requestLoadUserAssets()
            println("请求用户资产数据 2---successful")
        }
        d2.await()

        val d3 = async {
            requestLoaderUserAssetsDetail()
            println("请求用户资产详情数据 3---successful")
        }
        d3.await()
         */

        //======================================

        /*
        情况1：measureTimeMillis:10121

        d1.await()
        d2.await()
        d3.await()
        */

        var d1= async { delay(1000) }
        var d2= async { delay(1000) }
        var d3= async { delay(1000) }

        d1.await()
        d2.await()
        d3.await()
    }
    println("measureTimeMillis:$measureTimeMillis")
}