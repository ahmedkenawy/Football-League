package com.ahmedkenawy.footballleague.features.list.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedkenawy.footballleague.features.list.domain.Competitions

/**
 * Room database class for managing competitions data.
 */
@Database(entities = [Competitions::class], version = 1)
abstract class CompetitionsDatabase : RoomDatabase() {

    /**
     * Abstract method to retrieve the CompetitionsDao instance for accessing competitions data.
     *
     * @return The CompetitionsDao instance.
     */
    abstract fun competitionsDao(): CompetitionsDao
}