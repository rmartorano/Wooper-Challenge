package com.rafaelmartorano.sicredichallenge.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.learning.newsapiclient.data.util.Resource
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventList
import com.rafaelmartorano.sicredichallenge.domain.usecase.CheckinEventUseCase
import com.rafaelmartorano.sicredichallenge.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class EventViewModel(
    private val app: Application,
    private val getEventsUseCase: GetEventsUseCase,
    private val checkinEventUseCase: CheckinEventUseCase
): AndroidViewModel(app) {

    val events: MutableLiveData<Resource<EventList>> = MutableLiveData()

    fun getEvents() = viewModelScope.launch(Dispatchers.IO){
        try{
            events.postValue(Resource.Loading())
            if(isNetworkAvailable(app)){
                val apiResult = getEventsUseCase.execute()
                events.postValue(apiResult)
            }
            else{
                events.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception){
            events.postValue(Resource.Error(e.message.toString()))
        }
    }

    suspend fun checkinEvent(checkinPost: CheckinPost): Response<CheckinPost> {
        return checkinEventUseCase.execute(checkinPost)
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

}