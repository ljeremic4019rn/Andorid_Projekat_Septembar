package com.example.projekat_septembar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekat_septembar.data.models.Car
import com.example.projekat_septembar.data.models.Resource
import com.example.projekat_septembar.data.models.SellerDetails
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
    override val paginationList: MutableLiveData<List<Car>> = MutableLiveData()
    override val contactedSeller: MutableLiveData<SellerDetails> = MutableLiveData()
    override var allCars: List<Car> = arrayListOf()
    private var size = 0


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

    override fun loadPagination(initial: Boolean) {//todo ova funkija je ista kao kod krstica mozda posumnja da je kopirano, Mladenova odluka
        val tmpArrayList: ArrayList<Car> = arrayListOf()
        when {
            initial -> size = 9
            size + 10 <= allCars.size -> size += 10
            else -> size += (allCars.size - size)
        }

        println("counter $size list ${allCars.size}")

        if (size <= allCars.size) {
            for (i in 0 until size)
                tmpArrayList.add(allCars[i])
            paginationList.value = tmpArrayList
        }
    }

    override fun search(type: String, key: String) {
        val subscription = carRepository
            .search(type, key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    carState.value = CarState.Searched(it)
                },
                {
                    carState.value = CarState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun saveCar(car: Car) {
        val subscription = carRepository
            .saveCarToDb(car)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    carState.value = CarState.Saved("Car has been saved")
                    Timber.e("Saved Car")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll() {
        val subscription = carRepository
            .getAllCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    carState.value = CarState.LocalSuccess(it.reversed())
                },
                {
                    carState.value = CarState.Error("Error happened while getting data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteCar(carId: Long){
        val subscription = carRepository
            .deleteById(carId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("DELETED")

                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getSeller(id: Long) {
        val subscription = carRepository
            .getSeller(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    contactedSeller.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun contactSeller(firstname: String, lastname: String, message: String, contact: Int) {
        val subscription = carRepository
            .contactSeller(firstname, lastname, message, contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Success -> carState.value = CarState.Contacted("Message sent to seller")
                        is Resource.Error -> carState.value = CarState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}