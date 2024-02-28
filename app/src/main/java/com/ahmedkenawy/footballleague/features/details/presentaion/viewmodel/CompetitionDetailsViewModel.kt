package com.ahmedkenawy.footballleague.features.details.presentaion.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.ahmedkenawy.footballleague.core.base.BaseCoroutineDispatchers
import com.ahmedkenawy.footballleague.core.base.BaseViewModel
import com.ahmedkenawy.footballleague.features.details.presentaion.event.CompetitionDetailsEvent
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.utils.Constants.Intent.COMPETITION_NAME
import com.ahmedkenawy.footballleague.utils.databinding.ObservableString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class responsible for managing competition details data and handling related business logic.
 *
 * @param savedStateHandle The SavedStateHandle provided by Hilt for accessing saved state data.
 * @param dispatchers The coroutine dispatchers used for performing background tasks.
 */
@HiltViewModel
class CompetitionDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    dispatchers: BaseCoroutineDispatchers
) : BaseViewModel<CompetitionDetailsEvent>(dispatchers) {

    /**
     * The competition details retrieved from saved state.
     */
    val competitionName: Competitions? = savedStateHandle.get<Competitions>(COMPETITION_NAME)

    /**
     * Observable string representing the competition title.
     */
    val title = ObservableString()

    /**
     * Observable string representing the competition area.
     */
    val area = ObservableString()

    /**
     * Observable string representing the competition emblem.
     */
    val emblem = ObservableString()

    /**
     * Method to load initial data when the ViewModel is initialized.
     */
    override fun loadInitialData() {
        loadCompetitionDetails()
    }

    /**
     * Method to load competition details and update observable fields.
     */
    private fun loadCompetitionDetails() {
        competitionName?.let {
            title.set(it.competitionName)
            area.set(it.areaName)
            emblem.set(it.competitionEmblem)
        }
    }
}