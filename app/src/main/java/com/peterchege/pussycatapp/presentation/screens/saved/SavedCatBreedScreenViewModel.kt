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
package com.peterchege.pussycatapp.presentation.screens.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.domain.mappers.toUIModel
import com.peterchege.pussycatapp.domain.models.CatBreedUI
import com.peterchege.pussycatapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface SavedCatBreedScreenUiState {

    object Loading : SavedCatBreedScreenUiState

    data class Success(val catBreeds: List<CatBreedUI>) : SavedCatBreedScreenUiState

    data class Error(val message: String) : SavedCatBreedScreenUiState

}


class SavedCatBreedScreenViewModel(
    val repository: CatRepository
) : ViewModel() {
    fun saveCatBreed(catBreed: CatBreed) {
        viewModelScope.launch {
            repository.insertCatBreed(catBreed)
        }
    }

    fun unSaveCatBreed(id: String) {
        viewModelScope.launch {
            repository.deleteCatBreedById(id)
        }
    }

    val uiState = repository.getAllSavedCatBreeds()
        .map {
            SavedCatBreedScreenUiState.Success(it.map { it.toUIModel(isSavedInDb = true) })
        }
        .catch {
            SavedCatBreedScreenUiState.Error(message = "Unexpected error occurred")
        }
        .onStart { SavedCatBreedScreenUiState.Loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SavedCatBreedScreenUiState.Loading
        )

}