package com.rafaelmartorano.sicredichallenge.domain.usecase

import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.domain.repository.EventsRepository

class CheckinEventUseCase(private val eventsRepository: EventsRepository) {
    suspend fun execute(checkinPost: CheckinPost) = eventsRepository.checkingEvent(checkinPost)
}