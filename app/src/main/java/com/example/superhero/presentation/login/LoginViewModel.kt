package com.example.superhero.presentation.login

import android.util.Log
import com.example.superhero.presentation.SuperViewModel

class LoginViewModel : SuperViewModel() {
    init {
        Log.d("LoginViewModel", "init")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginViewModel", "on Destory")
    }
}