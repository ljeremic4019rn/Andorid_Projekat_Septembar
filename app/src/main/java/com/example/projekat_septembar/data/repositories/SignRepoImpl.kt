package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.datasources.remote.SignDataSource
import com.example.projekat_septembar.data.models.SignInRequest
import com.example.projekat_septembar.data.models.serverResponses.UserDetails
import io.reactivex.Observable

class SignRepoImpl (private val signInDataSource: SignDataSource): SignRepository {
    override fun userAuth(username: String, password: String): Observable<UserDetails> {
        return signInDataSource
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
}