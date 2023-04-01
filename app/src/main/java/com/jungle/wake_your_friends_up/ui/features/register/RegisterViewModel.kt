package com.jungle.wake_your_friends_up.ui.features.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jungle.wake_your_friends_up.core.BaseViewModel
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.ServerErrorModel
import com.jungle.wake_your_friends_up.data.model.request.LoginRequestModel
import com.jungle.wake_your_friends_up.data.model.request.RegisterRequestModel
import com.jungle.wake_your_friends_up.data.model.response.LoginResponseModel
import com.jungle.wake_your_friends_up.data.model.response.RegisterResponseModel
import com.jungle.wake_your_friends_up.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _register = MutableLiveData<NetworkResult<RegisterResponseModel>>()
    val register: LiveData<NetworkResult<RegisterResponseModel>> = _register


    fun register(registerRequestModel: RegisterRequestModel) {
        viewModelScope.launch {
            _register.postValue(NetworkResult.Loading)
            authRepository.register(registerRequestModel)
                .addOnSuccessListener { authResult ->
                    authResult.user?.uid?.let { uid ->
                        _register.postValue(
                            NetworkResult.Success(
                                RegisterResponseModel(
                                    uid = uid
                                )
                            )
                        )
                    } ?: kotlin.run {
                        _register.postValue(
                            NetworkResult.Error(
                                ServerErrorModel(
                                    "Can not access user data."
                                )
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    _register.postValue(
                        NetworkResult.Error(
                            ServerErrorModel(
                                it.localizedMessage ?: "An error occurred"
                            )
                        )
                    )
                }
        }
    }
}