package com.ahmedkenawy.footballleague.features.list.data.remote

import com.ahmedkenawy.footballleague.features.list.data.remote.response.CompetitionDto
import com.ahmedkenawy.footballleague.network.ApiResponse
import retrofit2.http.GET

/**
 * Interface representing the API service for fetching competitions data.
 */
interface CompetitionsApi {

    /**
     * Method to fetch competitions data from the remote server.
     *
     * @return An ApiResponse containing a list of CompetitionDto objects wrapped in a MutableList.
     */
    @GET("competitions")
    suspend fun fetchCompetitions(): ApiResponse<MutableList<CompetitionDto>>
}
