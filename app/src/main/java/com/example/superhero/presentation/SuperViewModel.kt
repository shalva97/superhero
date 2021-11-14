package com.example.superhero.presentation

import android.util.Log
import androidx.annotation.CallSuper

abstract class SuperViewModel {

    init {
        Log.d(this::class.java.canonicalName, "init")
    }

    @CallSuper
    open fun onDestroy() {
        Log.d("LoginViewModel", "on Destory")
    }
}