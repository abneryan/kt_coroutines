package com.example.kt_coroutines.t47

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain47Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

// 47-Android企业级Flow项目的开发案例编写
class MainActivity47 : AppCompatActivity(),CoroutineScope by MainScope() {
    private lateinit var mainBinding: ActivityMain47Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main47)

        mainBinding.bt.setOnClickListener{
            launch(Dispatchers.Main) {
                println("launch thread：${Thread.currentThread().name}")
                NetworkRequest.uploadRequestAction().flowOn(Dispatchers.IO).collect{
                    mainBinding.tv.text = "正在上传服务器。。。进度是：$it"
                    mainBinding.pb.progress = it
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}