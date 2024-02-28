package com.ahmedkenawy.footballleague.features.list.presentaion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ahmedkenawy.footballleague.R
import com.ahmedkenawy.footballleague.databinding.ItemCompetitionBinding
import com.ahmedkenawy.footballleague.features.list.domain.Competitions

/**
 * Adapter class for displaying a list of competitions in a RecyclerView.
 *
 * @param onCompetitionClickListener A lambda function that takes the position of the clicked item
 *                                   and the associated competition item as parameters.
 */
class CompetitionsAdapter(
    private val onCompetitionClickListener: (Int, Competitions) -> Unit
) :
    ListAdapter<Competitions, CompetitionsAdapter.CompetitionsViewHolder>(MyDiffUtil) {

    /**
     * Companion object used for implementing the DiffUtil.ItemCallback interface for calculating
     * the difference between two lists of competitions.
     */
    companion object MyDiffUtil : DiffUtil.ItemCallback<Competitions>() {
        /**
         * Checks if two items represent the same object.
         *
         * @param oldItem The old competition item.
         * @param newItem The new competition item.
         * @return True if items are the same, false otherwise.
         */
        override fun areItemsTheSame(oldItem: Competitions, newItem: Competitions): Boolean {
            return oldItem == newItem
        }

        /**
         * Checks if the contents of two items are the same.
         *
         * @param oldItem The old competition item.
         * @param newItem The new competition item.
         * @return True if contents are the same, false otherwise.
         */
        override fun areContentsTheSame(oldItem: Competitions, newItem: Competitions): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * ViewHolder class for holding the views associated with a single competition item.
     *
     * @param binding The ViewBinding object for the item competition layout.
     */
    inner class CompetitionsViewHolder(val binding: ItemCompetitionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds data to the views within the ViewHolder.
         *
         * @param position The position of the item within the adapter.
         * @param competition The competition object to bind data from.
         */
        fun bind(position: Int, competition: Competitions) = with(binding) {
            competition.let {
                // Load competition emblem image using Coil library
                ivCompetitionEmblem.load(it.competitionEmblem) {
                    placeholder(R.drawable.image_placeholder)
                    transformations(CircleCropTransformation())
                    error(R.drawable.error_place_holder)
                    crossfade(true)
                }
                // Set competition name
                tvCompetitionName.text = it.competitionName
                // Set area name
                tvAreaName.text = it.areaName
            }

            // Set click listener for competition item
            binding.clCompetition.setOnClickListener {
                onCompetitionClickListener(position, competition)
            }
        }
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        // Inflate the item competition layout
        val binding =
            ItemCompetitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionsViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item
     *               at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        // Bind data to the ViewHolder
        getItem(position)?.let {
            holder.bind(position, it)
        }
    }
}
