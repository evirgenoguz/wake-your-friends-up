package com.jungle.wake_your_friends_up.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jungle.wake_your_friends_up.utils.LogHelper
import com.jungle.wake_your_friends_up.utils.UiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Burak Taşcı on 2.03.2023.
 */

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }

    /**
     * Determines whether the app should show splash screen
     * or not.
     */
    var isLoading = true

    init {
        viewModelScope.launch {
            performSplashScreen()
        }
    }


    /**
     * Performs the splash screen operation.
     */
    private suspend fun performSplashScreen() {
        // Do it in runCatching block so we can catch
        // if the call has been successfully completed.
        runCatching {
            // To not skip the splash screen, delay for
            // a couple of seconds and continue.
            delay(UiConstants.SPLASH_SCREEN_DURATION)
        }.onSuccess {
            delay(UiConstants.SPLASH_SCREEN_DURATION_EXTRA)
            isLoading = false
        }.onFailure {
            // A mock debug message for splash screen
            LogHelper.logDebug(TAG, "Failed to load data.", it)
        }
    }
}