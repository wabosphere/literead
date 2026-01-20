package com.literead.reader

import android.content.Context
import com.literead.data.model.Document
import java.io.File

/**
 * Interface abstraite pour les lecteurs de documents
 */
interface DocumentReader {
    suspend fun openDocument(context: Context, path: String): Boolean
    suspend fun getTotalPages(): Int
    suspend fun getPageBitmap(pageNumber: Int)
    suspend fun searchText(query: String): List<SearchResult>
    suspend fun closeDocument()
    fun getDocumentInfo(): DocumentInfo
}

data class DocumentInfo(
    val title: String,
    val author: String? = null,
    val totalPages: Int,
    val creator: String? = null,
    val creationDate: String? = null
)

data class SearchResult(
    val page: Int,
    val text: String,
    val position: Int
)

/**
 * Factory pour créer les lecteurs appropriés
 */
object DocumentReaderFactory {
    fun createReader(mimeType: String): DocumentReader {
        return when {
            mimeType.contains("pdf", ignoreCase = true) -> PDFReader()
            mimeType.contains("epub", ignoreCase = true) -> EPUBReader()
            mimeType.contains("text", ignoreCase = true) -> TextReader()
            mimeType.contains("mobi", ignoreCase = true) -> MOBIReader()
            else -> throw IllegalArgumentException("Unsupported format: $mimeType")
        }
    }
}
