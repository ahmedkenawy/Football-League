package com.ahmedkenawy.footballleague.features.list.presentaion

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ahmedkenawy.footballleague.core.base.BaseFragment
import com.ahmedkenawy.footballleague.databinding.FragmentCompetitionsListScreenBinding
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.features.list.presentaion.adapter.CompetitionsAdapter
import com.ahmedkenawy.footballleague.features.list.presentaion.event.CompetitionsListEvent
import com.ahmedkenawy.footballleague.features.list.presentaion.viewmodel.CompetitionsListViewModel
import com.ahmedkenawy.footballleague.utils.extentions.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsListScreen : BaseFragment<CompetitionsListEvent>() {

    private lateinit var competitionsAdapter: CompetitionsAdapter

    override val mTag = "CompetitionsListScreen"

    override val mBinding by lazy {
        FragmentCompetitionsListScreenBinding.inflate(layoutInflater)
    }

    override val mViewModel by viewModels<CompetitionsListViewModel>()

    override fun onMyViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViews()
    }

    override fun setUpViews() {}

    override fun renderEvent(event: CompetitionsListEvent) {
        when (event) {
            is CompetitionsListEvent.FetchCompetition -> displayCompetitions(event.competitions)
            is CompetitionsListEvent.ShowNoInternet ->
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayCompetitions(competitions: List<Competitions?>) {
        competitionsAdapter = CompetitionsAdapter { position, _ ->
            navigate(
                CompetitionsListScreenDirections.actionCompetitionsListScreenToCompetitionDetailsScreen(
                    competitions[position]
                )
            )
        }
        mBinding.rvCompetitions.adapter = competitionsAdapter
        competitionsAdapter.submitList(competitions)
    }
}
