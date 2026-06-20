package com.ahmedkenawy.footballleague

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionEntity
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDao
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
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
        val entity = CompetitionEntity(id = 1, areaName = "", competitionName = "", competitionEmblem = "")

        dao.insertCompetitions(listOf(entity))

        val result = dao.getAllItems()

        assertEquals(1, result.size)
        assertEquals(entity, result.first())
    }
}
