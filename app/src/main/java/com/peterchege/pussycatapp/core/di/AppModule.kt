package com.peterchege.pussycatapp.core.di

import com.peterchege.pussycatapp.core.api.CatApi
import com.peterchege.pussycatapp.core.util.Constants
import com.peterchege.pussycatapp.data.ImageRepositoryImpl
import com.peterchege.pussycatapp.domain.repository.ImageRepository
import com.peterchege.pussycatapp.presentation.screens.home_screen.HomeScreenViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {

    single<CatApi>{
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(CatApi::class.java)
    }

    single<ImageRepository>{
        ImageRepositoryImpl(get())

    }

    viewModel {
        HomeScreenViewModel(get())
    }



}