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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.util.NetworkResult
import com.peterchege.pussycatapp.domain.repository.NetworkConnectivityService
import com.peterchege.pussycatapp.domain.repository.NetworkStatus
import com.peterchege.pussycatapp.domain.use_case.GetAllCatBreedsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch



sealed interface HomeScreenUiState {

    object Loading : HomeScreenUiState

    data class Success(val catBreeds:List<CatBreed> ) : HomeScreenUiState

    data class Error(val message: String ) : HomeScreenUiState

}

class HomeScreenViewModel(
    private val getCatBreedsUseCase: GetAllCatBreedsUseCase,
    private val networkConnectivityService: NetworkConnectivityService,
) : ViewModel() {


    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()


    val networkStatus = networkConnectivityService.networkStatus
        .stateIn(
            scope = viewModelScope,
            started= SharingStarted.WhileSubscribed(5000L),
            initialValue = NetworkStatus.Unknown,
        )

    init {
        getCatBreeds()
    }

    fun getCatBreeds(){
        viewModelScope.launch {
            val response = getCatBreedsUseCase()
            when(response){
                is NetworkResult.Loading -> {
                    _uiState.value = HomeScreenUiState.Loading
                }
                is NetworkResult.Error -> {
                    _uiState.value = HomeScreenUiState.Error(message = response.message)

                }
                is NetworkResult.Success -> {
                    _uiState.value = HomeScreenUiState.Success(catBreeds = response.data)
                }
                is NetworkResult.Empty -> {
                    _uiState.value = HomeScreenUiState.Error(message = "Nothing was found")
                }
            }


        }
    }




}