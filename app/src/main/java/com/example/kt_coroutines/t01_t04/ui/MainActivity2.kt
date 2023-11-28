package com.example.kt_coroutines.t01_t04.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain2Binding
import com.example.kt_coroutines.t01_t04.api.APIClient
import com.example.kt_coroutines.t01_t04.api.WanAndroidAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
 *  协程：
 *     线程封装框架
 *     用同步代码，实现异步效果
 *  Retrofit 2.6 以上 一旦Retrofit 遇到suspend关键字，内部会自动做：
 *      1，自动切换到IO异步线程执行耗时操作，20秒耗时
 *      2，20s 耗时操作完成后，恢复到当前协程所在的线程==Main 安卓主线程
 */

class MainActivity2 : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        mainBinding.btLogin.setOnClickListener {
            startRequest()
        }
    }

    private fun startRequest() {
        //协程块
        //如果不设置Dispatchers.Main   在Android上，他默认是异步线程 上的 协程块
        GlobalScope.launch(Dispatchers.Main) {
            Log.w("YGQ",Thread.currentThread().name)      // YGQ   com.example.kt_coroutines            W  DefaultDispatcher-worker-1
            //我们在Android主线程上运行耗时操作，居然没有任何问题？ 答：suspend 的两件事（见底部注释）
            val loginAction = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                .loginActionCoroutine  ("Derry-vip", "123456")
            mainBinding.tvResult.text = loginAction?.data.toString()
        }
    }
}