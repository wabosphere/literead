package com.literead.data.repository

import androidx.lifecycle.LiveData
import com.literead.data.db.BookmarkDao
import com.literead.data.db.DocumentDao
import com.literead.data.db.ReadingHistoryDao
import com.literead.data.model.Bookmark
import com.literead.data.model.Document
import com.literead.data.model.ReadingHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository centralisé pour accès à la base de données
 */
class DocumentRepository(
    private val documentDao: DocumentDao,
    private val bookmarkDao: BookmarkDao,
    private val readingHistoryDao: ReadingHistoryDao
) {
    // ========== DOCUMENT OPERATIONS ==========

    fun getAllDocuments(): LiveData<List<Document>> = documentDao.getAllDocuments()

    fun getRecentDocuments(): LiveData<List<Document>> = documentDao.getRecentDocuments()

    suspend fun getDocumentById(id: Long): Document? =
        withContext(Dispatchers.IO) {
            documentDao.getDocumentById(id)
        }

    suspend fun addDocument(document: Document): Long =
        withContext(Dispatchers.IO) {
            documentDao.insert(document)
        }

    suspend fun updateDocument(document: Document) =
        withContext(Dispatchers.IO) {
            documentDao.update(document)
        }

    suspend fun deleteDocument(id: Long) =
        withContext(Dispatchers.IO) {
            documentDao.deleteById(id)
            bookmarkDao.deleteBookmarksForDocument(id)
            readingHistoryDao.deleteHistoryForDocument(id)
        }

    suspend fun searchDocuments(query: String): List<Document> =
        withContext(Dispatchers.IO) {
            documentDao.searchDocuments(query)
        }

    suspend fun updateReadingProgress(documentId: Long, page: Int) =
        withContext(Dispatchers.IO) {
            documentDao.updateReadingProgress(documentId, System.currentTimeMillis(), page)
        }

    // ========== BOOKMARK OPERATIONS ==========

    fun getBookmarks(documentId: Long): LiveData<List<Bookmark>> =
        bookmarkDao.getBookmarksForDocument(documentId)

    suspend fun addBookmark(documentId: Long, page: Int, note: String = ""): Long =
        withContext(Dispatchers.IO) {
            val bookmark = Bookmark(
                documentId = documentId,
                page = page,
                note = note
            )
            bookmarkDao.insert(bookmark)
        }

    suspend fun removeBookmark(documentId: Long, page: Int) =
        withContext(Dispatchers.IO) {
            val bookmark = bookmarkDao.getBookmarkAt(documentId, page)
            bookmark?.let { bookmarkDao.delete(it) }
        }

    suspend fun hasBookmark(documentId: Long, page: Int): Boolean =
        withContext(Dispatchers.IO) {
            bookmarkDao.getBookmarkAt(documentId, page) != null
        }

    // ========== READING HISTORY OPERATIONS ==========

    suspend fun saveReadingHistory(documentId: Long, page: Int, progress: Float) =
        withContext(Dispatchers.IO) {
            val history = ReadingHistory(
                documentId = documentId,
                page = page,
                progress = progress
            )
            readingHistoryDao.insert(history)
        }

    suspend fun getLastReadingPosition(documentId: Long): ReadingHistory? =
        withContext(Dispatchers.IO) {
            readingHistoryDao.getLatestHistory(documentId)
        }
}
