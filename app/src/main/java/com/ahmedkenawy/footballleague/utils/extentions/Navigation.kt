package com.ahmedkenawy.footballleague.utils.extentions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * Extension function to simplify navigation within a Fragment.
 *
 * @param action The NavDirections representing the navigation action.
 */
fun Fragment.navigate(action: NavDirections) {
    findNavController().navigate(action)
}
