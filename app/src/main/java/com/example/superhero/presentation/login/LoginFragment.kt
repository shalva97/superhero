package com.example.superhero.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.superhero.R
import com.example.superhero.SuperApp
import com.example.superhero.databinding.FragmentLoginBinding
import com.example.superhero.navigation

class LoginFragment : Fragment(R.layout.fragment_login) {

    val viewModel by lazy {
        (activity?.application as SuperApp).viewModelStorage.get(this::class)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentLoginBinding.bind(view).apply {
            signUp.setOnClickListener {
                navigation().toSignUp()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.isChangingConfigurations
    }
}
