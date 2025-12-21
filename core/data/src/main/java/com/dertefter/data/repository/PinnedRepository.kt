package com.dertefter.data.repository

import com.dertefter.data.model.PinnedItem
import kotlinx.coroutines.flow.Flow

interface PinnedRepository {

    suspend fun getPinnedItems(): List<PinnedItem>

    suspend fun pinItem(item: PinnedItem)

    suspend fun unpinItem(item: PinnedItem)

    suspend fun isPinned(path: String): Boolean

    fun getPinnedFlow(): Flow<List<PinnedItem>>



}