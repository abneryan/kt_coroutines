package com.example.kt_coroutines.t08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

class MainActivity9 : AppCompatActivity() {
    private suspend fun fun01(): Float {
        delay(10000L)
        return 3.14f
    }

    private lateinit var mainBinding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        mainBinding.btLogin.setOnClickListener {
           /*
                //官方框架---等同于Retrofit在写需求
                GlobalScope.launch(Dispatchers.Main) {
                    val fun01 = fun01()
                    println(fun01)
                }
            */

            //语言基础层，动态代理，反射，注解 最基本的API 写需求
            val suspendFun: suspend () -> Float = suspend {
                delay(10000L)
                3.14f
            }

            /*
                参数里面的Continuation<Float> : suspend协程体完成时 成果3.14 的反馈或者异常反馈
                外面接收的Continuation<Unit>  :suspend协程体的管理者，协程的本地是不会激活的（suspend 携程体）
             */
            val continuation:Continuation<Unit> = suspendFun.createCoroutine(object : Continuation<Float> {
                override val context: CoroutineContext
                    get() = Dispatchers.Main //设置协程运行的线程类型

                override fun resumeWith(result: Result<Float>) {
                    println("成果的打印 resumeWith: result:${result.getOrNull()}")
                }
            })
            //continuation 负责人 管理者
            continuation.resume(Unit)

            /*
                上面的基础代码到底会有几次调用 resume?  2次
                    第一步：continuation 负责管理者去激活，（suspend携程体）
                    第二步：suspend协程体开始执行，耗时delay 10s 后，把成果给参数里面的Continuation<Float> 一次resume
             */
        }
    }
}