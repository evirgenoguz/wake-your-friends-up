package com.jungle.wake_your_friends_up.ui.features.start

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.databinding.FragmentStartBinding

class StartFragment : BaseFragment<FragmentStartBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentStartBinding
        get() = FragmentStartBinding::inflate

    override fun setupUi() {
        initListeners()
    }

    private fun initListeners(){
        binding.apply {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
        }
    }


}