package com.rafaelmartorano.sicredichallenge.presentation.di

import com.rafaelmartorano.sicredichallenge.domain.repository.EventsRepository
import com.rafaelmartorano.sicredichallenge.domain.usecase.CheckinEventUseCase
import com.rafaelmartorano.sicredichallenge.domain.usecase.GetEventsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetEventsUseCase(
        eventsRepository: EventsRepository
    ): GetEventsUseCase{
        return GetEventsUseCase(eventsRepository)
    }

    @Singleton
    @Provides
    fun provideCheckinEventUseCase(
        eventsRepository: EventsRepository
    ): CheckinEventUseCase{
        return CheckinEventUseCase(eventsRepository)
    }

}