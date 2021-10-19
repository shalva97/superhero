package com.example.superhero

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.superhero.presentation.SuperViewModel
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

    fun get(
        clazz: KClass<out Fragment>,
        viewModelFactory: () -> SuperViewModel
    ): SuperViewModel? {
        return storage[clazz.qualifiedName]
            ?: storage.put(clazz.qualifiedName!!, viewModelFactory.invoke())
    }

    fun remove(clazz: KClass<out Fragment>) {
        storage.remove(clazz.qualifiedName)
            ?.onDestroy()
    }
}