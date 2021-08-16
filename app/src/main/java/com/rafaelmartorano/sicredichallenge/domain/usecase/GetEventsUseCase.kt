package com.rafaelmartorano.sicredichallenge.domain.usecase

import com.learning.newsapiclient.data.util.Resource
import com.rafaelmartorano.sicredichallenge.data.model.EventItem
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import com.rafaelmartorano.sicredichallenge.domain.repository.EventsRepository

class GetEventsUseCase(private val eventsRepository: EventsRepository) {
    suspend fun execute(): Resource<EventList> = eventsRepository.getEvents()
}