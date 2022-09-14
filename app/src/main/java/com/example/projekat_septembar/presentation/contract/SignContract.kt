package com.example.projekat_septembar.presentation.contract

import androidx.lifecycle.LiveData
import com.example.projekat_septembar.presentation.view.states.SignInState

interface SignContract {

    interface SignViewModel{
        val signInState: LiveData<SignInState>
        fun signIn(username: String, password: String): Boolean
        fun signUp(name: String, lastname: String, phone: String ,country: String): Boolean
    }
}