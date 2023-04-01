package com.jungle.wake_your_friends_up.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.jungle.wake_your_friends_up.data.model.request.LoginRequestModel
import com.jungle.wake_your_friends_up.data.model.request.RegisterRequestModel
import com.jungle.wake_your_friends_up.data.model.request.ResetPasswordRequestModel

/**
 * Created by Oguz Evirgen on 26.03.2023.
 */

class AuthRepository (
    private val firebaseAuth: FirebaseAuth,
) {
    fun register(registerRequestModel: RegisterRequestModel) = firebaseAuth.createUserWithEmailAndPassword(registerRequestModel.email, registerRequestModel.password)

    fun login(loginRequestModel: LoginRequestModel) = firebaseAuth.signInWithEmailAndPassword(loginRequestModel.email, loginRequestModel.password)

    fun resetPassword(resetPasswordRequestModel: ResetPasswordRequestModel) = firebaseAuth.sendPasswordResetEmail(resetPasswordRequestModel.email)
}