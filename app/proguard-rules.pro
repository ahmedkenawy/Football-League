# Keep Room annotations and database classes
-keep class androidx.room.** { *; }

# Keep Parcelable implementation methods
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep ViewModel and LiveData classes and methods
-keep class androidx.lifecycle.ViewModel
-keep class androidx.lifecycle.ViewModelProvider$Factory

# Keep Hilt-related classes and methods
-keep class dagger.hilt.** { *; }
-keep class dagger.hilt.android.** { *; }
-keep class javax.inject.** { *; }
-keep class kotlin.coroutines.Continuation { *; }

# Keep Retrofit and OkHttp classes and methods
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class com.squareup.okhttp3.** { *; }

# Keep Coil classes and methods
-keep class coil.** { *; }

# Keep Paging library classes and methods
-keep class androidx.paging.** { *; }

# Keep Glide classes and methods
-keep class com.bumptech.glide.** { *; }

# Keep ReactiveNetwork classes and methods
-keep class com.github.pwittchen.reactivenetwork.** { *; }

# Keep custom Parcelable classes and their fields
-keepclassmembers class com.ahmedkenawy.footballleague.features.list.domain.Competitions { *; }

# Keep custom Application class and its methods
-keep class com.ahmedkenawy.footballleague.MyApplication { *; }

# Keep Gson classes and methods
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# Keep Retrofit interfaces and their methods
-keep,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Keep enum classes along with their fields and methods
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep entry point classes specified in AndroidManifest.xml
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference

# Keep JNI references
-keepclasseswithmembernames class * {
    native <methods>;
}

# Suppress warnings for missing translations
-dontwarn android.support.v7.internal.**
-dontwarn android.support.v7.view.menu.**
-dontwarn android.support.design.internal.**
-dontwarn android.support.design.widget.**


# Keep all members of specific classes
-keepclassmembers class com.ahmedkenawy.footballleague.features.list.presentaion.** { *; }
-keepclassmembers class com.ahmedkenawy.footballleague.features.list.domain.** { *; }
-keepclassmembers class com.ahmedkenawy.footballleague.core.** { *; }
-keepclassmembers class com.ahmedkenawy.footballleague.network.** { *; }

# Keep custom proguard rules
-include proguard-custom-rules.pro

