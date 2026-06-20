package com.ahmedkenawy.footballleague

import com.ahmedkenawy.footballleague.features.list.data.CompetitionRepository
import com.ahmedkenawy.footballleague.features.list.data.CompetitionsMapper
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionEntity
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDao
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
import com.ahmedkenawy.footballleague.features.list.data.remote.CompetitionsApi
import com.ahmedkenawy.footballleague.features.list.data.remote.response.CompetitionDto
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.network.ApiResponse
import com.ahmedkenawy.footballleague.network.State
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class CompetitionsRepositoryTest {

    private lateinit var mockApi: CompetitionsApi
    private lateinit var mockDatabase: CompetitionsDatabase
    private lateinit var mockDao: CompetitionsDao
    private lateinit var mockMapper: CompetitionsMapper
    private lateinit var repository: CompetitionRepository

    @Before
    fun setup() {
        mockApi = mock(CompetitionsApi::class.java)
        mockDatabase = mock(CompetitionsDatabase::class.java)
        mockDao = mock(CompetitionsDao::class.java)
        mockMapper = CompetitionsMapper()

        `when`(mockDatabase.competitionsDao()).thenReturn(mockDao)

        repository = CompetitionRepository(mockApi, mockDatabase, mockMapper)
    }

    @Test
    fun testFetchCompetitions() = runBlockingTest {
        val dto = CompetitionDto(
            area = null, code = "", currentSeason = null, emblem = "",
            id = 123, lastUpdated = "", name = "Premier League",
            numberOfAvailableSeasons = 5, plan = "", type = ""
        )
        val apiResponse = ApiResponse(count = 1, filters = "", responseData = mutableListOf(dto))

        `when`(mockApi.fetchCompetitions()).thenReturn(apiResponse)

        val result = repository.fetchCompetitions()

        verify(mockApi).fetchCompetitions()
        assertNotNull(result)
        assertTrue(result is State.Success)
        val data = (result as State.Success).data
        assertEquals(1, data.size)
        assertEquals(123, data.first().id)
        assertEquals("Premier League", data.first().competitionName)
    }

    @Test
    fun testFetchCompetitionsFromLocalDatabase() = runBlockingTest {
        val entities = listOf(CompetitionEntity(1, "England", "Premier League", "url"))
        `when`(mockDao.getAllItems()).thenReturn(entities)

        val result = repository.fetchCompetitionsFromLocalDatabase()

        assertEquals(1, result.size)
        assertEquals(Competitions(1, "England", "Premier League", "url"), result.first())
    }
}
