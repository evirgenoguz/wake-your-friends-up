package com.jungle.wake_your_friends_up.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

/**
 * Created by Oguz Evirgen on 28.03.2023.
 */

/*
viewModel.sampleLiveData.observe(viewLifecycleOwner) { result ->
    result.onLoading {
        // TODO Create a loading animation for here
        Toast.makeText(
            requireContext(),
            "Loading animation should be here.",
            Toast.LENGTH_LONG
        ).show()
    }.onSuccess { responseModel ->
        binding.tvSomeKindOfText.text = responseModel.sampleId.toString()
    }.onError { error ->
        // TODO create a dialog fragment for showing error messages
        Snackbar.make(binding.linearLayout, error.message, 1000).show()
    }
}
*/

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, block: (T) -> Unit){

    liveData.observe(viewLifecycleOwner){
        it?.let(block)
    }
}
