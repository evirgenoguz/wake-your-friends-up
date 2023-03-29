package com.jungle.wake_your_friends_up.ui.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jungle.wake_your_friends_up.R

/**
 * Created by Oguz Evirgen on 29.03.2023.
 */

fun Fragment.setupBottomSheetDialog(
    onSendClick: (String) -> Unit
) {
    val dialog = BottomSheetDialog(requireContext())
    val view = layoutInflater.inflate(R.layout.dialog_reset_password, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val etEmail= view.findViewById<EditText>(R.id.etEmailForResetPassword)
    val btnSend = view.findViewById<Button>(R.id.btnSendResetPassword)
    val btnCancel = view.findViewById<Button>(R.id.btnCancelResetPassword)

    btnSend.setOnClickListener {
        val email = etEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    btnCancel.setOnClickListener {
        dialog.dismiss()
    }
}