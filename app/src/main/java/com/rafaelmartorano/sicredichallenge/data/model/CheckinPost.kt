package com.rafaelmartorano.sicredichallenge.data.model

import com.google.gson.annotations.SerializedName

class CheckinPost(

    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String

)