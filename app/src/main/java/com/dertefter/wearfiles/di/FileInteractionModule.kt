package com.dertefter.wearfiles.di

import android.content.Context
import com.dertefter.data.repository.FileInteractionHandler
import com.dertefter.wearfiles.data.FileInteractionHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileInteractionModule {

    @Provides
    @Singleton
    fun provideFileInteractionHandler(
        @ApplicationContext context: Context
    ): FileInteractionHandler = FileInteractionHandlerImpl(context)
}