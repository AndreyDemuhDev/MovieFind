package com.pidzama.moviefind.ui.screens.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pidzama.moviefind.R
import com.pidzama.moviefind.databinding.FragmentSignInBinding
import com.pidzama.moviefind.utils.validation.Invalid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val authViewModel: AuthorisationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignUp.setOnClickListener {
            if (validate()) {
                authViewModel.signInUser(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ) {
                    findNavController().navigate(R.id.tabsFragment)
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.sign_up_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun validate(): Boolean {
        val isPasswordValid = validatePassword()
        val isEmailValid = validateEmail()
        return isPasswordValid == null && isEmailValid == null
    }

    private fun validatePassword(): String? {
        val passwordInputLayout = binding.inputPassword
        passwordInputLayout.editText?.let {
            val result = com.pidzama.moviefind.utils.validation.validatePassword(it.text.toString())
            return when (result) {
                is Invalid -> {
                    passwordInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    passwordInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    private fun validateEmail(): String? {
        val emailInputLayout = binding.inputEmail
        emailInputLayout.editText?.let {
            val result = com.pidzama.moviefind.utils.validation.validateEmail(it.text.toString())
            return when (result) {
                is Invalid -> {
                    emailInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    emailInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

}