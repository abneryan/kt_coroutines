package com.example.kt_coroutines.t37

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 00:58
 * @Description:
 */
/*
    Android supervisorScope{}
    //异常时候不会影响兄弟协程
 */
fun main() = runBlocking<Unit> {
    supervisorScope {
        val job1 = launch {
            println("lauch1 start")
            delay(1000)
            throw  KotlinNullPointerException("is null")
            println("lauch2 end")
        }
        val  job2 =launch {
            println("lauch2 start")
            delay(2000)
            println("lauch2 end")
        }
        println("supervisorScope run")
        yield()//yield 让出执行权，先让协程job1执行，1s后抛出异常，然后再在36行抛出异常（  throw KotlinNullPointerException("supervisorScope{} 里面抛出异常")）
        delay(1000)
        println("supervisorScope run end")

        throw KotlinNullPointerException("supervisorScope{} 里面抛出异常")
    }
}