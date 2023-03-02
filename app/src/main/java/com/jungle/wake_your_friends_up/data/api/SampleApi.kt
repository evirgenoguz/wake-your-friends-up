package com.jungle.wake_your_friends_up.data.api

import com.jungle.wake_your_friends_up.data.model.request.SampleRequestModel
import com.jungle.wake_your_friends_up.data.model.response.SampleResponseModel
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created by Burak Taşcı on 20.02.2023.
 */
interface SampleApi {

    @GET(ENDPOINT_SAMPLE)
    suspend fun sampleRequest(@Body requestModel: SampleRequestModel): SampleResponseModel


    private companion object{

        const val ENDPOINT_SAMPLE = "sample-endpoint"
    }
}