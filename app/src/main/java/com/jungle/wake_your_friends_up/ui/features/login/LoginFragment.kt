package com.jungle.wake_your_friends_up.ui.features.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.databinding.FragmentLoginBinding
import com.jungle.wake_your_friends_up.ext.observeLiveData
import com.jungle.wake_your_friends_up.ui.dialog.setupBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel by viewModels<LoginViewModel>()


    override fun setupUi() {
        initListeners()
        observeLoginLiveData()
        observeResetPasswordLiveData()
    }

    private fun observeResetPasswordLiveData() {
        lifecycleScope.launchWhenStarted {
            observeLiveData(viewModel.resetPassword){
                when (it) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(
                            context,
                            "Reset link was sent to ${it.body.email}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is NetworkResult.Error -> {
                        Log.e("Login Fragment", it.error.message)
                    }
                }
            }
        }
    }

    private fun observeLoginLiveData() {
        lifecycleScope.launchWhenStarted {
            observeLiveData(viewModel.login) {
                when (it) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(
                            context,
                            "${it.body.fullName} successfully login",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is NetworkResult.Error -> {
                        Log.e("Login Fragment", it.error.message)
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }
            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            fabBack.setOnClickListener {
                findNavController().navigateUp()
            }
            tvForgotPassword.setOnClickListener {
                resetPassword()
            }
        }
    }

    private fun resetPassword() {
        setupBottomSheetDialog { email ->
            viewModel.resetPassword(email)
        }
    }

    private fun login() {
        binding.apply {
            val email = tietEmail.text.toString().trim()
            val password = tietPassword.text.toString().trim()


            viewModel.login(email, password)
        }


    }


}