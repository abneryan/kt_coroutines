package com.example.kt_coroutines.t26

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
// Android企业中use函数释放
fun main() = runBlocking<Unit> {
    val job = launch {
        val fileReader =
            BufferedReader(FileReader("/Users/yanguoqing/Develop/projects/XX/kt_coroutines/app/src/main/res/data.txt"))
        fileReader.use {
            var lineContent: String?
            while (true) {
                delay(1000)
                lineContent = it.readLine()
                lineContent ?: break
                println(lineContent)
            }
        }
    }
    delay(2000)
    job.cancel()
}