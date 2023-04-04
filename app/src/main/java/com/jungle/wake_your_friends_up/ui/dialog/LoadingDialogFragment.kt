package com.jungle.wake_your_friends_up.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.core.BaseDialogFragment
import com.jungle.wake_your_friends_up.databinding.FragmentLoadingDialogBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * This Dialog Fragment will work at Loading status from api
 */
@AndroidEntryPoint
class LoadingDialogFragment(
    private val fragmentManager: FragmentManager
) : BaseDialogFragment<FragmentLoadingDialogBinding>(R.layout.fragment_loading_dialog) {

    override val bindingInflater: (LayoutInflater) -> FragmentLoadingDialogBinding
        get() = FragmentLoadingDialogBinding::inflate



    /**
     * LoadingDialog has to be full screen
     */
    override fun isFullScreenDialog() = true

    init {
        isCancelable = false
    }

    override fun setupUi() {

    }


    fun show(){
        show(fragmentManager, TAG)
    }

    fun hide(){
        dismissAllowingStateLoss()
    }

    companion object{
        private const val TAG = "LoadingDialog"
    }

}