package com.example.superhero.presentation.login

import com.example.superhero.async.SuperThreadPool
import com.example.superhero.presentation.SuperViewModel
import com.superhero.lib.Client

class LoginViewModel(
    private val client: Client, private val superThreadPool: SuperThreadPool
) : SuperViewModel() {

    private fun exceptionHandler(exception: Throwable) {
        exception.printStackTrace()
    }

    fun login() {
        superThreadPool.launch(::exceptionHandler) {
            client.login("string", "string")
        }
    }
}