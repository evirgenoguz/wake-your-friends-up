package com.jungle.wake_your_friends_up.data

/**
 * Created by Burak Taşcı on 21.02.2023.
 */
sealed class NetworkResult<out T> {

    data class Success<out T>(val body: T) : NetworkResult<T>()

    object Loading : NetworkResult<Nothing>()

    data class Error(val error: ServerErrorModel) : NetworkResult<Nothing>()

    inline fun onSuccess(
        block: (T) -> Unit
    ): NetworkResult<T> {
        return this.also {
            if (it is Success<T>)
                block(it.body)
        }
    }

    inline fun onError(
        block: (ServerErrorModel) -> Unit
    ): NetworkResult<T> {
        return this.also {
            if (it is Error)
                block(it.error)
        }
    }

    inline fun onLoading(
        block: () -> Unit
    ): NetworkResult<T> {
        return this.also {
            if (it is Loading)
                block()
        }
    }
}
