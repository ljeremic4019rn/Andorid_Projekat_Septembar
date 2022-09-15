package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.datasources.local.CarDao
import com.example.projekat_septembar.data.datasources.remote.CarDataSource
import com.example.projekat_septembar.data.models.Car
import com.example.projekat_septembar.data.models.Resource
import io.reactivex.Observable

class CarRepositoryImpl (private val localDataSource: CarDao, private val remoteDataSource: CarDataSource) : CarRepository {


    override fun fetchAllFromServer(): Observable<List<Car>> {
        return remoteDataSource
            .fetchAll()
            .map {
                it.cars.map { carResponse ->
                    Car(
                        id = carResponse.id,
                        car = carResponse.car,
                        car_model = carResponse.car_model,
                        car_color = carResponse.car_color,
                        car_model_year = carResponse.car_model_year,
                        car_vin = carResponse.car_vin,
                        price = carResponse.price,
                        availability = carResponse.availability,
                    )
                }
            }
    }


}