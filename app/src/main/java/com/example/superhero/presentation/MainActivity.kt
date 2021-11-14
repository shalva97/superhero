package com.example.superhero.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superhero.R
import com.example.superhero.navigation.Navigation
import com.example.superhero.presentation.login.LoginFragment
import com.example.superhero.presentation.sign_up.SignUpFragment

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isConfigChange(savedInstanceState)) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content, LoginFragment())
                commit()
            }
        }

    }

    private fun isConfigChange(savedInstanceState: Bundle?) = savedInstanceState == null

    override fun toSignUp() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, SignUpFragment())
        transaction.addToBackStack("asdf")
        transaction.commit()
    }

    override fun toLogIn() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, LoginFragment())
        transaction.commit()
    }
}

