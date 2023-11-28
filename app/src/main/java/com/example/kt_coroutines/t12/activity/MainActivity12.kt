package com.example.kt_coroutines.t12.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain12Binding
import com.example.kt_coroutines.t12.viewmodel.APIViewModel

/*
    Android 协程+Jetpack全家桶+MVVM 设计架构
 */

class MainActivity12 : AppCompatActivity(){ //委托
    private lateinit var mainBinding: ActivityMain12Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main12)
        mainBinding.lifecycleOwner = this

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(APIViewModel::class.java)
        mainBinding.vm = viewModel


        mainBinding.btLogin.setOnClickListener {
            viewModel.requestLogin("Derry-vip", "123456")
        }
    }
}