package com.example.kt_coroutines.t09

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 16:03
 * @Description:
 */
//JVM 程序而已，是没有 Android平台的，所以 Android的main（Dispatchers.Main） 我们拿不到，会保存
fun main() {
    GlobalScope.launch(Dispatchers.Main) {
        println("coroutione start")
        println("coroutione end")
    }
}