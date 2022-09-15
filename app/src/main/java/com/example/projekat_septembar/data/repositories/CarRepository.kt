package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.Car
import com.example.projekat_septembar.data.models.Resource
import com.example.projekat_septembar.data.models.SellerDetails
import io.reactivex.Observable
import io.reactivex.Completable


interface CarRepository {
    fun fetchAllFromServer(): Observable<List<Car>>
    fun getSeller(id: Long): Observable<SellerDetails>
    fun contactSeller(firstname: String, lastname: String, message: String, contact: Int): Observable<Resource<Unit>>
    fun search(type: String, key: String): Observable<List<Car>>

    fun saveCarToDb(car: Car): Completable
}