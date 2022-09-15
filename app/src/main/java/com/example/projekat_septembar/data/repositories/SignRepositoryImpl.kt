package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.datasources.remote.SignDataSource
import com.example.projekat_septembar.data.models.serverRequests.SignInRequest
import com.example.projekat_septembar.data.models.serverRequests.SignUpRequest
import com.example.projekat_septembar.data.models.serverResponses.UserDetails
import io.reactivex.Observable

class SignRepositoryImpl (private val signDataSource: SignDataSource): SignRepository {

    override fun signIn(username: String, password: String): Observable<UserDetails> {
        return signDataSource
            .signIn(
                SignInRequest(username, password, true)
            )
            .map {
                UserDetails(
                    userName = it.userDetails.userName,
                    password = it.userDetails.password,
                    verified = true
                )
            }
    }

    override fun signUp(firstname: String, lastname: String, mobile: Long, country: String): Observable<String> {
        return signDataSource
            .signUp(
                SignUpRequest(firstname, lastname, mobile, country)
            )
            .map {
                it.Message
            }
    }
}