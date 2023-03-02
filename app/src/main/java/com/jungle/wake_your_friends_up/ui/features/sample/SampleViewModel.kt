package com.jungle.wake_your_friends_up.ui.features.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jungle.wake_your_friends_up.core.BaseViewModel
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.model.request.SampleRequestModel
import com.jungle.wake_your_friends_up.data.model.response.SampleResponseModel
import com.jungle.wake_your_friends_up.data.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleRepository: SampleRepository
) : BaseViewModel() {

    private val _sampleLiveData = MutableLiveData<NetworkResult<SampleResponseModel>>()
    val sampleLiveData: LiveData<NetworkResult<SampleResponseModel>> = _sampleLiveData


    fun sampleRequest(requestModel: SampleRequestModel){
        viewModelScope.launch {
            _sampleLiveData.postValue(NetworkResult.Loading)
            val result = sampleRepository.sampleRequest()
            _sampleLiveData.postValue(result)
        }
    }
}