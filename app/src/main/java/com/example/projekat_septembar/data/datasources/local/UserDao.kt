package com.example.projekat_septembar.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projekat_septembar.data.models.CarEntity
import com.example.projekat_septembar.data.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userEntity: UserEntity): Completable

    @Query("SELECT COUNT(*) FROM users WHERE name == :name and lastname == :lastname and phone == :phone and country == :country ")
    abstract fun getByCredentials(name: String, lastname: String, country: String, phone: Long): Observable<Int>

}