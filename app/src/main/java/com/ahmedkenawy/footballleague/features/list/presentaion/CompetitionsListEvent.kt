package com.ahmedkenawy.footballleague.features.list.presentaion

import com.ahmedkenawy.footballleague.features.list.domain.Competitions

/**
 * Sealed class representing events related to competitions list in the UI.
 */
sealed class CompetitionsListEvent {

    /**
     * Event representing the action of fetching competitions from a data source.
     *
     * @param competitions The list of competitions fetched from the data source. Can be null if
     *                      the fetch operation failed or returned an empty list.
     */
    data class FetchCompetition(val competitions: List<Competitions?>) : CompetitionsListEvent()
}
