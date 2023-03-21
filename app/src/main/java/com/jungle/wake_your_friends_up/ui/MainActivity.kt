package com.jungle.wake_your_friends_up.ui

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.jungle.wake_your_friends_up.core.BaseActivity
import com.jungle.wake_your_friends_up.databinding.ActivityMainBinding
import com.jungle.wake_your_friends_up.ext.findNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun preOnCreate() {
        super.preOnCreate()
        // splash screen setup, it appears according
        // to MainViewModel's isLoading variable.
        setupSplashScreen()
    }

    override fun setupUi() {
        // getting NavController with findNavController
        // extension.
        navController = findNavController()

        // Create the app bar configuration to support
        // unhandled navigate up calls.
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupSplashScreen() {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading
        }
    }
}