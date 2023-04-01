package com.jungle.wake_your_friends_up.ui.features.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.ServerErrorModel
import com.jungle.wake_your_friends_up.data.model.request.LoginRequestModel
import com.jungle.wake_your_friends_up.data.model.request.ResetPasswordRequestModel
import com.jungle.wake_your_friends_up.data.model.response.LoginResponseModel
import com.jungle.wake_your_friends_up.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _login = MutableLiveData<NetworkResult<LoginResponseModel>>()
    val login: LiveData<NetworkResult<LoginResponseModel>> = _login

    private val _resetPassword = MutableLiveData<NetworkResult<Unit>>()
    val resetPassword: LiveData<NetworkResult<Unit>> = _resetPassword

    fun login(loginRequestModel: LoginRequestModel) {
        viewModelScope.launch {
            _login.postValue(NetworkResult.Loading)
            authRepository.login(loginRequestModel)
                .addOnSuccessListener { authResult ->
                    authResult.user?.let { user ->
                        val uid = user.uid
                        _login.postValue(NetworkResult.Success(LoginResponseModel(uid = uid)))
                    } ?: kotlin.run {
                        _login.postValue(
                            NetworkResult.Error(
                                ServerErrorModel(
                                    "Cannot access user data."
                                )
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    _login.postValue(
                        NetworkResult.Error(
                            ServerErrorModel(
                                it.localizedMessage ?: "An error occurred!"
                            )
                        )
                    )
                }
        }
        Patterns.EMAIL_ADDRESS
    }

    fun resetPassword(resetPasswordRequestModel: ResetPasswordRequestModel) {
        viewModelScope.launch {
            _resetPassword.postValue(NetworkResult.Loading)
            authRepository.resetPassword(resetPasswordRequestModel)
                .addOnSuccessListener {
                    _resetPassword.postValue(
                        NetworkResult.Success(
                            Unit
                        )
                    )
                }
                .addOnFailureListener {
                    _resetPassword.postValue(
                        NetworkResult.Error(
                            ServerErrorModel(
                                it.localizedMessage ?: "An error occurred!"
                            )
                        )
                    )
                }
        }
    }


}