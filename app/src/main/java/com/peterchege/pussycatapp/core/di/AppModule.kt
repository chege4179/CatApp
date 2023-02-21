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

import com.peterchege.pussycatapp.core.api.CatApi
import com.peterchege.pussycatapp.core.util.Constants
import com.peterchege.pussycatapp.data.ImageRepositoryImpl
import com.peterchege.pussycatapp.domain.repository.ImageRepository
import com.peterchege.pussycatapp.domain.use_case.GetCatBreedsUseCase
import com.peterchege.pussycatapp.domain.use_case.GetCatsByBreedUseCase
import com.peterchege.pussycatapp.presentation.screens.cat_breed_screen.CatBreedScreenViewModel
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
    single {
        GetCatBreedsUseCase(get())
    }
    single {
        GetCatsByBreedUseCase(get())
    }

    viewModel {
        HomeScreenViewModel(get())
    }

    viewModel {
        CatBreedScreenViewModel(get(),get())

    }



}