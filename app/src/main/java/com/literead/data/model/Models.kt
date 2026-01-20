package com.literead.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Modèle représentant un document (PDF, EPUB, TXT, MOBI)
 */
@Serializable
@Entity(tableName = "documents")
data class Document(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val filePath: String,
    val fileSize: Long,
    val mimeType: String,
    val dateAdded: Long = System.currentTimeMillis(),
    val lastReadTime: Long = 0,
    val lastReadPage: Int = 0,
    val totalPages: Int = 0,
    val coverPath: String? = null
)

/**
 * Modèle pour les signets
 */
@Serializable
@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val documentId: Long,
    val page: Int,
    val position: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val note: String = ""
)

/**
 * Modèle pour l'historique de lecture
 */
@Serializable
@Entity(tableName = "reading_history")
data class ReadingHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val documentId: Long,
    val page: Int,
    val progress: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Modèle pour les paramètres de l'utilisateur
 */
@Serializable
data class UserPreferences(
    val theme: String = "light", // light, dark, sepia, amoled
    val fontSize: Int = 14, // px
    val fontFamily: String = "sans-serif",
    val readingMode: String = "page", // page, scroll
    val brightness: Float = 1f, // 0.1 to 1.0
    val lineHeight: Float = 1.5f
)
