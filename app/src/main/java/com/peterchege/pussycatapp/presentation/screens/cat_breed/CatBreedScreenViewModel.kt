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
package com.peterchege.pussycatapp.presentation.screens.cat_breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.util.NetworkResult
import com.peterchege.pussycatapp.domain.use_case.GetCatBreedByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface CatBreedScreenUiState {

    object Loading : CatBreedScreenUiState

    data class Success(val catBreed:CatBreed):CatBreedScreenUiState

    data class Error(val message: String ) : CatBreedScreenUiState

}

class CatBreedScreenViewModel(

    private val getCatsByBreedUseCase: GetCatBreedByIdUseCase,
) :ViewModel(){

    private val _uiState = MutableStateFlow<CatBreedScreenUiState>(CatBreedScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getCatBreedById(breedId:String){
        viewModelScope.launch {
            val result = getCatsByBreedUseCase(breedId = breedId)
            when(result){
                is NetworkResult.Empty -> {
                    _uiState.value = CatBreedScreenUiState.Error(message = "This cat info was not found")
                }
                is NetworkResult.Loading -> {
                    _uiState.value = CatBreedScreenUiState.Loading
                }
                is NetworkResult.Success -> {
                    _uiState.value = CatBreedScreenUiState.Success(catBreed = result.data)
                }
                is NetworkResult.Error -> {
                    _uiState.value = CatBreedScreenUiState.Error(message = result.message)
                }
            }
        }
    }



}