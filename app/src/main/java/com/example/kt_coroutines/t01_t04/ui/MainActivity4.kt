package com.example.kt_coroutines.t01_t04.ui

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
  请求加载[用户数据]
 */
private suspend fun requestLoadUser(): String {
    // 加载成功，和，加载失败，的标记
    val isLoadSuccess = true

    // 开启异步线程，去请求加载服务器的数据集
    withContext(Dispatchers.IO) {
        Log.w("YGQ",Thread.currentThread().name + "====4")
        // 模拟请求服务器 所造成的耗时
        delay(3000L)
    }
    Log.w("YGQ",Thread.currentThread().name + "====5")
    if (isLoadSuccess) {
        return "加载到用户数据信息集"
    } else
        return "加载用户数据信息集失败！！！"
}

private suspend fun requestLoadUserAssets(): String {
    // 加载成功，和，加载失败，的标记
    val isLoadSuccess = true
    // 开启异步线程，去请求加载服务器的数据集
    withContext(Dispatchers.IO) {
        // 模拟请求服务器 所造成的耗时
        delay(3000L)
    }
    if (isLoadSuccess) {
        return "加载到用户资产数据信息集"
    } else
        return "加载用户数据资产信息集失败！！！"
}

private suspend fun requestLoadUserAssetsDetail(): String {
    // 加载成功，和，加载失败，的标记
    val isLoadSuccess = true
    // 开启异步线程，去请求加载服务器的数据集
    withContext(Dispatchers.IO) {
        // 模拟请求服务器 所造成的耗时
        delay(3000L)
    }
    if (isLoadSuccess) {
        return "加载到用户资产详情数据信息集"
    } else
        return "加载用户数据资产详情信息集失败！！！"
}

class MainActivity4 : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMain2Binding
    var mProgressDialog: ProgressDialog? = null
    private val TAG = "YGQ"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        mainBinding.btLogin.setOnClickListener {
            startRequest()
        }
    }

    private fun startRequest() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setTitle("请求服务器中...")
        mProgressDialog?.show()
        //协程块
        GlobalScope.launch(Dispatchers.Main) {
            Log.w("YGQ",Thread.currentThread().name + "====1")
            //异步请求1
            val requestLoadUser = requestLoadUser()
            Log.w("YGQ",Thread.currentThread().name + "====2")
            mainBinding.tvResult.text = requestLoadUser;
            mainBinding.tvResult.setTextColor(Color.GREEN)

            //异步请求2
            val requestLoadUserAssets = requestLoadUserAssets()
            mainBinding.tvResult.text = requestLoadUserAssets;
            mainBinding.tvResult.setTextColor(Color.BLUE)

            //异步请求3
            val requestLoadUserAssetsDetail = requestLoadUserAssetsDetail()
            mainBinding.tvResult.text = requestLoadUserAssetsDetail;
            mainBinding.tvResult.setTextColor(Color.RED)
            Log.w("YGQ",Thread.currentThread().name + "====3")


            mProgressDialog?.dismiss()
        }
    }


    /*
        2023-11-21 10:46:24.324 12503-12503 YGQ                     com.example.kt_coroutines            W  main====1
        2023-11-21 10:46:24.344 12503-12542 YGQ                     com.example.kt_coroutines            W  DefaultDispatcher-worker-1====4
        2023-11-21 10:46:27.351 12503-12503 YGQ                     com.example.kt_coroutines            W  main====5
        2023-11-21 10:46:27.352 12503-12503 YGQ                     com.example.kt_coroutines            W  main====2
        2023-11-21 10:46:33.364 12503-12503 YGQ                     com.example.kt_coroutines            W  main====3

     */
}