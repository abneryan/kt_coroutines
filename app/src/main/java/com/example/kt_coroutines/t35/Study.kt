package com.example.kt_coroutines.t35

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
    Android 协程传播的补充
 */
fun main() = runBlocking<Unit> {
    //非根协程
    val d = GlobalScope.async {
        launch {
            launch {
                async {//async 会立即开始调度，所有会往父协程抛异常
                    //await() 拿返回值或者异常
                    throw KotlinNullPointerException("我异常了")
                }
            }
        }
    }
    d.await()
}