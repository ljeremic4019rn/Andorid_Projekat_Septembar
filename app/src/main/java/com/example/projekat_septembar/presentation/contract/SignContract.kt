package com.example.projekat_septembar.presentation.contract

import androidx.lifecycle.LiveData
import com.example.projekat_septembar.data.models.UserEntity
import com.example.projekat_septembar.presentation.view.states.CarState
import com.example.projekat_septembar.presentation.view.states.SignInState
import com.example.projekat_septembar.presentation.view.states.SignUpState
import io.reactivex.Completable
import io.reactivex.Observable

interface SignContract {

    interface SignViewModel{
        val signInState: LiveData<SignInState>
        val signUpState: LiveData<SignUpState>

        fun signIn(username: String, password: String): Boolean
        fun signUp(name: String, lastname: String, phone: Long ,country: String): Boolean

        fun registerUser(name: String, lastname: String, country: String, phone: Long)
        fun checkByCredentials(name: String, lastname: String, country: String, phone: Long)
    }
}