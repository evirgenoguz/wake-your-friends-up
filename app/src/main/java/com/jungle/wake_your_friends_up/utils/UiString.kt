package com.jungle.wake_your_friends_up.utils

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Created by Burak Taşcı on 3.03.2023.
 */
@Parcelize
class UiString(
    @StringRes private val resId: Int,
    private vararg val args: @RawValue Any
): Parcelable {
    companion object {
        private const val INVALID_RES_ID = 0

        val EMPTY = UiString(INVALID_RES_ID)
    }

    fun get(context: Context) = if (resId == INVALID_RES_ID)
        ""
    else
        context.getString(resId, *args)
}