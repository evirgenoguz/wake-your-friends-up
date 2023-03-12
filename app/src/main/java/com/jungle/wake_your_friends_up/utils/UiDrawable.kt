package com.jungle.wake_your_friends_up.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize

/**
 * Created by Burak Taşcı on 3.03.2023.
 */
@Parcelize
class UiDrawable(
    @DrawableRes private val resId: Int
): Parcelable {
    companion object {
        private const val INVALID_RES_ID = 0

        val EMPTY = UiDrawable(INVALID_RES_ID)
    }

    fun get(context: Context) = if (resId == INVALID_RES_ID)
        ColorDrawable(Color.BLACK)
    else
        ContextCompat.getDrawable(context, resId)
}