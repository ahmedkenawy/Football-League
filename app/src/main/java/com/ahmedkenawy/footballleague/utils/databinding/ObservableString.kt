package com.ahmedkenawy.footballleague.utils.databinding

import androidx.databinding.Observable
import androidx.databinding.ObservableField


/**
 * ObservableString class extends ObservableField<String> and provides an observable string property.
 *
 * @param dependencies Additional observables that this ObservableString depends on.
 */
class ObservableString(
    vararg dependencies: Observable
) : ObservableField<String>(*dependencies) {

    /**
     * Initializes the ObservableString with an empty string value.
     */
    init {
        set("")
    }

    /**
     * Overrides the get method to return an empty string if the value is null.
     *
     * @return The value of the ObservableString or an empty string if null.
     */
    override fun get(): String = super.get() ?: ""
}
