package com.example.superhero.presentation.sign_up

import android.util.Log
import com.example.superhero.presentation.SuperViewModel

class SignUpViewModel : SuperViewModel() {

    init {
        Log.d("SignUpViewModel", "on init block")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignUpViewModel", "on Destory")
    }
}