package com.sportunity.marathon.data.remote.service

import com.sportunity.marathon.data.remote.dto.events.MarathonEvents
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarathonService {

    @GET("events")
    suspend fun getEvents(
        @Query("include") include: String = "races",
        @Query("filters") filter: String = "eyJzdGF0ZSI6WyJiZWZvcmUi",
        @Query("page") page: Int = 1
    ): MarathonEvents

    @GET("985/races/{id}")
    suspend fun getRace(
        @Path("id") id: Int,
        @Query("include") include: String = "route"
    )

    companion object {
        const val BASE_URL = "https://api.tracx.events/v1/web/"
    }
}