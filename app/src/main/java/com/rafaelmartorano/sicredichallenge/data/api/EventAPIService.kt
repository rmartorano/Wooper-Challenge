package com.rafaelmartorano.sicredichallenge.data.api

import com.google.gson.JsonElement
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventAPIService {

    @GET("api/events")
    suspend fun getEvents(): Response<EventList>

    @POST("api/checkin")
    suspend fun checkIn(@Body checkinPost: CheckinPost): Response<CheckinPost>

}