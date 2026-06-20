package com.ahmedkenawy.footballleague.features.list.data

import com.ahmedkenawy.footballleague.features.list.data.local.CompetitionsDatabase
import com.ahmedkenawy.footballleague.features.list.data.local.toDomain
import com.ahmedkenawy.footballleague.features.list.data.local.toEntity
import com.ahmedkenawy.footballleague.features.list.data.remote.CompetitionsApi
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.network.NetworkRouter
import com.ahmedkenawy.footballleague.network.State
import com.ahmedkenawy.footballleague.network.mapList
import javax.inject.Inject

class CompetitionRepository @Inject constructor(
    private val api: CompetitionsApi,
    private val competitionsDatabase: CompetitionsDatabase,
    private val competitionsMapper: CompetitionsMapper
) : ICompetitionRepository {

    override suspend fun fetchCompetitions(): State<MutableList<Competitions>> =
        NetworkRouter.invokeCall { api.fetchCompetitions() }.mapList(competitionsMapper)

    override suspend fun fetchCompetitionsFromLocalDatabase(): List<Competitions> =
        competitionsDatabase.competitionsDao().getAllItems().map { it.toDomain() }

    override suspend fun insertCompetitions(competitions: List<Competitions>) =
        competitionsDatabase.competitionsDao().insertCompetitions(competitions.map { it.toEntity() })
}
