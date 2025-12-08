package com.dertefter.data.repository

import java.io.File

interface FileManagerRepository {
    suspend fun getFiles(path: String): Result<List<File>>

    suspend fun getParentFilePath(path: String): String?
    fun hasFileAccess(): Boolean
    fun deleteFile(file: File): Result<Boolean>
    fun createDirectory(path: String, name: String): Result<Boolean>

    fun getBasePath(): String

    fun getFileName(path: String): Result<String>

    fun canNavigateUpFrom(path: String): Boolean

    fun canRename(path: String): Boolean


}