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
package com.peterchege.pussycatapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.peterchege.pussycatapp.core.api.responses.get_cat_breed_by_id_response.CatBreed
import com.peterchege.pussycatapp.core.util.Screens
import com.peterchege.pussycatapp.domain.mappers.toCatBreed
import com.peterchege.pussycatapp.domain.mappers.toUIModel
import com.peterchege.pussycatapp.domain.repository.NetworkStatus
import com.peterchege.pussycatapp.presentation.components.CatBreedCard
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navigateToCatBreedScreen:(String) -> Unit,
) {
    val viewModel = getViewModel<HomeScreenViewModel>()
    val networkStatus by viewModel.networkStatus.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val savedCatBreeds by viewModel.savedCatBreeds.collectAsStateWithLifecycle()

    HomeScreenContent(
        networkStatus = networkStatus,
        uiState = uiState,
        retryCallBack = { viewModel.getCatBreeds() },
        navigateToCatBreedScreen = navigateToCatBreedScreen,
        savedCatBreeds = savedCatBreeds,
        saveCatBreed = {
            viewModel.saveCatBreed(it)
        },
        unSaveCatBreed = {
            viewModel.unSaveCatBreed(it)
        }
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    networkStatus: NetworkStatus,
    uiState: HomeScreenUiState,
    retryCallBack: () -> Unit,
    navigateToCatBreedScreen:(String) -> Unit,
    savedCatBreeds: List<CatBreed>,
    saveCatBreed: (CatBreed) -> Unit,
    unSaveCatBreed: (String) -> Unit,
) {
    val snackbarHostState = SnackbarHostState()


    LaunchedEffect(key1 = networkStatus) {
        when (networkStatus) {
            is NetworkStatus.Disconnected -> {
                snackbarHostState.showSnackbar(message = "You are offline")
            }

            is NetworkStatus.Connected -> {
                snackbarHostState.showSnackbar(message = "Connected....")
            }

            is NetworkStatus.Unknown -> {
                snackbarHostState.showSnackbar(message = "Checking....")
            }
        }


    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cat App")
                }
            )
        }
    ) {
        when (uiState) {
            is HomeScreenUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
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

            is HomeScreenUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is HomeScreenUiState.Success -> {
                val cats = uiState.catBreeds
                    .map {
                        it.toUIModel(isSavedInDb = savedCatBreeds.map { it.id }.contains(it.id))
                    }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
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