package com.rafaelmartorano.sicredichallenge.presentation.di

import com.rafaelmartorano.sicredichallenge.data.repository.EventsRepositoryImpl
import com.rafaelmartorano.sicredichallenge.data.repository.datasource.EventRemoteDataSource
import com.rafaelmartorano.sicredichallenge.domain.repository.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideEventsRepository(
        eventRemoteDataSource: EventRemoteDataSource
    ): EventsRepository{
        return EventsRepositoryImpl(eventRemoteDataSource)
    }

}