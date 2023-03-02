package com.jungle.wake_your_friends_up.data

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.google.gson.Gson
import com.jungle.wake_your_friends_up.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Provider

/**
 * Created by Burak Taşcı on 21.02.2023.
 */
class NetworkManager(
    private val application: Context,
    private val gsonProvider: Provider<Gson>
) {

    private companion object {
        val TAG: String = NetworkManager::class.java.simpleName
    }

    /**
     * Retrieves the GSON object. Used as a provider to
     * eliminate performance issues when the object is creating.
     */
    private val gson: Gson
        get() = gsonProvider.get()

    /**
     * The dispatcher to be used when making requests.
     */
    private val dispatcher = Dispatchers.IO

    /**
     * Helper function to manage errors, success states with
     * help of [Retrofit] and [NetworkResult].
     */
    suspend fun <T> makeRequest(
        apiCall: suspend NetworkManager.() -> T
    ): NetworkResult<T> {
        return withContext(dispatcher) {
            try {
                NetworkResult.Success(apiCall(this@NetworkManager))
            } catch (ioException: IOException) {
                Log.d(TAG, "Network Connection Failed.")
                NetworkResult.Error(
                    ServerErrorModel(message = stringResource(R.string.not_connected_try_again))
                )
            } catch (exception: Exception) {
                val errorMessage = exception.localizedMessage ?: exception.message ?: stringResource(R.string.general_error_message)
                NetworkResult.Error(
                    ServerErrorModel(message = errorMessage)
                )
            }
        }
    }

    /**
     * To retrieve strings from resource id with help of
     * injected application context.
     */
    private fun stringResource(@StringRes resId: Int) = application.getString(resId)

}