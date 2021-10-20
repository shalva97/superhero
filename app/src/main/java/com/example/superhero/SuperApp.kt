package com.example.superhero

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.superhero.presentation.SuperViewModel
import java.util.*

class SuperApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}

private val storage = HashMap<String, SuperViewModel>()

fun Fragment.getViewModel(viewModelFactory: () -> SuperViewModel): SuperViewModel {
    val superViewModel = storage[this::class.qualifiedName]
    return if (superViewModel == null) {

        lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                if (activity?.isChangingConfigurations == false) {
                    storage.remove(this::class.qualifiedName)
                        ?.onDestroy()
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