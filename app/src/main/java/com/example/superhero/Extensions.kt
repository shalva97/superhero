package com.example.superhero

import androidx.fragment.app.Fragment
import com.example.superhero.navigation.Navigation

fun Fragment.navigation(): Navigation {
    return activity as Navigation
}

