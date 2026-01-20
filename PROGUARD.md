# ProGuard Configuration for LiteRead

## Optimization & Obfuscation Rules

# Enable optimizations
-optimizationpasses 5
-dontusemixedcaseclassnames

# Preserve line numbers for crash reports
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ============ Keep Application Classes ============

# Keep all public activities, services, broadcast receivers, content providers
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Fragment
-keep public class * extends androidx.fragment.app.Fragment

# Keep custom application classes
-keep public class com.literead.LiteReadApp
-keep public class com.literead.ui.activity.**
-keep public class com.literead.ui.fragment.**

# ============ Keep Model Classes ============

# Keep data models (used for serialization/database)
-keep class com.literead.data.model.** { *; }
-keepclassmembers class com.literead.data.model.** {
    <init>(...);
    public <fields>;
}

# ============ Room Database ============

-keep class androidx.room.** { *; }
-keepnames class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }

# ============ ViewModel & LiveData ============

-keepclasseswithmembernames class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keepclasseswithmembernames class * extends androidx.lifecycle.AndroidViewModel {
    <init>(...);
}

# ============ Kotlin ============

-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }
-keepnames class kotlin.** { *; }

# Kotlin coroutines
-keep class kotlinx.coroutines.** { *; }
-keepnames class kotlinx.coroutines.** { *; }

# Kotlin serialization
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ============ Koin Dependency Injection ============

-keep class org.koin.** { *; }
-keepnames class org.koin.** { *; }
-keep interface org.koin.** { *; }

# ============ Android Components ============

-keepclasseswithmembers class android.** {
    public <init>();
}

-keep class android.** {
    public static final int id;
}

# ============ Support Library ============

-keep class androidx.** { *; }
-keepnames class androidx.** { *; }
-keep interface androidx.** { *; }
-keepattributes *Annotation*

# ============ Material Design ============

-keep class com.google.android.material.** { *; }
-keepnames class com.google.android.material.** { *; }

# ============ PDF/EPUB Libraries ============

# PDFium
-keep class io.github.javacpp.** { *; }

# Readium
-keep class org.readium.** { *; }
-keepnames class org.readium.** { *; }
-keep interface org.readium.** { *; }

# ============ XML Parsing ============

-keep class org.xmlpull.** { *; }
-keep interface org.xmlpull.** { *; }
-keepnames class org.xmlpull.** { *; }

# ============ Logging ============

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** println(...);
}

# But keep warnings and errors
-assumenosideeffects class android.util.Log {
    public static *** w(...);
    public static *** e(...);
    public static *** wtf(...);
}

# ============ Enum Classes ============

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# ============ R Classes ============

-keepclassmembers class **.R$* {
    public static <fields>;
}

# ============ Native Methods ============

-keepclasseswithmembernames class * {
    native <methods>;
}

# ============ View Constructors ============

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# ============ Callbacks ============

-keepclasseswithmembers class * {
    *** *Callback(...);
}

# ============ Parcelable ============

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# ============ Serializable ============

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# ============ General Optimization ============

# Remove unused code
-dontshrink
-dontoptimize
-keepattributes *Annotation*

# ============ Testing ============

-dontwarn junit.**
-dontwarn org.junit.**
-dontwarn androidx.test.**

# ============ Reflection Safe ============

-keepclasseswithmembernames class * {
    public *** get*(...);
    public *** set*(...);
}

# ============ Remove Debugging ============

-stripnumbers class !com.literead.** {
    public *** *(...);
}

# ============ Verbose Output ============

# Uncomment for debugging:
# -printmapping build/outputs/mapping.txt
# -verbose
