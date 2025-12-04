package com.dertefter.wearfiles.di

import android.content.Context
import androidx.wear.remote.interactions.RemoteActivityHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteActivityHelper(
        @ApplicationContext context: Context
    ): RemoteActivityHelper = RemoteActivityHelper(context)
}