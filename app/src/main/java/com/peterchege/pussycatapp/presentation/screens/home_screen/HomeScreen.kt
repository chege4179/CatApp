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

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.SubcomposeAsyncImage
import com.peterchege.pussycatapp.domain.repository.NetworkStatus
import com.peterchege.pussycatapp.presentation.components.CatBreedCard
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val snackbarHostState = SnackbarHostState()
    val viewModel = getViewModel<HomeScreenViewModel>()
    val networkStatus = viewModel.networkStatus.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = networkStatus.value) {
        when(networkStatus.value){
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
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ){
            items(items = viewModel.catBreeds.value){ breed ->
                CatBreedCard(
                    catBreed = breed,
                    onNavigateToBreedScreen = {

                    }
                )

            }

        }

    }

}