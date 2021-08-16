package com.rafaelmartorano.sicredichallenge.data.repository.datasource

import com.google.gson.JsonElement
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventItem
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import retrofit2.Call
import retrofit2.Response

interface EventRemoteDataSource {
    suspend fun getEvents(): Response<EventList>
    suspend fun checkinEvent(checkinPost: CheckinPost): Response<CheckinPost>
}