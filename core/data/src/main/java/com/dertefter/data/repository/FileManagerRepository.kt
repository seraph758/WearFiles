package com.dertefter.data.repository

import java.io.File

interface FileManagerRepository {
    suspend fun getFiles(path: String): Result<List<File>>

    suspend fun rename(path: String, newName: String): Result<Boolean>

    suspend fun getParentFilePath(path: String): String?
    fun hasFileAccess(): Boolean

    fun hasImagesAccess(): Boolean

    fun hasVideosAccess(): Boolean

    fun hasAudioAccess(): Boolean

    fun getFileByPath(path: String): File?


    fun delete(path: String): Result<Boolean>
    fun createDirectory(path: String, name: String): Result<Boolean>

    fun getBasePath(): String

    fun getFileName(path: String): Result<String>

    fun canNavigateUpFrom(path: String): Boolean

    fun canBeRenamed(path: String): Boolean

    fun canBeDeleted(path: String): Boolean

    fun canCreateDirHere(path: String): Boolean

    fun isDirectory(path: String): Boolean


}