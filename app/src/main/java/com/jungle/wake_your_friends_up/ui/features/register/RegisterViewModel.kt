package com.jungle.wake_your_friends_up.ui.features.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jungle.wake_your_friends_up.core.BaseViewModel
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.ServerErrorModel
import com.jungle.wake_your_friends_up.data.model.request.UserRequestModel
import com.jungle.wake_your_friends_up.data.model.response.UserResponseModel
import com.jungle.wake_your_friends_up.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {

    private val _register = MutableLiveData<NetworkResult<UserResponseModel>>()
    val register: LiveData<NetworkResult<UserResponseModel>> = _register



    fun register(userRequestModel: UserRequestModel){
        viewModelScope.launch {
            _register.postValue(NetworkResult.Loading)
            authRepository.register(userRequestModel)
                .addOnSuccessListener {
                    _register.postValue(NetworkResult.Success(UserResponseModel(fullName = userRequestModel.fullName, email = userRequestModel.email)))
                }
                .addOnFailureListener {
                    _register.postValue(NetworkResult.Error(ServerErrorModel(it.localizedMessage ?: "An error occurred")))
                }
        }

    }

}