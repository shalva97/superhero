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

private fun get(
    fragment: Fragment,
    viewModelFactory: () -> SuperViewModel
): SuperViewModel {

    val superViewModel = storage[fragment::class.qualifiedName]
    return if (superViewModel == null) {

        fragment.lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                storage.remove(fragment::class.qualifiedName)
                    ?.onDestroy()
            }
        })

        val newViewModel = viewModelFactory.invoke()
        storage[fragment::class.qualifiedName!!] = newViewModel
        newViewModel
    } else {
        superViewModel
    }
}

fun Fragment.getViewModel(viewModelFactory: () -> SuperViewModel): SuperViewModel {
    return get(this, viewModelFactory)
}