package com.ahmedkenawy.footballleague.features.list.data.remote.response

data class CurrentSeason(
    val currentMatchday: Int?,
    val endDate: String?,
    val id: Int?,
    val startDate: String?,
    val winner: Winner?
)