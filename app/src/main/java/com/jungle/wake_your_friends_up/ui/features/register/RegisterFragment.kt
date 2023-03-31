package com.jungle.wake_your_friends_up.ui.features.register

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
import com.jungle.wake_your_friends_up.data.model.request.UserRequestModel
import com.jungle.wake_your_friends_up.databinding.FragmentRegisterBinding
import com.jungle.wake_your_friends_up.ext.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate


    private val viewModel by viewModels<RegisterViewModel>()

    override fun setupUi() {
        initListeners()
        observeRegisterLiveData()
    }

    private fun observeRegisterLiveData() {
        lifecycleScope.launchWhenStarted {
            observeLiveData(viewModel.register) {
                when (it) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(
                            context,
                            //TODO: get uid and navigate to inside app
                            "${it.body.email} successfully auth",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is NetworkResult.Error -> {
                        Log.e("Register Fragment", it.error.message)
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                register()
            }
            tvLogin.setOnClickListener {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
            fabBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun register() {
        binding.apply {
            val userRequestModel = UserRequestModel(
                tietEmail.text.toString().trim(),
                tietPassword.text.toString().trim()
            )

            viewModel.register(userRequestModel)
        }

    }


}