package com.jungle.wake_your_friends_up.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.jungle.wake_your_friends_up.data.model.request.UserRequestModel
import javax.inject.Inject

/**
 * Created by Oguz Evirgen on 26.03.2023.
 */
class AuthRepository @Inject constructor (
    private val firebaseAuth: FirebaseAuth,

) {

    //Firebase register method that we use at viewmodel
    fun register(userRequestModel: UserRequestModel) = firebaseAuth.createUserWithEmailAndPassword(userRequestModel.email, userRequestModel.password)



}