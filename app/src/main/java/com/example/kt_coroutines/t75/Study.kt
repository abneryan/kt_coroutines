package com.example.kt_coroutines.t75

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import okhttp3.internal.wait
import java.util.concurrent.atomic.AtomicInteger

/**
 *@author yanguoqing
 *@data 2024/7/14
 *Description:
 */

/**
 * 协程安全并发安全解决方案
 */

fun main(): Unit = runBlocking<Unit> {//单表达式函数
    //方案1：java api 方式
//    var i = AtomicInteger(0)
//
//    List(10000){
//        GlobalScope.launch {
//            i.incrementAndGet()
//        }
//    }
//    println(i.get())

    //方案2：KT 的api来解决，Channel 并发安全队列

    //方案3：KT 的api来解决，C++的互斥锁

    /*val mutex = Mutex()
    var i = 0;
    List(10000) {
        GlobalScope.launch {
            mutex.toLock {
                i++
            }
        }
    }.joinAll()
    println(i)*/


    //方案4：KT 的api来解决，信号量控制
   /* val semaphore = Semaphore(1) //Semaphore(1) 等价与Mutex
    var i = 0;
    List(10000) {
        GlobalScope.launch {
            semaphore.toLock {
                i++
            }
        }
    }.joinAll()
    println(i)*/

    //方案5：直接在写代码的时候不出现并发安全问题，
    //不变性
    var i= 0
    var r = i+ List(10000){
        GlobalScope.async { 1 }
    }.map{
        it.await()
    }.sum()
    println(r)
}

private suspend inline fun <T> Mutex.toLock(action: () -> T) {
    this.lock()//上锁
    try {
        action()
    } finally {
        this.unlock()//解锁
    }
}

private suspend inline fun <T> Semaphore.toLock(action: () -> T) {
    this.acquire()
    try {
        action()
    } finally {
        release()
    }

}

