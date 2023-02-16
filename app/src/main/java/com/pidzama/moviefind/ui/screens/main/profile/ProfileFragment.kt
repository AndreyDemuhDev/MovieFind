package com.pidzama.moviefind.ui.screens.main.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.pidzama.moviefind.R
import com.pidzama.moviefind.databinding.FragmentProfileBinding
import com.pidzama.moviefind.ui.screens.auth.login.AuthorisationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModelAuth: AuthorisationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEditProfile.setOnClickListener {
            changeNavFragment().navigate(R.id.editProfileFragment)
        }

        binding.buttonLogout.setOnClickListener {
            onClickLogout()
        }

    }

    /**
     * Функция которая переопряделяет нафигационный контейнер
     */
    private fun changeNavFragment(): NavController {
        val navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.containerMainFragment) as NavHostFragment?
        return navHost?.navController ?: findNavController()
    }


    fun onClickLogout() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.logout_account)
            .setPositiveButton(R.string.btn_logout) { _, _ ->
                viewModelAuth.logout()
                changeNavFragment().navigate(R.id.signInFragment)
                Toast.makeText(requireContext(), R.string.logout_successful, Toast.LENGTH_SHORT)
                    .show()
            }
            .setNegativeButton(R.string.btn_no) { _, _ ->

            }
            .show()
    }
}