package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.Car
import io.reactivex.Observable
import com.example.projekat_septembar.data.models.Resource
import io.reactivex.Completable


interface CarRepository {
    fun fetchAllFromServer(): Observable<List<Car>>

    fun saveCarToDb(car: Car): Completable
}