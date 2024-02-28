package com.ahmedkenawy.footballleague

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDao
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CompetitionsDaoTest {

    private lateinit var database: CompetitionsDatabase
    private lateinit var dao: CompetitionsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CompetitionsDatabase::class.java
        ).build()
        dao = database.competitionsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `test insert and retrieve competitions`() = runBlocking {
        // Create a test competition
        val competition =
            Competitions(id = 0, areaName = "", competitionName = "", competitionEmblem = "")

        // Insert the competition into the database
        dao.insertCompetitions(listOf(competition))

        // Retrieve all competitions from the database
        val competitionsFromDb = dao.getAllItems()

        // Verify that the retrieved competitions contain the inserted competition
        assertEquals(1, competitionsFromDb.size)
        assertEquals(competition, competitionsFromDb.first())
    }
}
