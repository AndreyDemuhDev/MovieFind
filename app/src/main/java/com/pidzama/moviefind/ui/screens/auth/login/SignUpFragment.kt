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
import com.pidzama.moviefind.databinding.FragmentSignUpBinding
import com.pidzama.moviefind.utils.validation.Invalid
import com.pidzama.moviefind.utils.validation.validateEmail
import com.pidzama.moviefind.utils.validation.validatePassword
import com.pidzama.moviefind.utils.validation.validateName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val authViewModel: AuthorisationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.login.setOnClickListener {
            if (validate()) {
                authViewModel.registrationUser(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString(),
                    {
                        findNavController().navigate(R.id.tabsFragment)
                    }, {
                        binding.errorText.text = resources.getText(R.string.email_is_busy)
                    }
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.registration_failed,
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
        val isNameValid = validateName()
        return isPasswordValid == null && isEmailValid == null && isNameValid == null
    }

    private fun validatePassword(): String? {
        val passwordInputLayout = binding.inputPassword
        passwordInputLayout.editText?.let {
            return when (val result = validatePassword(it.text.toString())) {
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
            return when (val result = validateEmail(it.text.toString())) {
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

    private fun validateName(): String? {
        val nameInputLayout = binding.inputName
        nameInputLayout.editText?.let {
            return when (val result = validateName(it.text.toString())) {
                is Invalid -> {
                    nameInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    nameInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

}