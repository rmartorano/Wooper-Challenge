package com.rafaelmartorano.sicredichallenge.presentation.di

import android.app.Application
import com.rafaelmartorano.sicredichallenge.domain.usecase.CheckinEventUseCase
import com.rafaelmartorano.sicredichallenge.domain.usecase.GetEventsUseCase
import com.rafaelmartorano.sicredichallenge.presentation.viewmodel.EventViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideEventViewModelFactory(
        application: Application,
        getEventsUseCase: GetEventsUseCase,
        checkinEventUseCase: CheckinEventUseCase
    ): EventViewModelFactory{
        return EventViewModelFactory(application, getEventsUseCase, checkinEventUseCase)
    }
}