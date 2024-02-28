package com.ahmedkenawy.footballleague.di.modules.local

import android.content.Context
import androidx.room.Room
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CompetitionsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CompetitionsDatabase::class.java,
            "database"
        ).build()
    }
}