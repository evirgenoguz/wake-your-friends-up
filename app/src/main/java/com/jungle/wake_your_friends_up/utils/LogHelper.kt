package com.jungle.wake_your_friends_up.utils

import android.util.Log
import com.jungle.wake_your_friends_up.BuildConfig

/**
 * Created by Burak Taşcı on 2.03.2023.
 */
object LogHelper {


    fun logDebug(tag: String, message: String, throwable: Throwable? = null) {
        if (BuildConfig.DEBUG){
            val stackTrace = Log.getStackTraceString(throwable)
            Log.w(tag, "$message\n$stackTrace")
        }
    }

    /**
     * Wraps a result object and logs its exception if any failure happens.
     *
     * @param throwIfDebug will throw the exception if true and the
     * app is in debug mode.
     */
    fun <T> Result<T>.logException(throwIfDebug: Boolean = false): Result<T> {
        return onFailure {
            handleException(it, throwIfDebug)
        }
    }

    fun handleException(t: Throwable?, throwIfDebug: Boolean = false) {
        logDebug("Result", "Exception while performing runCatching block.", t)
        if (throwIfDebug && BuildConfig.DEBUG && t != null) {
            // Exit the task and make sure the program quits.
            Thread {
                throw t
            }.start()
        }
    }
}