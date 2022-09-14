package com.example.projekat_septembar.modules


import com.example.projekat_septembar.data.datasources.remote.SignDataSource
import com.example.projekat_septembar.data.repositories.SignRepoImpl
import com.example.projekat_septembar.data.repositories.SignRepository
import com.example.projekat_septembar.presentation.viewmodel.SignViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val signInModule = module {


    viewModel { SignViewModel(signRepository = get()) }

    single<SignRepository> { SignRepoImpl(signDataSource = get()) }

    single<SignDataSource> { create(retrofit = get()) }


}