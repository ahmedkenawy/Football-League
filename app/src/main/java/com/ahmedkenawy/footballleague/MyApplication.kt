package com.ahmedkenawy.footballleague

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom Application class for the application.
 */
@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        /**
         * Static reference to the application instance.
         */
        lateinit var instance: MyApplication
            private set
    }

    /**
     * Called when the application is starting.
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}