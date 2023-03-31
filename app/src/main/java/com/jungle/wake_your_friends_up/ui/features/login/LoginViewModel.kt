package com.jungle.wake_your_friends_up.ui.features.login

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.ServerErrorModel
import com.jungle.wake_your_friends_up.data.model.response.UserResponseModel
import com.jungle.wake_your_friends_up.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _login = MutableLiveData<NetworkResult<UserResponseModel>>()
    val login: LiveData<NetworkResult<UserResponseModel>> = _login

    private val _resetPassword = MutableLiveData<NetworkResult<UserResponseModel>>()
    val resetPassword: LiveData<NetworkResult<UserResponseModel>> = _resetPassword

    fun login(email: String, password: String){
        viewModelScope.launch {
            _login.postValue(NetworkResult.Loading)
            authRepository.login(email, password)
                .addOnSuccessListener {
                    _login.postValue(NetworkResult.Success(UserResponseModel(uid = it.user!!.uid ,email = email)))
                }
                .addOnFailureListener {
                    _login.postValue(NetworkResult.Error(ServerErrorModel(it.localizedMessage ?: "An error occurred!")))
                }
        }
        Patterns.EMAIL_ADDRESS
    }

    fun resetPassword(email: String){
        viewModelScope.launch {
            _resetPassword.postValue(NetworkResult.Loading)
            authRepository.resetPassword(email)
                .addOnSuccessListener {
                    _resetPassword.postValue(NetworkResult.Success(UserResponseModel(email = email)))
                }
                .addOnFailureListener {
                    _resetPassword.postValue(NetworkResult.Error(ServerErrorModel(it.localizedMessage ?: "An error occurred!")))
                }
        }
    }


}