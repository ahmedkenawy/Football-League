package com.ahmedkenawy.footballleague.network

import com.google.gson.annotations.SerializedName


/**
 * Data class representing an API response.
 *
 * @param count The number of items in the response.
 * @param filters The filters applied to the response. It can be of any type.
 * @param responseData The response data of type T, typically representing the main data returned by the API.
 *                      It's annotated with @SerializedName to map the JSON key "competitions" to responseData.
 */
data class ApiResponse<T>(
    val count: Int, // The number of items in the response
    val filters: Any, // The filters applied to the response. It can be of any type.
    @SerializedName(value = "competitions")
    val responseData: T, // The response data of type T
)
