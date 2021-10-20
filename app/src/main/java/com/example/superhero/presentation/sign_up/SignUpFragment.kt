package com.example.superhero.presentation.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.superhero.R
import com.example.superhero.SuperApp
import com.example.superhero.databinding.FragmentSignUpBinding
import com.example.superhero.getViewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel by lazy {
        getViewModel {
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

}