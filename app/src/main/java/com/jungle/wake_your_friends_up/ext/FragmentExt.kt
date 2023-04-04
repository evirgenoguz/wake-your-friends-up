package com.jungle.wake_your_friends_up.ext


import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.jungle.wake_your_friends_up.ui.dialog.LoadingDialogFragment

/**
 * Created by Oguz Evirgen on 28.03.2023.
 */

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, block: (T) -> Unit){

    liveData.observe(viewLifecycleOwner){
        it?.let(block)
    }
}


