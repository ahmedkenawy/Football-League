package com.ahmedkenawy.footballleague.features.list.presentaion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.ahmedkenawy.footballleague.R
import com.ahmedkenawy.footballleague.core.base.BaseFragment
import com.ahmedkenawy.footballleague.core.base.BaseViewModel
import com.ahmedkenawy.footballleague.databinding.FragmentCompetitionsListScreenBinding
import com.ahmedkenawy.footballleague.features.list.domain.Competitions
import com.ahmedkenawy.footballleague.features.list.presentaion.adapter.CompetitionsAdapter
import com.ahmedkenawy.footballleague.utils.extentions.navigate
import dagger.hilt.android.AndroidEntryPoint
/**
 * Fragment class responsible for displaying the list of competitions in the UI.
 */
@AndroidEntryPoint
class CompetitionsListScreen : BaseFragment<CompetitionsListEvent>() {

    /**
     * Adapter for displaying competitions in a RecyclerView.
     */
    private lateinit var competitionsAdapter: CompetitionsAdapter

    /**
     * Tag used for logging and debugging purposes.
     */
    override val mTag = "CompetitionsListScreen"

    /**
     * View binding instance for accessing views in the layout.
     */
    override val mBinding by lazy {
        FragmentCompetitionsListScreenBinding.inflate(layoutInflater)
    }

    /**
     * ViewModel instance associated with the fragment.
     */
    override val mViewModel by viewModels<CompetitionsListViewModel>()

    /**
     * Method called when the fragment view is created.
     *
     * @param view The fragment's root view.
     * @param savedInstanceState The saved instance state of the fragment.
     */
    override fun onMyViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViews()
    }

    /**
     * Method for setting up views and initializing UI components.
     */
    override fun setUpViews() {
        // Views setup can be done here if needed
    }

    /**
     * Method for rendering events received from the ViewModel.
     *
     * @param event The competition list event received from the ViewModel.
     */
    override fun renderEvent(event: CompetitionsListEvent) {
        when (event) {
            is CompetitionsListEvent.FetchCompetition -> displayCompetitions(event.competitions)
        }
    }

    /**
     * Method for displaying the list of competitions in the UI.
     *
     * @param competitions The list of competitions to be displayed.
     */
    private fun displayCompetitions(competitions: List<Competitions?>) {
        competitionsAdapter = CompetitionsAdapter { position, competition ->
            // Navigate to competition details screen when a competition item is clicked
            navigate(
                CompetitionsListScreenDirections.actionCompetitionsListScreenToCompetitionDetailsScreen(
                    competitions[position]
                )
            )
        }
        // Set up RecyclerView with competitions adapter
        mBinding.rvCompetitions.adapter = competitionsAdapter
        competitionsAdapter.submitList(competitions)
    }
}
