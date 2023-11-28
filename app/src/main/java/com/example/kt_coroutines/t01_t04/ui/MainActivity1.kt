package com.example.kt_coroutines.t01_t04.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain1Binding
import com.example.kt_coroutines.t01_t04.api.APIClient
import com.example.kt_coroutines.t01_t04.api.WanAndroidAPI
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponse
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponseWrapper

class MainActivity1 : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMain1Binding
    private var mHandler = Handler(Looper.getMainLooper()) {
        var loginRegisterResponseWrapper =
            it.obj as LoginRegisterResponseWrapper<LoginRegisterResponse>
        val data = loginRegisterResponseWrapper.data
        mainBinding.tvResult.text = data.toString()
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main1)
        mainBinding.btLogin.setOnClickListener {
            startRequest()
        }
    }
    private fun startRequest() {
        object : Thread(){
            override fun run() {
                val loginAction =  APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                    .loginAction("Derry-vip", "123456")
                val result = loginAction.execute().body()

                val obtainMessage = mHandler.obtainMessage()
                obtainMessage.obj = result
                mHandler.sendMessage(obtainMessage)
            }
        }.start()
    }
}