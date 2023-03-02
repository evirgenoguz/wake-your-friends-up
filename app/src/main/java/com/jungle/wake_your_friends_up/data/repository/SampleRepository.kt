package com.jungle.wake_your_friends_up.data.repository

import com.jungle.wake_your_friends_up.data.NetworkManager
import com.jungle.wake_your_friends_up.data.NetworkResult
import com.jungle.wake_your_friends_up.data.api.SampleApi
import com.jungle.wake_your_friends_up.data.model.response.SampleResponseModel
import kotlinx.coroutines.delay

/**
 * Created by Burak Taşcı on 21.02.2023.
 */
class SampleRepository(
    private val apiService: SampleApi,
    private val networkManager: NetworkManager
) {

    suspend fun sampleRequest(): NetworkResult<SampleResponseModel> {
        delay(3000L)
//        return networkManager.makeRequest {
//            apiService.sampleRequest(SampleRequestModel(sample = "21"))
//        }
        return NetworkResult.Success(SampleResponseModel(sampleId = 2131))
    }
}