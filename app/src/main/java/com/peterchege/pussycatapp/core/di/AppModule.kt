package com.peterchege.pussycatapp.core.di

import com.peterchege.pussycatapp.core.api.CatApi
import com.peterchege.pussycatapp.core.util.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {

    single{
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(CatApi::class.java)
    }

    single {

    }

    single {

    }

    viewModel{

    }
}