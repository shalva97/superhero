package com.example.superhero.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.superhero.R
import com.example.superhero.SuperApp
import com.example.superhero.databinding.FragmentLoginBinding
import com.example.superhero.getViewModel
import com.example.superhero.navigation

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel = getViewModel {
        LoginViewModel(context?.applicationContext as SuperApp)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentLoginBinding.bind(view).apply {
            signUp.setOnClickListener {
                navigation().toSignUp()
            }

            signIn.setOnClickListener {
                viewModel.login()
            }
        }
        viewModel

    }
}
