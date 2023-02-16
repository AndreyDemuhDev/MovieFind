package com.pidzama.moviefind.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pidzama.moviefind.ui.screens.main.MainActivity
import com.pidzama.moviefind.databinding.FragmentSplashBinding
import com.pidzama.moviefind.repository.AuthorisationRepository
import com.pidzama.moviefind.ui.screens.main.MainActivityArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val authRepository = AuthorisationRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return (binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded) {
            launchMainActivity()
        }
    }

    private fun launchMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val args = MainActivityArgs(isSignedIn = launchSignInFragment())
        intent.putExtras(args.toBundle())

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(intent)
        }
    }

    private fun launchSignInFragment(): Boolean {
        return authRepository.isUserLogin()
    }
}