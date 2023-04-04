package com.jungle.wake_your_friends_up.core

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.jungle.wake_your_friends_up.R
import com.jungle.wake_your_friends_up.ext.withNotNull

/**
 * Created by Oguz Evirgen on 3.04.2023.
 */
abstract class BaseDialogFragment<VB : ViewBinding>(private val layoutId: Int) : DialogFragment() {

    //Fragment Binding
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    // The inflater class for ViewBinding
    abstract val bindingInflater: (LayoutInflater) -> VB

    // The TAG value to use in logs.
    protected val TAG = javaClass.simpleName

    // The function to use the UI setup
    protected abstract fun setupUi()

    open fun isFullScreenDialog() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (isFullScreenDialog()) {
            with(dialog) {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
            }
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        if (isFullScreenDialog()) {
            withNotNull(dialog?.window){
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                WindowCompat.setDecorFitsSystemWindows(this, false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}