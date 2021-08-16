package com.rafaelmartorano.sicredichallenge.data.repository

import android.util.Log
import com.google.gson.JsonElement
import com.learning.newsapiclient.data.util.Resource
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventItem
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import com.rafaelmartorano.sicredichallenge.data.repository.datasource.EventRemoteDataSource
import com.rafaelmartorano.sicredichallenge.domain.repository.EventsRepository
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class EventsRepositoryImpl(
    private val eventRemoteDataSource: EventRemoteDataSource
): EventsRepository {

    override suspend fun checkingEvent(checkinPost: CheckinPost): Response<CheckinPost> {
        return eventRemoteDataSource.checkinEvent(checkinPost)
    }

    override suspend fun getEvents(): Resource<EventList> {
        return getEventsFromAPI(eventRemoteDataSource.getEvents())
    }

    private fun getEventsFromAPI(response: Response<EventList>): Resource<EventList>{
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}