package com.example.kt_coroutines.t46

 import kotlinx.coroutines.GlobalScope
 import kotlinx.coroutines.delay
 import kotlinx.coroutines.flow.flow
 import kotlinx.coroutines.launch
 import kotlinx.coroutines.runBlocking

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/27 12:19
 * @Description:
 */
/*
    46-Android协程Flow的特点
     1.发起区域 有 Flow类型构建器的函数 flow()
     2.flow{里面可以挂起与恢复}
     3.getFlow不需要是 suspend 挂起函数，意味着 没有任何限制，更自由
 */
fun main() = runBlocking<Unit> {//顶级父协程
    //目标：异步返回多个值，协作的一个一个的得到
     fun getFlow() = flow {
        for (item in 1..8){
            delay(1000)
            //flow 的发射端（起点Observable） 发射元素
            emit(item)
        }
    }
    //顶级协程
    val job = GlobalScope.launch{
        //flow的末尾端，（终点Obsever）
        getFlow().collect{
            println(it)
        }
    }
    job.join()

}