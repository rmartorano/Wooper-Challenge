package com.rafaelmartorano.sicredichallenge.data.repository.datasourceImpl

import com.google.gson.JsonElement
import com.rafaelmartorano.sicredichallenge.data.api.EventAPIService
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import com.rafaelmartorano.sicredichallenge.data.repository.datasource.EventRemoteDataSource
import retrofit2.Call
import retrofit2.Response

class EventRemoteDataSourceImpl(
    private val eventAPIService: EventAPIService
): EventRemoteDataSource {
    override suspend fun getEvents(): Response<EventList> = eventAPIService.getEvents()
    override suspend fun checkinEvent(checkinPost: CheckinPost): Response<CheckinPost> = eventAPIService.checkIn(checkinPost)
}