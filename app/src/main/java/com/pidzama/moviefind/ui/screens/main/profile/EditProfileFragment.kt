package com.pidzama.moviefind.ui.screens.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pidzama.moviefind.databinding.FragmentEditProfileBinding
import com.pidzama.moviefind.ui.screens.auth.login.AuthorisationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: AuthorisationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailText.text = viewModel.getEmail()

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonChangePhoto.setOnClickListener {
            val chooseImage = Intent(Intent.ACTION_PICK)
            chooseImage.type = "image/*"
            startActivity(chooseImage)
        }

        binding.buttonSaveChange.setOnClickListener {

        }

    }
}