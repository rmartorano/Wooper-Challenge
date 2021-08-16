package com.rafaelmartorano.sicredichallenge.presentation.di

import com.rafaelmartorano.sicredichallenge.data.api.EventAPIService
import com.rafaelmartorano.sicredichallenge.data.repository.datasource.EventRemoteDataSource
import com.rafaelmartorano.sicredichallenge.data.repository.datasourceImpl.EventRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideEventRemoteDataSource(
        eventAPIService: EventAPIService
    ): EventRemoteDataSource{
        return EventRemoteDataSourceImpl(eventAPIService)
    }

}