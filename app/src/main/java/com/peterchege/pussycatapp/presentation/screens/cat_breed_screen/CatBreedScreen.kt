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
package com.peterchege.pussycatapp.presentation.screens.cat_breed_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.peterchege.pussycatapp.core.util.getCatImageById
import com.peterchege.pussycatapp.presentation.screens.home_screen.HomeScreenViewModel
import org.koin.androidx.compose.getViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreen(
    navController:NavController,
    breedId:String,
){
    val viewModel = getViewModel<CatBreedScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = breedId){
        viewModel.getCatBreedById(breedId = breedId)
    }

    CatBreedScreenContent(
        navController = navController,
        uiState = uiState,
        retryCallBack = { viewModel.getCatBreedById(breedId) }
    )



}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreenContent(
    navController:NavController,
    uiState:CatBreedScreenUiState,
    retryCallBack:() -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            when(uiState){
                is CatBreedScreenUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),

                        ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                is CatBreedScreenUiState.Error -> {
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
                is CatBreedScreenUiState.Success -> {
                    val catBreed = uiState.catBreed
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        SubcomposeAsyncImage(
                            model = getCatImageById(imageId = catBreed.reference_image_id),
                            loading = {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(
                                            Alignment.Center
                                        )
                                    )
                                }
                            },
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentDescription = "Cat Images"
                        )
                        Text(text = "Cat breed : ${catBreed.name}")
                        Text(text = "Description : ${catBreed.description}")
                        Text(text = "Origin : ${catBreed.origin}")

                    }


                }
            }


        }

    }

}