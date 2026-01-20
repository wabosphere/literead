package com.literead.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.literead.data.model.Bookmark
import com.literead.data.model.Document
import com.literead.data.model.ReadingHistory

/**
 * Base de données Room - ultra-légère avec minification
 */
@Database(
    entities = [Document::class, Bookmark::class, ReadingHistory::class],
    version = 1,
    exportSchema = false
)
abstract class LiteReadDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun readingHistoryDao(): ReadingHistoryDao
}
