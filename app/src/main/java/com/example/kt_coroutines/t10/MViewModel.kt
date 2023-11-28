package com.example.kt_coroutines.t10

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 18:59
 * @Description:
 */
class MViewModel : ViewModel() {

    fun method(){
        viewModelScope.launch{

        }
    }
}