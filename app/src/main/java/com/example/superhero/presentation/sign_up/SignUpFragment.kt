package com.example.superhero.presentation.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.superhero.R
import com.example.superhero.SuperApp
import com.example.superhero.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel by lazy {
        (activity?.application as SuperApp).viewModelStorage.get(this::class) {
            SignUpViewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignUpBinding.bind(view)
        binding.signUpBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        if (activity?.isChangingConfigurations == false) {
            (activity?.application as SuperApp).viewModelStorage.remove(this::class)
        }
    }
}