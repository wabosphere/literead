# Proguard rules pour LiteRead - minimiser l'APK

-ignorewarnings

# Fix R8 XmlPull conflicts
-dontwarn org.xmlpull.v1.**
-dontwarn org.xmlpull.mxp1.**
-dontwarn org.xmlpull.mxp1_serializer.**
-keep class org.xmlpull.v1.** { *; }
-keep class org.xmlpull.mxp1.** { *; }
-keep class org.xmlpull.mxp1_serializer.** { *; }
-keep class org.xmlpull.mxp1.MXParser { *; }
-keep class org.xmlpull.mxp1_serializer.MXSerializer { *; }
-keep class android.content.res.XmlResourceParser { *; }

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

# Android XML
-keep class android.content.res.** { *; }

# Serialization
-keep class org.ogce.xpp3.** { *; }

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
