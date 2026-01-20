package com.literead.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.literead.data.model.Document

/**
 * DAO pour les documents
 */
@Dao
interface DocumentDao {
    @Insert
    suspend fun insert(document: Document): Long

    @Update
    suspend fun update(document: Document)

    @Delete
    suspend fun delete(document: Document)

    @Query("SELECT * FROM documents WHERE id = :id")
    suspend fun getDocumentById(id: Long): Document?

    @Query("SELECT * FROM documents ORDER BY dateAdded DESC")
    fun getAllDocuments(): LiveData<List<Document>>

    @Query("SELECT * FROM documents ORDER BY lastReadTime DESC LIMIT 10")
    fun getRecentDocuments(): LiveData<List<Document>>

    @Query("SELECT * FROM documents WHERE title LIKE '%' || :query || '%'")
    suspend fun searchDocuments(query: String): List<Document>

    @Query("DELETE FROM documents WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE documents SET lastReadTime = :time, lastReadPage = :page WHERE id = :id")
    suspend fun updateReadingProgress(id: Long, time: Long, page: Int)
}
