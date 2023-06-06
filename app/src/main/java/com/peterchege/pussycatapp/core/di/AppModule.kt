/*
 * Copyright 2023 Pussy Cat App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.pussycatapp.core.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.peterchege.pussycatapp.core.api.CatService
import com.peterchege.pussycatapp.core.api.CatServiceImpl
import com.peterchege.pussycatapp.core.api.HttpClientFactory
import com.peterchege.pussycatapp.data.ImageRepositoryImpl
import com.peterchege.pussycatapp.data.NetworkConnectivityServiceImpl
import com.peterchege.pussycatapp.domain.repository.ImageRepository
import com.peterchege.pussycatapp.domain.repository.NetworkConnectivityService
import com.peterchege.pussycatapp.domain.use_case.GetAllCatBreedsUseCase
import com.peterchege.pussycatapp.domain.use_case.GetCatBreedByIdUseCase

import com.peterchege.pussycatapp.presentation.screens.cat_breed_screen.CatBreedScreenViewModel
import com.peterchege.pussycatapp.presentation.screens.home_screen.HomeScreenViewModel
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single<CatService> {
        CatServiceImpl(client = get())
    }
    single<HttpClient> {
        HttpClientFactory().create(engine = get())
    }
    single<HttpClientEngine>{
        OkHttp.create {
            addInterceptor(
                ChuckerInterceptor.Builder(androidContext())
                    .collector(ChuckerCollector(androidContext()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
        }
    }
    single<NetworkConnectivityService>{
        NetworkConnectivityServiceImpl(context = androidContext())
    }

    single<ImageRepository> {
        ImageRepositoryImpl(api = get())

    }
    single {
        GetAllCatBreedsUseCase(repository = get())
    }
    single {
        GetCatBreedByIdUseCase(repository = get())
    }

    viewModel {
        HomeScreenViewModel(getCatBreedsUseCase = get(), networkConnectivityService = get())
    }

    viewModel {
        CatBreedScreenViewModel(getCatsByBreedUseCase =  get())

    }


}