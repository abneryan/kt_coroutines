package com.example.kt_coroutines.t34

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 00:58
 * @Description:
 */
/*
    Android 非根协程的异常
 */
fun main() = runBlocking<Unit> {
    //非根协程
    val d = GlobalScope.async {
        launch {
            launch {
                launch {
                    throw KotlinNullPointerException("我异常了")
                }
            }
        }
    }
    try {
        d.await()
    } catch (e: Exception) {
        println(e) // kotlin.KotlinNullPointerException: 我异常了

    }

}