package com.example.kt_coroutines.t52

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 *@author yanguoqing
 *@data 2024/7/7
 *Description:
 */

/**
 * 协程Flow的lauchIn收集流
 */

fun getFlowValue() = listOf(1, 2, 3, 4, 5)
    .asFlow()
    .onEach { delay(2000) }
    .flowOn(Dispatchers.IO)

fun main() = runBlocking {//单表达式函数
  /*  getFlowValue()
        .collect {
            println("thread :${Thread.currentThread().name} $it")
        }*/
  /*
    thread :main @coroutine#1 1
    thread :main @coroutine#1 2
    thread :main @coroutine#1 3
    thread :main @coroutine#1 4
    thread :main @coroutine#1 5
  */

    getFlowValue()
        .onEach { println("thread :${Thread.currentThread().name} $it") }
        .launchIn(CoroutineScope(Dispatchers.IO)).join()

   /*
    thread :DefaultDispatcher-worker-1 1
    thread :DefaultDispatcher-worker-1 2
    thread :DefaultDispatcher-worker-3 3
    thread :DefaultDispatcher-worker-3 4
    thread :DefaultDispatcher-worker-3 5
*/
}
