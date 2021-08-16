package com.rafaelmartorano.sicredichallenge.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafaelmartorano.sicredichallenge.domain.usecase.CheckinEventUseCase
import com.rafaelmartorano.sicredichallenge.domain.usecase.GetEventsUseCase

class EventViewModelFactory(
    private val app: Application,
    private val getEventsUseCase: GetEventsUseCase,
    private val checkinEventUseCase: CheckinEventUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventViewModel(
            app,
            getEventsUseCase,
            checkinEventUseCase
        ) as T
    }
}