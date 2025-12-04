package com.dertefter.wearfiles.di

import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.wearfiles.FileManagerRepositoryImpl
import com.dertefter.data.repository.RemoteInteractionRepository
import com.dertefter.wearfiles.RemoteInteractionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindFileManagerRepository(
        impl: FileManagerRepositoryImpl
    ): FileManagerRepository

    @Binds
    fun bindRemoteRepository(
        impl: RemoteInteractionRepositoryImpl
    ): RemoteInteractionRepository
}