package com.literead.data.db

import androidx.room.*
import com.literead.data.model.ReadingHistory

/**
 * DAO pour l'historique de lecture
 */
@Dao
interface ReadingHistoryDao {
    @Insert
    suspend fun insert(history: ReadingHistory): Long

    @Update
    suspend fun update(history: ReadingHistory)

    @Query("SELECT * FROM reading_history WHERE documentId = :documentId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestHistory(documentId: Long): ReadingHistory?

    @Query("DELETE FROM reading_history WHERE documentId = :documentId")
    suspend fun deleteHistoryForDocument(documentId: Long)

    @Query("DELETE FROM reading_history WHERE timestamp < :olderThan")
    suspend fun deleteOldHistory(olderThan: Long)
}
