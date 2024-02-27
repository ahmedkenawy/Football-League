package com.ahmedkenawy.footballleague.features.details.presentaion


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ahmedkenawy.footballleague.core.base.BaseFragment
import com.ahmedkenawy.footballleague.core.base.BaseViewModel
import com.ahmedkenawy.footballleague.databinding.FragmentCompetitionDetailsScreenBinding
import dagger.hilt.android.AndroidEntryPoint



/**
 * Fragment class responsible for displaying details of a competition.
 */
@AndroidEntryPoint
class CompetitionDetailsScreen : BaseFragment<CompetitionDetailsEvent>() {

    /**
     * Tag used for logging and debugging purposes.
     */
    override val mTag = "CompetitionDetailsScreen"

    /**
     * View binding instance for accessing views in the layout.
     */
    override val mBinding by lazy {
        FragmentCompetitionDetailsScreenBinding.inflate(layoutInflater)
    }

    /**
     * ViewModel instance associated with the fragment.
     */
    override val mViewModel by viewModels<CompetitionDetailsViewModel>()

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
        // Set the ViewModel instance to the binding variable for data binding
        mBinding.viewModel = mViewModel
    }
}