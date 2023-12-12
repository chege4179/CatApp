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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.util.Screens
import com.peterchege.pussycatapp.domain.mappers.toCatBreed
import com.peterchege.pussycatapp.domain.mappers.toUIModel
import com.peterchege.pussycatapp.domain.repository.NetworkStatus
import com.peterchege.pussycatapp.presentation.components.CatBreedCard
import com.peterchege.pussycatapp.presentation.screens.home.HomeScreenUiState
import org.koin.androidx.compose.getViewModel

@Composable
fun SavedCatBreedScreen(
    navigateToCatBreedScreen:(String) -> Unit,
) {
    val viewModel = getViewModel<SavedCatBreedScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavedCatBreedScreenContent(
        uiState = uiState,
        retryCallBack = { /*TODO*/ },
        navigateToCatBreedScreen = navigateToCatBreedScreen,
        saveCatBreed = {
            viewModel.saveCatBreed(it)
        },
        unSaveCatBreed = {
            viewModel.unSaveCatBreed(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedCatBreedScreenContent(
    uiState: SavedCatBreedScreenUiState,
    retryCallBack: () -> Unit,
    saveCatBreed: (CatBreed) -> Unit,
    unSaveCatBreed: (String) -> Unit,
    navigateToCatBreedScreen:(String) -> Unit,
) {
    val snackbarHostState = SnackbarHostState()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Saved Cat Breeds")
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is SavedCatBreedScreenUiState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = uiState.message)
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            retryCallBack()
                        }
                    ) {
                        Text(text = "Retry")

                    }
                }
            }

            is SavedCatBreedScreenUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is SavedCatBreedScreenUiState.Success -> {
                val cats = uiState.catBreeds
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .padding(paddingValues),
                ) {

                    items(items = cats) { breed ->
                        CatBreedCard(
                            catBreed = breed,
                            onNavigateToBreedScreen = {
                                navigateToCatBreedScreen(it)
                            },
                            saveCatBreed = {
                                saveCatBreed(it.toCatBreed())
                            },
                            unSaveCatBreed = {
                                unSaveCatBreed(it)

                            }

                        )
                    }
                }
            }
        }
    }
}

