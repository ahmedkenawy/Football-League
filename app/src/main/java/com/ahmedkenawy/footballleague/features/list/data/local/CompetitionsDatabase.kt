package com.ahmedkenawy.footballleague.features.list.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CompetitionEntity::class], version = 1)
abstract class CompetitionsDatabase : RoomDatabase() {
    abstract fun competitionsDao(): CompetitionsDao
}
