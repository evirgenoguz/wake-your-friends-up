package com.jungle.wake_your_friends_up.ui.features.register

import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.model.request.LoginRequestModel
import com.jungle.wake_your_friends_up.data.model.request.RegisterRequestModel
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
                            "${it.body.uid} successfully auth",
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
            val registerRequestModel = RegisterRequestModel(
                tietEmail.text.toString().trim(),
                tietPassword.text.toString().trim()
            )

            viewModel.register(registerRequestModel)
        }

    }


}