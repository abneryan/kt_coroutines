package com.example.kt_coroutines.t39

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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
// 39-Android异常捕获常见错误分析
suspend fun main() {
    //CoroutineExceptionHandler 处理的是顶级launch协程，才能完成异常的捕获
    val exceptionHandler = CoroutineExceptionHandler { _, e ->
        println("你的协程发生了异常e:S$e")
    }
    GlobalScope.launch {
        launch {
            launch(exceptionHandler) {//exceptionHandler 设置给非顶级launch协程
                launch {
                    throw KotlinNullPointerException("launch 我错了")
                }
            }
        }
    }

    val deferred = GlobalScope.async(exceptionHandler) {
        async {
            async {
                async {
                    throw KotlinNullPointerException("async 我错了")
                }
            }
        }
    }
    try {
        deferred.await()
    } catch (e: Exception) {
        println("deferred 异常 e:$e")
    }

    Thread.sleep(2000)

    /*
    deferred 异常 e:kotlin.KotlinNullPointerException: async 我错了
    Exception in thread "DefaultDispatcher-worker-7" kotlin.KotlinNullPointerException: launch 我错了
	at com.example.kt_coroutines.t39.StudyKt$main$2$1$1$1.invokeSuspend(Study.kt:27)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:106)
	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:570)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:750)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:677)
	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:664)
	Suppressed: kotlinx.coroutines.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@5352c80d, Dispatchers.Defau
     */
}