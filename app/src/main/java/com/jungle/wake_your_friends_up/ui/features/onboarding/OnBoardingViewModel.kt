package com.jungle.wake_your_friends_up.ui.features.onboarding

import android.graphics.Bitmap
import android.os.Parcelable
import com.jungle.wake_your_friends_up.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : BaseViewModel() {





    @Parcelize
    data class OnBoardingModel(
        val title: String,
        val contentImage: Bitmap
    ): Parcelable
}