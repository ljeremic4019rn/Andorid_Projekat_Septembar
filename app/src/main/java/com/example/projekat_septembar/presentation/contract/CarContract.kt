package com.example.projekat_septembar.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projekat_septembar.data.models.Car
import com.example.projekat_septembar.data.models.SellerDetails
import com.example.projekat_septembar.presentation.view.states.CarState

interface CarContract {
    interface ViewModel {
        val carState: LiveData<CarState>
        var allCars: List<Car>
        val paginationList: MutableLiveData<List<Car>>
        val contactedSeller: LiveData<SellerDetails>


        fun saveCar(car: Car)
        fun getSeller(id: Long)
        fun fetchAllCarsFromServer()
        fun loadPagination(initial: Boolean)

    }
}