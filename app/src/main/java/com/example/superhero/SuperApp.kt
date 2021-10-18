package com.example.superhero

import android.app.Application
import com.example.superhero.presentation.SuperViewModel
import com.example.superhero.presentation.login.LoginFragment
import java.util.*
import kotlin.reflect.KClass

class SuperApp : Application() {

    val viewModelStorage = ViewModelStorage()

    override fun onCreate() {
        super.onCreate()
    }
}

class ViewModelStorage() {

    private val storage = HashMap<String, SuperViewModel>()

    fun get(clazz: KClass<LoginFragment>): SuperViewModel? {
        return storage[clazz.qualifiedName!!]
            ?: storage.put(clazz.qualifiedName!!, clazz)
    }

}