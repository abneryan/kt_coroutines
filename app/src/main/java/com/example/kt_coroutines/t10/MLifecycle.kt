package com.example.kt_coroutines.t10

import androidx.lifecycle.*
import kotlinx.coroutines.launch

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 19:00
 * @Description:
 */
class MLifecycle : LifecycleOwner {
    override val lifecycle: Lifecycle
        get() = TODO("Not yet implemented")

    fun fun01() {
        lifecycleScope.launch {

        }
    }
}