// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")

    }
}

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "7.4.2" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false

}