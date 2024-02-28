import com.ahmedkenawy.footballleague.features.list.data.CompetitionRepository
import com.ahmedkenawy.footballleague.features.list.data.CompetitionsMapper
import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
import com.ahmedkenawy.footballleague.features.list.data.remote.CompetitionsApi
import com.ahmedkenawy.footballleague.features.list.data.remote.response.CompetitionDto
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.network.ApiResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
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
    private lateinit var mockMapper: CompetitionsMapper
    private lateinit var repository: CompetitionRepository

    @Before
    fun setup() {
        mockApi = mock(CompetitionsApi::class.java)
        mockDatabase = mock(CompetitionsDatabase::class.java)
        mockMapper = CompetitionsMapper()

        repository = CompetitionRepository(mockApi, mockDatabase, mockMapper)
    }

    @Test
    fun testFetchCompetitions() = runBlockingTest {
        // Mock data
        val competitions = listOf(
            CompetitionDto(
                area = null,
                code = "",
                currentSeason = null,
                emblem = "",
                id = 123,
                lastUpdated = "-02-27",
                name = "",
                numberOfAvailableSeasons = 5,
                plan = "",
                type = ""
            )
        )
        val competitionsDto = CompetitionDto(
            area = null,
            code = "",
            currentSeason = null,
            emblem = "",
            id = 123,
            lastUpdated = "-02-27",
            name = "",
            numberOfAvailableSeasons = 5,
            plan = "",
            type = ""
        )
        val competitionsFromApi = ApiResponse(
            count = 0,
            filters = "",
            responseData = competitions.toMutableList()
        )
        val competition = Competitions(0, "", "", "")
        val mappedCompetitions = listOf(Competitions(0, "", "", ""))

        // Mock API response
        `when`(mockApi.fetchCompetitions()).thenReturn(competitionsFromApi)

        // Mock mapper
        `when`(mockMapper.map(competitionsDto)).thenReturn(competition)

        // Invoke fetchCompetitions
        val result = repository.fetchCompetitions()

        // Verify API and mapper interactions
        verify(mockApi).fetchCompetitions()
        verify(mockMapper).map(competitionsDto)

        // Verify result
        assertNotNull(result)
        assertEquals(mappedCompetitions, result)
    }
}
