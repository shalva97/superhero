package com.example.superhero

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.superhero.presentation.SuperViewModel
import com.superhero.lib.Client
import java.util.*

class SuperApp : Application() {

    val components = Components()

    override fun onCreate() {
        super.onCreate()
    }
}

class Components {
    val httpClient by lazy { Client() }
}

private val storage = HashMap<String, SuperViewModel>()

fun <T: SuperViewModel> Fragment.getViewModel(viewModelFactory: () -> T): T {
    val superViewModel = storage[this::class.qualifiedName] as? T
    return if (superViewModel == null) {

        lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                if (activity?.isChangingConfigurations == false) {
                    storage.remove(this@getViewModel::class.qualifiedName)
                        ?.onDestroy()
                    lifecycle.removeObserver(this)
                }
            }
        })

        val newViewModel = viewModelFactory.invoke()
        storage[this::class.qualifiedName!!] = newViewModel
        newViewModel
    } else {
        superViewModel
    }
}