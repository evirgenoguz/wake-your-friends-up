package com.jungle.wake_your_friends_up.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.jungle.wake_your_friends_up.data.model.request.UserRequestModel
import javax.inject.Inject

/**
 * Created by Oguz Evirgen on 26.03.2023.
 */

class AuthRepository (
    private val firebaseAuth: FirebaseAuth,
) {
    fun register(userRequestModel: UserRequestModel) = firebaseAuth.createUserWithEmailAndPassword(userRequestModel.email, userRequestModel.password)

    fun login(email: String, password: String) = firebaseAuth.signInWithEmailAndPassword(email, password)

    fun resetPassword(email: String) = firebaseAuth.sendPasswordResetEmail(email)
}