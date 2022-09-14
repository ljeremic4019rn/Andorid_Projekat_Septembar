package com.example.projekat_septembar.presentation.view.states


import com.example.projekat_septembar.data.models.serverResponses.SignUpResponse
import com.example.projekat_septembar.data.models.serverResponses.UserDetails

sealed class SignUpState {
    object DataFetched: SignUpState()
    data class Success(val singUpResponse: String): SignUpState()
    data class Error(val message: String): SignUpState()
}