package com.example.kt_coroutines.t05_06

import android.app.ProgressDialog
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
    在协程中最重要的概念，先【suspend挂起】和再【resume 恢复】、
    suspend挂起：有人称为 暂停当前协程/挂起当前协程，然后再保存当前协程中所的局部信息（为了后面的恢复做支持）
    resume恢复：上面的挂起完成后，从当前协程挂起点，开始恢复，然后继续执行
 */

class MainActivity5 : AppCompatActivity() {
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
            Log.d(TAG, "requestLogin--start")
            requestLogin()
            Log.d(TAG, "requestLogin--end")
            mProgressDialog?.dismiss()
        }
        Log.d(TAG, "requestLogin--main")

    }

    private suspend fun requestLogin() {
        Log.d(TAG, "loginAction--start")
        val loginAction = loginAction()
        Log.d(TAG, "loginAction--end")
        Log.d(TAG, "requestLogin--loginAction:$loginAction");
    }

    private suspend fun loginAction(): String = withContext(Dispatchers.IO) {
        delay(3000L)
        "模拟服务器请求完成 数据data{}"
    }
}