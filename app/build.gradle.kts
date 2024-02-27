plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.ahmedkenawy.footballleague"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ahmedkenawy.footballleague"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"ab46221ac9c24c60a993ed7128eecac6\"")
            buildConfigField("String", "BASE_URL", "\"https://api.football-data.org/v4/\"")
        }
        debug {
            buildConfigField("String", "API_KEY", "\"ab46221ac9c24c60a993ed7128eecac6\"")
            buildConfigField("String", "BASE_URL", "\"https://api.football-data.org/v4/\"")
        }

    }


    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0-beta01")
    implementation("androidx.fragment:fragment-ktx:1.7.0-alpha06")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // SplashScreen compat library
    implementation("androidx.core:core-splashscreen:1.0.1")


    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.44")

    // Coroutine
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    // ViewModels delegation extensions for activity (by activityViewModels())
    implementation("androidx.activity:activity-ktx:1.8.1")

    // Network
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")


    //Glide
    api("io.coil-kt:coil:2.6.0")


    //paging
    api("androidx.paging:paging-runtime-ktx:3.2.1")
    testImplementation("androidx.paging:paging-common-ktx:3.2.1")
    //ssp //sdp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    // SplashScreen compat library
    implementation("androidx.core:core-splashscreen:1.0.1")

    // room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")


    implementation ("com.github.pwittchen:reactivenetwork-rx2:3.0.2")




}