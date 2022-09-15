package com.example.projekat_septembar.data.datasources.local

import androidx.room.Dao
import androidx.room.Query
import com.example.projekat_septembar.data.models.CarEntity
import io.reactivex.Observable

@Dao
abstract class CarDao {

    @Query("SELECT * FROM cars")
    abstract fun getAll(): Observable<List<CarEntity>>
}