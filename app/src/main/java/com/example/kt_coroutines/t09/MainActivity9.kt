package com.example.kt_coroutines.t09

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

/*
    Dispatchers.Main
        Android 上的主线程：用来处理交互和一些轻量级任务 例如 更新控件，更新LiveData
    Dispatchers.IO
        非主线-异步：专门为磁盘和网络IO进行了优化，  例如：数据库耗时操作，文件读写操作，网络处理耗时操作
    Dispatchers.Default
        非主线程-异步：专门为CPU密集型任务进行了优化 例如：数组大量排序，Json 数据大量解析操作，计算大量密集型任务
 */

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
            GlobalScope.launch (Dispatchers.Main){
                println("coroutione start")
                println("coroutione end")
            }
            Thread.sleep(10000L)
            println("MainActivity9")
        }
    }
}