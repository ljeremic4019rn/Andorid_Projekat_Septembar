package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.Resource
import com.example.projekat_septembar.data.models.UserEntity
import com.example.projekat_septembar.data.models.serverResponses.UserDetails
import io.reactivex.Completable
import io.reactivex.Observable

interface SignRepository {

    fun signIn(username:String, password:String): Observable<UserDetails>
    fun signUp(firstname:String, lastname:String, mobile:Long, country:String): Observable<String>

    fun registerUser(name: String, lastname: String, country: String, phone: Long): Completable
    fun checkByCredentials(name: String, lastname: String, country: String, phone: Long): Observable<Int>
}