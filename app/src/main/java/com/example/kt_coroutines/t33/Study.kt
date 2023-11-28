package com.example.kt_coroutines.t33

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
    协程自动传播异常与向用户暴露异常
 */
fun main() = runBlocking<Unit> {
    //协程用户暴露异常 ==========================
    launch {
        try {
            throw KotlinNullPointerException("异常了")
        } catch (e: Exception) {
            println("launch cathc exception:$e")
        }
    }

    //子协程
    async {
        try {
            throw KotlinNullPointerException("异常了")
        } catch (e: Exception) {

        }
    }
    //全局作用域就可以了，不是子协程
    val d = GlobalScope.async {
        throw KotlinNullPointerException("异常了")
    }

    // 协程自动传播异常 ========================================

    try {
        d.await()
    } catch (e:Exception){
        println("async 返回值 catch exception:$e")
    }
}