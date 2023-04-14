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
package com.peterchege.pussycatapp.presentation.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.pussycatapp.core.api.responses.cat_breeds_response.CatBreed
import com.peterchege.pussycatapp.core.api.responses.cats_by_breeds_response.Breed
import com.peterchege.pussycatapp.domain.use_case.GetCatBreedsUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
) : ViewModel() {
    val _catBreeds = mutableStateOf<List<CatBreed>>(emptyList())
    val catBreeds :State<List<CatBreed>> = _catBreeds

    init {
        getCatBreeds()
    }

    private fun getCatBreeds(){
        viewModelScope.launch {
            val response = getCatBreedsUseCase()
            _catBreeds.value = response

        }
    }




}