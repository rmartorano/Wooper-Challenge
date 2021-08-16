package com.rafaelmartorano.sicredichallenge.presentation.di

import com.rafaelmartorano.sicredichallenge.BuildConfig
import com.rafaelmartorano.sicredichallenge.data.api.EventAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideEventAPIService(retrofit: Retrofit): EventAPIService{
        return retrofit.create(EventAPIService::class.java)
    }

}