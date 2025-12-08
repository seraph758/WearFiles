package com.dertefter.wearfiles.data

import android.os.Build
import android.os.Environment
import com.dertefter.data.repository.FileManagerRepository
import kotlinx.coroutines.delay
import java.io.File
import javax.inject.Inject

class FileManagerRepositoryImpl @Inject constructor() : FileManagerRepository {

    override suspend fun getFiles(path: String): Result<List<File>> {
        return runCatching {
            val file = File(path)
            if (!file.exists()) throw IllegalArgumentException("Path does not exist: $path")
            if (!file.isDirectory) throw IllegalArgumentException("Path is not a directory: $path")

            file.listFiles()?.toList()
                ?: throw IllegalStateException("Cannot list files for: $path")
        }
    }

    override fun getFileName(path: String): Result<String> {
        return runCatching {
            val file = File(path)
            file.name
        }
    }

    override suspend fun rename(path: String, newName: String): Result<Boolean> {
        return runCatching {
            val file = File(path)

            if (!file.exists()) throw IllegalArgumentException("File does not exist: $path")
            if (newName.isBlank()) throw IllegalArgumentException("New name cannot be empty")

            val newFile = File(file.parent, newName)

            val success = file.renameTo(newFile)
            if (!success) throw IllegalStateException("Failed to rename $path to $newName")

            success
        }
    }


    override suspend fun getParentFilePath(path: String): String? {

        try{
            val file = File(path)
            return file.parent
        }catch (e: Exception){
            return null
        }
    }

    override fun hasFileAccess(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else true
    }

    override fun deleteFile(file: File): Result<Boolean> {
        return runCatching {
            if (!file.exists()) throw IllegalArgumentException("File does not exist: ${file.path}")

            if (file.isDirectory) {
                file.deleteRecursively()
            } else {
                file.delete()
            }
        }
    }

    override fun createDirectory(path: String, name: String): Result<Boolean> {
        return runCatching {
            val newDir = File(path, name)

            if (newDir.exists()) throw IllegalStateException("Directory already exists: ${newDir.path}")

            newDir.mkdirs()
        }
    }

    override fun getBasePath(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }




    override fun canNavigateUpFrom(path: String): Boolean {
        val homePath = getBasePath()
        return path.startsWith(homePath) && path != homePath
    }

    override fun canBeRenamed(path: String): Boolean {

        try {
            val file = File(path)

            if (!file.exists()) return false

            val parent = file.parentFile ?: return false

            if (!parent.canWrite()) return false

            return true
        } catch (e: Exception){
            return false
        }


    }

    override fun canBeDeleted(path: String): Boolean {
        return try {
            val file = File(path)

            if (!file.exists()) return false

            val parent = file.parentFile ?: return false
            if (!parent.canWrite()) return false

            true
        } catch (e: Exception) {
            false
        }
    }

    override fun canCreateDirHere(path: String): Boolean {
        return try {
            val dir = File(path)

            if (!dir.exists()) return false
            if (!dir.isDirectory) return false
            if (!dir.canWrite()) return false

            true
        } catch (e: Exception) {
            false
        }
    }

    override fun isDirectory(path: String): Boolean {
        return try{
            val file = File(path)
            file.isDirectory
        }
        catch (e: Exception){
            false
        }
    }


}
