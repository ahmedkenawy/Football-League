package com.ahmedkenawy.footballleague.features.details.presentaion.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.ahmedkenawy.footballleague.R

/**
 * Binding adapter function to set the competition image using Coil library.
 *
 * @param imageView The ImageView to set the competition image.
 * @param name The name or URL of the competition image. Can be null if no image is available.
 */
@BindingAdapter("setCompetitionImage")
fun setCompetitionImage(imageView: ImageView, name: String?) {
    // Load competition image using Coil library
    imageView.load(name) {
        placeholder(R.drawable.image_placeholder) // Placeholder image while loading
        transformations(CircleCropTransformation()) // Apply circle crop transformation
        error(R.drawable.image_placeholder )  // error place holder image
        crossfade(true) // Enable crossfade animation
    }
}