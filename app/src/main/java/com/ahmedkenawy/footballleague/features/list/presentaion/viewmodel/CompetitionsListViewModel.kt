package com.ahmedkenawy.footballleague.features.list.presentaion.viewmodel

import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.footballleague.core.base.BaseCoroutineDispatchers
import com.ahmedkenawy.footballleague.core.base.BaseViewModel
import com.ahmedkenawy.footballleague.features.list.data.ICompetitionRepository
import com.ahmedkenawy.footballleague.features.list.presentaion.event.CompetitionsListEvent
import com.ahmedkenawy.footballleague.network.process
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionsListViewModel @Inject constructor(
    private val competitionRepository: ICompetitionRepository,
    @ApplicationContext val context: Context,
    dispatchers: BaseCoroutineDispatchers
) : BaseViewModel<CompetitionsListEvent>(dispatchers) {

    private var networkDisposable: Disposable? = null

    override fun loadInitialData() {
        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(context)
            .subscribe { connectivity ->
                if (connectivity.available()) fetchDataFromRemote()
                else fetchDataFromLocal()
            }
    }

    private fun fetchDataFromLocal() {
        viewModelScope.launch {
            val localCompetitions = competitionRepository.fetchCompetitionsFromLocalDatabase()
            if (localCompetitions.isNotEmpty()) {
                pushSingle(CompetitionsListEvent.FetchCompetition(localCompetitions))
            } else {
                pushSingle(CompetitionsListEvent.ShowNoInternet)
            }
        }
    }

    private fun fetchDataFromRemote() {
        viewModelScope.launch {
            competitionRepository.fetchCompetitions()
                .process {
                    pushSingle(CompetitionsListEvent.FetchCompetition(it))
                    competitionRepository.insertCompetitions(it)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkDisposable?.dispose()
    }
}
