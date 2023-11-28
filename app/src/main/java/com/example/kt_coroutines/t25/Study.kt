package com.example.kt_coroutines.t25

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.FileReader

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/24 20:23
 * @Description:
 */
//协程取消的影响
fun main() = runBlocking<Unit> {
    val fileReader =
        BufferedReader(FileReader("/Users/yanguoqing/Develop/projects/XX/kt_coroutines/app/src/main/res/data.txt"))
    val job = launch {
        fileReader.also {
            var lineContent: String?
            try {
                while (true) {
                    delay(1000)
                    lineContent = it.readLine()
                    lineContent ?: break //如果读取的内容是空，就跳出循环
                    println("$lineContent")
                }
            } catch (e: Exception) {
                println("协程被取消了 异常是：$e")
            } finally {
                println("BufferedReader被释放了")
                it.close()
            }
        }
    }
    delay(2000)
    job.cancel()
}