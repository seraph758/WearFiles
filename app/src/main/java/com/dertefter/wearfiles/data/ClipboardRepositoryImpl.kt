package com.dertefter.wearfiles.data

import com.dertefter.data.repository.ClipboardOperation
import com.dertefter.data.repository.ClipboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import javax.inject.Inject

class ClipboardRepositoryImpl @Inject constructor() : ClipboardRepository {

    private var clipboard: String? = null

    override var operation: ClipboardOperation? = null

    override fun cut(path: String) {
        operation = ClipboardOperation.CUT
        clipboard = path
    }

    override fun copy(path: String) {
        operation = ClipboardOperation.COPY
        clipboard = path
    }

    override fun cancel() {
        operation = null
        clipboard = null
    }

    override suspend fun insertTo(path: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val srcPath = clipboard ?: return@withContext false

                val srcFile = File(srcPath)
                val destDir = File(path)

                if (!srcFile.exists()) return@withContext false
                if (!destDir.exists() || !destDir.isDirectory) return@withContext false

                val target = findUniqueFile(destDir, srcFile.name, srcFile.isDirectory)

                if (operation == ClipboardOperation.COPY) {
                    if (srcFile.isDirectory) {
                        copyDirectoryRecursively(srcFile, target)
                    } else {
                        copyFile(srcFile, target)
                    }
                } else if (operation == ClipboardOperation.CUT) {
                    try {
                        srcFile.renameTo(target)
                    } catch (e: Exception) {
                        return@withContext false
                    }
                } else {
                    return@withContext false
                }

                clipboard = null
                operation = null

                true
            } catch (e: Exception) {
                false
            }
        }
    }

    private fun findUniqueFile(destDir: File, name: String, isDirectory: Boolean): File {
        var newFile = File(destDir, name)
        if (!newFile.exists()) {
            return newFile
        }

        val baseName: String
        val extension: String

        if (isDirectory) {
            baseName = name
            extension = ""
        } else {
            val dotIndex = name.lastIndexOf('.')
            if (dotIndex > 0 && dotIndex < name.length - 1) { // file.ext
                baseName = name.substring(0, dotIndex)
                extension = name.substring(dotIndex) // .ext
            } else { // no extension, or .file
                baseName = name
                extension = ""
            }
        }

        var counter = 1
        while (newFile.exists()) {
            val newName = "$baseName ($counter)$extension"
            newFile = File(destDir, newName)
            counter++
        }
        return newFile
    }

    private fun copyFile(src: File, dest: File) {
        dest.parentFile?.mkdirs()
        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    private fun copyDirectoryRecursively(src: File, dest: File) {
        if (!dest.exists()) dest.mkdirs()

        val children = src.listFiles() ?: return
        for (child in children) {
            val targetChild = File(dest, child.name)
            if (child.isDirectory) {
                copyDirectoryRecursively(child, targetChild)
            } else {
                copyFile(child, targetChild)
            }
        }
    }

}
