package com.literead

import android.app.Application
import androidx.room.Room
import com.literead.data.db.LiteReadDatabase
import com.literead.data.repository.DocumentRepository
import com.literead.utils.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Application class avec injection de dépendances Koin
 */
class LiteReadApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialiser Koin
        startKoin {
            androidContext(this@LiteReadApp)
            modules(appModule)
        }
    }
}

private val appModule = module {
    // Database
    single {
        Room.databaseBuilder(
            get(),
            LiteReadDatabase::class.java,
            "literead_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // DAOs
    single { get<LiteReadDatabase>().documentDao() }
    single { get<LiteReadDatabase>().bookmarkDao() }
    single { get<LiteReadDatabase>().readingHistoryDao() }

    // Repository
    single {
        DocumentRepository(
            documentDao = get(),
            bookmarkDao = get(),
            readingHistoryDao = get()
        )
    }

    // Preferences
    single { PreferencesManager(get()) }
}
