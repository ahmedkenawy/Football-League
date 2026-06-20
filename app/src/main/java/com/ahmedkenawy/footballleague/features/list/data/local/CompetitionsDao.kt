package com.ahmedkenawy.footballleague.features.list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompetitionsDao {

    @Query("SELECT * FROM competitions")
    suspend fun getAllItems(): List<CompetitionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitions(competitions: List<CompetitionEntity>)
}
