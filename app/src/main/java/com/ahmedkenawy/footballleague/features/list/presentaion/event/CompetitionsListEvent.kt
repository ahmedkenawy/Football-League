package com.ahmedkenawy.footballleague.features.list.presentaion.event

import com.ahmedkenawy.footballleague.features.list.domain.Competitions

sealed class CompetitionsListEvent {
    data class FetchCompetition(val competitions: List<Competitions?>) : CompetitionsListEvent()
    object ShowNoInternet : CompetitionsListEvent()
}
