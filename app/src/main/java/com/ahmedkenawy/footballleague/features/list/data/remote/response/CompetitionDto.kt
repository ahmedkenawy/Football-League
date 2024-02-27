package com.ahmedkenawy.footballleague.features.list.data.remote.response

data class CompetitionDto(
    val area: Area?,
    val code: String?,
    val currentSeason: CurrentSeason?,
    val emblem: String?,
    val id: Int,
    val lastUpdated: String?,
    val name: String?,
    val numberOfAvailableSeasons: Int?,
    val plan: String?,
    val type: String?
)