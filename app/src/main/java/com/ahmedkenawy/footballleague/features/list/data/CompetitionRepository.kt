package com.ahmedkenawy.footballleague.features.list.data

import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
import com.ahmedkenawy.footballleague.features.list.data.remote.CompetitionsApi
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.network.NetworkRouter
import com.ahmedkenawy.footballleague.network.mapList
import javax.inject.Inject

/**
 * Repository class responsible for handling competitions data.
 *
 * @param api The API service interface for fetching competitions data from the remote server.
 * @param competitionsDatabase The local database instance for accessing competitions data stored locally.
 * @param competitionsMapper The mapper class responsible for mapping competitions data between different formats.
 */
open class CompetitionRepository @Inject constructor(
    private val api: CompetitionsApi,
    private val competitionsDatabase: CompetitionsDatabase,
    private val competitionsMapper: CompetitionsMapper
) {

    /**
     * Method to fetch competitions data from the remote server.
     *
     * @return A list of competitions fetched from the remote server.
     */
    suspend fun fetchCompetitions() =
        NetworkRouter.invokeCall { api.fetchCompetitions() }
            .mapList(competitionsMapper)

    /**
     * Method to fetch competitions data from the local database.
     *
     * @return A list of competitions fetched from the local database.
     */
    suspend fun fetchCompetitionsFromLocalDatabase(): List<Competitions> =
        competitionsDatabase.competitionsDao().getAllItems()

    /**
     * Method to insert competitions data into the local database.
     *
     * @param competitions The list of competitions to be inserted into the local database.
     */
    suspend fun insertCompetitions(competitions: List<Competitions>) =
        competitionsDatabase.competitionsDao().insertCompetitions(competitions)
}

