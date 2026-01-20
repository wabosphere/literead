package com.literead.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File

/**
 * Utilitaires pour la gestion des fichiers
 */
object FileUtils {
    fun getFileName(context: Context, uri: Uri): String {
        return when {
            uri.scheme == "content" -> {
                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    cursor.getString(nameIndex)
                } ?: uri.lastPathSegment ?: "document"
            }
            uri.scheme == "file" -> {
                File(uri.path ?: return "document").name
            }
            else -> uri.lastPathSegment ?: "document"
        }
    }

    fun getFileSize(context: Context, uri: Uri): Long {
        return when {
            uri.scheme == "content" -> {
                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                    cursor.moveToFirst()
                    cursor.getLong(sizeIndex)
                } ?: 0
            }
            uri.scheme == "file" -> {
                File(uri.path ?: return 0).length()
            }
            else -> 0
        }
    }

    fun getMimeType(fileName: String): String {
        return when {
            fileName.endsWith(".pdf", ignoreCase = true) -> "application/pdf"
            fileName.endsWith(".epub", ignoreCase = true) -> "application/epub+zip"
            fileName.endsWith(".txt", ignoreCase = true) -> "text/plain"
            fileName.endsWith(".mobi", ignoreCase = true) -> "application/x-mobipocket-ebook"
            else -> "application/octet-stream"
        }
    }

    fun isDocumentFile(fileName: String): Boolean {
        val extension = fileName.substringAfterLast(".", "").lowercase()
        return extension in listOf("pdf", "epub", "txt", "mobi")
    }

    fun formatFileSize(bytes: Long): String {
        val units = arrayOf("B", "KB", "MB", "GB")
        var size = bytes.toDouble()
        var unitIndex = 0

        while (size >= 1024 && unitIndex < units.size - 1) {
            size /= 1024
            unitIndex++
        }

        return String.format("%.2f %s", size, units[unitIndex])
    }
}
