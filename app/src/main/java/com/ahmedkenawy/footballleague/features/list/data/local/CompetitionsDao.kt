package com.ahmedkenawy.footballleague.features.list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedkenawy.footballleague.features.list.domain.Competitions

/**
 * Data Access Object (DAO) interface for accessing competitions data in the local database.
 */
@Dao
interface CompetitionsDao {

    /**
     * Method to retrieve all competitions from the local database.
     *
     * @return A list of competitions retrieved from the local database.
     */
    @Query("SELECT * FROM competitions")
    suspend fun getAllItems(): List<Competitions>

    /**
     * Method to insert competitions into the local database.
     *
     * @param competitions The list of competitions to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitions(competitions: List<Competitions>)
}
