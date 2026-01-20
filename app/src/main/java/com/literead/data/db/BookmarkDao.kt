package com.literead.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.literead.data.model.Bookmark

/**
 * DAO pour les signets
 */
@Dao
interface BookmarkDao {
    @Insert
    suspend fun insert(bookmark: Bookmark): Long

    @Delete
    suspend fun delete(bookmark: Bookmark)

    @Query("SELECT * FROM bookmarks WHERE documentId = :documentId ORDER BY page ASC")
    fun getBookmarksForDocument(documentId: Long): LiveData<List<Bookmark>>

    @Query("SELECT * FROM bookmarks WHERE documentId = :documentId AND page = :page LIMIT 1")
    suspend fun getBookmarkAt(documentId: Long, page: Int): Bookmark?

    @Query("DELETE FROM bookmarks WHERE documentId = :documentId")
    suspend fun deleteBookmarksForDocument(documentId: Long)
}
