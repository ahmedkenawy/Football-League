package com.ahmedkenawy.footballleague.features.list.presentaion.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.footballleague.core.base.BaseCoroutineDispatchers
import com.ahmedkenawy.footballleague.core.base.BaseViewModel
import com.ahmedkenawy.footballleague.features.list.data.CompetitionRepository
import com.ahmedkenawy.footballleague.features.list.presentaion.event.CompetitionsListEvent
import com.ahmedkenawy.footballleague.network.process
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * ViewModel class responsible for managing competitions list data and handling related business logic.
 *
 * @param competitionRepository The repository responsible for fetching competitions data from
 *                               remote or local data sources.
 * @param context The application context provided by Hilt for accessing system resources.
 * @param dispatchers The coroutine dispatchers used for performing background tasks.
 */
@HiltViewModel
class CompetitionsListViewModel @Inject constructor(
    private val competitionRepository: CompetitionRepository,
    @ApplicationContext val context: Context,
    dispatchers: BaseCoroutineDispatchers
) : BaseViewModel<CompetitionsListEvent>(dispatchers) {

    /**
     * LiveData object for monitoring network connectivity changes.
     */
    private val connectivityLiveData = ReactiveNetwork.observeNetworkConnectivity(context)

    /**
     * Method to load initial data when the ViewModel is initialized.
     */
    override fun loadInitialData() {
        fetchCompetitions()
    }

    /**
     * Method to fetch competitions data based on network connectivity status.
     */
    @SuppressLint("CheckResult")
    private fun fetchCompetitions() {
        connectivityLiveData.subscribe { connectivity ->
            val isConnected = connectivity.available()
            if (isConnected) {
                fetchDataFromRemote()
            } else {
                fetchDataFromLocal()
            }
        }
    }

    /**
     * Method to fetch competitions data from the local database.
     */
    private fun fetchDataFromLocal() {
        viewModelScope.launch {
            val localCompetitions = competitionRepository.fetchCompetitionsFromLocalDatabase()
            if (localCompetitions.isNotEmpty()) {
                // Data available in local database
                pushSingle(CompetitionsListEvent.FetchCompetition(localCompetitions))
            } else {
                // No data available in local database
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Method to fetch competitions data from the remote data source.
     */
    private fun fetchDataFromRemote() {
        viewModelScope.launch {
            competitionRepository.fetchCompetitions()
                .process {
                    // Handle successful data retrieval from remote source
                    pushSingle(CompetitionsListEvent.FetchCompetition(it))
                    // Insert fetched data into the local database
                    competitionRepository.insertCompetitions(it)
                }
        }
    }
}
