package com.example.kt_coroutines.t40_t41

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain40Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    Android 协程异常捕获解决App崩溃
 */
class MainActivity40 : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var mainBinding: ActivityMain40Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mProgressDialog = ProgressDialog(this@MainActivity40)
        mProgressDialog.setTitle("计算中...")
        /*val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Toast.makeText(
                this,
                "您输入的内容为空，或，您输入第二项是0 0不能除以谁",
                Toast.LENGTH_SHORT
            ).show()
            if (mProgressDialog.isShowing)
                mProgressDialog.dismiss()
        }
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main40)
        mainBinding.bt.setOnClickListener {
            launch(Dispatchers.Main + exceptionHandler) {
                val num1 = mainBinding.etNumber1.text.toString().toInt()
                val num2 = mainBinding.etNumber2.text.toString().toInt()

                mProgressDialog.show()
                var d = async(Dispatchers.IO) {
                    println("async 开始计算")
                    delay(3000)
                    val result = num1 / num2
                    result
                }
                println("async 计算前")
                Toast.makeText(this@MainActivity40, "计算的结果是:${d.await()}", Toast.LENGTH_SHORT)
                    .show()
                mainBinding.tvResult.text = d.await().toString()
                println("async 计算后")
                mProgressDialog.dismiss()
            }
        }*/

        //==========================================================================================
        //使用kotlin 全局异常捕获的方式，捕获异常（AllCoroutineExceptionHandler）
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main40)
        mainBinding.bt.setOnClickListener {
            launch(Dispatchers.Main) {
                val num1 = mainBinding.etNumber1.text.toString().toInt()
                val num2 = mainBinding.etNumber2.text.toString().toInt()

                mProgressDialog.show()
                var d = async(Dispatchers.IO) {
                    println("async 开始计算")
                    delay(3000)
                    val result = num1 / num2
                    result
                }
                println("async 计算前")
                Toast.makeText(this@MainActivity40, "计算的结果是:${d.await()}", Toast.LENGTH_SHORT)
                    .show()
                mainBinding.tvResult.text = d.await().toString()
                println("async 计算后")
                mProgressDialog.dismiss()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}