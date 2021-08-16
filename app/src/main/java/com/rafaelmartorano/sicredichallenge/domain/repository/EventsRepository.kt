package com.rafaelmartorano.sicredichallenge.domain.repository

import com.google.gson.JsonElement
import com.learning.newsapiclient.data.util.Resource
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Flow

interface EventsRepository {
    suspend fun getEvents(): Resource<EventList>
    suspend fun checkingEvent(checkinPost: CheckinPost): Response<CheckinPost>
}