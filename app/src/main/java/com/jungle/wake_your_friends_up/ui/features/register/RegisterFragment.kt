package com.jungle.wake_your_friends_up.ui.features.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setupUi() {
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                // TODO: Use Firebase Auth and Firestore for save data
            }
            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            fabBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


}