package com.jungle.wake_your_friends_up.di

import com.google.firebase.auth.FirebaseAuth
import com.jungle.wake_your_friends_up.data.NetworkManager
import com.jungle.wake_your_friends_up.data.api.SampleApi
import com.jungle.wake_your_friends_up.data.repository.AuthRepository
import com.jungle.wake_your_friends_up.data.repository.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Burak Taşcı on 21.02.2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {


    @Provides
    @ViewModelScoped
    fun providesSampleRepository(
        apiService: SampleApi,
        networkManager: NetworkManager
    ): SampleRepository =
        SampleRepository(apiService, networkManager)


    @Provides
    @ViewModelScoped
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository = AuthRepository(firebaseAuth)
}