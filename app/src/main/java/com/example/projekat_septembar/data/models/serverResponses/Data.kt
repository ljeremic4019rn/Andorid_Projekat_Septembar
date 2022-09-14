package com.example.projekat_septembar.data.models.serverResponses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val firstName: String,
    val lastName: String,
    val mobile: Int,
    val country: String
)
