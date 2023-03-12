package com.jungle.wake_your_friends_up.ext

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

/**
 * Created by Burak Taşcı on 3.03.2023.
 */
@Suppress("DEPRECATION")
inline fun <reified T: Parcelable> Bundle.getParcelableModel(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key)
    }
}