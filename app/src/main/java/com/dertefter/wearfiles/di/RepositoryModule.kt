package com.dertefter.wearfiles.di

import com.dertefter.data.repository.ClipboardRepository
import com.dertefter.data.repository.FileContentRepository
import com.dertefter.data.repository.FileManagerRepository
import com.dertefter.data.repository.PinnedRepository
import com.dertefter.wearfiles.data.FileManagerRepositoryImpl
import com.dertefter.data.repository.RemoteInteractionHandler
import com.dertefter.wearfiles.data.ClipboardRepositoryImpl
import com.dertefter.wearfiles.data.FileContentRepositoryImpl
import com.dertefter.wearfiles.data.PinnedRepositoryImpl
import com.dertefter.wearfiles.data.RemoteInteractionHandlerImpl
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
    @Singleton
    fun bindClipboardRepository(
        impl: ClipboardRepositoryImpl
    ): ClipboardRepository

    @Binds
    @Singleton
    fun bindFileContentRepository(
        impl: FileContentRepositoryImpl
    ): FileContentRepository

    @Binds
    fun bindRemoteRepository(
        impl: RemoteInteractionHandlerImpl
    ): RemoteInteractionHandler

    @Binds
    @Singleton
    fun bindPinnedRepository(
        impl: PinnedRepositoryImpl
    ): PinnedRepository
}