package com.example.projekat_septembar.data.models.serverResponses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponse(
    val data: Data,
    val Message: String,
    val UserId: String,
    val CreatedAt: Int,
    val NextSteps: String,
    val EmailValidation: String,
    val Subscribed: Boolean,

)
