package com.example.projekat_septembar.presentation.contract

import androidx.lifecycle.LiveData
import com.example.projekat_septembar.presentation.view.states.CarState

interface CarContract {
    interface ViewModel {
        val carState: LiveData<CarState>

        fun fetchAllCarsFromServer()
    }
}