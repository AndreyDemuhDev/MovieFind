package com.pidzama.moviefind.ui.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pidzama.moviefind.R
import com.pidzama.moviefind.databinding.FragmentCheckSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckSignInFragment : Fragment() {

    private lateinit var binding: FragmentCheckSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.buttonSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}