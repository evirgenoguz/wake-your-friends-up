package com.jungle.wake_your_friends_up.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Burak Taşcı on 2.03.2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object UtilityModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()
}