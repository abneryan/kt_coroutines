package com.example.kt_coroutines.t61

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 *@author yanguoqing
 *@data 2024/7/10
 *Description:
 */

/**
 * 协程Flow的catch
 *
 * FLow 是异步流
 */

fun getNubmers() = (1..6).asFlow()


fun main() = runBlocking {//单表达式函数
    //对下游的异常处理： 我们人为主动try 属于命令式 传统方式
   /* try {
        getNubmers()
            .onEach { delay(1000) }
            .collect {
                println(it)
                if (it == 4) {
                    throw KotlinNullPointerException("下游计算过程中，抛出异常")
                }
            }
    } catch (e: Exception) {
        println("e:$e")
    }*/
//    不加try catch输出结果：
//    1
//    2
//    3
//    4
//    Exception in thread "main" kotlin.KotlinNullPointerException: 下游计算过程中，抛出异常
//    at com.example.kt_coroutines.t61.StudyKt$main$1$2.emit(Study.kt:35)
//    at com.example.kt_coroutines.t61.StudyKt$main$1$2.emit(Study.kt:32)
//    at kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2.emit(Emitters.kt:224)
//    at kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1.invokeSuspend(Emitters.kt)
//    at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//    at kotlinx.coroutines.DispatchedTaskKt.resume(DispatchedTask.kt:234)
//    at kotlinx.coroutines.DispatchedTaskKt.dispatch(DispatchedTask.kt:166)
//    at kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(CancellableContinuationImpl.kt:397)
//    at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl(CancellableContinuationImpl.kt:431)
//    at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl$default(CancellableContinuationImpl.kt:420)
//    at kotlinx.coroutines.CancellableContinuationImpl.resumeUndispatched(CancellableContinuationImpl.kt:518)
//    at kotlinx.coroutines.EventLoopImplBase$DelayedResumeTask.run(EventLoop.common.kt:500)
//    at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:284)
//    at kotlinx.coroutines.BlockingCoroutine.joinBlocking(Builders.kt:85)
//    at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking(Builders.kt:59)
//    at kotlinx.coroutines.BuildersKt.runBlocking(Unknown Source)
//    at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(Builders.kt:38)
//    at kotlinx.coroutines.BuildersKt.runBlocking$default(Unknown Source)
//    at com.example.kt_coroutines.t61.StudyKt.main(Study.kt:29)
//    at com.example.kt_coroutines.t61.StudyKt.main(Study.kt)

//==============================
//    加try catch输出结果：
//    1
//    2
//    3
//    4
//    e:kotlin.KotlinNullPointerException: 下游计算过程中，抛出异常

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //对上游异常的处理 采用声明式 Compose
    flow {
        listOf(100, 200).forEach {
            emit(it)
            throw KotlinNullPointerException("上游抛出了异常")
        }
    }
        .catch {
            println("e:$it")
            emit(-1)
        }
        .onEach { delay(1000) }
        .collect {
            println(it)
        }
//输出结果：
//    100
//    e:kotlin.KotlinNullPointerException: 上游抛出了异常
//    -1
}
