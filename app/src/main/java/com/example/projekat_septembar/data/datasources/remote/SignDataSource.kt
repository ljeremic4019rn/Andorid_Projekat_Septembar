package com.example.projekat_septembar.data.datasources.remote

import com.example.projekat_septembar.data.models.SignInRequest
import com.example.projekat_septembar.data.models.serverResponses.SignInResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface SignDataSource {

    @POST("api/login")
    fun signIn(@Body body: SignInRequest): Observable<SignInResponse>

    @POST("api/login")
    fun signUp(@Body body: SignInRequest): Observable<SignInResponse>
}