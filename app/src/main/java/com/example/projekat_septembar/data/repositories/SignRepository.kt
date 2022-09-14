package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.serverResponses.UserDetails
import io.reactivex.Observable

interface SignRepository {

    fun signIn(username:String, password:String): Observable<UserDetails>
    fun signUp(firstname:String, lastname:String, mobile:Long, country:String): Observable<String>
}