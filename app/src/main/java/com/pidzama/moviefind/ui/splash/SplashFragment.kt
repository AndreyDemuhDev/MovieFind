package com.pidzama.moviefind.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pidzama.moviefind.databinding.FragmentSplashBinding
import com.pidzama.moviefind.repository.FirebaseAuthRepository
import com.pidzama.moviefind.ui.main.MainActivity
import com.pidzama.moviefind.ui.main.MainActivityArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val authRepository = FirebaseAuthRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return (binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchMainActivity()
    }

    private fun launchMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        val args = MainActivityArgs(isSignedIn = launchCheckSignInFragment())
        intent.putExtras(args.toBundle())

        if (isAdded) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                startActivity(intent)
            }
        }
    }

    private fun launchCheckSignInFragment(): Boolean {
        return authRepository.isUserLogin()
    }
}