package com.example.projekat_septembar.data.repositories

import com.example.projekat_septembar.data.models.serverResponses.UserDetails
import io.reactivex.Observable

interface SignRepository {

    fun userAuth(username:String, password:String): Observable<UserDetails>
}