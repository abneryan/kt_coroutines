package com.example.kt_coroutines.t09

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain2Binding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
    Android协程CoroutineScope 魅力
    当某个协程出现了丢失，无法跟踪，会导致内存，CPU ,磁盘资源的浪费，或者发送一个无用的网络请求，这种情况属于协程任务的不可控
    在Kotlin协程中为了解决，协程任务的不可控，出现了一种机制，就是【结构化并发】

    Thread.start  stop 弃用

    结构化并发的职责是：
        1，可以取消协程任务
        2，协程任务正在执行中，任务的情况，任务的状态，我们是可以追踪的
        3，协程任务正在执行中，如果出现了什么意外，出现了什么异常情况，可以给我们发送消息

   所有的协程体里面都是CoroutineScope
   定义协程的时候，必须指定是CoroutineScope ,他会追踪所的协程，他可以取消由CoroutineScope管理的协程

   CoroutineScope 常用的子类
        GlobalScope :生命周期是进程级别的，绑定APP进程的，哪怕你的Activity ，fragment都被销毁了，这个协程作用域还在
                       【开发者不常用】
        MainScope  :在Activity 中使用，我们在onDestroy中取消协程
        viewModelScope: 绑定ViewModel的生命周期，ViewModel 被销毁，他会自动销毁
        lifecycleScope：因为Lifecycle 是绑定Activity/Frament的生命周期的,会跟着销毁。

 */

class MainActivity10 : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        mainBinding.btLogin.setOnClickListener {
            GlobalScope.launch{

            }
        }
     }
}