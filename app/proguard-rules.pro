# Proguard rules pour LiteRead - minimiser l'APK

# Garder les classes pour la sérialisation
-keep class com.literead.data.model.** { *; }
-keep class com.literead.data.db.** { *; }

# Room
-keep class androidx.room.** { *; }
-keepnames class androidx.room.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }

# Readium
-keep class org.readium.** { *; }
-keep interface org.readium.** { *; }

# Koin
-keep class org.koin.** { *; }

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
