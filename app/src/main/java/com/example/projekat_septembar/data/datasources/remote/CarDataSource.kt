package com.example.projekat_septembar.data.datasources.remote

import com.example.projekat_septembar.data.models.serverResponses.GetCarsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface CarDataSource {

    @GET("api/cars")
    fun fetchAll(): Observable<GetCarsResponse>

}