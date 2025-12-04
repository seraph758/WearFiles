package com.dertefter.data.repository

import java.io.File

interface FileManagerRepository {
    suspend fun getFiles(path: String): List<File>
    fun hasFileAccess(): Boolean
    fun deleteFile(file: File): Boolean
    fun createDirectory(path: String, name: String): Boolean

    fun getBasePath(): String
}