package com.sportunity.marathon.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarathonService {

    @GET
    fun getEvents(
        @Query("include") include: String = "races",
        @Query("filters") filter: String = "eyJzdGF0ZSI6WyJiZWZvcmUi",
        @Query("page") page: Int = 1
    )

    @GET("985/races/{id}")
    fun getRace(
        @Path("id") id: Int,
        @Query("include") include: String = "route"
    )

    companion object {
        const val BASE_URL = "https://api.tracx.events/v1/web/events/"
    }
}