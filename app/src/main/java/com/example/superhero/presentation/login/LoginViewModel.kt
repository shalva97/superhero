package com.example.superhero.presentation.login

import android.util.Log
import com.example.superhero.SuperApp
import com.example.superhero.presentation.SuperViewModel

class LoginViewModel(val application: SuperApp) : SuperViewModel() {
    init {
        Log.d("LoginViewModel", "init")
    }

    fun login() {
        application.components.httpClient.login("string", "string")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginViewModel", "on Destory")
    }
}