package com.example.kt_coroutines.t11

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain2Binding
import com.example.kt_coroutines.t01_t04.api.APIClient
import com.example.kt_coroutines.t01_t04.api.WanAndroidAPI
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponse
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity11 : AppCompatActivity(), CoroutineScope by MainScope() { //委托
    private lateinit var mainBinding: ActivityMain2Binding
    var progressDialog: ProgressDialog? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        mainBinding.btLogin.setOnClickListener {
            launch(Dispatchers.Main) {
                val requestResult = requestLogin("Derry-vip", "123456")
                mainBinding.tvResult.text = requestResult.data.toString()
                delay(6000L)
                progressDialog?.dismiss()
                mainBinding.tvResult.text = "恢复全部完成！"
            }
            progressDialog = ProgressDialog(this@MainActivity11)
            progressDialog?.setTitle("服务器请求中。。。")
            mainBinding.tvResult.text = "服务器请求中。。。"
            progressDialog?.show()
        }
    }

    private suspend fun requestLogin(userName: String, userPwd: String)
            : LoginRegisterResponseWrapper<LoginRegisterResponse> {

        if (userName.isEmpty() || userPwd.isEmpty()) {
            throw KotlinNullPointerException("userName or userPwd is null")
        }
        val responseResult: LoginRegisterResponseWrapper<LoginRegisterResponse> =
            withContext(Dispatchers.IO) {
                delay(6000L)
                val responseResult = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                    .loginActionCoroutine(userName, userPwd)
                responseResult
            }
        return responseResult
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}