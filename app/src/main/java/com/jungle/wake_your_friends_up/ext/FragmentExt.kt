package com.jungle.wake_your_friends_up.ext

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jungle.wake_your_friends_up.R

/**
 * Created by Oguz Evirgen on 28.03.2023.
 */

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, block: (T) -> Unit){

    liveData.observe(viewLifecycleOwner){
        it?.let(block)
    }
}


