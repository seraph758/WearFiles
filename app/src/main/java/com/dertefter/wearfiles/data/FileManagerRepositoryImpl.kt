package com.dertefter.wearfiles.data

import android.os.Build
import android.os.Environment
import com.dertefter.data.repository.FileManagerRepository
import java.io.File
import javax.inject.Inject

class FileManagerRepositoryImpl @Inject constructor() : FileManagerRepository {


    override suspend fun getFiles(path: String): List<File> {
        val file = File(path)
        return if (file.exists() && file.isDirectory) {
            file.listFiles()?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getParentFilePath(path: String): String? {
        val file = File(path)
        return file.parent
    }

    override fun hasFileAccess(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            true
        }
    }

    override fun deleteFile(file: File): Boolean {
        return if (file.exists()) {
            if (file.isDirectory) {
                file.deleteRecursively()
            } else {
                file.delete()
            }
        } else {
            false
        }
    }

    override fun createDirectory(path: String, name: String): Boolean {
        val newDir = File(path, name)
        return if (!newDir.exists()) {
            newDir.mkdirs()
        } else {
            false
        }
    }

    override fun getBasePath(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }

    override fun canNavigateUpFrom(path: String): Boolean {

        val homePath = getBasePath()

        return path.startsWith(homePath) && path != homePath
    }


}