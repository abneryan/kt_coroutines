package com.example.kt_coroutines.t05_06

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/21 10:53
 * @Description:
  */

fun fun01(){
    println("模拟服务器请求中。。。")
    Thread.sleep(3000L)
    println("模拟服务器请求完成，数据data{}。。。")
}

fun fun02(action: (String) ->Unit){
    println("模拟服务器请求中。。。")
    Thread.sleep(3000L)
    action("模拟服务器请求完成，数据data{}。。。")
}


fun fun03() :String{
    println("模拟服务器请求中。。。")
    Thread.sleep(3000L)
    return "模拟服务器请求完成，数据data{}。。。"
}

fun main() {
    fun01()
    println()

    fun02(){
        println(it)
    }
    println()

    val fun03 = fun03()
    println(fun03)
}