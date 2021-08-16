package com.rafaelmartorano.sicredichallenge.presentation.di

import com.rafaelmartorano.sicredichallenge.presentation.adapter.EventAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsAdapter(): EventAdapter{
        return EventAdapter()
    }

}