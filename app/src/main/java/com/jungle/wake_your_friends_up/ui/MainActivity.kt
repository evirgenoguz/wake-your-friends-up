package com.jungle.wake_your_friends_up.ui

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.jungle.wake_your_friends_up.core.BaseActivity
import com.jungle.wake_your_friends_up.databinding.ActivityMainBinding
import com.jungle.wake_your_friends_up.ext.findNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val viewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavController

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupUi() {
        // splash screen setup, it appears according
        // to MainViewModel's isLoading variable.
        setupSplashScreen()

        // getting NavController with findNavController
        // extension.
        navController = findNavController()
    }

    private fun setupSplashScreen() {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading
        }
    }
}