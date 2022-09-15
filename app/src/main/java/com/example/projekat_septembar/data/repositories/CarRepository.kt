package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.Car
import io.reactivex.Observable
import com.example.projekat_septembar.data.models.Resource


interface CarRepository {
    fun fetchAllFromServer(): Observable<List<Car>>

}