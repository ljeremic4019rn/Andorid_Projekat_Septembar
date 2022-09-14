package com.example.projekat_septembar.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekat_septembar.data.repositories.SignRepository
import com.example.projekat_septembar.presentation.contract.SignContract
import com.example.projekat_septembar.presentation.view.states.SignInState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class SignViewModel(private val signRepository: SignRepository) : ViewModel(), SignContract.SignViewModel {

    private val subscriptions = CompositeDisposable()
    override val signInState: MutableLiveData<SignInState> = MutableLiveData()

    override fun signIn(username: String, password: String): Boolean {
        val subscription = signRepository
            .userAuth(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    signInState.value = SignInState.Success(it)
                },
                {
                    signInState.value = SignInState.Error("You've entered wrong data")
                    Timber.e(it)

                },
                {
                    Timber.e("Completed")
                }
            )

        subscriptions.add(subscription)
        return true

    }

    override fun signUp(name: String, lastname: String, phone: String, country: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}