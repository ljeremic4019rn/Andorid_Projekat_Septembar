package com.example.projekat_septembar.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projekat_septembar.data.models.CarEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class CarDao {

    @Query("SELECT * FROM cars")
    abstract fun getAll(): Observable<List<CarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(carEntity: CarEntity): Completable
}