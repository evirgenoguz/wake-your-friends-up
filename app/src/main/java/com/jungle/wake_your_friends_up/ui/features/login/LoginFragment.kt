package com.jungle.wake_your_friends_up.ui.features.login

import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.core.BaseFragment
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.model.request.LoginRequestModel
import com.jungle.wake_your_friends_up.data.model.request.ResetPasswordRequestModel
import com.jungle.wake_your_friends_up.databinding.FragmentLoginBinding
import com.jungle.wake_your_friends_up.ext.observeLiveData
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
                            "Reset link was sent to",
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
                        // TODO: navigate inside the app with uid
                        Toast.makeText(
                            context,
                            "${it.body.uid} successfully login",
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
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
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
            viewModel.resetPassword(ResetPasswordRequestModel(email))
        }
    }

    private fun login() {
        binding.apply {
            val email = tietEmail.text.toString().trim()
            val password = tietPassword.text.toString().trim()

            viewModel.login(LoginRequestModel(email, password))
        }

    }


}

private fun LoginFragment.setupBottomSheetDialog(
    onSendClick: (String) -> Unit
) {
    val dialog = BottomSheetDialog(requireContext())
    val view = layoutInflater.inflate(R.layout.dialog_reset_password, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val etEmail= view.findViewById<EditText>(R.id.etEmailForResetPassword)
    val btnSend = view.findViewById<Button>(R.id.btnSendResetPassword)
    val btnCancel = view.findViewById<Button>(R.id.btnCancelResetPassword)

    btnSend.setOnClickListener {
        val email = etEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    btnCancel.setOnClickListener {
        dialog.dismiss()
    }
}