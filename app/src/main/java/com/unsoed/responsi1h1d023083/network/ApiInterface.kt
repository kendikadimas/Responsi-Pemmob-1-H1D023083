package com.unsoed.responsi1h1d023083.network

import retrofit2.Response
import com.unsoed.responsi1h1d023083.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {
    @GET("v4/teams/{id}")
    suspend fun getTeamDetails(
        @Path("id") teamId: String,
        @Header("X-Auth-Token") token: String
    ): Response<SearchResponse>

}
