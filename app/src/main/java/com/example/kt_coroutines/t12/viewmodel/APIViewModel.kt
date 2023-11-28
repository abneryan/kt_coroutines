package com.example.kt_coroutines.t12.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponse
import com.example.kt_coroutines.t01_t04.entity.LoginRegisterResponseWrapper
import com.example.kt_coroutines.t12.repository.APIRepository
import kotlinx.coroutines.launch

/**
 * @Auther: yanguoqing
 * @Date: 2023/11/23 22:25
 * @Description:
 */
class APIViewModel :ViewModel() {
    var userLiveData = MutableLiveData<LoginRegisterResponseWrapper<LoginRegisterResponse>>()
    fun requestLogin(userName: String,usrPwd:String){
        viewModelScope.launch {//默认是main线程 ，其他默认是Default
            userLiveData.value = APIRepository().requestLogin(userName,usrPwd )
        }
    }
}