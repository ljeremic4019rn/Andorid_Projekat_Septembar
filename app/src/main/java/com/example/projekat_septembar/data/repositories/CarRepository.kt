package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.Car
import com.example.projekat_septembar.data.models.SellerDetails
import io.reactivex.Observable
import io.reactivex.Completable


interface CarRepository {
    fun fetchAllFromServer(): Observable<List<Car>>
    fun contactSeller(id: Long): Observable<SellerDetails>

    fun saveCarToDb(car: Car): Completable
}