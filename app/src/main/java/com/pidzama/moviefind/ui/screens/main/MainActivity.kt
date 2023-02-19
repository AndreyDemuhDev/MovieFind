package com.pidzama.moviefind.ui.screens.main

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pidzama.moviefind.R
import com.pidzama.moviefind.utils.AirplaneReceiverMode
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val receiver = AirplaneReceiverMode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        val navController = getRootNavController()
        launchNavController(isSignedIn(), isFirstOpen(), navController)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

    private fun isSignedIn(): Boolean {
        val bundle = intent.extras ?: throw IllegalStateException("No required arguments SIGN IN")
        val args = MainActivityArgs.fromBundle(bundle)
        return args.isSignedIn
    }

    private fun isFirstOpen(): Boolean {
        return mainViewModel.isFirstStart()
    }

    private fun launchNavController(
        isSignedIn: Boolean,
        isFirstOpen: Boolean,
        navController: NavController
    ) {
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())
        graph.setStartDestination(
            if (isFirstOpen) {
                getViewPagerDestination()
            } else if (isSignedIn) {
                getTabsDestination()
            } else {
                getSignInDestination()
            }
        )
        navController.graph = graph
    }

    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.containerMainFragment) as NavHostFragment
        return navHost.navController
    }

    private fun getMainNavigationGraphId(): Int = R.navigation.main_nav_graph

    private fun getTabsDestination(): Int = R.id.tabsFragment

    private fun getSignInDestination(): Int = R.id.signInFragment

    private fun getViewPagerDestination(): Int = R.id.viewPagerFragment

}