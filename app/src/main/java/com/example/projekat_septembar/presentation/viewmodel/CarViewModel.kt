package com.example.projekat_septembar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekat_septembar.data.models.Resource
import com.example.projekat_septembar.data.repositories.CarRepository
import com.example.projekat_septembar.presentation.contract.CarContract
import com.example.projekat_septembar.presentation.view.states.CarState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CarViewModel  (private val carRepository: CarRepository ) : ViewModel(), CarContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val carState: MutableLiveData<CarState> = MutableLiveData()



    //todo paginacija


    override fun fetchAllCarsFromServer() {
        val subscription = carRepository
            .fetchAllFromServer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    carState.value = CarState.Success(it)
                },
                {
                    carState.value = CarState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}