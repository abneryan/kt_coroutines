package com.example.kt_coroutines.t48

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/28 01:16
 * @Description:
 */

fun getFlow() = flow {
    println("getFlow 1")
    emit("YGQ")
    println("getFlow 2")
}
//协程Flow 是冷流概念
/*
      // Flow是冷流  == RxJava也是冷流

    // 冷流 生活中的例子：
    // 你去餐厅吃饭，坐到椅子上， 服务器 拿菜单，   服务器 拿筷子给我    服务器 端菜给我   ...  【服务极差】
    // 案例二： 挑水喝，你打开水龙头的那一刻 没有水， 叫一声，给我一点水，就真的给一点水

    // 热流 生活中的例子：
    // 你去餐厅吃饭，坐到椅子上 什么话都不说，人家服务器 各种 给你考虑到 热情的  【服务很好 很热情】
    // 案例二：你打开水龙头的那一刻，就马上立刻有水（因为 早就把水准备好了，就等你用了）
 */
fun main() = runBlocking<Unit >() {

    launch {
        println("launch start")
        delay(6000)
        println("launch end")
        getFlow()
        println("getFlow end")
        println("开始收集上游 ")

        getFlow().collect{
            println("it:$it")
        }
    }

    /*
        launch start
        launch end
        getFlow end
        开始收集上游
        getFlow 1
        it:YGQ
        getFlow 2
     */
}