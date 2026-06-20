package com.ahmedkenawy.footballleague.features.list.data

import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.network.State

interface ICompetitionRepository {
    suspend fun fetchCompetitions(): State<MutableList<Competitions>>
    suspend fun fetchCompetitionsFromLocalDatabase(): List<Competitions>
    suspend fun insertCompetitions(competitions: List<Competitions>)
}
